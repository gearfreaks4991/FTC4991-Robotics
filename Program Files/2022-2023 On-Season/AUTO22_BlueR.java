package org.firstinspires.ftc.teamcode;

// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="AUTO22_BlueR")
public class AUTO22_BlueR extends COMP22_Auto_Base_ProgramC {

    @Override
    public void runOpMode() throws InterruptedException {


        // Defining the Motors, Servos, and Color Sensor.
        DcMotor Lift;
        Servo Intake;
        ColorSensor Color;

        // Defining the Variables used in the program.
        int destination=0;
        double FL_power = 0.0;
        double FR_power = 0.0;
        double BL_power = 0.0;
        double BR_power = 0.0;


        init_motors();


        waitForStart();
        /*
            The goal of this program is to do five things; detect the sleeve correctly, place a cone on the terminal, the
            low junction near to the station, and the other low junction closest to the terminal, and finally park correctly
            in the designated space.
        The Drive Motors are 312 RPM with 537.7 Pulses per Rotation
        */

        //drive, strafe right, drive, turn 45 degrees left, turn 45 degrees left, turn 45 degrees right, turn 45 degrees left,
        //strafe left, drive, ???

        drive(0.50, 500);
        strafe(0.50, -875);
        drive(0.50, 800);
        turn(0.50, 275);
        turn(0.50, 275);
        turn(0.50, -275);
        turn(0.50, 275);
        strafe(0.50, 800);
        drive(0.50, 800);



        /*drive(0.20, 1000);
        sleep(500);
        drive(0.20, -1000);
        sleep(500);
        strafe(0.20, 1000);
        sleep(500);
        strafe(0.20, -1000);
        sleep(500);
        turn(0.20, 1000);
        sleep(500);
        turn(0.20, -1000);
        sleep(500);*/








    }


}