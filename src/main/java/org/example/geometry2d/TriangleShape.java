package org.example.geometry2d;

import java.awt.*;

public class TriangleShape extends Shape {
    public TriangleShape(int x, int y, int width, int height, Color color) {
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
        int[] xPoints = {x, x + width / 2, x - width / 2};
        int[] yPoints = {y, y + height, y + height};
        g2d.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public boolean contains(int mouseX, int mouseY) {
        Polygon triangle = new Polygon();
        triangle.addPoint(x, y);
        triangle.addPoint(x + width / 2, y + height);
        triangle.addPoint(x - width / 2, y + height);
        return triangle.contains(mouseX, mouseY);
    }
}