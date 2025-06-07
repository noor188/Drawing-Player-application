package com.drawingPlayer.org.service;

import com.drawingPlayer.org.model.Drawing;
import com.drawingPlayer.org.model.User;
import com.drawingPlayer.org.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DrawingService drawingService;

    public User saveUser(User user, List<Drawing> drawings){
        user.setDrawings(drawings);
        return userRepository.save(user);
    }

    public Optional<User> findUserbyId(Integer id){
        return userRepository.findById(id);
    }

    public Optional<User> findUserByUsername(String username){
        return userRepository.findByUserName(username);
    }

    public List<Drawing> getAllDrawings(Integer userID){
        return findUserbyId(userID).get().getDrawings();
    }
}

