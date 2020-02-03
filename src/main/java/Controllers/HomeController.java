package Controllers;

import Models.ApplicationUser;
import Models.ApplicationUserRepository;
import Models.Diagram;
import Models.DiagramRepository;
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
    public String displayIndex(Principal p, Model m) {

        if (p != null) {
            // get user and user's list of diagrams
            ApplicationUser user = userRepository.findByUserName(p.getName());
            List<Diagram> diagrams = diagramRepository.findByApplicationUserId(user.getId());

            // add user and list to model
            m.addAttribute("user", user);
            m.addAttribute("diagrams", diagrams);
        }
        return "index";
    }
}