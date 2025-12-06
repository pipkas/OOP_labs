package view;

import controller.Controller;
import model.PlayerScore;

import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.util.List;

public class LeaderBoard extends JPanel {
    public static final int width = Window.width;
    public static final int height = Window.height;
    private DefaultTableModel tableModel;
    private JTable table;

    Controller controller;
    public LeaderBoard(Controller inputController) {
        setLayout(null);
        setBounds(0, 0, width, height);

        controller = inputController;
        JButton backBtn = Window.createStyledButton("Back to Menu");
        backBtn.setBounds(width - 200, height - 30, 200, 30);
        backBtn.addActionListener(controller::moveToMenu);
        add(backBtn);

        String[] columns = {"Player", "Score"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, width - 100, height - 150);

        add(scrollPane);
    }

    public void updateLeaderBoard(List<PlayerScore> scores) {
        tableModel.setRowCount(0); // Очищаем старые данные
        for (PlayerScore entry : scores) {
            tableModel.addRow(new Object[]{ entry.getPlayerName(), entry.getScore()});
        }
    }
}
