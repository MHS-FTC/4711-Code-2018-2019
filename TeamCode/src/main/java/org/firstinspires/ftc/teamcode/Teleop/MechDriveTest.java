package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Robot.MechBot;

@TeleOp(name = "MecanumTeleop", group = "test")
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
    }
}
