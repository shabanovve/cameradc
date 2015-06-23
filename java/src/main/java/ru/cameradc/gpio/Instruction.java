package ru.cameradc.gpio;

import ru.cameradc.Direction;

/**
 * Created by Vladimir Shabanov on 22/06/15.
 */
public class Instruction {
    private Direction direction;
    private int step;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
