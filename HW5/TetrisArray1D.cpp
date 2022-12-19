#include <unistd.h>
#include <iostream>
#include <cstdlib>
#include "TetrisArray1D.h"
using namespace std;

namespace MuratTetris
{

TetrisArray1D::TetrisArray1D(const int &height1, const int &width1){
    int i,j;
    board = new char*[height1];
    
    for(i=0;i<height1;++i)
        board[i] = new char[width1];

    for(i=0;i<height1;++i)
        for(j=0;j<width1;++j)
            board[i][j] = 0;
    height = height1;
    width = width1;
}

int TetrisArray1D:: getHeight()const{
    return height;
}

int TetrisArray1D:: getWidth()const{
    return width;
}

int TetrisArray1D:: getlc()const{
    return lc;
}

int TetrisArray1D:: getrc()const{
    return rc;
}

void TetrisArray1D:: setlc(int value){
    lc = value;
}

void TetrisArray1D:: setrc(int value){
    rc = value;
}

// to determine wheter it is 'I' tetromino and its position is vertical or not
bool TetrisArray1D:: isI(const Tetromino &piece, const char &direction)const{
    if(direction == 'R'){
        if(piece.getshape() == I && piece.getY()==3 && piece.gettetro(0,0)!=0) return true;
        else return false;
    }
    else if(direction == 'L'){
        if(piece.getshape() == I && piece.getY()==3 && piece.gettetro(0,0)==0) return true;
        else return false;
    }
    else return false;
}

bool TetrisArray1D:: check_index(const char &direction, const int &count)const{
    if(direction == 'R'){
        if(rc+count >= width) return false;
    }
    else if(direction == 'L'){
        if(lc-count<0) return false;
    }
    return true;
}



// Instead of this function, we use overloaded += operator
//void Tetris:: add(const Tetromino &piece)const{
//    int i,j,x,y;
//    shapes shape;
//    shape = piece.getshape();
//    x = piece.getX();
//    y = piece.getY();
//    for(i=0;i<y;i++){
//        if(x==3){
//            board[i+3][width/2-1] = piece.gettetro(i,0);  /* Since some pieces have 2 columns and some pieces have 3 columns*/
//            board[i+3][width/2] = piece.gettetro(i,1);    /* wrote these two if statements */
//            board[i+3][width/2+1] = piece.gettetro(i,2);
//        }
//        else if(x==2){
//            board[i+3][width/2-1] = piece.gettetro(i,0);
//            board[i+3][width/2] = piece.gettetro(i,1); 
//        }
//    }
//}



/* This is in order to add piece to new position in the board after rotating (different from the operator+=)*/
void TetrisArray1D:: add(const Tetromino &piece, const int &y, const int &x)const{
    int i,j,tempx;
    for(i=0;i<piece.getY();++i){
        tempx=x;
        for(j=0;j<piece.getX();++j){
            board[i][tempx] = piece.gettetro(i,j);
            ++tempx;
        }
    }
}

void TetrisArray1D:: draw()const{
    int i,j,row,column;

    row = getHeight();
    column = getWidth();

    for(i=0;i<column+2;++i) cout << "#";
    cout << endl;
    for(i=3;i<row;++i){
        cout << "|";
        for(j=0;j<column;++j){
            cout << board[i][j];
            if(board[i][j]==0) cout << " ";
        }
        cout << "|";
        cout << endl;
    }
    for(i=0;i<column+2;++i) cout << "-";
    cout << endl;
}

void TetrisArray1D:: animate(Tetromino &piece){
    int i,j,k,start_column,end_row,count,flag=1,z=0,pieceRow;
    bool check=true;
    char** tempboard;
    string direction;

    while(1){
        cout << "Choose the direction to rotate piece(R for right, L for left, 0 for no rotation): ";
        cin >> direction;
        if(direction.size()!=1 || (direction[0] != 'R' && direction[0] != 'L' && direction[0] != '0'))
            cout << "Please enter proper input\n";
        else break;
    }
    if(direction[0]!='0'){
        cout << "Enter the rotation count: ";
        while(!(cin >> count) || count < 0){
	        cin.clear();
            cin.ignore(1000, '\n');
  	        cout << "Please enter proper input.\n";
	    }
        cin.ignore(1000, '\n');
    }
    erase(piece);
    
    if(direction[0]!='0')
        for(i=0;i<count;++i) piece.rotate(direction[0]);
    
    
    *this+=piece;
    system("clear");
    this->draw();

    // This is for preventing the any problem caused by empty part of 'I' tetromino (because it has 2d)
    if(piece.getshape()==I && piece.getX()==3 && piece.gettetro(0,0)!=0)
        piece.verticalreverse();
   
    while(1){
        cout << "Choose the direction to move piece(R for right, L for left, 0 for no movement): ";
        cin >> direction;
        if(direction.size()!=1 || (direction[0] != 'R' && direction[0] != 'L' && direction[0] != '0'))
            cout << "Please enter proper input\n";
        else break;
    }
    if(direction[0]!='0'){
        cout << "Enter the movement count: ";
        while(!(cin >> count) || count < 0){
	        cin.clear();
            cin.ignore(1000, '\n');
  	        cout << "Please enter proper input.\n";
	    }
        cin.ignore(1000, '\n');
    }
    
    bool check2 = isI(piece, direction[0]);
    
    
    //Special movement for vertical 'I' Tetromino
    if(check2==true && board[3][lc]==0 && lc-count>=-1){
        lc-=count;
        rc-=count;
    }
    else if(check2==true && board[3][lc]!=0 && lc+count<width){
        lc+=count;
        rc+=count;
    }
    else if(check2==true) check=false;
    
    
    else check = check_index(direction[0], count);
    if(check==false){
        erase(piece);
        this->draw();
        cout << "Wrong input. Please stay the board's boundry!\n";
    }
    else{
        if(check2!= true && direction[0] == 'R'){
            lc+=count;
            rc+=count;
        }
        else if(check2!= true && direction[0] == 'L'){
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
        if(end_row-piece.getY()==2){
            cout << "GAME OVER!" << endl;
            exit(EXIT_SUCCESS);
        }

        add(piece,end_row,start_column);


        tempboard = new char*[height];

        for(i=0;i<height;++i)
            tempboard[i] = new char[width];

        for(i=0;i<height;++i)
            for(j=0;j<width;++j)
                tempboard[i][j] = board[i][j];

        this->draw();

        for(k=end_row-piece.getY()+1;k>0;--k){    /* Each step, piece's row decrement by one */
            for(i=end_row;i>0;--i){
                for(j=start_column;j<piece.getX()+start_column;++j){
                    board[i][j] = board[i-1][j];
                    board[i-1][j] = 0;
                }
            }
            system("clear");
            this->draw();
            usleep(50000);
        } 
        for(i=0;i<this->height;++i){  /* After decrementing, Equalizing board and tempboard with some conditions to prevent losing any part of pieces */
            for(j=0;j<this->width;++j){
                if((board[i][j] != tempboard[i][j] && tempboard[i][j] != 0 && board[i][j] != 0 )|| (tempboard[i][j]!=0 && board[i][j]==0 && i>3)) 
                    board[i][j] =tempboard[i][j];
            }
        }
        system("clear");
        if(end_row-piece.getY()+1 > 5){
            this->erase(piece);
            this->erase(piece,start_column);
        }
        this->draw();
        for(i=0;i<height;++i)
            delete [] tempboard[i];
        delete [] tempboard;
    }
}
void TetrisArray1D:: erase(const Tetromino &piece)const{
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

void TetrisArray1D:: erase(const Tetromino &piece, const int &start_column)const{
    int i,j;
    
    for(i=0;i<piece.getY();++i)
        for(j=start_column;j<piece.getX()+start_column;++j)
            board[i][j]=0;
        

}

TetrisArray1D& TetrisArray1D:: operator+=(const Tetromino& oth){
    int i,j,x,y;
    
    y=oth.getY();
    x=oth.getX();
    
    lc=width/2-1;
    rc=lc+x-1;

    for(i=0;i<y;i++){
        if(x==3){
            board[i+3][width/2-1] = oth.gettetro(i,0);  /* Since some pieces have 2 columns and some pieces have 3 columns*/
            board[i+3][width/2] = oth.gettetro(i,1);    /* wrote these two if statements */
            board[i+3][width/2+1] = oth.gettetro(i,2);
        }
        else if(x==2){
            board[i+3][width/2-1] = oth.gettetro(i,0);
            board[i+3][width/2] = oth.gettetro(i,1);
            
        }
    }
    return *this;
}
void TetrisArray1D:: play(){
    int random;
    shapes shape;
    system("clear");
    this->draw();
    while(1){
        cout << "Enter tetromino type (Capital I, O, T, J, L, S, Z) (R for random, Q for quit.)\n";
        char type;
        while(1){
            string input;
            while(1){
            cin >> input;
            type = input[0];
            if(input.size()>1 ||(type!='I' && type!='O' && type!='T' && type!='J' && 
            type!='L' && type!='S' && type!='Z' && type!= 'R' && type!='Q'))
                cout << "Please enter the proper input.\n";
            else break;
            }
            if(type=='Q') break;
			else if(type == 'R'){
                random = rand() % 7;
                switch(random){            
                    case 0: type = 'I';
                    break;
                    case 1: type = 'S';
                    break;
                    case 2: type = 'Z';
                    break;
                    case 3: type = 'L';
                    break;
                    case 4: type = 'T';
                    break;
                    case 5: type = 'J';
                    break;
                    case 6: type = 'O';
                    break;
                }
            }
            shape = static_cast<shapes>(type);
            break;
		}
        if(type =='Q'){
            cout << "Quitting....\n";
            break;
        }
        Tetromino t(shape); 
        (*this)+=t;
        system("clear");
        
        this->draw();
        usleep(50000);
        this->animate(t);       
    } 
}

TetrisArray1D:: ~TetrisArray1D(){
    int i;
    for(i=0;i<height;++i)
        delete [] board[i];
    delete [] board;
}

}