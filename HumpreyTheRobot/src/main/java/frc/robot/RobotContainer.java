package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import java.lang.Math;

public class RobotContainer {

    public Joystick left = new Joystick(0);
    public Joystick center = new Joystick(1);
    public Joystick right = new Joystick(2);
    final JoystickButton speedBoost = new JoystickButton(center, 3);
    final JoystickButton teleopCargoLock = new JoystickButton(left, 3);
    final JoystickButton aimBotToggle = new JoystickButton(right, 3);

    public double speed, direction;
    public boolean boostEnabled;
    public boolean autoCargoEnabled;
    public boolean aimBotEnabled;
    final JoystickButton shootInitiate = new JoystickButton(right, 3);

    public double inputShooterSpeed;//The inputShooterSpeed will eventually be deleted and its function replaced by a distance lookup table
    public boolean shootInitiated;

    public RobotContainer(){
        readButtons();
    }

    public void readButtons(){
        speed = center.getY();
        direction = left.getX();
        boostEnabled = speedBoost.get();
        autoCargoEnabled = teleopCargoLock.get();
        aimBotEnabled = aimBotToggle.get();
        shootInitiated = shootInitiate.get();
        inputShooterSpeed = Math.abs(right.getY());//Absolute value to prevent the wheel from spinning backwards which could cause... idk what could happen, but probably not good
    }
}





