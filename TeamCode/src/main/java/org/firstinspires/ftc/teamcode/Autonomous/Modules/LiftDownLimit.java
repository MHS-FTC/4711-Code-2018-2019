package org.firstinspires.ftc.teamcode.Autonomous.Modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Lifter;

public class LiftDownLimit extends Module {
    private Lifter lift;
    private DigitalChannel limit;
    private final double ROTATIONS = 25.3;
    private boolean isDone = false;

    @Override
    public void start() {
        lift = (Lifter) robot.getSubSystem("Lifter");
        limit = robot.hardwareMap.digitalChannel.get("limit");

        limit.setMode(DigitalChannel.Mode.INPUT);//set limit switch to input

        lift.getMotor().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.getMotor().setTargetPosition((int) (ROTATIONS * lift.getMotor().getMotorType().getTicksPerRev()));
        lift.getMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.liftUp();
    }

    @Override
    public void tick() {
        //if limit switch has been pressed then stop
        if (!limit.getState()||!lift.getMotor().isBusy()) {
            isDone = true;
        }
        telemetry.addLine("Limit status: " + limit.getState());
    }

    @Override
    public int stop() {
        lift.liftStop();
        lift.getMotor().setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        return positionInArray;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }
}
