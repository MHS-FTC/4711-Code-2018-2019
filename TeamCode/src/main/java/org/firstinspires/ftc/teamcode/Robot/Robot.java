package org.firstinspires.ftc.teamcode.Robot;

import org.firstinspires.ftc.teamcode.FTC_API.Robot.RobotBase;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.FourWheelDrive;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Lifter;

public class Robot extends RobotBase {

    public Intake intake = new Intake().setMotorNames("arm", "lock", "release");
    public Lifter lifter = new Lifter().setMotorNames("lifter");
    public FourWheelDrive drive = new FourWheelDrive().setMotorNames("leftFront", "rightFront", "leftBack", "rightBack");


    public Robot() {

        addSubSystem(lifter);
        addSubSystem(drive);
        addSubSystem(intake);
    }

}
