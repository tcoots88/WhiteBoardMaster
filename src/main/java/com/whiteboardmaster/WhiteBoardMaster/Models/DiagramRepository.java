package com.whiteboardmaster.WhiteBoardMaster.Models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagramRepository extends JpaRepository<Diagram, Long> {
    public List<Diagram> findByApplicationUserId(long applicationUserId);
}
