package com.drawingPlayer.org.controller;

import com.drawingPlayer.org.model.Drawing;
import com.drawingPlayer.org.model.User;
import com.drawingPlayer.org.repository.UserRepository;
import com.drawingPlayer.org.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
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


}
