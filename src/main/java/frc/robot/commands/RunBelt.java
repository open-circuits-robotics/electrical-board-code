package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConveyorSubsystem;

public class RunBelt extends Command{
    ConveyorSubsystem belt; 
    Timer timer;

    public RunBelt(ConveyorSubsystem belt){
        this.belt = belt;
        timer = new Timer();
    }

    @Override
    public void initialize(){
        belt.beltSpeed(1);
        timer.start();
    }

    @Override
    public void end(boolean interrupted){
        belt.beltSpeed(0);
    }

    @Override
    public boolean isFinished(){
        return timer.get() >= 3;
    }
    
}
