package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


@Autonomous(name="Motor1_Position_Auto_Base")
public class Motor1_Position_Auto_Base extends Motor1_Autonomous_Base {

    @Override
    public void runOpMode() throws InterruptedException {

        init_motors();

        waitForStart();

            mccolor();
            sleep(3000);

            mcdrive(0.50, 2000);
            sleep(1000);

            //mcdrive(0.50, 3500);

            //mcdrivecheese(0.50, 2000);
            //sleep(1000);

            mcservo(0.00);
            sleep(1000);

            mcservo(1.00);
            sleep(1000);

            doublemcservo(0.00);
            sleep(1000);

            doublemcservo(1.00);
            sleep(1000);
            //sleep(10000);


    }
}
