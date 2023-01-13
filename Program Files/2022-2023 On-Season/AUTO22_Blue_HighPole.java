/* Copyright (c) 2019 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;


@Autonomous(name = "AUTO22_Blue_HighPole")

public class AUTO22_Blue_HighPole extends COMP22_Auto_Base_BuildChassis {

    // Program Information: \\
    /**

     */


    DcMotor Lift;
    Servo Claw;


    private static final String TFOD_MODEL_ASSET = "PowerPlay.tflite";
    // private static final String TFOD_MODEL_FILE  = "/sdcard/FIRST/tflitemodels/CustomTeamModel.tflite";


    private static final String[] LABELS = {
            "Bolt",
            "Bulb",
            "Panel"
    };


    private static final String VUFORIA_KEY =
            "ATss33L/////AAABmfQpm9/u4ka9s/52e/LBboY7IfKlzw/jvNF1+YUr4LCx4vSow4Xs0FS+AkUhKWxRYoUCh3FtFYR5udsIpGUYp/3ucKe0HTSQ9DaOTLCrsBxo345maLbjx9jq+xRfVnI2vRVaIHFs9ij7AB49GzU49afm7pew3c20Mn1J4VIeVGX7EKOzZTXEQl3nYjXklIJ9EkwKH05TU6+ewGqEd9vhm71dLNUmj2kuqRyQljjoIMXLbyCwv7+fpOxN+Dbu+RxbXIwrH5mLIeQB0v0TzNFKCWpMkD0Mn5mG5TpuI6y8e5UsqsUF/d8214lnOCGMR/NqxYNze/I4uhWq4YLS5fWSQlq9Br2cSLvD32h4PXiPjoeR";

    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;

    @Override
    public void runOpMode() {

        Lift = hardwareMap.dcMotor.get("lift");
        Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lift.setTargetPosition(0);
        Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        Claw = hardwareMap.servo.get("claw");

        initVuforia();
        initTfod();
        init_motors();
        int detectionPos = 3;
        int closeloop = 0;
        Claw.setPosition(1.00);


        if (tfod != null) {
            tfod.activate();
            tfod.setZoom(1.0, 16.0/9.0);
        }

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        waitForStart();

        Lift.setPower(0.50);
        Lift.setTargetPosition(-343);
        sleep(400);
        drive(0.50,70);
        sleep(100);
        Lift.setTargetPosition(-90);
        sleep(400);
        Claw.setPosition(0.00);
        sleep(  1000);
        Lift.setTargetPosition(-181);
        sleep(1000);
        drive(0.50,1200);
        strafe(0.50,1650);
        sleep(1000);
        Lift.setTargetPosition(-3000);
        sleep(1500);
        drive(0.50,50);
        Claw.setPosition(1.00);


        if (opModeIsActive()) {
            while (opModeIsActive()) {
                if (tfod != null) {

                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Objects Detected", updatedRecognitions.size());


                        for (Recognition recognition : updatedRecognitions) {
                            double col = (recognition.getLeft() + recognition.getRight()) / 2 ;
                            double row = (recognition.getTop()  + recognition.getBottom()) / 2 ;
                            double width  = Math.abs(recognition.getRight() - recognition.getLeft()) ;
                            double height = Math.abs(recognition.getTop()  - recognition.getBottom()) ;

                            telemetry.addData(""," ");
                            telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100 );
                            telemetry.addData("- Position (Row/Col)","%.0f / %.0f", row, col);
                            telemetry.addData("- Size (Width/Height)","%.0f / %.0f", width, height);
                            if((recognition.getLabel() == "Bolt") && (closeloop==0)) {
                                closeloop = 1;
                                detectionPos = 0;
                                telemetry.addData("position", detectionPos);
                                telemetry.addData("Detected Bolt.", detectionPos);
                                telemetry.update();
                                drive(0.50, 500);
                                sleep(100);
                                strafe(0.50, 1100);
                                sleep(100);
                                drive(0.50,30);
                                break;
                            } else if((recognition.getLabel() == "Bulb") && (closeloop==0)) {
                                closeloop = 1;
                                detectionPos = 1;
                                telemetry.addData("position", detectionPos);
                                telemetry.addData("Detected Bulb.", detectionPos);
                                telemetry.update();
                                drive(0.50, 635);
                                break;
                            } else if((recognition.getLabel() == "Panel") && (closeloop==0)) {
                                closeloop = 1;
                                detectionPos = 2;
                                telemetry.addData("position", detectionPos);
                                telemetry.addData("Detected Panel.", detectionPos);
                                telemetry.update();
                                drive(0.50, 500);
                                sleep(100);
                                strafe(0.50, -1150);
                                break;
                            }
                            telemetry.addData("position", detectionPos);
                        }
                        telemetry.update();




                    }
                }
            }
        }




    }

    /**
     * Initialize the Vuforia localization engine.
     */

    private void initVuforia() {

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "webcam");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.50f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 300;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);


        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
        // tfod.loadModelFromFile(TFOD_MODEL_FILE, LABELS);
    }


}

