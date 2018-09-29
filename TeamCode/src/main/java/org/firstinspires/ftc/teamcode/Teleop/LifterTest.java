package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot.Robot;

public class LifterTest extends OpMode {
    private Robot robot = new Robot();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        robot.tick();

        if(gamepad1.dpad_up){
            robot.lifter.liftUp(1);

        }
        if(gamepad1.dpad_down){
            robot.lifter.liftDown(-1);
        }

    }
}
