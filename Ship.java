package com.tn.ship;

import com.tn.game.Result;


public class Ship {
    private final String name;
    private final int size;
    private int lives;

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.lives = size;
    }

    public void hit() {
        if(lives > 0) {
            System.out.printf("%nGood shot! The %s was hit", name);
            lives--;
        } else {
            System.out.println("Ship is destroyed");
        }
    }

    public Result getState() {
        if(lives == 0) {
            return Result.DESTROYED;
        } else if(lives < size) {
            return Result.PARTIAL_HIT;
        } else {
            return Result.NO_HIT;
        }
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}