package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Anton on 11/4/2016.
 */
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="Auto", group="OctoPi")
public class AutoDrive extends LinearOpMode{
    Hardware robot = new Hardware(); //Use created hardware Class
    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        telemetry.addData("OctoPi is ready for ", "START");
        telemetry.update();

        //wait for driver to push play
        waitForStart();

        robot.pushLeft.setPosition(1);



        robot.motorBack.setPower(.5);
        robot.motorFront.setPower(.5);
        runtime.reset();
        while(opModeIsActive() && runtime.seconds()<.8){
            telemetry.addData("Path:", "Moving Forward", runtime.seconds());
            telemetry.update();
        }
        runtime.reset();
        /*while(opModeIsActive() && runtime.seconds()<.5) {
            robot.motorBack.setPower(.2);
            robot.motorRight.setPower(-.2);

            robot.motorFront.setPower(-.2);
            robot.motorLeft.setPower(.2);
            //runtime.reset()
        }
        robot.pushLeft.setPosition(.5);
        runtime.reset();

        while(opModeIsActive() && runtime.seconds()<.5){
            robot.motorBack.setPower(.3);
            robot.motorFront.setPower(.3);
        }*/
        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(3000);


    }
}
