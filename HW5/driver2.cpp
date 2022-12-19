#include <unistd.h>
#include <iostream>
#include <cstdlib>
#include "AbstractTetris.h"
#include "TetrisArray1D.h"
#include "TetrisVector.h"


using namespace std;


int main(){

    srand(time(0));
    
    int boardx;
    int boardy;
    
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
    
    cin.ignore(1000, '\n');

    MuratTetris:: AbstractTetris* tetris; 
    MuratTetris:: TetrisArray1D board(boardy+3,boardx);
    tetris = &board;

    int random;
    shapes shape;
    system("clear");
    tetris->draw();
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
        *tetris+=t;
        system("clear");
        
        tetris->draw();
        usleep(50000);
        tetris->animate(t);       
    } 

}



 
