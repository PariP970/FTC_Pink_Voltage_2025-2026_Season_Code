package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TransferSubsystem extends SubsystemBase {

    private final DcMotor transferMotor;
    private static final double TRANSFER_SPEED = 0.5;

    public TransferSubsystem(HardwareMap hardwareMap) {
        transferMotor = hardwareMap.get(DcMotor.class, "transferMotor");

        transferMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        transferMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        transferMotor.setDirection(DcMotor.Direction.FORWARD);
    }

    public void run() {
        transferMotor.setPower(TRANSFER_SPEED);
    }

    public void stop() {
        transferMotor.setPower(0);
    }
}
