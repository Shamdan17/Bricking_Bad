package ui;

import domain.BrickingBad;
import domain.model.shape.MovableShape;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GamePlayPanel extends JPanel implements Runnable {

    public JButton backToMain;
    private BrickingBad brickingBad;
    private JButton saveButton;
    private JButton loadButton;


    public GamePlayPanel(BrickingBad bb){
        backToMain = new JButton("Back to Main");
        brickingBad = bb;
        saveButton = new JButton("save");
        loadButton = new JButton("load");
        this.add(backToMain);
        //(new Thread(this)).start();
    }

    public void run(){
        try{
            repaint();
            Thread.sleep(30);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void paint(Graphics g){
        List<MovableShape> drawables = brickingBad.getMovables();
        for(MovableShape ms : drawables){
            Drawable d = getDrawable(ms);
            d.draw(g);
        }
    }

    public Drawable getDrawable(MovableShape ms){
        if(ms.getType() == MovableShape.Type.Brick)
            return new Brick(ms);
        if(ms.getType() == MovableShape.Type.Paddle)
            return new Paddle(ms);
        if(ms.getType() == MovableShape.Type.Ball)
            return new Ball(ms);
        return null;
    }
}
