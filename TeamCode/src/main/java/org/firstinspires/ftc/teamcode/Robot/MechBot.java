package org.firstinspires.ftc.teamcode.Robot;

import org.firstinspires.ftc.teamcode.FTC_API.Robot.RobotBase;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.MecanumDriveSubsystem;

public class MechBot extends RobotBase {
    public MecanumDriveSubsystem drive = new MecanumDriveSubsystem()
            .setMotorNames("leftFront", "rightFront", "leftBack", "rightBack");

    public MechBot() {
        addSubSystem(drive);
    }
}
