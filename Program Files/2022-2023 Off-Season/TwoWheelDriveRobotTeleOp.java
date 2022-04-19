package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="TwoWheelDriveRobotTeleOp")
public class TwoWheelDriveRobotTeleOp extends LinearOpMode {

    //-------------------------------- Drivetrain Motors --------------------------------\\

    DcMotor LeftMotor;
    DcMotor RightMotor;



    //-------------------------------- Variables --------------------------------\\


    // Buttons that will be used to control the robot's functions.

    //Gamepad1 Variables
    float left_y_value;
    float right_y_value;
    double LeftMotorPower;
    double RightMotorPower;

    @Override
    public void runOpMode() throws InterruptedException {


        //-------------------------------- Hardware Mapping --------------------------------\\


        // Hardware mapping the Drivetrain's 2 drive motors, they are Neverest 60 Motors and go 1680 ticks per rotation.
        LeftMotor = hardwareMap.dcMotor.get("LeftMotor");
        RightMotor = hardwareMap.dcMotor.get("RightMotor");

        // Reversing 1 of the Drivetrain motors to allow the wheels to both move in the correct direction.
        LeftMotor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();


        while (opModeIsActive()) {
            ///-------------------------------- Defining Buttons and Drivetrain code --------------------------------\\

            left_y_value = gamepad1.left_stick_y;
            right_y_value = gamepad1.right_stick_y;
            LeftMotorPower = left_y_value;
            RightMotorPower = right_y_value;
            LeftMotorPower = Range.clip(LeftMotorPower, -1, 1);
            RightMotorPower = Range.clip(RightMotorPower, -1, 1);
            LeftMotor.setPower(-LeftMotorPower*(0.50));
            RightMotor.setPower(-RightMotorPower*(0.50));


        }
    }
}