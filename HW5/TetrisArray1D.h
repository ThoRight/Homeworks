#ifndef _TetrisArray1D_H_
#define _TetrisArray1D_H_
#include <unistd.h>
#include <iostream>
#include <cstdlib>
#include "AbstractTetris.h"
#include "Tetromino.h"
using namespace std;

namespace MuratTetris
{

class TetrisArray1D: public AbstractTetris{
public:
    TetrisArray1D(const int &height1, const int &width1);  /* Constructor to initilize the board according to parameters */
    int getHeight()const;
    int getWidth()const;
    int getlc()const;
    int getrc()const;
    void setlc(const int value);
    void setrc(const int value);
    void draw()const;
    TetrisArray1D& operator+=(const Tetromino& oth);
//  void add(const Tetromino &piece)const; /* Instead of this function , we use overloaded operator+= */
    void add(const Tetromino &piece, const int &y, const int &x)const; /* This is in order to add piece to new position in the board after rotating (different from the operator+=)*/
    void animate(Tetromino &piece);
    bool check_index(const char &direction, const int &count)const; /* to determine wheter index is out of boundry or not */
    bool isI(const Tetromino &piece, const char &direction)const; /* since 'I' tetromino has 2d and its 1d is empty, i wrote special function to determine boundry problems */
    void erase(const Tetromino &piece)const; /* Erase the default position of piece after new location of piece is determined */
    void erase(const Tetromino &piece, const int &start_column)const; /* Erase the piece by starting from start column */
    void play(); /* to take input from user and call the necessary functions to execute program */
    ~TetrisArray1D();

private:
    char** board; /* Double pointer of my board */
    int height; /* Board's height and width */
    int width;
    int lc; /* These keep left and right bottom corner indexes of Tetromino that has been just added */
    int rc;
};

}
#endif /* _TetrisArray2D_H_ */
