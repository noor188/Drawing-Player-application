package com.drawingPlayer.org.repository;

import com.drawingPlayer.org.model.Drawing;
import com.drawingPlayer.org.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String username);
    List<Drawing> getDrawingsByUserID(Integer ID);
}
