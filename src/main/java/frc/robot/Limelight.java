package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.controller.PIDController;

public class Limelight {
    private static PIDController turnController, distanceController;
    private static double p1, i1, d1, p2, i2, d2;
    static {
        p1 = 0.0001;
        i1 = 0.000000001;
        d1 = 0.000000001;
        p2 = 0;
        i2 = 0;
        d2 = 0;

        turnController = new PIDController(p1, i1, d1);
        turnController.setTolerance(.1);
        turnController.setSetpoint(0);
        distanceController = new PIDController(p1, 0, 0);
        // Test tolerance and setpoint
        distanceController.setTolerance(1);
        distanceController.setSetpoint(7);
    }

    public static void setP(double p) {
            p1 = p;
            turnController.setP(p1);
    }
    public static void setI(double i) {
        i1 = i;
        turnController.setI(i1);
}
public static void setD(double d) {
    d1 = d;
    turnController.setD(d1);
}

    public static void run(boolean dataOnly) {
        NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
        double tv = limelightTable.getEntry("tv").getDouble(0);
        double tx = limelightTable.getEntry("tx").getDouble(0);
        double ta = limelightTable.getEntry("ta").getDouble(0);
        System.out.println("Tv: " + tv + " Tx: " + tx + " Ta: " + ta);
        if (tv == 1.0 && !dataOnly) {
            System.out.println("Running");
            double turnSpeed = turnController.calculate(tx);
            double straightSpeed = distanceController.calculate(ta);
            System.out.println("Turn speed: " + turnSpeed);
            System.out.println("Straight speed: " + -straightSpeed);
            Drivetrain.arcadeDrive(-straightSpeed, turnSpeed, 0);
        } else {
            Drivetrain.arcadeDrive(0, 0, 0);
        }
    }
}