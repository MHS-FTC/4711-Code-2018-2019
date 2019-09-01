package org.firstinspires.ftc.teamcode.Robot;

import org.firstinspires.ftc.teamcode.FTC_Library.Autonomous.Modules.Premade.PIDEncoderDrive;
import org.firstinspires.ftc.teamcode.FTC_Library.Robot.RobotBase;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.ElementFling;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Lifter;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.MecanumDriveSubsystem;

public class Robot extends RobotBase {

    public Intake intake = new Intake().setMotorNames("arm", "lock", "release", "intake").setLimitName("armLimit");
    public Lifter lifter = new Lifter().setDeviceNames("lifter", "limit");
    public MecanumDriveSubsystem drive = new MecanumDriveSubsystem().setMotorNames("leftFront", "rightFront", "leftBack", "rightBack");
    public ElementFling fling = null;//for programs

    public PIDEncoderDrive.PIDConfig PIDConfig = new PIDEncoderDrive.PIDConfig()
            .setPID(0.002, 0.0004, 0.0006)
            .setSettlingTime(2)
            .setTolerance(200)
            .setMaxSpeed(0.8)
            .setWheelCircumference(12.56);

    public Robot() {

        addSubSystem(lifter);
        addSubSystem(drive);
        addSubSystem(intake);
    }

}
