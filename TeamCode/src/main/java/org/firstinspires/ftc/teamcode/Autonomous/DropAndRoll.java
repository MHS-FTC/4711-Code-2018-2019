package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Gyroscope;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.LiftDown;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.PidEncoderDrive;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@Autonomous(name = "DropAndRoll")
public class DropAndRoll extends AutonomousBase {
    private Robot bot = new Robot();
    private Module[][] steps = new Module[][]{

            {new LiftDown()},
            {new Gyroscope().setTurn(90).setCalibrate(true)},
            {new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(20, 20)},
            {new Gyroscope().setTurn(-90).setCalibrate(false)},
            {new PidEncoderDrive().setConfig(bot.PIDConfig).setDistances(64, 64)},


    };

    @Override
    public void init() {
        init(hardwareMap, bot, steps);
    }
}