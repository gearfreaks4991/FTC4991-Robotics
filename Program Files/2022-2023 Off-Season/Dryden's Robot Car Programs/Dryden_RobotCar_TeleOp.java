package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.concurrent.RecursiveTask;

@TeleOp(name="Dryden_RobotCar_TeleOp")
public class Dryden_RobotCar_TeleOp extends LinearOpMode {

    DcMotor RL;
    DcMotor RR;
    DcMotor Steering;

    float RightStick;
    float LeftStick;

    double RL_Power;
    double RR_Power;



    @Override
    public void runOpMode() throws InterruptedException {
        /* ---------- EPIC LIST OF STUFF YOU SHOULD ADD, DRYDEN: ----------

        1: Headlights or Taillights

        2: (Possibly) Movable spoiler/wing? One mode for downforce and one mode more for speed via adjusting
        the angle of said spoiler/wing.

        3: Front Splitter. (A front spoiler because you don't know what that is, nerd.)

        4: More body styling, to allow for a more aerodnaymic design.

        5: An Underbody. This makes the airflow better and provides more downforce.

        */

        RL = hardwareMap.dcMotor.get("RearLeft");
        RL.setDirection(DcMotorSimple.Direction.REVERSE);

        RR = hardwareMap.dcMotor.get("RearRight");


        Steering = hardwareMap.dcMotor.get("Steering");
        // Steering.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        waitForStart();


        while (opModeIsActive()) {



            LeftStick = gamepad1.left_stick_y;
            RightStick = gamepad1.right_stick_x;


            double SteeringPower = -gamepad1.right_stick_x;
            float PowerValue = gamepad1.left_stick_y;

            RL_Power = (PowerValue);
            RR_Power = (-PowerValue);


            RL_Power = Range.clip(RL_Power, -1, 1);
            RR_Power = Range.clip(RR_Power, -1, 1);
            RL.setPower(RL_Power);
            RR.setPower(RR_Power);


            SteeringPower = Range.clip(SteeringPower, -0.75, 0.75);
            if (gamepad1.right_stick_x > 0.1 || gamepad1.right_stick_x < -0.1) {
                Steering.setPower(-SteeringPower);
            }
            else {
                Steering.setPower(0.00);
            }


        }
    }
}


