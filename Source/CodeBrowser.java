package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by Livorni on 11.11.2015.
 */

public class CodeBrowser {
    private static CodeBrowser instance;
    @FXML
    private TextArea textArea;
    private File file;

    public static CodeBrowser getInstance() {
        if (instance == null) {
            instance = new CodeBrowser();
        }
        return instance;
    }

    private CodeBrowser() {
        textArea = new TextArea();
        textArea.setLayoutX(15);
        textArea.setLayoutY(15);
        textArea.setPrefSize(485, 380);
        setText("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "</head>\n" +
                "<body>\n" +
                "</body>\n" +
                "</html>");
    }

    protected TextArea getTextArea() {
        return textArea;
    }

    protected void setText(String text) {
        textArea.setText(text);
    }

    protected String getText() {
        return textArea.getText();
    }

    protected void saveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save As..");
        FileChooser.ExtensionFilter extFilter =  new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html");
        fileChooser.getExtensionFilters().add(extFilter);
        file = fileChooser.showSaveDialog(Main.getStage());
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(CodeBrowser.getInstance().getText().getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open HTML File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html"),
                                                 new FileChooser.ExtensionFilter("Text Documents (*.txt)", "*.txt"));
        file = fileChooser.showOpenDialog(Main.getStage());
        if (file != null) {
            try {
                FileInputStream inFile = new FileInputStream(file);
                byte[] str = new byte[inFile.available()];
                inFile.read(str);
                CodeBrowser.getInstance().setText(new String(str));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void previewFile() {
        Desktop desktop = null;
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        }
        try {
            assert desktop != null;
            desktop.open(file);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
