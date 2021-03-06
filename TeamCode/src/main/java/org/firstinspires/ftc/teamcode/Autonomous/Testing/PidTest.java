package org.firstinspires.ftc.teamcode.Autonomous.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.FTC_Library.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_Library.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.FTC_Library.Autonomous.Modules.Premade.PIDEncoderDrive;
import org.firstinspires.ftc.teamcode.Robot.MechBot;

@Autonomous(name = "PID Test v1")
public class PidTest extends AutonomousBase {
    private MechBot bot = new MechBot();
    private Module[][] steps = new Module[][]{
            {new PIDEncoderDrive().setWheelCircumference(12.56).setDistances(24, 24).setPID(0.002, 0.0004, 0.0006, 2, 200, 0.85)}
    };

    @Override
    public void init() {
        init(hardwareMap, bot, steps);
    }

    @Override
    public void tick() {
        telemetry.addLine("Left" + bot.drive.getLeftSideMotors()[0].getCurrentPosition() + ";" + bot.drive.getLeftSideMotors()[1].getCurrentPosition());
        telemetry.addLine("Right" + bot.drive.getRightSideMotors()[0].getCurrentPosition() + ";" + bot.drive.getRightSideMotors()[1].getCurrentPosition());

    }
}