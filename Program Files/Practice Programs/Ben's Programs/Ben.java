package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.concurrent.RecursiveTask;

@TeleOp(name="Ben")
public class Ben extends LinearOpMode {

    DcMotor left_drive;
    DcMotor right_drive;
    ColorSensor color;


    float rightjoystick;
    float leftjoystick;





    @Override
    public void runOpMode() throws InterruptedException {

        left_drive = hardwareMap.dcMotor.get("left_drive");
        left_drive.setDirection(DcMotorSimple.Direction.REVERSE);

        right_drive = hardwareMap.dcMotor.get("right_drive");
        color = hardwareMap.colorSensor.get("color");

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("red", color.red());
            telemetry.addData("blue", color.blue());
            telemetry.addData("green", color.green());
            telemetry.addData("alpha", color.alpha());
            telemetry.update();


            if (color.red() > 100) {

                left_drive.setPower(1.00);
                right_drive.setPower(1.00);
                sleep(3000);
                left_drive.setPower(0.00);
                right_drive.setPower(0.00);


            }


        }
    }

}