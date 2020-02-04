package com.whiteboardmaster.WhiteBoardMaster.Models;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
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
    private String bigONotation;
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

    public Board(String problemDomain, String algorithm, String pseudoCode, String bigONotation, String verification, String code, String edgeCases, String inputAndOutput, String visual, String title) {
        this.problemDomain = problemDomain;
        this.algorithm = algorithm;
        this.pseudoCode = pseudoCode;
        this.bigONotation = bigONotation;
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

    public void setBigONotation(String bigONotation) {
        this.bigONotation = bigONotation;
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

    public String getBigONotation() {
        return bigONotation;
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

}
