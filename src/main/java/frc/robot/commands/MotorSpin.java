package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Motor;

public class MotorSpin extends Command {

    double robotSpeed = 0.5;    
    
    Motor theMotor;

    protected final DoubleSupplier stickY;

    public MotorSpin(Motor theBestMotor, DoubleSupplier inStickY){
        stickY = inStickY;
        theMotor = theBestMotor;
        addRequirements(theMotor);
        System.out.println("con MotorSpin");

    }

    @Override
    public void execute(){
        robotSpeed = (SmartDashboard.getNumber("DB/Slider 0", 2.5) + 1) / 4;

        theMotor.driveMotor(stickY.getAsDouble() * robotSpeed);
    }
/* 
    @Override
    public void end(boolean i) {
        theMotor.driveMotor(0);
    }
        */
    @Override
    public boolean isFinished(){
        return false;
    }
        

}
