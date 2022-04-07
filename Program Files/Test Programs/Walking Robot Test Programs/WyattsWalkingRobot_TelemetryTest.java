package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name="WyattsWalkingRobot_TelemetryTest")
public class WyattsWalkingRobot_TelemetryTest extends LinearOpMode {



    boolean A;
    boolean B;
    boolean X;
    boolean Y;
    boolean DpadUp;
    boolean DpadDown;
    boolean DpadLeft;
    boolean DpadRight;
    float LeftJoystickX;
    float RightJoystickX;
    float LeftJoystickY;
    float RightJoystickY;

    @Override
    public void runOpMode() throws InterruptedException {

        Servo FL_Upper;
        Servo FL_Lower;
        Servo FR_Upper;
        Servo FR_Lower;
        Servo BL_Upper;
        Servo BL_Lower;
        Servo BR_Upper;
        Servo BR_Lower;

        FL_Upper = hardwareMap.servo.get("ufl");
        FL_Lower = hardwareMap.servo.get("lfl");
        FR_Upper = hardwareMap.servo.get("ufr");
        FR_Lower = hardwareMap.servo.get("lfr");
        BL_Upper = hardwareMap.servo.get("ubl");
        BL_Lower = hardwareMap.servo.get("lbl");
        BR_Upper = hardwareMap.servo.get("ubr");
        BR_Lower = hardwareMap.servo.get("lbr");

        waitForStart();



        while (opModeIsActive()) {


            A = gamepad1.a;
            B = gamepad1.b;
            X = gamepad1.x;
            Y = gamepad1.y;
            DpadUp = gamepad1.dpad_up;
            DpadDown = gamepad1.dpad_down;
            DpadLeft = gamepad1.dpad_left;
            DpadRight = gamepad1.dpad_right;
            LeftJoystickX = gamepad1.left_stick_x;
            LeftJoystickY = gamepad1.left_stick_y;
            RightJoystickX = gamepad1.right_stick_x;
            RightJoystickY = gamepad1.right_stick_y;

            // FL Upper
            if (A) {
                FL_Upper.setPosition(0.4);
                telemetry.addData("FL_Upper Pos:", FL_Upper.getPosition());
                telemetry.update();
            }
            if (B) {
                FL_Upper.setPosition(0.6);
                telemetry.addData("FL_Upper Pos:", FL_Upper.getPosition());
                telemetry.update();
            }

            // FL Lower
            if (X) {
                FL_Lower.setPosition(0.4);
                telemetry.addData("FL_Lower Pos:", FL_Lower.getPosition());
                telemetry.update();
            }
            if (Y) {
                FL_Lower.setPosition(0.6);
                telemetry.addData("FL_Lower Pos:", FL_Lower.getPosition());
                telemetry.update();
            }

            // FR Upper
            if (DpadUp) {
                FR_Upper.setPosition(0.4);
                telemetry.addData("FR_Upper Pos:", FR_Upper.getPosition());
                telemetry.update();
            }
            if (DpadDown) {
                FR_Upper.setPosition(0.6);
                telemetry.addData("FR_Upper Pos:", FR_Upper.getPosition());
                telemetry.update();
            }

            // FR Lower
            if (DpadLeft) {
                FR_Lower.setPosition(0.4);
                telemetry.addData("FL_Lower Pos:", FR_Lower.getPosition());
                telemetry.update();
            }
            if (DpadRight) {
                FR_Lower.setPosition(0.6);
                telemetry.addData("FL_Lower Pos:", FR_Lower.getPosition());
                telemetry.update();
            }

            // BL Upper
            if (LeftJoystickY > 0.2) {
                BL_Upper.setPosition(0.4);
                telemetry.addData("BL_Upper Pos:", BL_Upper.getPosition());
                telemetry.update();
            }
            if (LeftJoystickY < -0.2) {
                BL_Upper.setPosition(0.6);
                telemetry.addData("BL_Upper Pos:", BL_Upper.getPosition());
                telemetry.update();
            }

            // BL Lower
            if (LeftJoystickX > 0.2) {
                BL_Lower.setPosition(0.4);
                telemetry.addData("BL_Lower Pos:", BL_Lower.getPosition());
                telemetry.update();
            }
            if (LeftJoystickX < -0.2) {
                BL_Lower.setPosition(0.6);
                telemetry.addData("BL_Lower Pos:", BL_Lower.getPosition());
                telemetry.update();
            }

            // BR Upper
            if (RightJoystickY > 0.2) {
                BR_Upper.setPosition(0.4);
                telemetry.addData("BR_Upper Pos:", BR_Upper.getPosition());
                telemetry.update();
            }
            if (RightJoystickY < -0.2) {
                BR_Upper.setPosition(0.6);
                telemetry.addData("BR_Upper Pos:", BR_Upper.getPosition());
                telemetry.update();
            }

            // BR Lower
            if (RightJoystickX > 0.2) {
                BR_Lower.setPosition(0.4);
                telemetry.addData("BR_Lower Pos:", BR_Lower.getPosition());
                telemetry.update();
            }
            if (RightJoystickX < -0.2) {
                BR_Lower.setPosition(0.6);
                telemetry.addData("BR_Lower Pos:", BR_Lower.getPosition());
                telemetry.update();
            }

            // If this doesn't work I will spontaneously combust into a moderately-sized napalm explosion resulting in the death of brupper_upper(tm).
        }

    }



}
