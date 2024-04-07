package org.example;

import javafx.scene.paint.Color;
import java.io.Serializable;

public class SerializableColor implements Serializable {
    private double red;
    private double green;
    private double blue;
    private double opacity;

    public SerializableColor(Color color) {
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
        this.opacity = color.getOpacity();
    }

    public Color getColor() {
        return new Color(red, green, blue, opacity);
    }
}

