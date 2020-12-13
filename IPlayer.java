package com.tn.player;


public interface IPlayer {
    void placeShips();
    void fireAt(IPlayer opponent);
    int getTotalLivesLeft();
}