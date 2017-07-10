package ru.yanchenko.vlad.graphicsapps.generics;

import ru.yanchenko.vlad.graphicsapps.Rendering;
import ru.yanchenko.vlad.graphicsapps.listeners.*;

import javax.swing.*;
import java.awt.*;

/**
 *
 * Created by vladislav on 07.07.17.
 */
public class Screen {

    private static final Color COLOR_SCREEN_BACKGROUND = new Color(0, 0, 0);

//    private int ScreenWidth = 1200;
//    private int ScreenHeight = 800;
    private Color clrWindowBackground;
    // Used to incorporate a JPanel, on which to draw a graphics on.
    private JFrame frame;
    //** Width of a screen
    private int screenWidth;
    //** Height of a screen
    private int screenHeight;
    //** Center of a plane (screen)
    private Point screenCenter;
    //** Trigger used to check if a window frame is to be seen.
    private boolean windowFrame;
    private FPS fps = new FPS();
    //** In charge of running / stopping / continuing convergence.
    private RenderButton renderButton;

    public Screen() {
        clrWindowBackground = COLOR_SCREEN_BACKGROUND;
        frame = new JFrame();
        screenCenter = new Point();
        // TODO Make a renderButton first.
        renderButton = new RenderButton();
//                JLabel(imgRenderButton);
    }
    //** Initializing a JFrame
    private void initializeScreen(Rendering rendering) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenCenter.x = (int) screenSize.getWidth() / 2;
        screenCenter.y = (int) screenSize.getWidth() / 2;
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        screenSize = null;

        frame.setSize(screenWidth, screenHeight);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(rendering);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setBackground(clrWindowBackground);
        frame.setVisible(true);
        frame.requestFocus();
        if (!windowFrame) {
            frame.setUndecorated(true);
        }
        addListeners(frame);
        rendering.setBackground(clrWindowBackground);
    }

    //** Adding a listeners to a frame
    private void addListeners(JFrame frame) {
        frame.addKeyListener(new FrameKeyListener());
        frame.addMouseListener(new FrameMouseListener());
        frame.addMouseMotionListener(new FrameMouseMotionListener());
        frame.addMouseWheelListener(new FrameMouseWheelListener());
    }

    private void initializeScreenComponents() {
        fps.setFPSLabelDefaultPosition(frame);
        fps.getLabel().setForeground(fps.getColor());
        frame.add(fps.getLabel());
        frame.add(renderButton.getView());
        renderButton.getView().setSize(renderButton.getImage().getIconWidth(),
                renderButton.getImage().getIconHeight());
        renderButton.getView().addMouseListener(new LabelMouseListener());
    }

    //** Initializing some data - images, frame, adding listeners.
//    private void initializeData() {
//
//        this.initializeScreen(oFrmDrawingBoard);
//
//        this.populateStringMap(getMapStrImages());
////        oRepository.getoFrmDrawingBoard().setBackground(oRepository.getClrWindowBackground());
//
//        oRendering.setBackground(
//                oRepository.getClrWindowBackground()
//        );
//    }

}
