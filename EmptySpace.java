public class EmptySpace extends ValueSpace{
    boolean partOfBlob;
    public EmptySpace(int x, int y){
        super(x, y, 0);
        partOfBlob = false;
    }
    public EmptySpace(int x, int y, boolean inBlob){
        super(x, y, 0);
        partOfBlob = inBlob;
    }
    public void setAsPartOfBlob(){
        partOfBlob = true;
    }
    public boolean isInBlob(){
        return partOfBlob;
    }
    
}