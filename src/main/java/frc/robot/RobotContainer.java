// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


//OK HERE'S HOW TO SIMULATE THE CODE
//because there's no way M is gonna remember this tomorrow
//SO FIRSTLY 
//you're going to go to build.gradle and look for def includeDesktopSupport
//this should equal true, if it doesn't, set it to true
//SECONDLY
//ctrl+shift+P to open the command thingy
//use command WPILib: Simulate Robot Code and that'll make it start doing stuff
//THIRDLY
//about halfway through it's gonna have two check boxes, one for a GUI and one for "use real drivers station"
//the GUI one on top should be checked, the drivers station one should not be, just double check that and hit enter
//it's gonna build and then open up a window which brings us to
//FOURTHLY
//There'll be a bunch of lil windows, important ones are the top left corner window which controls the robot's perceived state
//I tend to set this to test but use whatever you need
//FIFTHLY
//double check that there's a window talking about PWM values
//I think settings last so that should be there but if it's not, select "Hardware"
//and from that dropdown pick the PWM value one and it should show up
//These are for the Spark motors, they display their speed
//so this is how you know which controllers are being run
//and at what speed/direction
//SIXTHLY, if you HAVE an xbox controller
//There should be a "joysticks" window in which joystick 0 is set to the xbox controller
//If it isn't there, there'll be another window called "system joysticks" which lists the xbx controller
//Grab that and drag it over to joystick 0 and itll show up below, replacing "unassigned"
//SIXTHLY, if you DO NOT have an xbox controller
//go to the "system joysticks" window and click and drag "keyboard 0"
//drag and release over joystick 0 in the big "joysticks" window
//SEVENTHLY 
//you should be all set to go, if on keyboard the z button corresponds to A on a controller
//You can also simulate joysticks with keyboard keys if you go to "DS" and select "keyboard 0 settings"
//this menu is also where you pick which keyboard keys correspond to which buttons
//unhelpfully i do not know which of buttons 1 2 3 and 4 correspond with xbox a b x and y
//except that button 1 is a, since that's what z is set to
//anyway i'm done rambling, hopefully the people of the future can unravel what all this means
//my apologies for the many layers of comment, and also general unreadability of these instructions

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.MotorSpin;
import frc.robot.commands.RunBelt;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Motor;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Motor m_motor = new Motor();
  private final ConveyorSubsystem conveyor = new ConveyorSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(0);
      private final MotorSpin m_motorSpin = new MotorSpin(m_motor, () -> m_driverController.getLeftY());
      private final RunBelt runBelt = new RunBelt(conveyor, () -> m_driverController.a().getAsBoolean());


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    //m_driverController.a().whileTrue(m_motorSpin);
    m_driverController.a().onTrue(runBelt);
    

    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    m_motor.setDefaultCommand(m_motorSpin);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
