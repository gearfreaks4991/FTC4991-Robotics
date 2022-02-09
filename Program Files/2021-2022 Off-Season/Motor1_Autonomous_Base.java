package org.firstinspires.ftc.teamcode;

// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public abstract class Motor1_Autonomous_Base extends LinearOpMode {

    DcMotor FLMotor;
    DcMotor FRMotor;
    Servo HalfServo;
    Servo ContinuousServo;
    ColorSensor Color1;
    TouchSensor Touch1;
    int target=0;
    int target2=0;

    public void init_motors () {
        FLMotor = hardwareMap.dcMotor.get("motor1");
        FRMotor = hardwareMap.dcMotor.get("motor2");
        HalfServo = hardwareMap.servo.get("servo1");
        ContinuousServo = hardwareMap.servo.get("servo2");
        Color1 = hardwareMap.colorSensor.get("color1");
        Touch1 = hardwareMap.touchSensor.get("touch1");

        FLMotor.setTargetPosition(0);
        FLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FRMotor.setTargetPosition(0);
        FRMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }


    public void mcdrive (double speed, int ticks) {
        telemetry.addData("START POSITION: ", FLMotor.getCurrentPosition());
        telemetry.update();
        speed = Range.clip(speed, -1, 1);
        FLMotor.setPower(speed);
        target = FLMotor.getCurrentPosition();
        target+=ticks;
        FLMotor.setTargetPosition(target);
        while(FLMotor.isBusy()) {
           telemetry.addData("POSITION: ", FLMotor.getCurrentPosition());
           telemetry.update();
        }
        FLMotor.setPower(0.00);
        telemetry.addData("FINAL POSITION: ", FLMotor.getCurrentPosition());
        telemetry.update();
    }


    public void mcdrivecheese (double speed, int ticks) {
        telemetry.addData("START POSITION: ", FRMotor.getCurrentPosition());
        telemetry.update();
        speed = Range.clip(speed, -1, 1);
        FRMotor.setPower(speed);
        target2 = FRMotor.getCurrentPosition();
        target2+=ticks;
        FRMotor.setTargetPosition(target2);
        while(FRMotor.getCurrentPosition() < target2){
            telemetry.addData("Current Position", FRMotor.getCurrentPosition());
            telemetry.update();
        }
        while(FRMotor.isBusy()) {
            telemetry.addData("POSITION: ", FRMotor.getCurrentPosition());
            telemetry.update();
        }
        FRMotor.setPower(0.00);
        telemetry.addData("FINAL POSITION: ", FRMotor.getCurrentPosition());
        telemetry.update();
    }


    public void mcservo (double position)  {
        HalfServo.setPosition(position);
    }


    public void doublemcservo (double position) {
        ContinuousServo.setPosition(position);
    }


    public void mccolor () {
   /* if statment " if one of the three color's value is grater than
   130 than go through
    */

        //if (Color1.red())|| Color1.blue())|| Color1.green());
        telemetry.addData("Red", Color1.red());
        telemetry.addData("Blue", Color1.blue());
        telemetry.addData("Green", Color1.green());
        telemetry.update();

    }




}