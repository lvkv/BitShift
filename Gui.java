import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Gui extends JFrame implements KeyListener, Constants{

    private JFrame frame;

    public Gui(String title){
        frame = new JFrame(title);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(DIMENSIONS[0], DIMENSIONS[1]);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this);
    }

    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                Game2048.swipe(LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                Game2048.swipe(RIGHT);
                break;
            case KeyEvent.VK_UP:
                Game2048.swipe(UP);
                break;
            case KeyEvent.VK_DOWN:
                Game2048.swipe(DOWN);
                break;
        }
        Game2048.dispBoard();
    }

    public void keyTyped(KeyEvent e){}

    public void keyReleased(KeyEvent e){}
}
