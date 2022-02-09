package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public abstract class Autonomous_Base extends LinearOpMode {

    DcMotor FL;
    DcMotor FR;
    Servo claw;

    public void init_motors () {

        double FL_power = 0.0;
        double FR_power = 0.0;
        FL = hardwareMap.dcMotor.get("fl");
        FR = hardwareMap.dcMotor.get("fr");
        claw = hardwareMap.servo.get("motor");
    }
    public void mcdrive () {

        FL.setPower(1.00);
        FR.setPower(1.00);

        sleep(3000);
        FL.setPower(0.00);
        FR.setPower(0.00);
    }

    public void grabber () {

        // this is for a continuous servo

        //move servo forward for 3 seconds
        claw.setPosition(1.00);
        sleep(3000);

        // move servo reverse for 4 seconds
        claw.setPosition(0.00);
        sleep(4000);

    }


}