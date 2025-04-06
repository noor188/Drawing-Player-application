package com.drawingPlayer.org.model.Impl;


import com.drawingPlayer.org.model.Shape;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "Rectangles")
public class Rectangle extends Shape {

    public Rectangle(int x, int y, int width, int height, boolean selected, int instrument, int playLineCord) {
        super(x, y, width, height, selected, instrument, playLineCord);
    }
    public Rectangle(){}
}
