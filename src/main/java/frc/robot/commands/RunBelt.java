package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConveyorSubsystem;

public class RunBelt extends Command{
    ConveyorSubsystem belt; 
    Timer timer;
    BooleanSupplier aPressed;

    public RunBelt(ConveyorSubsystem belt, BooleanSupplier aPressed){
        this.belt = belt;
        timer = new Timer();
        this.aPressed = aPressed;
    }

    @Override
    public void initialize(){
        belt.beltSpeed(1);
        timer.restart();
    }

    @Override
    public void end(boolean interrupted){
        belt.beltSpeed(0);
    }

    @Override
    public boolean isFinished(){
        if (aPressed.getAsBoolean()){
            return false;
        }
        System.out.println(timer.get());
        return timer.get() >= 3;
    }
    
}
