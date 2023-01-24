
enum shapes{I,O,T,J,L,S,Z};


/**
* @author Murat Erbilici
* @since 20.01.2023
*/
public class Tetromino{
    private char[][] tetro; // 2d tetromino
	private shapes shapePiece; // type of tetromino
	private int x; // number of column of tetromino
	private int y; // number of row  of tetromino


/**
* Constructor that creates a tetromino according to parameter
* @param shape type of shape for tetromino that we want to construct
*/
    public Tetromino(shapes shape){
    	setshape(shape);
    	int i,j;
    	switch(shapePiece){  /* initializing tetramino according to parameter */
    	case I:
    		y=2;
    		x=3;
    		tetro = new char[2][3];

            tetro[0][0] = 0;
            tetro[0][1] = 0;
            tetro[0][2] = 0;
    		tetro[1][0] = 'I';
    		tetro[1][1] = 'I';
    		tetro[1][2] = 'I';
    	break;
    		case O:
    		y=2;
    		x=2;
    		tetro = new char[2][2];

    		tetro[0][0] = 'O';
    		tetro[0][1] = 'O';
    		tetro[1][0] = 'O';
    		tetro[1][1] = 'O';
    
    	break;
    		case T:
    		y=2;
    		x=3;
    		tetro = new char[2][3];

    		tetro[0][0] = 'T';
    		tetro[0][1] = 'T';
    		tetro[0][2] = 'T';
    		tetro[1][0] = 0;
    		tetro[1][1] = 'T';
    		tetro[1][2] = 0;
    	break;
    		case J:
    		y=3;
    		x=2;
    		tetro = new char[3][2];

    		tetro[0][0] = 0;
    		tetro[0][1] = 'J';
    		tetro[1][0] = 0;
    		tetro[1][1] = 'J';
    		tetro[2][0] = 'J';
    		tetro[2][1] = 'J';
    	break;
    		case L:
    		y=3;
    		x=2;
    		tetro = new char[3][2];

    		tetro[0][0] = 'L';
    		tetro[0][1] = 0;
    		tetro[1][0] = 'L';
    		tetro[1][1] = 0;
    		tetro[2][0] = 'L';
    		tetro[2][1] = 'L';
    	break;
    		case S:
    		y=2;
    		x=3;
    		tetro = new char[2][3];

    		tetro[0][0] = 0;
    		tetro[0][1] = 'S';
    		tetro[0][2] = 'S';
    		tetro[1][0] = 'S';
    		tetro[1][1] = 'S';
    		tetro[1][2] = 0;
    	break;
    		case Z:
    		y=2;
    		x=3;
    		tetro = new char[2][3];

    		tetro[0][0] = 'Z';
    		tetro[0][1] = 'Z';
    		tetro[0][2] = 0;
    		tetro[1][0] = 0;
    		tetro[1][1] = 'Z';
    		tetro[1][2] = 'Z';
    	break;
    	}
    }

/**
* Prints the tetromino
*/
    public void print(){
	    int i,j;
	    for(i=0;i<y;i++){
	    	for(j=0;j<x;j++){
	    		if(tetro[i][j]==0) System.out.printf(" ");
	    		System.out.printf("%c", tetro[i][j]);
	    	}
	    	System.out.printf("\n");
	    }
	    System.out.printf("\n");
    }


/**
* Rotates the tetromino according to parameter
* @param dir 'R' for right, 'L' for left
*/
    public void rotate(char dir){ /* These rotating operations work like matrix rotation (not exactly same for some part) */
	    if(dir=='L'){ 
		    if(x==3){
			    this.reverse();
			    this.transpose();
		    }

		    else{
			    this.transpose();
			    this.verticalreverse();
		    }
	    }
	    else if(dir=='R'){
		    if(x==3){
			    this.verticalreverse();
			    this.transpose();
		    }
		    else{
			    this.transpose();
		    	this.reverse();
		    }
	    }
    }
/**
* Helper function for rotate(horizontally reverse)
*/
    private void reverse(){
	    char temp;
	    int i=0;
	    if(x==3){
		    for(i=0;i<2;++i){
			    temp = tetro[i][0];
			    tetro[i][0] = tetro[i][2];
			    tetro[i][2] = temp;
		    }
	    }
	    else if(x==2){
		    for(i=0;i<2;++i){
			    temp = tetro[i][0];
			    tetro[i][0] = tetro[i][1];
			    tetro[i][1] = temp;
		    }
	    }
    }

/**
* Helper function for rotate(vertically reverse)
*/
	public void verticalreverse(){
	    char temp;
	    int i;
	    if(y==3){ /* we already know that  y=3 => x=2 and vice versa*/
		    for (i=0;i<2;++i){
			    temp = tetro[0][i];
			    tetro[0][i] = tetro[2][i];
			    tetro[2][i] = temp;
		    }
	    }
	    else if(y==2){
		    for (i=0;i<3;++i){
			    temp = tetro[0][i];
			    tetro[0][i] = tetro[1][i];
			    tetro[1][i] = temp;
		    }
	    }
    }

/**
* Helper function for rotate
*/
    private void transpose(){
	    int i,j;
	    int r=y;
	    int c=x;
	    int tempint;
	    setX(r);
	    setY(c);
        char[][] temp;
	    temp = new char[c][r];
	    
	    if(shapePiece == shapes.O);  //intentionally empty Because 'O' Tetromino doesn't have to be rotated
	    else{
		    for(i=0;i<c;++i){
			    for(j=0;j<r;++j){
				    temp[i][j] = tetro[j][i];
			    }
		    }
		    tetro=temp;
	    }
	
    }

    public void setshape(shapes s){
        shapePiece = s;
    }
    public shapes getshape(){
        return shapePiece;
    }
    public void setX(int value){
	    x=value;
    }
    public void setY(int value){
	    y=value;
    }
    public int getX(){
	    return x;
    }
    public int getY(){
	    return y;
    }
    public char gettetro(int y, int x){
	    return tetro[y][x];
    }

}