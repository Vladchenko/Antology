package ru.yanchenko.vlad.graphicsapps.generics;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * Counting and displaying a frames per second (FPS) made by graphics rendering on a JFrame, using JLabel.
 * Created by Влад on 06.07.2017.
 */
public class FPS {

    // Default position of a FPS label by y ordinate on a screen.
    private static final int FPS_LABEL_Y_DEFAULT_ORDINATE = 25;
    // Text that is shown in a FPS label at the beginning.
    private static final String FPS_LABEL_BEGIN_TEXT = "FPS:";
    private static final Color FPS_LABEL_DEFAULT_COLOR = new Color(150, 150, 150);

    // Following 2 fields stand for a size of a FPS label in a JFrame (screen).
    private int width = 60;
    private int height = 15;
    // Updating FPS label on a JFrame every nth milliseconds.
    private int updateTimeOut = 200;     // in millisecs
    // Number of frames appeared in one second.
    private int framesCount;
    // label where fps is shown
    private JLabel label = new JLabel(FPS_LABEL_BEGIN_TEXT);
    // Time span in milliseconds, when a count of a frames begins.
    private long beginTime;
    // Time span in milliseconds, when a count of a frames ends.
    private long endTime = (new Date()).getTime();
    // Color of a JLabel that displays the FPS value.
    private Color color = FPS_LABEL_DEFAULT_COLOR;

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
                FPS_LABEL_Y_DEFAULT_ORDINATE,
                width,
                height);
    }

    /**
     * Counting a number of frames appeared on a screen in one second.
     */
    public void countFPS() {
        framesCount++;
        if (endTime - beginTime >= updateTimeOut) {
            beginTime = (new Date()).getTime();
            //** Such a computation is required due to a variative fps measurement time
            label.setText(FPS_LABEL_BEGIN_TEXT + framesCount * (1000 / updateTimeOut));
            framesCount = 0;
        }
    }

    //region getters and setters
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

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color colorFPS) {
        this.color = colorFPS;
    }

    public int getFramesCount() {
        return framesCount;
    }

    public void setFramesCount(int framesCount) {
        this.framesCount = framesCount;
    }
    //endregion
}
