#include <unistd.h>
#include <iostream>
#include "Tetromino.h"
#include "Tetris.h"
#include <cstdlib>

using namespace std;

int main(){
    shapes shape1=L, shape2=I, shape3=T;
    MuratTetris:: Tetromino t1(shape1);
    MuratTetris:: Tetromino t2(shape2);
    MuratTetris:: Tetromino t3(shape3);
    
    cout << "#######     Driver1     #######\n";
    cout << "\n\n------- Tetromino class's functions -------\n\n";
    cout << "Print L tetromino(print function):  \n";
    t1.print();
    cout << "Rotate Right(rotate function): \n";
    t1.rotate('R');
    t1.print();
    cout << "Rotate Left(rotate function): \n";
    t1.rotate('L');
    t1.print();

    cout << "Print I tetromino(print function):  \n";
    t2.print();
    cout << "Rotate Right(rotate function): \n";
    t2.rotate('R');
    t2.print();
    cout << "Rotate Left(rotate function): \n";
    t2.rotate('L');
    t2.print();
    cout << "Other tetrominos work same because they have same sizes (2x3 or 3x2).\n";
    
    cout << "canFit function: L for I?(1=TRUE, 0=FALSE)\n";
    cout << t1.canFit(t2) << "\n\n";

    cout << "------- Tetris class's functions -------\n\n";
    cout << "Draw the board 5x5(draw function):\n";
    
    MuratTetris:: Tetris board(5+3,5);

    board.draw();
    cout << "Overloaded operator += to Add function for T tetromino(default position: at top of the board in the middle):\n";

    board+=t3;
    board.draw();

    cout << "Erase the piece from default poisiton :(function ı added)\n";
    board.erase(t3);
    board.draw();

    cout << "Animate function will be already tested on driver 2\n\n";
    return 0;
}