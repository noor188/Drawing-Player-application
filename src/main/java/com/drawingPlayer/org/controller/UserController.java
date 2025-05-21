package com.drawingPlayer.org.controller;

import com.drawingPlayer.org.model.Drawing;
import com.drawingPlayer.org.model.Shape;
import com.drawingPlayer.org.model.User;
import com.drawingPlayer.org.repository.UserRepository;
import com.drawingPlayer.org.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/home/{id}")
    public String getAllDrawings(@PathVariable Integer id, Model model) {
        Optional<User> user = userService.findUserbyId(id);

        if(user.isPresent()){
            List<Drawing> drawings = user.get().getDrawings();
            model.addAttribute("user", user.get());
            model.addAttribute("drawings", drawings);
            return "drawing-canvas";
        }
        return "redirect:/user/new";

    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/user/home/" + user.getUserID();
    }

    @GetMapping("/new")
    public String UserCreationForm(Model model){
        User user = new User();
        Drawing drawing = new Drawing();
        model.addAttribute("user", user);
        model.addAttribute("drawing", drawing);
        return "user-form";
    }

    @PostMapping("/playmusic")
    public ResponseEntity<Map<String, String>> UserPlayDrawing(@RequestBody List<Shape> shapes){
        System.out.println("recived JSON" + shapes);
        Map<String, String> response = new HashMap<>();

        try{
            for (Shape shape: shapes){
                System.out.println("Received shape: " + shape);
            }
            response.put("message", "shapes received");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            response.put("message", "An error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
