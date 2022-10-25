package org.firstinspires.ftc.teamcode;

// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="AUTO22_BlueR")
public class AUTO22_BlueR extends COMP22_Auto_Base {

    @Override
    public void runOpMode() throws InterruptedException {


        // Defining the Motors, Servos, and Color Sensor.
        DcMotor Lift;
        Servo Intake;
        ColorSensor Color;

        // Defining the Variables used in the program.
        int destination=0;
        double FL_power = 0.0;
        double FR_power = 0.0;
        double BL_power = 0.0;
        double BR_power = 0.0;
        boolean First;
        boolean Second;
        boolean Third;




        init_motors();

        // Hardwaremapping the various devices.
        Intake = hardwareMap.servo.get("intake");
        Color = hardwareMap.colorSensor.get("color");
        Lift = hardwareMap.dcMotor.get("lift");
        Lift.setTargetPosition(0);
        Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();
        /*
            The goal of this program is to 1; drive up to one of the 3 squares and check for the Duck/Team
            Shipping Element. If it does not detect one of the game elements, it will strafe to the next square.
            It does this with the first two, if it doesn't detect the game elements there, it automatically assumes
            that it is located in the 3rd square, and will drop the cube off in the 3rd level.

            In this variant, it will do nothing after completing the cube task.

        The Drive Motors are 312 RPM with 537.7 Pulses per Rotation
        */

        drive(0.50, 650);

        sleep(750);








    }


}