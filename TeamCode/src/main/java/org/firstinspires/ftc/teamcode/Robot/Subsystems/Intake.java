package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.FTC_API.Robot.SubSystems.SubSystem;

public class Intake extends SubSystem
{

    private String spinName;
    private String wristName;
    private String intakeName;

    private DcMotor spin;
    private DcMotor wrist;
    private DcMotor intake;

    private final double INTAKE_SPEED = 0.7;

    @Override
    public boolean init(HardwareMap hardwareDevices) {

        spin = hardwareDevices.dcMotor.get(spinName);
        wrist = hardwareDevices.dcMotor.get(wristName);
        intake = hardwareDevices.dcMotor.get(intakeName);

        return true;
    }

    public Intake setMotorNames(String spin, String wrist, String intake){
        spinName = spin;
        wristName = wrist;
        intakeName = intake;

        return this;

    }

    public void rotateWrist(double speed) {
        wrist.setPower(speed);
    }

    public void rotateOut(double speed) {
        spin.setPower(speed);
    }

    public void startIntake() {
        intake.setPower(INTAKE_SPEED);

    }

    public void stopIntake() {
        intake.setPower(0);
    }

    public void reverseIntake() {
        intake.setPower(-INTAKE_SPEED);
    }


}
