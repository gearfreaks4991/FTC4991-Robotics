package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="wobble_park_timed")
public class wobble_park_timed extends LinearOpMode {

    //-------------------------------- Drivetrain Motors --------------------------------\\
    // Motors are Gobilda 5202 and rotate at around 435 Revolutions/Rotations Per Minute (RPM).
    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;

    //-------------------------------- Intake Motors --------------------------------\\
    // The motor for the Intake is an AndyMark Neverest 40 Motor.
    DcMotor Intake;

    //-------------------------------- Flipper Servo --------------------------------\\
    // Loadingservo is a smart servo used for our flipper on the robot.
    Servo Loadingservo;

    //-------------------------------- Flywheel Motors --------------------------------\\
    // Both Flywheel 1 and Flywheel 2 use AndyMark Neverest 40 Motors.
    DcMotor Flywheel1;
    DcMotor Flywheel2;

    //-----------------------------Wobble goal servo and motor--------------------------------------\\

    //This is the motor for rotating the wobble goal arm. It will rotate downwards 90 degrees for grabbing.
    DcMotor Rotation;
    //This servo is for grabbing and letting go of the wobble goals.
    Servo Claw;

    @Override
    public void runOpMode() throws InterruptedException {


        //-------------------------------- Hardware Mapping --------------------------------\\


        // Hardware mapping the Drivetrain's 4 main drive motors, they should be Gobilda 5202 which spin at 435 RPM.
        FL = hardwareMap.dcMotor.get("lf");
        FR = hardwareMap.dcMotor.get("rf");
        BL = hardwareMap.dcMotor.get("lb");
        BR = hardwareMap.dcMotor.get("rb");
        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Reversing 2 of the Drivetrain motors to allow the wheels to all move in the correct direction.
        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);


        // - Intake Motor, uses a single AndyMark Neverest 40 Motor.
        Intake = hardwareMap.dcMotor.get("intake");


        // - Flipper Servo.
        Loadingservo = hardwareMap.servo.get("flipper");


        // - Flywheel Motors, which are both AndyMark Neverest 40 Motors.
        Flywheel1 = hardwareMap.dcMotor.get("flyl");
        Flywheel2 = hardwareMap.dcMotor.get("flyr");

        // Reversing one of the Flywheel motors to allow them both to spin in the same direction.
        Flywheel2.setDirection(DcMotor.Direction.REVERSE);

        // - Wobble Goal Mechanism Motors.
        Rotation = hardwareMap.dcMotor.get("rotation");
        Rotation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Rotation.setTargetPosition(0);
        Rotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        Claw = hardwareMap.servo.get("claw");

//        Rotation.setTargetPosition(0);


        //-------------------------------- Robot Start Telemetry & WaitForStart Code. --------------------------------\\

        telemetry.addData("Status", "Ready to run");
        telemetry.update();


        // Wait for the game to start (driver presses PLAY).
        waitForStart();

        //-------------------------------- Variables and Step-By-Step Code --------------------------------\\

        // check to see if any buttons are pressed.

            /*Step 1  -  Drive Forward 62 inches
            (12.566 is the circumference.) (4.93 rotations for 62 inches)
            (1891.148 ticks in 62 inches) (I rounded to 1891)
            */
        FL.setPower(0.75);
        FR.setPower(0.75);
        BR.setPower(0.75);
        BL.setPower(0.75);
        sleep(2525);
        FL.setPower(0.00);
        FR.setPower(0.00);
        BR.setPower(0.00);
        BL.setPower(0.00);
        sleep(750);
        FL.setPower(-0.75);
        FR.setPower(-0.75);
        BR.setPower(-0.75);
        BL.setPower(-0.75);
        sleep(1250);
        FL.setPower(0.00);
        FR.setPower(0.00);
        BR.setPower(0.00);
        BL.setPower(0.00);
    }
}