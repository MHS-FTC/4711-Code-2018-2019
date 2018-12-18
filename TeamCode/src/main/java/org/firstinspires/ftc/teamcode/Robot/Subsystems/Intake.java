package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FTC_API.Robot.SubSystems.SubSystem;

public class Intake extends SubSystem
{

    private String armName;
    private String lockName;
    private String releaseName;

    private DcMotor arm;
    private Servo lock;
    private Servo release;

    private final double ARM_SPEED = 0.7;

    private final double LOCK_UP = 1;
    private final double LOCK_DOWN = 0;

    private final double RELEASE = 1;
    private final double UNRELEASE = 0;


    @Override
    public boolean init(HardwareMap hardwareDevices) {

        arm = hardwareDevices.dcMotor.get(armName);
        lock = hardwareDevices.servo.get(lockName);
        release = hardwareDevices.servo.get(releaseName);

        return true;
    }

    public Intake setMotorNames(String arm, String lock, String release ){
        lockName = lock;
        armName = arm;
        releaseName = release;
        return this;

    }

    public void driveArm(double up, double down) {
        double armControl = up + down;

        arm.setPower(armControl);

    }

    public void lockUp() { lock.setPosition(LOCK_UP); }

    public void lockDown() {lock.setPosition(LOCK_DOWN); }

    public void releaseUp(){release.setPosition(RELEASE); }

    public void releaseDown() {release.setPosition((UNRELEASE));}
}
