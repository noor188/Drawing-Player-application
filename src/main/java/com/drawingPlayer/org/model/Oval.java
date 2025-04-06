package com.drawingPlayer.org.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Ovals")
public class Oval extends Shape{

    public Oval(int x, int y, int width, int height, boolean selected, int instrument, int playLineCord) {
        super(x, y, width, height, selected, instrument, playLineCord);
    }

    public Oval(){}
}