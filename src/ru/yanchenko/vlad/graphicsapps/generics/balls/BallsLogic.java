package ru.yanchenko.vlad.graphicsapps.generics.balls;

import ru.yanchenko.vlad.graphicsapps.beans.BallMoved;
import ru.yanchenko.vlad.graphicsapps.beans.Dot;
import ru.yanchenko.vlad.graphicsapps.logic.Geometry;

import java.awt.image.BufferedImage;

/**
 * Operations performed with a balls - moving, converging, changing color
 * Created by vladislav on 10.07.17.
 */
public class BallsLogic {

    BallsImages ballsImages;

    public void moveBalls() {

    }

    public void rotateBallsAroundOne(Balls balls, int rotationAmount, int screenWidth, int screenHeight) {
        /**
         * This operation rotates a scattered balls around a selected one.
         */
        //** When there is no any scattered ball selected
        if (balls.getBallSelected().equals(
                balls.getBallDummy())) {

            if (rotationAmount < 0) {

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

            if (rotationAmount > 0) {

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
            if (rotationAmount < 0) {
                Geometry.computePolarCoors(
                        balls);
                //** Increasing an angle
                balls.setAngle(
                        +balls.getAngleStep());
                Geometry.computeDekartCoors(
                        balls);
            }
            if (rotationAmount > 0) {
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

    /**
     * Generating a coordinates of scattered dots at random with a specific
     * shape.
     * TODO Get rid of double balls presence - Balls balls, Dot[] ballsArray
     */
    public void scatterTheBalls(Balls balls, Dot[] ballsArray, double angle,
                                int screenWidth, int screenHeight,
                                BufferedImage image, boolean converge) {

        double maxDistance = 0;
        double screenRatio;
        double dotsQuantityRoot;
        int columnQuantity;
        int rowQuantity = 0;
        int rad = balls.getRadius() / 6;
        double xDelta = 0;
        double yDelta = 0;
        double incXDelta = 0;
        double incYDelta = 0;
        double stepPI = 2 * Math.PI / ballsArray.length;
        double angleStep2 = 0;
        double step = 0.02095;
        double angleStep = (balls.getDestDotsFigureKind() == 25) ? Math.PI : 0;
        double radius2 = 20;

//        {angleStep += angle;}

        if (balls.getScatterMode() > 0) {
            screenRatio = screenWidth / screenHeight;
            dotsQuantityRoot = Math.sqrt(balls.getBallsScattered().length);
            columnQuantity = (int) (dotsQuantityRoot * screenRatio);
            rowQuantity = (int) (dotsQuantityRoot / screenRatio);
            if (columnQuantity * rowQuantity > balls.getBallsScattered().length) {
                columnQuantity--;
            }
            xDelta = screenWidth / columnQuantity;
            yDelta = screenHeight / rowQuantity;
            incXDelta = xDelta / 2;
            incYDelta = yDelta / 2;
//            if (balls.getScatterMode() == 2) {
//                incYDelta = 0;
//            }
//
//            if (balls.getScatterMode() == 5) {
//                incYDelta = yDelta / 8;
//                incXDelta = screenWidth / 2;
//            }
//            if (balls.getScatterMode() == 4) {
//                incXDelta = screenWidth / 2;
//            }

        }

        for (int i = 0; i < ballsArray.length; i++) {

            switch (balls.getScatterMode()) {
                //** Balls scattered at random
                case 0: {
                    ballsArray[i].setX((Math.random()
                            * (screenWidth - image.getWidth())));
                    ballsArray[i].setY((Math.random()
                            * (screenHeight - image.getHeight())));
                    break;
                }
                //** Wall of a balls
                case 1: {
                    if (i % rowQuantity == 0) {
                        if (i != 0) {
                            incYDelta += yDelta;
                        }
                        incXDelta = xDelta / 2;
                    }
                    ballsArray[i].setX(incXDelta + Math.cos(stepPI
                            + balls.getAnglDestDotsShift() * 3) * 20);
                    ballsArray[i].setY(incYDelta + Math.sin(stepPI
                            + balls.getAnglDestDotsShift() * 3) * 20);
                    incXDelta += xDelta;
                    break;
                }
                //** Curvy wall of a balls
                case 2: {
                    ballsArray[i].setX(incXDelta + xDelta * Math.sin(stepPI
                            + balls.getAnglDestDotsShift() * 8) / 2);
                    ballsArray[i].setY(incYDelta + yDelta * Math.cos(stepPI
                            + balls.getAnglDestDotsShift() * 8) / 2);
                    incXDelta += xDelta;
                    stepPI += 0.02;
                    if (i % rowQuantity == 0
                            && i > 0) {
                        incYDelta += yDelta;
                        incXDelta = xDelta / 2;
                    }
                    break;
                }
                //** Several curved lines
                case 3: {
                    angleStep2 += stepPI;
                    ballsArray[i].setX(Math.tan(angleStep2 + angleStep2 / 2
                            + balls.getAnglDestDotsShift()) * balls.getRadius() + screenWidth / 2);
                    ballsArray[i].setY(Math.tan(angle + angleStep2 * 4
                            + balls.getAnglDestDotsShift()) * balls.getRadius() + screenHeight / 2);
                    if (ballsArray[i].getX() > screenWidth
                            || ballsArray[i].getX() < 0) {
                        ballsArray[i].setX(screenWidth / 2);
                        try {
                            ballsArray[i].setX(ballsArray[i - 1].getX());
                            ballsArray[i].setY(ballsArray[i - 1].getY());
                            break;
                        } catch (Exception e) {
                            ballsArray[i].setX(ballsArray[i + 1].getX());
                            ballsArray[i].setY(ballsArray[i + 1].getY());
                        }
                    }
                    if (ballsArray[i].getY() > screenHeight
                            || ballsArray[i].getY() < 0) {
                        ballsArray[i].setY(screenHeight / 2);
                        try {
                            ballsArray[i].setX(ballsArray[i - 1].getX());
                            ballsArray[i].setY(ballsArray[i - 1].getY());
                            break;
                        } catch (Exception e) {
                            ballsArray[i].setX(ballsArray[i + 1].getX());
                            ballsArray[i].setY(ballsArray[i + 1].getY());
                        }
                    }
                    break;
                }
                //** Circle
                case 4: {
                    angle += stepPI;
                    ballsArray[i].setX(Math.cos(angle + balls.getAnglDestDotsShift())
                            * balls.getRadius() + screenWidth / 2);
                    ballsArray[i].setY(-Math.sin(angle + balls.getAnglDestDotsShift())
                            * balls.getRadius() + screenHeight / 2);
                    break;
                }
                //** Curvy circle
                case 5: {
                    angle += .5018;
                    radius2 = (int) (Math.cos(angle) * balls.getRadiusFloating());
                    radius2 += balls.getRadius();
                    ballsArray[i].setX(Math.cos(i * step + balls.getAnglDestDotsShift())
                            * radius2 + screenWidth / 2);
                    ballsArray[i].setY(-Math.sin(i * step + balls.getAnglDestDotsShift())
                            * radius2 + screenHeight / 2);
                    break;
                }
                //** Shape like 8, but horizontal
                case 6: {
                    angleStep2 += stepPI;
                    ballsArray[i].setX(Math.cos(angleStep2 + angleStep2 + Math.PI / 2
                            + balls.getAnglDestDotsShift()) * balls.getRadius() + screenWidth / 2);
                    ballsArray[i].setY(Math.sin(angle - angleStep2 + Math.PI / 2
                            + balls.getAnglDestDotsShift()) * balls.getRadius() + screenHeight / 2);
                    break;
                }
                //** Shape like 88
                case 7: {
                    angleStep2 += stepPI * 2;
                    ballsArray[i].setX(Math.cos(angleStep2 + angleStep2 / 2 - angle
                            + balls.getAnglDestDotsShift()) * balls.getRadius() + screenWidth / 2);
                    ballsArray[i].setY(Math.sin(angle / 2 - angleStep2
                            + balls.getAnglDestDotsShift()) * balls.getRadius() + screenHeight / 2);
                    break;
                }
                //** Cross of a balls (4 spikes)
                case 8: {
                    stepPI = Math.PI / 2;
                    ballsArray[i].setX(screenWidth / 2
                            + balls.getRadius() * i * Math.sin(i * stepPI
                            + balls.getAnglDestDotsShift()) / ballsArray.length);
                    ballsArray[i].setY(screenHeight / 2
                            + balls.getRadius() * i * Math.cos(i * stepPI
                            + balls.getAnglDestDotsShift()) / ballsArray.length);
                    break;
                }
                //** 8 spikes of a balls
                case 9: {
                    stepPI = Math.PI / 4;
                    ballsArray[i].setX(screenWidth / 2
                            + balls.getRadius() * i
                            * Math.sin(i * stepPI - Math.PI * 3 / 4
                            + balls.getAnglDestDotsShift()) / ballsArray.length);
                    ballsArray[i].setY(screenHeight / 2
                            + balls.getRadius() * i
                            * Math.cos(i * stepPI - Math.PI * 3 / 4
                            + balls.getAnglDestDotsShift()) / ballsArray.length);
                    break;
                }
                //** 20 spikes of a balls
                case 10: {
                    stepPI = Math.PI / 20;
                    ballsArray[i].setX(screenWidth / 2
                            + balls.getRadius() * i
                            * Math.sin(i * stepPI - Math.PI * 3 / 4
                            + balls.getAnglDestDotsShift()) / ballsArray.length);
                    ballsArray[i].setY(screenHeight / 2
                            + balls.getRadius() * i
                            * Math.cos(i * stepPI - Math.PI * 3 / 4
                            + balls.getAnglDestDotsShift()) / ballsArray.length);
                    break;
                }
                //** Concaved triangle = to destination circle
                case 11: {
                    stepPI = Math.PI * 2 / ballsArray.length;
                    ballsArray[i].setX(screenWidth / 2
                            + balls.getRadius() * 6 * Math.sin(i * stepPI
                            + balls.getAnglDestDotsShift() * 2) / 9
                            + balls.getRadius() / 3 * Math.cos(i * 2 * stepPI));
                    ballsArray[i].setY(screenHeight / 2
                            + balls.getRadius() * 6 * Math.cos(i * stepPI
                            + balls.getAnglDestDotsShift() * 2) / 9
                            + balls.getRadius() / 3 * Math.sin(i * 2 * stepPI));
                    break;
                }
                //** Pentagramm = to destination circle
                case 12: {
                    stepPI = Math.PI * 2 / ballsArray.length;
                    ballsArray[i].setX(screenWidth / 2
                            + balls.getRadius() * 4 * Math.sin(i * stepPI
                            + balls.getAnglDestDotsShift() * 2) / 5
                            + balls.getRadius() / 5 * Math.cos(i * 4 * stepPI));
                    ballsArray[i].setY(screenHeight / 2
                            + balls.getRadius() * 4 * Math.cos(i * stepPI
                            + balls.getAnglDestDotsShift() * 2) / 5
                            + balls.getRadius() / 5 * Math.sin(i * 4 * stepPI));
                    break;
                }
                //** 10 curved lines = to a destination circle
                case 13: {
                    stepPI = Math.PI * 2 / ballsArray.length;
                    ballsArray[i].setX(screenWidth / 2
                            + balls.getRadius() * 3 * Math.sin(i * stepPI
                            + balls.getAnglDestDotsShift() * 2) / 3.3
                            + balls.getRadius() / 9 * Math.cos(i * 9 * stepPI));
                    ballsArray[i].setY(screenHeight / 2
                            + balls.getRadius() * 3 * Math.cos(i * stepPI
                            + balls.getAnglDestDotsShift() * 2) / 3.3
                            + balls.getRadius() / 9 * Math.sin(i * 9 * stepPI));
                    break;
                }
                //** 4 petals inside a destination circle
                case 14: {
                    stepPI = Math.PI * 2 / ballsArray.length;
                    ballsArray[i].setX(screenWidth / 2
                            + balls.getRadius() * Math.sin(i * stepPI - Math.PI / 2
                            + balls.getAnglDestDotsShift() * 2) / 2
                            + balls.getRadius() / 2 * Math.cos(i * 3 * stepPI));
                    ballsArray[i].setY(screenHeight / 2
                            + balls.getRadius() * Math.cos(i * stepPI - Math.PI / 2
                            + balls.getAnglDestDotsShift() * 2) / 2
                            + balls.getRadius() / 2 * Math.sin(i * 3 * stepPI));
                    break;
                }
                //** 8 petals inside a destination circle
                case 15: {
                    stepPI = Math.PI * 2 / ballsArray.length;
                    ballsArray[i].setX(screenWidth / 2
                            + balls.getRadius() * 10 * Math.sin(i * stepPI + Math.PI / 16
                            + balls.getAnglDestDotsShift() * 2) / 20
                            + balls.getRadius() / 2 * Math.cos(i * 7 * stepPI));
                    ballsArray[i].setY(screenHeight / 2
                            + balls.getRadius() * 10 * Math.cos(i * stepPI + Math.PI / 16
                            + balls.getAnglDestDotsShift() * 2) / 20
                            + balls.getRadius() / 2 * Math.sin(i * 7 * stepPI));
                    break;
                }
                case 16: {
                    stepPI = Math.PI * 2 / ballsArray.length;
                    ballsArray[i].setX(screenWidth / 2
                            + balls.getRadius() * Math.sin(i * stepPI
                            + balls.getAnglDestDotsShift() * 2) / 2
                            + balls.getRadius() / 2 * Math.cos(i * 10 * stepPI));
                    ballsArray[i].setY(screenHeight / 2
                            + balls.getRadius() * Math.cos(i * stepPI
                            + balls.getAnglDestDotsShift() * 2) / 2
                            + balls.getRadius() / 2 * Math.sin(i * 10 * stepPI));
                    break;
                }
                //** 20 petals inside a destination circle, with a blank in a center
                case 17: {
                    stepPI = Math.PI * 2 / ballsArray.length;
                    ballsArray[i].setX(screenWidth / 2
                            + balls.getRadius() * 2 * Math.sin(i * stepPI
                            + balls.getAnglDestDotsShift() * 2) / 3
                            + balls.getRadius() / 3 * Math.cos(i * 19 * stepPI));
                    ballsArray[i].setY(screenHeight / 2
                            + balls.getRadius() * 2 * Math.cos(i * stepPI
                            + balls.getAnglDestDotsShift() * 2) / 3
                            + balls.getRadius() / 3 * Math.sin(i * 19 * stepPI));
                    break;
                }
                //** Spiral
                case 18: {
                    stepPI = Math.PI * 2 / ballsArray.length * 8;
                    ballsArray[i].setX(screenWidth / 2
                            + balls.getRadius() * i * Math.sin(i * stepPI - Math.PI * 3 / 4
                            + balls.getAnglDestDotsShift() * 2) / ballsArray.length );
                    ballsArray[i].setY(screenHeight / 2
                            + balls.getRadius() * i * Math.cos(i * stepPI - Math.PI * 3 / 4
                            + balls.getAnglDestDotsShift() * 2) / ballsArray.length );
                    break;
                }
                //** Cobweb
                case 19: {
                    stepPI = Math.PI * 2 / ballsArray.length * 8;
                    ballsArray[i].setX(screenWidth / 2
                            + balls.getRadius() * i * Math.sin(i * stepPI - Math.PI * 3 / 4
                            + balls.getAnglDestDotsShift() * 2) / ballsArray.length
                            + balls.getRadius() / 20 * Math.cos(i * 10 * stepPI));
                    ballsArray[i].setY(screenHeight / 2
                            + balls.getRadius() * i * Math.cos(i * stepPI - Math.PI * 3 / 4
                            + balls.getAnglDestDotsShift() * 2) / ballsArray.length
                            + balls.getRadius() / 20 * Math.sin(i * 10 * stepPI));
                    break;
                }
                //** Balls in the circles
                case 20: {
                    stepPI = Math.PI / 20;
                    ballsArray[i].setX(screenWidth / 2
                            + balls.getRadius() * Math.sin(i * stepPI - Math.PI * 3 / 4
                            + balls.getAnglDestDotsShift() * 2)
                            + balls.getRadius() / 10 * Math.cos(i * 8 * stepPI) );
                    ballsArray[i].setY(screenHeight / 2
                            + balls.getRadius() * Math.cos(i * stepPI - Math.PI * 3 / 4
                            + balls.getAnglDestDotsShift() * 2)
                            + balls.getRadius() / 10 *  Math.sin(i * 8 * stepPI) );
                    break;
                }
                //** Balls placed in 2 circles
                case 21: {
                    stepPI = Math.PI * 2 / ballsArray.length;
                    if (i % 20 == 0) {
                        balls.setRadius(balls.getRadius() + rad);
                        rad *= -1;
                    }
                    ballsArray[i].setX(screenWidth / 2
                            + balls.getRadius() * Math.sin(i * stepPI - Math.PI * 3 / 4
                            + balls.getAnglDestDotsShift() * 2));
                    ballsArray[i].setY(screenHeight / 2
                            + balls.getRadius() * Math.cos(i * stepPI - Math.PI * 3 / 4
                            + balls.getAnglDestDotsShift() * 2));
                    break;
                }
                //** Star
                case 22: {
                    stepPI = Math.PI * 2 / ballsArray.length;
                    ballsArray[i].setX(screenWidth / 2
                            + balls.getRadius() * 2 * Math.sin(i * stepPI * 4
                            + balls.getAnglDestDotsShift() * 2) / 3
                            + balls.getRadius() / 3 * Math.cos(i * 6 * stepPI));
                    ballsArray[i].setY(screenHeight / 2
                            + balls.getRadius() * 2 * Math.cos(i * stepPI * 4
                            + balls.getAnglDestDotsShift() * 2) / 3
                            + balls.getRadius() / 3 * Math.sin(i * 6 * stepPI));
                    break;
                }
                //** Amplitude wave
                case 23: {
                    ballsArray[i].setX(incXDelta * Math.sin(
                            stepPI * 1.5
                                    + balls.getAnglDestDotsShift() * 2)
                            + xDelta / 2
                            + screenWidth / 2);
                    ballsArray[i].setY(incYDelta - 10);
                    incXDelta += xDelta / 2;
                    stepPI += 0.0093;
                    if (i % rowQuantity == 0) {
                        incXDelta = 0;
                        incYDelta += yDelta / 1.09;
                    }
                    break;
                }
                //** Shape |X| in horizontal placing
                case 24: {
                    angleStep2 += stepPI;
                    ballsArray[i].setX(Math.tan(angleStep2 + angleStep2
                            + balls.getAnglDestDotsShift()) * balls.getRadius()
                            + screenWidth / 2);
                    ballsArray[i].setY(Math.sin(angle - angleStep2
                            + balls.getAnglDestDotsShift()) * balls.getRadius()
                            + screenHeight / 2);
                    if (ballsArray[i].getX() > screenWidth
                            || ballsArray[i].getX() < 0) {
                        ballsArray[i].setX(screenWidth / 2);
                        try {
                            ballsArray[i].setX(ballsArray[i - 1].getX());
                            ballsArray[i].setY(ballsArray[i - 1].getY());
                            break;
                        } catch (Exception e) {
                            ballsArray[i].setX(ballsArray[i + 1].getX());
                            ballsArray[i].setY(ballsArray[i + 1].getY());
                        }
                    }
                    if (ballsArray[i].getY() > screenHeight
                            || ballsArray[i].getY() < 0) {
                        ballsArray[i].setY(screenHeight / 2);
                        try {
                            ballsArray[i].setX(ballsArray[i - 1].getX());
                            ballsArray[i].setY(ballsArray[i - 1].getY());
                            break;
                        } catch (Exception e) {
                            ballsArray[i].setX(ballsArray[i + 1].getX());
                            ballsArray[i].setY(ballsArray[i + 1].getY());
                        }
                    }
                    break;
                }
                // Strangely, this peace of code ceased to work properly.
                //** Circle with X crossed
//                case 25: {
//                    angleStep = Math.PI;
//                    angleStep2 += stepPI;
//                    radius2 = (int) (Math.cos(angle) * balls.getRadiusFloating());
//                    balls[i].setX(Math.cos(angleStep2 + angleStep / 2 - angle
//                            + balls.getAnglDestDotsShift() * 2)
//                            + balls.getAnglDestDotsShift() * balls.getRadius()
//                            + screenWidth / 2);
//                    balls[i].setY(-Math.sin(angle / 2 - angleStep2
//                            + balls.getAnglDestDotsShift() * 2)
//                            + balls.getAnglDestDotsShift() * balls.getRadius()
//                            + screenHeight / 2);
//                    break;
//                }
            }

        }

        balls.computeMetaData(converge);
    }


    //** Setting images for a balls
    public void setScatteredBallsLook(BallMoved[] balls,
                                      BufferedImage image) {
        for (BallMoved ball : balls) {
            ball.setImage(image);
        }
    }

}
