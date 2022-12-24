#ifndef TetrisAdapter_H_
#define TetrisAdapter_H_

#include "AbstractTetris.h"
#include <vector>
#include <array>
#include <deque>

namespace MuratTetris{

template <typename T>
class TetrisAdapter: public AbstractTetris{
public:
    TetrisAdapter(int height1, int width1);
    void draw()const;
    void operator+=(const Tetromino& oth);
    void animate(Tetromino &piece);
    void erase(const Tetromino &piece); /* Erase the default position of piece after new location of piece is determined */
    void erase(const Tetromino &piece, const int &start_column); /* Erase the piece by starting from start column */
    void add(const Tetromino &piece, const int &y, const int &x); /* This is in order to add piece to new position in the board after rotating (different from the operator+=)*/
    void writeToFile(const char* file);
    void readfromFile(const char* file);
    void animate(Tetromino &piece, string d1, int c1, string d2, int c2);
private:
    T board;
    int twotoone(int y, int x)const;
};

}

#endif /* TetrisAdapter_H_ */