package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.Modules.Gyroscope;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.PidEncoderDrive;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.TeamElementDrop;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Wait;
import org.firstinspires.ftc.teamcode.Autonomous.Testing.TensorFlowTest;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@Autonomous(name = "ClaimZoneAuto")
public class ClaimZoneAuto extends AutonomousBase {
    private Robot bot = new Robot();
    private Module[][] steps = new Module[][]{
            {new TensorFlowTest()},
            {
                    new Gyroscope().setTurn(-35).setCalibrate(true),
                    new Wait().setWaitTime(100),
                    new Gyroscope().setTurn(35).setCalibrate(true)
            },
            {
                    new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(34, 34),
                    new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(60, 60),
                    new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(34, 34)

            },
            {
                    new Gyroscope().setTurn(-120).setCalibrate(true),
                    new TeamElementDrop(),
                    new Gyroscope().setTurn(-45).setCalibrate(true),
            },
            {
                    new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(36, 36),
                    new Gyroscope().setTurn(-120).setCalibrate(true),
                    new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(36, 36)
            },
            {
                    new TeamElementDrop(),
                    new Wait(),
                    new TeamElementDrop()
            },
            {
                    new Wait(),
                    new Wait(),
                    new Gyroscope().setTurn(-120).setCalibrate(true),
            },
            {new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(77, 77)}

    };


    @Override
    public void init() {
        init(hardwareMap, bot, steps);
    }
}
