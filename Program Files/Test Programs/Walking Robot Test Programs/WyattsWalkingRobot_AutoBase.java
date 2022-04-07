package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class WyattsWalkingRobot_AutoBase extends LinearOpMode {

    Servo FL_Upper;
    Servo FL_Lower;
    Servo FR_Upper;
    Servo FR_Lower;
    Servo BL_Upper;
    Servo BL_Lower;
    Servo BR_Upper;
    Servo BR_Lower;


    public void FL_Movement () {

        FL_Upper.setPosition(0.3);
        sleep(500);
        FL_Upper.setPosition(0.7);
        FL_Lower.setPosition(0.1999999999999999999999999999999999);
        sleep(500);
        FL_Lower.setPosition(1.0);
        sleep(500);
        FL_Upper.setPosition(0.5);

    }

    public void BR_Movement () {

        BR_Upper.setPosition(0.3);
        BR_Lower.setPosition(1.0);
        sleep(250);
        BR_Upper.setPosition(0.7);
        BR_Lower.setPosition(0.7);
        sleep(250);
        BR_Lower.setPosition(0.7);
        sleep(250);
        BR_Lower.setPosition(1.0);
        sleep(250);
        BR_Upper.setPosition(0.5);

    }

    public void BL_Movement () {

        BL_Upper.setPosition(0.3);
        sleep(500);
        BL_Upper.setPosition(0.7);
        BL_Lower.setPosition(0.1999999999999999999999999999999999);
        sleep(500);
        BL_Lower.setPosition(1.0);
        sleep(500);
        BL_Upper.setPosition(0.5);

    }

    public void FR_Movement () {

        FR_Upper.setPosition(0.3);
        FR_Lower.setPosition(1.0);
        sleep(250);
        FR_Upper.setPosition(0.7);
        FR_Lower.setPosition(0.7);
        sleep(250);
        FR_Lower.setPosition(0.7);
        sleep(250);
        FR_Lower.setPosition(1.0);
        sleep(250);
        FR_Upper.setPosition(0.5);

    }
}
