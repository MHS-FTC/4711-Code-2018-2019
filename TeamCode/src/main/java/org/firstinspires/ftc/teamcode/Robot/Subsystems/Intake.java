package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FTC_API.Robot.SubSystems.SubSystem;

public class Intake extends SubSystem {

    private String armName;
    private String lockName;
    private String releaseName;
    private String intakeName;

    private DcMotor arm;
    private Servo lock;
    private Servo release;
    private CRServo intake;


    private final double ARM_SPEED = 0.8;

    private final double LOCK_UP = 0.95;
    private final double LOCK_DOWN = 0.07;

    private final double RELEASE = 0.95;
    private final double UNRELEASE = 0.05;

    private double armTargetPosition = 0;

    @Override
    public boolean init(HardwareMap hardwareDevices) {

        arm = hardwareDevices.dcMotor.get(armName);
        lock = hardwareDevices.servo.get(lockName);
        release = hardwareDevices.servo.get(releaseName);
        intake = hardwareDevices.crservo.get(intakeName);

        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setTargetPosition(0);
        arm.setPower(ARM_SPEED);

        return true;
    }

    public Intake setMotorNames(String arm, String lock, String release, String intake) {
        lockName = lock;
        armName = arm;
        releaseName = release;
        intakeName = intake;
        return this;

    }

    public void driveArm(double power) {
        armTargetPosition += power * 15;

        arm.setTargetPosition((int) armTargetPosition);
    }

    public int getArmTarget(){
        return (int) armTargetPosition;
    }

    public void intakeSpeed(double speed) {
        intake.setPower(speed);
    }

    public void lockUp() {
        lock.setPosition(LOCK_UP);
    }

    public void lockDown() {
        lock.setPosition(LOCK_DOWN);
    }

    public void releaseUp() {
        release.setPosition(RELEASE);
    }

    public void releaseDown() {
        release.setPosition((UNRELEASE));
    }
}
