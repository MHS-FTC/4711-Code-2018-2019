package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.DriveTime;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Wait;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.CallFunction;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@Autonomous(name = "DeployPark")
public class DropAndRoll extends AutonomousBase {
    private Robot bot = new Robot();
    private Module[][] steps = new Module[][]{
            {new CallFunction().setFunction(() -> bot.lifter.liftUp())},
            {new Wait().setWaitTime(1500)},
            {new CallFunction().setFunction(() -> bot.lifter.liftStop())},

            {new DriveTime().setSpeeds(-0.3,0,0).setTime(1200)},
            {new Wait().setWaitTime(300)},


            {new CallFunction().setFunction(() -> bot.fling.flingUp())},
            {new Wait().setWaitTime(2000)},
            {new CallFunction().setFunction(() -> bot.fling.flingStop())},

            {new DriveTime().setSpeeds(0,0.3,0).setTime(400)},


            {new DriveTime().setSpeeds(0.4,0,0).setTime(2000)}
    };
    @Override
    public void init() {
        init(hardwareMap,bot,steps);
    }
}