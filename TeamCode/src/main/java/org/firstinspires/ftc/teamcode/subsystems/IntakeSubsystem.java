package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeSubsystem extends SubsystemBase {

    private final DcMotor intakeMotor;

    private static final double INTAKE_SPEED = 1.0;

    public IntakeSubsystem(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");

        // CRITICAL CONFIG
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
    }

    public void runForward() {
        intakeMotor.setPower(INTAKE_SPEED);
    }

    public void runReverse() {
        intakeMotor.setPower(-INTAKE_SPEED);
    }

    public void stop() {
        intakeMotor.setPower(0);
    }
}
