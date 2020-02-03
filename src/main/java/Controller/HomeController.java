package Controller;

import Models.ApplicationUser;
import Models.ApplicationUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {
    @GetMapping("/")
    public String getHome(Principal p, Model m) {
        System.out.println("----------HOME");
        if(p == null){
            m.addAttribute("username", "New user!");
        } else {
            m.addAttribute("username", p.getName());
        }

        return "home";
    }
}
