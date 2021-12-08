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

    stringToJson s = new stringToJson();

    public Webcam webcam = null;
    public WebcamPanel panel = null;
    public JTextArea textarea = new JTextArea("test text");
    public JTextField soucefolder = new JTextField();
    public JButton upload = new JButton("push");

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

        add(upload);
        upload.setBounds(0,400,50,30);
        upload.setBackground(steamRedA);
        upload.setForeground(steamGold);
        upload.setVisible(true);
        upload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    s.makeJson(textarea.getText(),soucefolder.getText());
                } catch (IOException | ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
    
}
