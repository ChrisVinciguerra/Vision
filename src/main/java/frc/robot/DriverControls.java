package frc.robot;

public class DriverControls extends PS4Controller {

    public static DriverControls singletonInstance = new DriverControls(0, 2);

    private double speedStraight, speedLeft, speedRight;

    public static void driverControls() {
        singletonInstance.controls();
    }

    /**
     * Initializes the controller
     *
     * @param portMain   : main port of the controller
     * @param portRumble : port with external PS4 drivers
     */
    private DriverControls(int portMain, int portRumble) {
        super(portMain, portRumble);
    }

    /**
     * Runs the driver controls
     */
    private void controls() {
        if (getXButton()) {
            Limelight.run();
        } else {
            speedStraight = getLeftYAxis();
            speedStraight = Math.abs(speedStraight) > .1 ? speedStraight : 0;
            speedLeft = getLeftTriggerAxis();
            speedLeft = Math.abs(speedLeft) > .1 ? speedLeft : 0;
            speedRight = getRightTriggerAxis();
            speedRight = Math.abs(speedRight) > .1 ? speedRight : 0;
            Drivetrain.arcadeDrive(speedStraight, speedLeft, speedRight);
        }
    }
}