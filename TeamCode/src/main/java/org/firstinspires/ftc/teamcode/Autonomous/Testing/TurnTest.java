package org.firstinspires.ftc.teamcode.Autonomous.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Gyroscope;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Wait;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.MechBot;

@Autonomous(name = "Turn Test v1")
public class TurnTest extends AutonomousBase {
    private MechBot bot = new MechBot();
    private Module[][] steps = new Module[][]{
            {new Gyroscope().setTurn(90).setCalibrate(true)},
            {new Wait().setWaitTime(300)},
            {new Gyroscope().setTurn(-90).setCalibrate(false)},
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
