package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.Modules.TeamElementDrop;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Wait;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.TensorFlowDetect;
import org.firstinspires.ftc.teamcode.FTC_Library.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_Library.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.FTC_Library.Autonomous.Modules.Premade.Gyroscope;
import org.firstinspires.ftc.teamcode.FTC_Library.Autonomous.Modules.Premade.PIDEncoderDrive;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@Autonomous(name = "ClaimZoneAuto no lift")
public class ClaimZoneAuto extends AutonomousBase {
    private Robot bot = new Robot();
    private Module[][] steps = new Module[][]{
            {new TensorFlowDetect()},
            {
                    new Gyroscope().setTurn(-25).setCalibrate(true),
                    new Wait().setWaitTime(100),
                    new Gyroscope().setTurn(25).setCalibrate(true)
            },
            {
                    new PIDEncoderDrive().setConfig(bot.PIDConfig).setDistances(34, 34),
                    new PIDEncoderDrive().setConfig(bot.PIDConfig).setDistances(60, 60),
                    new PIDEncoderDrive().setConfig(bot.PIDConfig).setDistances(34, 34)

            },
            {
                    new Gyroscope().setTurn(29).setCalibrate(false),
                    new TeamElementDrop(),
                    new Gyroscope().setTurn(-45).setCalibrate(false),
            },
            {
                    new PIDEncoderDrive().setConfig(bot.PIDConfig).setDistances(25, 25),
                    new Gyroscope().setTurn(-118).setCalibrate(true),
                    new PIDEncoderDrive().setConfig(bot.PIDConfig).setDistances(36, 36)
            },
            {
                    new TeamElementDrop(),
                    new Wait(),
                    new TeamElementDrop()
            },
            {
                    new Gyroscope().setTurn(36).setCalibrate(false),
                    new Wait(),
                    new Gyroscope().setTurn(-97).setCalibrate(false),
            },

            {
                    new PIDEncoderDrive().setConfig(bot.PIDConfig).setDistances(-68, -68),
                    new Wait(),
                    new PIDEncoderDrive().setConfig(bot.PIDConfig).setDistances(79, 79)
            },
            {   new Wait(),
                new PIDEncoderDrive().setConfig(bot.PIDConfig).setDistances(77, 77)}

    };


    @Override
    public void init() {
        init(hardwareMap, bot, steps);
    }
}
