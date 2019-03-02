package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.FTC_API.Robot.SubSystems.SubSystem;

public class Lifter extends SubSystem {

    private String liftName;
    private String limitName;

    private DcMotor lift;
    private DigitalChannel limit;

    private final double LIFT_SPEED = 1;
    private final int STOP_WAIT = 200;//wait before engaging positioning

    private long stoppedTime;

    //TODO have a preset value for upEncoderPosition so it can do an estimate if needed
    private int PRE_LIFT_ENCODER_TICKS = (int) (8.5 * 600);//TODO adjust this value
    private int upEncoderPosition = PRE_LIFT_ENCODER_TICKS;//the position of the encoder when lifter is all the way up//starts not moving
    //1500 is 2.5 cm
    //32.5 - 24.5 = 8.5

    @Override
    public boolean init(HardwareMap hardwareDevices) {

        lift = hardwareDevices.dcMotor.get(liftName);

        lift.setDirection(DcMotorSimple.Direction.REVERSE);
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setTargetPosition(0);
        lift.setPower(0.1);//this should be enough power to keep motor at the correct position

        limit = hardwareDevices.digitalChannel.get(limitName);
        limit.setMode(DigitalChannel.Mode.INPUT);//set limit switch to input

        return true;
    }

    public Lifter setDeviceNames(String lift, String limitSwitch) {
        liftName = lift;
        limitName = limitSwitch;
        return this;

    }

    public void liftUp() {
        if (!isPressed()) {
            lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lift.setPower(LIFT_SPEED);
        } else {
            //hold position if pressed
            liftStop();
        }

    }

    public void liftDown() {
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift.setPower(-LIFT_SPEED);
    }

    //kind of an artificial stop designed to work for hanging
    public void liftStop() {
        if (lift.getPower() != 0.1 && lift.getPower() != 0) {
            lift.setPower(0);
            stoppedTime = System.currentTimeMillis();
        }
        if (stoppedTime + STOP_WAIT < System.currentTimeMillis()) {//if has stopped
            lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lift.setTargetPosition(lift.getCurrentPosition());
            lift.setPower(0.1);//this should be enough power to keep motor at the correct position
        }
    }

    public void goToPreLiftHeight() {
        isPressed();
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setTargetPosition(upEncoderPosition - PRE_LIFT_ENCODER_TICKS);
        lift.setPower(LIFT_SPEED);
    }

    public boolean isPressed() {
        boolean pressed = !limit.getState();
        if (pressed) {
            upEncoderPosition = lift.getCurrentPosition();
        }
        return pressed;
    }

    public DcMotor getMotor() {
        return lift;
    }
}
