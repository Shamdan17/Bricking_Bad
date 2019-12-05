import domain.BrickingBad;
import org.apache.log4j.Logger;
import ui.LoginForm;
import ui.MainFrame;
import utils.Position;

public class Main {
    final static Logger logger = Logger.getLogger(Main.class);

        public static void main(String args[]) {
        BrickingBad bb = new BrickingBad();
        bb.addBrick(new Position(4, 4));
        bb.addBrick(new Position(100, 100));
        new MainFrame(bb);
    }

//    public static void main(String[] args) {
//
//
//
//        logger.info("The game is running");
//        LoginForm loginForm = new LoginForm();
//        loginForm.setVisible(true);
//    }
}
