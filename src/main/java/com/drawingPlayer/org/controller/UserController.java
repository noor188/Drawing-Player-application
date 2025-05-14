package com.drawingPlayer.org.controller;

import com.drawingPlayer.org.model.Drawing;
import com.drawingPlayer.org.model.User;
import com.drawingPlayer.org.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String getAllDrawings(Principal principal, Model model) {
        return "drawing-canvas";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user){
        userRepository.save(user);
        return "redirect:/user";
    }

    @GetMapping("/new")
    public String UserCreationForm(Model model){
        User user = new User();
        Drawing drawing = new Drawing();
        model.addAttribute("user", user);
        model.addAttribute("drawing", drawing);
        return "user-form";
    }


}
