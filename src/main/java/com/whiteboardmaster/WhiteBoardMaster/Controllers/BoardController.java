package com.whiteboardmaster.WhiteBoardMaster.Controllers;

import com.whiteboardmaster.WhiteBoardMaster.Models.*;
import com.whiteboardmaster.WhiteBoardMaster.Models.ApplicationUserRepository;
import com.whiteboardmaster.WhiteBoardMaster.Models.Board;
import com.whiteboardmaster.WhiteBoardMaster.Models.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @PostMapping("/createBoard")
    public String createBoard(Model m, String problemDomain, String algorithm, String pseudoCode, String bigONotation, String verification, String code, String edgeCases, String inputAndOutput, String visual, String title) {

        Board newBoard = new Board(problemDomain, algorithm, pseudoCode, bigONotation, verification, code, edgeCases, inputAndOutput, visual, title);
        m.addAttribute("board", newBoard);

        return "whiteBoard";
    }

    @PostMapping("/saveBoard")
    public RedirectView saveBoard(Board boardToSave, Principal p) {

        if (p != null) {
            // get user and save board to their account
            ApplicationUser user = userRepository.findByUserName(p.getName());
            boardToSave.setApplicationUser(user);
            user.addBoard(boardToSave);
            userRepository.save(user);
        }

        return new RedirectView("/whiteBoard");
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
        return new RedirectView("/whiteBoard");
    }


    /*
                    GET ROUTES
    */
    @GetMapping("/getBoard")
    public String getBoardFromUserProfile(Principal p, Model m, long boardId) {

        Board boardFromDataBase = boardRepository.getOne(boardId);
        m.addAttribute("board", boardFromDataBase);

        return "whiteBoard";
    }

    @GetMapping("/build")
    public String displayWhiteboardForm(Principal p, Model m) {

        return "whiteboard";
    }
}
