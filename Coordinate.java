import java.util.ArrayList;

public class Coordinate {
    int x, y;
    int value;
    boolean isMine;
    boolean isFlagged;
    boolean partOfBlob;

    // Near Mine Space
    public Coordinate(int x, int y, int value){
        this.x = x;
        this.y = y;
        this.value = value;
        isMine = false;
        isFlagged = false;
        partOfBlob = false;
    }
    // Mine Space
    public Coordinate(int x, int y, boolean isMine){
        this.x = x;
        this.y = y;
        this.value = -1;
        this.isMine = isMine;
        isFlagged = false;
        partOfBlob = false;
    }
    // public ArrayList<Coordinate> surroundingSpaces(int x, int y){
    //     ArrayList<Coordinate> result = new ArrayList<>();
    //     result.add(new Coordinate(x-1, y-1));
    //     result.add(new Coordinate(x, y-1));
    //     result.add(new Coordinate(x+1, y-1));
    //     result.add(new Coordinate(x-1, y));
    //     result.add(new Coordinate(x+1, y));
    //     result.add(new Coordinate(x-1, y+1));
    //     result.add(new Coordinate(x, y+1));
    //     result.add(new Coordinate(x+1, y+1));
    //     return result;
    // }
}