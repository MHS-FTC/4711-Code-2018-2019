package org.firstinspires.ftc.teamcode.Teleop.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Robot.MechBot;

@Disabled
@TeleOp(name = "Mecanum Teleop", group = "test")
public class MechDriveTest extends OpMode {
    private MechBot robot = new MechBot();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        robot.tick();
        //the y direction of the joysticks needs to be reversed
        //Commented out because of diffrence in method signature
        robot.drive.driveMecanum(gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x);
/*
        if (gamepad1.dpad_up) {
            robot.lifter.liftUp();
        } else if (gamepad1.dpad_down) {
            robot.lifter.liftDown();
        } else {
            robot.lifter.liftStop();
        }
        //robot lifter

        if(gamepad2.right_bumper ){
            robot.fling.flingUp();

        }
        else if(gamepad1.left_bumper ){
            robot.fling.flingDown();
        }
        else {
            robot.fling.flingStop();
        }
        */
        //unobtainum intaker


    }
}
