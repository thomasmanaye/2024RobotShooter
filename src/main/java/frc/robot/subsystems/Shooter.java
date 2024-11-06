package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.XboxController;

public class Shooter {
    XboxController controller = new XboxController (0);
    TalonFX shooterMotor = new TalonFX(13);
    TalonFX bottomMotor = new TalonFX(14);

enum ShooterStates {
    SHOOTING,
    INTAKING,
    IDLE
}
ShooterStates state = ShooterStates.IDLE;
public void ShooterPeriodic() {
    if (state == ShooterStates.IDLE) {
        shooterMotor.set(0);
        bottomMotor.set(0);

        if (controller.getXButtonPressed()) {
            state = ShooterStates.SHOOTING;
        }
        if (controller.getAButtonPressed()) {
            state = ShooterStates.INTAKING;
        }
    if (state == ShooterStates.INTAKING) {
        shooterMotor.set(-1);
        bottomMotor.set(-1);
    
    if (controller.getBButtonPressed() || bottomMotor.getSupplyCurrent().getValueAsDouble() > 35) {
        state = ShooterStates.IDLE;
        }
    }   
    if (state == ShooterStates.SHOOTING) {
        shooterMotor.set(1);
        
        if (controller.getBButtonPressed()) {
        state = ShooterStates.IDLE;
        }
        
        if (shooterMotor.getVelocity().getValueAsDouble() > 110) {
            bottomMotor.set(1);

        }

    }


    }
    
}
}
