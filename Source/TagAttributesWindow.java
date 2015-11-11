package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * Created by Livorni on 11.11.2015.
 */

public class TagAttributesWindow {
    private ArrayList<String> attributesNameList;
    private ArrayList<Attribute> attributesList;
    private Attribute attribute;
    private Stage stage;
    private Group root;
    private Scene scene;
    private VBox vBox;
    private Button addButton, cancelButton;

    public TagAttributesWindow(String tagName) {
        stage = new Stage();
        root = new Group();
        vBox = new VBox();
        attributesList = new ArrayList<>();

        getAttributes("common");
        getAttributes(tagName);

        vBox.setLayoutX(15);
        vBox.setLayoutY(15);
        vBox.setSpacing(10);

        addButton = new Button("Add");
        addButton.setLayoutX(30);
        addButton.setLayoutY(attributesList.size() * 35 + 25);
        cancelButton = new Button("Cancel");
        cancelButton.setLayoutX(125);
        cancelButton.setLayoutY(attributesList.size() * 35 + 25);

        root.getChildren().addAll(vBox, addButton, cancelButton);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void getAttributes(String tagName) {
        attributesNameList = AttributeStorage.getInstance().getAttributesList(tagName);
        if (attributesNameList == null) return;
        for (String attributeName: attributesNameList) {
            attribute = new Attribute(attributeName);
            vBox.getChildren().add(attribute.getAttributeHBox());
            attributesList.add(attribute);
        }
    }
}
