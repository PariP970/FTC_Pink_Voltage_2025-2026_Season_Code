package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ShooterSubsystem extends SubsystemBase {
    private final DcMotor shooterMotor;
    private final double SHOOTER_SPEED = 1.0; // adjust as needed yup

    public ShooterSubsystem(HardwareMap hardwareMap) {
        shooterMotor = hardwareMap.get(DcMotor.class, "shooterMotor");
        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void spin() {
        shooterMotor.setPower(SHOOTER_SPEED);
    }

    public void stop() {
        shooterMotor.setPower(0);
    }
}
