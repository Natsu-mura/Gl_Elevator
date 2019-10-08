/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jfxtest;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author justi
 */
public class Control extends Observable{
    
    private ArrayList<Integer> nextFloors;
    private int currentFloor;
    private int direction;
    
    
    private Control() {
        this.nextFloors=new ArrayList();
        this.currentFloor=0;
        this.direction=0;
    }
     
    /** Holder */
    private static class SingletonHolder
    {       
        /** Instance unique non préinitialisée */
        private final static Control Instance = new Control();
    }
 
    /** Point d'accès pour l'instance unique du singleton
     * @return  */
    public static Control getInstance()
    {
        return SingletonHolder.Instance;
    }
    
    
    /* Getters */
    public ArrayList<Integer> getFloors(){
        return nextFloors;
    }
    public synchronized int getDirection() {
        return direction;
    }
    
    public synchronized int getCurrent() {
        return currentFloor;
    }
    
    /* Appels */
    public void addFloor(Integer f){
        this.nextFloors.add(f);
    }
    public void removeFloor(Integer f){
        this.nextFloors.remove(f);
    }
    
    
    /* Observer */
    public void updateDir() {
        synchronized (this) {
            this.direction=1;
            if(this.nextFloors.isEmpty() || this.nextFloors.contains(-1) || this.currentFloor==this.nextFloors.get(0)){
                this.direction=0;
            }
            else if(this.nextFloors.get(0)<this.currentFloor)
                this.direction = -1;
        }
        setChanged();
        notifyObservers();
    }

    /* Arret d'urgence */
    public void emergency(Integer f){
        synchronized(this){
            this.nextFloors.removeAll(nextFloors);
            this.nextFloors.add(f);
        }
        setChanged();
        notifyObservers();
    }
    
}
