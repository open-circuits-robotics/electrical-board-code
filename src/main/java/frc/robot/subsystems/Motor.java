package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Motor extends SubsystemBase {
    
    Spark motorcontroller;

    public Motor(){
        motorcontroller = new Spark(1);
        //Was originally 0 but M set it to 1 for her simulation testing, CHANGE THIS IF YOU WANT TO USE IT AT 0!
        System.out.println("motor con");
    }

    public void driveMotor(double speed){
        motorcontroller.set(speed);
        System.out.println("Test!");
    }

    @Override
    public void periodic() {
        //System.out.println("Test2");
    }

}
