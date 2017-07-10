package ru.yanchenko.vlad.graphicsapps.generics.balls;

import ru.yanchenko.vlad.graphicsapps.logic.Geometry;

/**
 * Operations performed with a balls - moving, converging, changing color
 * Created by vladislav on 10.07.17.
 */
public class BallsLogic {

    Balls balls;
    BallsImages ballsImages;

    public BallsLogic(Balls balls) {
        this.balls = balls;
    }

    public void moveBalls() {

    }

    public void rotateBallsAroundOne(int rotation, int screenWidth, int screenHeight) {
        /**
         * This operation rotates a scattered balls around a selected one.
         */
        //** When there is no any scattered ball selected
        if (balls.getBallSelected().equals(
                balls.getBallDummy())) {

            if (rotation < 0) {

                //** Moving a destination balls in a current figure
                balls.setAnglDestDotsShift(
                        balls.getAnglDestDotsShift()
                                - 0.04);
                balls.scatterTheBalls(
                        balls.getBallsDestination(),
                        0,
                        screenWidth,
                        screenHeight,
                        ballsImages.getImgScattered(),
                        balls.isRoam());
            }

            if (rotation > 0) {

                //** Moving a destination balls in a current figure
                balls.setAnglDestDotsShift(
                        balls.getAnglDestDotsShift()
                                + 0.04);
                balls.scatterTheBalls(
                        balls.getBallsDestination(),
                        0,
                        screenWidth,
                        screenHeight,
                        ballsImages.getImgScattered(),
                        balls.isRoam());
            }
            //** When there is some scattered ball selected
        } else {
            if (rotation < 0) {
                Geometry.computePolarCoors(
                        balls);
                //** Increasing an angle
                balls.setAngle(
                        +balls.getAngleStep());
                Geometry.computeDekartCoors(
                        balls);
            }
            if (rotation > 0) {
                Geometry.computePolarCoors(
                        balls);
                //** Decreasing an angle
                balls.setAngle(
                        -balls.getAngleStep());
                Geometry.computeDekartCoors(
                        balls);
            }
        }
    }

}
