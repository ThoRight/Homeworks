#include "TetrisAdapter.h"
#include <fstream>
#include <unistd.h>

namespace MuratTetris{

template <typename T>
TetrisAdapter<T>::TetrisAdapter(int height1, int width1){
    int i;
    totalsize = height1 * width1;
    height = height1;
    width = width1;
    for(i=0;i<totalsize;++i)
        board.push_back(0);
    
}

template <typename T>
void TetrisAdapter<T>::draw()const{
    int i,j,row,column,indx;

    row = getHeight();
    column = getWidth();
    for(i=0;i<column+2;++i) cout << "#";
    cout << endl;
    for(i=3;i<row;++i){
        cout << "|";
        for(j=0;j<column;++j){
            indx = twotoone(i,j);
            cout << board[indx];
            if(board[indx]==0) cout << " ";
        }
        cout << "|";
        cout << endl;
    }
    for(i=0;i<column+2;++i) cout << "-";
    cout << endl;
}


/* This is in order to add piece to new position in the board after rotating (different from the operator+=)*/
template <typename T>
void TetrisAdapter<T>::add(const Tetromino &piece, const int &y, const int &x){
    int i,j,tempx,indx;
    for(i=0;i<piece.getY();++i){
        tempx=x;
        for(j=0;j<piece.getX();++j){
            indx = twotoone(i,tempx);
            board[indx] = piece.gettetro(i,j);
            ++tempx;
        }
    }
}

template <typename T>
void TetrisAdapter<T>::animate(Tetromino &piece){
    int i,j,k,start_column,end_row,count1=0,count2=0,flag=1,z=0,pieceRow,indx;
    bool check=true;
    char* tempboard;
    string direction1;
    string direction2;


    while(1){
        cout << "Choose the direction to rotate piece(R for right, L for left, 0 for no rotation): ";
        cin >> direction1;
        if(direction1.size()!=1 || (direction1[0] != 'R' && direction1[0] != 'L' && direction1[0] != '0'))
            cout << "Please enter proper input\n";
        else break;
    }
    if(direction1[0]!='0'){
        cout << "Enter the rotation count: ";
        while(!(cin >> count1) || count1 < 0){
	        cin.clear();
            cin.ignore(1000, '\n');
  	        cout << "Please enter proper input.\n";
	    }
        cin.ignore(1000, '\n');
    }

    erase(piece);
    
    if(direction1[0]!='0')
        for(i=0;i<count1;++i) piece.rotate(direction1[0]);
    
    
    *this+=piece;
    system("clear");
    this->draw();

    // This is for preventing the any problem caused by empty part of 'I' tetromino (because it has 2d)
    if(piece.getshape()==I && piece.getX()==3 && piece.gettetro(0,0)!=0)
        piece.verticalreverse();
   
    while(1){
        cout << "Choose the direction to move piece(R for right, L for left, 0 for no movement): ";
        cin >> direction2;
        if(direction2.size()!=1 || (direction2[0] != 'R' && direction2[0] != 'L' && direction2[0] != '0'))
            cout << "Please enter proper input\n";
        else break;
    }
    if(direction2[0]!='0'){
        cout << "Enter the movement count: ";
        while(!(cin >> count2) || count2 < 0){
	        cin.clear();
            cin.ignore(1000, '\n');
  	        cout << "Please enter proper input.\n";
	    }
        cin.ignore(1000, '\n');
    }

    
    
    bool check2 = isI(piece, direction2[0]);
    
    
    //Special movement for vertical 'I' Tetromino
    indx = twotoone(3,lc);
    if(check2==true && board[indx]==0 && lc-count2>=-1){
        lc-=count2;
        rc-=count2;
    }
    else if(check2==true && board[indx]!=0 && lc+count2<width){
        lc+=count2;
        rc+=count2;
    }
    else if(check2==true) check=false;
    
    
    else check = check_index(direction2[0], count2);
    if(check==false){
        erase(piece);
        system("clear");
        this->draw();
        cout << "Wrong input. Please stay the board's boundry!\n";
    }
    else{
        
        lm.clear();
        lm.push_back(piece.getshape());
        lm+="        ";
        lm.push_back(direction1[0]);
        lm+="             ";
        lm+=to_string(count1);
        lm+="             ";
        lm.push_back(direction2[0]);
        lm+="            ";
        lm+=to_string(count2);

        nom = nom + count1 + count2;


        if(check2!= true && direction2[0] == 'R'){
            lc+=count2;
            rc+=count2;
        }
        else if(check2!= true && direction2[0] == 'L'){
            lc-=count2;
            rc-=count2;
        }
        erase(piece);
        pieceRow = piece.getY();
        start_column=lc;
        end_row=3;
        for(i=4;i<height;++i){ /* Start looking from top until touch the ground or another tetromino */
            end_row=i-1;
            for(j=lc;j<lc+piece.getX();++j){
                indx = twotoone(i,j);
                if(board[indx] !=0 && piece.gettetro(pieceRow-1,z) !=0){
                    flag=0;
                    break;
                }
                else if((board[indx] !=0 && piece.gettetro(pieceRow-1,z) == 0 && piece.gettetro(pieceRow-2,z)!=0)){
                    if(z==0 && i!=height-1 && piece.gettetro(pieceRow-1,z+1)!=0 && board[indx+1]!=0){
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


        tempboard = new char[totalsize];

        for(i=0;i<height;++i){
            for(j=0;j<width;++j){
                indx = twotoone(i,j);
                tempboard[indx] = board[indx];
            }
        }

        this->draw();
        int indx2;
        for(k=end_row-piece.getY()+1;k>0;--k){    /* Each step, piece's row decrement by one */
            for(i=end_row;i>0;--i){
                for(j=start_column;j<piece.getX()+start_column;++j){
                    indx = twotoone(i,j);
                    indx2 = twotoone(i-1,j);
                    board[indx] = board[indx2];
                    board[indx2] = 0;
                }
            }
            system("clear");
            this->draw();
            usleep(50000);
        } 
        for(i=0;i<this->height;++i){  /* After decrementing, Equalizing board and tempboard with some conditions to prevent losing any part of pieces */
            for(j=0;j<this->width;++j){
                indx = twotoone(i,j);
                if((board[indx] != tempboard[indx] && tempboard[indx] != 0 && board[indx] != 0 )|| (tempboard[indx]!=0 && board[indx]==0 && i>3)) 
                    board[indx] = tempboard[indx];
            }
        }
        system("clear");
        if(end_row-piece.getY()+1 > 5){
            this->erase(piece);
            this->erase(piece,start_column);
        }
        this->draw();       
        delete [] tempboard;
    }
}

template <typename T>
void TetrisAdapter<T>::animate(Tetromino &piece, string d1, int c1, string d2, int c2){
    int i,j,k,start_column,end_row,count1=c1,count2=c2,flag=1,z=0,pieceRow,indx;
    bool check=true;
    char* tempboard;
    string direction1 = d1;
    string direction2 = d2;

    erase(piece);
    
    if(direction1[0]!='0')
        for(i=0;i<count1;++i) piece.rotate(direction1[0]);
    
    
    *this+=piece;
    system("clear");
    this->draw();

    // This is for preventing the any problem caused by empty part of 'I' tetromino (because it has 2d)
    if(piece.getshape()==I && piece.getX()==3 && piece.gettetro(0,0)!=0)
        piece.verticalreverse();
   
    bool check2 = isI(piece, direction2[0]);
    
    
    //Special movement for vertical 'I' Tetromino
    indx = twotoone(3,lc);
    if(check2==true && board[indx]==0 && lc-count2>=-1){
        lc-=count2;
        rc-=count2;
    }
    else if(check2==true && board[indx]!=0 && lc+count2<width){
        lc+=count2;
        rc+=count2;
    }
    else if(check2==true) check=false;
    
    
    else check = check_index(direction2[0], count2);
    if(check==false){
        erase(piece);
        system("clear");
        this->draw();
        cout << "Wrong input. Please stay the board's boundry!\n";
    }
    else{
        /* assign the last moves data */
        lm.clear();
        lm.push_back(piece.getshape());
        lm+="        ";
        lm.push_back(direction1[0]);
        lm+="             ";
        lm+=to_string(count1);
        lm+="             ";
        lm.push_back(direction2[0]);
        lm+="            ";
        lm+=to_string(count2);

        /* assign the number of moves data */
        nom = nom + count1 + count2;


        if(check2!= true && direction2[0] == 'R'){
            lc+=count2;
            rc+=count2;
        }
        else if(check2!= true && direction2[0] == 'L'){
            lc-=count2;
            rc-=count2;
        }
        erase(piece);
        pieceRow = piece.getY();
        start_column=lc;
        end_row=3;
        for(i=4;i<height;++i){ /* Start looking from top until touch the ground or another tetromino */
            end_row=i-1;
            for(j=lc;j<lc+piece.getX();++j){
                indx = twotoone(i,j);
                if(board[indx] !=0 && piece.gettetro(pieceRow-1,z) !=0){
                    flag=0;
                    break;
                }
                else if((board[indx] !=0 && piece.gettetro(pieceRow-1,z) == 0 && piece.gettetro(pieceRow-2,z)!=0)){
                    if(z==0 && i!=height-1 && piece.gettetro(pieceRow-1,z+1)!=0 && board[indx+1]!=0){
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


        tempboard = new char[totalsize];

        for(i=0;i<height;++i){
            for(j=0;j<width;++j){
                indx = twotoone(i,j);
                tempboard[indx] = board[indx];
            }
        }

        this->draw();
        int indx2;
        for(k=end_row-piece.getY()+1;k>0;--k){    /* Each step, piece's row decrement by one */
            for(i=end_row;i>0;--i){
                for(j=start_column;j<piece.getX()+start_column;++j){
                    indx = twotoone(i,j);
                    indx2 = twotoone(i-1,j);
                    board[indx] = board[indx2];
                    board[indx2] = 0;
                }
            }
            system("clear");
            this->draw();
            usleep(50000);
        } 
        for(i=0;i<this->height;++i){  /* After decrementing, Equalizing board and tempboard with some conditions to prevent losing any part of pieces */
            for(j=0;j<this->width;++j){
                indx = twotoone(i,j);
                if((board[indx] != tempboard[indx] && tempboard[indx] != 0 && board[indx] != 0 )|| (tempboard[indx]!=0 && board[indx]==0 && i>3)) 
                    board[indx] = tempboard[indx];
            }
        }
        system("clear");
        if(end_row-piece.getY()+1 > 5){
            this->erase(piece);
            this->erase(piece,start_column);
        }
        this->draw();      
        delete [] tempboard;
    }
}

template <typename T>
void TetrisAdapter<T>::writeToFile(const char* file){
    int indx,i,j;
    ofstream fp(file, ofstream::out);
    if(fp.is_open()){
        for(i=0;i<width+2;++i) fp << "#";
        fp << endl;
        for(i=3;i<height;++i){
            fp << "|";
            for(j=0;j<width;++j){
                indx = twotoone(i,j);
                if(board[indx]==0) fp << " ";
                else fp << board[indx];
            }
            fp << "|";
            fp << endl;
        }
        for(i=0;i<width+2;++i) fp << "-";
        fp << endl;
    }
    fp.close();
}

template <typename T>
void TetrisAdapter<T>::readfromFile(const char* file){
    int indx,r=2,w;
    string game;
    fstream fp; 
    fp.open(file, ios::in);
    if(fp.is_open()){
        while (getline(fp, game)){
            for(int j=0;j<width+2;++j){
                indx = twotoone(r,w);
                if(game[j]!='#' && game[j]!='|' && game[j]!='-'){
                    board[indx] = game[j];
                    ++w;
                }
            }
            ++r;
            w=0;
        }
    }
    fp.close();
}

template <typename T>
int TetrisAdapter<T>::twotoone(int y, int x)const{
    int newindex;
    newindex = y * width + x ;
    return newindex;
}

template <typename T>
void TetrisAdapter<T>::erase(const Tetromino &piece){
    int i,x,y,indx;
    
    shapes shape;
    shape = piece.getshape();
    
    x = piece.getX();
    y = piece.getY();
    
    for(i=0;i<y;i++){
        indx = twotoone(i+3,width/2-1);
        if(x==3){
            board[indx] = 0;
            board[indx+1] = 0;
            board[indx+2] = 0;
        }
        else if(x==2){
            board[indx] = 0;
            board[indx+1] = 0;
        }
    }
}

template <typename T>
void TetrisAdapter<T>::erase(const Tetromino &piece, const int &start_column){
    int i,j,indx;
    
    for(i=0;i<piece.getY();++i)
        for(j=start_column;j<piece.getX()+start_column;++j){
            indx = twotoone(i,j);
            board[indx]=0;
        }

}

template <typename T>
void TetrisAdapter<T>:: operator+=(const Tetromino& oth){
    int i,j,x,y,indx;
    
    y=oth.getY();
    x=oth.getX();
    
    lc=width/2-1;
    rc=lc+x-1;

    for(i=0;i<y;i++){
        indx = twotoone(i+3, width/2-1);
        if(x==3){
            board[indx] = oth.gettetro(i,0);  /* Since some pieces have 2 columns and some pieces have 3 columns*/
            board[indx+1] = oth.gettetro(i,1);    /* wrote these two if statements */
            board[indx+2] = oth.gettetro(i,2);
        }
        else if(x==2){
            board[indx] = oth.gettetro(i,0);
            board[indx+1] = oth.gettetro(i,1);
            
        }
    }
}



}