package com.drawingPlayer.org.model;

import java.awt.*;

import com.drawingPlayer.org.sound.MidiSynth;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Shape {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    final Color PLAYING_COLOR;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean selected;
    @Transient
    private MidiSynth midiSynth;
    private int instrument;
    private int playLineCord;

    @ManyToOne
    @JoinColumn(name = "DrawingID")
    private Drawing drawing;

    public Shape(){
        PLAYING_COLOR = new Color(230, 158, 60);
    }

    public Shape(int x, int y, int width, int height, boolean selected, int instrument, int playLineCord){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.selected = selected;
        this.instrument = instrument;
        this.playLineCord = playLineCord;
        PLAYING_COLOR = new Color(230, 158, 60);
    }
}
