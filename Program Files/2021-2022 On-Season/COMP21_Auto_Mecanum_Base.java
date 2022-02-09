package org.firstinspires.ftc.teamcode;

// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class COMP21_Auto_Mecanum_Base extends LinearOpMode {
    // -- Program Information -- \\
    /*
     This is the Autonomous Program base we use for making programs for our Mecanum Robot. The program name is
     COMP21_Auto_Mecanum_Base, in which the 'COMP21' signifies that is was made and primarily used in the
     2021 Competition Season. The 'Auto' is short for 'Autonomous', which means that this program is used as a
     base for making any Autonomous programs, specifically, the Mecanum Wheel Robot, which is what the 'Mecanum'
     portion of the program name is used for. Finally, the 'Base' part of the program name specifically shows that
     this is the Base Program which the Autonomous Programs for the 2021 Competition Season utilize.
     */

    //--Defining Motors--\\
    DcMotor FLMotor;
    DcMotor FRMotor;
    DcMotor BLMotor;
    DcMotor BRMotor;
    DcMotor Lift;
    DcMotor Wheel;
    Servo Intake;
    ColorSensor Color;

    //--Defining Variables--\\
    int destination = 0;

    //A public void is a command that can be rewritten to do all that is inside the public void with a simple
    //command, instead of having to type out the same thing over and over. This public void is used to
    //initialize the motors, set their mode, and set their direction.
    public void init_motors () {
        FLMotor = hardwareMap.dcMotor.get("fl");
        FLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLMotor.setTargetPosition(0);
        FLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FLMotor.setDirection(DcMotor.Direction.REVERSE);

        FRMotor = hardwareMap.dcMotor.get("fr");
        FRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FRMotor.setTargetPosition(0);
        FRMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        BLMotor = hardwareMap.dcMotor.get("bl");
        BLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLMotor.setTargetPosition(0);
        BLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BLMotor.setDirection(DcMotor.Direction.REVERSE);

        BRMotor = hardwareMap.dcMotor.get("br");
        BRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRMotor.setTargetPosition(0);
        BRMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        Lift = hardwareMap.dcMotor.get("lift");
        Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lift.setTargetPosition(0);
        Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Wheel = hardwareMap.dcMotor.get("wheel");
        Intake = hardwareMap.servo.get("intake");

    }

    //This public void is used to drive the robot forward or backwards. all that's needed is the speed
    //and length it moves. setting the "ticks" to negative will make the robot move backwards.
    public void drive (double speed, int ticks) {
        speed = Range.clip(speed, -1, 1);

        destination=BRMotor.getCurrentPosition();
        destination+=ticks;
        BRMotor.setTargetPosition(destination);
        BRMotor.setPower(speed);

        destination=BLMotor.getCurrentPosition();
        destination+=ticks;
        BLMotor.setTargetPosition(destination);
        BLMotor.setPower(speed);

        destination=FLMotor.getCurrentPosition();
        destination+=ticks;
        FLMotor.setTargetPosition(destination);
        FLMotor.setPower(speed);

        destination=FRMotor.getCurrentPosition();
        destination+=ticks;
        FRMotor.setTargetPosition(destination);
        FRMotor.setPower(speed);

        while ((FLMotor.isBusy())|| (FRMotor.isBusy())|| (BLMotor.isBusy())|| (BRMotor.isBusy())) {
            telemetry.addData("FL pos", FLMotor.getCurrentPosition());
            telemetry.addData("FR pos", FRMotor.getCurrentPosition());
            telemetry.addData("BL pos", BLMotor.getCurrentPosition());
            telemetry.addData("BR pos", BRMotor.getCurrentPosition());
            telemetry.update();

        }
        FLMotor.setPower(0.00);
        FRMotor.setPower(0.00);
        BLMotor.setPower(0.00);
        BRMotor.setPower(0.00);


    }

    //This public void is used to move the robot to the sides. all that's needed is the speed and length it moves.
    //setting the "ticks" to negative will make the robot strafe the other direction.
    public void strafe (double speed, int ticks) {
        speed = Range.clip(speed, -1, 1);

        //setting the speed to negative on the front left and back right wheels will make the robot strafe sideways.
        destination=FLMotor.getCurrentPosition();
        destination-=ticks;
        FLMotor.setTargetPosition(destination);
        FLMotor.setPower(-speed);

        destination=FRMotor.getCurrentPosition();
        destination+=ticks;
        FRMotor.setTargetPosition(destination);
        FRMotor.setPower(speed);

        destination=BLMotor.getCurrentPosition();
        destination+=ticks;
        BLMotor.setTargetPosition(destination);
        BLMotor.setPower(speed);

        destination=BRMotor.getCurrentPosition();
        destination-=ticks;
        BRMotor.setTargetPosition(destination);
        BRMotor.setPower(-speed);

        while ((FLMotor.isBusy())|| (FRMotor.isBusy())|| (BLMotor.isBusy())|| (BRMotor.isBusy()));
        FLMotor.setPower(0.00);
        FRMotor.setPower(0.00);
        BLMotor.setPower(0.00);
        BRMotor.setPower(0.00);
    }

    //This public void will make the robot turn left or right. all that's needed is the speed and length it moves.
    //setting the "ticks" to negative will make the robot turn the other direction.
    public void turn (double speed, int ticks) {
        speed = Range.clip(speed, -1, 1);

        //setting the front left and back left wheels to negative will make the robot turn.
        destination=FLMotor.getCurrentPosition();
        destination-=ticks;
        FLMotor.setPower(-speed);
        FLMotor.setTargetPosition(destination);

        destination=FRMotor.getCurrentPosition();
        destination+=ticks;
        FRMotor.setPower(speed);
        FRMotor.setTargetPosition(destination);

        destination=BLMotor.getCurrentPosition();
        destination-=ticks;
        BLMotor.setPower(-speed);
        BLMotor.setTargetPosition(destination);

        destination=BRMotor.getCurrentPosition();
        destination+=ticks;
        BRMotor.setPower(speed);
        BRMotor.setTargetPosition(destination);

        while ((FLMotor.isBusy())|| (FRMotor.isBusy())|| (BLMotor.isBusy())|| (BRMotor.isBusy()));
        FLMotor.setPower(0.00);
        FRMotor.setPower(0.00);
        BLMotor.setPower(0.00);
        BRMotor.setPower(0.00);
    }

    public void init_color() {

        Color = hardwareMap.colorSensor.get("color");

            telemetry.addData("red:", Color.red());
            telemetry.addData("blue:", Color.blue());
            telemetry.addData("green", Color.green());
            telemetry.update();


    }


                                 // - extra - \\

    // sensor telemetries \\
    // color sensor telemetries
           // telemetry.addData("red:", Color.red());
           // telemetry.addData("blue:", Color.blue());
           // telemetry.addData("green", Color.green());
           // telemetry.update();

    // range sensor telemetries
            /*telemetry.addData("raw ultrasonic", rangeSensor.rawUltrasonic());
            telemetry.addData("raw optical", rangeSensor.rawOptical());
            telemetry.addData("cm optical", "%.2f cm", rangeSensor.cmOptical());
            telemetry.addData("cm", "%.2f cm", rangeSensor.getDistance(DistanceUnit.CM));
            telemetry.update();*/


}