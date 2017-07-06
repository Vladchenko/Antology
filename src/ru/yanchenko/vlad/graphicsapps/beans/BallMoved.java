/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.yanchenko.vlad.graphicsapps.beans;

/**
 * Ball that is to be converged to a destination ball.
 * @author Влад
 */
public class BallMoved extends Ball {

    // Keeps a delta(step) that a ball is move in one iteration.
    private double dx = 0;
    // Keeps a delta(step) that a ball is move in one iteration.
    private double dy = 0;
    // Angle used to make a rotation of a balls around a selected one, maybe for something else.
    private double angle = 0;
    // To know at which radius the ball is placed relatively to a selected one.
    private double radius = 0;

    // Constructor populates a coordinate of this ball.
    public BallMoved(int x, int y, boolean random) {
        super(x, y, random);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public double getdX() {
        return dx;
    }
    
    public void setdX(double dx) {
        this.dx = dx;
    }
    
    public double getdY() {
        return dy;
    }
    
    public void setdY(double dy) {
        this.dy = dy;
    }
    
    public double getAngle() {
        return angle;
    }
    
    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
    
    //</editor-fold>
    
}
