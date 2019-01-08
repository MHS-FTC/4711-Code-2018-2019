package org.firstinspires.ftc.teamcode.Teleop.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@TeleOp(name = "Lift Test", group = "test")
public class LifterTest extends OpMode {
    private Robot robot = new Robot();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        robot.tick();

        if (gamepad1.dpad_up) {
            robot.lifter.liftUp();

        }
        if (gamepad1.dpad_down) {
            robot.lifter.liftDown();
        }
        if (gamepad1.x) {
            robot.lifter.liftStop();
        }

    }
}
