package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Livorni on 11.11.2015.
 */

public class TagAttributesWindow {
    private ArrayList<String> attributesNameList;
    private ArrayList<Attribute> attributesList;
    private String tagName;
    private Attribute attribute;
    private Stage stage;
    private Group root;
    private Scene scene;
    private VBox vBox;
    private Button addButton, cancelButton;

    public TagAttributesWindow(String tagName) {
        this.tagName = tagName;
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
        addButton.setOnAction(event -> {
            CodeBrowser.getInstance().insertTag(generateTagString());
            stage.close();
        });

        cancelButton = new Button("Cancel");
        cancelButton.setLayoutX(125);
        cancelButton.setLayoutY(attributesList.size() * 35 + 25);
        cancelButton.setOnAction(event -> stage.close());

        root.getChildren().addAll(vBox, addButton, cancelButton);
        scene = new Scene(root);
        stage.setTitle("Set Attributes");
        stage.setScene(scene);
        stage.show();
    }

    private void getAttributes(String tagName) {
        attributesNameList = AttributeStorage.getInstance().getAttributesList(tagName);
        if (attributesNameList == null) return;
        for (String attributeName: attributesNameList) {
            attribute = new Attribute(attributeName);
            vBox.getChildren().add(attribute.getAttributeGroup());
            attributesList.add(attribute);
        }
    }

    public String generateTagString() {
        String tagString = "<";
        Integer numberOfElements = 0;
        tagString += tagName + ' ';
        for (Attribute attribute: attributesList) {
            tagString += (!Objects.equals(attribute.getAttributeValue(), "") && !Objects.equals(attribute.getAttributeName(), "Number of elements"))
                    ? attribute.getAttributeName() + "=\"" + attribute.getAttributeValue() + "\" " : "";
            if (Objects.equals(attribute.getAttributeName(), "Number of elements"))
                numberOfElements = Integer.parseInt(attribute.getAttributeValue());
        }
        tagString = tagString.substring(0, tagString.length() - 1);
        switch (tagName) {
            case "link":
                tagString += "/>";
                break;
            case "html":
            case "body":
            case "head":
            case "div":
            case "table":
                tagString += ">\n\n" + "</" + tagName + '>';
                break;
            case "ol":
            case "ul":
                tagString += ">\n";
                for (int i = 0; i < numberOfElements; i++)
                    tagString += "\t<li></li>\n";
                tagString += "</" + tagName + '>';
                break;
            default:
                tagString += "></" + tagName + '>';
        }
        tagName += '\n';
        return tagString;
    }
}
