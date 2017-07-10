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
import ru.yanchenko.vlad.graphicsapps.logic.Geometry;

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

            //** When there is no any scattered ball selected
            if (balls.getBallSelected().equals(
                    balls.getBallDummy())) {

                if (e.getWheelRotation() < 0) {
                    
                    //** Increasing a size of a destination balls figure
                    balls.setRadius(
                            balls.getRadius() + 15);
                    balls.setRadiusFloating(
                            balls.getRadiusFloating() + 3);
                    // Rescattering a balls respectively to a current figure
                    balls.scatterTheBalls(
                            balls.getBallsDestination(),
                            0,
                            screenWidth,
                            screenHeight,
                            balls.getBallsImages().getImgScattered(),
                            !balls.isRoam());
                }

                if (e.getWheelRotation() > 0) {
                    //** Decreasing a size of a destination balls figure
                    balls.setRadius(
                            balls.getRadius() - 15);
                    balls.setRadiusFloating(
                            balls.getRadiusFloating() - 3);
                    // Rescattering a balls respectively to a current figure
                    balls.scatterTheBalls(
                            balls.getBallsDestination(),
                            0,
                            screenWidth,
                            screenHeight,
                            balls.getBallsImages().getImgScattered(),
                            !balls.isRoam());
                }
                //** When there is some ball selected
            } else {
                if (e.getWheelRotation() < 0) {
                    Geometry.computePolarCoors(
                            balls);
                    balls.setAngle(0);
                    for (int i = 0; i < balls.
                            getBallsMetaData().length; i++) {
                        balls.getBallsMetaData()[i].setRadius(
                                balls.
                                getBallsMetaData()[i].getRadius()
                                + balls.
                                getBallsMetaData()[i].getRadius() / 20);

                    }
                    Geometry.computeDekartCoors(
                            balls);
                }
                if (e.getWheelRotation() > 0) {
                    Geometry.computePolarCoors(
                            balls);
                    balls.setAngle(0);
                    for (int i = 0; i < balls.
                            getBallsMetaData().length; i++) {
                        balls.getBallsMetaData()[i].setRadius(
                                balls.
                                getBallsMetaData()[i].getRadius()
                                - balls.
                                getBallsMetaData()[i].getRadius() / 20);

                    }
                    Geometry.computeDekartCoors(
                            balls);
                }
            }
        }
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="When SHIFT is pressed">
        if (keyboard.isShiftPressed()) {

            //** When there is no any scattered ball selected
            if (balls.getBallSelected().equals(
                    balls.getBallDummy())) {

                if (e.getWheelRotation() < 0) {
                    //** Changing a figure of a destination balls
                    balls.setScatterMode(
                            balls.getScatterMode() + 1
                    );
                    if (balls.getScatterMode() > 24) {
                        balls.setScatterMode(0);
                    }
                    balls.scatterTheBalls(
                            balls.getBallsDestination(),
                            0,
                            screenWidth,
                            screenHeight,
                            balls.getBallsImages().getImgScattered(),
                            !balls.isRoam());
                }
                if (e.getWheelRotation() > 0) {
                    //** Changing a figure of a destination balls
                    balls.setScatterMode(
                            balls.getScatterMode() - 1
                    );
                    if (balls.getScatterMode() < 0) {
                        balls.setScatterMode(24);
                    }
                    balls.scatterTheBalls(
                            balls.getBallsDestination(),
                            0,
                            screenWidth,
                            screenHeight,
                            balls.getBallsImages().getImgScattered(),
                            !balls.isRoam());
                }
            } //** When there is some scattered ball selected
            else {
                //** Changing a pattern of a scattered balls spreading
                if (e.getWheelRotation() < 0) {
                    balls.setScatterMode(
                            balls.getScatterMode() + 1
                    );
                    if (balls.getScatterMode() > 25) {
                        balls.setScatterMode(0);
                    }
                }
                if (e.getWheelRotation() > 0) {
                    balls.setScatterMode(
                            balls.getScatterMode() - 1
                    );
                    if (balls.getScatterMode() < 0) {
                        balls.setScatterMode(25);
                    }
                }
                balls.scatterTheBalls(
                            balls.getBallsScattered(),
                            0,
                            screenWidth,
                            screenHeight,
                            balls.getBallsImages().getImgScattered(),
                            !balls.isRoam());
            }
            balls.computeMetaData(true);
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
