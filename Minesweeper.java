import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

public class Minesweeper extends JPanel implements ActionListener, MouseListener {

    JFrame frame;

    JMenuBar menuBar;
    JMenu menu;
    JMenuItem item1;
    int dimW = 9; 
    int dimH = 9;
    final int scale = 50;
    JToggleButton[][] toggles;

    boolean firstClick = true;

    ImageIcon mine, flag, one, two, three, four, five, six, seven, eight;

    Game game;

    public Minesweeper(){
        frame = new JFrame("Minesweeper");
        frame.add(this);
        frame.setSize(dimW*scale, dimH*scale+20);

        menuBar = new JMenuBar();
        menu = new JMenu("Game");
        item1 = new JMenuItem("Beginner");
        item1.addActionListener(this);
        toggles = new JToggleButton[dimH][dimW];
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(dimH, dimW));
        for(int x=0;x<toggles.length;x++)
            for(int y=0;y<toggles[0].length;y++){
                toggles[x][y] = new JToggleButton();
                toggles[x][y].addMouseListener(this);
                panel.add(toggles[x][y]);
            }
        
        flag = new ImageIcon("flag.png");
        flag = new ImageIcon(flag.getImage().getScaledInstance(frame.getWidth()/dimW, frame.getHeight()/dimH, Image.SCALE_SMOOTH));
        mine = new ImageIcon("mine.png");
        mine = new ImageIcon(mine.getImage().getScaledInstance(frame.getWidth()/dimW, frame.getHeight()/dimH, Image.SCALE_SMOOTH));
        one = new ImageIcon("one.png");
        one = new ImageIcon(one.getImage().getScaledInstance(frame.getWidth()/dimW, frame.getHeight()/dimH, Image.SCALE_SMOOTH));
        two = new ImageIcon("two.png");
        two = new ImageIcon(two.getImage().getScaledInstance(frame.getWidth()/dimW, frame.getHeight()/dimH, Image.SCALE_SMOOTH));
        three = new ImageIcon("three.png");
        three = new ImageIcon(three.getImage().getScaledInstance(frame.getWidth()/dimW, frame.getHeight()/dimH, Image.SCALE_SMOOTH));
        four = new ImageIcon("four.png");
        four = new ImageIcon(four.getImage().getScaledInstance(frame.getWidth()/dimW, frame.getHeight()/dimH, Image.SCALE_SMOOTH));
        five = new ImageIcon("five.png");
        five = new ImageIcon(five.getImage().getScaledInstance(frame.getWidth()/dimW, frame.getHeight()/dimH, Image.SCALE_SMOOTH));
        six = new ImageIcon("six.png");
        six = new ImageIcon(six.getImage().getScaledInstance(frame.getWidth()/dimW, frame.getHeight()/dimH, Image.SCALE_SMOOTH));
        seven = new ImageIcon("seven.png");
        seven = new ImageIcon(seven.getImage().getScaledInstance(frame.getWidth()/dimW, frame.getHeight()/dimH, Image.SCALE_SMOOTH));
        eight = new ImageIcon("eight.png");
        eight = new ImageIcon(eight.getImage().getScaledInstance(frame.getWidth()/dimW, frame.getHeight()/dimH, Image.SCALE_SMOOTH));

        game = new Game(dimW, dimH, 10);

        menu.add(item1);
        menuBar.add(menu);
        frame.add(menuBar, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent event){

    }
    public void mousePressed(MouseEvent event){

    }
    public void mouseExited(MouseEvent event){

    }
    public void mouseEntered(MouseEvent event){

    }
    public void mouseReleased(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON3){
            for(int x=0;x<toggles.length;x++)
                for(int y=0;y<toggles[0].length;y++){
                    if(e.getSource() == toggles[x][y]){
                        if(!toggles[x][y].isSelected()){
                            toggles[x][y].setSelected(true);
                            toggles[x][y].setIcon(flag);
                        }
                        else {
                            toggles[x][y].setSelected(false);
                            toggles[x][y].setIcon(null);
                        }
                        
                    }
                }
        }
        if(e.getButton()==MouseEvent.BUTTON1){
            for(int x=0;x<toggles.length;x++)
                for(int y=0;y<toggles[0].length;y++){
                    if(e.getSource() == toggles[x][y]){
                        if(firstClick){
                            firstClick = false;
                            game.firstClick(y, x);
                        }
                        for(int i=0;i<game.board.length;i++){
                            for(int j=0;j<game.board[0].length;j++){
                                if(toggles[i][j].isSelected())
                                    switch(game.board[i][j]){
                                        case 1:
                                            toggles[i][j].setIcon(one);
                                        break;
                                        case 2:
                                            toggles[i][j].setIcon(two);
                                        break;
                                        case 3:
                                            toggles[i][j].setIcon(three);
                                        break;
                                        case 4:
                                            toggles[i][j].setIcon(four);
                                        break;
                                        case 5:
                                            toggles[i][j].setIcon(five);
                                        break;
                                        case 6:
                                            toggles[i][j].setIcon(six);
                                        break;
                                        case 7:
                                            toggles[i][j].setIcon(seven);
                                        break;
                                        case 8:
                                            toggles[i][j].setIcon(eight);
                                        break;
                                        default:
                                            if(game.board[i][j]<0)
                                                toggles[i][j].setIcon(mine);
                                        break;
                                    }
                            }
                        }

                    }
                }
        }
        frame.revalidate();
    }
    public void mouseClicked(MouseEvent e){
    }
    public static void main (String [] args){
        Minesweeper app = new Minesweeper();
    }

}