package org.firstinspires.ftc.teamcode;

import android.view.Display;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;


/**
 * Created by Anton on 1/4/2017.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="New TeleOp", group="OctoPi")
public class TeleOp2 extends LinearOpMode {
    //Initiate variables
    Hardware2 robot = new Hardware2(); //Use created hardware Class

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        //declaring button variables
        double R;
        double L;
        boolean left = false;
        boolean right = false;
        boolean shoot = false;


        //signal robot waiting with message
        telemetry.addData("OctoPi is ready for ", "START");
        telemetry.update();

        //wait for driver to push play
        waitForStart();

        //until stop is pressed
        while(opModeIsActive()){
            //get values of joysticks
            R = -gamepad1.right_stick_y;
            L = -gamepad1.left_stick_y;
            //set power equal to those in tank drive format
            robot.Right.setPower(R/2);
            robot.Left.setPower(L/2);
            //find out which bumpers are being pressed
            left = gamepad1.left_bumper;
            right = gamepad1.right_bumper;
            //set front and back motors to move left or right depending on the bumper pressed
            if(left && !right){
                robot.Front.setPower(.2);
                robot.Back.setPower(.2);
            } else if(right && !left){
                robot.Front.setPower(-.2);
                robot.Back.setPower(-.2);
            }
            //check if a was pressed on gamepad 2, can be changed to gamepad 1 if we arent using two gamepads
            shoot = gamepad2.a;
            //turn on the shooter motors in opposite directions
            if(shoot){
                robot.Shooter1.setPower(.5);
                robot.Shooter2.setPower(-.5);
            }
            


        }
    }
}
