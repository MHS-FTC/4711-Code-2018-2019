package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.DriveTime;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Wait;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.CallFunction;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@Autonomous(name = "DropAndRoll")
@Disabled
public class DropAndRoll extends AutonomousBase {
    private Robot bot = new Robot();
    private Module[][] steps = new Module[][]{
            {new CallFunction().setFunction(() -> bot.lifter.liftUp())},
            {new Wait().setWaitTime(4700)},
            {new CallFunction().setFunction(() -> bot.lifter.liftStop())},

            {new DriveTime().setSpeeds(-0.3,0,0).setTime(1500)},
            {new Wait().setWaitTime(400)},

            {new DriveTime().setSpeeds(0,0.3,0).setTime(400)},


            {new DriveTime().setSpeeds(0.4,0,0).setTime(2000)}
    };
    @Override
    public void init() {
        init(hardwareMap,bot,steps);
    }
}