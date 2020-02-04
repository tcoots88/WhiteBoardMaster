package com.whiteboardmaster.WhiteBoardMaster.Controllers;

import com.whiteboardmaster.WhiteBoardMaster.Models.*;
import com.whiteboardmaster.WhiteBoardMaster.Models.ApplicationUserRepository;
import com.whiteboardmaster.WhiteBoardMaster.Models.Diagram;
import com.whiteboardmaster.WhiteBoardMaster.Models.DiagramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.Principal;



@Controller
public class DiagramController {

    @Autowired
    ApplicationUserRepository userRepository;

    @Autowired
    DiagramRepository diagramRepository;

    /*
                    POST ROUTES
     */

    static { System.setProperty("java.awt.headless", "false");}

    @PostMapping("diagram/create")
    public RedirectView createDiagram(Principal p, String problemDomain, String algorithm, String pseudoCode, String bigONotation, String verification, String code, String edgeCases, String inputAndOutput, String visual) {

        if (p != null) {
            // get user and create new diagram
            ApplicationUser user = userRepository.findByUserName(p.getName());
            Diagram newDiagram = new Diagram(user, problemDomain, algorithm, pseudoCode, bigONotation, verification, code, edgeCases, inputAndOutput, visual);


        }


        return new RedirectView("/");
    }

    @PostMapping("/generate")
    public RedirectView generate()
    {
        System.out.println("CALLED");
        try
        {
            Robot robot = new Robot();
            String format = "jpg";
            String fileName = "WhiteBoard." + format;
            //Change constraints of rectangle to be containing window and not full window.
            Rectangle screen_Area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage generated_WhiteBoard = robot.createScreenCapture(screen_Area);
            ImageIO.write(generated_WhiteBoard, format, new File(fileName));
            //Change destination of file save to desktop
            System.out.println("A full screenshot saved!");
        }
        catch (AWTException | IOException e)
        {
            System.err.println(e);
        }
        return new RedirectView("/whiteBoard");
    }

    @GetMapping("/whiteBoard")
    public String getWhiteBoard()
    {
        return "/whiteBoard";
    }

    /*
                    GET ROUTES
     */
}
