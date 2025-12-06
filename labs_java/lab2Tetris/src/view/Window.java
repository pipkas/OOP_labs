package view;

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import controller.Controller;

import model.Model;
import model.PlayerScore;
import java.util.List;

import java.awt.Insets;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Window extends JFrame implements PropertyChangeListener {
    private final Model model;
    private final Controller controller;

    private GamePanel gamePanel;
    private AboutPanel aboutPanel;
    private MainMenu menuPanel;
    private LeaderBoard leaderBoardPanel;
    public static final int width = Model.boardWidth * Config.SIZE * 2;
    public static final int height = Model.boardHeight * Config.SIZE;
    private int totalWidth;
    private int totalHeight;

    public Window(Model inputModel, Controller inputController) {
        model = inputModel;
        controller = inputController;
        initComponents();
    }
    private void initComponents(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setTitle("Tetris");
        setLayout(null);//автоматически не определяет расположение(в ручную задаем)
        setResizable(false);

        Insets insets = getInsets();//учитывает доп место для системных частей окна
        totalWidth = width + insets.left + insets.right;
        totalHeight = height + insets.top + insets.bottom;
        setSize(totalWidth, totalHeight);
        setLocationRelativeTo(null);
        model.addPropertyChangeListener(this);

        leaderBoardPanel = new LeaderBoard(controller);
        menuPanel = new MainMenu(controller);
        aboutPanel = new AboutPanel(controller);

        add(leaderBoardPanel);
        add(menuPanel);
        add(aboutPanel);
        revalidate();// используется после add чтобы компоненты дорисовать
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("inGame".equals(evt.getPropertyName())) {
            boolean inGame = (Boolean)evt.getNewValue();
            if (inGame) {
                SwingUtilities.invokeLater(this::startGame);
            }
        }
        if ("inMenu".equals(evt.getPropertyName())) {
            boolean inMenu = (Boolean)evt.getNewValue();
            if (inMenu) {
                SwingUtilities.invokeLater(this::showMenu);
            }
        }
        if ("inAbout".equals(evt.getPropertyName())) {
            boolean inAbout = (Boolean)evt.getNewValue();
            if (inAbout) {
                SwingUtilities.invokeLater(this::showAbout);
            }
        }
        if ("inLeaderBoard".equals(evt.getPropertyName())) {
            boolean inLeaderBoard = (Boolean)evt.getNewValue();
            if (inLeaderBoard) {
                SwingUtilities.invokeLater(this::showLeaderboard);
            }
        }
        if ("gameOver".equals(evt.getPropertyName())) {
            boolean gameOver = (Boolean)evt.getNewValue();
            if (gameOver) {
                String playerName = JOptionPane.showInputDialog(this, "Game over :)\nPlease enter your name.\n");
                controller.updateLeaderBoard(playerName);
            }
        }
        if ("updateLeaderBoard".equals(evt.getPropertyName())) {
            Object newValue = evt.getNewValue();
            if (newValue instanceof List<?>) {
                @SuppressWarnings("unchecked")
                List<PlayerScore> leaderBoard = (List<PlayerScore>) newValue;

                leaderBoardPanel.updateLeaderBoard(leaderBoard);
            }
        }
    }

    void clean(Component object){
        if (object != null){
            object.setVisible(false);
        }
    }

    public void showMenu() {
        clean(gamePanel);
        clean(leaderBoardPanel);
        clean(aboutPanel);

        menuPanel.setVisible(true);
        repaint();
        menuPanel.setFocusable(true);
        menuPanel.requestFocusInWindow();
    }

    public void showAbout(){
        clean(menuPanel);

        aboutPanel.setVisible(true);
        repaint();
        aboutPanel.setFocusable(true);
        aboutPanel.requestFocusInWindow();
    }

    public void showLeaderboard(){
        clean(menuPanel);

        leaderBoardPanel.setVisible(true);
        repaint();
        leaderBoardPanel.setFocusable(true);
        leaderBoardPanel.requestFocusInWindow();
    }

    public void startGame() {
        clean(menuPanel);

        gamePanel = new GamePanel(this);
        add(gamePanel);
        revalidate();
        repaint();
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
    }

    public static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setFocusPainted(false);
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        return button;
    }

    public Model getModel() {
        return model;
    }

    public Controller getController() {
        return controller;
    }

    public int getTotalHeight(){
        return totalHeight;
    }
    public int getTotalWidth(){
        return totalWidth;
    }

}
