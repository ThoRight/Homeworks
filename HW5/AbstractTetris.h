#ifndef _AbstractTetris_H_
#define _AbstractTetris_H_

namespace MuratTetris{


class AbstractTetris{
public:
    virtual void draw()const = 0;
    virtual void play() = 0;
    virtual TetrisArray1D& operator+=(const Tetromino& oth) = 0;
    virtual TetrisVector& operator+=(const Tetromino& oth) = 0;
    virtual void animate(Tetromino &piece);
    int deneme;
};

}

#endif /* _AbstractTetris_H_ */