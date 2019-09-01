package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.FTC_Library.Robot.SubSystems.SubSystem;

public class ElementFling extends SubSystem {

    private String flingName;

    private DcMotor fling;


    private final double FLING_SPEED = 1;

    @Override
    public boolean init(HardwareMap hardwareDevices) {

        fling = hardwareDevices.dcMotor.get(flingName);

        return true;
    }

    public ElementFling setMotorNames(String fling) {
        flingName = fling;
        return this;

    }

    public void flingUp() {
        fling.setPower(FLING_SPEED);
    }

    public void flingDown() {
        fling.setPower(-FLING_SPEED);
    }

    public void flingStop() {
        fling.setPower(0);
    }

}
