package ru.yanchenko.vlad.graphicsapps;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import ru.yanchenko.vlad.graphicsapps.generics.balls.Balls;
import ru.yanchenko.vlad.graphicsapps.generics.balls.BallsImages;
import ru.yanchenko.vlad.graphicsapps.generics.FPS;
import ru.yanchenko.vlad.graphicsapps.generics.Screen;
import ru.yanchenko.vlad.graphicsapps.listeners.FrameKeyListener;
import ru.yanchenko.vlad.graphicsapps.listeners.FrameMouseListener;
import ru.yanchenko.vlad.graphicsapps.listeners.FrameMouseMotionListener;
import ru.yanchenko.vlad.graphicsapps.listeners.FrameMouseWheelListener;
import ru.yanchenko.vlad.graphicsapps.listeners.LabelMouseListener;
import ru.yanchenko.vlad.graphicsapps.logic.Logic;

public class Repository {

    //<editor-fold defaultstate="collapsed" desc="System related fields">
//** Trigger used to check if an Alt key is pressed.
    private boolean keyAlt = false;
    //** Trigger used to check if a Ctrl key is pressed.
    private boolean keyCtrl = false;
    //** Trigger used to check if an Shift key is pressed.
    private boolean keyShift = false;
    //** Trigger used to check if a full screen is enabled.
    private boolean fullScreen = true;
    //** Trigger used to check if a window frame is to be seen.
    private boolean windowFrame = false;
//    //** Fields used for a FPS representation
//    private int framesCount;

    /**
     * When full screen is off, then following fields stand for a size of a
     * window
     */
//    private int ScreenWidth = 1200;
//    private int ScreenHeight = 800;
    private Color clrWindowBackground = new Color(0, 0, 0);
    private FPS fps = new FPS();
    private static Repository repository;
    private static frmDrawingBoard oFrmDrawingBoard;
    private static Logic logic;
    private static Rendering rendering;
    private Screen screen = new Screen();
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="User related fields">
    /**
     * Put to this area a fields that has to do with a problem realised in a 
     * program itself.
     */
    private boolean converge = false;
    private boolean roam = false;
    //** Number of balls present in an array.
    private int ballsQuantity = 800;
    //** Field used to find out a size of a screen
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    //** Width of a screen
    private int screenWidth = (int) screenSize.getWidth();
    //** Height of a screen
    private int screenHeight = (int) screenSize.getHeight();
    //** Center of a plane (screen)
    private Point pntScreenCenter = new Point(screenWidth / 2, screenHeight / 2);
    /**
     * Timer that is in charge of computations done while balls are converging,
     * i.e. scatterred balls going towards the destination balls.
     */
    private Timer tmrRendering;
    /**
     * Timer that is in charge of computations done after balls are converged.
     * In fact, it makes balls roam around the plane, with its borders bounced
     * off.
     */
    private Timer tmrAfterConvergence;
    //** Images for a balls
    private BallsImages ballsImages = new BallsImages();
    /**
     * This field holds a selected ball, dummy ball, destination and 
     * scattered balls, all the required methods to operate with.
     */
//    private Balls balls = new Balls(
//            screenWidth,
//            screenHeight,
//            ballsQuantity, getBallsImages());
    //** Strings that stand for a paths where an images of a render button reside
    private String strImgStartInitial = "pics/RenderButton/Start_Initial.png";
    private String strImgStartHovered = "pics/RenderButton/Start_Hovered.png";
    private String strImgStartPressed = "pics/RenderButton/Start_Pressed.png";
    private String strImgPauseInitial = "pics/RenderButton/Pause_Initial.png";
    private String strImgPauseHovered = "pics/RenderButton/Pause_Hovered.png";
    private String strImgPausePressed = "pics/RenderButton/Pause_Pressed.png";
    private String strImgContinueInitial = "pics/RenderButton/Continue_Initial.png";
    private String strImgContinueHovered = "pics/RenderButton/Continue_Hovered.png";
    private String strImgContinuePressed = "pics/RenderButton/Continue_Pressed.png";
    private String strImgInitInitial = "pics/RenderButton/Init_Initial.png";
    private String strImgInitHovered = "pics/RenderButton/Init_Hovered.png";
    private String strImgInitPressed = "pics/RenderButton/Init_Pressed.png";
    private Map<String, String> mapStrImages = new HashMap();
    //** In charge of an actual file that is to hold an image of a ball.
    private File fileImg;
    //** Image shown on a JLabel.
    private ImageIcon imgRenderButton = new ImageIcon(strImgStartInitial);
    //** In charge of running / stopping / continuing convergence.
    private JLabel lblRenderButton = new JLabel(getImgRenderButton());
    //</editor-fold>

    //** Used to paint a screen on.
    private class frmDrawingBoard extends JFrame {
    }

    //** Retrieving an object of a Repository
    public static Repository getInstance() {

        if (repository == null) {

            repository = new Repository();
            Repository.logic = new Logic();
            Repository.rendering = new Rendering();
            // Required here, unless the color is not set.
            rendering.setBackground(Color.BLACK);
//            screen = new Screen();
//            screen.initializeData(rendering);
//
//            Repository.oFrmDrawingBoard = repository.new frmDrawingBoard();

//            repository.initializeData();

        }
        return repository;

    }

    public Repository() {
//        Repository.logic = new Logic();
//        Repository.rendering = new Rendering();
        // Required here, unless the color is not set.
        rendering.setBackground(Color.BLACK);
//            screen = new Screen();
        screen.initializeData(rendering);
    }

    //** Adding a listeneres to a frame
    private void addListeners(JFrame frame) {
        frame.addKeyListener(new FrameKeyListener());
        frame.addMouseListener(new FrameMouseListener());
        frame.addMouseMotionListener(new FrameMouseMotionListener());
        frame.addMouseWheelListener(new FrameMouseWheelListener());
    }

    //** Populate images names
    private void populateStringMap(Map map) {

        map.put("continueHovered", strImgContinueHovered);
        map.put("continueInitial", strImgContinueInitial);
        map.put("continuePressed", strImgContinuePressed);

        map.put("initHovered", strImgInitHovered);
        map.put("initInitial", strImgInitInitial);
        map.put("initPressed", strImgInitPressed);

        map.put("pauseHovered", strImgPauseHovered);
        map.put("pauseInitial", strImgPauseInitial);
        map.put("pausePressed", strImgPausePressed);

        map.put("startHovered", strImgStartHovered);
        map.put("startInitial", strImgStartInitial);
        map.put("startPressed", strImgStartPressed);

    }

    //** Initializing a JFrame
    private void initializeFrame(JFrame frame) {
//        frame.setSize(repository.getScreenWidth(),
//                repository.getScreenHeight());
        frame.setLocationRelativeTo(null);
        frame.setContentPane(repository.getoDrawing());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (!repository.isWindowFrame()) {
            frame.setUndecorated(true);
        }
        frame.setLayout(null);
        fps.setDefaultPosition(frame);
        fps.getLabel().setForeground(fps.getColor());
        frame.add(fps.getLabel());
        frame.add(repository.getLblRenderButton());

        frame.setBackground(repository.getClrWindowBackground());
        frame.setVisible(true);
        this.addListeners(oFrmDrawingBoard);
        frame.requestFocus();
    }

    //** Initializing some data - images, frame, adding listeners.
    private void initializeData() {

        //** Put an initialization code here */
        if (repository.isFullScreen()) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            repository.pntScreenCenter.x = (int) screenSize.getWidth() / 2;
            repository.pntScreenCenter.y = (int) screenSize.getWidth() / 2;
//            repository.setScreenWidth((int) screenSize.getWidth());
//            repository.setScreenHeight((int) screenSize.getHeight());
        } else {
//            repository.pntScreenCenter.x = repository.getScreenWidth() / 2;
//            repository.pntScreenCenter.y = repository.getScreenHeight() / 2;
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

        this.initializeFrame(oFrmDrawingBoard);

        this.populateStringMap(getMapStrImages());
//        repository.getoFrmDrawingBoard().setBackground(repository.getClrWindowBackground());

        rendering.setBackground(
                repository.getClrWindowBackground()
        );
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">

    public boolean isConverge() {
        return converge;
    }

    public void setConverge(boolean converge) {
        this.converge = converge;
    }

    /**
     * @return the ballsImages
     */
    public BallsImages getBallsImages() {
        return ballsImages;
    }

    /**
     * @param ballsImages the ballsImages to set
     */
    public void setBallsImages(BallsImages ballsImages) {
        this.ballsImages = ballsImages;
    }
    
    public Balls getBalls() {
        return balls;
    }

    public void setBalls(Balls balls) {
        this.balls = balls;
    }
    
    /**
     * @return the mapStrImages
     */
    public Map<String, String> getMapStrImages() {
        return mapStrImages;
    }

    /**
     * @param mapStrImages the mapStrImages to set
     */
    public void setMapStrImages(Map<String, String> mapStrImages) {
        this.mapStrImages = mapStrImages;
    }

    /**
     * @return the lblRenderButton
     */
    public JLabel getLblRenderButton() {
        return lblRenderButton;
    }

    /**
     * @param lblRenderButton the lblRenderButton to set
     */
    public void setLblRenderButton(JLabel lblRenderButton) {
        this.lblRenderButton = lblRenderButton;
    }

    /**
     * @return the imgRenderButton
     */
    public ImageIcon getImgRenderButton() {
        return imgRenderButton;
    }

    /**
     * @param imgRenderButton the imgRenderButton to set
     */
    public void setImgRenderButton(ImageIcon imgRenderButton) {
        this.imgRenderButton = imgRenderButton;
    }

//    /**
//     * @return the ballSelected
//     */
//    public int getBallSelected() {
//        return ballSelected;
//    }

//    /**
//     * @param ballSelected the ballSelected to set
//     */
//    public void setBallSelected(int ballSelected) {
//        this.ballSelected = ballSelected;
//    }

    public Timer getTmrRendering() {
        return tmrRendering;
    }

    public void setTmrRendering(Timer tmrRendering) {
        this.tmrRendering = tmrRendering;
    }

    public Timer getTmrAfterConvergence() {
        return tmrAfterConvergence;
    }

    public void setTmrAfterConvergence(Timer tmrAfterConvergence) {
        this.tmrAfterConvergence = tmrAfterConvergence;
    }

    public String getStrImgStartInitial() {
        return strImgStartInitial;
    }

    public void setStrImgStartInitial(String strImgStartInitial) {
        this.strImgStartInitial = strImgStartInitial;
    }

    public String getStrImgStartHovered() {
        return strImgStartHovered;
    }

    public void setStrImgStartHovered(String strImgStartHovered) {
        this.strImgStartHovered = strImgStartHovered;
    }

    public String getStrImgStartPressed() {
        return strImgStartPressed;
    }

    public void setStrImgStartPressed(String strImgStartPressed) {
        this.strImgStartPressed = strImgStartPressed;
    }

    public String getStrImgPauseInitial() {
        return strImgPauseInitial;
    }

    public void setStrImgPauseInitial(String strImgPauseInitial) {
        this.strImgPauseInitial = strImgPauseInitial;
    }

    public String getStrImgPauseHovered() {
        return strImgPauseHovered;
    }

    public void setStrImgPauseHovered(String strImgPauseHovered) {
        this.strImgPauseHovered = strImgPauseHovered;
    }

    public String getStrImgPausePressed() {
        return strImgPausePressed;
    }

    public void setStrImgPausePressed(String strImgPausePressed) {
        this.strImgPausePressed = strImgPausePressed;
    }

    public String getStrImgContinueInitial() {
        return strImgContinueInitial;
    }

    public void setStrImgContinueInitial(String strImgContinueInitial) {
        this.strImgContinueInitial = strImgContinueInitial;
    }

    public String getStrImgContinueHovered() {
        return strImgContinueHovered;
    }

    public void setStrImgContinueHovered(String strImgContinueHovered) {
        this.strImgContinueHovered = strImgContinueHovered;
    }

    public String getStrImgContinuePressed() {
        return strImgContinuePressed;
    }

    public void setStrImgContinuePressed(String strImgContinuePressed) {
        this.strImgContinuePressed = strImgContinuePressed;
    }

    public String getStrImgInitInitial() {
        return strImgInitInitial;
    }

    public void setStrImgInitInitial(String strImgInitInitial) {
        this.strImgInitInitial = strImgInitInitial;
    }

    public String getStrImgInitHovered() {
        return strImgInitHovered;
    }

    public void setStrImgInitHovered(String strImgInitHovered) {
        this.strImgInitHovered = strImgInitHovered;
    }

    public String getStrImgInitPressed() {
        return strImgInitPressed;
    }

    public void setStrImgInitPressed(String strImgInitPressed) {
        this.strImgInitPressed = strImgInitPressed;
    }

    public File getFileImg() {
        return fileImg;
    }

    public void setFileImg(File fileImg) {
        this.fileImg = fileImg;
    }

//    public BallsArray getBallsScattered() {
//        return ballsScattered;
//    }

//    public void setBallsScattered(BallsArray ballsScattered) {
//        this.ballsScattered = ballsScattered;
//    }

//    public BallsArray getBallsDestination() {
//        return ballsDestination;
//    }
//
//    public void setBallsDestination(BallsArray ballsDestination) {
//        this.ballsDestination = ballsDestination;
//    }
//
//    public static BufferedImage getImgSelected() {
//        return imgSelected;
//    }
//
//    public static void setImgSelected(BufferedImage aImgSelected) {
//        imgSelected = aImgSelected;
//    }
//
//    public static BufferedImage getImgScattered() {
//        return imgScattered;
//    }
//
//    public static void setImgScattered(BufferedImage aImgScattered) {
//        imgScattered = aImgScattered;
//    }
//
//    public static BufferedImage getImgConverged() {
//        return imgConverged;
//    }
//
//    public static void setImgConverged(BufferedImage aImgConverged) {
//        imgConverged = aImgConverged;
//    }
//
//    public static BufferedImage getImgDestination() {
//        return imgDestination;
//    }
//
//    public static void setImgDestination(BufferedImage aImgDestination) {
//        imgDestination = aImgDestination;
//    }

    public int getDotsQuantity() {
        return ballsQuantity;
    }

    public void setDotsQuantity(int dotsQuantity) {
        this.ballsQuantity = dotsQuantity;
    }

    public Logic getoLogic() {
        return logic;
    }

    public void setoLogic(Logic oLogic) {
        this.logic = oLogic;
    }

    public Rendering getoDrawing() {
        return rendering;
    }

    public void setoDrawing(Rendering oRendering) {
        this.rendering = oRendering;
    }

    public Point getPntScreenCenter() {
        return pntScreenCenter;
    }

    public void setPntScreenCenter(Point pntScreenCenter) {
        this.pntScreenCenter = pntScreenCenter;
    }

    public Color getClrWindowBackground() {
        return clrWindowBackground;
    }

    public void setClrWindowBackground(Color clrWindowBackground) {
        this.clrWindowBackground = clrWindowBackground;
    }

    public static Repository getRepository() {
        return repository;
    }

    public static void setRepository(Repository Repository) {
        Repository.repository = Repository;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    public boolean isWindowFrame() {
        return windowFrame;
    }

    public void setWindowFrame(boolean windowFrame) {
        this.windowFrame = windowFrame;
    }
//
//    public int getScreenWidth() {
//        return ScreenWidth;
//    }
//
//    public void setScreenWidth(int ScreenWidth) {
//        this.ScreenWidth = ScreenWidth;
//    }
//
//    public int getScreenHeight() {
//        return ScreenHeight;
//    }
//
//    public void setScreenHeight(int ScreenHeight) {
//        this.ScreenHeight = ScreenHeight;
//    }

    public frmDrawingBoard getoFrmDrawingBoard() {
        return Repository.oFrmDrawingBoard;
    }

    public void setoFrmDrawingBoard(frmDrawingBoard aoFrmDrawingBoard) {
        Repository.oFrmDrawingBoard = aoFrmDrawingBoard;
    }

    public boolean isKeyAlt() {
        return keyAlt;
    }

    public void setKeyAlt(boolean keyAlt) {
        this.keyAlt = keyAlt;
    }

    public boolean isKeyCtrl() {
        return keyCtrl;
    }

    public void setKeyCtrl(boolean keyCtrl) {
        this.keyCtrl = keyCtrl;
    }

    public boolean isKeyShift() {
        return keyShift;
    }

    public void setKeyShift(boolean keyShift) {
        this.keyShift = keyShift;
    }

    public boolean isRoam() {
        return roam;
    }

    public void setRoam(boolean roam) {
        this.roam = roam;
    }

    public FPS getFps() {
        return fps;
    }

    public void setFps(FPS fps) {
        this.fps = fps;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

//</editor-fold>    
}
