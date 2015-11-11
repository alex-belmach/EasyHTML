package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;



/**
 * Created by Livorni on 11.11.2015.
 */

public class CodeBrowser {
    private static CodeBrowser instance;
    @FXML
    private TextArea textArea;

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
        textArea.setPrefSize(350, 300);
    }

    protected TextArea getTextArea() {
        return textArea;
    }
}
