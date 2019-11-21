package ui;

import domain.BrickingBad;
import domain.model.Movable;
import domain.model.shape.MovableShape;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MapEditorPanel extends JPanel implements Runnable {


    public JButton backToMain;
    private BrickingBad brickingBad;
    private JButton saveButton;

    public MapEditorPanel(BrickingBad bb){
        backToMain = new JButton("Back to Main");
        saveButton = new JButton("Save");
        brickingBad = bb;
        this.add(backToMain);
        //(new Thread(this)).start();
    }

    public void run(){
        while(true){
            try{
                repaint();
                Thread.sleep(30);
            }catch(Exception e){
                e.printStackTrace();
            }
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
        return null;
    }

}
