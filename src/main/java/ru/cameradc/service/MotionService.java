package ru.cameradc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cameradc.Direction;
import ru.cameradc.gpio.GpioMover;
import ru.cameradc.bean.Motion;
import ru.cameradc.gpio.Instruction;

import java.util.logging.Logger;

/**
 * Created by Vladimir Shabanov on 22/06/15.
 */
@Service
public class MotionService {
        private Logger logger = Logger.getLogger(MotionService.class.toString());

    @Autowired
    private GpioMover mover;

    public void move(Motion motion){
        logger.info(motion.getDirection());

        Instruction instruction = new Instruction();
        instruction.setDirection(Direction.valueOf(motion.getDirection()));
        instruction.setStep(Integer.decode(motion.getStep()));

        mover.addInstruction(instruction);
    }
}
