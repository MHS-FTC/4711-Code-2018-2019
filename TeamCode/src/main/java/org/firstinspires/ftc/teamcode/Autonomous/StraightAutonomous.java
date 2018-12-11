package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.DriveTime;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Wait;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.CallFunction;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.CompRobot;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@Autonomous(name = "StraightAuto")
public class StraightAutonomous extends AutonomousBase {
    private CompRobot bot = new CompRobot();
    private Module[][] steps = new Module[][]{
            {new DriveTime().setSpeeds(-0.3,0,0).setTime(1500)},
            {new Wait().setWaitTime(300)},

/*
            {new CallFunction().setFunction(() -> bot.fling.flingUp())},
            {new Wait().setWaitTime(1500)},
            {new CallFunction().setFunction(() -> bot.fling.flingStop())},
            */
    };
    @Override
    public void init() {
        init(hardwareMap,bot,steps);
    }

    @Override
    public void tick(){
        telemetry.addLine("Left" + bot.drive.getLeftSideMotors()[0].getCurrentPosition() +";"+bot.drive.getLeftSideMotors()[1].getCurrentPosition());
        telemetry.addLine("Right" + bot.drive.getRightSideMotors()[0].getCurrentPosition() +";"+bot.drive.getRightSideMotors()[1].getCurrentPosition());
    }
}
