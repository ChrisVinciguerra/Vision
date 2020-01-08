package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Drivetrain {
    private static TalonSRX frontLeft, topLeft, backLeft, frontRight, topRight, backRight;
    static {
        topLeft = new TalonSRX(10);
        topRight = new TalonSRX(4);
        /*frontLeft = new TalonSRX(9);
        frontLeft.set(ControlMode.Follower, 10);
        backLeft = new TalonSRX(8);
        backLeft.set(ControlMode.Follower, 10);
        frontRight = new TalonSRX(2);
        frontRight.set(ControlMode.Follower, 4);
        backRight = new TalonSRX(3);
        backRight.set(ControlMode.Follower, 4);
*/
    }
    public static void arcadeDrive(double speedStraight, double speedLeft, double speedRight) {
        topLeft.set(ControlMode.PercentOutput, speedStraight + speedLeft - speedRight);
        topRight.set(ControlMode.PercentOutput, speedStraight - speedLeft + speedRight);
    }

}