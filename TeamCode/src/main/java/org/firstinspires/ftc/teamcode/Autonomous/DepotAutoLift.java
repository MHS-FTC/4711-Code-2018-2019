package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.*;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@Autonomous(name = "ClaimZoneAUTO w/ lift")
public class DepotAutoLift extends AutonomousBase {
    private Robot bot = new Robot();
    private Module[][] steps = new Module[][]{
            {new LiftDownLimit()},
            {new Gyroscope().setTurn(-15).setCalibrate(true)},
            {new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(-4, -4)},
            {new Gyroscope().setTurn(75).setCalibrate(false)},
            {new TensorFlowTwoDetect()},
            {new Gyroscope().setTurn(17).setCalibrate(false),
                    new Gyroscope().setTurn(17).setCalibrate(false),
                    new Gyroscope().setTurn(17).setCalibrate(false)
            },

            {
                    new Gyroscope().setTurn(-25).setCalibrate(false),
                    new Wait().setWaitTime(100),
                    new Gyroscope().setTurn(25).setCalibrate(false)
            },
            {
                    new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(34, 34),
                    new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(60, 60),
                    new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(34, 34)

            },
            {
                    new Gyroscope().setTurn(32).setCalibrate(false),
                    new TeamElementDrop(),
                    new Gyroscope().setTurn(-45).setCalibrate(false),
            },
            {
                    new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(25, 25),
                    new Gyroscope().setTurn(-118).setCalibrate(false),
                    new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(36, 36)
            },
            {
                    new TeamElementDrop(),
                    new Wait(),
                    new TeamElementDrop()
            },
            {
                    new Gyroscope().setTurn(44).setCalibrate(false),
                    new Wait(),
                    new Gyroscope().setTurn(-92).setCalibrate(false),
            },

            {
                    new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(-68, -68),
                    new Wait(),
                    new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(79, 79)
            },
            {new Wait(),
                    new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(77, 77)}

    };


    @Override
    public void init() {
        init(hardwareMap, bot, steps);
    }
}
