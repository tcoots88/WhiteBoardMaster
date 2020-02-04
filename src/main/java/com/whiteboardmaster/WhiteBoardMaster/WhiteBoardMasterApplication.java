package com.whiteboardmaster.WhiteBoardMaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SpringBootApplication
public class WhiteBoardMasterApplication
{

	public static void main(String[] args) {
		SpringApplication.run(WhiteBoardMasterApplication.class, args);
	}
}
