import controller.Controller;
import model.Model;
import view.Window;

public class Tetris implements Runnable{
    @Override
    public void run() {
        Model model = new Model();
        Controller controller = new Controller(model);
        java.awt.EventQueue.invokeLater(() -> {
            Window window = new Window(model, controller);
            model.openMenu();
            window.setVisible(true);
        });
    }
    public static void main(String[] args) {
        Tetris tetris = new Tetris();
        tetris.run();
    }
}
