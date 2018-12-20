package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@TeleOp(name = "MAIN Teleop")
public class MainTeleop extends OpMode {
     private Robot robot = new Robot();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        robot.tick();

        robot.drive.driveMecanum(gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x);

        if(gamepad1.dpad_up){
            robot.lifter.liftUp();

        }else
        if(gamepad1.dpad_down){
            robot.lifter.liftDown();
        }
        else {
            robot.lifter.liftStop();
        }

        if(gamepad2.a) {
            robot.intake.lockUp();
        }
        if (gamepad2.b){
            robot.intake.lockDown();
        }

        if (gamepad2.right_bumper && gamepad2.left_bumper) {
            robot.intake.releaseDown();
        }
        else{
            robot.intake.releaseUp();
        }

        robot.intake.intakeSpeed(gamepad2.right_stick_y);

        robot.intake.driveArm(gamepad2.left_stick_y,telemetry);
    }
}
