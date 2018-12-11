package org.firstinspires.ftc.teamcode.Robot;

import org.firstinspires.ftc.teamcode.FTC_API.Robot.RobotBase;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.ElementFling;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.ElementWrist;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.FourWheelDrive;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Lifter;

public class CompRobot extends RobotBase{

    //public Intake intake = new Intake().setMotorNames("spin", "wrist", "intake");
    public Lifter lifter = new Lifter().setMotorNames("lifter");
    public FourWheelDrive drive = new FourWheelDrive().setMotorNames("leftFront", "rightFront", "leftBack", "rightBack");

    public CompRobot() {
        //addSubSystem(intake);
        addSubSystem(lifter);
        addSubSystem(drive);
    }


}


