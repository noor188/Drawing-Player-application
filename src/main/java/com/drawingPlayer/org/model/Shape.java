package com.drawingPlayer.org.model;

import java.awt.*;

import com.drawingPlayer.org.model.Impl.Oval;
import com.drawingPlayer.org.model.Impl.Rectangle;
import com.drawingPlayer.org.sound.MidiSynth;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Oval.class, name = "OVAL"),
        @JsonSubTypes.Type(value = Rectangle.class, name = "RECTANGLE")
})
public abstract class Shape {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    final Color PLAYING_COLOR;
    @Enumerated(EnumType.STRING)
    private Type type;
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
        this.PLAYING_COLOR = new Color(230, 158, 60);
    }

    public Shape(int x, int y, int width, int height, Type type){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        PLAYING_COLOR = new Color(230, 158, 60);
    }
}
