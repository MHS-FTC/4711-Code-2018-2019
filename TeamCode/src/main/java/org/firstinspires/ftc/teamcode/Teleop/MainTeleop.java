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
    public void start() {
        robot.lifter.goToPreLiftHeight();
    }

    @Override
    public void loop() {
        robot.tick();

        robot.drive.driveMecanum(gamepad1.left_stick_y, -gamepad1.right_stick_x, -gamepad1.left_stick_x);

        if (gamepad1.left_bumper) {
            robot.lifter.liftUp();
        } else if (gamepad1.right_bumper) {
            robot.lifter.liftDown();
        } else if (gamepad2.x) {
            robot.lifter.goToPreLiftHeight();
        } else {
            robot.lifter.liftStop();
        }
        telemetry.addLine(String.valueOf(robot.lifter.getMotor().getCurrentPosition() / robot.lifter.getMotor().getMotorType().getTicksPerRev()));

        if (gamepad2.a) {
            robot.intake.lockUp();
        } else if (gamepad2.b) {
            robot.intake.lockDown();
        }

        if (gamepad2.right_bumper && gamepad2.left_bumper) {
            robot.intake.releaseDown();
        } else {
            robot.intake.releaseUp();
        }

        robot.intake.intakeSpeed(gamepad2.right_stick_y);
        robot.intake.driveArm(gamepad2.left_stick_y);
        if (gamepad2.dpad_up) {
            robot.intake.goToUpPosition();
        } else if (gamepad2.dpad_down) {
            robot.intake.goToDownPosition();
        }

        telemetry.addLine("Arm Target:" + robot.intake.getArmTarget());
        telemetry.addLine("Is lifter limit pressed?:" + robot.lifter.isPressed());
    }
}
