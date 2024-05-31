import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseEventAllEx extends JFrame {
    private JLabel la = new JLabel("Move Me");

    public MouseEventAllEx() {
        setTitle("MouseListener and MouseMotionListener Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();

        MyMouseListener listener = new MyMouseListener();
        c.addMouseListener(listener);
        c.addMouseMotionListener(listener);
        c.setLayout(null);
        
        la.setSize(80, 20);
        la.setLocation(100, 80);
        c.add(la); // Adding the label component

        setSize(300, 200);
        setVisible(true);
    }

    class MyMouseListener implements MouseListener, MouseMotionListener {
        public void mousePressed(MouseEvent e) {
            la.setLocation(e.getX(), e.getY());
            setTitle("mousePressed (" + e.getX() + ", " + e.getY() + ")");
        }

        public void mouseReleased(MouseEvent e) {
            la.setLocation(e.getX(), e.getY());
            setTitle("mouseReleased (" + e.getX() + ", " + e.getY() + ")");
        }

        public void mouseClicked(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseDragged(MouseEvent e) {
            la.setLocation(e.getX(), e.getY());
            setTitle("mouseDragged (" + e.getX() + ", " + e.getY() + ")");
        }
        public void mouseMoved(MouseEvent e) {}
    }

    public static void main(String[] args) {
        new MouseEventAllEx();
    }
}