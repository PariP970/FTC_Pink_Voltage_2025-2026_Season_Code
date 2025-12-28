package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.commands.MechanumDrive;

@TeleOp(name = "TeleopCommandBased")
public class Teleop extends CommandOpMode {

    private DriveSubsystem driveSubsystem;

    @Override
    public void initialize() {

        // --- Drive Initialization ---
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "FrontRight");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "BackLeft");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "BackRight");
        IMU imu = hardwareMap.get(IMU.class, "imu");

        driveSubsystem = new DriveSubsystem(frontLeft, frontRight, backLeft, backRight, imu);
        register(driveSubsystem);

        // --- Default Drive Command ---
        driveSubsystem.setDefaultCommand(new MechanumDrive(
                driveSubsystem,
                () -> -gamepad1.left_stick_y,   // forward/back corrected
                () -> gamepad1.right_stick_x,   // rotation
                () -> gamepad1.left_stick_x     // strafe
        ));
    }
}
