/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.yanchenko.vlad.graphicsapps.listeners;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.ImageIcon;
import ru.yanchenko.vlad.graphicsapps.generics.Keyboard;
import ru.yanchenko.vlad.graphicsapps.generics.balls.Balls;

/**
 *
 * @author Влад
 */
public class FrameMouseWheelListener implements MouseWheelListener {

//    private Repository repository = Repository.getInstance();
    private int screenWidth;
    private int screenHeight;
    private Keyboard keyboard;
    private Balls balls;

    public FrameMouseWheelListener(Balls balls, Keyboard keyboard, int screenWidth, int screenHeight) {
        this.keyboard = keyboard;
        this.balls = balls;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        //<editor-fold defaultstate="collapsed" desc="When no ALT / CTRL / SHIFT keys pressed">
        if (!keyboard.isAltPressed()
                && !keyboard.isCtrlPressed()
                && !keyboard.isShiftPressed()) {

            /**
             * This operation rotates a scattered balls around a selected one.
             */
            balls.getBallsLogic().rotateBallsAroundOne(balls, e.getWheelRotation(),
                    screenWidth,
                    screenHeight);
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="When CTRL is pressed">
        if (keyboard.isCtrlPressed()) {
            balls.getBallsLogic().changeSizeAndRescatterWhenMouseWheelMoved(balls, e.getWheelRotation(), screenWidth,
                    screenHeight);
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="When SHIFT is pressed">
        if (keyboard.isShiftPressed()) {
            balls.getBallsLogic().rescatterWhenMouseWheelMoved(balls, e.getWheelRotation(), screenWidth, screenHeight);
        }
        //</editor-fold>
        
        /**
         * If a scattered balls are converged (that could be defined by 
         * checking at least one ball's image), make them to have scattered 
         * images (look) back again.
         */
        if (repository.getTmrAfterConvergence().isRunning()) {
            //** Stopping this timer
            repository.getTmrAfterConvergence().stop();
            repository.getTmrRendering().start();
            balls.getBallsLogic().setScatteredBallsLook(
                    balls.getBallsScattered(),
                    balls.getBallsImages().getImgScattered());
            //** Changing an image of a selected ball to be back, yellow
            balls.getBallSelected().setImage(
                balls.getBallsImages().getImgSelected());
            /**
             * Setting an image for a RenderButton to be Start, for a 
             * convergence could run consequently.
             */
            .setImgRenderButton(
                    new ImageIcon(repository.getStrImgStartInitial()));
            //** Assigning an image to a renderButton 
            repository.getLblRenderButton().setIcon(
                                repository.getImgRenderButton());
        }
        
//        System.out.println(repository.getTmrRendering().isRunning());

        //** Computing metadata, only in case a "convergence" process is running
        balls.computeMetaData(
                !balls.isRoam());
//        balls.printMetaData();
    }
}
