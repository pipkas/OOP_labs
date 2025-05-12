package view;

import controller.Controller;
import model.PlayerScore;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AboutPanel extends JPanel {
    public static final int width = Window.width;
    public static final int height = Window.height;
    Controller controller;

    public AboutPanel(Controller inputController) {
        setLayout(null);
        setBounds(0, 0, width, height);

        controller = inputController;
        JButton backBtn = Window.createStyledButton("Back to Menu");
        backBtn.setBounds(width - 200, height - 30, 200, 30);
        backBtn.addActionListener(controller::moveToMenu);
        add(backBtn);

        String[] scoringRules = {
                "1 line cleared - 1 point",
                "2 lines cleared simultaneously - 5 points",
                "3 lines cleared simultaneously - 20 points",
                "4 lines cleared simultaneously - 100 points"
        };

        for (int i = 0; i < scoringRules.length; i++) {
            JLabel ruleLabel = new JLabel(scoringRules[i]);
            ruleLabel.setBounds(70, 290 + i*30, 300, 25);
            add(ruleLabel);
        }

        String[] controls = {
                "↑ Arrow - Rotate the piece",
                "↓ Arrow - Drop the piece faster",
                "→ Arrow - Move piece right",
                "← Arrow - Move piece left"
        };

        for (int i = 0; i < controls.length; i++) {
            JLabel controlLabel = new JLabel(controls[i]);
            controlLabel.setBounds(70, 120 + i*30, 300, 25);
            add(controlLabel);
        }

    }

}
