package ui;

import domain.BrickingBad;
import domain.model.Movable;
import domain.model.shape.MovableShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MapEditorPanel extends JPanel implements Runnable , ActionListener, MouseListener, MouseMotionListener {


    public JButton backToMain;
    private BrickingBad brickingBad;
    private JButton saveButton;

    public MapEditorPanel(BrickingBad bb){
        backToMain = new JButton("Back to Main");
        saveButton = new JButton("Save");
        brickingBad = bb;
        this.add(backToMain);
        this.add(saveButton);
        (new Thread(this)).start();
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

    public void paintComponent(Graphics g){
        List<MovableShape> drawables = brickingBad.getMapEditorMoveables();
        for(MovableShape ms : drawables){
            Drawable d = getDrawable(ms);
            d.draw(g);
        }
    }

    public Drawable getDrawable(MovableShape ms){
        if(ms.getType() == MovableShape.Type.Brick)
            return new Brick(ms, brickingBad);
        return new Brick(ms, brickingBad);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Save")){
            brickingBad.saveMap();
        }
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
