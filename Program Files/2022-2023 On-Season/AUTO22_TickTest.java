package org.firstinspires.ftc.teamcode;

// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.COMP22_Auto_Base;

@Autonomous(name="AUTO22_TickTest")
public class AUTO22_TickTest extends COMP22_Auto_Base {

    @Override
    public void runOpMode() throws InterruptedException {


        // Defining the Motors, Servos, and Color Sensor.


        // Defining the Variables used in the program.
        int destination = 0;
        double FL_power = 0.0;
        double FR_power = 0.0;
        double BL_power = 0.0;
        double BR_power = 0.0;


        init_motors();

        // Hardwaremapping the various devices.

        waitForStart();
        /*
            The goal of this program is to do five things; detect the sleeve correctly, place a cone on the terminal, the
            low junction near to the station, and the other low junction closest to the terminal, and finally park correctly
            in the designated space.
        The Drive Motors are 312 RPM with 537.7 Pulses per Rotation
        */
        telemetry.addData("FL pos", FLMotor.getCurrentPosition());
        telemetry.addData("FR pos", FRMotor.getCurrentPosition());
        telemetry.addData("BL pos", BLMotor.getCurrentPosition());
        telemetry.addData("BR pos", BRMotor.getCurrentPosition());
        telemetry.update();

    }

}