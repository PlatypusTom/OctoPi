package org.firstinspires.ftc.teamcode;
/* Initialize all objects and put them into default stages.
 *
 */
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Hardware2 {

    //initialize motors for wheels
    public DcMotor Right = null;
    public DcMotor Left  = null;

    public DcMotor Front = null;
    public DcMotor Back = null;

    //initilize motors for shooter
    public DcMotor Shooter1  = null;
    public DcMotor Shooter2 = null;

    //create initial hardware map and counts time
    public HardwareMap hwMap = null;
    private ElapsedTime period  = new ElapsedTime();

    /*Initialize hardware interfaces*/
    public void init(HardwareMap ahwMap) {

        //save reference to HW map
        hwMap = ahwMap;

        //define and initialize motors
        Right = hwMap.dcMotor.get("Right");
        Left = hwMap.dcMotor.get("Left");
        Right.setDirection(DcMotor.Direction.REVERSE);

        Front = hwMap.dcMotor.get("Front");
        Back = hwMap.dcMotor.get("Back");
        Front.setDirection(DcMotor.Direction.REVERSE);

        Shooter1 = hwMap.dcMotor.get("Shooter1");
        Shooter2 = hwMap.dcMotor.get("Shooter2");

        //set all motors to zero power
        Right.setPower(0);
        Left.setPower(0);

        Front.setPower(0);
        Back.setPower(0);

        Shooter1.setPower(0);
        Shooter2.setPower(0);

        //Set all motors to run using encoders.
        //May want to use RUN_USING_ENCODERS if encoders are installed.
        //use RUN_WITHOUT_ENCODERS to disable encoders

        Right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Front.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Back.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Shooter1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Shooter2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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
