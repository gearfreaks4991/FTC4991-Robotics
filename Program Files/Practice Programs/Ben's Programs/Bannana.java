package org.firstinspires.ftc.teamcode;

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

@TeleOp(name="Bannana")
public class Bannana extends LinearOpMode {

    DcMotor left_drive;
    DcMotor right_drive;

    float rightjoystick;
    float leftjoystick;





    @Override
    public void runOpMode() throws InterruptedException {

        left_drive=hardwareMap.dcMotor.get("left_drive");
        left_drive.setDirection(DcMotorSimple.Direction.REVERSE);

        right_drive=hardwareMap.dcMotor.get("right_drive");
        waitForStart();

        while(opModeIsActive()){

            rightjoystick = gamepad1.right_stick_y;
            leftjoystick = gamepad1.left_stick_y;


            float leftPowerValue = gamepad1.left_stick_y;
            float rightPowerValue = gamepad1.right_stick_y;

            leftPowerValue = Range.clip(leftPowerValue, -1, 1);
            rightPowerValue = Range.clip(rightPowerValue, -1, 1);

            left_drive.setPower(leftPowerValue);
            right_drive.setPower(rightPowerValue);









        }


    }















}