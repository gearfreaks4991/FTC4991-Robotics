package org.firstinspires.ftc.teamcode;

// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="TEST_RedL_None_Buttons")
public class TEST_RedL_None_Buttons extends COMP21_Auto_Mecanum_Base {

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
        boolean B;
        boolean A;
        boolean Y;


        init_motors();

        // Hardwaremapping the various devices.
        Intake = hardwareMap.servo.get("intake");
        Color = hardwareMap.colorSensor.get("color");
        Lift = hardwareMap.dcMotor.get("lift");
        Lift.setTargetPosition(0);
        Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        waitForStart();

        while (opModeIsActive()) {

            A = gamepad1.a;
            B = gamepad1.b;
            Y = gamepad1.x;


            if (A) {
                drive(0.50, 650);
                telemetry.addData("Object 1 detected, Red =", Color.red());
                telemetry.update();
                strafe(0.50, -1500);
                drive(0.50, -75);
                Lift.setPower(0.50);
                Lift.setTargetPosition(750);
                sleep(1000);
                drive(0.50, 75);
                Intake.setPosition(0.00);
                sleep(2000);
                Intake.setPosition(0.50);
                drive(0.50, -50);
                Lift.setPower(0.50);
                Lift.setTargetPosition(0);

            }



                if (B) {
                    drive(0.50, 650);
                    strafe(0.50, -425);
                    sleep(500);
                    telemetry.addData("Object 2 detected, Red =", Color.red());
                    telemetry.update();
                    strafe(0.50, -1000);
                    drive(0.50, -75);
                    Lift.setPower(0.50);
                    Lift.setTargetPosition(1250);
                    sleep(1250);
                    drive(0.50, 75);
                    Intake.setPosition(0.00);
                    sleep(2000);
                    Intake.setPosition(0.50);
                    drive(0.50, -50);
                    Lift.setPower(0.50);
                    Lift.setTargetPosition(0);

                }

                if (Y){
                    drive(0.50, 650);
                    strafe(0.50, -425);
                    sleep(500);
                    telemetry.addData("Object 3 detected, Red =", Color.red());
                    telemetry.update();
                    strafe(0.50, -1000);
                    drive(0.50, -75);
                    Lift.setPower(0.50);
                    Lift.setTargetPosition(1750);
                    sleep(2000);
                    drive(0.50, 175);
                    Intake.setPosition(0.00);
                    sleep(2000);
                    Intake.setPosition(0.50);
                    drive(0.50, -150);
                    Lift.setPower(0.50);
                    Lift.setTargetPosition(0);
                }
            }


        }


    }


