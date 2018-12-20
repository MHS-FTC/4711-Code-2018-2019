package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.robotcore.external.Telemetry;
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


    private final double ARM_SPEED = 0.7;

    private final double UNLOCKED = 0.95;
    private final double LOCKED = 0.07;

    private final double RELEASE = 0.95;
    private final double UNRELEASE = 0.05;

    private boolean armLocked = false;
    private int previousArmPosition = 0;
    private long previousTime = 0;
    private double armCorrection = 0;
    private int previousTPS = 1;

    @Override
    public boolean init(HardwareMap hardwareDevices) {

        arm = hardwareDevices.dcMotor.get(armName);
        lock = hardwareDevices.servo.get(lockName);
        release = hardwareDevices.servo.get(releaseName);
        intake = hardwareDevices.crservo.get(intakeName);

        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        previousArmPosition = arm.getCurrentPosition();
        previousTime = System.currentTimeMillis();

        return true;
    }

    public Intake setMotorNames(String arm, String lock, String release, String intake) {
        lockName = lock;
        armName = arm;
        releaseName = release;
        intakeName = intake;
        return this;

    }

    public void driveArm(double speed, Telemetry telemetry) {
        double armControl = speed * 0.5;//always limited to the multiplied number
        double askingTPS = speed * 2 * arm.getMotorType().getTicksPerRev();//-1 to 1 * 2 rotations of small motor per second
        //calculate TPS
        int ticksPerSecond = previousTPS;
        if (previousTime + 100 < System.currentTimeMillis()) {//every 10th of second, recalculate speed
            ticksPerSecond = (int) ((arm.getCurrentPosition() - previousArmPosition) / (System.currentTimeMillis() / 1000.0 - previousTime / 1000.0));
            //ticksPerSecond = (int) ((ticksPerSecond * 1.8 + previousTPS * 0.2)/2);//apply weighted average to increase reliability
            previousArmPosition = arm.getCurrentPosition();
            previousTime = System.currentTimeMillis();
            previousTPS = ticksPerSecond;
        }

        if(askingTPS - 50 > ticksPerSecond){
            //too slow
            armCorrection += 0.03;
        }else if (askingTPS + 50 < ticksPerSecond){
            //too fast
            armCorrection -= 0.03;
        }

        armControl = armControl + armCorrection;//add correction to speed positive



        //limit power of correction
        if(armCorrection > 0.3){
            armCorrection = 0.3;
        }
        if(armCorrection < -0.3){
            armCorrection = -0.3;
        }


        telemetry.addLine("Correction: "+ armCorrection);
        telemetry.addLine("Arm Speed w/ c: "+ armControl);
        telemetry.addLine("Raw speed: " + speed *0.5);
        telemetry.addLine("current TPS: " + ticksPerSecond);
        telemetry.addLine("Target TPS: " + askingTPS);

        /*
        int askingTPS = speed *

        if (previousSetSpeed - 0.05 < speed && previousSetSpeed + 0.05 > speed && previousTime + 100 < System.currentTimeMillis()) {
            armControl = previousActualSpeed;//if the current asking speed is close to the previous one and less than 10th sec, just go with that
        } else {
            int ticksPerSecond = previousTPS;
            if (previousTime + 100 < System.currentTimeMillis()) {//every 10th of second, recalculate speed
                ticksPerSecond = (int) ((arm.getCurrentPosition() - previousArmPosition) / (System.currentTimeMillis()-previousTime));
                previousArmPosition= arm.getCurrentPosition();
                previousTime = System.currentTimeMillis();
                previousTPS = ticksPerSecond;
            }
        }
        */


        arm.setPower(armControl);
    }

    public void intakeSpeed(double speed) {
        intake.setPower(speed);
    }

    public void lockUp() {
        lock.setPosition(UNLOCKED);
        armLocked = false;
    }

    public void lockDown() {
        lock.setPosition(LOCKED);
        armLocked = true;
    }

    public void releaseUp() {
        release.setPosition(RELEASE);
    }

    public void releaseDown() {
        release.setPosition((UNRELEASE));
    }
}
