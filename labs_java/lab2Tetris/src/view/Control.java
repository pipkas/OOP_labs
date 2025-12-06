package view;

import model.Model;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Control extends JPanel implements PropertyChangeListener {
    private Model model;
    private JLabel scoreLabel;
    public static final int width = Window.width - Board.width;
    public static final int height = Board.height;

    public Control(Window frame){
        model = frame.getModel();
        scoreLabel = new JLabel();
        setLayout(null);

        setBounds(Board.width,0, width, height);
        setBackground(new Color(220, 230, 240));

        scoreLabel.setText("Score: 0");
        scoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

        int x = width / 4;
        int y = height / 8;
        scoreLabel.setBounds(x, y, 200, 30);
        add(scoreLabel);

        JButton backBtn = Window.createStyledButton("Back to Menu");
        backBtn.setBounds(x, height - 50, 200, 30);
        backBtn.addActionListener(frame.getController()::gameOver);
        add(backBtn);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("score".equals(evt.getPropertyName())) {
            scoreLabel.setText("Score: " + model.getGameThread().getScore());
        }
    }
}
