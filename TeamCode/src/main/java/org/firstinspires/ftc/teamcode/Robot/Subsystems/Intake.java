package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.teamcode.FTC_Library.Robot.SubSystems.SubSystem;

public class Intake extends SubSystem {

    private String armName;
    private String lockName;
    private String releaseName;
    private String intakeName;
    private String limitName;

    private DcMotor arm;
    private DcMotor intake;
    private Servo lock;
    private Servo release;
    private DigitalChannel limit;


    private final double ARM_SPEED = 0.95;
    private final double DOWN_SPEED_DIFFERENCE = 0.15;//how much to slow down arm if going down to keep up with the help we are getting from gravity
    private final double MANUAL_SPEED_DIFFERENCE = 0.1;

    private final double LOCK_UP = 0.95;
    private final double LOCK_DOWN = 0.07;

    private final double RELEASE = 0.95;
    private final double UNRELEASE = 0.05;

    /*
    how many encoder ticks the target must change by before an updated command is sent to the motor
    this is mostly so if the joystick is not actively being manipulated, other commands can run
    */
    private final int TARGETING_DEADZONE = 1;
    private final int TARGET_DIFF = 55;//change target by this amount each time if not on target
    private final int MANUAL_TARGET_DIFF = TARGET_DIFF - 3;

    private double ARM_UP_TARGETING_DIFFERENCE = 2;// this is in motor rotations and multiplied in 'init' by encoder ticks per rev
    private double armTargetPosition = 0;
    private int armDownLocation = 0;
    private int armUpLocation = 0;

    @Override
    public boolean init(HardwareMap hardwareDevices) {

        arm = hardwareDevices.dcMotor.get(armName);
        intake = hardwareDevices.dcMotor.get(intakeName);
        lock = hardwareDevices.servo.get(lockName);
        release = hardwareDevices.servo.get(releaseName);

        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        arm.setDirection(DcMotorSimple.Direction.REVERSE);

        //do some stuff if we are using the limit switch
        if (limitName != null && !limitName.isEmpty()) {//make sure we actually declare name of limit switch
            limit = hardwareDevices.digitalChannel.get(limitName);
            limit.setMode(DigitalChannel.Mode.INPUT);//set limit switch to input

            ARM_UP_TARGETING_DIFFERENCE *= arm.getMotorType().getTicksPerRev();//add encoder ticks to the calculation
        }

        return true;
    }

    public Intake setMotorNames(String arm, String lock, String release, String intake) {
        lockName = lock;
        armName = arm;
        releaseName = release;
        intakeName = intake;
        return this;

    }

    public Intake setLimitName(String limit) {
        limitName = limit;
        return this;
    }

    public void driveArm(double power) {
        armTargetPosition += power * MANUAL_TARGET_DIFF;

        if (inDownPosition()) {//don't run motor if we are in the down position
            armDownLocation = arm.getCurrentPosition();
            armUpLocation = (int) (armDownLocation - ARM_UP_TARGETING_DIFFERENCE);//init the up arm position
        }
        if (inDownPosition() && power > 0) {//only reset target position if the driver is trying to go down more
            armTargetPosition = arm.getCurrentPosition();
        }

        //if (Math.abs(armTargetPosition - arm.getTargetPosition()) > TARGETING_DEADZONE) {
        if (Math.abs(power) > 0.01) {
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //arm.setTargetPosition(0);
            arm.setPower(ARM_SPEED-MANUAL_SPEED_DIFFERENCE);
            arm.setTargetPosition((int) armTargetPosition);
        }
    }

    public void goToDownPosition() {
        if (armDownLocation != 0) {//only move if we know correct positions
            //down is positive, down location will generally always be greater than the current position
            if (armDownLocation - armTargetPosition > TARGETING_DEADZONE) {
                goToArmTarget(getArmTarget() + TARGET_DIFF);
            }
        }
        arm.setPower(ARM_SPEED- DOWN_SPEED_DIFFERENCE);
    }

    public void goToUpPosition() {
        if (armDownLocation != 0) {//only move if we know correct positions
            //up is negative, up location will generally always be less than the current position
            if (armTargetPosition - armUpLocation > TARGETING_DEADZONE) {
                goToArmTarget(getArmTarget() - TARGET_DIFF);
            } else if (armTargetPosition - armUpLocation < -TARGETING_DEADZONE) {
                goToArmTarget(getArmTarget() + TARGET_DIFF);
            }
        }
    }


    public int getArmTarget() {
        return (int) armTargetPosition;
    }

    //go to arm target
    public void goToArmTarget(int position) {
        arm.setTargetPosition(position);
        armTargetPosition = position;
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(ARM_SPEED);
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

    public boolean inDownPosition() {
        if (limit != null) {
            return !limit.getState();
        }
        return false;
    }
}
