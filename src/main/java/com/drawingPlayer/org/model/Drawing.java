package com.drawingPlayer.org.model;

import com.drawingPlayer.org.model.Impl.Oval;
import com.drawingPlayer.org.model.Impl.Rectangle;
import jakarta.persistence.*;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Drawings")
@Data
public class Drawing {

    @Id
    @Column(name = "DrawingID")
    private Integer id;
    private final int MUSIC_LINES_SPACE;
    private int playLineColumn;

    @OneToMany(mappedBy = "drawing", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Oval> ovals;

    @OneToMany(mappedBy = "drawing", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rectangle> rectangles;

    public Drawing(){
        super();
        this.MUSIC_LINES_SPACE = 30;
        this.ovals = new ArrayList<Oval>();
        this.rectangles = new ArrayList<Rectangle>();
    }
}
