package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="Motor1_Position_Tele_Base")
public class Motor1_Position_Tele_Base extends Motor1_TeleOp_Base {

    @Override
    public void runOpMode() throws InterruptedException {

        init_motors2();

        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Red", Color1.red());
            telemetry.addData("Blue", Color1.blue());
            telemetry.addData("Green", Color1.green());
            telemetry.update();

            if (Color1.red() > 320) {
                FLMotor.setPower(0.50);
            }
            else FLMotor.setPower(0.00);

            if (Color1.blue() > 320) {
                FRMotor.setPower(0.50);
            }
            else FRMotor.setPower(0.00);

            if (Color1.green() > 320) {
                HalfServo.setPosition(0.00);
            }
            else HalfServo.setPosition(0.50);

            if (Touch1.isPressed()) {
                ContinuousServo.setPosition(1.00);
            }
            else ContinuousServo.setPosition(0.50);
        }
    }
}
