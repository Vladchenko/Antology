package ru.yanchenko.vlad.graphicsapps.generics;

import ru.yanchenko.vlad.graphicsapps.generics.balls.Balls;
import ru.yanchenko.vlad.graphicsapps.generics.balls.BallsImages;
import ru.yanchenko.vlad.graphicsapps.generics.balls.BallsLogic;

/**
 * Created by Влад on 16.07.2017.
 */
public class RenderingComponents {

    private FPS fps;
    //** In charge of running / stopping / continuing convergence.
    private RenderButton renderButton;
    // Balls to be rendered (drawn) on a screen(JFrame).
    private Balls balls;
    //** Images for a balls
    private BallsImages ballsImages;

    public RenderingComponents(int screenWidth, int screenHeight, int ballsQuantity) {
        renderButton = new RenderButton();
        fps = new FPS();
        ballsImages = new BallsImages();
        balls = new Balls(
                new BallsLogic(),
                ballsImages,
                screenWidth,
                screenHeight,
                ballsQuantity);
    }

    public FPS getFps() {
        return fps;
    }

    public void setFps(FPS fps) {
        this.fps = fps;
    }

    public RenderButton getRenderButton() {
        return renderButton;
    }

    public void setRenderButton(RenderButton renderButton) {
        this.renderButton = renderButton;
    }

    public Balls getBalls() {
        return balls;
    }

    public void setBalls(Balls balls) {
        this.balls = balls;
    }

}
