package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.commands.MechanumDrive;
import org.firstinspires.ftc.teamcode.commands.RunShooter;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.commands.RunIntake;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.commands.TurretTrackingCommand;
import org.firstinspires.ftc.teamcode.subsystems.TurretTrackingSubsystem;

@TeleOp(name = "TeleopCommandBased")
public class Teleop extends CommandOpMode {

    // --- Subsystems ---
    private DriveSubsystem driveSubsystem;
    private ShooterSubsystem shooterSubsystem;
    //private IntakeSubsystem intakeSubsystem;
    //private TurretTrackingSubsystem turretSubsystem;

    // --- Commands ---
    private RunShooter runShooter;
    //private RunIntake runIntakeForward;
    //private RunIntake runIntakeReverse;

    @Override
    public void initialize() {

        // --- Drive Initialization (DO NOT TOUCH) ---
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "FrontRight");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "BackLeft");
        //DcMotor backRight = hardwareMap.get(DcMotor.class, "BackRight");
        IMU imu = hardwareMap.get(IMU.class, "imu");

        driveSubsystem = new DriveSubsystem(frontLeft, frontRight, backLeft, /*backRight,*/ imu);
        register(driveSubsystem);

        driveSubsystem.setDefaultCommand(new MechanumDrive(
                driveSubsystem,
                () -> -gamepad1.left_stick_y,
                () -> gamepad1.right_stick_x,
                () -> gamepad1.left_stick_x
        ));

        // --- Shooter ---
        shooterSubsystem = new ShooterSubsystem(hardwareMap);
        register(shooterSubsystem);
        runShooter = new RunShooter(shooterSubsystem);

        // --- Intake (DISABLED) ---
        /*
        intakeSubsystem = new IntakeSubsystem(hardwareMap);
        register(intakeSubsystem);
        runIntakeForward = new RunIntake(intakeSubsystem, true);
        runIntakeReverse = new RunIntake(intakeSubsystem, false);
        */

        // --- Turret ---
        /*
        turretSubsystem = new TurretTrackingSubsystem(hardwareMap);
        register(turretSubsystem);
        turretSubsystem.setDefaultCommand(new TurretTrackingCommand(turretSubsystem));
        */
    }

    @Override
    public void run() {
        super.run();

        // --- Shooter Control (RIGHT trigger) ---
        if (gamepad1.right_trigger > 0.1) {
            if (!runShooter.isScheduled()) runShooter.schedule();
        } else {
            if (runShooter.isScheduled()) runShooter.cancel();
            shooterSubsystem.stop();
        }

        // --- Intake Controls DISABLED ---
        /*
        if (gamepad1.left_trigger > 0.1) {
            if (!runIntakeForward.isScheduled()) runIntakeForward.schedule();
        } else {
            if (runIntakeForward.isScheduled()) runIntakeForward.cancel();
            intakeSubsystem.stop();
        }

        if (gamepad1.b) {
            if (!runIntakeReverse.isScheduled()) runIntakeReverse.schedule();
        } else {
            if (runIntakeReverse.isScheduled()) runIntakeReverse.cancel();
            intakeSubsystem.stop();
        }
        */
    }
}
