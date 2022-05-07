package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="WyattWalkingRobot_TeleOp")
public class WyattWalkingRobot_TeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {


        // -- Defining Buttons -- \\
        boolean A;
        boolean B;
        boolean X;
        boolean Y;
        boolean DpadUp;
        boolean DpadDown;
        boolean DpadLeft;
        boolean DpadRight;

        // -- Defining Servos -- \\
        Servo FL_Upper;
        Servo FL_Lower;
        Servo FR_Upper;
        Servo FR_Lower;
        Servo BL_Upper;
        Servo BL_Lower;
        Servo BR_Upper;
        Servo BR_Lower;
        Servo VH;
        Servo HH;

        // -- Hardwaremapping Section -- \\
        /* Hardwaremapping tells the program what the motors, servos, and other technical parts should be called
        in the configuration. We also use this section the motors' directions, so that the robot drives the correct
        way, and set the mode of them, as well.
        */
        FL_Upper = hardwareMap.servo.get("ufl");
        FL_Lower = hardwareMap.servo.get("lfl");
        FR_Upper = hardwareMap.servo.get("ufr");
        FR_Lower = hardwareMap.servo.get("lfr");
        BL_Upper = hardwareMap.servo.get("ubl");
        BL_Lower = hardwareMap.servo.get("lbl");
        BR_Upper = hardwareMap.servo.get("ubr");
        BR_Lower = hardwareMap.servo.get("lbr");
        VH = hardwareMap.servo.get("vh");
        HH = hardwareMap.servo.get("hh");

        waitForStart();

        while (opModeIsActive()) {
            // -- Code Information -- \\
            /*
                This is the TeleOp program that we use to control the basic functions of Wyatt's Walking
                Robot. This is also the main program we use for fine-tuning and testing out new values
                whenever we make changes to the robot itself. Any new values here are updated into the
                Telemetry_Test and AutoBase program variations.
             */

            // -- Button Mapping -- \\
            // This sets the Button Variables to the correct buttons on the GamePads.

            A = gamepad1.a;
            B = gamepad1.b;
            X = gamepad1.x;
            Y = gamepad1.y;
            DpadUp = gamepad1.dpad_up;
            DpadDown = gamepad1.dpad_down;
            DpadLeft = gamepad1.dpad_left;
            DpadRight = gamepad1.dpad_right;


            if (A) {

                BR_Upper.setPosition(0.35);
                sleep(150);
                BR_Lower.setPosition(0.525);
                sleep(2000);
                BR_Upper.setPosition(0.75);
                sleep(100);
                BR_Lower.setPosition(1.0);
                sleep(400);
                BR_Lower.setPosition(0.0);
                sleep(1000);
                BR_Upper.setPosition(0.5);

            }


            if (B) {

                FL_Upper.setPosition(0.69);
                sleep(2500);
                FL_Upper.setPosition(0.4);
                sleep(2500);
                FL_Lower.setPosition(0.0);
                sleep(2500);
                FL_Upper.setPosition(0.0);
                sleep(750);
                FL_Upper.setPosition(0.5);
                sleep(750);
                FL_Lower.setPosition(1.0);

            }


            if (Y) {

                BL_Upper.setPosition(1.0);
                sleep(5000);
                BL_Lower.setPosition(0.0);
                sleep(5000);
                BL_Upper.setPosition(0.1);
                sleep(5000);
                BL_Upper.setPosition(0.5);
                BL_Lower.setPosition(1.0);

            }


            if (X) {

                FR_Upper.setPosition(0.35);
                sleep(150);
                FR_Lower.setPosition(0.525);
                sleep(2000);
                FR_Upper.setPosition(0.75);
                sleep(100);
                FR_Lower.setPosition(1.0);
                sleep(400);
                FR_Lower.setPosition(0.0);
                sleep(1000);
                FR_Upper.setPosition(0.5);
            }


            if (DpadUp) {

                FL_Upper.setPosition(0.5);
                sleep(1000);
                FL_Lower.setPosition(1.0);

            }

            if (DpadLeft) {
                FR_Upper.setPosition(0.5);
                sleep(1000);
                FR_Lower.setPosition(0.0);
            }

            if (DpadRight) {
                BL_Upper.setPosition(0.5);
                sleep(1000);
                BL_Lower.setPosition(1.0);
            }

            if (DpadDown) {
                BR_Upper.setPosition(0.5);
                sleep(1000);
                BR_Lower.setPosition(0.0);
            }

        }

    }
}
