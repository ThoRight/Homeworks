#include <iostream>
#include <cstdlib>
#include <unistd.h>
#include "TetrisArray1D.h"
#include "TetrisVector.h"
#include "TetrisAdapter.cpp"
#include <time.h>
#include <string>

int main(){
    shapes shape = T;
    MuratTetris:: Tetromino t1(shape);
    shape = L;
    MuratTetris:: Tetromino t2(shape);
    string next;
    MuratTetris:: TetrisVector tetrisvector(10+3,10);
    MuratTetris:: TetrisVector emptytetrisvector(10+3,10);

    cout << "#######     Driver1     #######\n\n";
    cout << "------- TetrisVector class's functions -------\n\n";
    cout << "Draw the board 10x10(draw function):\n";

    tetrisvector.draw();
    cout << "Overloaded operator += to Add function for 'T' tetromino(default position: at top of the board in the middle):\n";

    tetrisvector+=t1;
    tetrisvector.draw();

    cout << "Erase the piece from default poisiton :(function i added)\n";
    tetrisvector.erase(t1);
    tetrisvector.draw();

    cout << "Testing Last Move Function(there is no move yet.)\n";
    try{
        if(tetrisvector.lastMove()[0]!=0){
            cout << "           Piece  Rotate Dir.  Rotate Count  Move Dir.  Move Count" << endl;
            cout << "Last Move:   " << tetrisvector.lastMove() << endl;
        }
        else{
            throw(tetrisvector.lastMove());
        }
    }
    catch(string lm){
        cout << "There is no lastmove yet please first make a move!\n";
    } 


    cout << "\nPress enter to animate function(terminal will be deleted!)\n";
    cout << "'T' Tetromino will rotate right by 1 and move left by 1.\n"; 
    getline(cin,next);
    system("clear");
    tetrisvector.animate(t1,"R",1,"L",1);
    cout << "number of moves(should be 2): ";
    cout << tetrisvector.numberOfMoves() << endl;
    cout << "\nPress enter to continue.\n";
    getline(cin,next);
    system("clear");
    tetrisvector+=t2;
    tetrisvector.draw();
    tetrisvector.erase(t2);
    cout << "'L' Tetromino will rotate left by 1 and move right by 1.\n";
    cout << "\nPress enter to continue.\n";
    getline(cin,next);
    system("clear");
    tetrisvector.animate(t2,"L",1,"R",1);
    cout << "Testing Last Move Function\n";
    try{
        if(tetrisvector.lastMove()[0]!=0){
            cout << "           Piece  Rotate Dir.  Rotate Count  Move Dir.  Move Count" << endl;
            cout << "Last Move:   " << tetrisvector.lastMove() << endl;
        }
        else{
            throw(tetrisvector.lastMove());
        }
    }
    catch(string lm){
        cout << "There is no lastmove yet!\n";
    }
    cout << "number of moves(should be 4): ";
    cout << tetrisvector.numberOfMoves() << endl;
    cout << "Writing the game to the file 'game.txt'\n";
    tetrisvector.writeToFile("game.txt");
    cout << "New empty board\n";
    emptytetrisvector.draw();
    cout << "\nPress enter to read file 'game.txt' to load empty board.(it should look just like the board above)\n";
    getline(cin,next);
    system("clear");
    emptytetrisvector.readfromFile("game.txt");
    cout << "New Board\n";
    emptytetrisvector.draw();

    cout << "\nPress enter to continue with TetrisArray1D class's functions.\n";
    getline(cin,next);
    system("clear");


    
    shape = J;
    MuratTetris:: Tetromino t3(shape);
    MuratTetris:: TetrisArray1D tetrisarray1d(11+3,11);
    MuratTetris:: TetrisArray1D emptytetrisarray1d(11+3,11);

    shape = Z;
    MuratTetris:: Tetromino t4(shape);
    
    cout << "------- TetrisArray1D class's functions -------\n\n";
    cout << "Draw the board 11x11(draw function):\n";

    tetrisarray1d.draw();
    cout << "Overloaded operator += to Add function for 'J' tetromino(default position: at top of the board in the middle):\n";
    
    tetrisarray1d+=t3;
    tetrisarray1d.draw();

    cout << "Erase the piece from default poisiton :(function i added)\n";
    tetrisarray1d.erase(t3);
    tetrisarray1d.draw();

    cout << "Testing Last Move Function(there is no move yet.)\n";
    try{
        if(tetrisarray1d.lastMove()[0]!=0){
            cout << "           Piece  Rotate Dir.  Rotate Count  Move Dir.  Move Count" << endl;
            cout << "Last Move:   " << tetrisarray1d.lastMove() << endl;
        }
        else{
            throw(tetrisarray1d.lastMove());
        }
    }
    catch(string lm){
        cout << "There is no lastmove yet please first make a move!\n";
    } 


    cout << "\nPress enter to animate function(terminal will be deleted!)\n";
    cout << "'J' Tetromino will rotate right by 3 and move left by 9(out of boundry!!).\n"; 
    getline(cin,next);
    system("clear");
    tetrisarray1d.animate(t3,"R",3,"L",9);
    cout << "number of moves(should be 0): ";
    cout << tetrisarray1d.numberOfMoves() << endl;
    cout << "\nPress enter to continue.\n";
    getline(cin,next);
    system("clear");
    tetrisarray1d+=t4;
    tetrisarray1d.draw();
    tetrisarray1d.erase(t4);
    cout << "'Z' Tetromino will rotate left by 3 and move right by 4.\n";
    cout << "\nPress enter to continue.\n";
    getline(cin,next);
    system("clear"); 
    tetrisarray1d.animate(t4,"L",3,"R",4);
    cout << "Testing Last Move Function\n";
    try{
        if(tetrisarray1d.lastMove()[0]!=0){
            cout << "           Piece  Rotate Dir.  Rotate Count  Move Dir.  Move Count" << endl;
            cout << "Last Move:   " << tetrisarray1d.lastMove() << endl;
        }
        else{
            throw(tetrisarray1d.lastMove());
        }
    }
    catch(string lm){
        cout << "There is no lastmove yet!\n";
    }
    cout << "number of moves(should be 7): ";
    cout << tetrisarray1d.numberOfMoves() << endl;
    cout << "Writing the game to the file 'game.txt'\n";
    tetrisarray1d.writeToFile("game.txt");
    cout << "New empty board\n";
    emptytetrisarray1d.draw();
    cout << "\nPress enter to read file 'game.txt' to load empty board.(it should look just like the board above)\n";
    getline(cin,next);
    system("clear");
    emptytetrisarray1d.readfromFile("game.txt");
    cout << "New Board\n";
    emptytetrisarray1d.draw();




    cout << "\nPress enter to continue with TetrisAdapter class's functions.\n";
    getline(cin,next);
    system("clear");


    
    shape = S;
    MuratTetris:: Tetromino t5(shape);
    MuratTetris:: TetrisAdapter<vector<char>> tetrisadapter(11+3,11);
    MuratTetris:: TetrisAdapter<vector<char>> emptytetrisadapter(11+3,11);

    shape = J;
    MuratTetris:: Tetromino t6(shape);
    
    cout << "------- TetrisAdapter class's functions -------\n\n";
    cout << "Draw the board 11x11(draw function):\n";

    tetrisadapter.draw();
    cout << "Overloaded operator += to Add function for 'S' tetromino(default position: at top of the board in the middle):\n";
    
    tetrisadapter+=t5;
    tetrisadapter.draw();

    cout << "Erase the piece from default poisiton :(function i added)\n";
    tetrisadapter.erase(t5);
    tetrisadapter.draw();

    cout << "Testing Last Move Function(there is no move yet.)\n";
    try{
        if(tetrisadapter.lastMove()[0]!=0){
            cout << "           Piece  Rotate Dir.  Rotate Count  Move Dir.  Move Count" << endl;
            cout << "Last Move:   " << tetrisadapter.lastMove() << endl;
        }
        else{
            throw(tetrisadapter.lastMove());
        }
    }
    catch(string lm){
        cout << "There is no lastmove yet please first make a move!\n";
    } 


    cout << "\nPress enter to animate function(terminal will be deleted!)\n";
    cout << "'S' Tetromino will not rotate and move left by 3.\n";
    getline(cin,next);
    system("clear");
    tetrisadapter.animate(t5,"R",0,"L",3);
    cout << "number of moves(should be 3): ";
    cout << tetrisadapter.numberOfMoves() << endl;
    cout << "\nPress enter to continue.\n";
    getline(cin,next);
    system("clear");
    tetrisadapter+=t6;
    tetrisadapter.draw();
    tetrisadapter.erase(t6);
    cout << "'J' Tetromino will rotate left by 4 and move left by 2.\n"; 
    cout << "\nPress enter to continue.\n";
    getline(cin,next);
    system("clear");
    tetrisadapter.animate(t6,"L",4,"L",2);
    cout << "Testing Last Move Function\n";
    try{
        if(tetrisadapter.lastMove()[0]!=0){
            cout << "           Piece  Rotate Dir.  Rotate Count  Move Dir.  Move Count" << endl;
            cout << "Last Move:   " << tetrisadapter.lastMove() << endl;
        }
        else{
            throw(tetrisadapter.lastMove());
        }
    }
    catch(string lm){
        cout << "There is no lastmove yet!\n";
    }
    cout << "number of moves(should be 9): ";
    cout << tetrisadapter.numberOfMoves() << endl;
    cout << "Writing the game to the file 'game.txt'\n";
    tetrisadapter.writeToFile("game.txt");
    cout << "New empty board\n";
    emptytetrisadapter.draw();
    cout << "\nPress enter to read file 'game.txt' to load empty board.(it should look just like the board above)\n";
    getline(cin,next);
    system("clear");
    emptytetrisadapter.readfromFile("game.txt");
    cout << "New Board\n";
    emptytetrisadapter.draw();

    cout << "\nPress enter to continue with Driver2.\n";
    getline(cin,next);
    system("clear");



}