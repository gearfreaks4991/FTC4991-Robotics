package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="WyattsWalkingRobot_TeleOp")
public class WyattsWalkingRobot_TeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {



        boolean A;
        boolean B;
        boolean X;
        boolean Y;
        boolean DpadUp;


        Servo FL_Upper;
        Servo FL_Lower;
        Servo FR_Upper;
        Servo FR_Lower;
        Servo BL_Upper;
        Servo BL_Lower;
        Servo BR_Upper;
        Servo BR_Lower;

        FL_Upper = hardwareMap.servo.get("ufl");
        FL_Lower = hardwareMap.servo.get("lfl");
        FR_Upper = hardwareMap.servo.get("ufr");
        FR_Lower = hardwareMap.servo.get("lfr");
        BL_Upper = hardwareMap.servo.get("ubl");
        BL_Lower = hardwareMap.servo.get("lbl");
        BR_Upper = hardwareMap.servo.get("ubr");
        BR_Lower = hardwareMap.servo.get("lbr");

        waitForStart();

        while (opModeIsActive()) {


            A = gamepad1.a;
            B = gamepad1.b;
            X = gamepad1.x;
            Y = gamepad1.y;
            DpadUp = gamepad1.dpad_up;


            if (A) {

                BR_Upper.setPosition(0.75);
                sleep(500);
                BR_Upper.setPosition(0.475);
                BR_Lower.setPosition(0.2);
                sleep(500);
                BR_Lower.setPosition(1.0);
                sleep(500);
                BR_Upper.setPosition(0.5);

            }

            if (B) {

                FL_Upper.setPosition(0.2);
                sleep(1000);
                FL_Upper.setPosition(0.7);
                sleep(1000);
                FL_Lower.setPosition(0.5);
                sleep(1000);
                FL_Upper.setPosition(0.2);
                sleep(1000);
                FL_Lower.setPosition(1.0);
                sleep(1000);
                FL_Upper.setPosition(0.5);

            }

            if (Y) {

                BL_Upper.setPosition(0.35);
                sleep(500);
                BL_Upper.setPosition(0.575);
                BL_Lower.setPosition(0.2);
                sleep(500);
                BL_Lower.setPosition(1.0);
                sleep(500);
                BL_Upper.setPosition(0.5);

            }

            if (X) {

                FR_Upper.setPosition(0.7);
                sleep(500);
                FR_Upper.setPosition(0.25555775466445);
                FR_Lower.setPosition(0.2);
                sleep(500);
                FR_Lower.setPosition(1.0);
                sleep(500);
                FR_Upper.setPosition(0.5);

            }

            if (DpadUp) {

                FL_Upper.setPosition(0.5);
                FL_Lower.setPosition(1.0);
                sleep(250);
                FR_Upper.setPosition(0.5);
                FR_Lower.setPosition(1.0);
                sleep(250);
                BL_Upper.setPosition(0.5);
                BL_Lower.setPosition(1.0);
                sleep(250);
                BR_Upper.setPosition(0.5);
                BR_Lower.setPosition(1.0);
                sleep(250);
                telemetry.addData("Leg Reset Complete.", BL_Upper.getPosition());
                telemetry.update();

            }



        }

    }
}