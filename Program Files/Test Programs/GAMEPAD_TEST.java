package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp (name="GAMEPAD_TEST")
public class GAMEPAD_TEST extends LinearOpMode {

    /*
    We use this program to make sure that all the buttons on a specific controller work without any issues.
    It uses all the buttons and displays Telemetry when the Joysticks are moved or when Buttons are pressed, using
    this Telemetry Data we can find out which of the buttons or which controllers may not be working correctly.

    This can help us determine which controllers are reliable enough to use in Competitions, Qualifiers, and
    Scrimmages.
     */
    //Gamepad1 Buttons
    float left_joystickx;
    float left_joysticky;
    float right_joystickx;
    float right_joysticky;

    boolean buttonA;
    boolean buttonB;
    boolean buttonX;
    boolean buttonY;

    boolean LeftBumper;
    boolean RightBumper;
    int leftTrigger;
    int rightTrigger;

    boolean dpad_up;
    boolean dpad_down;
    boolean dpad_left;
    boolean dpad_right;


    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {


        // The point of this program is to ensure that all of our gamepad buttons are working correctly.
        // We typically use this program if we are unsure if a gamepad is broken or not, or use it to test out
        // said gamepads inbetween competitions or scrimmages.

        telemetry.addData("Starting Position: ", "Current_Rotation_Position");
        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            buttonA = gamepad1.a;
            buttonB = gamepad1.b;
            buttonX = gamepad1.x;
            buttonX = gamepad1.y;
            dpad_up = gamepad1.dpad_up;
            dpad_down = gamepad1.dpad_down;
            dpad_left = gamepad1.dpad_left;
            dpad_right = gamepad1.dpad_right;

            left_joysticky = gamepad1.left_stick_y;
            left_joystickx = gamepad1.left_stick_x;
            right_joysticky = gamepad1.right_stick_y;
            right_joystickx = gamepad1.right_stick_x;

            if(buttonA) {
                telemetry.addData("Button A:", "PUSHED ");
            }
            if(buttonB) {
                telemetry.addData("Button B:", "PUSHED ");
            }
            if(buttonX) {
                telemetry.addData("Button X:", "PUSHED ");
            }
            if(buttonY) {
                telemetry.addData("Button Y:", "PUSHED ");
            }
            if(LeftBumper) {
                telemetry.addData("Left Bumper:", "PUSHED ");
            }
            if(RightBumper) {
                telemetry.addData("Right Bumper:", "PUSHED ");
            }
            if(leftTrigger > 0) {
                telemetry.addData("Left Trigger:", "PUSHED ");
            }
            if(rightTrigger > 0) {
                telemetry.addData("Right Trigger:", "PUSHED ");
            }
            if(dpad_up) {
                telemetry.addData("Dpad UP:", "PUSHED ");
            }
            if(dpad_down) {
                telemetry.addData("Dpad DOWN:", "PUSHED ");
            }
            if(dpad_left) {
                telemetry.addData("Dpad LEFT:", "PUSHED ");
            }
            if(dpad_right) {
                telemetry.addData("Dpad RIGHT:", "PUSHED ");
            }

            if(right_joystickx > 0) {
                telemetry.addData("Right Joystick X:", "PUSHED ");
            }
            if(right_joysticky > 0) {
                telemetry.addData("Right Joystick Y:", "PUSHED ");
            }
            if(left_joystickx > 0) {
                telemetry.addData("Left Joystick X:", "PUSHED ");
            }
            if(left_joysticky > 0) {
                telemetry.addData("Left Joystick Y:", "PUSHED ");
            }
            telemetry.update();


        }
    }
}