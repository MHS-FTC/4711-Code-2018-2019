package org.firstinspires.ftc.teamcode.Robot;

import org.firstinspires.ftc.teamcode.FTC_API.Robot.RobotBase;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;

public class Robot extends RobotBase {

public Intake intake = new Intake().setMotorNames("spin", "wrist", "intake");

     public Robot() {
        addSubSystem(intake);
    }


}
