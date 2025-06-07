package com.drawingPlayer.org.model;

import com.drawingPlayer.org.model.Impl.Oval;
import com.drawingPlayer.org.model.Impl.Rectangle;
import com.drawingPlayer.org.sound.MidiSynth;
import jakarta.persistence.*;
import lombok.Data;
import com.drawingPlayer.org.sound.MidiSynth;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Drawings")
@Data
public class Drawing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "DrawingID")
    private Integer id;
    private final int MUSIC_LINES_SPACE;
    private int playLineColumn;
//    private MidiSynth midiSynth;

    @OneToMany(mappedBy = "drawing", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Shape> shapes;

    @OneToMany(mappedBy = "drawing", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Oval> ovals;

    @OneToMany(mappedBy = "drawing", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rectangle> rectangles;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    public Drawing(){
        this.MUSIC_LINES_SPACE = 30;
        this.ovals = new ArrayList<Oval>();
        this.rectangles = new ArrayList<Rectangle>();
    }

    // EFFECTS: returns all Shapes at given column corresponding to an x-coordinate
    public List<Shape> getShapesAtColumn(int x) {
        List<Shape> shapesAtColumn = new ArrayList<Shape>();
        for (Shape shape : shapes) {
            if (shape.containsX(x))
                shapesAtColumn.add(shape);
        }
        return shapesAtColumn;
    }
}
