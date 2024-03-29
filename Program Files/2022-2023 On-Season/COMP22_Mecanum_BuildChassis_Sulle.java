package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="COMP22_Mecanum_BuildChassis_Sulle")
public class COMP22_Mecanum_BuildChassis_Sulle extends LinearOpMode {

    // -- Defining motors used within the program. -- \\
    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;
    DcMotor Lift;
    Servo Claw;


    // -- Defining additional variables needed within the program. -- \\
    double FL_power = 0.0;
    double FR_power = 0.0;
    double BL_power = 0.0;
    double BR_power = 0.0;
    double Lift_power = 0.0;
    double Motor_Power = 0.0;
    int B = 1;
    int destination = 0;
    double C = 0;


    // -- Defining the buttons used on both gamepad 1 and 2. -- \\

    // Gamepad 1 Buttons.
    float RS1; // Right Joystick
    float LS1; // Left Joystick
    boolean LB1; // Left Bumper 1
    boolean RB1; // Right Bumper 1
    float LT1; // Left Trigger, Gamepad 1.
    float RT1; // Right Trigger, Gamepad 1.
    boolean DpadDown1; // D-Pad Down, Gamepad 1.

    // Gamepad 2 Buttons.
    boolean LB2; // Left Bumper, Gamepad 2.
    boolean RB2; // Right Bumper, Gamepad 2.
    boolean DpadDown2; // D-Pad Down, Gamepad 2.
    boolean DpadUp2; // D-Pad Up, Gamepad 2.
    boolean DpadLeft2; // D-Pad Left, Gamepad 2.
    boolean DpadRight2; // D-Pad Right, Gamepad 2.
    float LT2; // Left Trigger, Gamepad 2.
    float RT2; // Right Trigger, Gamepad 2.
    boolean A2; // A Button, Gamepad 2.
    boolean B2; // B Button, Gamepad 2.
    boolean X2; // X Button, Gamepad 2.
    boolean Y2; // Y Button, Gamepad 2.



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
        FR = hardwareMap.dcMotor.get("fr");
        FR.setDirection(DcMotorSimple.Direction.REVERSE);
        BL = hardwareMap.dcMotor.get("bl");
        BR = hardwareMap.dcMotor.get("br");
        BR.setDirection(DcMotorSimple.Direction.REVERSE);
        Lift = hardwareMap.dcMotor.get("lift");
        Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lift.setTargetPosition(0);
        Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Claw = hardwareMap.servo.get("claw");
        telemetry.addData("Current Starting Position:", Lift.getCurrentPosition());
        telemetry.update();


        waitForStart();


        while (opModeIsActive()) {
            // -- Code Information -- \\
            /*
                PLEASE CHANGE!!!!!!
             */


            // -- Button Mapping -- \\
            // This sets the Button Variables to the correct buttons on the GamePads.

            // Buttons for 1st controller
            RS1 = gamepad1.right_stick_y;
            LS1 = gamepad1.left_stick_y;
            LT1 = gamepad1.left_trigger;
            RT1 = gamepad1.right_trigger;
            LB1 = gamepad1.left_bumper;
            RB1 = gamepad1.right_bumper;
            DpadDown1 = gamepad1.dpad_down;

            // Buttons for 2nd controller
            LT2 = gamepad2.left_trigger;
            RT2 = gamepad2.right_trigger;
            LB2 = gamepad2.left_bumper;
            RB2 = gamepad2.right_bumper;
            DpadUp2 = gamepad2.dpad_up;
            DpadDown2 = gamepad2.dpad_down;
            DpadLeft2 = gamepad2.dpad_left;
            DpadRight2 = gamepad2.dpad_right;
            A2 = gamepad2.a;
            B2 = gamepad2.b;
            X2 = gamepad2.x;
            Y2 = gamepad2.y;


            // Gamepad 1 Controls \\

            /**
             In this section of the program, we convert the values taken from the joysticks and set it the power of the motors.

             // Step-By-Step Explanation: \\

             1: The program takes the values that the gamepad sticks are currently at, and assigns them to one of three variables depending
             on which stick was moved and in which direction.

             2: The next two parts of the program then takes the assigned values' numbers and does simple math equations based off
             of a diagram we use to determine the direction of the wheels and assigns the numbers from these equations to an
             additional set of variables.

             3: Using a 'Range Clip', it limits the value that the power can be set to in the program to any number within the range
             of -1 and 1. This prevents the motors from being damaged from trying to set them to a power value that is unable to be
             reached.

             4: The final part of this program sets the power to the 4 drive wheels. For example, it takes the values from the
             FL_power variable and sets the power value of the Front Left motor to it. There is oen extra aspect to this part of the
             program, the motor_power variable. Using the left and right bumper on the 1st gamepad, we can set the 'maximum' power of
             the motors to roughly either 40% power or 80% power. This part of the program multiplies the value that will

             */

            float Yvalue1 = -gamepad1.left_stick_y;
            float Xvalue1 = gamepad1.left_stick_x;
            float Xvalue2 = gamepad1.right_stick_x;

            FL_power = (Yvalue1 + Xvalue1);
            FR_power = (Yvalue1 - Xvalue1);
            BL_power = (Yvalue1 - Xvalue1);
            BR_power = (Yvalue1 + Xvalue1);

            if (Xvalue2 > 0 || Xvalue2 < 0) {
                FL_power = (Xvalue2);
                FR_power = -(Xvalue2);
                BL_power = (Xvalue2);
                BR_power = -(Xvalue2);
            }

            // Limits the power to between 1 and -1, which is 100% power in forward and reverse.
            FL_power = Range.clip(FL_power, -1, 1);
            FR_power = Range.clip(FR_power, -1, 1);
            BL_power = Range.clip(BL_power, -1, 1);
            BR_power = Range.clip(BR_power, -1, 1);

            // Finally, this takes the Power Variables, and sets the power of the actual Motors to the power of the
            // Joysticks.

            FL.setPower(FL_power*(Motor_Power));
            FR.setPower(FR_power*(Motor_Power));
            BL.setPower(BL_power*(Motor_Power));
            BR.setPower(BR_power*(Motor_Power));


             /*
                Two simple button functions. One the right bumper, when pressed, it sets the drivetrain motors' power to roughly
                32%. The point to having this is to allow for much more precise maneuvering when necessary, like when driving around the
                poles, signals, and other key locations. When we need to get somewhere faster, such as larger distances across the
                field, we can press the Left Trigger to set the motor speed to 60%, which is the maximum speed that is still safe
                and easy to maneuver around the playing field stably.
             */
            if (LB1) {
                Motor_Power = 0.4;
                telemetry.addData("Low Speed Mode Enabled. Current Power:",Motor_Power);
                telemetry.update();
                C = 0.4;
            }
            if (RB1) {
                Motor_Power = 0.7;
                telemetry.addData("High Speed Mode Enabled. Current Power:",Motor_Power);
                telemetry.update();
                C = 0.7;
            }

            /*

             */
            if (DpadDown1) {
                B = 0;
                Lift.setPower(0.50);
                Lift.setTargetPosition(0);
                telemetry.addData("Returning to zero position.", Lift.getCurrentPosition());
                telemetry.update();
                B = 1;
                Lift.setPower(Lift_power);
            }

            // Gamepad 2 Controls \\

            /*if (Lift.isBusy()) {
                //Motor_Power = 0;
            }

            else{Motor_Power = C;
            } */


            if (LB2){
                Lift.setPower(Lift_power); // Sets the power of the Lift to 100% in Forward
                destination=Lift.getCurrentPosition(); // Finds and Stores the current position of the Lift.
                destination+=50; // This adds the number of Ticks to the stored current position of the lift. // Sleep to ensure that it has time to complete the action.
                Lift.setTargetPosition(destination); // The Position of the Lift is Updated to the newer Position.
                telemetry.addData("Lift Pos:", Lift.getCurrentPosition());
                telemetry.addData("Destination:", destination);
                telemetry.addData("Current Power:",Lift.getPower());
                telemetry.update();
            }

            // This does the same thing as the other D-Pad Statement, but subtracts Ticks instead of adding them.
            if (RB2) {
                Lift.setPower(Lift_power);
                destination=Lift.getCurrentPosition();
                destination-=50; // Minus and equal sign subtracts 100 Ticks and stores it as it's new base value.
                Lift.setTargetPosition(destination);
                telemetry.addData("Lift Pos:", Lift.getCurrentPosition());
                telemetry.addData("Destination:", destination);
                telemetry.addData("Current Power:",Lift.getPower());
                telemetry.update();
            }

            if (DpadUp2) {
                B = 0;
                if(Lift.getCurrentPosition() <= -350) {
                    Lift.setPower(0.50);
                }
                else{
                    Lift.setPower(0.25);
                }
                Lift.setTargetPosition(-350);
                sleep(2000);
                Lift.setTargetPosition(-126);
                sleep(1000);
                Claw.setPosition(0.00);
                sleep(1000);
                Lift.setTargetPosition(-3000);
                B = 1;
            }

            if (DpadDown2) {
                Lift.setTargetPosition(-375);
            }

            if (DpadLeft2) {
                B = 0;
                if(Lift.getCurrentPosition() <= -350) {
                    Lift.setPower(0.50);
                }
                else{
                    Lift.setPower(0.25);
                }
                Lift.setTargetPosition(-350);
                sleep(2000);
                Lift.setTargetPosition(-126);
                sleep(1000);
                Claw.setPosition(0.00);
                sleep(2000);
                Lift.setTargetPosition(-1385);
                B = 1;
            }

            if (DpadRight2) {
                B = 0;
                if(Lift.getCurrentPosition() <= -350) {
                    Lift.setPower(0.50);
                }
                else{
                    Lift.setPower(0.25);
                }
                Lift.setTargetPosition(-350);
                sleep(4000);
                Lift.setTargetPosition(-126);
                sleep(1000);
                Claw.setPosition(0.00);
                sleep(1000);
                Lift.setTargetPosition(-2236);
                B = 1;
            }


            if (RT2 > 0.9) {
                Claw.setPosition(1.00); // Setting the claw servo to the first position.
                /*
                Setting the position of the claw servo, it then automatically runs to the set position.
                This part sets the claw to open to allow for us to pick up cones.
                */

                // Extra telemetry coding to ensure that the claw is running correctly and goes to the correct position.
                telemetry.addData("Servo is running. Running to Position:", Claw.getPosition());
                telemetry.update();
            }

            // Second Claw Position.
            if (LT2 > 0.9) {
                Claw.setPosition(0.00); // Setting the claw servo to the second position.
                /*
                This second position allows the claw to grab the cone firmly.
                 */

                // More telemetry to allow us to see if there are any issues with the claw and/or our program.
                telemetry.addData("Servo is running. Running to Position:", Claw.getPosition());
                telemetry.update();
            }

            if (Y2) {
                Claw.setPosition(0.00);
                sleep(750);
                Lift.setTargetPosition(-3000);
            }

            if (B2) {
                Claw.setPosition(0.00);
                sleep(750);
                Lift.setTargetPosition(-2236);
            }

            if (X2) {
                Claw.setPosition(0.00);
                sleep(750);
                Lift.setTargetPosition(-1385);
            }

            if (A2) {
                Lift.setTargetPosition(0);
                sleep(100);
                Claw.setPosition(0.00);
                sleep(100);
                Lift.setTargetPosition(-3000);
            }

            // Extra Telemetry Values \\

            telemetry.addData("Current Power Value:", Lift_power);
            telemetry.addData("Lift Pos:", Lift.getCurrentPosition());
            telemetry.addData("Destination:", destination);
            telemetry.addData("Current Power:",Lift.getPower());
            telemetry.update();


            if (Lift.getCurrentPosition() <= -3000) {

                Lift.setTargetPosition(-3000);
                telemetry.addData("WARNING! Tick limit reached. Returning to safe destination.", Lift.getCurrentPosition() );
                telemetry.update();

            }


            if ((Lift.getCurrentPosition() <= -2700) && (B==1)){
                Lift_power = 1.00;
            }
            else {
                Lift_power = 0.75;
            }

            if ((Lift.getCurrentPosition() >= -300) && (B==1)){
                Lift_power = 1.00;
            }
            else {
                Lift_power = 0.75;
            }



        }
    Motor_Power = 0;
    }
}