package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Drivetrain {
    private static TalonSRX topLeft, topRight;
    private static VictorSPX backLeft, backRight;
    static {
        topLeft = new TalonSRX(2);
        topRight = new TalonSRX(1);
        backLeft = new VictorSPX(4);
        backLeft.follow(topLeft);
        backRight = new VictorSPX(3);
        backRight.follow(topRight);
    }

    public static void arcadeDrive(double speedStraight, double speedLeft, double speedRight) {
        double totalLeft = speedStraight + speedLeft - speedRight;
        double totalRight = speedStraight - speedLeft + speedRight;
        if (totalLeft > 1 || totalRight > 1) {
            double largest = Math.max(totalLeft, totalRight);
            totalLeft /= largest;
            totalRight /= largest;
        }
        topLeft.set(ControlMode.PercentOutput, totalLeft);
        topRight.set(ControlMode.PercentOutput, totalRight);
    }
}