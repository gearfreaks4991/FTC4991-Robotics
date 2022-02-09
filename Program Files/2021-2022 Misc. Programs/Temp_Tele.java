package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Temp_Tele")
public class Temp_Tele extends LinearOpMode {

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

    //This is the motor for rotating the wobble goal arm. It will rotate downwards 150
    // degrees for grabbing.
    DcMotor Rotation;
    int Current_Rotation_Position;
    //This servo is for grabbing and letting go of the wobble goals.
    Servo Claw;

    //-------------------------------- Variables --------------------------------\\

    // Setting the power of the drivetrain motor variables.
    double FL_power = 0.0;
    double FR_power = 0.0;
    double BL_power = 0.0;
    double BR_power = 0.0;


    // Buttons that will be used to control the robot's functions.

    //Gamepad1 Buttons
    boolean buttonA1;     // Increments the destination variable by 100

    boolean buttonB1;     //This button is for subtracting 100 ticks to the motor as it moves upwards 90 degrees.
    boolean buttonX1;     //This button, if used, is to bring the motor to half its ticks per rotation, 95.9.
    boolean buttonY1;     // not used
    boolean LeftBumper1;  // This is used to start the intake motors to pick up the Rings.
    boolean RightBumper1; // This is used to load the rings into the storage so they are ready to be loaded into the Flywheel.
    int rightTrigger1;    // not used
    int leftTrigger1;     // not used
    boolean dpad_down1;   /*This button, when used, will move the motor to the exact number of ticks,
                            depending on the position we will find using buttons A and B.
                          */
    boolean dpad_up1; //This button, when used, will move the motor to its rest position, pointed upwards.
    boolean dpad_right1;  //This button is for letting go of the wobble goals.
    boolean dpad_left1;     //This button is for grabbing the wobble goals.

    //Gamepad2 Buttons

    boolean buttonY2; // This button is used to decrease the Flywheel Motors' power by 0.05%.
    boolean buttonB2; // This button is used to bring both of the Flywheel's motors to a complete stop.
    boolean buttonA2; // This button is used to set the Flywheel's power to what the variable current_power's value is.
    boolean buttonX2; // This button is used to increase the Flywheel Motors' power by 0.05%.
    //boolean rightBumper2;
    //boolean leftBumper2;
    float RightTrigger2; // This is for loading rings and resetting the flipper.
    // float LeftTrigger2;
    boolean dpad_down2; // This is used to set the Flywheel Motors to aim at the Bottom Goal.
    boolean dpad_up2; // This is used to set the Flywheel Motors to aim at the Top Goal.
    boolean dpad_right2; // This is used to set the Flywheel Motors to aim at the Middle Goal.
    // boolean dpad_left2;


    // The X and Y values of the Joystick.
    float StickX2;
    float StickY2;
    int destination=0;  //This variable is for storing 100 ticks and adding or subtracting 100 more

    //-------------------------------- Misc. Variables --------------------------------\\
    // Current_Power is the variable used to define the power of the Flywheel Motors.
    double current_power = 1.00;

    // The boolean below is used to tell if the power has been increased/decreased in the part of the code which increases and decreases the flywheel motor's power by 0.05%.
    boolean FlywheelState = true;


    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {


        //-------------------------------- Hardware Mapping --------------------------------\\


        // Hardware mapping the Drivetrain's 4 main drive motors, they should be Gobilda 5202 which spin at 435 RPM.
        FL = hardwareMap.dcMotor.get("lf");
        FR = hardwareMap.dcMotor.get("rf");
        BL = hardwareMap.dcMotor.get("lb");
        BR = hardwareMap.dcMotor.get("rb");

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
        Rotation.setDirection(DcMotor.Direction.REVERSE);
        //Rotation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Current_Rotation_Position = Rotation.getCurrentPosition();
        Rotation.setTargetPosition(Current_Rotation_Position);
        Rotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        Claw = hardwareMap.servo.get("claw");

//        Rotation.setTargetPosition(0);


        //-------------------------------- Robot Start Telemetry & WaitForStart Code. --------------------------------\\

        telemetry.addData("Starting Position: ", Current_Rotation_Position);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();


        // Wait for the game to start (driver presses PLAY).
        waitForStart();



        while (opModeIsActive()) {


            ///-------------------------------- Defining Buttons --------------------------------\\

            buttonA1 = gamepad1.a;
            buttonB1 = gamepad1.b;
            buttonX1 = gamepad1.x;
            dpad_down1 = gamepad1.dpad_down;
            dpad_up1 = gamepad1.dpad_up;
            dpad_left1 = gamepad1.dpad_left;
            dpad_right1 = gamepad1.dpad_right;

            buttonA2 = gamepad2.a;
            buttonB2 = gamepad2.b;
            buttonX2 = gamepad2.x;
            buttonY2 = gamepad2.y;
            RightTrigger2 = gamepad2.right_trigger;
            LeftBumper1 = gamepad1.left_bumper;
            RightBumper1 = gamepad1.right_bumper;
            // LeftTrigger = gamepad1.left_trigger;
            dpad_up2 = gamepad2.dpad_up;
            dpad_down2 = gamepad2.dpad_down;
            // dpad_left2 = gamepad2.dpad_left;
            dpad_right2 = gamepad2.dpad_right;


            //-------------------------------- Drivetrain Calculations & Code --------------------------------\\

            // Gets the values of the joystick.
            float Yvalue1 = -gamepad1.left_stick_y;
            float Xvalue1 = gamepad1.left_stick_x;
            float Xvalue2 = gamepad1.right_stick_x;

            // check to see if any buttons are pressed.


            // Calculates the power of all the wheels of the Drivetrain.
            FL_power = (Yvalue1 + Xvalue1);
            FR_power = (Yvalue1 - Xvalue1);
            BL_power = (Yvalue1 - Xvalue1);
            BR_power = (Yvalue1 + Xvalue1);

            if(Xvalue2 > 0 || Xvalue2 < 0) {
                FL_power = + (Xvalue2);
                FR_power = - (Xvalue2);
                BL_power = + (Xvalue2);
                BR_power = - (Xvalue2);
            }

            // Makes sure the power levels are locked in-between the values of 1.00% (100%) power and -1.00% (100%) power.
            FL_power = Range.clip(FL_power, -1, 1);
            FR_power = Range.clip(FR_power, -1, 1);
            BL_power = Range.clip(BL_power, -1, 1);
            BR_power = Range.clip(BR_power, -1, 1);

            // Sets the power of the motors to the value of the Joystick.
            FL.setPower(FL_power);
            FR.setPower(FR_power);
            BL.setPower(BL_power);
            BR.setPower(BR_power);

            //-------------------------------- Intake Motor Control Code --------------------------------\\


            // Taking in the Rings.
            if (RightBumper1 == true) {
                Intake.setPower(1.00);
            }
            else{
                Intake.setPower(0.00);
            }

            // Pushing out the Rings.
            if (LeftBumper1 == true) {
                Intake.setPower(-1.00);
            }
            else{
                Intake.setPower(0.00);
            }

            //-------------------------------- Flipper Code --------------------------------\\
            // This portion of the code will load the rings into the Flywheel to fire them to the set goal.
            if (RightTrigger2 > 0.1) {
                Loadingservo.setPosition(0.00);
                telemetry.addData("Loading Rings into Flywheel.", " ");
                telemetry.update();
                sleep(200);
                Loadingservo.setPosition(1.00);
                telemetry.addData("Returning to resting position.", " ");
                telemetry.update();
            }

            //-------------------------------- Flywheel Motor Code --------------------------------\\
            // This button wll set the Flywheel Motors' power to zero.
            if (buttonB2) {
                telemetry.addData("Stopping All Flywheel Motors.", " ");
                telemetry.update();
                current_power = Range.clip(current_power, -1, 1);
                Flywheel1.setPower(0.00);
                Flywheel2.setPower(0.00);
            }

            // This button will set the Flywheel Motors' power to what the value of "current_power" is.
            if (buttonA2) {
                telemetry.addData("All Flywheel Motors Engaged.", " ");
                telemetry.update();
                current_power = Range.clip(current_power, -1, 1);
                Flywheel1.setPower(current_power);
                Flywheel2.setPower(current_power);
            }

            // This button will set the Flywheel Motors' to 100% throttle.
            if (dpad_up2) {
                telemetry.addData("Flywheel Motors set to Top Goal.", " ,");
                telemetry.update();
                current_power=(0.95);
                Flywheel1.setPower(current_power);
                Flywheel2.setPower(current_power);
            }

            // This button will set "current_power" to 60% throttle.
            if (dpad_right2) {
                telemetry.addData("Flywheel Motors set to Middle Goal.", " ,");
                telemetry.update();
                current_power=(0.90);
                Flywheel1.setPower(current_power);
                Flywheel2.setPower(current_power);
            }

            // This button will set "current_power" to 30% throttle.
            if (dpad_down2) {
                telemetry.addData("Flywheel Motors set to Bottom Goal.", " ,");
                telemetry.update();
                current_power=(0.85);
                Flywheel1.setPower(current_power);
                Flywheel2.setPower(current_power);
            }

            // This button will increase .05% to "current_power".
            if (buttonX2 && FlywheelState) {
                FlywheelState = false;
                telemetry.addData("Current Flywheel Power Level Is: ", current_power);
                current_power = (current_power + 0.05);
                telemetry.addData("Calculated Flywheel Power", current_power);
                current_power = Range.clip(current_power, -1, 1);
                telemetry.addData("Clipped Flywheel Power", current_power);
                telemetry.addData("Flywheel Motor 2 Power", Flywheel2.getPower());
                telemetry.update();
                FlywheelState = true;
                sleep(200);
            }

            // This button will decrease .05% from "current_power".
            if (buttonY2 && FlywheelState) {
                FlywheelState = false;
                telemetry.addData("Current Flywheel Power Level Is: ", current_power);
                current_power = (current_power - 0.05);
                telemetry.addData("Calculated Flywheel Power", current_power);
                current_power = Range.clip(current_power, -1, 1);
                telemetry.addData("clipped Flywheel power", current_power);
                telemetry.update();
                FlywheelState = true;
                sleep(200);
            }



            //--------------------------------Wobble Goal Arm & Claw control--------------------------\\
            //This is adding 100 ticks from the motor, so that the motor can be brought closer to its grabbing position.
            if (buttonA1) {
                Rotation.setPower(1.00);
                destination=Rotation.getCurrentPosition();
                destination+=100; // plus and equal sign adds 100 and stores 100 as its new base value
                sleep (200);
                telemetry.addData("destination", destination);
                Rotation.setTargetPosition(destination); // the target is the new value that is equated
                telemetry.addData("destination", Rotation.getCurrentPosition());
                telemetry.update();
            }

            //This is subtracting 100 ticks from the motor, so that the motor can be brought closer to its rest position.
            if (buttonB1) {
                Rotation.setPower(1.00);
                destination=Rotation.getCurrentPosition();
                destination-=100; // minus and equal sign subtracts 100 and stores (value-100) as its new base value
                telemetry.addData("destination", destination);
                Rotation.setTargetPosition(destination); // the target is the new value that is equated
                sleep (500);
                telemetry.addData("backwards", Rotation.getCurrentPosition());
                telemetry.update();
            }
            //This portion is the servo letting go of the wobble goals.
            if (dpad_right1) {
                Claw.setPosition(1.00);
            }
            //This portion is the servo grabbing of the wobble goals.
            if (dpad_left1) {
                Claw.setPosition(0.00);
            }
            //This is resetting the wobble goal.
            if (dpad_up1) {
                Rotation.setPower(1.00);
                Current_Rotation_Position = Rotation.getCurrentPosition();
                telemetry.addData("Starting Position", Current_Rotation_Position);
                //Current_Rotation_Position = Current_Rotation_Position - 2500;
                Current_Rotation_Position = 2100;
                //Rotation.setTargetPosition(-400);
                if(Current_Rotation_Position > 2100) {Current_Rotation_Position = 2100; }
                if(Current_Rotation_Position < 0) {Current_Rotation_Position = 0; }
                Rotation.setTargetPosition(Current_Rotation_Position);
                telemetry.addData("New Position", Current_Rotation_Position);
                telemetry.update();
            }

            //This is lowering the wobble goal.
            if (dpad_down1){
                Rotation.setPower(1.00);
                Current_Rotation_Position = Rotation.getCurrentPosition();
                telemetry.addData("Starting Position", Current_Rotation_Position);
//                Current_Rotation_Position = Current_Rotation_Position + 2500;
                Current_Rotation_Position = 0;
                //Rotation.setTargetPosition(0);
                if(Current_Rotation_Position < 0) {Current_Rotation_Position = 0; }
                if(Current_Rotation_Position > 2100) {Current_Rotation_Position = 2100; }
                Rotation.setTargetPosition(Current_Rotation_Position);
                telemetry.addData("New Position", Current_Rotation_Position);
                telemetry.update();
            }


        }
    }
}