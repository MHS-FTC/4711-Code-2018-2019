package org.firstinspires.ftc.teamcode.Teleop.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@TeleOp(name = "Drive+Lift", group = "test")
public class DriveLiftTest extends OpMode {
    private Robot robot = new Robot();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        robot.drive.driveTank(gamepad1.right_stick_y, gamepad1.left_stick_y);

        if (gamepad1.dpad_up) {
            robot.lifter.liftUp();
        } else if (gamepad1.dpad_down) {
            robot.lifter.liftDown();
        } else {
            robot.lifter.liftStop();
        }

        if (gamepad1.y) {
            robot.fling.flingUp();
        } else if (gamepad1.a) {
            robot.fling.flingDown();
        } else {
            robot.fling.flingStop();
        }
/*
        if (gamepad1.dpad_right) {
            robot.wrist.wristDump();
        }
        else if (gamepad1.dpad_left){
            robot.wrist.wristLoad();
        }
        */
    }
}