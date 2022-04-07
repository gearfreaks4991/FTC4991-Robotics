package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;

@Autonomous(name="WyattsWalkingRobot_Auto")
public class WyattsWalkingRobot_Auto extends WyattsWalkingRobot_AutoBase {


    @Override
    public void runOpMode() throws InterruptedException {

        /*
            This program is more of a test program, where we were experimenting with the ability to
            make Wyatt's Robot walk automatically in a loop. To do this, we made a Autonomous Base
            program that we coded to have 4 functions that correspond to each movement of a leg.
            (To see more info on this, look in the WyattsWalkingRobot_AutoBase Program!) We focused
            more on the TeleOp programs before moving onto Autonomous to get the values of the
            movements fine-tuned.
         */


        // Declaring the Servos used in the program. \\
        Servo FL_Upper;
        Servo FL_Lower;
        Servo FR_Upper;
        Servo FR_Lower;
        Servo BL_Upper;
        Servo BL_Lower;
        Servo BR_Upper;
        Servo BR_Lower;

        // Hardware-mapping the Servos. \\
        FL_Upper = hardwareMap.servo.get("ufl");
        FL_Lower = hardwareMap.servo.get("lfl");
        FR_Upper = hardwareMap.servo.get("ufr");
        FR_Lower = hardwareMap.servo.get("lfr");
        BL_Upper = hardwareMap.servo.get("ubl");
        BL_Lower = hardwareMap.servo.get("lbl");
        BR_Upper = hardwareMap.servo.get("ubr");
        BR_Lower = hardwareMap.servo.get("lbr");

        waitForStart();

        /*
            Below here is the entire autonomous program, the process that it will go through is 1:
            Move the Front Left (FL) Leg. After waiting 2 seconds, it will 2: Move the
            Back Left (BL) Leg, before going into another 2 second long wait. On step 3 it moves the
            Front Right (FR) Leg and then goes into another 2 second wait. Then, for step 4: it
            moves the Back Right (BR) Leg, before finally going into another 2 second wait to allow
            for the program to loop better.
         */
        FL_Movement();
        sleep(2000);
        BL_Movement();
        sleep(2000);
        FR_Movement();
        sleep(2000);
        BR_Movement();
        sleep(2000);
    }
}