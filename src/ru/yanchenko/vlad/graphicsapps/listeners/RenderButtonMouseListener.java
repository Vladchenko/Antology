/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.yanchenko.vlad.graphicsapps.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import ru.yanchenko.vlad.graphicsapps.Repository;
import ru.yanchenko.vlad.graphicsapps.generics.RenderButton;

/**
 *
 * @author v.yanchenko
 */
public class RenderButtonMouseListener implements MouseListener {

    private Repository oRepository = Repository.getInstance();
    RenderButton renderButton;

    public RenderButtonMouseListener(RenderButton renderButton) {
        this.renderButton = renderButton;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        System.out.println("Render label mouse button clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        renderButton.changeLookRenderButtonPressed(
                oRepository.getImgRenderButton(),
                oRepository.getLblRenderButton(),
                oRepository.getMapStrImages());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        //** When convergence is off, turn it on and vice versa.
        if (!oRepository.isConverge()) {
            oRepository.setConverge(true);
        } else {
            oRepository.setConverge(false);
        }

        //<editor-fold defaultstate="collapsed" desc="If renderButton has an "Init" inscription on it">
        if (oRepository.getImgRenderButton().getDescription()
                == oRepository.getStrImgInitPressed()) {
            
            //** Stopping a convergence
            oRepository.setConverge(false);
            oRepository.setRoam(false);
            oRepository.getTmrAfterConvergence().stop();
            //            oRepository.getTmrRendering().stop();
            oRepository.getoLogic().setScatteredBallsLook(
                    oRepository.getBalls().getBallsScattered(),
                    oRepository.getBallsImages().getImgScattered());
            
            //** Randomizing scattered balls to repeat all over again
            //            oRepository.getBalls().setScatterMode(8);
            oRepository.getBalls().scatterTheBalls(
                    oRepository.getBalls().getBallsScattered(),
                    Math.PI,
                    oRepository.getScreen().getScreenWidth(),
                    oRepository.getScreen().getScreenHeight(),
                oRepository.getBallsImages().getImgScattered(), false);
            
            //** Recomputing metadata
            oRepository.getBalls().computeMetaData(
                    !oRepository.getTmrAfterConvergence().isRunning());
            oRepository.getTmrRendering().start();
        }
        //</editor-fold>

        renderButton.changeLookRenderButtonReleased(
                oRepository.getImgRenderButton(),
                oRepository.getLblRenderButton(),
                oRepository.getMapStrImages());

        //** When an "after convergence" timer is running
//        if (oRepository.getTmrAfterConvergence().isRunning()) {
//            //** Stopping this timer
//            oRepository.getTmrAfterConvergence().stop();
//            //** Changing an image of a scattered balls to be back, red
//            oRepository.getoLogic().setScatteredBallsLook(
//                    oRepository.getBalls().getBallsScattered(),
//                    oRepository.getBallsImages().getImgScattered());
//            oRepository.getBalls().randomizeBalls(
//                    oRepository.getBalls().getBallsScattered(), 
//                    oRepository.getScreenWidth(),
//                    oRepository.getScreenHeight());
//            //** Recomputeulating metadata
//            oRepository.getBalls().computeMetaData(true);
//            //** Set renderbutton to start
//            oRepository.setImgRenderButton(
//                    new ImageIcon(oRepository.getStrImgStartInitial()));
//            //** Assigning an image to a button
//            oRepository.getLblRenderButton().setIcon(
//                                oRepository.getImgRenderButton());
////            oRepository.setConverge(true);
////            oRepository.setRoam(true);
////            oRepository.getTmrRendering().start();
//        }

//        if (oRepository.getTmrRoaming().isRunning()) {
//            //** Set renderbutton to start
//            oRepository.setImgRenderButton(
//                    new ImageIcon(oRepository.getStrImgPauseInitial()));
//            //** Assigning an image to a button
//            oRepository.getLblRenderButton().setIcon(
//                                oRepository.getImgRenderButton());
//        }
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        renderButton.changeLookRenderButtonEntered(
                oRepository.getImgRenderButton(),
                oRepository.getLblRenderButton(),
                oRepository.getMapStrImages());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        renderButton.changeLookRenderButtonExited(
                oRepository.getImgRenderButton(),
                oRepository.getLblRenderButton(),
                oRepository.getMapStrImages());
    }
}
