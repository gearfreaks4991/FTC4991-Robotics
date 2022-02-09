package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="wheel_FR_test")
public class wheel_FR_test extends LinearOpMode {

    //-------------------------------- Drivetrain Motors --------------------------------\\
    // Motors are Gobilda 5202 and rotate at around 435 Revolutions/Rotations Per Minute (RPM).
    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;

    @Override
    public void runOpMode() throws InterruptedException {


        //-------------------------------- Hardware Mapping --------------------------------\\


        // Hardware mapping the Drivetrain's 4 main drive motors, they should be Gobilda 5202 which spin at 435 RPM.
        FL = hardwareMap.dcMotor.get("lf");
        FR = hardwareMap.dcMotor.get("rf");
        BL = hardwareMap.dcMotor.get("lb");
        BR = hardwareMap.dcMotor.get("rb");

        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FL.setTargetPosition(0);
        FR.setTargetPosition(0);
        BL.setTargetPosition(0);
        BR.setTargetPosition(0);

        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Reversing 2 of the Drivetrain motors to allow the wheels to all move in the correct direction.
      /*  FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);
*/

        telemetry.addData("FR Starting position: ", FR.getCurrentPosition());
        telemetry.update();


        // Wait for the game to start (driver presses PLAY).
        waitForStart();

            FR.setPower(1.00);
            telemetry.addData("FL: Starting Ticks" , FR.getCurrentPosition());
            FR.setTargetPosition(5000);
            while( FR.isBusy()) {
                telemetry.addData("FR: ", FR.getCurrentPosition());
                telemetry.update();
            }
        telemetry.addData("FR: ", FR.getCurrentPosition());
        telemetry.update();
        }
    }
