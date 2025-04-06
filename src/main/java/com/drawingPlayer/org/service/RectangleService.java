package com.drawingPlayer.org.service;

import com.drawingPlayer.org.model.Shape;
import com.drawingPlayer.org.repository.ShapeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RectangleService {

    @Autowired
    private ShapeRepository shapeRepository;

    public List<Shape> getAllRectangles(){
        return shapeRepository.findAll();
    }
    public void createRectangle(Shape shape) {
        shapeRepository.save(shape);
    }

    public void getRectangleById(Integer id){
        shapeRepository.findById(id);
    }

    public Shape updateRectangle(Integer id, Shape updatedeRectangle) {
        return shapeRepository.findById(id)
                .map(rectangle -> {
                    rectangle.setX(updatedeRectangle.getX());
                    rectangle.setY(updatedeRectangle.getY());
                    rectangle.setWidth(updatedeRectangle.getWidth());
                    rectangle.setHeight(updatedeRectangle.getHeight());
                    rectangle.setSelected(updatedeRectangle.isSelected());
                    rectangle.setMidiSynth(updatedeRectangle.getMidiSynth());
                    rectangle.setInstrument(updatedeRectangle.getInstrument());
                    rectangle.setPlayLineCord(updatedeRectangle.getPlayLineCord());
                    return shapeRepository.save(rectangle);
                })
                .orElseThrow(() -> new RuntimeException("Rectangle not found"));
    }

    public void deleteRectangle(Integer id){
        shapeRepository.deleteById(id);
    }

}