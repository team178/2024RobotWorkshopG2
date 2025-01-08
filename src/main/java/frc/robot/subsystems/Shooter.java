package frc.robot.subsystems;

import java.util.function.BooleanSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
    private CANSparkMax shooterLowerMotor;
    private CANSparkMax shooterUpperMotor;
    private CANSparkMax shooterIndexMotor;

    private boolean in;
    private boolean out;

    private DigitalInput photosensor;

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

        photosensor = new DigitalInput(ShooterConstants.kPhotosensorDIO);

        in = false;
        out = false;
    }

    public void setIn(boolean slow) {
        this.in = slow;
    }

    public void setOut(boolean fast) {
        this.out = fast;
    }

    public boolean getPhotosensor() {
        return !photosensor.get();
    }

    public void shoot(double speed) {
        shooterLowerMotor.setVoltage(-1*speed);
        shooterUpperMotor.setVoltage(speed);
        shooterIndexMotor.setVoltage(-1*speed);
    }

    public Command runShoot(double speed) {
        return runOnce(() -> shoot(speed));
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("shooter/slow", in);
        SmartDashboard.putBoolean("shooter/fast", out);
        SmartDashboard.putNumber("speed", shooterUpperMotor.getEncoder().getVelocity());
        SmartDashboard.putBoolean("photosensor", !photosensor.get()); // not because flipped
    }
}