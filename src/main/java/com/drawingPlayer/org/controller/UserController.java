package com.drawingPlayer.org.controller;

import com.drawingPlayer.org.model.Drawing;
import com.drawingPlayer.org.model.PlayRequest;
import com.drawingPlayer.org.model.Shape;
import com.drawingPlayer.org.model.User;
import com.drawingPlayer.org.players.DrawingPlayer;
import com.drawingPlayer.org.service.UserService;
import com.drawingPlayer.org.sound.MidiSynth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    private MidiSynth midiSynth;
    private Optional<User> user;

    @GetMapping("/home/{id}")
    public String getAllDrawings(@PathVariable Integer id, Model model) {
        user = userService.findUserbyId(id);

        // initialize midiSynth
        midiSynth = new MidiSynth();
        midiSynth.open();

        if(user.isPresent()){
            List<Drawing> drawings = user.get().getDrawings();
            drawings.add(new Drawing());
            model.addAttribute("user", user.get());
            model.addAttribute("drawings", drawings);
            return "drawing-canvas";
        }
        return "redirect:/user/new";

    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user){
        List<Drawing> drawings = new ArrayList<>();
        Drawing drawing = new Drawing();
        drawing.setUser(user);
        drawings.add(drawing);
        userService.saveUser(user, drawings);
        return "redirect:/user/home/" + user.getUserID();
    }

    @GetMapping("/new")
    public String UserCreationForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "user-form";
    }

    @PostMapping("/playmusic")
    public ResponseEntity<Map<String, String>> UserPlayDrawing(@RequestBody PlayRequest request){
        List<Shape> shapes = request.getShapes();
        Integer userID = request.getUserID();
        List<Drawing> drawings = userService.getAllDrawings(userID);
        drawings.get(0).setShapes(shapes);
        int canvasWidth = request.getCanvasWidth();
        Map<String, String> response = new HashMap<>();
//
//        try{
//            for (Shape shape: shapes){
//                System.out.println("Received shape: " + shape);
//            }
//            response.put("message", "shapes received");
//            return ResponseEntity.ok(response);
//        }catch (Exception e){
//            response.put("message", "An error occurred");
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
        final Timer t = new Timer(2, null);

        ActionListener drawingPlayer = new DrawingPlayer(drawings.get(0), t, canvasWidth, midiSynth);
        t.addActionListener(drawingPlayer);
        t.setInitialDelay(0);
        t.start();
        response.put("message", "shapes received");
        return ResponseEntity.ok(response);
    }

}
