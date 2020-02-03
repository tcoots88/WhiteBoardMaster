package Controllers;

import Models.ApplicationUser;
import Models.ApplicationUserRepository;
import Models.DiagramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ApplicationUserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ApplicationUserRepository userRepository;

    @Autowired
    DiagramRepository diagramRepository;

    /*
                        USER ROUTES
     */
    @PostMapping("/user/register")
    public RedirectView register(HttpServletRequest request, String userName, String password, String firstName, String lastName) {

        // create user and add to database
        ApplicationUser newUser = new ApplicationUser(userName, passwordEncoder.encode(password), firstName, lastName);
        userRepository.save(newUser);

        // auto-login feature after creating account
        try {
            request.login(userName, password);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return new RedirectView("/");
    }
}
