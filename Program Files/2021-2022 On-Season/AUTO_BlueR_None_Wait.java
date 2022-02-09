package org.firstinspires.ftc.teamcode;

// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="AUTO_BlueR_None_Wait")
public class AUTO_BlueR_None_Wait extends COMP21_Auto_Mecanum_Base {

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
            The goal of this program is to 1; drive up to one of the 3 squares and check for the Duck/Team
            Shipping Element. If it does not detect one of the game elements, it will strafe to the next square.
            It does this with the first two, if it doesn't detect the game elements there, it automatically assumes
            that it is located in the 3rd square, and will drop the cube off in the 3rd level.

            In this variant, it will do nothing after completing the cube task.

        The Drive Motors are 312 RPM with 537.7 Pulses per Rotation
        */

        drive(0.50, 650);

        sleep(15750);

        if(Color.red() > 100) {

            telemetry.addData("Object 3 detected, Red =", Color.red());
            telemetry.update();
            strafe(0.50,2000);
            drive(0.50,-75);
            Lift.setPower(0.50);
            Lift.setTargetPosition(-1700);
            sleep(2000);
            drive(0.50, 180);
            Intake.setPosition(1.00);
            sleep(2000);
            Intake.setPosition(0.50);
            drive(0.50, -180);
            Lift.setPower(0.50);
            Lift.setTargetPosition(0);


        }
        else {

            strafe(0.50,425);

            sleep(500);

            if(Color.red() > 100) {

                telemetry.addData("Object 2 detected, Red =", Color.red());
                telemetry.update();
                strafe(0.50, 1500);
                drive(0.50, -75);
                Lift.setPower(0.50);
                Lift.setTargetPosition(-1200);
                sleep(1250);
                drive(0.50, 75);
                Intake.setPosition(1.00);
                sleep(2000);
                Intake.setPosition(0.50);
                drive(0.50, -75);
                Lift.setPower(0.50);
                Lift.setTargetPosition(0);

            }
            else {
                telemetry.addData("Object 1 detected, Red =", Color.red());
                telemetry.update();
                strafe(0.50, 1500);
                drive(0.50, -75);
                Lift.setPower(0.50);
                Lift.setTargetPosition(-700);
                sleep(1000);
                drive(0.50, 75);
                Intake.setPosition(1.00);
                sleep(2000);
                Intake.setPosition(0.50);
                drive(0.50, -75);
                Lift.setPower(0.50);
                Lift.setTargetPosition(0);
            }
        }






    }


}