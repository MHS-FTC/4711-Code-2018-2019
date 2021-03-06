package org.firstinspires.ftc.teamcode.Autonomous.Modules;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FTC_Library.Autonomous.Modules.Module;

public class TeamElementDrop extends Module {

    private Servo elementHook;
    private double startTime;
    private final int DELAY = 1000;

    @Override
    public void start() {
        elementHook = robot.hardwareMap.servo.get("hook");
        startTime = robot.getTimeMilliseconds();
        elementHook.setPosition(0.4);
    }

    @Override
    public boolean tick() {
        return startTime + DELAY < robot.getTimeMilliseconds();
    }
}
