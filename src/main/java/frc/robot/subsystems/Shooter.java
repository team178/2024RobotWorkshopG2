package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
    private double speed;
    private CANSparkMax shooterLowerMotor;
    private CANSparkMax shooterUpperMotor;
    private CANSparkMax shooterIndexMotor;

    public Shooter() {
        shooterLowerMotor = new CANSparkMax(ShooterConstants.kLowerMotorCANID, MotorType.kBrushless);
        shooterUpperMotor = new CANSparkMax(ShooterConstants.kUpperMotorCANID, MotorType.kBrushless);
        shooterIndexMotor = new CANSparkMax(ShooterConstants.kIndexMotorCANID, MotorType.kBrushless);

        shooterLowerMotor.restoreFactoryDefaults();
        shooterUpperMotor.restoreFactoryDefaults();
        shooterIndexMotor.restoreFactoryDefaults();

        shooterLowerMotor.burnFlash();
        shooterUpperMotor.burnFlash();
        shooterIndexMotor.burnFlash();
    }

    public void shoot() {
        shooterLowerMotor.setVoltage(-3);
        shooterUpperMotor.setVoltage(3);
        shooterIndexMotor.setVoltage(-3);
    }

    public void rest() {
        shooterLowerMotor.setVoltage(0);
        shooterUpperMotor.setVoltage(0);
        shooterIndexMotor.setVoltage(0);
    }

    public Command runShoot() {
        return runOnce(() -> shoot());
    }

    public Command runRest() {
        return runOnce(() -> rest());
    }
}
