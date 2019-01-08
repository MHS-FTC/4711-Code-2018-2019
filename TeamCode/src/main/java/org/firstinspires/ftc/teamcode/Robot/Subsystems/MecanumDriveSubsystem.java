package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.FTC_API.Robot.SubSystems.SidedDriveSystemTemplate;

/**
 * Created by Ethan Hampton on 8/19/17.
 * <p>
 * Simple TwoWheelDrive class that can be implemented and used
 */


public class MecanumDriveSubsystem extends SidedDriveSystemTemplate {
    protected DcMotor leftFrontMotor;
    protected DcMotor rightFrontMotor;
    protected DcMotor leftBackMotor;
    protected DcMotor rightBackMotor;

    public static final String ID = "MecanumWheelDrive";

    private String leftFrontMotorName;
    private String rightFrontMotorName;
    private String leftBackMotorName;
    private String rightBackMotorName;
    private boolean usingEncoders = false;

    @Override
    public boolean init(HardwareMap hardwareDevices) {
        leftFrontMotor = hardwareDevices.dcMotor.get(leftFrontMotorName);
        rightFrontMotor = hardwareDevices.dcMotor.get(rightFrontMotorName);
        leftBackMotor = hardwareDevices.dcMotor.get(leftBackMotorName);
        rightBackMotor = hardwareDevices.dcMotor.get(rightBackMotorName);

        leftFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        if (usingEncoders) {
            runUsingAllEncoders();//use encoders to give more precise speed
        } else {
            runNotUsingAllEncoders();
        }
        return true;
    }

    @Override
    public DcMotor[] getRightSideMotors() {
        return new DcMotor[]{rightBackMotor, rightFrontMotor};
    }

    @Override
    public DcMotor[] getLeftSideMotors() {
        return new DcMotor[]{leftBackMotor, leftFrontMotor};
    }

    /**
     * @param leftPower  how fast the left motors should go
     * @param rightPower how fast the right motors should go
     */
    public void driveTank(double leftPower, double rightPower) {
        leftFrontMotor.setPower(leftPower);
        leftBackMotor.setPower(leftPower);
        rightFrontMotor.setPower(rightPower);
        rightBackMotor.setPower(rightPower);
    }

    @Override
    public void driveArcade(double forward, double turn) {
        double left = forward + turn;
        double right = forward - turn;

        driveTank(left, right);
    }


    /**
     * TODO: should dead zone joystick to insure we are not burning out motors
     *
     * @param forward how much to go forward and backwards, from -1 to 1, 1 is full forwards
     * @param rotate  how much to rotate, from -1 to 1, 1 is full right
     * @param strafe  how much to strafe, from -1 to 1, 1 is full right. left x = strafe
     */
    @Override
    public void driveMecanum(double forward, double rotate, double strafe) {
        double frontLeft = forward + rotate + strafe;
        double rearLeft = forward + rotate - strafe;
        double frontRight = forward - rotate + strafe;
        double rearRight = forward - rotate - strafe;

        leftFrontMotor.setPower(frontLeft);
        leftBackMotor.setPower(rearLeft);
        rightFrontMotor.setPower(frontRight);
        rightBackMotor.setPower(rearRight);

        // Where:
        // Ch1 = Right joystick X-axis
        // Ch3 = Left joystick Y-axis
        // Ch4 = Left joystick X-axis
    }

    public MecanumDriveSubsystem setMotorNames(String leftFront, String rightFront, String leftBack, String rightBack) {
        leftFrontMotorName = leftFront;
        rightFrontMotorName = rightFront;
        leftBackMotorName = leftBack;
        rightBackMotorName = rightBack;
        return this;
    }

    public MecanumDriveSubsystem setUsingEncoders(boolean usingEncoders) {
        this.usingEncoders = usingEncoders;
        return this;
    }
}