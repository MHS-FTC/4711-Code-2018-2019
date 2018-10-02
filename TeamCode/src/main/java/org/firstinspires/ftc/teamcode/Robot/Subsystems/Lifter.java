package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.FTC_API.Robot.SubSystems.SubSystem;

public class Lifter extends SubSystem {

    private String liftName;

    private DcMotor lift;


    private final double LIFT_SPEED = 1;

    @Override
    public boolean init(HardwareMap hardwareDevices) {

        lift = hardwareDevices.dcMotor.get(liftName);

        return true;
    }

    public Lifter setMotorNames(String lift){
        liftName = lift;
        return this;

    }

    public void liftUp() {
        lift.setPower(LIFT_SPEED);
    }

    public void liftDown () {
        lift.setPower(-LIFT_SPEED);
    }

    public void liftStop(){
        lift.setPower(0);
    }

}
