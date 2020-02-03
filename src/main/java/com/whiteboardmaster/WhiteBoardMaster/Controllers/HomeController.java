package com.whiteboardmaster.WhiteBoardMaster.Controllers;

import com.whiteboardmaster.WhiteBoardMaster.Models.ApplicationUser;
import com.whiteboardmaster.WhiteBoardMaster.Models.ApplicationUserRepository;
import com.whiteboardmaster.WhiteBoardMaster.Models.DiagramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ApplicationUserRepository userRepository;

    @Autowired
    DiagramRepository diagramRepository;

    @GetMapping("/")
    public String getHome(Principal p, Model m){

        if(p == null){
            m.addAttribute("username", "New User");
        } else {
            m.addAttribute("username", p.getName());
        }

        return "home";
    }
}