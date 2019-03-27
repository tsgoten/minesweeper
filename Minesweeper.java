import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class Minesweeper extends JPanel implements ActionListener, MouseListener {

    private static final long serialVersionUID = 2813932003284179739L;
    JFrame frame;
    JLabel timer;
    JButton resetButton;
    JMenuBar menuBar;
    JMenu menuGame, menuTheme;
    JMenuItem itemGameBeginner, itemGameIntermediate, itemGameExpert, menuHelp;
    JMenuItem itemThemeDefault, itemThemeZelda, itemThemeMario;
    JPanel panel, topPanel;
    int dimW; 
    int dimH;
    final int scale = 50;
    JToggleButton[][] toggles;
    int flags;
    boolean firstClick = true;
    int timePassed;
    ImageIcon mine, flag, one, two, three, four, five, six, seven, eight, smiley, white, zflag, mmine;
    
    Game game;
    Difficulty difficulty;

    Timer time;

    public Minesweeper(){
        frame = new JFrame("Minesweeper");
        frame.add(this);

        //default
        difficulty = Difficulty.easy;
        topPanel = new JPanel();

        menuBar = new JMenuBar();
        menuTheme = new JMenu("Theme");
        menuGame = new JMenu("Game");
        menuHelp = new JMenuItem("Help");
        menuHelp.addActionListener(this);
        itemGameBeginner = new JMenuItem("Beginner");
        itemGameBeginner.addActionListener(this);
        itemGameIntermediate = new JMenuItem("Intermediate");
        itemGameIntermediate.addActionListener(this);
        itemGameExpert = new JMenuItem("Expert");
        itemGameExpert.addActionListener(this);
        itemThemeDefault = new JMenuItem("Default");
        itemThemeDefault.addActionListener(this);
        itemThemeZelda = new JMenuItem("Zelda");
        itemThemeZelda.addActionListener(this);
        itemThemeMario = new JMenuItem("Mario");
        itemThemeMario.addActionListener(this);
        
        resetButton = new JButton();
        resetButton.setBorder(null);
        resetButton.setBackground(Color.WHITE);
        resetButton.addActionListener(this);
        topPanel.setLayout(new GridLayout(1,3));

        topPanel.add(menuBar);
        topPanel.add(resetButton);

        makeBoard();

        setTheme(0);
        white = new ImageIcon("white.jpg");
        white = new ImageIcon(white.getImage().getScaledInstance(frame.getWidth()/dimW, frame.getHeight()/dimH, Image.SCALE_SMOOTH));
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
        resetButton.setIcon(smiley);
        menuGame.add(itemGameBeginner);
        menuGame.add(itemGameIntermediate);
        menuGame.add(itemGameExpert);
        menuTheme.add(itemThemeDefault);
        menuTheme.add(itemThemeMario);
        menuTheme.add(itemThemeZelda);
        menuBar.add(menuGame);
        menuBar.add(menuTheme);
        menuBar.add(menuHelp);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    private void setTheme(int choice){
        switch(choice){
            case 0:
                flag = new ImageIcon("dFlag.png");
                mine = new ImageIcon("dMine.png");
            break;
            case 1:
                flag = new ImageIcon("mFlag.png");
                mine = new ImageIcon("mMine.png");
            break;
            case 2:
                flag = new ImageIcon("zFlag.png");
                mine = new ImageIcon("zMine.png");
            break;
            default:
                flag = new ImageIcon("dFlag.png");
                mine = new ImageIcon("dMine.png");
            break;
        }
        smiley = new ImageIcon("smiley.png");
        smiley = new ImageIcon(smiley.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        flag = new ImageIcon(flag.getImage().getScaledInstance(frame.getWidth()/dimW, frame.getHeight()/dimH, Image.SCALE_SMOOTH));
        mine = new ImageIcon(mine.getImage().getScaledInstance(frame.getWidth()/dimW, frame.getHeight()/dimH, Image.SCALE_SMOOTH));
        frame.revalidate();
    }
    private void makeBoard(){ 
        firstClick = true;
        switch(difficulty){
            case easy:
                dimW = 9;
                dimH = 9;
                flags = 10;
            break;
            case intermediate:
                dimW = 16;
                dimH = 16;
                flags = 40;
            break;
            case expert:
                dimW = 30;
                dimH = 16;
                flags = 99;
            break;
        }
        frame.setSize(dimW*scale, dimH*scale+20);
        try {
            frame.remove(panel);
            topPanel.remove(timer);
            game.board.clear();       
            time.cancel();     
        } catch (NullPointerException e){

        }
        timer = new JLabel(" 00 ");
        toggles = new JToggleButton[dimH][dimW];
        panel = new JPanel();
        panel.setLayout(new GridLayout(dimH, dimW));
        for(int x=0;x<toggles.length;x++)
            for(int y=0;y<toggles[0].length;y++){
                toggles[x][y] = new JToggleButton();
                toggles[x][y].addMouseListener(this);
                panel.add(toggles[x][y]);
            }
        frame.add(panel, BorderLayout.CENTER);
        topPanel.add(timer);
        timePassed = 0;
        time = new Timer();
        time.schedule(new UpdateTimer(), 0, 1000);
        frame.revalidate();
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==resetButton){
            makeBoard();
        }
        else if (e.getSource()==itemGameBeginner){
            difficulty = Difficulty.easy;
            makeBoard();
        }
        else if (e.getSource()==itemGameIntermediate){
            difficulty = Difficulty.intermediate;
            makeBoard();
        }
        else if (e.getSource()==itemGameExpert){
            difficulty = Difficulty.expert;
            makeBoard();
        }
        else if(e.getSource()==itemThemeDefault){
            makeBoard();
            setTheme(0);
        }
        else if(e.getSource()==itemThemeMario){
            makeBoard();
            setTheme(1);
        }
        else if(e.getSource()==itemThemeZelda){
            makeBoard();
            setTheme(2);
        }
        else if(e.getSource()==menuHelp){
            JOptionPane.showMessageDialog(frame, "To win a round of Minesweeper, you must click on the board every square that doesn't have a mine under it. \nOnce you've done so, the game will be over. If you accidentally click a square that has a mine beneath it, the game will be over.\nYou'll have the option of starting a new game or redoing the one you just played.");
        }
    }
    public void mousePressed(MouseEvent event){

    }
    public void mouseExited(MouseEvent event){

    }
    public void mouseEntered(MouseEvent event){

    }
    public void mouseReleased(MouseEvent e){
        boolean gameOver = true;
        if(e.getButton() == MouseEvent.BUTTON3 && !firstClick){
            for(int x=0;x<toggles.length;x++)
                for(int y=0;y<toggles[0].length;y++){
                    if(e.getSource() == toggles[x][y]){
                        if(!toggles[x][y].isSelected()){
                            if(toggles[x][y].getIcon()==flag){
                                toggles[x][y].setIcon(null);
                                flags++;
                            }
                            else{
                                if(flags>0){
                                    toggles[x][y].setIcon(flag);
                                    flags--;
                                }
                            }
                        }
                    }
                }
        }
        else if(e.getButton()==MouseEvent.BUTTON1){
            for(int x=0;x<toggles.length;x++)
                for(int y=0;y<toggles[0].length;y++){
                    if(e.getSource() == toggles[x][y]){
                        if(firstClick){
                            firstClick = false;
                            game = new Game(difficulty, y, x);
                            for(EmptySpace em : EmptySpace.blobSpaces){
                                toggles[em.getY()][em.getX()].setSelected(true);
                            }
                        }
                        for(int i=0;i<game.getBoard().getHeight();i++){
                            for(int j=0;j<game.getBoard().getWidth();j++){
                                if(toggles[i][j].getIcon()==null)
                                    gameOver = false;
                                if(toggles[i][j].isSelected() && game.getBoard().get(j, i) instanceof ValueSpace && toggles[i][j].getIcon()!=flag){
                                    switch(((ValueSpace)game.getBoard().get(j,i)).value){
                                        case 0:
                                        break;
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
                                            toggles[i][j].setIcon(mine);
                                        break;
                                    }
                                }
                                if(toggles[i][j].isSelected() && game.getBoard().get(j, i) instanceof EmptySpace){
                                    toggles[i][j].setIcon(white);
                                }
                                if(toggles[i][j].isSelected() && game.getBoard().get(j, i) instanceof MineSpace){
                                    toggles[i][j].setIcon(mine);
                                    time.cancel();
                                    switch((int)JOptionPane.showConfirmDialog(frame, "Do you wish to restart?", "Game Over :(", JOptionPane.YES_NO_OPTION)){
                                        case 0:
                                            makeBoard();
                                        break;
                                        case 1:
                                            frame.setVisible(false);
                                            frame.dispose();
                                            System.exit(0);
                                        break;
                                    }
                                }
                            }
                        }
                        if(gameOver && !firstClick){
                            time.cancel();
                            switch((int)JOptionPane.showConfirmDialog(frame, "You finished in " + timePassed+ "s. \nDo you wish to restart?", "You Win!!", JOptionPane.YES_NO_OPTION)){
                                case 0:
                                    makeBoard();
                                break;
                                case 1:
                                    frame.setVisible(false);
                                    frame.dispose();
                                    System.exit(0);
                                break;
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
        new Minesweeper();
    }
    private class UpdateTimer extends TimerTask {
    public void run() {
            timePassed++;
            timer.setText("Time: "+timePassed + "     Flags: " + flags);
        }
    }
}

