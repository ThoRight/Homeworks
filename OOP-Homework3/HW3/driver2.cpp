#include <unistd.h>
#include <iostream>
#include <cstdlib>
#include "Tetris.h"
#include "Tetromino.h"

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

    MuratTetris:: Tetris board(boardy+3,boardx);

    board.play();

}



 
