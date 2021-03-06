package org.firstinspires.ftc.teamcode.Autonomous.Modules;

import org.firstinspires.ftc.teamcode.FTC_Library.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Intake;

public class ExtendyOuty extends Module {
    private double startTime;

    private final double ENCODER_ROTATIONS_TO_EXTEND = -1.8;
    private final int TIME_TO_WAIT = 5000;//wait 5 seconds

    @Override
    public void start() {
        Intake intake = (Intake) robot.getSubSystem("Intake");
        intake.goToArmTarget((int) (ENCODER_ROTATIONS_TO_EXTEND * 16 * 1120));//16 to 1 ratio and 1120 encoder ticks for a 40 motor
        startTime = robot.getTimeMilliseconds();
    }

    @Override
    public boolean tick() {
        return (startTime + TIME_TO_WAIT < robot.getTimeMilliseconds());
    }
}
