package com.drawingPlayer.org.repository;

import com.drawingPlayer.org.model.Shape;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShapeRepository extends JpaRepository<Shape, Integer> {
}
