import java.util.ArrayList;

public class MineSpace extends Space {

    static ArrayList<MineSpace> mineList = new ArrayList<>();

    public MineSpace(int x, int y){
        super(x, y);
        mineList.add(this);
    }
    public MineSpace(EmptySpace initialSpace, Board board){
        super(initialSpace.getX(), initialSpace.getY());
        int mineX = (int)(Math.random()*board.getWidth());
        int mineY = (int)(Math.random()*board.getHeight());
        while(board.get(mineX, mineY) instanceof MineSpace || (Math.abs(mineX-initialSpace.getX())<=1 && Math.abs(mineY-initialSpace.getY())<=1)){
            mineX = (int)(Math.random()*board.getWidth());
            mineY = (int)(Math.random()*board.getHeight());
        }
        setCoordinates(mineX, mineY);
        mineList.add(this);    
    }
    public int getNumberOfMines(){
        return mineList.size();
    }
    public String toString(){
        return "M";
    }

}