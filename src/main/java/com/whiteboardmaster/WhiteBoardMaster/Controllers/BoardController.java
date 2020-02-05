package com.whiteboardmaster.WhiteBoardMaster.Controllers;

import com.whiteboardmaster.WhiteBoardMaster.Models.*;
import com.whiteboardmaster.WhiteBoardMaster.Models.ApplicationUserRepository;
import com.whiteboardmaster.WhiteBoardMaster.Models.Board;
import com.whiteboardmaster.WhiteBoardMaster.Models.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.Principal;


@Controller
public class BoardController {

    @Autowired
    ApplicationUserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    /*
                    POST ROUTES
    */

    static {
        System.setProperty("java.awt.headless", "false");
    }

    @PostMapping("/createAndSaveBoard")
    public String createAndSaveBoard(Model m, Principal p, String problemDomain, String algorithm, String pseudoCode, String bigOTimeNotation, String verification, String code, String edgeCases, String inputAndOutput, String visual, String title) throws IOException {

        Board newBoard = new Board(problemDomain, algorithm, pseudoCode, bigOTimeNotation, bigOTimeNotation, verification, code, edgeCases, inputAndOutput, visual, title);
        newBoard.toMarkDown();
        m.addAttribute("board", newBoard);

        if (p != null) {
            // get user and save board to their account
            ApplicationUser user = userRepository.findByUserName(p.getName());
            newBoard.setApplicationUser(user);
            boardRepository.save(newBoard);
            user.addBoard(newBoard);
            userRepository.save(user);
        }
        return "result";
    }

    @PostMapping("/generate")
    public RedirectView generate() {
        System.out.println("CALLED");
        try {
            Robot robot = new Robot();
            String format = "jpg";
            String fileName = "WhiteBoard." + format;
            //Change constraints of rectangle to be containing window and not full window.
            Rectangle screen_Area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage generated_WhiteBoard = robot.createScreenCapture(screen_Area);
            ImageIO.write(generated_WhiteBoard, format, new File(fileName));



            //Change destination of file save to desktop
            System.out.println("A full screenshot saved!");
        } catch (AWTException | IOException e) {
            System.err.println(e);
        }
        return new RedirectView("/whiteboard");
    }


    /*
                    GET ROUTES
    */
    @GetMapping("/board/{id}")
    public String getBoardFromUserProfile(@PathVariable long id, Principal p, Model m) {

        Board boardFromDataBase = boardRepository.getOne(id);
        m.addAttribute("board", boardFromDataBase);

        return "result";
    }

    @GetMapping("/testboard")
    public String returnEmptyBoard()
    {

        return "result";
    }

    @GetMapping("/build")
    public String displayWhiteboardForm(Principal p, Model m) {

        return "whiteboard";
    }

    @GetMapping("/boards")
    public String displayWhiteboardResultForm(Principal p, Model m) {

        return "result";
    }
}
