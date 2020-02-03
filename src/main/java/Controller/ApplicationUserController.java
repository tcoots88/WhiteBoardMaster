package Controller;

import Models.ApplicationUser;
import Models.ApplicationUserRepository;
import Models.Diagram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ApplicationUserController {
    @Autowired
    ApplicationUserRepository userRepository;

    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public RedirectView createNewApplicationUser(String userName, String password, String firstName, String lastName){
        // make the user AND salt and hash the password
        // this does the salting and hashing for you
        ApplicationUser newUser = new ApplicationUser(userName, passwordEncoder.encode(password), firstName, lastName);

        // save the user to db
        userRepository.save(newUser);

        // send them back home
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }
}
