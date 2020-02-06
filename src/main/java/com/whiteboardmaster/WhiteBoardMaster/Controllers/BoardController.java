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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

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
    Board board;


    @PostMapping("/createAndSaveBoard")
    public String createAndSaveBoard(Model m, Principal p, String problemDomain, String algorithm, String pseudoCode, String bigOTimeNotation, String bigOSpaceNotation, String verification, String code, String edgeCases, String inputAndOutput, String visual, String title, HttpServletResponse response) throws IOException {

        Board newBoard = new Board(problemDomain, algorithm, pseudoCode, bigOTimeNotation, bigOSpaceNotation, verification, code, edgeCases, inputAndOutput, visual, title);

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
        this.addUserNameToPage(p, m);

        return "result";
    }

    @PostMapping("/updateAndSaveBoard")
    public RedirectView updateBoard(Principal p, Model m, long id, String problemDomain, String algorithm, String pseudoCode, String bigOTimeNotation, String bigOSpaceNotation, String verification, String code, String edgeCases, String inputAndOutput, String visual, String title, HttpServletResponse response) {

        Board boardToUpdate = boardRepository.getOne(id);

        boardToUpdate.setProblemDomain(problemDomain);
        boardToUpdate.setAlgorithm(algorithm);
        boardToUpdate.setPseudoCode(pseudoCode);
        boardToUpdate.setBigOTimeNotation(bigOTimeNotation);
        boardToUpdate.setBigOSpaceNotation(bigOSpaceNotation);
        boardToUpdate.setVerification(verification);
        boardToUpdate.setCode(code);
        boardToUpdate.setEdgeCases(edgeCases);
        boardToUpdate.setInputAndOutput(inputAndOutput);
        boardToUpdate.setVisual(visual);
        boardToUpdate.setTitle(title);

        boardRepository.save(boardToUpdate);

        m.addAttribute("board", boardToUpdate);
        this.addUserNameToPage(p, m);

        return new RedirectView("/profile");
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

        this.addUserNameToPage(p, m);


        return "result";
    }

    @GetMapping("/build")
    public String displayWhiteboardForm(Principal p, Model m) {

        this.addUserNameToPage(p, m);

        return "whiteboard";
    }

    @GetMapping("/delete")
    public RedirectView deleteWhiteBoardFromUserProfile(long id) {

        boardRepository.deleteById(id);

        return new RedirectView("/profile");
    }

    @GetMapping("/update")
    public String updateWhiteBoardFromUserProfile(long id, Principal p, Model m) {

        Board boardToUpdate = boardRepository.getOne(id);
        m.addAttribute("board", boardToUpdate);

        this.addUserNameToPage(p, m);

        return "whiteboardupdate";
    }

    private void addUserNameToPage(Principal p, Model m) {
        if (p != null) {
            m.addAttribute("username", p.getName());
        } else {
            m.addAttribute("username", "New user");
        }
    }
}
