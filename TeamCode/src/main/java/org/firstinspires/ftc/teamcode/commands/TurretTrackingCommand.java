package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.TurretTrackingSubsystem;

public class TurretTrackingCommand extends CommandBase {

    private final TurretTrackingSubsystem turret;

    public TurretTrackingCommand(TurretTrackingSubsystem turret) {
        this.turret = turret;
        addRequirements(turret);
    }

    @Override
    public void execute() {
        turret.periodic(); // runs your PID auto-trackingg
    }

    @Override
    public boolean isFinished() {
        return false; // never ends; always runs as default
    }
}
