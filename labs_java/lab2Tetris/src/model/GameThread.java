package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;


public class GameThread extends Thread {
    private final Model model;
    private ActiveFigure activeFigure;
    private int score;
    private final Background background;
    private final PropertyChangeSupport pcs;

    GameThread(Model inputModel){
        model = inputModel;
        makeNewFigure();
        score = 0;
        background = new Background(Model.boardWidth, Model.boardHeight);
        pcs = new PropertyChangeSupport(this);
    }

    public void makeNewFigure(){
        Figure figure = Figure.getRandomFigure();
        Coords point = new Coords(Model.boardWidth / 2 - 1, -figure.getHeight());
        activeFigure = new ActiveFigure(figure, point);
    }

    @Override
    public void run(){
        while (model.isInGame()){

            if (activeFigure.moveDown(model.getBackground())) {
                model.updateView();
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                background.resetBackground(model);
                if (!model.isInGame())
                    break;
                model.updateView();
                int linesNum = background.cleanBackground(activeFigure);
                updateScore(linesNum);
                model.updateView();
                makeNewFigure();
            }
        }
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void updateScore(int linesNum){
        Map<Integer, Integer> scores = new HashMap<>(Map.of(
                0, 0,
                1, 1,
                2, 5,
                3, 20,
                4, 100
        ));

        score += scores.get(linesNum);
        pcs.firePropertyChange("score", null, score);
    }

    public int getScore(){
        return score;
    }

    public Background getBackground(){
        return background;
    }

    public ActiveFigure getActiveFigure(){
        return activeFigure;
    }
}