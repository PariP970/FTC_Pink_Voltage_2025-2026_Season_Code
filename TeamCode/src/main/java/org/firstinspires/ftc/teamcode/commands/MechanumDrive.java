package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;

public class MechanumDrive extends CommandBase {

    private final DriveSubsystem driveSubsystem;
    private final DoubleSupplier leftY, leftX, rightX; // Joystick suppliers

    public MechanumDrive(DriveSubsystem driveSubsystem,
                         DoubleSupplier leftY,
                         DoubleSupplier rightX,
                         DoubleSupplier leftX) {
        this.driveSubsystem = driveSubsystem;
        this.leftY = leftY;   // forward/back
        this.leftX = leftX;   // strafe
        this.rightX = rightX; // rotation

        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        // Invert Y axis so pushing joystick forward moves robot forward
        double drive = -leftY.getAsDouble();  // <- fix forward/back
        double strafe = leftX.getAsDouble();  // positive right, negative left
        double turn = rightX.getAsDouble();

        driveSubsystem.mecanumDriveFieldOriented(drive, strafe, turn);
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
