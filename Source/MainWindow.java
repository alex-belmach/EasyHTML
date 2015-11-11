package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Livorni on 11.11.2015.
 */

public class MainWindow {
    private static MainWindow instance;
    private static Stage stage;
    private Parent mainPageRoot;
    @FXML
    private AnchorPane anchorPane;

    public static MainWindow getInstance() {
        if (instance == null) {
            instance = new MainWindow();
        }
        return instance;
    }

    private MainWindow() {
        stage = Main.getStage();

        try {
            mainPageRoot = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        anchorPane = (AnchorPane)mainPageRoot.lookup("#mainAnchorPane");
        anchorPane.getChildren().add(CodeBrowser.getInstance().getTextArea());

        Scene scene = new Scene(mainPageRoot);
        stage.setScene(scene);
    }

    protected static void show() {
        stage.show();
    }
}
