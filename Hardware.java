package org.firstinspires.ftc.teamcode;
/* Initialize all objects and put them into default stages.
 *
 */
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Hardware {

    //initialize motors for wheels
    public DcMotor motorFront = null;
    public DcMotor motorBack  = null;
    public DcMotor motorRight = null;
    public DcMotor motorLeft  = null;

    //initialize and set ranges for pushers
    public Servo pushRight = null;
    public Servo pushLeft = null;

    public final static double pushRight_MIN = 0;
    public final static double pushRight_MAX = 1;

    public final static double pushLeft_MIN = 0;
    public final static double pushLeft_MAX = 1;

    //create initial hardware map and counts time
    public HardwareMap hwMap = null;
    private ElapsedTime period  = new ElapsedTime();

    /*Initialize hardware interfaces*/
    public void init(HardwareMap ahwMap) {

        //save reference to HW map
        hwMap = ahwMap;

        //define and initialize motors
        motorFront = hwMap.dcMotor.get("motorFront");
        motorBack = hwMap.dcMotor.get("motorBack");
        motorFront.setDirection(DcMotor.Direction.REVERSE);

        motorRight = hwMap.dcMotor.get("motorRight");

        motorLeft = hwMap.dcMotor.get("motorLeft");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        pushLeft = hwMap.servo.get("pushLeft");
        pushRight = hwMap.servo.get("pushRight");

        //set all motors to zero power
        motorFront.setPower(0);
        motorBack.setPower(0);

        motorRight.setPower(0);
        motorLeft.setPower(0);

        pushLeft.setPosition(.5);
        pushRight.setPosition(.5);

        //Set all motors to run using encoders.
        //May want to use RUN_USING_ENCODERS if encoders are installed.

        motorFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        //pushLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //pushRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }
    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     */
    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}
