public class Board {

    private int width, height;
    private int mines;
    private EmptySpace initialSpace;
    private Space [][] board;

    public Board(int width, int height, int mines, int initialX, int initialY){
        this.width = width;
        this.height = height;
        this.mines = mines;
        initialSpace = new EmptySpace(initialX, initialY);
        board = new Space [height][width];
        board[initialY][initialX] = initialSpace;
        generateMines();
    }
    private void generateMines(){
        for(int i=0;i<mines;i++){
            MineSpace mineSpace = new MineSpace(initialSpace, this);
            add(mineSpace);
        }
    }
    public void add(Space space){
        board[space.getY()][space.getX()] = space;
    }
    public Space get(int x, int y){
        return board[y][x];
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}