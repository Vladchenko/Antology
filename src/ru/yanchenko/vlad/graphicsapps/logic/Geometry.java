package ru.yanchenko.vlad.graphicsapps.logic;

/**
 * Created by Влад on 16.06.2017.
 */
public class Geometry {

    /**
     * Computing distances between 2 dots.
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public static double computeDistance(double x1, double y1, double x2, double y2) {
        return (Math.sqrt(
                Math.pow((double) (x1 - x2), (double) 2)
                        + Math.pow((double) (y1 - y2), (double) 2)));
    }

}
