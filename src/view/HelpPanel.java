package view;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

import static java.nio.file.Files.lines;

/**
 * Created by Moritz on 11/21/2016.
 * <p></p>
 */
public class HelpPanel extends JPanel{
    private JTextArea text = new JTextArea();

    public HelpPanel(){
        Stream<String> files;

        try {
            files = Files.lines(new File("PA-3-Manual.txt").toPath());
            files.forEach(line -> text.append(line+"\n"));
        } catch (IOException e) {
            text.setText("Could not find file 'PA-3-Manual.txt'.\nRefer to this file for a user guide.");
        }

        add(text);
    }
}
