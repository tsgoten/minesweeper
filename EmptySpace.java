import java.util.ArrayList;

public class EmptySpace extends ValueSpace{
    boolean partOfBlob;
    static ArrayList<EmptySpace> blobSpaces = new ArrayList<>();
    public EmptySpace(int x, int y){
        super(x, y, 0);
        partOfBlob = false;
    }
    public EmptySpace(int x, int y, boolean inBlob){
        super(x, y, 0);
        setAsPartOfBlob();
    }
    public void setAsPartOfBlob(){
        partOfBlob = true;
        if(!blobSpaces.contains(this))
            blobSpaces.add(this);
    }
    public boolean isInBlob(){
        return partOfBlob;
    }
    public String toString(){
        if(partOfBlob)
            return "_";
        else
            return "0";
    }
    
}