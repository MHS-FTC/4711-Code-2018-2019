package org.firstinspires.ftc.teamcode.Robot.Subsystems;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.FTC_API.Robot.SubSystems.SubSystem;
import org.firstinspires.ftc.teamcode.Utilitys.Constants;

public class ElementWrist extends SubSystem {

    private String wristName;

    private Servo wrist;


    @Override
    public boolean init(HardwareMap hardwareDevices) {

        wrist = hardwareDevices.servo.get(wristName);

        return true;
    }

    public ElementWrist setMotorNames(String wrist) {
        wristName = wrist;
        return this;

    }


    public void wristDump() {
        wrist.setPosition(Constants.WRIST_DUMP);
    }

    public void wristLoad() {
        wrist.setPosition(Constants.WRIST_LOAD);
    }

}