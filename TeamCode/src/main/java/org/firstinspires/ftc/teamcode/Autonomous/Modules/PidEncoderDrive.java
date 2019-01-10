package org.firstinspires.ftc.teamcode.Autonomous.Modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.FTC_API.Robot.SubSystems.SidedDriveSystemTemplate;
import org.firstinspires.ftc.teamcode.Utilitys.PIDController;

public class PidEncoderDrive extends Module {
    private SidedDriveSystemTemplate drive;
    private boolean isDone = false;
    private double startTime;//TODO add watchdog

    private double leftRotations;
    private double rightRotations;

    private double leftSpeed;
    private double rightSpeed;
    private PIDController leftController;
    private PIDController rightController;
    private double wheelCircumference;//TODO create location to store constant (perhaps static?)

    @Override
    public void start() {
        if (robot.getDriveSystem() instanceof SidedDriveSystemTemplate) {
            drive = (SidedDriveSystemTemplate) robot.getDriveSystem();
        } else {
            isDone = true;//if not a sided drive system, then exit
            telemetry.log().add("Need SidedDriveSystem");
        }

        if (wheelCircumference == 0) {
            isDone = true;
            telemetry.log().add("Please add wheel circumference");
        }

        startTime = robot.getTimeMilliseconds();


        /* // TODO: 12/2/18 Probably don't need this part
        //create speed for each motors in order to scale properly
        leftSpeed = speed;
        rightSpeed = speed;


        //scale speed so that turns are relatively smooth, doesn't change anything if they are the same
        //note that we are scaling by 2 so we have a differential effort

        if (Math.abs(leftRotations) > Math.abs(rightRotations)) {
            double scale = Math.abs(rightRotations) / Math.abs(leftRotations);
            rightSpeed = rightSpeed * scale;
        } else if (Math.abs(rightRotations) > Math.abs(leftRotations)) {
            double scale = Math.abs(leftRotations) / Math.abs(rightRotations);
            leftSpeed = leftSpeed * scale;
        }
        */


        //sets targets
        long leftTarget = -(int) (leftRotations * drive.getMotorType().getTicksPerRev());
        long rightTarget = -(int) (rightRotations * drive.getMotorType().getTicksPerRev());

        drive.resetAllEncoders();

        //drive.setLeftSideTarget(leftTarget);//TODO may not need this
        //drive.setRightSideTarget(rightTarget);
        //drive.runToPositionAllEncoders();
        drive.runUsingAllEncoders();


        leftController.setTarget(leftTarget, 0);//set PID controllers
        rightController.setTarget(rightTarget, 0);

        //if either motor doesn't need to move then don't move it
        if (leftTarget == 0) {
            leftSpeed = 0;
        }
        if (rightTarget == 0) {
            rightSpeed = 0;
        }

        drive.driveTank(leftSpeed, rightSpeed);//todo determine starting speed

        if (hasTelemetry()) {
            telemetry.log().add("Right Target:" + rightTarget + " Left Target:" + leftTarget);
            telemetry.log().add("Right Rotations:" + rightRotations + " Left Rotations:" + leftRotations);
            //telemetry.log().add("Position in Array:" + positionInArray);
        }
    }

    @Override
    public void tick() {
        int currentLeft = 0;
        int motorsLeft = 0;
        int currentRight = 0;
        int motorsRight = 0;

        for (DcMotor motor : drive.getLeftSideMotors()) {
            currentLeft += motor.getCurrentPosition();
            motorsLeft++;
        }
        for (DcMotor motor : drive.getRightSideMotors()) {
            currentRight += motor.getCurrentPosition();
            motorsRight++;
        }

        if (motorsRight == 0 || motorsLeft == 0) {
            //need at least one motor per side
            telemetry.log().add("One or both sides of the robot don't have motors!");
            isDone = true;
            return;
        }

        //update speed so as to not overshoot
        leftSpeed = leftController.getOutput(currentLeft / motorsLeft);
        rightSpeed = rightController.getOutput(currentRight / motorsRight);
        drive.driveTank(leftSpeed, rightSpeed);

        telemetry.addLine("Left Speed:" + leftSpeed + ", Right Speed:" + rightSpeed);


        //stop if on target, and end if both are on target
        if (leftController.isOnTarget()) {
            drive.stopLeftMotors();
        }
        if (rightController.isOnTarget()) {
            drive.stopRightMotors();
        }
        if (leftController.isOnTarget() && rightController.isOnTarget()) {
            isDone = true;
        }
    }


    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public int stop() {
        drive.runUsingAllEncoders();
        //just pass through the position, this allows for "multithreaded" things that can be called
        return positionInArray;
    }

    /**
     * Resets the position in array number so you can changing it in the next step
     *
     * @return this object for building
     */
    public PidEncoderDrive resetPositionInArray() {
        positionInArray = 0;
        return this;
    }


    public PidEncoderDrive setPID(double p, double i, double d, double settlingTime, int tolerance) {
        leftController = new PIDController(p, i, d, 0, tolerance, settlingTime);
        rightController = new PIDController(p, i, d, 0, tolerance, settlingTime);

        leftController.setNoOscillation(true);//we don't want to change direction to correct to target
        rightController.setNoOscillation(true);

        final double max = 0.8;
        leftController.setOutputRange(-max, max);
        rightController.setOutputRange(-max, max);
        return this;
    }

    /**
     * @param distanceLeft  distance in inches the left motor(s) should go
     * @param distanceRight distance in inches the right motor(s) should go
     * @return this object for building
     */
    public PidEncoderDrive setDistances(double distanceLeft, double distanceRight) {
        leftRotations = distanceLeft / wheelCircumference;
        rightRotations = distanceRight / wheelCircumference;
        return this;
    }

    public PidEncoderDrive setWheelCircumference(double circumference) {
        wheelCircumference = circumference;
        return this;
    }

    private boolean isDoneAtPosition(double current, double target) {
        if (target >= 0) {//target is positive
            return current + 15 >= target || current - 15 >= target;
        } else {//otherwise it must be negative and should be treated as such
            return current + 15 <= target || current - 15 <= target;
        }
    }

    public PidEncoderDrive setConfig(PIDConfig config){
        setWheelCircumference(config.wheelCircumference);
        setPID(config.p, config.i, config.d, config.settlingTime, config.tolerance);
        return this;
    }
    public static class PIDConfig {
        private double p, i, d, wheelCircumference, settlingTime;
        private int tolerance;
        private double maxSpeed;

        public PIDConfig setPID(double p, double i, double d){
            this.p = p;
            this.i = i;
            this.d = d;
            return this;
        }

        public double getP() {
            return p;
        }

        public PIDConfig setP(double p) {
            this.p = p;
            return this;
        }

        public double getI() {
            return i;
        }

        public PIDConfig setI(double i) {
            this.i = i;
            return this;
        }

        public double getD() {
            return d;
        }

        public PIDConfig setD(double d) {
            this.d = d;
            return this;
        }

        public double getWheelCircumference() {
            return wheelCircumference;
        }

        public PIDConfig setWheelCircumference(double wheelCircumference) {
            this.wheelCircumference = wheelCircumference;
            return this;
        }

        public double getSettlingTime() {
            return settlingTime;
        }

        public PIDConfig setSettlingTime(double settlingTime) {
            this.settlingTime = settlingTime;
            return this;
        }

        public int getTolerance() {
            return tolerance;
        }

        public PIDConfig setTolerance(int tolerance) {
            this.tolerance = tolerance;
            return this;
        }

        public double getMaxSpeed() {
            return maxSpeed;
        }

        public PIDConfig setMaxSpeed(double maxSpeed) {
            this.maxSpeed = maxSpeed;
            return this;
        }
    }
}
