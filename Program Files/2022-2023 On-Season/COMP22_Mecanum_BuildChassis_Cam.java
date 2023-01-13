package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="COMP22_Mecanum_BuildChassis_Cam")
public class COMP22_Mecanum_BuildChassis_Cam extends LinearOpMode {

    // -- Defining motors used within the program. -- \\
    DcMotor FL; // Front Left (FL) drive motor.
    DcMotor FR; // Front Right (FR) drive motor.
    DcMotor BL; // Back Left (BL) drive motor.
    DcMotor BR; // Back Right (BR) drive motor.
    DcMotor Lift; // Lift motor.
    Servo Claw; // The Cam/Claw. Name is 'claw', however it could be either one depending on what we have installed.


    // -- Defining additional variables needed within the program. -- \\
    double FL_power = 0.0;
    double FR_power = 0.0;
    double BL_power = 0.0;
    double BR_power = 0.0;
    double Lift_power = 0.0;
    double Motor_Power = 0.0;
    int B = 1;
    int destination = 0;


    // -- Defining the buttons used on both gamepad 1 and 2. -- \\

    // Gamepad 1 Buttons.
    float RS1; // Right Joystick, Gamepad 1.
    float LS1; // Left Joystick, Gamepad 1.
    boolean LB1; // Left Bumper 1, Gamepad 1.
    boolean RB1; // Right Bumper 1, Gamepd 1.
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
                This is our primary TeleOp program for the 2022-2023 competition season. Much like previous seasons, we use Mecanum
                wheels and basic controls for simple and easy operation. This year we coded lots of lift operations as we felt it would
                be important. This is the version made for the cam design that Sulle (our 3D Printer) made.
             */


            // -- Button Mapping -- \\
            // This sets the Button Variables to the correct buttons on the GamePads.

            // Buttons for 1st controller
            RS1 = gamepad1.right_stick_y; // Right Joystick on the first controller, controls the drive motors.
            LS1 = gamepad1.left_stick_y; // Left Joystick on the first controller, controls the drive motors.
            LB1 = gamepad1.left_bumper; // Sets the drive motor's maximum power to 40%.
            RB1 = gamepad1.right_bumper; // Sets the drive motor's maximum power to 70%.
            DpadDown1 = gamepad1.dpad_down; // Sets the lift back down to the 0 position.

            // Buttons for 2nd controller
            LT2 = gamepad2.left_trigger; // Opens the cam/claw mechanism.
            RT2 = gamepad2.right_trigger; // Closes the cam/claw mechanism.
            LB2 = gamepad2.left_bumper; // Raises the lift manually by 50 ticks.
            RB2 = gamepad2.right_bumper; // Lowers the lift manually by 50 ticks.
            DpadUp2 = gamepad2.dpad_up; // Picks up a cone, then raises the lift to the high pole.
            DpadDown2 = gamepad2.dpad_down; // Sets the tick position to right above where we need it to be to pick up cones.
            DpadLeft2 = gamepad2.dpad_left; // Picks up a cone, then raises the lift to the low pole.
            DpadRight2 = gamepad2.dpad_right; // Picks up a cone, then raises the lift to the medium pole.
            A2 = gamepad2.a; // Picks up a cone, then raises the lift to the low pole. This is used for the beacon.
            B2 = gamepad2.b; // Closes the cam/claw, then raises the lift to the medium pole.
            X2 = gamepad2.x; // Closes the cam/claw, then raises the lift to the low pole.
            Y2 = gamepad2.y; // Closes the cam/claw, then raises the lift to the high pole.


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
             the motors to roughly either 40% power or 70% power. This section of the program multiplies the motor speed's value
             by motor_speed variable, then sets the power of the drive motors themselves to the power value taken from the 4 motor
             variables.

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
                Two simple button functions. One, the right bumper, when pressed, it sets the drivetrain motors' power to roughly
                40%. The point to having this is to allow for much more precise maneuvering when necessary, like when driving around the
                poles, signals, and other key locations. When we need to get somewhere faster, such as larger distances across the
                field, we can press the Left Trigger to set the motor speed to 70%, which is the maximum speed that is still safe
                and easy to maneuver around the playing field stably.
             */
            if (LB1) {
                Motor_Power = 0.4; // Setting the motor power cap to 40% for easier maneuvering.
                // Extra telemetry statements.
                telemetry.addData("Low Speed Mode Enabled. Current Power:",Motor_Power);
                telemetry.update();
            }
            if (RB1) {
                Motor_Power = 0.7; // Setting the motor power cap to 70% to drive around the field much quicker.
                // Extra telemetry statements.
                telemetry.addData("High Speed Mode Enabled. Current Power:",Motor_Power);
                telemetry.update();
            }

            /*
                This button on the primary gamepad makes the lift return back to its' original zero position. This is used for
                quickly resetting the lift and picking up cones easier.
             */
            if (DpadDown1) {
                B = 0; // Setting this variable to 0 makes sure it doesn't continuously repeat in a loop.
                Lift.setPower(0.50); // Lift power is set to 50%
                Lift.setTargetPosition(0); // Setting the lift's target position.
                // Telemetry to help drivers understand what the robot is doing.
                telemetry.addData("Returning to zero position.", Lift.getCurrentPosition());
                telemetry.update();
                B = 1; // Setting it to 1 to signify the program has completed one cycle.
                Lift.setPower(Lift_power); // Setting the lift power from 50% back to the level determined by the Lift_power variable.
            }

            // Gamepad 2 Controls \\


            if (LB2){
                Lift.setPower(Lift_power); // Sets the power of the Lift to the value of the Lift_power variable.
                destination=Lift.getCurrentPosition(); // Finds and Stores the current position of the Lift.
                destination+=50; // This adds the number of Ticks to the stored current position of the lift.
                Lift.setTargetPosition(destination); // The Position of the Lift is Updated to the newer Position.
                // Telemetry statements
                telemetry.addData("Lift Pos:", Lift.getCurrentPosition());
                telemetry.addData("Destination:", destination);
                telemetry.addData("Current Power:",Lift.getPower());
                telemetry.update();
            }

            // This does the same thing as the Left Bumper Statement, but subtracts Ticks instead of adding them.
            if (RB2) {
                Lift.setPower(Lift_power);
                destination=Lift.getCurrentPosition();
                destination-=50; // Minus and equal sign subtracts 50 Ticks and stores it as it's new base value.
                Lift.setTargetPosition(destination);
                telemetry.addData("Lift Pos:", Lift.getCurrentPosition());
                telemetry.addData("Destination:", destination);
                telemetry.addData("Current Power:",Lift.getPower());
                telemetry.update();
            }

            if (DpadUp2) {
                B = 0; // Setting the variable to 0 to signify the start of the program cycle.
                Lift.setPower(1.00); // Sets the lift power to 100%
                /*
                This portion of the code sets the target position to 224 ticks lower than the actual position needed to pick up the
                cone. The point of this is to allow time for the lift to lower at a safe speed and make sure it has the time to do so.
                 */
                Lift.setTargetPosition(-350);
                if (Lift.getCurrentPosition() > -1000) {
                    sleep(750);
                }
                else{
                    sleep(1500);
                }
                Lift.setPower(0.50); // Setting the Lift's power value to 50%.
                Lift.setTargetPosition(-126); // Setting the target position of the lift to the position needed to pick up the cone.
                sleep(500); // Sleeping to ensure it has time to run to its' positions.
                Claw.setPosition(0.00); // Changing the Cam/Claw's position to be able to pick up the cone.
                sleep(900); // Sleeping to ensure it has time to run to its' positions.
                Lift.setTargetPosition(-3000); // Setting the target position to the highest pole.
                B = 1; // Closing the program loop.
            }

            // Sets the Lift position to right above where the cone is, allowing us to align ourselves to proceed with picking it up.
            if (DpadDown2) {
                Lift.setTargetPosition(-375);
            }

            // Setting the Lift to the Low Pole Position
            if (DpadLeft2) {
                Lift.setTargetPosition(-350); // Setting the target position here to allow time for the lift to properly lower.
                sleep(1500); // Sleep statement to ensure it has time to run to its' positions.
                B = 0; // Setting the variable to 0 to signify the start of the program cycle.
                Lift.setPower(0.50); // Setting the lift power to 50%.
                Lift.setTargetPosition(-126); // Setting the lift position to the height needed to pick up cones.
                sleep(500); // Sleep statement to ensure it has time to run to its' positions.
                Claw.setPosition(0.00); // Changing the Cam/Claw position to be able to pick up cones.
                sleep(900); // Sleep statement to ensure it has time to run to its' positions.
                Lift.setTargetPosition(-1385); // Setting the target position to the lowest pole.
                B = 1; // Closing the program loop.
            }

            if (DpadRight2) {
                B = 0; // Setting the variable to 0 to signify the start of the program cycle.
                /*
                This does something similar to the previous buttons where it sets the lift position to just above where it is able to
                pick up the cone so the lift has the time it needs to lower at a safe speed.
                 */
                if(Lift.getCurrentPosition() <= -350) {
                    Lift.setPower(0.50);
                }
                else{
                    Lift.setPower(0.25);
                }
                Lift.setTargetPosition(-350);
                sleep(750); // Sleep statement to ensure it has time to run to its' positions.
                Lift.setTargetPosition(-126); // Sets the position to allow us to pick up cones.
                sleep(500); // Sleep statement to ensure it has time to run to its' positions.
                Claw.setPosition(0.00); // Changing the Cam/Claw position to allow us to pick up cones.
                sleep(900); // Sleep statement to ensure it has time to run to its' positions.
                Lift.setTargetPosition(-2236); // Setting the target position to the Middle Level Pole.
                B = 1; // Closing the program loop.
            }


            if (RT2 > 0.9) {
                Claw.setPosition(1.00); // Setting the claw servo to the position to drop off both cones and the signal.
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
                Claw.setPosition(0.00); // Setting the claw servo to the position to be able to pick up cones and the signal.
                /*
                This second position allows the claw to grab the cone firmly.
                 */

                // More telemetry to allow us to see if there are any issues with the claw and/or our program.
                telemetry.addData("Servo is running. Running to Position:", Claw.getPosition());
                telemetry.update();
            }

            /*
            By pressing the Y button on the 2nd controller first closes the cam/claw to allow us to pick up cones, then sets
            the lift position to -3000 ticks, which allows us to drop cones onto the tallest pole.
             */
            if (Y2) {
                Claw.setPosition(0.00);
                sleep(750);
                Lift.setTargetPosition(-3000);
            }

            /*
            By pressing the B button on the 2nd controller, it closes the cam/claw, waits, then lifts to the medium pole position.
             */
            if (B2) {
                Claw.setPosition(0.00);
                sleep(750);
                Lift.setTargetPosition(-2236);
            }

            /*
            Pressing the X button on the 2nd gamepad controller closes the cam/claw, then waits to ensure it has picked up the desired
            game element, then lifts to the positon allowing us to drop off the elements at the low pole.
             */
            if (X2) {
                Claw.setPosition(0.00);
                sleep(750);
                Lift.setTargetPosition(-1385);
            }


            if (A2) {
                B = 0; // Setting the variable to 0 to signify the start of the program cycle.
                Lift.setPower(0.50); // Setting the lift power to 50%.
                Lift.setTargetPosition(0); // Setting the lift target position to zero.
                /*
                Does something similar however allows the lift a longer wait time if it detects that it is lowering from a higher
                height. If it is, then it will allow a longer sleep period to allow the lift to naturally lower. If it is lowering from
                a lower height, then the sleep is decreased to speed up the process.
                 */
                if (Lift.getCurrentPosition() > -1000) {
                    sleep(500);
                }
                else{
                    sleep(750);
                }
                Claw.setPosition(0.00); // Changing the Cam/Claw position to allow us to pick up cones.
                sleep(1500); // Sleep statement to ensure it has time to run to its' positions.
                Lift.setTargetPosition(-1385);
                B = 1; // Closing the program loop.
            }

            // Extra Telemetry Values \\

            /*
             These telemetry values will display constantly to give the drive team more confidence on where they are. It includes
             the motor power, the actual lift power, lift position, and the lift_power variable's value.
             */
            telemetry.addData("Current Power Value:", Lift_power);
            telemetry.addData("Lift Pos:", Lift.getCurrentPosition());
            telemetry.addData("Destination:", destination);
            telemetry.addData("Current Power:",Lift.getPower());
            telemetry.update();

            /*
            This section of the program is crucial to ensuring the reliability of our lift. We found that if it goes over -3000 ticks,
            it will reach the limit of the strings which will break them or damage them. To prevent this, we added a stop for both the
            mechanic and software sides. The software side constantly checks to see if the lift's current position is close to -3000
            or if it's destination is close to or over -3000. If it detects that it is, then it will set the position to -3000 ticks
            and display a telemetry statement warning the drive team that the lift has reached the "tick limiter".
             */
            if (Lift.getCurrentPosition() <= -3000) {

                Lift.setTargetPosition(-3000);
                telemetry.addData("WARNING! Tick limit reached. Returning to safe destination.", Lift.getCurrentPosition() );
                telemetry.update();

            }

            /*
            This part slows the lift if it detects the tick value going over -2700. If it decreases further, it sets the lift power to
            75%. Otherwise, it remains at full power to allow for speedy ascents and descents.
             */
            if ((Lift.getCurrentPosition() <= -2700) && (B==1)){
                Lift_power = 1.00;
            }
            else {
                Lift_power = 0.75;
            }

            /*
            Does the same thing as the statement above this one. However, instead of it slowing it when it nears the mechanical limit
            of the motor, it slows when the lift comes close to the zero position.
             */
            if ((Lift.getCurrentPosition() >= -300) && (B==1)){
                Lift_power = 1.00;
            }
            else {
                Lift_power = 0.75;
            }



        }
    Motor_Power = 0; // Closes the program loop to ensure the robot doesn't act erratically while operating.
    }
}