package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name="WyattsWalkingRobot_TelemetryTest")
public class WyattsWalkingRobot_TelemetryTest extends LinearOpMode {


    // -- Defining Buttons -- \\
    boolean A;
    boolean B;
    boolean X;
    boolean Y;
    boolean DpadUp;
    boolean DpadDown;
    boolean DpadLeft;
    boolean DpadRight;

    // -- Defining Variables -- \\
    float LeftJoystickX;
    float RightJoystickX;
    float LeftJoystickY;
    float RightJoystickY;

    @Override
    public void runOpMode() throws InterruptedException {

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
                This program is used to get very specific values of both the Upper and Lower servos for each
                leg. We can use this program when we want these more specific values so we can apply them
                to the other programs.
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
            LeftJoystickX = gamepad1.left_stick_x;
            LeftJoystickY = gamepad1.left_stick_y;
            RightJoystickX = gamepad1.right_stick_x;
            RightJoystickY = gamepad1.right_stick_y;

            // FL Upper
            /*
                The A and B buttons control the FL_Upper servo. when the A button is pressed, it sets the
                Upper servo to a specific position, every time you push this button it moves the servo by 0.1.
                The B button does the same but moves it in a different direction.

             */
            if (A) {
                FL_Upper.setPosition(0.4);
                telemetry.addData("FL_Upper Pos:", FL_Upper.getPosition());
                telemetry.update();
            }
            if (B) {
                FL_Upper.setPosition(0.6);
                telemetry.addData("FL_Upper Pos:", FL_Upper.getPosition());
                telemetry.update();
            }

            // FL Lower
            /*
                Controlled by the X and Y buttons, this controls the FL_Lower servo. it moves the servo in
                 increments of 0.1 in both directions.
             */
            if (X) {
                FL_Lower.setPosition(0.4);
                telemetry.addData("FL_Lower Pos:", FL_Lower.getPosition());
                telemetry.update();
            }
            if (Y) {
                FL_Lower.setPosition(0.6);
                telemetry.addData("FL_Lower Pos:", FL_Lower.getPosition());
                telemetry.update();
            }

            // FR Upper
            /*
                Controlled by the Up and Down Dpad buttons, this controls the FR_Upper servo.
                it moves the servo in increments of 0.1 in both directions.
             */
            if (DpadUp) {
                FR_Upper.setPosition(0.4);
                telemetry.addData("FR_Upper Pos:", FR_Upper.getPosition());
                telemetry.update();
            }
            if (DpadDown) {
                FR_Upper.setPosition(0.6);
                telemetry.addData("FR_Upper Pos:", FR_Upper.getPosition());
                telemetry.update();
            }

            // FR Lower
            /*
                By Pressing the Left and Right Dpad buttons, this controls the FR_Lower servo.
                it moves the servo in increments of 0.1 in both directions.
             */
            if (DpadLeft) {
                FR_Lower.setPosition(0.4);
                telemetry.addData("FL_Lower Pos:", FR_Lower.getPosition());
                telemetry.update();
            }
            if (DpadRight) {
                FR_Lower.setPosition(0.6);
                telemetry.addData("FL_Lower Pos:", FR_Lower.getPosition());
                telemetry.update();
            }

            // BL Upper
            /*
                By Pushing the Left Joystick up or down, changing the Y value positively or negatively,
                this controls the BL_Upper servo. it moves the servo in increments of 0.1 in both directions.
                Because we use the Joysticks in the following If-Statements, and because they are not like
                buttons where they are either on or off, we first detect if the joystick is pushed past 0.2.
                If it detects it is past the 0.2 or -0.2 value, it moves the servo by 0.1 or by -0.1 in
                each corresponding direction.
             */
            if (LeftJoystickY > 0.2) {
                BL_Upper.setPosition(0.4);
                telemetry.addData("BL_Upper Pos:", BL_Upper.getPosition());
                telemetry.update();
            }
            if (LeftJoystickY < -0.2) {
                BL_Upper.setPosition(0.6);
                telemetry.addData("BL_Upper Pos:", BL_Upper.getPosition());
                telemetry.update();
            }

            // BL Lower
            /*
                By Pushing the Left Joystick up or down, changing the Y value positively or negatively,
                this controls the BL_Lower servo.
             */
            if (LeftJoystickX > 0.2) {
                BL_Lower.setPosition(0.4);
                telemetry.addData("BL_Lower Pos:", BL_Lower.getPosition());
                telemetry.update();
            }
            if (LeftJoystickX < -0.2) {
                BL_Lower.setPosition(0.6);
                telemetry.addData("BL_Lower Pos:", BL_Lower.getPosition());
                telemetry.update();
            }

            // BR Upper
            /*
                By Pushing the Left Joystick up or down, changing the Y value positively or negatively,
                this controls the BR_Upper servo.
             */
            if (RightJoystickY > 0.2) {
                BR_Upper.setPosition(0.4);
                telemetry.addData("BR_Upper Pos:", BR_Upper.getPosition());
                telemetry.update();
            }
            if (RightJoystickY < -0.2) {
                BR_Upper.setPosition(0.6);
                telemetry.addData("BR_Upper Pos:", BR_Upper.getPosition());
                telemetry.update();
            }

            // BR Lower
            /*
                By Pushing the Left Joystick up or down, changing the Y value positively or negatively,
                this controls the BR_Lower servo.
             */
            if (RightJoystickX > 0.2) {
                BR_Lower.setPosition(0.4);
                telemetry.addData("BR_Lower Pos:", BR_Lower.getPosition());
                telemetry.update();
            }
            if (RightJoystickX < -0.2) {
                BR_Lower.setPosition(0.6);
                telemetry.addData("BR_Lower Pos:", BR_Lower.getPosition());
                telemetry.update();
            }

        }

    }



}
