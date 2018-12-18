package org.firstinspires.ftc.teamcode.Teleop.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@TeleOp(name = "Element Fling Test", group = "test")
public class ElementFlingTest extends OpMode {
    private Robot robot = new Robot();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        robot.tick();

        if(gamepad1.y){
            robot.fling.flingUp();

        }
        if(gamepad1.a){
            robot.fling.flingDown();
        }
        if (gamepad1.x) {
            robot.fling.flingStop();
        }

    }
}
