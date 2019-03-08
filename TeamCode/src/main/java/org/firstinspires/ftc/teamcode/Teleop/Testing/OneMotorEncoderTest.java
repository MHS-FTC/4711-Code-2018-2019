package org.firstinspires.ftc.teamcode.Teleop.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "One Motor Test")
public class OneMotorEncoderTest extends OpMode {
    private DcMotor motor;
    @Override
    public void init() {
        motor = hardwareMap.dcMotor.get("motor");
    }

    @Override
    public void loop() {
        telemetry.addData("Encoder Position: ", motor.getCurrentPosition());
        motor.setPower(gamepad1.left_stick_y);
    }
}
