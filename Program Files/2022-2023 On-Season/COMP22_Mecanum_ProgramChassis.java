package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="COMP22_Mecanum_ProgramChassis")
public class COMP22_Mecanum_ProgramChassis extends LinearOpMode {

    // -- Defining Motors -- \\
    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;


    // -- Defining Servos -- \\
    Servo Claw;

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
    boolean LB; // Left Bumper
    boolean RB; // Right Bumper
    boolean X1; // X Button


    @Override
    public void runOpMode() throws InterruptedException {

        // -- Hardwaremapping Section -- \\
        /* Hardwaremapping tells the program what the motors, servos, and other technical parts should be called
        in the configuration. We also use this section to set the motors' directions, so that the robot drives the correct
        way. We set the mode of them, as well. These modes tell the motor how to act, such as it using an encoder wire.
        The lift motor is used for the lifting mechanism. F and B stand for front and back respectively, while R and L
        stand for right and left.
        */

        FL = hardwareMap.dcMotor.get("fl");
        FR = hardwareMap.dcMotor.get("fr");
        FR.setDirection(DcMotorSimple.Direction.REVERSE);
        BL = hardwareMap.dcMotor.get("bl");
        BR = hardwareMap.dcMotor.get("br");
        BR.setDirection(DcMotorSimple.Direction.REVERSE);
        Claw = hardwareMap.servo.get("claw");




        waitForStart();

        // Setting the default position of the claw, this is so that it is not too far out or in when we start.
        Claw.setPosition(0.125);

        while (opModeIsActive()) {
            // -- Code Information -- \\
            /*
            This program is made for the sole purpose of testing out programs made for mechanisms or parts of our chassis. This is the
            'PC' version, or 'Programming Chassis' version. This version allows us to test our programs and code made for mechanisms
            that are similar to those that are being worked on/installed on the main chassis. Having two programs allows us to not only
            test out our programs, but also allows the builders to continue to work on the main chassis, without one side having to wait
            on the other.
             */


            // -- Button Mapping -- \\
            // This section assigns the buttons named within the code and maps them to the proper buttons on each controller.

            // All mapped buttons for the 1st controller.
            RS = gamepad1.right_stick_y;
            LS = gamepad1.left_stick_y;
            LT = gamepad1.left_trigger;
            RT = gamepad1.right_trigger;
            X1 = gamepad1.x;


            // All mapped buttons for the 2nd controller
            RB = gamepad2.right_bumper;
            LB = gamepad2.left_bumper;
            DpadUp = gamepad1.dpad_up;
            DpadDown = gamepad1.dpad_down;
            DpadLeft = gamepad1.dpad_left;
            DpadRight = gamepad1.dpad_right;
            A = gamepad1.a;
            B = gamepad1.b;

            /*
            This portion of the code first finds and takes the current input value of the right/left joysticks on the controller.
            Next, it sets the value
            */
            float Yvalue1 = -gamepad1.left_stick_y;
            float Xvalue1 = gamepad1.left_stick_x;
            float Xvalue2 = gamepad1.right_stick_x;

            // Using calculations, these lines will set value of the Power depending on the directions of the values
            // of the Joysticks.

            // This part tells the wheels which way to spin.
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



            /* Because this season requires more delicate controller input, we handicapped the power from the full 100% to only 60%
            to allow for us to make more precise controller inputs. */
            FL.setPower(FL_power*(0.6));
            FR.setPower(FR_power*(0.6));
            BL.setPower(BL_power*(0.6));
            BR.setPower(BR_power*(0.6));



            // First Claw position.
            if (A) {
                Claw.setPosition(0.75); // Setting the claw servo to the first position.
                /*
                Setting the position of the claw servo, it then automatically runs to the set position.
                This part sets the claw to open to allow for us to pick up cones.
                */

                // Extra telemetry coding to ensure that the claw is running correctly and goes to the correct position.
                telemetry.addData("Servo is running. Running to Position:", Claw.getPosition());
                telemetry.update();
            }

           // Second Claw Position.
            if (B) {
                Claw.setPosition(0.35); // Setting the claw servo to the second position.
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
