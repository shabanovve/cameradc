package ru.cameradc.gpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.cameradc.Direction;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

/**
 * Created by Vladimir Shabanov on 22/06/15.
 */

@Component
@Scope("singleton")
public class GpioMover {
    private Logger logger = Logger.getLogger(GpioMover.class.toString());
    private List<Instruction> instructions;
    private Iterator<Instruction> iterator;
    private boolean motionAlreadyStarted = false;
    private GpioController gpio;
    private HashMap<Direction,GpioPinDigitalOutput> pins;

    @PostConstruct
    private void init(){
        instructions = new CopyOnWriteArrayList<Instruction>();
        iterator = instructions.iterator();
        gpio = GpioFactory.getInstance();

        for (Direction direction : Direction.values()){
            GpioPinDigitalOutput output = gpio.provisionDigitalOutputPin(direction.getPinId());
            pins.put(direction,output);
        }
    }

    public void addInstruction(Instruction instruction){
        instructions.add(instruction);

        if (motionAlreadyStarted) {
            return;
        } else {
            motionAlreadyStarted = true;
            start();
        }
    }

    private void start() {
        new Thread(new MotionThread()).start();
    }

    private class MotionThread implements Runnable{

        @Override
        public void run() {
            while (instructions.iterator().hasNext()){
                Instruction currentInstruction = instructions.iterator().next();

                GpioPinDigitalOutput output = pins.get(currentInstruction.getDirection());
                for (int i = 0; i < currentInstruction.getStep(); i++) {
                    output.low();
                    pause();
                    output.high();
                    logger.info("dr-dr-dr " + currentInstruction.getDirection().name());
                }
                instructions.remove(currentInstruction);
            }
            motionAlreadyStarted = false;

        }

        private void pause() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
