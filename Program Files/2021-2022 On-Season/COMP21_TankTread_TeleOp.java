package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.Range;

import org.checkerframework.checker.units.qual.A;

@TeleOp(name="COMP21_TankTread_TeleOp")
public class COMP21_TankTread_TeleOp extends LinearOpMode {
    // Defining the Motors, Servos, and other Devices.
    DcMotor LMotor;
    DcMotor RMotor;
    DcMotor Lift;
    DcMotor Carousel;
    DcMotor Intake;
    Servo Bucket;

    // Defining the Variables
    boolean ButtonA;
    boolean ButtonB;
    boolean ButtonY;
    boolean ButtonX;
    boolean DpadLeft;
    boolean DpadRight;
    boolean LB;
    boolean RB;
    float RT;
    float LT;
    int destination = 0;
    float LS;
    float RS;
    double BucketTest;

    @Override
    public void runOpMode() throws InterruptedException {

        // -- Hardwaremapping Section -- \\
        /* Hardwaremapping tells the program what the motors, servos, and other technical parts should be called
        in the Configuration. We also use this section the motors' directions, so that the robot drives the correct
        way, and set the mode of them, as well. */

        LMotor = hardwareMap.dcMotor.get("lmotor");
        RMotor = hardwareMap.dcMotor.get("rmotor");
        Carousel = hardwareMap.dcMotor.get("carousel");
        Intake = hardwareMap.dcMotor.get("intake");
        Lift = hardwareMap.dcMotor.get("lift");
        Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lift.setTargetPosition(0);
        Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Bucket = hardwareMap.servo.get("bucket");



       waitForStart();


       while (opModeIsActive()) {
            /*
                This is the COMP21_TankTread_TeleOp program, the COMP21 stands for Competition 2021, which
                shows that this program was made for and primarily used in the 2021 Competition Season, the second
                part of it, the TankTread part, signifies that it was coded for the Tank Tread robot specifically.
                The Tank Tread robot we made is used for Scrimmages and smaller events, meanwhile we use our
                Mecanum Wheel Robot for the main competitions and qualifiers.

                Finally, the TeleOp means that a person, or persons, control the robot via 1 or more gamepad
                controllers.

             */
           // Assigning the Variables to the buttons on the Gamepads.
           LS = gamepad1.left_stick_y;
           RS = gamepad1.right_stick_y;
           ButtonA = gamepad1.a;
           ButtonB = gamepad1.b;
           ButtonY = gamepad1.y;
           ButtonX = gamepad1.x;
           LB = gamepad1.left_bumper;
           RB = gamepad1.right_bumper;
           LT = gamepad1.left_trigger;
           RT = gamepad1.right_trigger;
           DpadLeft = gamepad1.dpad_left;
           DpadRight = gamepad1.dpad_right;


           //Joysticks control Moving the tank treads.
           LMotor.setPower(LS);
           RMotor.setPower(-RS);


           //Bucket
           if (ButtonA) {
               BucketTest = Bucket.getPosition();
               BucketTest += 0.05;
               Bucket.setPosition(BucketTest);
               telemetry.addData("Servo Pos:", Bucket.getPosition());
               telemetry.update();
           }


           if (ButtonB) {
               BucketTest = Bucket.getPosition();
               BucketTest -= 0.05;
               Bucket.setPosition(BucketTest);
               telemetry.addData("Servo Pos:", Bucket.getPosition());
               telemetry.update();
           }


           //Lift
           if (RB) {

               destination = Lift.getCurrentPosition(); // Finds and Stores the current position of the Lift.
               destination += 100; // This adds the number of Ticks to the stored current position of the lift.
               Lift.setPower(1.00); // Sets the power of the Lift to 100% in Forward
               Lift.setTargetPosition(destination); // The Position of the Lift is Updated to the newer Position.
               sleep (200); // Sleep to ensure that it has time to complete the action.
               Lift.setPower(0.00);
           }

           if(LB) {
               destination = Lift.getCurrentPosition();
               destination -= 100; // plus and equal sign adds 100 and stores 100 as its new base value
               Lift.setPower(-1.00);
               Lift.setTargetPosition(destination); // the target is the new value that is equated
               sleep(200);
               Lift.setPower(0.00);
           }


           //Carousel
           if (RT > 0.10) {
               Carousel.setPower(1.00);
           }
           else {
               Carousel.setPower(0.00);
           }
           if (LT > 0.10) {
               Carousel.setPower(-1.00);
           }
           else {
               Carousel.setPower(0.00);
           }


           //Intake
           if (ButtonY) {
               Intake.setPower(-1.00);
           }


           if (ButtonX) {
               Intake.setPower(1.00);
           }

           if (DpadLeft) {
               Intake.setPower(0.00);
           }

    }
}
}

