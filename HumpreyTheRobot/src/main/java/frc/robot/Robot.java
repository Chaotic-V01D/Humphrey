// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.subsystems.HumphreyShooter;
import frc.robot.subsystems.drivehumphrey;
import edu.wpi.first.cameraserver.CameraServer;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  
  public static drivehumphrey drivehumphrey = new drivehumphrey();
  public RobotContainer m_robotContainer;
  public int autoRuns = 0;
  public static final double reverseTimeS = 3;
  public static final double autonomousPeriodTimeS = 0.02;
  public static final double autonomousReverseCycles = reverseTimeS / autonomousPeriodTimeS;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  
  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    drivehumphrey.leftSide.setInverted(true);
    CameraServer.startAutomaticCapture();
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    System.out.println("autoinit");
    autoRuns = 0;
  }

  @Override
  public void autonomousPeriodic() {
    autoRuns += 1;
    if (autoRuns <= autonomousReverseCycles) {
      drivehumphrey.drive(0.75, 0);
    }
    else {
      drivehumphrey.drive(0, 0);
    }

  }

  @Override
  public void teleopInit() {
    System.out.println("starting teleop");

  }

  @Override
  public void teleopPeriodic() {
    m_robotContainer.readButtons();
    if(m_robotContainer.boostEnabled) {
      drivehumphrey.drive(m_robotContainer.speed, m_robotContainer.direction);
    }
    else{
      drivehumphrey.drive(m_robotContainer.speed * 0.75, m_robotContainer.direction);
    }
    if(m_robotContainer.shootInitiated) {
      //TODO: change the shoot input to a look up table
      HumphreyShooter.shoot(m_robotContainer.inputShooterSpeed);//This right now just sets the variable shooter wheel to the input from the third joystick
    }
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {
    m_robotContainer.readButtons();
    drivehumphrey.drive(m_robotContainer.speed, m_robotContainer.direction);
  }
}






