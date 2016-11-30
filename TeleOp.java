package org.firstinspires.ftc.teamcode;

import android.view.Display;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Anton on 11/3/2016.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Telop", group="OctoPi")
public class TeleOp extends LinearOpMode {
    //Initiate variables
    Hardware robot = new Hardware(); //Use created hardware Class

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        //declaring button variables
        double RL;
        double FB;
        double left;
        boolean leftExtended=false;
        boolean rightExtended= false;


        //signal robot waiting with message
        telemetry.addData("OctoPi is ready for ", "START");
        telemetry.update();

        //wait for driver to push play
        waitForStart();

        //until stop is pressed
        while(opModeIsActive()){
            if(gamepad1.left_stick_x==0) {
                //tank drive forward: move left stick forward (gamepad goes negative when forward so negate it)
                RL = -gamepad1.right_stick_x;
                robot.motorRight.setPower(RL / 2);
                //robot.motor_R2.setPower(left);
                robot.motorLeft.setPower(RL / 2);
                //robot.motor_L2.setPower(left);

                //tank drive turning: move right stick left for left turn, right for right turn
                FB = -gamepad1.right_stick_y;
                robot.motorBack.setPower(FB / 2);
                robot.motorFront.setPower(FB / 2);
            }
            //for rotating
            else if(gamepad1.right_stick_x==0 && gamepad1.right_stick_y==0) {
                left = -gamepad1.left_stick_x;
                robot.motorBack.setPower(left / 4);
                robot.motorRight.setPower(-left / 4);

                robot.motorFront.setPower(-left / 4);
                robot.motorLeft.setPower(left / 4);
            }

            //activate left pusher by pushing left bumper
            if(gamepad1.left_bumper){
                robot.pushLeft.setPosition(1);
            } else if(gamepad1.left_trigger>0){
                robot.pushLeft.setPosition(.5);
            }
            if(gamepad1.right_bumper){
                robot.pushRight.setPosition(0);
            } else if(gamepad1.right_trigger>0){
                robot.pushRight.setPosition(.5);
            }
            //activate right pusher by pushing right bumper

        }



    }
}
