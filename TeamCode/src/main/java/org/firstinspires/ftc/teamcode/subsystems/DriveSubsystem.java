

package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class DriveSubsystem extends SubsystemBase {
    private DcMotor frontLeft, frontRight, backLeft, backRight;
    //might need to look into sequintal and parrelel command groups in futurw
    private IMU imu;



    public DriveSubsystem(DcMotor frontLeft,
                          DcMotor frontRight,
                          DcMotor backLeft,
                          DcMotor backRight, IMU imu
    ){
        //assign all wheels to object varible
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;
        this.imu = imu;

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        imu.initialize(new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
                )
        ));

    }
//mmm
    public void MecanumDrive(double driveMotion, double turnMotion, double strafeMotion){
        double speedReducer = 0.5;
        double controlledCap = Math.max(Math.abs(driveMotion)+ Math.abs(turnMotion)+ Math.abs(strafeMotion), 1);
        frontLeft.setPower(((driveMotion + turnMotion + strafeMotion)/ controlledCap) * speedReducer);
        frontRight.setPower(((driveMotion - turnMotion - strafeMotion)/ controlledCap) * speedReducer);
        backLeft.setPower(((driveMotion + turnMotion - strafeMotion) / controlledCap) * speedReducer);
        backRight.setPower(((driveMotion - turnMotion + strafeMotion) / controlledCap) * speedReducer);
    }
    public void MecanumDriveFeildOri(double driveMotion, double turnMotion, double strafeMotion){
        //field ori math is wrong!
        double robotHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        double adjStrafe = strafeMotion * 1.1;

        double rotX = adjStrafe * Math.cos(-robotHeading) - driveMotion * Math.sin(-robotHeading);
        double rotY = adjStrafe * Math.sin(-robotHeading) + driveMotion * Math.cos(-robotHeading);

        //rotY is the new drive and rotX is the new strafe
        MecanumDrive(rotY, turnMotion, rotX);





    }

    public void imuIntilization(RevHubOrientationOnRobot revHubOrientationOnRobot){
        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));
    }
    public void stop(){
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}