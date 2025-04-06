package com.drawingPlayer.org.service;

import com.drawingPlayer.org.model.Drawing;
import com.drawingPlayer.org.model.Impl.Oval;
import com.drawingPlayer.org.model.Shape;
import com.drawingPlayer.org.repository.ShapeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OvalService {

    @Autowired
    private ShapeRepository shapeRepository;

    public List<Shape> getAllOvals(){
        return shapeRepository.findAll();
    }
    public void createOval(Shape shape) {
        shapeRepository.save(shape);
    }

    public void getOvalById(Integer id){
        shapeRepository.findById(id);
    }

    public Shape updateOval(Integer id, Shape updatedeOval) {
        return shapeRepository.findById(id)
                .map(oval -> {
                    oval.setX(updatedeOval.getX());
                    oval.setY(updatedeOval.getY());
                    oval.setWidth(updatedeOval.getWidth());
                    oval.setHeight(updatedeOval.getHeight());
                    oval.setSelected(updatedeOval.isSelected());
                    oval.setMidiSynth(updatedeOval.getMidiSynth());
                    oval.setInstrument(updatedeOval.getInstrument());
                    oval.setPlayLineCord(updatedeOval.getPlayLineCord());
                    return shapeRepository.save(oval);
                })
                .orElseThrow(() -> new RuntimeException("Drawing not found"));
    }

    public void deleteOval(Integer id){
        shapeRepository.deleteById(id);
    }

}
