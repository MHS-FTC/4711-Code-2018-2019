package org.firstinspires.ftc.teamcode.Autonomous.Modules;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Utilitys.Constants;

import java.util.List;


public class TensorFlowTwoDetect extends Module {
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the Tensor Flow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;

    private boolean isDone = false;
    private int mineralPos = 1;//By default assume the center position
    private double startTime;
    private final int TIMEOUT = 7 * 1000;//wait max of 7 seconds


    @Override
    public void start() {
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

        /* Activate Tensor Flow Object Detection. */
        if (tfod != null) {
            tfod.activate();
        }

        startTime = robot.getTimeMilliseconds();
    }

    @Override
    public void tick() {
        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                telemetry.addData("# Object Detected", updatedRecognitions.size());
                if (updatedRecognitions.size() == 2) {//only need to see two objects
                    int goldMineralX = -1;
                    int silverMineral1X = -1;
                    int silverMineral2X = -1;
                    //update positions
                    for (Recognition recognition : updatedRecognitions) {
                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            goldMineralX = (int) recognition.getLeft();
                        } else if (silverMineral1X == -1) {
                            silverMineral1X = (int) recognition.getLeft();
                        } else {
                            silverMineral2X = (int) recognition.getLeft();
                        }
                    }

                    //we are detecting the left two
                    if (silverMineral1X != -1 && silverMineral2X != -1 && goldMineralX == -1) {
                        //gold must be on right if we can see both of the silver
                        telemetry.log().add("Gold Mineral Position", "Right");
                        mineralPos = 2;
                        isDone = true;
                    } else if (goldMineralX < silverMineral1X && silverMineral2X ==-1) {
                        //gold must be on left if the x value is less than the silver position
                        telemetry.log().add("Gold Mineral Position", "Left");
                        mineralPos = 0;
                        isDone = true;
                    } else if (goldMineralX != -1 && silverMineral1X != -1){
                        //just assume center if its not left or right
                        telemetry.log().add("Gold Mineral Position", "Center");
                        mineralPos = 1;
                        isDone = true;
                    }
                }
                telemetry.update();
            }
        }

        //code for timeout
        //looking for balls can take a long time so make sure we don't spend whole autonomous looking for balls
        if (startTime + TIMEOUT < robot.getTimeMilliseconds()) {
            //have exceeded timeout
            isDone = true;
            telemetry.log().add("Tensorflow detection has exceeded timeout, moving on");
        }
    }


    @Override
    public int stop() {
        if (tfod != null) {
            tfod.shutdown();
        }
        tfod = null;
        return mineralPos;
    }


    @Override
    public boolean isDone() {
        return isDone;
    }

    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = Constants.VUFORIA_KEY;
        parameters.cameraName = robot.hardwareMap.get(WebcamName.class, "Webcam");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    /**
     * Initialize the Tensor Flow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = robot.hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", robot.hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        //tfodParameters.minimumConfidence = 0.5;
        telemetry.log().add("Current minimum Tensorflow confidence: " + tfodParameters.minimumConfidence);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }

}
