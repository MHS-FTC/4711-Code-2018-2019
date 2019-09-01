package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.*;
import org.firstinspires.ftc.teamcode.FTC_Library.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_Library.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.FTC_Library.Autonomous.Modules.Premade.Gyroscope;
import org.firstinspires.ftc.teamcode.FTC_Library.Autonomous.Modules.Premade.PIDEncoderDrive;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@Autonomous(name = "DropAndRoll")
@Disabled
public class DropAndRoll extends AutonomousBase {
    private Robot bot = new Robot();
    private Module[][] steps = new Module[][]{
            //landing code
            {new LiftDownLimit()},
            {new Gyroscope().setTurn(-15).setCalibrate(true)},
            {new PIDEncoderDrive().setConfig(bot.PIDConfig).setDistances(-4, -4)},
            {new Gyroscope().setTurn(90).setCalibrate(false)},
            {new Wait().setWaitTime(4000)},
            {new Gyroscope().setTurn(15).setCalibrate(false)},

            //just drive
            {new PIDEncoderDrive().setConfig(bot.PIDConfig).setDistances(20, 20)},
    };

    @Override
    public void init() {
        init(hardwareMap, bot, steps);
    }
}