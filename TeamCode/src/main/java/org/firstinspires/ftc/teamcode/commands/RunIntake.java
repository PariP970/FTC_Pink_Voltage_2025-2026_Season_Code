package org.firstinspires.ftc.teamcode.commands;
import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TransferSubsystem;

public class RunIntake extends CommandBase {

    private final IntakeSubsystem intake;
    private final TransferSubsystem transfer;

    public RunIntake(IntakeSubsystem intake, TransferSubsystem transfer) {
        this.intake = intake;
        this.transfer = transfer;
        addRequirements(intake, transfer);
    }

    @Override
    public void execute() {
        intake.runForward();
        transfer.run();
    }

    @Override
    public void end(boolean interrupted) {
        intake.stop();
        transfer.stop();
    }

    @Override
    public boolean isFinished() {
        return false; // runs ONLY while held
    }
}

//package org.firstinspires.ftc.teamcode.commands;
//import com.arcrobotics.ftclib.command.CommandOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.IMU;
//import com.arcrobotics.ftclib.command.CommandBase;
//import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
//
//public class RunIntake extends CommandBase {
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
//            intake.runForward();
//        } else {
//            intake.runReverse();
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


