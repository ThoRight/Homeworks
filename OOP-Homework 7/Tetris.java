import java.util.Scanner; // for getting input from user
import java.lang.Thread; // for random function

/**
* @author Murat Erbilici
* @since 20.01.2023
*/
public class Tetris{
    private char[][] board; /* Double pointer of my board */
    private int height; /* Board's height and width */
    private int width;
    private int lc; /* This keeps left bottom corner index of Tetromino that has been just added */
    private int rc; /* This keeps right bottom corner index of Tetromino that has been just added */

/**
* Constructor that creates board according to parameters
* @param height1 for board's height
* @param width1 for board's width
*/
    public Tetris(int height1, int width1){
        int i,j;
        board = new char[height1][width1];

        for(i=0;i<height1;++i)
            for(j=0;j<width1;++j)
                board[i][j] = 0;
        height = height1;
        width = width1;
    }
    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }
    public int getlc(){
        return lc;
    }
    public int getrc(){
        return rc;
    }
    public void setlc(int value){
        lc = value;
    }
    public void setrc(int value){
        rc = value;
    }

/**
* Determines wheter it is 'I' tetromino and its position is vertical or not
* @param piece tetromino that is wanted to be checked
* @param direction control is being happened according to direction ('R' or 'L')
* @return true if it's I, false otherwise
*/    
    private boolean isI(Tetromino piece, char direction){
        if(direction == 'R'){
            if(piece.getshape() == shapes.I && piece.getY()==3 && piece.gettetro(0,0)!=0) return true;
            else return false;
        }
        else if(direction == 'L'){
            if(piece.getshape() == shapes.I && piece.getY()==3 && piece.gettetro(0,0)==0) return true;
            else return false;
        }
        else return false;
    }
/** 
* Determines if its in board or not
* @param direction Left or Right
* @param count number of counting
* @return true if its boards boundry, false otherwise
*/
    private boolean check_index(char direction, int count){
        if(direction == 'R'){
            if(rc+count >= width) return false;
        }
        else if(direction == 'L'){
            if(lc-count<0) return false;
        }
        return true;
    }

/**
* Adds the tetromino default position(at the top row in the middle)
* @param piece piece that we want to add
*/
    public void add(Tetromino piece){
        int i,j,x,y;
        shapes shape;
        shape = piece.getshape();
        x = piece.getX();
        y = piece.getY();

        lc=width/2-1;
        rc=lc+x-1;

        for(i=0;i<y;i++){
            if(x==3){
                board[i+3][width/2-1] = piece.gettetro(i,0);  /* Since some pieces have 2 columns and some pieces have 3 columns*/
                board[i+3][width/2] = piece.gettetro(i,1);    /* wrote these two if statements */
                board[i+3][width/2+1] = piece.gettetro(i,2);
            }
            else if(x==2){
                board[i+3][width/2-1] = piece.gettetro(i,0);
                board[i+3][width/2] = piece.gettetro(i,1); 
            }
        }
    }
/**
* Adds piece to new position in the board after rotating
* @param piece piece that we want to add
* @param y location of y for tetromino that will be added
* @param x location of x for tetromino that will be added
*/
    public void add(Tetromino piece, int y, int x){
        int i,j,tempx;
        for(i=0;i<piece.getY();++i){
            tempx=x;
            for(j=0;j<piece.getX();++j){
                board[i][tempx] = piece.gettetro(i,j);
                ++tempx;
            }
        }
    }

/**
* Draws the board
*/
    public void draw(){
        int i,j,row,column;

        row = getHeight();
        column = getWidth();

        for(i=0;i<column+2;++i) System.out.printf("#");
        System.out.printf("\n");
        for(i=3;i<row;++i){
            System.out.printf("|");
            for(j=0;j<column;++j){
                if(board[i][j]==0) System.out.printf(" ");
                else System.out.printf("%c", board[i][j]);
            }
            System.out.printf("|");
            System.out.printf("\n");
        }
        for(i=0;i<column+2;++i) System.out.printf("-");;
        System.out.printf("\n");
    }

/**
* Animates the added tetromino droping to the bottom of the board
* @param piece piece that will be animated
*/
    public void animate(Tetromino piece){
        int i,j,k,start_column,end_row,pieceRow,count=0,random;
        int flag=1;
        int z=0;
        boolean check=true,check2;
        char[][] tempboard;
        char dir;
        String direction;


        erase(piece);
        random = (int)(Math.random() * 3);  // random decides rotation direction randomly.
        if(random !=0 && piece.getshape()!= shapes.O){
            count = (int)(Math.random() * 4); // count decides rotation count randomly.
            if(random==1)
                for(i=0;i<count;++i) piece.rotate('R');
            else
                for(i=0;i<count;++i) piece.rotate('L');
        }
    
    
        this.add(piece);
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        this.draw();

        // This is for preventing the any problem caused by empty part of 'I' tetromino (because it has 2d)
        if(piece.getshape()==shapes.I && piece.getX()==3 && piece.gettetro(0,0)!=0)
            piece.verticalreverse();
        
        while(true){ // after random movement count, boundry is being checked in this loop. if boundry is not proper, loop is continue.
            check = true;
            random = (int)(Math.random() * 2); // random decides movement direction randomly.
            count = (int)(Math.random() * width); // count decides movement count randomly.
            if(random==0) dir = 'R';
            else dir = 'L';
            check2 = isI(piece, dir);
    
    
            //Special movement for vertical 'I' Tetromino
            if(check2==true && board[3][lc]==0 && lc-count>=-1){
                piece.rotate('R');
                piece.rotate('R');
                lc-=count-1;
                rc-=count-1;
            }
            else if(check2==true && board[3][lc]!=0 && lc+count<width){
                piece.rotate('R');
                piece.rotate('R');
                lc+=count-1;
                rc+=count-1;
            }
            else if(check2==true) check=false;
    
    
            else check = check_index(dir, count);
            if(check==false){
                erase(piece);
            }
            else{
                break;
            }
        }

        if(check2!= true && dir == 'R'){
            lc+=count;
            rc+=count;
        }
        else if(check2!= true && dir == 'L'){
            lc-=count;
            rc-=count;
        }
        erase(piece);
        pieceRow = piece.getY();
        start_column=lc;
        end_row=3;
        for(i=4;i<height;++i){ /* Start looking from top until touch the ground or another tetromino */
            end_row=i-1;
            for(j=lc;j<lc+piece.getX();++j){
                if(board[i][j] !=0 && piece.gettetro(pieceRow-1,z) !=0){
                    flag=0;
                    break;
                }
                else if((board[i][j] !=0 && piece.gettetro(pieceRow-1,z) == 0 && piece.gettetro(pieceRow-2,z)!=0)){
                    if(z==0 && i!=height-1 && piece.gettetro(pieceRow-1,z+1)!=0 && board[i][j+1]!=0){
                        flag=0;
                        break;
                    }
                    flag=0;
                    end_row = i;
                    break;
                }
                ++z;
            }
            if(flag==0) break;
            z=0;
        }
        if(flag==1) end_row=height-1;
        

        add(piece,end_row,start_column);


        tempboard = new char[height][width];

        for(i=0;i<height;++i)
            for(j=0;j<width;++j)
                tempboard[i][j] = board[i][j];

        this.draw();

        for(k=end_row-piece.getY()+1;k>0;--k){    /* Each step, piece's row decrement by one */
            for(i=end_row;i>0;--i){
                for(j=start_column;j<piece.getX()+start_column;++j){
                    board[i][j] = board[i-1][j];
                    board[i-1][j] = 0;
                }
            }
            System.out.print("\033[H\033[2J");  
            System.out.flush();  
            this.draw();
            try{
                Thread.sleep(50);
            } 
            catch(InterruptedException e){
            // this part is necessary for sleep function
            }
        }       
        for(i=0;i<this.height;++i){  /* After decrementing, Equalizing board and tempboard with some conditions to prevent losing any part of pieces */
            for(j=0;j<this.width;++j){
                if((board[i][j] != tempboard[i][j] && tempboard[i][j] != 0 && board[i][j] != 0 )|| (tempboard[i][j]!=0 && board[i][j]==0 && i>3)) 
                    board[i][j] =tempboard[i][j];
            }
        }
        System.out.print("\033[H\033[2J");  
        System.out.flush();   
        if(end_row-piece.getY()+1 > 5){
            this.erase(piece);
            this.erase(piece,start_column);
        }
        this.draw();
        if(this.isover()){
            System.out.printf("GAME OVER!\n");
            System.exit(0);
        }
    
}

/**
* Erases the default position of piece after new location of piece is determined
* @param piece piece that will be erased
*/
    public void erase(Tetromino piece){
        int i,x,y;
    
        shapes shape;
        shape = piece.getshape();
    
        x = piece.getX();
        y = piece.getY();
    
        for(i=0;i<y;i++){
            if(x==3){
                board[i+3][width/2-1] = 0;
                board[i+3][width/2] = 0;
                board[i+3][width/2+1] = 0;
            }
            else if(x==2){
                board[i+3][width/2-1] = 0;
                board[i+3][width/2] = 0;
            }
        }
    }

/**
* Erase the piece by starting from start column
* @param piece piece that will be erased
* @param start_column it shows first column of the piece
*/
    public void erase(Tetromino piece, int start_column){
        int i,j;
        for(i=0;i<piece.getY();++i)
            for(j=start_column;j<piece.getX()+start_column;++j)
                board[i][j]=0;
    }

/**
* After initializing board, starts the game for user
*/
    public void play(){
        Scanner inp = new Scanner(System.in);
        int random;
        shapes shape = shapes.O;
        System.out.print("\033[H\033[2J");  
        System.out.flush();   
        this.draw();
        while(true){
            System.out.printf("Enter tetromino type (Capital I,O,T,J,L,S,Z) (R for random, Q for quit.)\n");
            char type;
            while(true){
                String input;
                while(true){
                input = inp.nextLine();
                type = input.charAt(0);
                if(input.length()>1 ||(type!='I' && type!='O' && type!='T' && type!='J' && 
                type!='L' && type!='S' && type!='Z' && type!= 'R' && type!='Q'))
                    System.out.printf("Please enter the proper input.\n");
                else break;
                }
                if(type=='Q') break;
			    else if(type == 'R'){
                    random = (int)(Math.random() * 7);
                    switch(random){       
                        case 0: 
                            type = 'I';
                            shape = shapes.I;
                        break;
                        case 1: 
                            type = 'S';
                            shape = shapes.S;
                        break;
                        case 2:
                            type = 'Z';
                            shape = shapes.Z;
                        break;
                        case 3: 
                            type = 'L';
                            shape = shapes.L;
                        break;
                        case 4:
                            type = 'T';
                            shape = shapes.T;
                        break;
                        case 5: 
                            type = 'J';
                            shape = shapes.J;
                        break;
                        case 6: 
                            type = 'O';
                            shape = shapes.O;
                        break;
                    }
                }
                else{
                    switch(type){
                        case 'I': shape = shapes.I;
                        break;
                        case 'S': shape = shapes.S;
                        break;
                        case 'Z': shape = shapes.Z;
                        break;
                        case 'L': shape = shapes.L;
                        break;
                        case 'T': shape = shapes.T;
                        break;
                        case 'J': shape = shapes.J;
                        break;
                        case 'O': shape = shapes.O;
                        break;
                    }
                }
                break;
		    }
            if(type =='Q'){
                System.out.printf("Quitting....\n");
                break;
            }
            Tetromino t = new Tetromino(shape); 
            this.add(t);
            
            System.out.print("\033[H\033[2J");  
            System.out.flush();    

            this.draw();
            try{
                Thread.sleep(50);
            } 
            catch(InterruptedException e){
             // this part is necessary for sleep function.
            }
            this.animate(t);       
        } 
    }

/**
* Decides if the game is over or not by checking first row of the board
* @return true if game is over, false otherwise
*/
    public boolean isover(){
        for(int i=0;i<width;++i)
            if(board[3][i]!=0) return true;
        return false;
    }

}