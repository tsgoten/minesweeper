public class Game{
    Board board;
    public Game(Difficulty difficulty, int x, int y){
        switch(difficulty){
            case easy:
                board = new Board(9, 9, 10, x, y);
            break;
            case intermediate:
                board = new Board(16, 16, 40, x, y);
            break;
            case expert:
                board = new Board(30, 16, 99, x, y);
            break;
        }
    }
    public Board getBoard(){
        return board;
    }
    

}

