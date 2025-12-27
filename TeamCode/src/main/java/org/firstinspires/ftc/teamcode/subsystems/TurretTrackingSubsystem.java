package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TurretTrackingSubsystem extends SubsystemBase {

    private DcMotor turretMotor;
    private Limelight3A limelight;

    // PID constants
    private double kP = 0.025;
    private double kI = 0.0;
    private double kD = 0.002;

    private double integral = 0;
    private double lastError = 0;

    private final double deadzone = 1.0;
   //adjust soft limits
    private final int MIN_TICKS = -850;
    private final int MAX_TICKS = 850;

    public TurretTrackingSubsystem(HardwareMap hardwareMap) {
        turretMotor = hardwareMap.get(DcMotor.class, "turretMotor");
        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        turretMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        turretMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        turretMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        limelight.start();
        limelight.pipelineSwitch(0);
    }

    @Override
    public void periodic() {
        LLResult result = limelight.getLatestResult();

        if (result == null || !result.isValid()) {
            turretMotor.setPower(0);
            integral = 0;
            lastError = 0;
            return;
        }

        double error = result.getTx();

        if (Math.abs(error) < deadzone) {
            turretMotor.setPower(0);
            integral = 0;
            lastError = 0;
            return;
        }

        // PID calculations
        integral += error;
        integral = Math.max(-100, Math.min(100, integral));

        double derivative = error - lastError;
        double power = kP * error + kI * integral + kD * derivative;
        lastError = error;

        int pos = turretMotor.getCurrentPosition();
        if ((pos <= MIN_TICKS && power < 0) || (pos >= MAX_TICKS && power > 0)) {
            power = 0;
        }

        power = Math.max(-0.6, Math.min(0.6, power));
        turretMotor.setPower(power);

        // --- TELEMETRY FOR PID TUNING ---
        telemetry.addData("Turret Pos", pos);
        telemetry.addData("PID Error", error);
        telemetry.addData("Motor Power", power);
        telemetry.addData("Integral", integral);
        telemetry.addData("Derivative", derivative);
        telemetry.update();
    }

    public void stop() {
        turretMotor.setPower(0);
    }
}

