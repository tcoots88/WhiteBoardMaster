package com.whiteboardmaster.WhiteBoardMaster.Models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    public ApplicationUser findByUserName(String userName);
}
