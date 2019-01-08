package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Gyroscope;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.LiftDown;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.PidEncoderDrive;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@Autonomous(name = "DropAndRoll")
public class DropAndRoll extends AutonomousBase {
    private Robot bot = new Robot();
    private Module[][] steps = new Module[][]{

            {new LiftDown()},
            {new Gyroscope().setTurn(90).setCalibrate(true)},
            {new PidEncoderDrive().setWheelCircumference(12.56)
                    .setDistances(20, 20).setPID(0.002, 0.0004, 0.0006, 2, 200)},
            {new Gyroscope().setTurn(-90).setCalibrate(false)},
            {new PidEncoderDrive().setWheelCircumference(12.56)
                    .setDistances(60, 60).setPID(0.002, 0.0004, 0.0006, 2, 200)},


    };

    @Override
    public void init() {
        init(hardwareMap, bot, steps);
    }
}