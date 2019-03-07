public class ValueSpace extends Space{
    
    int value;
    public ValueSpace(int x, int y, int value){
        super(x, y);
        this.value = value;
    }
    public int getValue(){
        return value;
    }
    public void iterateValue(){
        value++;
    }
}