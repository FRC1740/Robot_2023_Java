// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.Relay;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {
  //private final PWMSparkMax m_leftMotor = new PWMSparkMax(0);
  //private final PWMSparkMax m_rightMotor = new PWMSparkMax(1);
  private final CANSparkMax m_leftMotor = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
  private final CANSparkMax m_rightMotor = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
  private final CANSparkMax m_leftMotorb = new CANSparkMax(5, CANSparkMaxLowLevel.MotorType.kBrushless);
  private final CANSparkMax m_rightMotorb = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  // private final Joystick m_stick = new Joystick(0);
  private final XboxController m_driver = new XboxController(0);
  private final XboxController m_codriver = new XboxController(1);
  private final Relay m_relay = new Relay(0);

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    // m_rightMotor.setInverted(true);
    m_leftMotorb.follow(m_leftMotor);
    m_rightMotorb.follow(m_rightMotor);
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    // m_robotDrive.arcadeDrive(-m_stick.getY(), m_stick.getX());
    // "Mario Cart" Drive Mode
    m_robotDrive.arcadeDrive(m_driver.getLeftTriggerAxis() - m_driver.getRightTriggerAxis(), -m_driver.getRightX(), true);

    if (m_codriver.getAButton()) {
      m_relay.set(Relay.Value.kForward);
    } else {
      m_relay.set(Relay.Value.kOff);
    }
  }
}
