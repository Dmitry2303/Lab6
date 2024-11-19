package org.example.geometry2d;

import java.awt.*;

public abstract class Shape {
    public int x, y, width, height; // Измените модификатор доступа на public
    Color color;
    public boolean isSelected;  // Доступ теперь public

    Shape(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.isSelected = false;
    }

    public abstract void draw(Graphics2D g2d);
    public abstract boolean contains(int mouseX, int mouseY);
}