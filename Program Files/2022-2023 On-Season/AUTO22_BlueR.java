package org.firstinspires.ftc.teamcode;

// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="AUTO22_BlueR")
public class AUTO22_BlueR extends COMP22_Auto_Base {

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

        // Hardwaremapping the various devices.

        waitForStart();
        /*
            The goal of this program is to do three things; detect the sleeve correctly, place 3 cones on the two low junctions and
            the terminal, and finally park correctly. (for an alternate version of this, we'll place a cone in the floor junction instead
            of the terminal)

        The Drive Motors are 312 RPM with 537.7 Pulses per Rotation
        */



        // drive, (detect), turn right 90 degrees, drive,(drop cone), turn left 90 degrees, drive, turn 45 degrees left, (pick up cone),
        // turn 45 degrees left, (place cone), turn 45 degrees right, (pick up cone), turn 45 degrees left, strafe left, drive, (place cone), ???
        //1 Foot = ~500 ticks, so using math 1 inch is roughly 42 ticks (with starting point being back wheels)

        Claw.setPosition(1.00);
        sleep(1500);
        lift(0.50, 270);
        sleep(500);
        lift(0.50, 0);
        sleep(500);
        Claw.setPosition(0.00);
        sleep(500);
        lift(0.50, 500);
        sleep(1000);


        drive(0.50, 650);
        sleep(1000);
        turn(0.50, -950); //950 is 90 degrees?
        sleep(1000);
        drive(0.50, 1100);
        sleep(1000);
        turn(0.50, -475); //45 degrees?
        sleep(1000);
        drive(0.50, 100);
        Claw.setPosition(1.00);
        sleep(500);

        drive(0.50, -500);
        sleep(1000);
        turn(0.50, -1425);
        sleep(1000);
        drive(0.50, 500);








       /* turn(0.50, 1500);
        sleep(1500);
        drive(0.50, 800);
        sleep(1500);
        turn(0.50, 275);
        sleep(1500);
        pickupsmall();
        sleep(1500);
        turn(0.50, 275);
        sleep(1500);
        Claw.setPosition(1.00);
        sleep(500);

        turn(0.50, -275);
        sleep(1500);
        pickupsmall();
        sleep(1500);
        turn(0.50, 275);
        sleep(1500);
        strafe(0.50, 800);
        sleep(1500);
        drive(0.50, 800);
        sleep(1500);
        Claw.setPosition(1.00);
        sleep(500); */



    }


}