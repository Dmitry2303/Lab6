package org.example.geometry2d;

import java.awt.*;

public class CircleShape extends Shape {
    public CircleShape(int x, int y, int diameter, Color color) {
        super(x, y, diameter, diameter, color);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        if (isSelected) {
            g2d.setStroke(new BasicStroke(3));
        } else {
            g2d.setStroke(new BasicStroke(1));
        }
        g2d.fillOval(x, y, width, height);
    }

    @Override
    public boolean contains(int mouseX, int mouseY) {
        int radius = width / 2;
        return Math.pow(mouseX - (x + radius), 2) + Math.pow(mouseY - (y + radius), 2) <= Math.pow(radius, 2);
    }
}