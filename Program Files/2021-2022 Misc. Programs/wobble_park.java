package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="wobble_park")
public class wobble_park extends LinearOpMode {
    // Motors are Gobilda 5202 and rotate at around 435 Revolutions/Rotations Per Minute (RPM)
    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;

    // Setting the power of the drivetrain motor variables.

    double FL_power = 0.0;
    double FR_power = 0.0;
    double BL_power = 0.0;
    double BR_power = 0.0;
    // power determined by the x and y values of the sticks.
    double Yvalue1=0.0;
    double Xvalue1=0.0;
    double Xvalue2 = 0.0;

    // Intake motor
    DcMotor Intake; //This is using an AndyMark Neverest 40 Motor

    // Flipper motor
    // lodingservo is a smart servo used for our flipper on the robot
    Servo loadingservo;

    // Fly wheel motors

    DcMotor Flywheel1;
    DcMotor Flywheel2;
    //both Flywheel1 and Flywheel2 are neverrest 40's


    // additional variables
    double current_power;
    boolean GOOD = true;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        // Hardware mapping the Drivetrain's 4 main drive motors,
        // they should be Gobilda 5202 which spin at 435 RPM.
        FL = hardwareMap.dcMotor.get("lf");
        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FL.setTargetPosition(0);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        FR = hardwareMap.dcMotor.get("rf");
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setTargetPosition(0);
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        BL = hardwareMap.dcMotor.get("lb");
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setTargetPosition(0);
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        BR = hardwareMap.dcMotor.get("rb");
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setTargetPosition(0);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // - intake
        Intake = hardwareMap.dcMotor.get("intake");
        // - flipper
        loadingservo = hardwareMap.servo.get("flipper");
        // - flywheel
        Flywheel1 = hardwareMap.dcMotor.get("flyl");
        Flywheel2 = hardwareMap.dcMotor.get("flyr");
        Flywheel2.setDirection(DcMotor.Direction.REVERSE);


//      Reversing 2 of the motors to allow the wheels to all move in the correct direction.
        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);

        // Telemetry that ensures the robot is ready to run the program.
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();



        // while (opModeIsActive()) {



        /* The portion of code below should do the following, in order to Drive from the starting
        position 102 Inches forward, to push the Wobble Goal into the dropoff zone.
        */

         mcdrive(105);
         mcdrive(-36);





    }

    // This Program uses: Gobilda 5202 435 RPM motors.
    // Which has 383.6 ticks per/Revolution. Our wheel are 4" in diameter.
    // Circumference is 12.5663706144".
    // Approx. 30.5251030464" Ticks Per Inch.
    //because we are using a 1:2 ratio on our wheels we have to double the previous number
    //which comes out to 61.050206128
    public void mcdrive(double inches) {

            float distance = (((float) inches) * (float) 61.050206128);
            int distance_int = Math.round(distance);

            int target_position_FL;
            int target_position_FR;
            int target_position_BL;
            int target_position_BR;

            target_position_FL = (FL.getCurrentPosition() + distance_int);
            target_position_FR = (FR.getCurrentPosition() + distance_int);
            target_position_BL = (BL.getCurrentPosition() + distance_int);
            target_position_BR = (BR.getCurrentPosition() + distance_int);

            FL.setPower(1.00);
            FR.setPower(1.00);
            BL.setPower(1.00);
            BR.setPower(1.00);

            FL.setTargetPosition(target_position_FL);
            FR.setTargetPosition(target_position_FR);
            BL.setTargetPosition(target_position_BL);
            BR.setTargetPosition(target_position_BR);

       /* FL.setPower(0.00);
        FR.setPower(0.00);
        BL.setPower(0.00);
        BR.setPower(0.00);
*/
            while(FL.isBusy()|| FR.isBusy()||BL.isBusy()||BR.isBusy());


    }

}