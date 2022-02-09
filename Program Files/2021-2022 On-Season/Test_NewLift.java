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

@TeleOp(name="Test_NewLift")
public class Test_NewLift extends LinearOpMode {


    DcMotor Lift;


    boolean DpadUp;
    boolean DpadDown;

    int Liftvariable;
    @Override
    public void runOpMode() throws InterruptedException {


        Lift = hardwareMap.dcMotor.get("lift");
        Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lift.setTargetPosition(0);
        Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();

        while (opModeIsActive()) {

            DpadDown = gamepad1.dpad_down;
            DpadUp = gamepad1.dpad_up;
            Liftvariable = Lift.getCurrentPosition();

            if (Lift.getCurrentPosition() > 1800); {
                Liftvariable = 1800;
            }
            if (Lift.getCurrentPosition() < 0); {
                Liftvariable = 0;
            }

            if (DpadUp) {
                Lift.setPower(1.00);
                Liftvariable=Lift.getCurrentPosition();
                Liftvariable+=200;
                sleep (200);
                Lift.setTargetPosition(Liftvariable);
                telemetry.addData("Lift Pos:", Lift.getCurrentPosition());
                telemetry.update();
            }

            if (DpadDown) {
                Lift.setPower(1.00);
                Liftvariable=Lift.getCurrentPosition();
                Liftvariable-=200;
                sleep(200);
                Lift.setTargetPosition(Liftvariable);
                telemetry.addData("Lift Pos:", Lift.getCurrentPosition());
                telemetry.update();
            }










        }

    }

}
