#include <iostream>
#include <cstdlib>
#include <unistd.h>
#include "TetrisArray1D.h"
#include "TetrisVector.h"
#include "TetrisAdapter.cpp"
#include <time.h>
#include <string>

using namespace std;

int main(){

    srand(time(NULL));
    
    int boardx;
    int boardy;
    string input;
    
    cout << "#######     Driver2     #######\n\n";
    cout << "Terminal will be deleted in driver2 !!\n\n";
    cout << "Enter the column number of tetris board: ";
    
    while(!(cin >> boardx) || boardx <= 3){
	    cin.clear();
        cin.ignore(1000, '\n');
  	    cout << "Please enter proper input.\n";
	}
	
    cin.ignore(1000, '\n');
    
    cout << "Enter the row number of tetris board: ";
    
    while(!(cin >> boardy) || boardy <= 3){
	    cin.clear();
        cin.ignore(1000, '\n');
  	    cout << "Please enter proper input.\n";
	}

    cout << "Enter the type of the Tetris class (V for vector, 1 for array1D, A for adaptor): ";
    while(1){
        cin >> input;
        if(input.size()>1 || (input[0]!= 'V' && input[0] != '1' && input[0] != 'A'))
            cout << "Please enter proper input!\n";
        else
            break;
    }
    cin.ignore(1000, '\n');
    
    MuratTetris:: AbstractTetris * tetris; /* This is for polymorphic call */

    /* According to user choice, choosing which class will be used to initialize board */
    
    if(input[0] == 'V')
        tetris = new MuratTetris:: TetrisVector(boardy+3,boardx);
    else if(input[0] == '1')
        tetris = new MuratTetris:: TetrisArray1D(boardy+3,boardx);
    else if(input[0] == 'A'){
        cout << "Enter the type of Adapter Tetris class (V for vector, D for deque, which only they include random access iterator): ";
        while(1){
            cin >> input;
            if(input.size()>1 || (input[0]!= 'V' && input[0] != 'D'))
                cout << "Please enter proper input!\n";
            else
                break;
        }
        if(input[0]=='D')
            tetris = new MuratTetris:: TetrisAdapter<deque<char>>(boardy+3,boardx);
        else
            tetris = new MuratTetris:: TetrisAdapter<vector<char>>(boardy+3,boardx);
    }
    
    int random;
    shapes shape;
    system("clear");
    tetris->draw();
    while(1){
        cout << "Enter tetromino type (Capital I, O, T, J, L, S, Z) (R for random, Q for quit.)\n";
        char type;
        while(1){
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
        MuratTetris:: Tetromino t(shape); 
        *tetris+=t;
        system("clear");
        
        tetris->draw();
        usleep(50000);
        tetris->animate(t);       
    } 
  
    delete tetris;
}



 
