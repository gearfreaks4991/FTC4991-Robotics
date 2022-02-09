package org.firstinspires.ftc.teamcode;

// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="TEMPAUTO_RedL_Carousel")
public class TEMPAUTO_RedL_Carousel extends COMP21_Auto_Mecanum_Base {

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

        // Hardwaremapping the various devices.
        Intake = hardwareMap.servo.get("intake");
        Color = hardwareMap.colorSensor.get("color");
        Lift = hardwareMap.dcMotor.get("lift");
        Lift.setTargetPosition(0);
        Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();
        /*
            The designation of TEMPAUTO for this program is due to the fact that it is still in a testing phase
            in order to get the right tick values for it to be reliable for a competition or scrimmage setting,
            due to this it will be designated as "temporary" until we get the final measurements.

            The goal of this program is to drive up to one of the 3 squares and check for the Duck/Team
            Shipping Element. If it does not detect one of the game elements, it will strafe to the next square.
            It does this with the first two, if it doesn't detect the game elements there, it automatically assumes
            that it is located in the 3rd square, and will drop the cube off in the 3rd level.

            Have robot do carousel first, then move to location.


        The Drive Motors are 312 RPM with 537.7 Pulses per Rotation
        */

        drive(0.50, 650);

        sleep(750);

        if(Color.green() > 100) {

            telemetry.addData("Object 1 detected, Green =", Color.green());
            telemetry.update();
            strafe(0.50,-1500);
            drive(0.50,-75);
            Lift.setPower(0.50);
            Lift.setTargetPosition(750);
            sleep(1000);
            drive(0.50, 75);
            Intake.setPosition(0.00);
            sleep(2000);
            Intake.setPosition(0.50);
            drive(0.50, -50);


        }
        else {

            strafe(0.50,-420);

            if(Color.green() > 100) {

                telemetry.addData("Object 2 detected, Green =", Color.green());
                telemetry.update();
                strafe(0.60, -1000);
                drive(0.50, -75);
                Lift.setPower(0.50);
                Lift.setTargetPosition(1250);
                sleep(2000);
                drive(0.50, 75);
                Intake.setPosition(0.00);
                sleep(2000);
                Intake.setPosition(0.50);
                drive(0.50, -50);


            }
            else {
                telemetry.addData("Object 3 detected, Green =", Color.green());
                telemetry.update();
                strafe(0.60, -1000);
                drive(0.50, -75);
                Lift.setPower(0.50);
                Lift.setTargetPosition(1750);
                sleep(2000);
                drive(0.50, 175);
                Intake.setPosition(0.00);
                sleep(2000);
                Intake.setPosition(0.50);
                drive(0.50, -150);
            }
        }

        strafe(0.70,2000);

        turn(0.60,-600);

        drive(0.35,-600);

        turn(0.30, -100);

        Wheel.setPower(-0.35);

        sleep(4000);

        Wheel.setPower(0.00);

        turn(1.00,600);

        drive(1.00,380);


    }


}
