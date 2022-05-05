package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import java.time.Year;


@TeleOp(name="Wyatt_FL_ServoTest")
public class Wyatt_FL_ServoTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        boolean A;
        boolean B;
        boolean X;
        boolean Y;
        double destination = 0;
        Servo FL_Upper;
        Servo FL_Lower;
        FL_Upper = hardwareMap.servo.get("ufl");
        FL_Lower = hardwareMap.servo.get("lfl");

        waitForStart();

        while (opModeIsActive()) {

            A = gamepad1.a;
            B = gamepad1.b;
            X = gamepad1.x;
            Y = gamepad1.y;

            if (A) {

                FL_Lower.setPosition(1.00);

            }

            if (B) {

                FL_Lower.setPosition(0.75);

            }

            if (X) {

                FL_Lower.setPosition(0.5);

            }

            if (Y) {

                FL_Lower.setPosition(0.25);

            }

        }

    }
}
