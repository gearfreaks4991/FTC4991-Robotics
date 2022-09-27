package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="ControlHub_TestProgram")
public class ControlHub_TestProgram extends LinearOpMode {

    // -- Defining Motors -- \\
    DcMotor Motor1;
    DcMotor Motor2;
    DcMotor Motor3;
    DcMotor Motor4;
    Servo Servo360;
    Servo Servo180;
    ColorSensor ColorSensor;


    boolean A;
    boolean B;
    boolean X;
    boolean Y;

    boolean DpadUp;
    boolean DpadDown;
    boolean DpadLeft;
    boolean DpadRight;
    int destination = 0;



    @Override
    public void runOpMode() throws InterruptedException {

        Motor1 = hardwareMap.dcMotor.get("motor1");
        Motor1.setDirection(DcMotorSimple.Direction.REVERSE);
        Motor2 = hardwareMap.dcMotor.get("motor2");
        Motor3 = hardwareMap.dcMotor.get("motor3");
        Motor4 = hardwareMap.dcMotor.get("motor4");
        Motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Motor4.setTargetPosition(0);
        Motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Servo360 = hardwareMap.servo.get("servo360");
        Servo180 = hardwareMap.servo.get("servo180");
        ColorSensor = hardwareMap.colorSensor.get("color");

        waitForStart();

        while (opModeIsActive()) {

            A = gamepad1.a;
            B = gamepad1.b;
            X = gamepad1.x;
            Y = gamepad1.y;
            DpadUp = gamepad1.dpad_up;
            DpadDown = gamepad1.dpad_down;
            DpadLeft = gamepad1.dpad_left;
            DpadRight = gamepad1.dpad_right;


            telemetry.addData("Red =", ColorSensor.red());
            telemetry.addData("Green =", ColorSensor.green());
            telemetry.addData("Blue =", ColorSensor.blue());
            telemetry.update();


            // Motor1 Code
            if (DpadUp) {

                Motor1.setPower(0.85);
                telemetry.addData("Motor1 Running, Power Percentage:", Motor1.getPower());

            }
            else {
                Motor1.setPower(0.00);
            }

            // Motor2 Code
            if (DpadRight) {

                Motor2.setPower(0.85);
                telemetry.addData("Motor2 Running, Power Percentage:", Motor2.getPower());
            }
            else {
                Motor2.setPower(0.00);
            }

            // Motor3 Code
            if (DpadDown) {

                Motor3.setPower(0.85);
                telemetry.addData("Motor3 Running, Power Percentage:", Motor3.getPower());

            }
            else {
                Motor3.setPower(0.00);
            }

            // Motor4 Code
            if (A) {
                Motor4.setPower(-1.00);
                destination=Motor4.getCurrentPosition();
                destination-=100;
                sleep (200);
                Motor4.setTargetPosition(destination);
                telemetry.addData("Lift Pos:", Motor4.getCurrentPosition());
                telemetry.update();
            }

            if (B) {
                Motor4.setPower(1.00);
                destination=Motor4.getCurrentPosition();
                destination+=100;
                sleep (200);
                Motor4.setTargetPosition(destination);
                telemetry.addData("Lift Pos:", Motor4.getCurrentPosition());
                telemetry.update();
            }

            // 360 Degree Servo Code
            if (X) {
                Servo360.setPosition(1.00);
            }
            else {
                Servo360.setPosition(0.50);
            }


        }

    }

}

