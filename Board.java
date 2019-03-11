import java.util.ArrayList;

public class Board {

    private int width, height;
    private int mines;
    private EmptySpace initialSpace;
    private Space [][] board;

    public Board(int width, int height, int mines, int initialX, int initialY){
        this.width = width;
        this.height = height;
        this.mines = mines;
        initialSpace = new EmptySpace(initialX, initialY, true);
        board = new Space [height][width];
        board[initialY][initialX] = initialSpace;
        generateMines();
        addNumbers();
        createBlob(initialSpace);
        printBoard();
    }
    private void generateMines(){
        for(int i=0;i<mines;i++){
            MineSpace mineSpace = new MineSpace(initialSpace, this);
            add(mineSpace);
        }
    }
    private void addNumbers(){
        for(MineSpace m : MineSpace.mineList){
            try {
                for(Space s : m.getSurroundingSpaces()){
                    if(get(s) == null){
                        add(new ValueSpace(s.getX(), s.getY(), 1));
                    }
                    else if (get(s) instanceof ValueSpace){
                            ((ValueSpace) get(s)).iterateValue();
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e) { }
        }
        for(int i=0;i<height;i++)
            for(int j=0;j<width;j++)
                if(board[i][j]==null)
                    add(new EmptySpace(j, i));
    }
    private void createBlob(Space s){
        if(!(get(s) instanceof EmptySpace) || s == null)
            return;
        else {
            for(Space adj : s.getAdjacentSpaces()){
                createBlob(adj);
            }
        }
        if(s instanceof EmptySpace)
            ((EmptySpace) s).setAsPartOfBlob();
        return;

    }
    public void add(Space space){
        board[space.getY()][space.getX()] = space;
    }
    public Space get(int x, int y){
        try {
            return board[y][x];
        } catch (ArrayIndexOutOfBoundsException e){
            return null;
        }
    }
    public Space get(Space space){
        try {
            return board[space.getY()][space.getX()];
        } catch (ArrayIndexOutOfBoundsException e){
            return null;
        }
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public void printBoard(){
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
    }
    public static void main (String [] args){
        Board app = new Board(9, 9, 10, 3,3);
    }
}