import domain.BrickingBad;
import org.apache.log4j.Logger;
import ui.MainFrame;
import utils.Position;

public class Main {

    public static void main(String args[]) {
        BrickingBad bb = new BrickingBad();
        new MainFrame(bb);
    }

}
