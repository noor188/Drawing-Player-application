package com.drawingPlayer.org.service;

import com.drawingPlayer.org.model.Drawing;
import com.drawingPlayer.org.repository.DrawingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrawingService {

    @Autowired
    private DrawingRepository drawingRepository;

    public List<Drawing> getAllDrawings(){
        return drawingRepository.findAll();
    }
    public void createDrawing(Drawing drawing) {
        drawingRepository.save(drawing);
    }

    public void getDrawingById(Integer id){
        drawingRepository.findById(id);
    }

    public Drawing updateDrawing(Integer id, Drawing updatedeDrawing) {
        return drawingRepository.findById(id)
                .map(drawing -> {
                    drawing.setOvals(updatedeDrawing.getOvals());
                    drawing.setRectangles(updatedeDrawing.getRectangles());
                    drawing.setPlayLineColumn(updatedeDrawing.getPlayLineColumn());
                    return drawingRepository.save(drawing);
                })
                .orElseThrow(() -> new RuntimeException("Drawing not found"));
    }

    public void deleteDrawing(Integer id){
        drawingRepository.deleteById(id);
    }

}
