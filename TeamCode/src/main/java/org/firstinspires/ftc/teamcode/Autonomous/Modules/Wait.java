package org.firstinspires.ftc.teamcode.Autonomous.Modules;

import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;

/**
 * Created by ethan.hampton on 11/17/2017.
 * Designed to wait for a certain amount of time
 */

public class Wait extends Module {
    private double startTime;
    private long waitTime = 31000;

    public Wait setWaitTime(long waitTime) {
        this.waitTime = waitTime;
        return this;
    }

    @Override
    public void start() {
        startTime = robot.getTimeMilliseconds();
    }

    @Override
    public void tick() {
    }//Just here because it is required

    @Override
    public boolean isDone() {
        return (startTime + waitTime) < robot.getTimeMilliseconds();
    }

}