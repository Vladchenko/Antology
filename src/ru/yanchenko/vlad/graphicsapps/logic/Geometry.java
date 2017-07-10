package ru.yanchenko.vlad.graphicsapps.logic;

import ru.yanchenko.vlad.graphicsapps.generics.balls.Balls;

/**
 * Created by Влад on 16.06.2017.
 */
public class Geometry {

    /**
     * Computing distances between 2 dots.
     *
     * @param x1 dot1 x ordinate
     * @param y1 dot1 y ordinate
     * @param x2 dot2 x ordinate
     * @param y2 dot2 y ordinate
     * @return
     */
    public static double computeDistance(double x1, double y1, double x2, double y2) {
        return (Math.sqrt(
                Math.pow((double) (x1 - x2), (double) 2)
                        + Math.pow((double) (y1 - y2), (double) 2)));
    }

    /**
     * Computing a polar coordinates of balls scatterred at random, relatively
     * to a selected one.
     * TODO Make passing to this method not Balls, but something different.
     */
    public static void computePolarCoors(Balls balls) {

        double deltaX;
        double deltaY;

        for (int i = 0; i < balls.getBallsScattered().length; i++) {
            //** When the dot is not selected, perform a computeulation,
            //** because there is no sense in doing this with the same dot
//            if (!balls.getBallSelected().equals(balls.getBallDummy())) {
            deltaY = balls.getBallsScattered()[i].getY()
                    - balls.getBallSelected().getY();
            deltaX = balls.getBallsScattered()[i].getX()
                    - balls.getBallSelected().getX();
            //** computeulating the angles between selected
            //** dot and a dots scattered at random
            if (deltaX != 0) {
                balls.getBallsScattered()[i].setAngle(Math.atan(Math.abs(deltaY)
                        / Math.abs(deltaX)));
            }
            //** Adjusting the angle
            if (deltaX < 0 && deltaY < 0) {
                balls.getBallsScattered()[i].
                        setAngle(-balls.getBallsScattered()[i].getAngle()
                                + Math.PI);
            }
            if (deltaX < 0 && deltaY > 0) {
                balls.getBallsScattered()[i].
                        setAngle(balls.getBallsScattered()[i].getAngle()
                                + Math.PI);
            }
            if (deltaX > 0 && deltaY > 0) {
                balls.getBallsScattered()[i].
                        setAngle(-balls.getBallsScattered()[i].getAngle()
                                + Math.PI * 2);
            }

            if (deltaX == 0 && deltaY > 0) {
                balls.getBallsScattered()[i].
                        setAngle(2 * Math.PI - Math.PI / 2);
            }
            if (deltaX == 0 && deltaY < 0) {
                balls.getBallsScattered()[i].setAngle(Math.PI / 2);
            }
            if (deltaX > 0 && deltaY == 0) {
                balls.getBallsScattered()[i].setAngle(0);
            }
            if (deltaX < 0 && deltaY == 0) {
                balls.getBallsScattered()[i].setAngle(Math.PI);
            }

            if (deltaX == 0 && deltaY == 0) {
                balls.getBallsScattered()[i].setAngle(0);
            }
            //** computeulating the radiuses between selected
            //** dot and a dots scattered at random
            balls.getBallsMetaData()[i].setRadius(
                    Math.sqrt(
                            Math.pow(balls.getBallSelected().getY()
                                    - balls.getBallsScattered()[i].getY(), 2)
                                    + Math.pow(balls.getBallSelected().getX()
                                    - balls.getBallsScattered()[i].getX(), 2)));
        }
    }

    /**
     * Computing a dekart coordinates of a balls scattered at
     * random, relatively to a selected one.
     */
    public static void computeDekartCoors(Balls balls) {
        for (int i = 0; i < balls.getBallsDestination().length; i++) {
//            if (!balls.getBallsScattered()[i].isSelected()) {
            balls.getBallsScattered()[i].setX(
                    balls.getBallSelected().getX()
                            + Math.cos(balls.getBallsScattered()[i].getAngle()
                            + balls.getAngle())
                            * balls.getBallsMetaData()[i].getRadius());
            balls.getBallsScattered()[i].setY(
                    balls.getBallSelected().getY()
                            - Math.sin(balls.getBallsScattered()[i].getAngle()
                            + balls.getAngle())
                            * balls.getBallsMetaData()[i].getRadius());
//            }
        }
    }


}
