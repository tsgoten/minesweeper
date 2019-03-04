import java.util.ArrayList;

public class Game {

    private int w, h;
    int flags;
    int [][] board;

    ArrayList<int []> mineCoordinates;
    ArrayList<ArrayList<Integer>> blob;
    public Game(int dimW, int dimH, int numberOfMines){
        w = dimW;
        h = dimH;
        flags = numberOfMines;
        board = new int[w][h];

        mineCoordinates = new ArrayList<>();
        blob = new ArrayList<>();
    }
    public void firstClick(int buttonX, int buttonY){
        for(int i=0;i<10;i++){
            int mineX = (int)(Math.random()*w);
            int mineY = (int)(Math.random()*h);
            while(board[mineY][mineX]<0 || (Math.abs(mineX-buttonX)<=1 && Math.abs(mineY-buttonY)<=1)) {
                mineX = (int)(Math.random()*w);
                mineY = (int)(Math.random()*h);
            }
            board[mineY][mineX] = -10;
            int [] r = {mineX, mineY};
            mineCoordinates.add(r);
        }
        generateNumbers(mineCoordinates);
        printBoard();
        generateBlob(buttonX, buttonY);
        

    }
    public void generateBlob(int buttonX, int buttonY){
        ArrayList<int []> checkingCurrently = new ArrayList<>();
        checkingCurrently.add(coord(buttonX, buttonY));
        while(checkingCurrently.size()>0){
            ArrayList<int []> checkingNext = new ArrayList<>();
            for(int [] r : checkingCurrently){
                boolean partOfBlob = false;
                for(int [] a : getAdjacentSpaces(r)){
                    if(boardVal(a)==0){
                        ArrayList <Integer> aL = new ArrayList<>();
                        aL.add(a[0]);
                        aL.add(a[1]);
                        if(!blob.contains(aL)){
                            checkingNext.add(a);
                        }
                        partOfBlob = true;
                    }
                }
                if(partOfBlob){
                    boolean isUnique = true;
                    // for(int [] h : blob){
                    //     if(h[0]==r[0] && h[1]==r[1])
                    //         isUnique = false;
                    // }
                    if(isUnique){
                        ArrayList<Integer> re = new ArrayList<>();
                        re.add(r[0]);
                        re.add(r[1]);
                        blob.add(re);

                    }
                }
            }
            System.out.println(blob);
            checkingCurrently.clear();
            checkingCurrently.addAll(checkingNext);
            checkingNext.clear();
 
            // for(int [] a : blob){
            //     System.out.println("blob: " + a[0] + " " + a[1]);
            // }
        }
    
    }
    public int boardVal(int [] a){
        return board[a[0]][a[1]];
    }
    public ArrayList<int []> getAdjacentSpaces(int [] r){
        ArrayList<int []> result = new ArrayList<>();
        result.add(coord(r[0]-1, r[1]));
        result.add(coord(r[0], r[1]-1));
        result.add(coord(r[0]+1, r[1]));
        result.add(coord(r[0], r[1]+1));
            
        ArrayList<int []> resultNoException = new ArrayList<>();
        for(int [] a : result){
            if(a[0]>=0 && a[1]<w && a[1]>=0 && a[1]<h)
                resultNoException.add(a);
        }
        return resultNoException;
    }
    public void generateNumbers(ArrayList<int []> mines){
        for(int [] r : mines){
            int x = r[0];
            int y = r[1];
            ArrayList<int []> spaces = surroundingSpaces(x, y);
            for(int [] space : spaces){
                try{
                    board[space[1]][space[0]] = board[space[1]][space[0]]+1;
                }catch(ArrayIndexOutOfBoundsException e){
                }
            }
        }
    }
    public ArrayList<int []> surroundingSpaces(int x, int y){
        ArrayList<int []> result = new ArrayList<>();
        result.add(coord(x-1, y-1));
        result.add(coord(x, y-1));
        result.add(coord(x+1, y-1));
        result.add(coord(x-1, y));
        result.add(coord(x+1, y));
        result.add(coord(x-1, y+1));
        result.add(coord(x, y+1));
        result.add(coord(x+1, y+1));
        return result;
    }
    public int [] coord(int x, int y){
        int [] r = {x, y};
        return r;
    }

    public void printBoard(){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
    }

}