package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="COMP22_Mecanum_BuildChassis_Piper")
public class COMP22_Mecanum_BuildChassis_Piper extends LinearOpMode {

    // -- Defining Motors -- \\
    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;
    DcMotor Lift;
    Servo Claw;


    // -- Defining Variables -- \\
    double FL_power = 0.0;
    double FR_power = 0.0;
    double BL_power = 0.0;
    double BR_power = 0.0;
    double Lift_power = 0.0;
    double Motor_Power = 0.0;


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
    boolean LB; // Left Bumper
    boolean RB; // Right Bumper
    boolean X; // X Button
    boolean Y; // Y Button


    // This integer is used for the Lift motor.
    int destination = 0;


    @Override
    public void runOpMode() throws InterruptedException {

        // -- Hardwaremapping Section -- \\
        /*
            Hardwaremapping tells the program what the motors, servos, and other technical parts should be called
            in the configuration. We also use this section the motors' directions, so that the robot drives the correct
            way, and set the mode of them, as well. The lift motor is used for the lifting mechanism. The Wheel
            motor is used for the Carousel mechanism. F and B stand for front and back respectively, while R and L
            stand for right and left.
        */

        FL = hardwareMap.dcMotor.get("fl");
        FR = hardwareMap.dcMotor.get("fr");
        FR.setDirection(DcMotorSimple.Direction.REVERSE);
        BL = hardwareMap.dcMotor.get("bl");
        BL.setDirection(DcMotorSimple.Direction.REVERSE);
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

            //Buttons for 1st controller
            RS = gamepad1.right_stick_y;
            LS = gamepad1.left_stick_y;
            LT = gamepad1.left_trigger;
            RT = gamepad1.right_trigger;
            X = gamepad1.x;
            RB = gamepad2.right_bumper;
            LB = gamepad2.left_bumper;
            DpadUp = gamepad1.dpad_up;
            DpadDown = gamepad1.dpad_down;
            DpadLeft = gamepad1.dpad_left;
            DpadRight = gamepad1.dpad_right;
            A = gamepad1.a;
            B = gamepad1.b;
            Y = gamepad1.y;

            //Buttons for 2nd controller


            float Yvalue1 = -gamepad1.left_stick_y;
            float Xvalue1 = gamepad1.left_stick_x;
            float Xvalue2 = gamepad1.right_stick_x;

            /*
                These lines will set value of the power depending on the directions of the values
                of the joysticks.
            */

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

            /*
                Finally, this takes the Power Variables, and sets the power of the actual Motors to the power of the
                Joysticks, while multiplying the power of the wheels to the value of the Motor_Power variable.
            */

            FL.setPower(FL_power*(Motor_Power));
            FR.setPower(FR_power*(Motor_Power));
            BL.setPower(BL_power*(Motor_Power));
            BR.setPower(BR_power*(Motor_Power));


            /*
                Two simple button functions. One the right bumper, when pressed, it sets the drivetrain motors' power to roughly
                32%. The point to having this is to allow for much more precise maneuvering when necessary, like when driving around the
                poles, signals, and other key locations. When we need to get somewhere faster, such as larger distances across the
                field, we can press the Left Trigger to set the motor speed to 60%, which is the maximum speed that is still safe
                and easy to maneuver around stably.
             */
            if (RT > 0.9) {
                Motor_Power = 0.315;
            }
            if (LT > 0.9) {
                Motor_Power = 0.6;
            }

            /*
                This is a software RPM limiter. The purpose of this is to ensure that we don't move the lift so fast that we
                accidentally go over the tick limit. In this case, as the lift nears the software limiter we implemented,
                it slows from 100% speed to only 75% speed. When it detects the tick value is not above or below a certain value,
                the motor returns to full speed.
            */
            if (Lift.getCurrentPosition() <= -2700) {
                Lift_power = 1.00;
            }
            else {
                Lift_power = 0.75;
            }

            // Does the same as the other RPM limiter, but makes it so that the lift slows down when it is nearing its' 0-tick position.
            if (Lift.getCurrentPosition() >= -300) {
                Lift_power = 0.75;
            }
            else {
                Lift_power = 1.00;
            }

            // Telemetry that displays at all times to be able to get an idea of the values of certain aspects of the robot.
            telemetry.addData("Current Power Value:", Lift_power);
            telemetry.addData("Lift Pos:", Lift.getCurrentPosition());
            telemetry.addData("Destination:", destination);
            telemetry.addData("Current Power:",Lift.getPower());
            telemetry.update();



            if (B) {
                Lift.setPower(Lift_power); // Sets the power of the Lift to 100% in Forward
                destination=Lift.getCurrentPosition(); // Finds and Stores the current position of the Lift.
                destination+=25; // This adds the number of Ticks to the stored current position of the lift. // Sleep to ensure that it has time to complete the action.
                Lift.setTargetPosition(destination); // The Position of the Lift is Updated to the newer Position.
                telemetry.addData("Lift Pos:", Lift.getCurrentPosition());
                telemetry.addData("Destination:", destination);
                telemetry.addData("Current Power:",Lift.getPower());
                telemetry.update();
            }

            // This does the same thing as the other D-Pad Statement, but subtracts Ticks instead of adding them.
            if (A) {
                Lift.setPower(Lift_power);
                destination=Lift.getCurrentPosition();
                destination-=25; // Minus and equal sign subtracts 100 Ticks and stores it as it's new base value.
                Lift.setTargetPosition(destination);
                telemetry.addData("Lift Pos:", Lift.getCurrentPosition());
                telemetry.addData("Destination:", destination);
                telemetry.addData("Current Power:",Lift.getPower());
                telemetry.update();
            }

            // Sets the target position so that the robot is able to drop the cone on the tallest pole.
            if (DpadUp) {
                Lift.setTargetPosition(-3000);
            }

            // Sets the target position to be able to drop a cone on the intermediate-level pole.
            if (DpadLeft) {
                Lift.setTargetPosition(-2160);
            }

            // Sets the target position of the lift to the level needed to drop a cone on the smallest pole.
            if (DpadDown) {
                Lift.setTargetPosition(-1300);
            }

            // Sets the target position to the level needed to drop a cone in the signal areas.
            if (DpadRight) {
                Lift.setTargetPosition(-140);
            }

            /* Sets target position back to zero.
            if (LB) {
                Lift.setTargetPosition(0);
            }
            */

            /* Safety feature. I put in a software limit to make sure the lift doesn't go so high that it snaps the string or break
            any other components */
            if (Lift.getCurrentPosition() <= -3000) { // Detecting if the lift positon is set to be less than -3000.

                    Lift.setTargetPosition(-3000); // Immediately sets the position back to -3000 ticks.
                    telemetry.addData("WARNING! Tick limit reached. Returning to safe destination.", Lift.getCurrentPosition() );
                    telemetry.update();
                    // Telemetry warning that the software limiter has been reached, alerting any drivers to the situation.

            }

            if (Y) {
                Claw.setPosition(0.00); // Setting the claw servo to the first position.
                /*
                Setting the position of the claw servo, it then automatically runs to the set position.
                This part sets the claw to open to allow for us to pick up cones.
                */

                // Extra telemetry coding to ensure that the claw is running correctly and goes to the correct position.
                telemetry.addData("Servo is running. Running to Position:", Claw.getPosition());
                telemetry.update();
            }

            // Second Claw Position.
            if (X) {
                Claw.setPosition(1.00); // Setting the claw servo to the second position.
                /*
                This second position allows the claw to grab the cone firmly.
                 */

                // More telemetry to allow us to see if there are any issues with the claw and/or our program.
                telemetry.addData("Servo is running. Running to Position:", Claw.getPosition());
                telemetry.update();
            }


        }
    }
}