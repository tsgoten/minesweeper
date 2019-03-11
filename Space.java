import java.util.ArrayList;
public class Space implements Comparable<Space>{

    int x, y;
    public Space(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void setCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int compareTo(Space space){
        if(space.getX()==x && space.getY()==y){
            return 0;
        }
        else {
            return space.getX()-x;
        }
    }
    public String toString(){
        return getX() + ", " + getY();
    }
    public ArrayList<Space> getSurroundingSpaces(){
        ArrayList<Space> result = new ArrayList<>();
        result.add(new Space(x-1, y-1));
        result.add(new Space(x-1, y+1));
        result.add(new Space(x-1, y));
        result.add(new Space(x, y-1));
        result.add(new Space(x, y+1));
        result.add(new Space(x+1, y-1));
        result.add(new Space(x+1, y+1));
        result.add(new Space(x+1, y));
        return result;
    }
    public ArrayList<Space> getAdjacentSpaces(){
        ArrayList<Space> result = new ArrayList<>();
        result.add(new Space(x-1, y));
        result.add(new Space(x, y-1));
        result.add(new Space(x, y+1));
        result.add(new Space(x+1, y));
        return result;
    }
    //getAdjacentSpaces
    //getSurroundingSpaces

}