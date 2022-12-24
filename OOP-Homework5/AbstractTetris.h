#ifndef AbstractTetris_H_
#define AbstractTetris_H_
#include "Tetromino.h"


namespace MuratTetris{


class AbstractTetris{
public:
    virtual void draw()const = 0;
    virtual void operator+=(const Tetromino& oth)= 0;
    virtual void animate(Tetromino &piece) = 0;
    virtual void animate(Tetromino &piece, string d1, int c1, string d2, int c2)=0; /* this is for driver1 to make inputs automoatic */
//    virtual void fit() = 0; pdf says don't implement fit function.
    int getHeight()const;
    int getWidth()const;
    int getlc()const;
    int getrc()const;
    void setlc(const int value);
    void setrc(const int value);
    virtual void add(const Tetromino &piece, const int &y, const int &x)=0; /* This is in order to add piece to new position in the board after rotating (different from the operator+=)*/
    bool check_index(const char &direction, const int &count)const; /* to determine wheter index is out of boundry or not */
    bool isI(const Tetromino &piece, const char &direction)const; /* since 'I' tetromino has 2d and its 1d is empty, i wrote special function to determine boundry problems */
    virtual void erase(const Tetromino &piece)=0; /* Erase the default position of piece after new location of piece is determined */
    virtual void erase(const Tetromino &piece, const int &start_column)=0; /* Erase the piece by starting from start column */
    virtual void readfromFile(const char* file)=0;
    virtual void writeToFile(const char* file)=0;
    int numberOfMoves(); /* number of moves board made (both rotates and left and right moves)*/
    string lastMove();
protected:
    int totalsize; /* this is total number of 1d index of 2d board */
    int height;
    int width;
    int lc; /* These keep left and right bottom corner indexes of Tetromino that has been just added */
    int rc;
    int twotoone(int y, int x)const; /* return 1d index according to y,x */
    string lm; /* to keep last move */
    int nom=0; /* number of steps(moves) */
};

}

#endif /* _AbstractTetris_H_ */