package org.firstinspires.ftc.teamcode.Autonomous.Modules;

import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;

public class ExtendyOuty extends Module {
    private double startTime;

    private final int ENCODER_TICKS_TO_EXTEND = 500;
    private final int TIME_TO_WAIT = 5000;//wait 5 seconds

    @Override
    public void start() {
        Intake intake = (Intake) robot.getSubSystem("Intake");
        intake.setArmTarget(ENCODER_TICKS_TO_EXTEND);
        intake.driveArm(0);
        startTime = robot.getTimeMilliseconds();
    }

    @Override
    public void tick() {
    }

    @Override
    public boolean isDone() {
        return (startTime + TIME_TO_WAIT < robot.getTimeMilliseconds());
    }
}
