package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.Modules.Wait;
import org.firstinspires.ftc.teamcode.FTC_Library.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_Library.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.FTC_Library.Autonomous.Modules.Premade.PIDEncoderDrive;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@Autonomous(name = "StraightAuto")
public class StraightAutonomous extends AutonomousBase {
    private Robot bot = new Robot();
    private Module[][] steps = new Module[][]{
            {new PIDEncoderDrive().setConfig(bot.PIDConfig).setDistances(24, 24)},
            {new Wait().setWaitTime(30000)},

            /*
                        {new CallFunction().setFunction(() -> bot.fling.flingUp())},
                        {new Wait().setWaitTime(1500)},
                        {new CallFunction().setFunction(() -> bot.fling.flingStop())},
                        */
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
