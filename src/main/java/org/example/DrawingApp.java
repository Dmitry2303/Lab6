import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.example.geometry2d.CircleShape;
import org.example.geometry2d.RectangleShape;
import org.example.geometry2d.Shape;
import org.example.geometry2d.TriangleShape;

public class DrawingApp extends JPanel {
    private BufferedImage canvas;
    private Graphics2D g2d;
    private List<Shape> shapes;
    private Shape selectedShape;


    public DrawingApp() {
        canvas = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        g2d = canvas.createGraphics();
        clearCanvas();
        shapes = new ArrayList<>();

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Проверка нажатия по фигурам
                for (Shape shape : shapes) {
                    if (shape.contains(e.getX(), e.getY())) {
                        selectedShape = shape;
                        shape.isSelected = true;
                    } else {
                        shape.isSelected = false;
                    }
                }
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedShape != null) {
                    selectedShape.x = e.getX() - (selectedShape.width / 2);
                    selectedShape.y = e.getY() - (selectedShape.height / 2);
                    repaint();
                }
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    public void clearCanvas() {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g2d.setColor(Color.BLACK); // Установите цвет для фигур (например, черный)
    }
    public void drawRectangle() {
        Random rand = new Random();
        int x = rand.nextInt(canvas.getWidth());
        int y = rand.nextInt(canvas.getHeight());
        int width = rand.nextInt(100) + 20;
        int height = rand.nextInt(100) + 20;
        Color color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

        shapes.add(new RectangleShape(x, y, width, height, color));
        repaint(); // Перерисовываем холст, чтобы показать новые фигуры
    }

    public void drawCircle() {
        Random rand = new Random();
        int x = rand.nextInt(canvas.getWidth());
        int y = rand.nextInt(canvas.getHeight());
        int diameter = rand.nextInt(100) + 20;
        Color color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

        shapes.add(new CircleShape(x, y, diameter, color));
        repaint();
    }

    public void drawTriangle() {
        Random rand = new Random();
        int x = rand.nextInt(canvas.getWidth());
        int y = rand.nextInt(canvas.getHeight());
        int width = rand.nextInt(100) + 20;
        int height = rand.nextInt(100) + 20;
        Color color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

        shapes.add(new TriangleShape(x, y, width, height, color));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(canvas, 0, 0, null);
        for (Shape shape : shapes) {
            shape.draw(g2d);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Drawing App");
        DrawingApp drawingApp = new DrawingApp();

        JButton rectangleButton = new JButton("Прямоугольник");
        rectangleButton.setPreferredSize(new Dimension(150, 30));
        rectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingApp.drawRectangle();
            }
        });

        JButton circleButton = new JButton("Круг");
        circleButton.setPreferredSize(new Dimension(150, 30));
        circleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingApp.drawCircle();
            }
        });

        JButton triangleButton = new JButton("Треугольник");
        triangleButton.setPreferredSize(new Dimension(150, 30));
        triangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingApp.drawTriangle();
            }
        });

        JButton moveShapeButton = new JButton("Двигать");
        moveShapeButton.setPreferredSize(new Dimension(150, 30));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(rectangleButton);
        buttonPanel.add(circleButton);
        buttonPanel.add(triangleButton);
        buttonPanel.add(moveShapeButton);

        frame.setLayout(new BorderLayout());
        frame.add(drawingApp, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.EAST);

        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}