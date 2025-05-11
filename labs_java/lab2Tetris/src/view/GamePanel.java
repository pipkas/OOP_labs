package view;

import controller.Controller;
import model.Model;

import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    private final Window frame;
    private final Model model;
    private final Controller controller;
    private final Board board;
    private final Control control;
    public static final int width = Window.width;
    public static final int height = Window.height;

    public GamePanel(Window window) {
        setLayout(null);
        setBounds(0, 0, width, height);

        this.frame = window;
        board = new Board(frame);
        model = frame.getModel();
        controller = frame.getController();

        control = new Control(frame);
        add(board);
        add(control);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                controller.handleKeyPress(e);
            }
        });
        model.addPropertyChangeListener(board);
        model.getGameThread().addPropertyChangeListener(control);
    }
}