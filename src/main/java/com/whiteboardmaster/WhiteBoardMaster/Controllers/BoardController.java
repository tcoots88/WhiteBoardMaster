package com.whiteboardmaster.WhiteBoardMaster.Controllers;

import com.whiteboardmaster.WhiteBoardMaster.Models.*;
import com.whiteboardmaster.WhiteBoardMaster.Models.ApplicationUserRepository;
import com.whiteboardmaster.WhiteBoardMaster.Models.Board;
import com.whiteboardmaster.WhiteBoardMaster.Models.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.Principal;
import java.util.Collection;
import java.util.Locale;


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

    Board board;


    @PostMapping("/createAndSaveBoard")
    public String createAndSaveBoard(Model m, Principal p, String problemDomain, String algorithm, String pseudoCode, String bigOTimeNotation, String verification, String code, String edgeCases, String inputAndOutput, String visual, String title, HttpServletResponse response) throws IOException {

        Board newBoard = new Board(problemDomain, algorithm, pseudoCode, bigOTimeNotation, bigOTimeNotation, verification, code, edgeCases, inputAndOutput, visual, title);

        m.addAttribute("board", newBoard);
        board = newBoard;

        if (p != null) {
            // get user and save board to their account
            ApplicationUser user = userRepository.findByUserName(p.getName());
            newBoard.setApplicationUser(user);
            boardRepository.save(newBoard);
            user.addBoard(newBoard);
            userRepository.save(user);
        }
        System.out.println("TEST");
        return "result";
    }

    @PostMapping("/save")
    public void saveMD( HttpServletResponse response) throws IOException
    {
        board.toMarkDown(response);
    }

    @WebServlet("/createAndSaveBoard")
    public class DownloadServlet extends HttpServlet
    {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {

            board.toMarkDown(resp);

        }
    }


    /*
                    GET ROUTES
    */


    @GetMapping("/board/{id}")
    public String getBoardFromUserProfile(@PathVariable long id, Principal p, Model m, HttpServletResponse response) throws IOException {

        Board boardFromDataBase = boardRepository.getOne(id);
        m.addAttribute("board", boardFromDataBase);
        board = boardFromDataBase;

        boardFromDataBase.toMarkDown(response);

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
