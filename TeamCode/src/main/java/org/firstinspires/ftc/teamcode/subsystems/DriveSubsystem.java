package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class DriveSubsystem extends SubsystemBase {

    private final DcMotor frontLeft, frontRight, backLeft, backRight;
    private final IMU imu;

    private final double speedReducer = 0.6; // optional TeleOp speed scaling

    public DriveSubsystem(DcMotor frontLeft, DcMotor frontRight,
                          DcMotor backLeft, DcMotor backRight,
                          IMU imu) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;
        this.imu = imu;

        // -------- MOTOR DIRECTIONS --------
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);  // FL physically reversed
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);  // BR physically reversed
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);

        // -------- ENCODER RESET --------
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // -------- ZERO POWER BEHAVIOR --------
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // -------- IMU INITIALIZATION --------
        imu.initialize(new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
                )
        ));
    }

    // -------- ROBOT-ORIENTED MECANUM DRIVE --------
    public void mecanumDrive(double drive, double strafe, double turn) {
        double denominator = Math.max(Math.abs(drive) + Math.abs(strafe) + Math.abs(turn), 1.0);

        frontLeft.setPower((drive + strafe + turn) / denominator * speedReducer);
        frontRight.setPower((drive - strafe - turn) / denominator * speedReducer);
        backLeft.setPower((drive - strafe + turn) / denominator * speedReducer);
        backRight.setPower((drive + strafe - turn) / denominator * speedReducer);
    }

    // -------- FIELD-ORIENTED MECANUM DRIVE --------
    public void mecanumDriveFieldOriented(double drive, double strafe, double turn) {
        double heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double tempX = strafe * Math.cos(-heading) - drive * Math.sin(-heading);
        double tempY = strafe * Math.sin(-heading) + drive * Math.cos(-heading);

        // Call mecanumDrive with correct order
        mecanumDrive(tempY, tempX, turn);
    }

    // -------- STOP MOTORS --------
    public void stop() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}
