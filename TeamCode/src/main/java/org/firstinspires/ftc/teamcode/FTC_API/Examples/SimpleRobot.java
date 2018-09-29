package org.firstinspires.ftc.teamcode.FTC_API.Examples;

import com.qualcomm.hardware.motors.NeveRest20Gearmotor;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.FTC_API.Robot.RobotBase;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Lifter;

/**
 * Created by Ethan Hampton on 8/19/17.
 * <p>
 * Just a simple robot that drives
 */

class SimpleRobot extends RobotBase {
    Drive drive = new Drive()
            .setMotorNames("left", "right");



    SimpleRobot() {
      addSubSystem(drive);

    }
}
