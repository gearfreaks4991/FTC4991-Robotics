package org.firstinspires.ftc.teamcode;

//Imports
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

public abstract class mcBase_Auto extends LinearOpMode{

//drive train variables
    DcMotor FLMotor;
    DcMotor FRMotor;
    DcMotor BLMotor;
    DcMotor BRMotor;

    boolean YES = true;

    public void init_motors () {

        //hardware mapping

        FLMotor = hardwareMap.dcMotor.get("fl");
        FRMotor = hardwareMap.dcMotor.get("fr");
        BLMotor = hardwareMap.dcMotor.get("bl");
        BRMotor = hardwareMap.dcMotor.get("br");

        FLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLMotor.setTargetPosition(0);
        FLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FRMotor.setTargetPosition(0);
        FRMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        BLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLMotor.setTargetPosition(0);
        BLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        BRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRMotor.setTargetPosition(0);
        BRMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    // reversing motors
        FLMotor.setDirection(DcMotor.Direction.REVERSE);
        BLMotor.setDirection(DcMotor.Direction.REVERSE);


    }

    public void mcdrive(double inches, double speed) {

        float distance = (((float) inches) * (float) 61.050206128);
        int distance_int = Math.round(distance);

        int target_position_FL;
        int target_position_FR;
        int target_position_BL;
        int target_position_BR;

        target_position_FL = (FLMotor.getCurrentPosition() + distance_int);
        target_position_FR = (FRMotor.getCurrentPosition() + distance_int);
        target_position_BL = (BLMotor.getCurrentPosition() + distance_int);
        target_position_BR = (BRMotor.getCurrentPosition() + distance_int);

        FLMotor.setPower(speed);
        FRMotor.setPower(speed);
        BLMotor.setPower(speed);
        BRMotor.setPower(speed);


        FLMotor.setTargetPosition(target_position_FL);
        FRMotor.setTargetPosition(target_position_FR);
        BLMotor.setTargetPosition(target_position_BL);
        BRMotor.setTargetPosition(target_position_BR);



        while(FLMotor.isBusy()||FRMotor.isBusy()||BLMotor.isBusy()||BRMotor.isBusy()) {
            telemetry.addData("BR Wheel Position:", BRMotor.getCurrentPosition());
            telemetry.update();
        }

        FLMotor.setPower(0.00);
        FRMotor.setPower(0.00);
        BLMotor.setPower(0.00);
        BRMotor.setPower(0.00);



    }

    public void mcstrafe(double inches, double speed) {

        float distance = (((float) inches) * (float) 61.050206128);
        int distance_int = Math.round(distance);

        int target_position_FL;
        int target_position_FR;
        int target_position_BL;
        int target_position_BR;

        target_position_FL = (FLMotor.getCurrentPosition() - distance_int);
        target_position_FR = (FRMotor.getCurrentPosition() + distance_int);
        target_position_BL = (BLMotor.getCurrentPosition() + distance_int);
        target_position_BR = (BRMotor.getCurrentPosition() - distance_int);

        FLMotor.setPower(speed);
        FRMotor.setPower(speed);
        BLMotor.setPower(speed);
        BRMotor.setPower(speed);



        FLMotor.setTargetPosition(target_position_FL);
        FRMotor.setTargetPosition(target_position_FR);
        BLMotor.setTargetPosition(target_position_BL);
        BRMotor.setTargetPosition(target_position_BR);



        while(FLMotor.isBusy()||FRMotor.isBusy()||BLMotor.isBusy()||BRMotor.isBusy()) {
            telemetry.addData("BR Wheel Position:", BRMotor.getCurrentPosition());
            telemetry.update();
        }

        FLMotor.setPower(0.00);
        FRMotor.setPower(0.00);
        BLMotor.setPower(0.00);
        BRMotor.setPower(0.00);


    }
    public void mcturn(double inches, double speed) {

        float distance = (((float) inches) * (float) 61.050206128);
        int distance_int = Math.round(distance);
        //turning is not the same number as inches, obviously, but 23 inches
        //roughly translates to 90 degrees.


        int target_position_FL;
        int target_position_FR;
        int target_position_BL;
        int target_position_BR;

        target_position_FL = (FLMotor.getCurrentPosition() - distance_int);
        target_position_FR = (FRMotor.getCurrentPosition() + distance_int);
        target_position_BL = (BLMotor.getCurrentPosition() - distance_int);
        target_position_BR = (BRMotor.getCurrentPosition() + distance_int);

        FLMotor.setPower(speed);
        FRMotor.setPower(speed);
        BLMotor.setPower(speed);
        BRMotor.setPower(speed);



        FLMotor.setTargetPosition(target_position_FL);
        FRMotor.setTargetPosition(target_position_FR);
        BLMotor.setTargetPosition(target_position_BL);
        BRMotor.setTargetPosition(target_position_BR);



        while(FLMotor.isBusy()||FRMotor.isBusy()||BLMotor.isBusy()||BRMotor.isBusy()) {
            telemetry.addData("BR Wheel Position:", BRMotor.getCurrentPosition());
            telemetry.update();
        }

        FLMotor.setPower(0.00);
        FRMotor.setPower(0.00);
        BLMotor.setPower(0.00);
        BRMotor.setPower(0.00);


    }






}
