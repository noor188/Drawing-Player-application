package com.drawingPlayer.org.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;


@Entity
@Table(name = "Rectangles")
public class Rectangle extends Shape{

    public Rectangle(int x, int y, int width, int height, boolean selected, int instrument, int playLineCord) {
        super(x, y, width, height, selected, instrument, playLineCord);
    }
    public Rectangle(){}
}
