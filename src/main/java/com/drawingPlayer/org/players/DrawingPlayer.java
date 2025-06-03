package com.drawingPlayer.org.players;

import com.drawingPlayer.org.model.Drawing;
import com.drawingPlayer.org.model.Shape;
import com.drawingPlayer.org.sound.MidiSynth;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DrawingPlayer implements ActionListener {

    private Drawing drawing;
    private Timer timer;
    private int playingColumn;
    private int canvasWidth;
    private MidiSynth midiSynth;

    private List<Shape> lastColumnPlayed;
    private List<Shape> shapesInColumn;

    // EFFECTS: constructs a DrawingPlayer
    public DrawingPlayer(Drawing drawing, Timer timer, int canvasWidth, MidiSynth midiSynth){
        this.drawing = drawing;
        this.timer = timer;
        this.canvasWidth = canvasWidth;
        this.midiSynth = midiSynth;
        playingColumn = 0;
        lastColumnPlayed = new ArrayList<Shape>();
        shapesInColumn = new ArrayList<Shape>();
    }

    // MODIFIES: this
    // EFFECTS:  plays shapes in current column, repaints, increments column,  stops if done
    //           this class is the listener for the timer object, and this method is what the timer calls
    //           each time through its loop.
    @Override
    public void actionPerformed(ActionEvent e) {
        selectAndPlayShapes();
        drawRedLine();
        incrementColumn();
        stopPlayingWhenDone();
    }

    // MODIFIES: this
    // EFFECTS:  moves current x-column to next column; updates figures
    private void incrementColumn() {
        playingColumn += 1;
        lastColumnPlayed = shapesInColumn;
    }

    // MODIFIES: this
    // EFFECTS:  moves playback line to playingColumn to trigger sound and repaint
    private void drawRedLine() {
        drawing.setPlayLineColumn(playingColumn);
//        drawing.repaint(); // the Java Graphics framework will call Drawing.paintComponent()
    }

    // MODIFIES: this
    // EFFECTS:  calls Timer.stop() when playingColumn is past the edge of the frame
    private void stopPlayingWhenDone() {
        if (playingColumn > this.canvasWidth){
            timer.stop();
        }
    }

    // MODIFIES: this
    // EFFECTS:  selects and plays shape(s) in the playingColumn
    private void selectAndPlayShapes() {
        shapesInColumn = drawing.getShapesAtColumn(playingColumn);

        for (Shape shape : lastColumnPlayed) {
            if (!shapesInColumn.contains(shape)) {
                shape.unselectAndStopPlaying(midiSynth);
            }
        }

        for (Shape shape : shapesInColumn) {
            if (!lastColumnPlayed.contains(shape)) {
                shape.selectAndPlay(midiSynth);
            }
        }
    }
}
