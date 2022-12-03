package org.firstinspires.ftc.teamcode;

// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="AUTO22_TickTest")
public class AUTO22_TickTest extends COMP22_Auto_Base_ProgramChassis {

    @Override
    public void runOpMode() throws InterruptedException {



        init_motors();

        waitForStart();
        /*
            The goal of this program is to do five things; detect the sleeve correctly, place a cone on the terminal, the
            low junction near to the station, and the other low junction closest to the terminal, and finally park correctly
            in the designated space.
        The Drive Motors are 312 RPM with 537.7 Pulses per Rotation
        */
        telemetry.addData("FL pos", FLMotor.getCurrentPosition());
        telemetry.addData("FR pos", FRMotor.getCurrentPosition());
        telemetry.addData("BL pos", BLMotor.getCurrentPosition());
        telemetry.addData("BR pos", BRMotor.getCurrentPosition());
        telemetry.update();

        drive(0.25, 2000);
        sleep(1500);
        strafe(0.25,-500);
        sleep(1500);
        turn(0.25,500);

    }

}