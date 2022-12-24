#include "AbstractTetris.h"
#include <fstream>
#include <string>

using namespace std;
namespace MuratTetris{

int AbstractTetris::getHeight()const{
    return height;
}


int AbstractTetris::getWidth()const{
    return width;
}

int AbstractTetris::getlc()const{
    return lc;
}

int AbstractTetris::getrc()const{
    return rc;
}


void AbstractTetris::setlc(int value){
    lc = value;
}

void AbstractTetris::setrc(int value){
    rc = value;
}

// to determine wheter it is 'I' tetromino and its position is vertical or not
bool AbstractTetris::isI(const Tetromino &piece, const char &direction)const{
    if(direction == 'R'){
        if(piece.getshape() == I && piece.getY()==3 && piece.gettetro(0,0)!=0) return true;
        else return false;
    }
    else if(direction == 'L'){
        if(piece.getshape() == I && piece.getY()==3 && piece.gettetro(0,0)==0) return true;
        else return false;
    }
    else return false;
}


bool AbstractTetris::check_index(const char &direction, const int &count)const{
    if(direction == 'R'){
        if(rc+count >= width) return false;
    }
    else if(direction == 'L'){
        if(lc-count<0) return false;
    }
    return true;
}



int AbstractTetris::twotoone(int y, int x)const{
    int newindex;
    newindex = y * width + x ;
    return newindex;
}

int AbstractTetris::numberOfMoves(){
    return nom;
}

string AbstractTetris::lastMove(){
    return lm;

}

}