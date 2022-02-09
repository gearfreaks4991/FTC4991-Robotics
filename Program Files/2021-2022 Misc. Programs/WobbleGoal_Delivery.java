package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@Autonomous(name="WobbleGoal_Delivery")
public class WobbleGoal_Delivery extends Autonomous_Base {
    @Override
    public void runOpMode() throws InterruptedException {
        init_motors();

        waitForStart();

        //mcdrive();
        grabber();
    }
}
