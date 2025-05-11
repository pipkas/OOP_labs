package view;

import controller.Controller;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;

import static view.Window.createStyledButton;

public class MainMenu extends JPanel {
    private final JButton newGameButton;
    private final JButton leaderBoardButton;
    private final JButton aboutButton;
    private final JButton exitButton;
    public static final int width = Window.width;
    public static final int height = Window.height;

    public MainMenu(Controller controller) {
        setLayout(null);
        setBounds(0, 0, width, height);
        setOpaque(true); // панель непрозрачная
        setBackground(new Color(50, 110, 160));

        int buttonWidth = 200;
        int buttonHeight = 50;
        int gap = 20;
        int x = (width - buttonWidth) / 2;
        int startY = 150;

        newGameButton = createStyledButton("New Game");
        newGameButton.setBounds(x, startY, buttonWidth, buttonHeight);

        leaderBoardButton = createStyledButton("Leaderboard");
        leaderBoardButton.setBounds(x, startY + buttonHeight + gap,
                buttonWidth, buttonHeight);

        aboutButton = createStyledButton("About");
        aboutButton.setBounds(x, startY + 2*(buttonHeight + gap),
                buttonWidth, buttonHeight);

        exitButton = createStyledButton("Exit");
        exitButton.setBounds(x, startY + 3*(buttonHeight + gap),
                buttonWidth, buttonHeight);


        newGameButton.addActionListener(controller::handleNewGame);
        leaderBoardButton.addActionListener(controller::handleLeaderBoard);
        aboutButton.addActionListener(controller::handleAbout);
        exitButton.addActionListener(controller::handleExit);

        add(newGameButton);
        add(leaderBoardButton);
        add(aboutButton);
        add(exitButton);
        revalidate();
    }
}