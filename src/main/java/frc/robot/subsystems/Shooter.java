package frc.robot.subsystems;

import java.util.function.BooleanSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
    private CANSparkMax shooterLowerMotor;
    private CANSparkMax shooterUpperMotor;
    private CANSparkMax shooterIndexMotor;

    private boolean slow;
    private boolean fast;

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

        slow = false;
        fast = false;
    }

    public void setSlow(boolean slow) {
        this.slow = slow;
    }

    public void setFast(boolean fast) {
        this.fast = fast;
    }

    public void shoot(double speed) {
        shooterLowerMotor.setVoltage(-1*speed);
        shooterUpperMotor.setVoltage(speed);
        shooterIndexMotor.setVoltage(-1*speed);
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

    public Command runSetSlow(boolean slow) {
        return runOnce(() -> setSlow(slow));
    }

    public Command runSetFast(boolean fast) {
        return runOnce(() -> setFast(fast));
    }

    public Command runShooter(BooleanSupplier slow, BooleanSupplier fast) {
        return run(() -> {
            if(fast.getAsBoolean()) {
                shoot(6);
            } else if(slow.getAsBoolean()) {
                shoot();
            } else {
                rest();
            }
        });
    }

    public Command runShoot() {
        return runOnce(() -> shoot());
    }

        public Command runShoot(double speed) {
            return runOnce(() -> shoot(speed));

        }

    public Command runRest() {
        return runOnce(() -> rest());
    }

    @Override
    public void periodic() {
        if(fast) {
            shoot(6);
        } else if(slow) {
            shoot();
        } else {
            rest();
        }
    }
}