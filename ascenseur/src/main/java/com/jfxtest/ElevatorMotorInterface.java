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
public interface ElevatorMotorInterface {
    public void goUp();
    public void goDown();
    public void stopNextFloor();
    public void stopNow();
}
