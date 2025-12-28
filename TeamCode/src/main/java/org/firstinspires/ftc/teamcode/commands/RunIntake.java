//package org.firstinspires.ftc.teamcode.commands;
////help
//import com.arcrobotics.ftclib.command.CommandBase;
//import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
//
//public class RunIntake extends CommandBase {
//
//    private final IntakeSubsystem intake;
//    private final boolean forward; // true = forward, false = reverse
//
//    public RunIntake(IntakeSubsystem intake, boolean forward) {
//        this.intake = intake;
//        this.forward = forward;
//        addRequirements(intake);
//    }
//
//    @Override
//    public void execute() {
//        if (forward) {
//            intake.runForward(); // fixed speed forward
//        } else {
//            intake.runReverse(); // fixed speed reverse
//        }
//    }
//
//    @Override
//    public void end(boolean interrupted) {
//        intake.stop();
//    }
//
//    @Override
//    public boolean isFinished() {
//        return false; // runs until cancelled
//    }
//}
//
