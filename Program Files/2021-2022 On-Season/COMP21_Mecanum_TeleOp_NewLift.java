package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="COMP21_Mecanum_TeleOp_NewLift")
public class COMP21_Mecanum_TeleOp_NewLift extends LinearOpMode {

    // -- Defining Motors -- \\
    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;
    DcMotor Lift;
    DcMotor Wheel;
    ColorSensor Color;

    // -- Defining Servos -- \\
    Servo Intake;

    // -- Defining Variables -- \\
    double FL_power = 0.0;
    double FR_power = 0.0;
    double BL_power = 0.0;
    double BR_power = 0.0;

    // -- Defining Buttons -- \\
    float RS; // Right Joystick
    float LS; // Left Joystick
    boolean DpadDown; // D-Pad Down
    boolean DpadUp; // D-Pad Up
    boolean DpadLeft; // D-Pad Left
    boolean DpadRight; // D-Pad Right
    float LT; // Left Trigger
    float RT; // Right Trigger
    boolean A; // A Button
    boolean B; // B Button
    boolean RB;
    boolean LB;
    boolean LB2; // Left Bumper
    boolean RB2; // Right Bumper
    boolean X1; // X Button


    // This integer is used for the Lift motor.
    int destination = 0;


    @Override
    public void runOpMode() throws InterruptedException {

        // -- Hardwaremapping Section -- \\
        /* Hardwaremapping tells the program what the motors, servos, and other technical parts should be called
        in the configuration. We also use this section the motors' directions, so that the robot drives the correct
        way, and set the mode of them, as well. The lift motor is used for the lifting mechanism. The Wheel
        motor is used for the Carousel mechanism. F and B stand for front and back respectively, while R and L
        stand for right and left.
        */

        FL = hardwareMap.dcMotor.get("fl");
        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        FR = hardwareMap.dcMotor.get("fr");
        BL = hardwareMap.dcMotor.get("bl");
        BL.setDirection(DcMotorSimple.Direction.REVERSE);
        BR = hardwareMap.dcMotor.get("br");
        Lift = hardwareMap.dcMotor.get("lift");
        Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lift.setTargetPosition(0);
        Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Wheel = hardwareMap.dcMotor.get("wheel");
        Intake = hardwareMap.servo.get("intake");
        Color = hardwareMap.colorSensor.get("color");


           waitForStart();

        while (opModeIsActive()) {
            // -- Code Information -- \\
            /*
                This is the COMP21_Mecanum_TeleOp program, 'COMP21' designates it as it being coded and primarily
                used for the 2021 Competition Season. The 'Mecanum' section specifies that it is made for the
                Mecanum Wheel robot, which is used primarily for Competitions and Qualifiers, and finally the
                'TeleOp' portion of the name specifies that it is controlled by 1 or more people utilizing 1 or
                more gamepad controllers, and is used after the 30-second autonomous period at the beginning
                of a match.

             */


            // -- Button Mapping -- \\
            // This sets the Button Variables to the correct buttons on the GamePads.

            //Buttons for 1st controller
            RS = gamepad1.right_stick_y;
            LS = gamepad1.left_stick_y;
            LT = gamepad1.left_trigger;
            RT = gamepad1.right_trigger;
            X1 = gamepad1.x;


            //Buttons for 2nd controller
            RB = gamepad1.right_bumper;
            LB = gamepad1.left_bumper;
            RB2 = gamepad2.right_bumper;
            LB2 = gamepad2.left_bumper;
            DpadUp = gamepad2.dpad_up;
            DpadDown = gamepad2.dpad_down;
            DpadLeft = gamepad2.dpad_left;
            DpadRight = gamepad2.dpad_right;
            A = gamepad2.a;
            B = gamepad2.b;

            telemetry.addData("Red =", Color.red());
            telemetry.addData("Green =", Color.green());
            telemetry.addData("Blue =", Color.blue());
            telemetry.addData("Lift Pos:", Lift.getCurrentPosition());
            telemetry.update();
            // This finds and takes the current value of the Joysticks, and sets the value of the variables to the
            // value of the Joysticks.

            float Yvalue1 = -gamepad1.left_stick_y;
            float Xvalue1 = gamepad1.left_stick_x;
            float Xvalue2 = gamepad1.right_stick_x;

            // Using calculations, these lines will set value of the Power depending on the directions of the values
            // of the Joysticks.

            FL_power = (Yvalue1 + Xvalue1);
            FR_power = (Yvalue1 - Xvalue1);
            BL_power = (Yvalue1 - Xvalue1);
            BR_power = (Yvalue1 + Xvalue1);

            if (Xvalue2 > 0 || Xvalue2 < 0) {
                FL_power = +(Xvalue2);
                FR_power = -(Xvalue2);
                BL_power = +(Xvalue2);
                BR_power = -(Xvalue2);
            }

            // Limits the power to between 1 and -1, which is 100% power in forward and reverse.
            FL_power = Range.clip(FL_power, -1, 1);
            FR_power = Range.clip(FR_power, -1, 1);
            BL_power = Range.clip(BL_power, -1, 1);
            BR_power = Range.clip(BR_power, -1, 1);

            // Finally, this takes the Power Variables, and sets the power of the actual Motors to the power of the
            // Joysticks.

            FL.setPower(FL_power*(0.85));
            FR.setPower(FR_power*(0.85));
            BL.setPower(BL_power*(0.85));
            BR.setPower(BR_power*(0.85));


            // This is used to set the starting position of the Lift needs to be, we do this to make sure that the
            // robot starts correctly and that the Lift operates without issues.

            destination=Lift.getCurrentPosition(); // Finds and Stores the current position of the Lift.

            //these two if statements set a limit to the length the lift can go to, being 0-1800.
            if (Lift.getCurrentPosition() > 1800); {
                destination = 1800;
            }
            if (Lift.getCurrentPosition() < 0); {
                destination = 0;
            }

            if (A) {
                Lift.setPower(-1.00); // Sets the power of the Lift to 100% in Forward
                destination=Lift.getCurrentPosition();
                destination-=100; // This adds the number of Ticks to the stored current position of the lift.
                sleep (200); // Sleep to ensure that it has time to complete the action.
                Lift.setTargetPosition(destination); // The Position of the Lift is Updated to the newer Position.
                telemetry.addData("Lift Pos:", Lift.getCurrentPosition());
                telemetry.update();
            }

            // This does the same thing as the other D-Pad Statement, but subtracts Ticks instead of adding them.
            if (B) {
                Lift.setPower(1.00);
                destination=Lift.getCurrentPosition();
                destination+=100; // Minus and equal sign subtracts 100 Ticks and stores it as it's new base value.
                sleep (200);
                Lift.setTargetPosition(destination);
                telemetry.addData("Lift Pos:", Lift.getCurrentPosition());
                telemetry.update();
            }

            //We have all four D-Pad buttons change the height of the lift: D-Pad down moves to lower position,
            //D-Pad left is bottom goal position, D-Pad right is middle goal position, and D-Pad up is
            //upper goal position. We also have buttons to slightly modify the position of the lift.

            if(DpadDown) {   // Loading Position for the Lift: 0 Ticks.
                Lift.setPower(0.50);
                Lift.setTargetPosition(0);
                telemetry.addData("Lift Pos:", Lift.getCurrentPosition());
                telemetry.update();
            }

            if(DpadLeft) {     // Lowest Level Position for the Lift: ~550 Ticks.
                Lift.setPower(0.50);
                Lift.setTargetPosition(-550);
                telemetry.addData("Lift Pos:", Lift.getCurrentPosition());
                telemetry.update();
            }

            if(DpadRight) {     // Middle Level Position for the Lift: ~1050 Ticks.
                Lift.setPower(0.50);
                Lift.setTargetPosition(-1050);
                telemetry.addData("Lift Pos:", Lift.getCurrentPosition());
                telemetry.update();
            }

            if(DpadUp) {        // Top Level Position for the Lift: ~1700 Ticks.
                Lift.setPower(0.50);
                Lift.setTargetPosition(-1700);
                telemetry.addData("Lift Pos:", Lift.getCurrentPosition());
                telemetry.update();
            }


            // -- Intake System: This coding is for the intake system. The intake system is what we use to get the
            // items on the lifter. The intake system is made up of a servo connected to an axle with surgical tubing.
            // the coding is a servo and two/three buttons to control the servo both directions.
            if(LT > 0.10) {
                Intake.setPosition(1.00);
                sleep(200);
            }


            if(RT > 0.10) {
                Intake.setPosition(0.00);
                sleep(200);
            }

            //setting the position of a servo to "0.50" will stop the servo from moving entirely.
            if(RB) {
                Intake.setPosition(0.50);
            }

            if(LB) {
                Intake.setPosition(0.50);
            }

            // Carousel Mechanism: all that is needed is a motor that moves a wheel, that will then move
            // the carousel. The coding used is a motor and a button to control the motor.
            if(LB2) {
                Wheel.setPower(0.33);
                sleep(100);
            }
            else {
                Wheel.setPower(0.00);
            }

            if(RB2) {
                Wheel.setPower(-0.33);
                sleep(100);
            }
            else {
                Wheel.setPower(0.00);
            }

        }
    }
}