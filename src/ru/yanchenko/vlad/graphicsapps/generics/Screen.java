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
    private RenderingComponents renderingComponents;

    public Screen() {
        clrWindowBackground = COLOR_SCREEN_BACKGROUND;
        frame = new JFrame();
        screenCenter = new Point();
//                JLabel(imgRenderButton);
//        renderingComponents = new RenderingComponents();
    }

    //** Adding a listeners to a frame
    private void addListeners(JFrame frame) {
        frame.addKeyListener(new FrameKeyListener());
        frame.addMouseListener(new FrameMouseListener());
        frame.addMouseMotionListener(new FrameMouseMotionListener());
        frame.addMouseWheelListener(new FrameMouseWheelListener());
    }

    private void setScreenSizeAndCenter() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        screenCenter.x = (int) screenSize.getWidth() / 2;
        screenCenter.y = (int) screenSize.getWidth() / 2;
    }

    private void initializeFrame(Rendering rendering) {
        frame.setSize(screenWidth, screenHeight);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(rendering);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
//        frame.setBackground(clrWindowBackground);
        frame.setVisible(true);
        frame.requestFocus();
    }

    //** Initializing a JFrame
    private void initializeScreen(Rendering rendering) {
        if (!windowFrame) {
            frame.setUndecorated(true);
        }
        setScreenSizeAndCenter();
        initializeFrame(rendering);
        addListeners(frame);
//        rendering.setBackground(clrWindowBackground);
        rendering.setOpaque(true);
    }

    private void initializeScreenComponents(RenderingComponents renderingComponents) {
        renderingComponents.getFps().setDefaultPosition(frame);
        renderingComponents.getFps().getLabel().setForeground(renderingComponents.getFps().getColor());
        renderingComponents.getRenderButton().getView().setSize(
                renderingComponents.getRenderButton().getImage().getIconWidth(),
                renderingComponents.getRenderButton().getImage().getIconHeight());
        // TODO - Is this statement correct ?
        renderingComponents.getRenderButton().getView().addMouseListener(
                new RenderButtonMouseListener(renderingComponents.getRenderButton()));
    }

    private void addComponentsToScreen() {
        frame.add(renderingComponents.getFps().getLabel());
        frame.add(renderingComponents.getRenderButton().getView());
    }

    //** Initializing some data - images, frame, adding listeners.
    public void initializeData(Rendering rendering) {
//
//        this.initializeScreen(oFrmDrawingBoard);
//
//        this.populateStringMap(getMapStrImages());
////        oRepository.getoFrmDrawingBoard().setBackground(oRepository.getClrWindowBackground());
//
//        oRendering.setBackground(
//                oRepository.getClrWindowBackground()
//        );
        initializeScreen(rendering);
        initializeScreenComponents(renderingComponents);
        addComponentsToScreen();
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

}
