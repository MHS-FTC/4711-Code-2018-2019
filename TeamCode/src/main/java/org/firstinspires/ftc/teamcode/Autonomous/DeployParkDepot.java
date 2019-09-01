package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.DriveTime;
import org.firstinspires.ftc.teamcode.FTC_Library.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_Library.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@Autonomous(name = "DeployParkDepot")
@Disabled
public class DeployParkDepot extends AutonomousBase {
    private Robot bot = new Robot();
    private Module[][] steps = new Module[][]{
            {new DriveTime().setSpeeds(-0.3, 0, 0).setTime(1200)},

            {new DriveTime().setSpeeds(0, 0.3, 0).setTime(400)},


            {new DriveTime().setSpeeds(0.4, 0, 0).setTime(2000)}
    };

    @Override
    public void init() {
        init(hardwareMap, bot, steps);
    }
}