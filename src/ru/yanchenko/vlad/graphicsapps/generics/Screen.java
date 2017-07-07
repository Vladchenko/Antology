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
    private JFrame frame;
//    //** Field used to find out a size of a screen
//    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    //** Width of a screen
    private int screenWidth;
    //** Height of a screen
    private int screenHeight;
    //** Center of a plane (screen)
    private Point screenCenter;
    //** Trigger used to check if a window frame is to be seen.
    private boolean windowFrame = false;
    private FPS fps = new FPS();
    //** In charge of running / stopping / continuing convergence.
    private JLabel lblRenderButton = new JLabel(getImgRenderButton());

    public Screen() {
        clrWindowBackground = COLOR_SCREEN_BACKGROUND;
        frame = new JFrame();
        screenCenter = new Point();
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
        if (!windowFrame) {
            frame.setUndecorated(true);
        }
        frame.setLayout(null);
        frame.setBackground(clrWindowBackground);
        frame.setVisible(true);
        addListeners(frame);
        frame.requestFocus();
    }

    //** Adding a listeneres to a frame
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
        frame.add(oRepository.getLblRenderButton());
        this.lblRenderButton.setSize(
                this.imgRenderButton.getIconWidth(),
                this.imgRenderButton.getIconHeight());
        this.lblRenderButton.addMouseListener(new LabelMouseListener());
    }

    //** Initializing some data - images, frame, adding listeners.
    private void initializeData() {

        //** Put an initialization code here */
        if (oRepository.isFullScreen()) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            oRepository.pntScreenCenter.x = (int) screenSize.getWidth() / 2;
            oRepository.pntScreenCenter.y = (int) screenSize.getWidth() / 2;
            oRepository.setScreenWidth((int) screenSize.getWidth());
            oRepository.setScreenHeight((int) screenSize.getHeight());
        } else {
            oRepository.pntScreenCenter.x = oRepository.getScreenWidth() / 2;
            oRepository.pntScreenCenter.y = oRepository.getScreenHeight() / 2;
        }

        //<editor-fold defaultstate="collapsed" desc="Images instantiating">
//        try {
//            fileImg = new File(strImgCircluar);
//            imgDestination = ImageIO.read(fileImg);
//        } catch (Exception ie) {
//            System.out.println("Error: " + ie.getMessage());
//        }
//
//        try {
//            fileImg = new File(strImgRandom);
//            imgScattered = ImageIO.read(fileImg);
//        } catch (Exception ie) {
//            System.out.println("Error: " + ie.getMessage());
//        }
//
//        try {
//            fileImg = new File(strImgConverged);
//            imgConverged = ImageIO.read(fileImg);
//        } catch (Exception ie) {
//            System.out.println("Error: " + ie.getMessage());
//        }
//
//        try {
//            fileImg = new File(strImgSelected);
//            imgSelected = ImageIO.read(fileImg);
//        } catch (Exception ie) {
//            System.out.println("Error: " + ie.getMessage());
//        }
        //</editor-fold>

        this.initializeScreen(oFrmDrawingBoard);

        this.populateStringMap(getMapStrImages());
//        oRepository.getoFrmDrawingBoard().setBackground(oRepository.getClrWindowBackground());

        oRendering.setBackground(
                oRepository.getClrWindowBackground()
        );
    }

}
