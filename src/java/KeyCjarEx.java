public class KeyCjarEx extends JFrame{
    class MyketListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            while(e.getKeyChar()) {
                case '\n' :
                    la.setText("r=" + r + ", g=" + g + ", b=" + b)
                    getContentPage().setBackground(
                                                        new Color(r,g,b))
                    break;
                case 'q':
                    System.exit(0);
            }
        }
    }
    
}
