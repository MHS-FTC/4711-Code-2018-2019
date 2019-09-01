package org.firstinspires.ftc.teamcode.Robot;

import org.firstinspires.ftc.teamcode.FTC_Library.Robot.RobotBase;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.MecanumDriveSubsystem;

public class MechBot extends RobotBase {
    public MecanumDriveSubsystem drive = new MecanumDriveSubsystem()
            .setMotorNames("leftFront", "rightFront", "leftBack", "rightBack");
    //public Lifter lifter = new Lifter().setMotorNames("lifter");
    //public ElementFling fling = new ElementFling().setMotorNames("collector");  //use fling code for collector


    public MechBot() {
        addSubSystem(drive);
        //addSubSystem(lifter);
        //addSubSystem(fling);
    }
}
