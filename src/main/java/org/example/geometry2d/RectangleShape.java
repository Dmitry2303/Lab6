package org.example.geometry2d;

import java.awt.*;

public class RectangleShape extends Shape {
    public RectangleShape(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        if (isSelected) {
            g2d.setStroke(new BasicStroke(3));
        } else {
            g2d.setStroke(new BasicStroke(1));
        }
        g2d.fillRect(x, y, width, height);
    }

    @Override
    public boolean contains(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}