package ru.cameradc;


import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

/**
 * Created by Vladimir Shabanov on 22/06/15.
 */
public enum Direction {
    UP(RaspiPin.GPIO_00),DOWN(RaspiPin.GPIO_01),LEFT(RaspiPin.GPIO_02),RIGHT(RaspiPin.GPIO_03);

    private Pin pinId;

    public Pin getPinId() {
        return pinId;
    }

    Direction(Pin pinId) {
        this.pinId = pinId;
    }
}
