package ru.yanchenko.vlad.graphicsapps.generics;

/**
 * Created by vladislav on 10.07.17.
 */
public class Keyboard {

    //** Trigger used to check if Alt key is pressed.
    private boolean altPressed = false;
    //** Trigger used to check if Ctrl key is pressed.
    private boolean ctrlPressed = false;
    //** Trigger used to check if Shift key is pressed.
    private boolean shiftPressed = false;

    public boolean isAltPressed() {
        return altPressed;
    }

    public void setAltPressed(boolean altPressed) {
        this.altPressed = altPressed;
    }

    public boolean isCtrlPressed() {
        return ctrlPressed;
    }

    public void setCtrlPressed(boolean ctrlPressed) {
        this.ctrlPressed = ctrlPressed;
    }

    public boolean isShiftPressed() {
        return shiftPressed;
    }

    public void setShiftPressed(boolean shiftPressed) {
        this.shiftPressed = shiftPressed;
    }
}
