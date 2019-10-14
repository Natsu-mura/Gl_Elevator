/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jfxtest;

/**
 *
 * @author justi
 */
public interface ControllerInterface {
    public void intButtonCallback(int i);
    public void extButtonCallback(int i, int direction);
    public void floorCrossedCallback(int i) throws InterruptedException;
    public void emergency();
    public void emergencyResolved();
    public void updateTarget();
}
