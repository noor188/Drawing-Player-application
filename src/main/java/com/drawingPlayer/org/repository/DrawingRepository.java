package com.drawingPlayer.org.repository;

import com.drawingPlayer.org.model.Drawing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrawingRepository extends JpaRepository<Drawing, Integer> {
}
