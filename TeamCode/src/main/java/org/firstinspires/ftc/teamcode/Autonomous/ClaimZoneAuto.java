package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.Modules.Gyroscope;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.PidEncoderDrive;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Wait;
import org.firstinspires.ftc.teamcode.Autonomous.Testing.TensorFlowTest;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@Autonomous(name = "ClaimZoneAuto")
public class ClaimZoneAuto extends AutonomousBase {
    private Robot bot = new Robot();
    private Module[][] steps = new Module[][]{
            {new TensorFlowTest()},
            {
                new Gyroscope().setTurn(-45).setCalibrate(true),
                new Wait().setWaitTime(100),
                new Gyroscope() .setTurn(45).setCalibrate(true)
            },
            {new PidEncoderDrive().setWheelCircumference(12.56)
                    .setDistances(30, 30).setPID(0.002, 0.0004, 0.0006, 2, 200)},
            {new Gyroscope() .setTurn(-90).setCalibrate(true)},
            {new PidEncoderDrive().setWheelCircumference(12.56)
                    .setDistances(75, 75).setPID(0.002, 0.0004, 0.0006, 2, 200)},

    };


    @Override
    public void init() {
        init(hardwareMap,bot,steps);
    }
}