package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;


import java.util.function.DoubleSupplier;


public class MechanumDrive extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private DoubleSupplier driveMotion, turnMotion, strafeMotion; //to collect joystick input

    private RevHubOrientationOnRobot revHubOrientationOnRobot = new RevHubOrientationOnRobot(
            RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
            RevHubOrientationOnRobot.UsbFacingDirection.UP
    );

    public MechanumDrive(DriveSubsystem driveSubsystem, DoubleSupplier driveMotion,
                         DoubleSupplier turnMotion, DoubleSupplier strafeMotion){

        this.driveSubsystem = driveSubsystem;
        this.driveMotion = driveMotion;
        this.turnMotion = turnMotion;
        this.strafeMotion = strafeMotion;

        addRequirements(driveSubsystem);

    }
    @Override
    public void initialize(){
        //telemetry.addLine("Started");
        //telemetry.update();
    }
    @Override
    public void execute(){
        driveSubsystem.MecanumDriveFeildOri(driveMotion.getAsDouble(), turnMotion.getAsDouble(), strafeMotion.getAsDouble());
    }
    @Override
    public void end(boolean interrupted){
        driveSubsystem.stop();
    }
    @Override
    public boolean isFinished(){
        return false; //command should not end unless interrupted
    }
}