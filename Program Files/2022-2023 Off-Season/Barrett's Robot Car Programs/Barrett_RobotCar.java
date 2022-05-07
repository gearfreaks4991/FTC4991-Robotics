package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.Range;

import org.checkerframework.checker.units.qual.A;

@TeleOp(name="Barrett_RobotCar")
public class Barrett_RobotCar extends LinearOpMode {
    // Defining the Motors, Servos, and other Devices.
    DcMotor LMotor;
    DcMotor RMotor;
    DcMotor Carousel;


    // Defining the Variables
    float RT;
    float LT;
    int destination = 0;
    float LS;
    float RS;

    @Override
    public void runOpMode() throws InterruptedException {

        LMotor = hardwareMap.dcMotor.get("lmotor");
        RMotor = hardwareMap.dcMotor.get("rmotor");
        Carousel = hardwareMap.dcMotor.get("carousel");




        waitForStart();


        while (opModeIsActive()) {

            // Assigning the Variables to the buttons on the Gamepads.
            LS = gamepad1.left_stick_y;
            RS = gamepad1.right_stick_y;
            LT = gamepad1.left_trigger;
            RT = gamepad1.right_trigger;



            //Joysticks control Moving the tank treads.
            LMotor.setPower(LS*0.85);
            RMotor.setPower(-RS*0.85);


            //Carousel
            if (RT > 0.10) {
                Carousel.setPower(-1.00);
            } else {
                Carousel.setPower(0.00);
            }
            if (LT > 0.10) {
                Carousel.setPower(1.00);
            } else {
                Carousel.setPower(0.00);
            }

        }
    }
}


