//package org.firstinspires.ftc.teamcode.subsystems;
//
//import com.arcrobotics.ftclib.command.SubsystemBase;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//public class IntakeSubsystem extends SubsystemBase {
//    private final DcMotor intakeMotor;
//
//    private final double INTAKE_SPEED = 1.0; // fixed speed
//
//    public IntakeSubsystem(HardwareMap hardwareMap) {
//        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
//        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//    }
//
//    // Run intake forward at fixed speed y
//    public void runForward() {
//        intakeMotor.setPower(INTAKE_SPEED);
//    }
//
//    // Run intake reverse at fixed speed
//    public void runReverse() {
//        intakeMotor.setPower(-INTAKE_SPEED);
//    }
//
//    // Run intake at arbitrary power (optional, for flexibility)
//    public void run(double power) {
//        intakeMotor.setPower(power);
//    }
//
//    // Stop intake
//    public void stop() {
//        intakeMotor.setPower(0);
//    }
//}
