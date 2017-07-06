package ru.yanchenko.vlad.graphicsapps.generics;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * Counting and displaying a frames per second (FPS) made by graphics rendering on a JFrame, using JLabel.
 * Created by Влад on 06.07.2017.
 */
public class FPS {

    //** Following 2 fields are in charge of a size of a FPS label in a JFrame (screen).
    private int width = 60;
    private int height = 15;
    //** Updating FPS label on a JFrame every nth milliseconds.
    private int updateTimeOut = 200;     // in millisecs
    private JLabel label = new JLabel("FPS:");
    //** Time span in milliseconds, when a count of a frames begins.
    private long countBeginTime;
    //** Time span in milliseconds, when a count of a frames ends.
    private long countEndTime = (new Date()).getTime();
    //** Color of a JLabel that displays the FPS value.
    private Color color = new Color(150, 150, 150);

    public FPS() { }

    /**
     * Setting a position of FPS label on a JFrame, with a specific coordinates.
     * @param positionByX - x ordinate on a JFrame
     * @param positionByY - y ordinate on a JFrame
     * @param frame - JFrame on which a FPS label is to be drawn.
     */
    public void setFPSLabelPosition(int positionByX, int positionByY, JFrame frame) {
        label.setBounds(
                frame.getWidth() - width + positionByX,
                5 + positionByY,
                width,
                height);
    }

    /**
     * Setting a position of FPS label on a JFrame, with a default coordinate. That is an upper right corner.
     * @param frame - JFrame on which a FPS label is to be drawn.
     */
    public void setFPSLabelDefaultPosition(JFrame frame) {
        label.setBounds(
                frame.getWidth() - width,
                5,
                width,
                height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getUpdateTimeOut() {
        return updateTimeOut;
    }

    public void setUpdateTimeOut(int updateTimeOut) {
        this.updateTimeOut = updateTimeOut;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public long getCountBeginTime() {
        return countBeginTime;
    }

    public void setCountBeginTime(long countBeginTime) {
        this.countBeginTime = countBeginTime;
    }

    public long getCountEndTime() {
        return countEndTime;
    }

    public void setCountEndTime(long countEndTime) {
        this.countEndTime = countEndTime;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color colorFPS) {
        this.color = colorFPS;
    }
}
