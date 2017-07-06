package ru.yanchenko.vlad.graphicsapps;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Displaying a graphics on a screen, using JPanel.
 */
public class Rendering extends JPanel {

    private Repository oRepository = Repository.getInstance();

    public static synchronized Rendering getInstance(Repository Repository) {
        if (Repository.getoDrawing() == null) {
            Repository.setoDrawing(new Rendering());
        }
        return Repository.getoDrawing();
    }

    @Override
    public void paintComponent(Graphics g) {

        //** Erasing a previously drawn bitmap 
        super.paintComponent(g);

        //** Passing through all the balls and draw them
        for (int i = 0; i < oRepository.getDotsQuantity(); i++) {
            g.drawImage(oRepository.getBalls().getBallsDestination()[i].getImage(),
                    (int) oRepository.getBalls().getBallsDestination()[i].getX(),
                    (int) oRepository.getBalls().getBallsDestination()[i].getY(),
                    this);
            g.drawImage(oRepository.getBalls().getBallsScattered()[i].getImage(),
                    (int) oRepository.getBalls().getBallsScattered()[i].getX(),
                    (int) oRepository.getBalls().getBallsScattered()[i].getY(),
                    this);
        }

    }
}
