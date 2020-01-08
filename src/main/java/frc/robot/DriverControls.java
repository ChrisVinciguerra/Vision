package frc.robot;

public class DriverControls extends PS4Controller {

    public static DriverControls singletonInstance = new DriverControls(0, 2);

    private double speedStraight, speedLeft, speedRight, p = .00001, i = 0.000000001;

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
            Limelight.run(false);
        } else {
            speedStraight = getLeftYAxis();
            speedStraight = Math.abs(speedStraight) > .1 ? speedStraight : 0;
            speedLeft = getLeftTriggerAxis();
            speedLeft = Math.abs(speedLeft) > .1 ? speedLeft : 0;
            speedRight = getRightTriggerAxis();
            speedRight = Math.abs(speedRight) > .1 ? speedRight : 0;
            Drivetrain.arcadeDrive(speedStraight, speedLeft, speedRight);
        }
        if(getLeftBumperButton()){
            p*=1.05;
            Limelight.setP(p);
            System.out.println("1: "+p);
        }
        if(getRightBumperButton()){
            p/=1.05;
            Limelight.setP(p);
            System.out.println("2: "+p);
        }
        if(getSquareButton()){
            i*=1.05;
            Limelight.setI(i);
            System.out.println("3: "+i);  
        }
        if(getCircleButton()){
            i/=1.05;
            Limelight.setI(i);
            System.out.println("4: " +i);
        }
    }
}