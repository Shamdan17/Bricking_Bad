import org.apache.log4j.Logger;
import ui.LoginForm;

public class Main {
    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("The game is running");
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
    }
}
