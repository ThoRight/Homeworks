#ifndef Tetromino_H_
#define Tetromino_H_
#include <iostream>
#include <cstdlib>


using namespace std;
enum shapes{I=73,O=79,T=84,J=74,L=76,S=83,Z=90};

namespace MuratTetris
{

class Tetromino{
public:
	Tetromino(shapes& shape); /* Constructor */
	void print() const; /* to print tetromino objects */
	void rotate(const char& dir);
	
	void reverse()const; /* These 3 functions are being used in rotate function */
	void verticalreverse()const;
	void transpose();
	
	Tetromino& operator=(const Tetromino& oth);
	void setshape(const shapes &s);
	shapes getshape()const;
	bool canFit(const Tetromino &tetro2)const; /* This function takes Tetromino object as a parameter to determine the position according to main object */
	void setX(const int &value);
	void setY(const int &value);
	int getX()const;
	int getY()const;
	char gettetro(const int &y, const int &x)const; /* getter for private tetro variable */
	~Tetromino();

private:
	char** tetro; // 2d tetromino
	shapes shapePiece; // type of tetromino
	int x; // number of column of tetromino
	int y; // number of row  of tetromino
};

}


#endif /* _Tetromino_H_ */
