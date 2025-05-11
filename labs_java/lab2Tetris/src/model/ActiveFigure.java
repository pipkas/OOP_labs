package model;

import javax.swing.Timer;

import java.awt.Color;
import java.util.Arrays;

public class ActiveFigure {
    private Figure figure;
    private Coords position;
    public ActiveFigure(Figure figure, Coords at) {
        this.figure = figure;
        position = at;
    }
    public Coords getPosition() {
        return position;
    }

    private boolean canMoveTo(int newX, int newY, Color[][] background) {
        int figureWidth = figure.getWidth();
        int figureHeight = figure.getHeight();
        int[][] shape = figure.getShape();

        for (int x = 0; x < figureWidth; x++)
            for (int y = 0; y < figureHeight; y++)
                if (shape[y][x] == 1) {
                    int boardX = newX + x;
                    int boardY = newY + y;

                    if (boardX < 0 || boardX >= Model.boardWidth ||
                            boardY >= Model.boardHeight) {
                        return false;
                    }
                    if (boardY < 0)
                        break;
                    if (background[boardY][boardX] != null)
                        return false;
                }

        return true;
    }

    public boolean moveLeft(Background background) {
        int newX = position.x() - 1;
        int newY = position.y();

        if (canMoveTo(newX, newY, background.getField())) {
            position = new Coords(newX, newY);
            return true;
        }
        return false;
    }

    public boolean moveRight(Background background) {
        int newX = position.x() + 1;
        int newY = position.y();

        if (canMoveTo(newX, newY, background.getField())) {
            position = new Coords(newX, newY);
            return true;
        }
        return false;
    }

    public boolean moveDown(Background background) {
        int newX = position.x();
        int newY = position.y() + 1;

        if (canMoveTo(newX, newY, background.getField())) {
            position = new Coords(newX, newY);
            return true;
        }
        return false;
    }

    public void dropToTheBottom(Model model) {

        Timer timer = new Timer(50, e -> {
            if (!moveDown(model.getBackground())) {
                ((Timer)e.getSource()).stop(); // Остановить таймер при достижении дна
            }
            model.updateView();
        });
        timer.start();
    }

    public Color getColor() {
        return figure.getColor();
    }
    public int[][] getShape() {
        return figure.getShape();
    }
    public int getHeight(){
        return figure.getHeight();
    }
    public int getWidth(){
        return figure.getWidth();
    }
    public void turnRight(Background background) {
        Figure turnFigure = figure.turnRight();
        Figure saveFigure = figure;
        figure = turnFigure;
        if (!canMoveTo(position.x(), position.y(), background.getField())) {
            figure = saveFigure;
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("position=").append(position);
        sb.append("\n shape:\n");

        int[][] shape = figure.getShape();
        for (int[] row : shape) {
            sb.append(Arrays.toString(row)).append("\n");
        }

        return sb.toString();
    }
}
