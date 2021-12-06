package qrjson.input;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

public class display extends JFrame {
    Color steamRed = new Color(78, 22, 20);// correct
    Color steamRedA = new Color(98, 28, 25);// light
    Color steamRedB = new Color(46, 13, 12);// dark
    Color steamGold = new Color(178, 159, 76);// gold

    public Webcam webcam = null;
    public WebcamPanel panel = null;
    public JTextArea textarea = new JTextArea("test text");
    public JTextField soucefolder = new JTextField();

    public void init() {
        setSize(465, 1080);
        setLayout(null);
        setVisible(true);
        setTitle("SteamCamera 2021");
        getContentPane().setBackground(steamRed);

        webcam = Webcam.getWebcams().get(0);

        panel = new WebcamPanel(webcam);
        panel.setBounds(0, 0, 465, 345);
        panel.setFPSDisplayed(true);
        add(panel);

        textarea.setBounds(0, 345, 465, 50);
        textarea.setBackground(steamRedA);
        textarea.setForeground(steamGold);
        add(textarea);
        textarea.setVisible(true);

        try {
            soucefolder.setText(Files.readString(
                    Paths.get("/Users/lostl/Desktop/code/java/steam_camera/src/main/java/qrjson/input/lastRepo.txt")));
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        soucefolder.setBounds(0, 395, 465, 20);
        soucefolder.setBackground(steamRedA);
        soucefolder.setForeground(steamGold);
        add(soucefolder);
        soucefolder.setVisible(true);
    }
}
