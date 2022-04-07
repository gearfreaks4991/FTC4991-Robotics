package org.firstinspires.ftc.teamcode;

// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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

@TeleOp(name="water")
public class water extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {


        // Defining the Motors, Servos, and Color Sensor.
        DcMotor  left_drive;
        DcMotor right_drive;
        float JoystickR;
                float JoystickL;

        // Defining the Variables used in the program.




        // Hardwaremapping the various devices.
        left_drive = hardwareMap.dcMotor.get("left");
        right_drive = hardwareMap.dcMotor.get("right");

        waitForStart();
        while (opModeIsActive()){
    JoystickR = gamepad1.right_stick_y;
    JoystickL = gamepad1.left_stick_x;

    right_drive.setPower(0.00);
    left_drive.setPower(0.00);

    if(JoystickR >= 0.10){
        right_drive.setPower((JoystickR));

        if(JoystickR <= -0.10){
            right_drive.setPower(JoystickR);

            if(JoystickL >= 0.10){
                left_drive.setPower(((JoystickL)));

                if(JoystickL <= -0.10){
                    left_drive.setPower(JoystickL);

                }
                }
            }
        }
    }
        right_drive.setPower(0.00);
        left_drive.setPower(0.00);

        }
    }