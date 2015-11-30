package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


/**
 * Created by Livorni on 11.11.2015.
 */

public class CodeBrowser {
    private static CodeBrowser instance;
    private Integer caretPosition = 0;
    @FXML
    private TextArea textArea;
    private File file;
    private final ArrayList<String> basis;

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
        textArea.setPrefSize(508, 380);
        textArea.setOnMouseClicked(event -> caretPosition = textArea.getCaretPosition());
        textArea.setOnKeyReleased(event -> caretPosition = textArea.getCaretPosition());
        basis = new ArrayList<>();
        basis.add("<!DOCTYPE html>");
        basis.add("<html>");
        basis.add("<head>");
        basis.add("<meta charset=\"utf-8\">");
        basis.add("<title>");
        basis.add("</title>");
        basis.add("</head>");
        basis.add("<body>");
        basis.add("</body>");
        basis.add("</html>");

        setText("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<meta charset=\"utf-8\">\n" +
                "\t<title></title>\n\n" +
                "</head>\n" +
                "<body>\n\n" +
                "</body>\n" +
                "</html>");
    }

    protected void insertTag(String tagString) {
        textArea.insertText(caretPosition, tagString);
    }

    protected void saveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save As..");
        FileChooser.ExtensionFilter extFilter =  new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html");
        fileChooser.getExtensionFilters().add(extFilter);
        file = fileChooser.showSaveDialog(Main.getStage());
        if (file != null) {
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                outputStream.write(CodeBrowser.getInstance().getText().getBytes());
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        if (Objects.equals(file, null))
            saveFile();
        if (Objects.equals(file, null))
            return;
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        }
        try {
            if (checkBasis()) {
                assert desktop != null;
                desktop.open(file);
            } else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Code must contains all main tags!");
                alert.showAndWait();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
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

    private boolean checkBasis() {
        for (String tag: basis) {
            if (!textArea.getText().contains(tag))
                return false;
        }
        return true;
    }
}
