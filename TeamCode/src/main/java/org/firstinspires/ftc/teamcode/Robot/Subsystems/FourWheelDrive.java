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


public class FourWheelDrive extends SidedDriveSystemTemplate {
    private DcMotor leftFrontMotor;
    private DcMotor rightFrontMotor;
    private DcMotor leftBackMotor;
    private DcMotor rightBackMotor;

    private String LFMName;
    private String RFMName;
    private String LBMName;
    private String RBMName;


    @Override
    public boolean init(HardwareMap hardwareDevices) {
        leftFrontMotor = hardwareDevices.dcMotor.get(LFMName);
        rightFrontMotor = hardwareDevices.dcMotor.get(RFMName);
        leftBackMotor = hardwareDevices.dcMotor.get(LBMName);
        rightBackMotor = hardwareDevices.dcMotor.get(RBMName);

        leftFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        return true;
    }

    public FourWheelDrive setMotorNames(String leftFront, String rightFront, String leftBack, String rightBack) {
        LFMName = leftFront;
        RFMName = rightFront;
        LBMName = leftBack;
        RBMName = rightBack;
        return this;
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

    /**
     * TODO: should dead zone joystick to insure we are not burning out motors
     *
     * @param forward how much to go forward and backwards, from -1 to 1, 1 is full forwards
     * @param rotate  how much to rotate, from -1 to 1, 1 is full right
     */
    public void driveArcade(double forward, double rotate) {
        double left = forward + rotate;
        double right = forward - rotate;

        driveTank(left, right);
    }

    @Override
    public void driveMecanum(double forward, double turn, double strafe) {
        driveArcade(forward, turn);
    }

    @Override
    public DcMotor[] getRightSideMotors() {
        return new DcMotor[]{rightFrontMotor, rightBackMotor};
    }

    @Override
    public DcMotor[] getLeftSideMotors() {
        return new DcMotor[]{rightFrontMotor, rightBackMotor};
    }

}
