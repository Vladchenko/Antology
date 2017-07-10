/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.yanchenko.vlad.graphicsapps.listeners;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.ImageIcon;
import ru.yanchenko.vlad.graphicsapps.Repository;
import ru.yanchenko.vlad.graphicsapps.generics.Keyboard;
import ru.yanchenko.vlad.graphicsapps.generics.balls.BallsLogic;

/**
 *
 * @author Влад
 */
public class FrameMouseWheelListener implements MouseWheelListener {

    private Repository repository = Repository.getInstance();
    private Keyboard keyboard;
    private BallsLogic ballsLogic;

    public FrameMouseWheelListener() {
        keyboard = new Keyboard();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        //<editor-fold defaultstate="collapsed" desc="When no ALT / CTRL / SHIFT keys pressed">
        if (!repository.isKeyAlt()
                && !repository.isKeyCtrl()
                && !repository.isKeyShift()) {

            /**
             * This operation rotates a scattered balls around a selected one.
             */
            ballsLogic.rotateBallsAroundOne(e.getWheelRotation(),
                    repository.getScreen().getScreenWidth(),
                    repository.getScreen().getScreenHeight());
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="When CTRL is pressed">
        if (repository.isKeyCtrl()) {

            //** When there is no any scattered ball selected
            if (repository.getBalls().getBallSelected().equals(
                    repository.getBalls().getBallDummy())) {

                if (e.getWheelRotation() < 0) {
                    
                    //** Increasing a size of a destination balls figure
                    repository.getBalls().setRadius(
                            repository.getBalls().getRadius() + 15);
                    repository.getBalls().setRadiusFloating(
                            repository.getBalls().getRadiusFloating() + 3);
                    repository.getBalls().scatterTheBalls(
                            repository.getBalls().getBallsDestination(),
                            0,
                            repository.getScreen().getScreenWidth(),
                            repository.getScreen().getScreenHeight(),
                            repository.getBallsImages().getImgScattered(),
                            !repository.isRoam());
                }

                if (e.getWheelRotation() > 0) {
                    //** Decreasing a size of a destination balls figure
                    repository.getBalls().setRadius(
                            repository.getBalls().getRadius() - 15);
                    repository.getBalls().setRadiusFloating(
                            repository.getBalls().getRadiusFloating() - 3);
                    repository.getBalls().scatterTheBalls(
                            repository.getBalls().getBallsDestination(),
                            0,
                            repository.getScreen().getScreenWidth(),
                            repository.getScreen().getScreenHeight(),
                            repository.getBallsImages().getImgScattered(),
                            !repository.isRoam());
                }
                //** When there is some ball selected
            } else {
                if (e.getWheelRotation() < 0) {
                    repository.getoLogic().computePolarCoors(
                            repository.getBalls());
                    repository.getBalls().setAngle(0);
                    for (int i = 0; i < repository.getBalls().
                            getBallsMetaData().length; i++) {
                        repository.getBalls().getBallsMetaData()[i].setRadius(
                                repository.getBalls().
                                getBallsMetaData()[i].getRadius()
                                + repository.getBalls().
                                getBallsMetaData()[i].getRadius() / 20);

                    }
                    repository.getoLogic().computeDekartCoors(
                            repository.getBalls());
                }
                if (e.getWheelRotation() > 0) {
                    repository.getoLogic().computePolarCoors(
                            repository.getBalls());
                    repository.getBalls().setAngle(0);
                    for (int i = 0; i < repository.getBalls().
                            getBallsMetaData().length; i++) {
                        repository.getBalls().getBallsMetaData()[i].setRadius(
                                repository.getBalls().
                                getBallsMetaData()[i].getRadius()
                                - repository.getBalls().
                                getBallsMetaData()[i].getRadius() / 20);

                    }
                    repository.getoLogic().computeDekartCoors(
                            repository.getBalls());
                }
            }
        }
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="When SHIFT is pressed">
        if (repository.isKeyShift()) {

            //** When there is no any scattered ball selected
            if (repository.getBalls().getBallSelected().equals(
                    repository.getBalls().getBallDummy())) {

                if (e.getWheelRotation() < 0) {
                    //** Changing a figure of a destination balls
                    repository.getBalls().setScatterMode(
                            repository.getBalls().getScatterMode() + 1
                    );
                    if (repository.getBalls().getScatterMode() > 24) {
                        repository.getBalls().setScatterMode(0);
                    }
                    repository.getBalls().scatterTheBalls(
                            repository.getBalls().getBallsDestination(),
                            0,
                            repository.getScreen().getScreenWidth(),
                            repository.getScreen().getScreenHeight(),
                            repository.getBallsImages().getImgScattered(),
                            !repository.isRoam());
                }
                if (e.getWheelRotation() > 0) {
                    //** Changing a figure of a destination balls
                    repository.getBalls().setScatterMode(
                            repository.getBalls().getScatterMode() - 1
                    );
                    if (repository.getBalls().getScatterMode() < 0) {
                        repository.getBalls().setScatterMode(24);
                    }
                    repository.getBalls().scatterTheBalls(
                            repository.getBalls().getBallsDestination(),
                            0,
                            repository.getScreen().getScreenWidth(),
                            repository.getScreen().getScreenHeight(),
                            repository.getBallsImages().getImgScattered(),
                            !repository.isRoam());
                }
            } //** When there is some scattered ball selected
            else {
                //** Changing a pattern of a scattered balls spreading
                if (e.getWheelRotation() < 0) {
                    repository.getBalls().setScatterMode(
                            repository.getBalls().getScatterMode() + 1
                    );
                    if (repository.getBalls().getScatterMode() > 25) {
                        repository.getBalls().setScatterMode(0);
                    }
                }
                if (e.getWheelRotation() > 0) {
                    repository.getBalls().setScatterMode(
                            repository.getBalls().getScatterMode() - 1
                    );
                    if (repository.getBalls().getScatterMode() < 0) {
                        repository.getBalls().setScatterMode(25);
                    }
                }
                repository.getBalls().scatterTheBalls(
                            repository.getBalls().getBallsScattered(),
                            0,
                            repository.getScreen().getScreenWidth(),
                            repository.getScreen().getScreenHeight(),
                            repository.getBallsImages().getImgScattered(),
                            !repository.isRoam());
            }
            repository.getBalls().computeMetaData(true);
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
            repository.getoLogic().setScatteredBallsLook(
                    repository.getBalls().getBallsScattered(),
                    repository.getBallsImages().getImgScattered());
            //** Changing an image of a selected ball to be back, yellow
            repository.getBalls().getBallSelected().setImage(
                repository.getBallsImages().getImgSelected());
            /**
             * Setting an image for a RenderButton to be Start, for a 
             * convergence could run consequently.
             */
            repository.setImgRenderButton(
                    new ImageIcon(repository.getStrImgStartInitial()));
            //** Assigning an image to a renderButton 
            repository.getLblRenderButton().setIcon(
                                repository.getImgRenderButton());
        }
        
//        System.out.println(repository.getTmrRendering().isRunning());

        //** Computing metadata, only in case a "convergence" process is running
        repository.getBalls().computeMetaData(
                !repository.isRoam());
//        repository.getBalls().printMetaData();
    }
}
