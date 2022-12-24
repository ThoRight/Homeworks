#ifndef TetrisVector_H_
#define TetrisVector_H_
#include <iostream>
#include <cstdlib>
#include <vector>


#include "AbstractTetris.h"

using namespace std;

namespace MuratTetris
{

class TetrisVector: public AbstractTetris{
public:
    TetrisVector(const int &height1, const int &width1);  /* Constructor to initilize the board according to parameters */
    void draw()const;
    void operator+=(const Tetromino& oth);
    void add(const Tetromino &piece, const int &y, const int &x); /* This is in order to add piece to new position in the board after rotating (different from the operator+=)*/
    void animate(Tetromino &piece);
    void animate(Tetromino &piece, string d1, int c1, string d2, int c2);
    void erase(const Tetromino &piece); /* Erase the default position of piece after new location of piece is determined */
    void erase(const Tetromino &piece, const int &start_column); /* Erase the piece by starting from start column */
    void writeToFile(const char* file);
    void readfromFile(const char* file);
private:
    vector<vector<char>> board;
};

}
#endif /* _TetrisVector_H_ */