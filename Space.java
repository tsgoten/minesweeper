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
    //getAdjacentSpaces
    //getSurroundingSpaces

}