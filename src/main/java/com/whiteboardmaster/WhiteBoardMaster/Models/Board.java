package com.whiteboardmaster.WhiteBoardMaster.Models;
// code review: nicely organized and good use of comments
import net.steppschuh.markdowngenerator.image.Image;
import net.steppschuh.markdowngenerator.text.Text;
import net.steppschuh.markdowngenerator.text.heading.Heading;
import org.springframework.http.MediaType;

import javax.persistence.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Entity
public class Board {


    /*
                                INSTANCE VARIABLES
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private ApplicationUser applicationUser;

    private String problemDomain;
    private String algorithm;
    private String pseudoCode;
    private String bigOTimeNotation;
    private String bigOSpaceNotation;
    private String verification;
    private String code;
    private String edgeCases;
    private String inputAndOutput;
    private String visual;
    private String title;


    /*
                                CONSTRUCTORS
    */
    public Board() {

    }

    public Board(String problemDomain, String algorithm, String pseudoCode, String bigOTimeNotation, String bigOSpaceNotation, String verification, String code, String edgeCases, String inputAndOutput, String visual, String title) {
        this.problemDomain = problemDomain;
        this.algorithm = algorithm;
        this.pseudoCode = pseudoCode;
        this.bigOTimeNotation = bigOTimeNotation;
        this.bigOSpaceNotation = bigOSpaceNotation;
        this.verification = verification;
        this.code = code;
        this.edgeCases = edgeCases;
        this.inputAndOutput = inputAndOutput;
        this.visual = visual;
        this.title = title;
    }


    /*
                                SETTERS
    */
    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public void setProblemDomain(String problemDomain) {
        this.problemDomain = problemDomain;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setPseudoCode(String pseudoCode) {
        this.pseudoCode = pseudoCode;
    }

    public void setBigOTimeNotation(String bigOTimeNotation) {
        this.bigOTimeNotation = bigOTimeNotation;
    }

    public void setBigOSpaceNotation(String bigOSpaceNotation) {
        this.bigOSpaceNotation = bigOSpaceNotation;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setEdgeCases(String edgeCases) {
        this.edgeCases = edgeCases;
    }

    public void setInputAndOutput(String inputAndOutput) {
        this.inputAndOutput = inputAndOutput;
    }

    public void setVisual(String visual) {
        this.visual = visual;
    }

    public void setTitle(String title) { this.title = title; }


    /*
                                GETTERS
    */
    public long getId() {
        return id;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public String getProblemDomain() {
        return problemDomain;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getPseudoCode() {
        return pseudoCode;
    }

    public String getBigOTimeNotation() {
        return bigOTimeNotation;
    }

    public String getBigOSpaceNotation() {
        return bigOSpaceNotation;
    }

    public String getVerification() {
        return verification;
    }

    public String getCode() {
        return code;
    }

    public String getEdgeCases() {
        return edgeCases;
    }

    public String getInputAndOutput() {
        return inputAndOutput;
    }

    public String getVisual() {
        return visual;
    }

    public String getTitle() { return title; }

    public String toMarkDown(HttpServletResponse response) throws IOException {
        StringBuilder md_String = new StringBuilder()
                .append(new Heading("Summary", 1)).append("\n")
                .append(new Text(this.problemDomain)).append("\n")
                .append(new Text("                                               ")).append("\n")
                .append(new Text("                                               ")).append("\n")
                .append(new Heading("Description", 2)).append("\n")
                .append(new Text(this.algorithm)).append("\n")
                .append(new Text("                                               ")).append("\n")
                .append(new Text("                                               ")).append("\n")
                .append(new Heading("Approach & Efficiency", 2)).append("\n")
                .append(new Text(this.pseudoCode)).append("\n")
                .append(new Text("Time: " + this.bigOTimeNotation)).append("\n")
                .append(new Text("Space: " + this.bigOSpaceNotation)).append("\n")
                .append(new Text("                                               ")).append("\n")
                .append(new Text("                                               ")).append("\n")
                .append(new Heading("Solution", 2)).append("\n")
                .append(new Image("White Board", "WhiteBoard.png"));


        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Content-Disposition","attachment;filename=README-" + title + ".md");
        ServletOutputStream out = response.getOutputStream();
        out.println(md_String.toString());
        out.flush();
        out.close();


        return "result";
    }

}
