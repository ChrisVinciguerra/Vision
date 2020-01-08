package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Limelight {
    private static PIDController turnController, distanceController;
    static {
        turnController = new PIDController(.001, 0, 0);
        turnController.setTolerance(.1);
        turnController.setSetpoint(0);
        distanceController = new PIDController(.001, 0, 0);
        distanceController.setTolerance(1);
        distanceController.setSetpoint(7);
        SmartDashboard.putData(turnController);
        SmartDashboard.putData(distanceController);
    }

    public static void run(boolean dataOnly) {
        NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
        double tv = limelightTable.getEntry("tv").getDouble(0);
        double tx = limelightTable.getEntry("tx").getDouble(0);
        double ta = limelightTable.getEntry("ta").getDouble(0);
        SmartDashboard.putNumber("TV", tv);
        SmartDashboard.putNumber("TX", tx);
        SmartDashboard.putNumber("TA", ta);
        if (tv == 1.0 && !dataOnly) {
            double turnSpeed = turnController.calculate(tx);
            double straightSpeed = -distanceController.calculate(ta);
            SmartDashboard.putNumber("Straight Speed", straightSpeed);
            SmartDashboard.putNumber("Turn Speed", turnSpeed);
            Drivetrain.arcadeDrive(straightSpeed, turnSpeed, 0);
        } else {
            SmartDashboard.putNumber("Straight Speed", 0);
            SmartDashboard.putNumber("Turn Speed", 0);
            Drivetrain.arcadeDrive(0, 0, 0);
        }
    }
}