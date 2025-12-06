package view;

import controller.Controller;
import model.ActiveFigure;
import model.Model;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class Board extends JPanel implements PropertyChangeListener {
    private Model model;
    private Controller controller;
    public static final int width = Model.boardWidth * Config.SIZE;
    public static final int height = Model.boardHeight * Config.SIZE;

    Board(Window frame){
        model = frame.getModel();
        controller = frame.getController();
        setBounds(0,0, width,height);
        setBackground(Color.WHITE);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("model".equals(evt.getPropertyName())) {
            repaint();
        }
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawFigure(model.getGameThread().getActiveFigure(), g);
        drawBackground(g);
    }

    public void drawFigure(ActiveFigure figure, Graphics g){
        int[][] shape = figure.getShape();
        for (int x = 0; x < figure.getWidth(); x++)
            for (int y = 0; y < figure.getHeight(); y++)
                if (shape[y][x] == 1){
                    int coordX = x + figure.getPosition().x();
                    int coordY = y + figure.getPosition().y();
                    drawSell(figure.getColor(), coordX, coordY, g);
                }
    }

    public void drawSell(Color color, int coordX, int coordY, Graphics g){
        if (coordY < 0 || coordY >= Model.boardHeight)
            return;
        g.setColor(color);
        g.fillRect(coordX * Config.SIZE, coordY * Config.SIZE, Config.SIZE, Config.SIZE);
        g.setColor(Color.BLACK);
        g.drawRect(coordX * Config.SIZE, coordY * Config.SIZE, Config.SIZE, Config.SIZE);
    }

    public void drawBackground(Graphics g){
        for (int x = 0; x < Model.boardWidth; x++)
            for (int y = 0; y < Model.boardHeight; y++){
                Color colorCell = model.getBackground().getField()[y][x];
                if (colorCell != null)
                    drawSell(colorCell, x, y, g);
            }
    }
}
