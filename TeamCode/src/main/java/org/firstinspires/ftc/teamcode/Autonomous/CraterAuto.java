package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.*;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@Autonomous(name = "CraterAUTO")
public class CraterAuto extends AutonomousBase {
    private Robot bot = new Robot();
    private Module[][] steps = new Module[][]{
            {new LiftDownLimit()},
            {new Gyroscope().setTurn(-15).setCalibrate(true)},
            {new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(-4, -4)},
            {new Gyroscope().setTurn(70).setCalibrate(false)},
            {new TensorFlowTwoDetect()},
            {       new Gyroscope().setTurn(17).setCalibrate(false),
                    new Gyroscope().setTurn(17).setCalibrate(false),
                    new Gyroscope().setTurn(17).setCalibrate(false)
            },
            {
                    new Gyroscope().setTurn(-25).setCalibrate(false),
                    new Wait().setWaitTime(100),
                    new Gyroscope().setTurn(25).setCalibrate(false)
            },
            {
                    new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(32, 32),
                    new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(28, 28),
                    new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(32, 32)
            },

            {       new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(-32, -32),
                    new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(-28, -28),
                    new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(-32, -32)
            },

            {
                    new Gyroscope().setTurn(25).setCalibrate(false),
                    new Wait().setWaitTime(100),
                    new Gyroscope().setTurn(-25).setCalibrate(false)
            },
            { new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(10, 10)},
            {new Gyroscope().setTurn(-50).setCalibrate(false)},
            {new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(43, 43)},
            {new Gyroscope().setTurn(-66).setCalibrate(false)},
            {new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(58, 58)},
            {new TeamElementDrop()},
            {new Gyroscope().setTurn(-5)},
            {new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(-70, -70)},
            {new Gyroscope().setTurn(-180)},
            {new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(9, 9)},
            {new ExtendyOuty()}





    };


    @Override
    public void init() {
        init(hardwareMap, bot, steps);
    }
}
