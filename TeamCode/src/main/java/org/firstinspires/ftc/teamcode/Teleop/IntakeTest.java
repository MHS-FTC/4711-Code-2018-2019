package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot.Robot;

public class IntakeTest extends OpMode {
private Robot robot = new Robot();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        robot.tick();
/*
        if(gamepad1.x){
            robot.intake.startIntake();

        }
        if(gamepad1.y){
            robot.intake.stopIntake();
        }

        robot.intake.rotateOut(gamepad1.left_stick_y);

        robot.intake.rotateWrist(gamepad1.right_stick_y);
*/

    }
}
