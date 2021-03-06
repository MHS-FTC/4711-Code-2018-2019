package org.firstinspires.ftc.teamcode.Autonomous.Modules;

import org.firstinspires.ftc.teamcode.FTC_Library.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.FTC_Library.Robot.SubSystems.SidedDriveSystemTemplate;
import org.firstinspires.ftc.teamcode.Utilitys.Constants;

/**
 * Created by Ethan Hampton on 8/19/17.
 * <p>
 * Simple drive for time module
 */

public class DriveTime extends Module {
    private SidedDriveSystemTemplate drive;
    private double startTime;

    private int driveTime;
    private double forwardSpeed = Constants.DEFAULT_SPEED;
    private double turnSpeed = 0;
    private double strafeSpeed = 0;

    @Override
    public void start() {
        drive = (SidedDriveSystemTemplate) robot.getDriveSystem();
        startTime = robot.getTimeMilliseconds();

        drive.driveMecanum(forwardSpeed, turnSpeed, strafeSpeed);
    }

    @Override
    public boolean tick() {
        if ((robot.getTimeMilliseconds() - startTime) > driveTime) {
            drive.driveMecanum(0, 0, 0);
            return true;
        }
        return false;
    }


    public DriveTime setSpeeds(double forwardSpeed, double turnSpeed, double strafeSpeed) {
        this.forwardSpeed = forwardSpeed;
        this.turnSpeed = turnSpeed;
        this.strafeSpeed = strafeSpeed;
        return this;
    }

    public DriveTime setTime(int time) {
        driveTime = time;
        return this;
    }
}