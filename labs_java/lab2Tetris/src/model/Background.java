package model;

import java.awt.Color;


public class Background {
    private final Color[][] field;
    private final int boardWidth;
    private final int boardHeight;

    public Background(int boardWidth, int boardHeight) {

        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        field = new Color[this.boardHeight][this.boardWidth];
    }

    public void resetBackground(Model model){
        ActiveFigure activeFigure = model.getGameThread().getActiveFigure();
        for (int x = 0; x < activeFigure.getWidth(); x++)
            for (int y = 0; y < activeFigure.getHeight(); y++)
                if (activeFigure.getShape()[y][x] == 1){
                    int curX = x + activeFigure.getPosition().x();
                    int curY = y + activeFigure.getPosition().y();
                    if (curY < 0){
                        model.gameOver();
                        return;
                    }
                    if (curX < boardWidth && curY < boardHeight) {
                        field[curY][curX] = activeFigure.getColor();
                    }
                }
    }

    void shiftLines(int curLine, int height, int shift){
        for (int y = 0; y < height; y++){
            System.arraycopy(field[curLine - shift - y], 0, field[curLine - y], 0, boardWidth);
            cleanLine(curLine - shift - y);
        }
    }

    public int cleanBackground(ActiveFigure figure){
        int figureY = figure.getPosition().y();
        int figureHeight = figure.getHeight();
        int numCleanedLines = 0;
        int curLine = figureY + figureHeight - 1;
        for (int y = 0; y < figureHeight; y++){
            if (isFullLine(curLine)){
                cleanLine(curLine);
                numCleanedLines++;
                shiftLines(curLine, figureHeight - y - 1, 1);
            }
            else{
                curLine--;
            }
        }

        if (numCleanedLines != 0)
            shiftLines(curLine, figureY - 1, numCleanedLines);
        return numCleanedLines;
    }

    void cleanLine(int y){
        for (int x = 0; x < boardWidth; x++)
            field[y][x] = null;
    }

    boolean isEmptyLine(int y){
        for (int x = 0; x < boardWidth; x++){
            if (field[y][x] != null)
                return false;
        }
        return true;
    }

    boolean isFullLine(int y){
        for (int x = 0; x < boardWidth; x++){
            if (field[y][x] == null)
                return false;
        }
        return true;
    }

    public Color[][] getField() {
        return field;
    }
}
