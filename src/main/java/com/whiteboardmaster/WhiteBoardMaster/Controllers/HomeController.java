package com.whiteboardmaster.WhiteBoardMaster.Controllers;

import com.whiteboardmaster.WhiteBoardMaster.Models.ApplicationUserRepository;
import com.whiteboardmaster.WhiteBoardMaster.Models.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    ApplicationUserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @GetMapping("/")
    public String getHome(Principal p, Model m){

        if (p == null){
            m.addAttribute("username", "");
        } else {
            m.addAttribute("username", ", " + p.getName());
        }
        m.addAttribute("heroImgSrc", "/images/home.png");

        return "home";
    }
}