package sample;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Created by Livorni on 11.11.2015.
 */

public class Attribute {
    private Label attributeLabel;
    private TextField attributeTextField;
    private Group attributeGroup;

    Attribute(String attributeName) {
        attributeLabel = new Label(attributeName);
        attributeTextField = new TextField();
        attributeLabel.setLayoutY(5);
        attributeTextField.setLayoutX(120);
        attributeTextField.setMaxWidth(100);
        attributeGroup = new Group(attributeLabel, attributeTextField);
    }

    public Group getAttributeGroup() {
        return attributeGroup;
    }

    public String getAttributeName() {
        return attributeLabel.getText();
    }

    public String getAttributeValue() {
        return attributeTextField.getText();
    }
}
