package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Limelight {
    private static PIDController turnController, distanceController;
    private static NetworkTable limelightTable;
    static {
        limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
        turnController = new PIDController(0.016, 0, .06);// .21, .7
        turnController.setSetpoint(0);
        distanceController = new PIDController(.008, 0, 0);// 1.1, .9
        distanceController.setSetpoint(0);
        SmartDashboard.putData(turnController);
        SmartDashboard.putData(distanceController);
    }

    public static void run() {
        double tv = limelightTable.getEntry("tv").getDouble(0);
        double tx = limelightTable.getEntry("tx").getDouble(0);
        double tlong = limelightTable.getEntry("tlong").getDouble(0);
        
        double turnSpeed = 0;
        double straightSpeed = 0;
        if (tv == 1.0) {
            turnSpeed = turnController.calculate(tx);
            straightSpeed = -distanceController.calculate(tlong);
            Drivetrain.arcadeDrive(straightSpeed, turnSpeed, 0);
        }
        SmartDashboard.putNumber("Current error: ", tx);
        SmartDashboard.putNumber("Current area: ", tlong);

        SmartDashboard.putNumber("Straight Speed", straightSpeed);
        SmartDashboard.putNumber("Turn Speed", turnSpeed);
        Drivetrain.arcadeDrive(straightSpeed, turnSpeed, 0);
    }
}