package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="WyattsWalkingRobot_TeleOp")
public class WyattsWalkingRobot_TeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {


        // -- Defining Buttons -- \\
        boolean A;
        boolean B;
        boolean X;
        boolean Y;
        boolean DpadUp;

        // -- Defining Servos -- \\
        Servo FL_Upper;
        Servo FL_Lower;
        Servo FR_Upper;
        Servo FR_Lower;
        Servo BL_Upper;
        Servo BL_Lower;
        Servo BR_Upper;
        Servo BR_Lower;

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


            /*
                When the A button is pushed on the Gamepad, the Back Right leg's Upper and Lower servos go
                through a specific function where it first moves the BR_Upper servo to the 0.75 position.
                (For reference, the Upper servos start at the 0.5 position, or right in the center.)
                Then there is a wait for half of a second, before the BR_Upper servo is set to the 0.475
                position. Immediately after this, it moves the BR_Lower position to 0.2. (Again, for
                reference, the Lower servos start at the 1 position to have the legs pointing straight down.
                Both the Upper and Lower servos are like this due to the way the servos are mounted onto
                the robot.) Then there is another wait for half a second, before the BR_Lower
                servo is set back to its original starting position. Following another half a second wait
                the BR_Upper servo is set back to its original starting position.
             */
            if (A) {

                BR_Upper.setPosition(0.75);
                sleep(500);
                BR_Upper.setPosition(0.475);
                BR_Lower.setPosition(0.2);
                sleep(500);
                BR_Lower.setPosition(1.0);
                sleep(500);
                BR_Upper.setPosition(0.5);

            }

            /*
                When the B button is pushed, the Front Left Leg Movement plays out. First, the FL_Upper servo
                is set to the 0.2 position, before going into a 1-second wait. The reason we increased the
                wait times to 1 second on all of these is to better observe each step of the process as it
                goes through them. After the wait it sets the FL_Upper position to 0.7. After another wait
                it moves the FL_Lower to the 0.5 position, before waiting, then moving the FL_Upper servo to
                the 0.2 position. Another wait occurs before it sets the FL_Lower servo to the original
                resting/starting position. After another final 1-second wait, it sets the FL_Upper servo to
                its original starting position, too.
             */
            if (B) {

                FL_Upper.setPosition(0.2);
                sleep(1000);
                FL_Upper.setPosition(0.7);
                sleep(1000);
                FL_Lower.setPosition(0.5);
                sleep(1000);
                FL_Upper.setPosition(0.2);
                sleep(1000);
                FL_Lower.setPosition(1.0);
                sleep(1000);
                FL_Upper.setPosition(0.5);

            }

            /*
                When the Y button is pushed, it firstly moves the BL_Upper servo to the 0.35 position. After
                a half-a-second wait, the BL_Upper servo is set to 0.575. Straight after this the BL_Lower
                servo is set to 0.2. After another half a second wait, it sets the BL_Lower servo to the
                original starting position, before going into another wait. Finally, it sets the BL_Upper
                to its original starting position.
             */
            if (Y) {

                BL_Upper.setPosition(0.35);
                sleep(500);
                BL_Upper.setPosition(0.575);
                BL_Lower.setPosition(0.2);
                sleep(500);
                BL_Lower.setPosition(1.0);
                sleep(500);
                BL_Upper.setPosition(0.5);

            }

            /*
                When the X button is pressed down, it firstly sets the FR_Upper to 0.7. A half second wait
                occurs before it sets the FR_Upper servo to the 0.26 position, and after that it sets the
                FR_Lower position to 0.2. Half a second waits occur between setting the FR_Lower and
                FR_Upper servos to the original starting positions.
             */
            if (X) {

                FR_Upper.setPosition(0.7);
                sleep(500);
                FR_Upper.setPosition(0.26);
                FR_Lower.setPosition(0.2);
                sleep(500);
                FR_Lower.setPosition(1.0);
                sleep(500);
                FR_Upper.setPosition(0.5);

            }

            /*
                Finally, when the Up-Dpad button is pressed, it sets all of the Legs' positions back to the
                original starting positions. It then prints the telemetry saying that the leg reset is now
                complete. It also includes the BL_Upper position, this is because we could also use this
                to see if this starting position is actually straight and lined up.
             */
            if (DpadUp) {

                FL_Upper.setPosition(0.5);
                FL_Lower.setPosition(1.0);
                sleep(250);
                FR_Upper.setPosition(0.5);
                FR_Lower.setPosition(1.0);
                sleep(250);
                BL_Upper.setPosition(0.5);
                BL_Lower.setPosition(1.0);
                sleep(250);
                BR_Upper.setPosition(0.5);
                BR_Lower.setPosition(1.0);
                sleep(250);
                telemetry.addData("Leg Reset Complete.", BL_Upper.getPosition());
                telemetry.update();

            }



        }

    }
}