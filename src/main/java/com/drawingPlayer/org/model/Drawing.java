package com.drawingPlayer.org.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Drawings")
@Data
public class Drawing extends JPanel {

    @Id
    @Column(name = "DrawingID")
    private int id;
    private final int MUSIC_LINES_SPACE;
    private int playLineColumn;

    @OneToMany(mappedBy = "DrawingID", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Oval> ovals;

    @OneToMany(mappedBy = "DrawingID", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rectangle> rectangles;

    public Drawing(){
        super();
        this.MUSIC_LINES_SPACE = 30;
        this.ovals = new ArrayList<Oval>();
        this.rectangles = new ArrayList<Rectangle>();
        setBackground(Color.WHITE);
    }
}
