package org.firstinspires.ftc.teamcode.Robot;

import org.firstinspires.ftc.teamcode.FTC_API.Robot.RobotBase;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.ElementFling;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.ElementWrist;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.FourWheelDrive;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Lifter;

public class Robot extends RobotBase {

    //public Intake intake = new Intake().setMotorNames("spin", "wrist", "intake");
    public Lifter lifter = new Lifter().setMotorNames("lifter");
    public FourWheelDrive drive = new FourWheelDrive().setMotorNames("leftFront", "rightFront", "leftBack", "rightBack");
    public ElementFling fling = new ElementFling().setMotorNames("fling");
    public ElementWrist wrist = new ElementWrist().setMotorNames("wrist");

    public Robot() {
        //addSubSystem(intake);
        addSubSystem(lifter);
        addSubSystem(drive);
        addSubSystem(fling);
        addSubSystem(wrist);
    }


}
