package org.firstinspires.ftc.teamcode.Robot;

import org.firstinspires.ftc.teamcode.FTC_API.Robot.RobotBase;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.*;

public class Robot extends RobotBase {

    public Intake intake = new Intake().setMotorNames("arm", "lock", "release", "intake");
    public Lifter lifter = new Lifter().setMotorNames("lifter");
    public MecanumDriveSubsystem drive = new MecanumDriveSubsystem().setMotorNames("leftFront", "rightFront", "leftBack", "rightBack");
    public ElementFling fling = null;//for programs


    public Robot() {

        addSubSystem(lifter);
        addSubSystem(drive);
        addSubSystem(intake);
    }

}
