package model;


import javax.swing.SwingUtilities;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


public class Model {
    public static final int boardWidth = 10;
    public static final int boardHeight = 20;

    private GameThread gameThread;
    private final PropertyChangeSupport pcs;
    private List<PlayerScore> leaderBoard;

    private boolean inMenu;
    private boolean inGame;
    private boolean inLeaderBoard;

    public Model(){
        inMenu = false;
        inGame = false;
        inLeaderBoard = false;
        pcs = new PropertyChangeSupport(this);
        leaderBoard = loadLeaderBoard();
    }

    public void gameStart(){
        inGame = true;

        if (gameThread == null || !gameThread.isAlive()) {
            gameThread = new GameThread(this);
            gameThread.start();
        }
        pcs.firePropertyChange("inGame", null, true);
    }

    public void openMenu(){
        inMenu = true;
        pcs.firePropertyChange("inMenu", null, true);
    }

    public void openLeaderBoard(){
        inLeaderBoard = true;
        pcs.firePropertyChange("updateLeaderBoard", null, leaderBoard);
        pcs.firePropertyChange("inLeaderBoard", null, true);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void updateView() {
        if (SwingUtilities.isEventDispatchThread()) {
            pcs.firePropertyChange("model", null, this);
        } else {
            SwingUtilities.invokeLater(() -> pcs.firePropertyChange("model", null, this));
        }
    }


    public void gameOver(){
        inGame = false;
        pcs.firePropertyChange("gameOver", null, true);
        gameThread = null;
        openMenu();
    }

    public void addPlayer(String playerName) {
        int score = gameThread.getScore();
        leaderBoard.removeIf(ps -> ps.getPlayerName().equals(playerName));

        leaderBoard.add(new PlayerScore(score, playerName));

        leaderBoard.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));
        if (leaderBoard.size() > 10) {
            leaderBoard = new ArrayList<>(leaderBoard.subList(0, 10));
        }

        saveLeaderBoard();
        pcs.firePropertyChange("updateLeaderBoard", null, leaderBoard);
    }

    public List<PlayerScore> loadLeaderBoard() {
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("leaderboard.txt"))) {

            @SuppressWarnings("unchecked")
            List<PlayerScore> loadedList = (List<PlayerScore>) in.readObject();
            return loadedList;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Impossible to load leaderboard.txt. Maybe, it is absent.");
            return new ArrayList<>();
        }
    }

    public void saveLeaderBoard(){
        try(FileOutputStream fs = new FileOutputStream("leaderboard.txt");
            ObjectOutputStream os = new ObjectOutputStream(fs)) {

            os.writeObject(leaderBoard);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Background getBackground(){
        return gameThread.getBackground();
    }
    public GameThread getGameThread(){
        return gameThread;
    }
    public void setInMenu(boolean inMenu) {
        this.inMenu = inMenu;
    }
    public boolean isInGame() {
        return inGame;
    }
    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }
    public void setInLeaderBoard(boolean inLeaderBoard) {
        this.inLeaderBoard = inLeaderBoard;
    }
}
