package controller;

import model.ActiveFigure;
import model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Controller {
    private final Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void handleKeyPress(KeyEvent e){
        ActiveFigure figure = model.getGameThread().getActiveFigure();
        if (figure == null)
            return;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> figure.moveLeft(model.getBackground());
            case KeyEvent.VK_RIGHT -> figure.moveRight(model.getBackground());
            case KeyEvent.VK_DOWN -> figure.dropToTheBottom(model);
            case KeyEvent.VK_UP -> figure.turnRight(model.getBackground());
            default -> { return; }
        }
        model.updateView();
    }

    public void handleNewGame(ActionEvent e) {
        model.setInMenu(false);
        model.setInGame(true);
        model.setInLeaderBoard(false);
        model.gameStart();
    }

    public void handleLeaderBoard(ActionEvent e) {
        model.setInMenu(false);
        model.setInGame(false);
        model.setInLeaderBoard(true);
        model.openLeaderBoard();
    }

    public void handleAbout(ActionEvent e) {
        // Логика показа информации об игре
    }

    public void handleExit(ActionEvent e) {
        System.exit(0);
    }

    public void gameOver(ActionEvent e) {
        model.setInMenu(true);
        model.setInGame(false);
        model.setInLeaderBoard(false);
        model.gameOver();
    }

    public void moveToMenu(ActionEvent e) {
        model.setInMenu(true);
        model.setInGame(false);
        model.setInLeaderBoard(false);
        model.openMenu();
    }

    public void updateLeaderBoard(String PlayerName) {
        model.addPlayer(PlayerName);
    }
}
