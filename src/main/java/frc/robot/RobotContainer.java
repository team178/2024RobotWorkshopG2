// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Shooter;

public class RobotContainer {
  private CommandXboxController altController;
  private Shooter shooter;

  public RobotContainer() {
    altController = new CommandXboxController(0);
    shooter = new Shooter();
    configureBindings();
  }

  private void configureBindings() {
    // shooter.setDefaultCommand(shooter.runShooter(altController.a()::getAsBoolean, altController.b()::getAsBoolean));
    altController.a().onTrue(
      shooter.runShoot(-8)
      .andThen(new WaitUntilCommand(() -> !altController.a().getAsBoolean() || shooter.getPhotosensor()))
      .andThen(shooter.runShoot(0))
    );
    altController.b().onTrue(shooter.runShoot(8));
    altController.b().onFalse(shooter.runShoot(0));

  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }

  
}
