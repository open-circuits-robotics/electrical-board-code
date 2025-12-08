package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;

public class ConveyorSubsystem {
    Spark beltMotor;

    public ConveyorSubsystem(){
        beltMotor = new Spark(0);
    }

    public void beltSpeed(double speed){
        beltMotor.set(speed);
    }
}
