package sample;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Created by Livorni on 11.11.2015.
 */

public class Attribute {
    private Label attributeLabel;
    private TextField attributeTextField;
    private HBox attributeHBox;

    Attribute(String attributeName) {
        attributeLabel = new Label(attributeName);
        attributeTextField = new TextField();
        attributeHBox = new HBox(attributeLabel, attributeTextField);
    }

    public HBox getAttributeHBox() {
        return attributeHBox;
    }
}
