package org.firstinspires.ftc.teamcode;

// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="TensorFlow_TestLeft")
public class TensorFlow_TestLeft extends COMP22_Auto_Base {

    @Override
    public void runOpMode() throws InterruptedException {


        // Defining the Motors, Servos, and Color Sensor.


        // Defining the Variables used in the program.
        int destination=0;
        double FL_power = 0.0;
        double FR_power = 0.0;
        double BL_power = 0.0;
        double BR_power = 0.0;


        init_motors();



        waitForStart();


       /*left*/
        drive(0.50, 1250);
        sleep(500);
        strafe(0.50, 1250);
        sleep(500);
        drive(0.50, 1000);



    }


}