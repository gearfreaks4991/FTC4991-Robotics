package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


@Autonomous(name="McMcMcMcAuto")

public class McMcMcMcAuto extends mcBase_Auto {

    @Override
    public void runOpMode() throws InterruptedException {

        init_motors();

        waitForStart();

        mcdrive(24, 1.00);

        mcturn(23, 0.50);

        mcdrive(24, 1.00);

        mcstrafe(24, 0.50);

        mcdrive(-24, 1.00);

        mcturn(-23, 1.00);

        mcturn(92, 1.00);


    }

}
