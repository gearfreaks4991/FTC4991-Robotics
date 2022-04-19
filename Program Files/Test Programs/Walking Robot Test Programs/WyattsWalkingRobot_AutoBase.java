package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class WyattsWalkingRobot_AutoBase extends LinearOpMode {

    // Defining the Servo Names
    Servo FL_Upper;
    Servo FL_Lower;
    Servo FR_Upper;
    Servo FR_Lower;
    Servo BL_Upper;
    Servo BL_Lower;
    Servo BR_Upper;
    Servo BR_Lower;

    /*
        Because this is an Autonomous Base program, there is no need to include things such as waitforstart,
        calling @TeleOp or @Autonomous, and others. This is because this program is used for calling
        functions in other programs. There are 4 basic functions included in this program, which will be
        elaborated on further below in this program.
     */


    /*
        FL_Movement takes the values that we found while testing/troubleshooting the normal TeleOp program.
        We took the code from the TeleOp were you would push a button and it would move on particular leg.
        By doing this we made it so instead of having to push each individual button, you would only need to
        call FL_Movement in a separate Autonomous Program for the entire Front Left Leg movement pattern to
        play out.
     */
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

    /*
        BR_Movement is the same as FL_Movement, as in we took the values and code included in the
        WyattsWalkingRobot_TeleOp and put it into a function here.
     */
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

    /*
        BL_Movement is the same as FL_Movement. Again, we took the latest values and latest updated
        code included in the WyattsWalkingRobot_TeleOp and put it into a function here.
     */
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

    /*
        Finally, FR_Movement is the same as FL_Movement. We took the latest values and latest updated
        code included in the WyattsWalkingRobot_TeleOp and apply it into a function included here.
     */
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
