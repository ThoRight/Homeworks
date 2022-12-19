#ifndef _TetrisVector_H_
#define _TetrisVector_H_
#include <unistd.h>
#include <iostream>
#include <cstdlib>
#include <vector>
#include "Tetromino.h"
#include "AbstractTetris.h"

using namespace std;

namespace MuratTetris
{

class TetrisVector: public AbstractTetris{
public:
    TetrisVector(const int &height1, const int &width1);  /* Constructor to initilize the board according to parameters */
    int getHeight()const;
    int getWidth()const;
    int getlc()const;
    int getrc()const;
    void setlc(const int value);
    void setrc(const int value);
    void draw()const;
    TetrisVector& operator+=(const Tetromino& oth);
//  void add(const Tetromino &piece)const; /* Instead of this function , we use overloaded operator+= */
    void add(const Tetromino &piece, const int &y, const int &x); /* This is in order to add piece to new position in the board after rotating (different from the operator+=)*/
    void animate(Tetromino &piece);
    bool check_index(const char &direction, const int &count)const; /* to determine wheter index is out of boundry or not */
    bool isI(const Tetromino &piece, const char &direction)const; /* since 'I' tetromino has 2d and its 1d is empty, i wrote special function to determine boundry problems */
    void erase(const Tetromino &piece); /* Erase the default position of piece after new location of piece is determined */
    void erase(const Tetromino &piece, const int &start_column); /* Erase the piece by starting from start column */
    void play(); /* to take input from user and call the necessary functions to execute program */

private:
    vector<vector<char>> board;
    int height; /* Board's height and width */
    int width;
    int lc; /* These keep left and right bottom corner indexes of Tetromino that has been just added */
    int rc;
};

}
#endif /* _TetrisVector_H_ */