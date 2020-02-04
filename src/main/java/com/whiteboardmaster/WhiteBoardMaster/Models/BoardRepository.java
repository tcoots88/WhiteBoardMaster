package com.whiteboardmaster.WhiteBoardMaster.Models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    public List<Board> findByApplicationUserId(long applicationUserId);
}
