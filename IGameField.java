package com.tn.field;

import com.tn.game.Result;


public interface IGameField {
    char getIcon();
    Result shootAt();
}