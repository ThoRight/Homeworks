#include <unistd.h>
#include <iostream>
#include <cstdlib>
#include "Tetromino.h"
using namespace std;
namespace MuratTetris
{

Tetromino::Tetromino(shapes& shape){
	setshape(shape);
	int i,j;
	switch(getshape()){  /* initializing tetramino according to parameter */
	case I:
		y=2;
		x=3;
		tetro = new char*[y];
		for(i=0;i<y;++i){
			tetro[i] = new char[x];
		}
        tetro[0][0] = 0;
        tetro[0][1] = 0;
        tetro[0][2] = 0;
		tetro[1][0] = 'I';
		tetro[1][1] = 'I';
		tetro[1][2] = 'I';
	break;
		case O:
		y=2;
		x=2;
		tetro = new char*[y];
		for(i=0;i<y;++i){
			tetro[i] = new char[x];
		}
		tetro[0][0] = 'O';
		tetro[0][1] = 'O';
		tetro[1][0] = 'O';
		tetro[1][1] = 'O';
		
	break;
		case T:
		y=2;
		x=3;
		tetro = new char*[y];
		for(i=0;i<y;++i){
			tetro[i] = new char[x];
		}
		tetro[0][0] = 'T';
		tetro[0][1] = 'T';
		tetro[0][2] = 'T';
		tetro[1][0] = 0;
		tetro[1][1] = 'T';
		tetro[1][2] = 0;
	break;
		case J:
		y=3;
		x=2;
		tetro = new char*[y];
		for(i=0;i<y;++i){
			tetro[i] = new char[x];
		}
		tetro[0][0] = 0;
		tetro[0][1] = 'J';
		tetro[1][0] = 0;
		tetro[1][1] = 'J';
		tetro[2][0] = 'J';
		tetro[2][1] = 'J';
	break;
		case L:
		y=3;
		x=2;
		tetro = new char*[y];
		for(i=0;i<y;++i){
			tetro[i] = new char[x];
		}
		tetro[0][0] = 'L';
		tetro[0][1] = 0;
		tetro[1][0] = 'L';
		tetro[1][1] = 0;
		tetro[2][0] = 'L';
		tetro[2][1] = 'L';
	break;
		case S:
		y=2;
		x=3;
		tetro = new char*[y];
		for(i=0;i<y;++i){
			tetro[i] = new char[x];
		}
		tetro[0][0] = 0;
		tetro[0][1] = 'S';
		tetro[0][2] = 'S';
		tetro[1][0] = 'S';
		tetro[1][1] = 'S';
		tetro[1][2] = 0;
	break;
		case Z:
		y=2;
		x=3;
		tetro = new char*[y];
		for(i=0;i<y;++i){
			tetro[i] = new char[x];
		}
		tetro[0][0] = 'Z';
		tetro[0][1] = 'Z';
		tetro[0][2] = 0;
		tetro[1][0] = 0;
		tetro[1][1] = 'Z';
		tetro[1][2] = 'Z';
	break;
	}
}

void Tetromino::print() const{
	int i,j;
	for(i=0;i<y;i++){
		for(j=0;j<x;j++){
			if(tetro[i][j]==0) cout<<" ";
			cout << tetro[i][j];
		}
		cout << endl;
	}
	cout << endl;
}

void Tetromino::rotate(const char& dir){ /* These rotating operations work like matrix rotation (not exactly same for some part) */
	if(dir=='L'){ 
		if(x==3){
			this->reverse();
			this->transpose();
		}

		else{
			this->transpose();
			this->verticalreverse();
		}
	}
	else if(dir=='R'){
		if(x==3){
			this->verticalreverse();
			this->transpose();
		}
		else{
			this->transpose();
			this->reverse();
		}
	}
}

void Tetromino::reverse()const{ /*Horizontal*/
	char temp;
	int i=0;
	if(x==3){
		for(i=0;i<2;++i){
			temp = tetro[i][0];
			tetro[i][0] = tetro[i][2];
			tetro[i][2] = temp;
		}
	}
	else if(x==2){
		for(i=0;i<2;++i){
			temp = tetro[i][0];
			tetro[i][0] = tetro[i][1];
			tetro[i][1] = temp;
		}
	}
}

void Tetromino::verticalreverse()const{
	char temp;
	int i;
	if(y==3){ /* we already know that  y=3 => x=2 and vice versa*/
		for (i=0;i<2;++i){
			temp = tetro[0][i];
			tetro[0][i] = tetro[2][i];
			tetro[2][i] = temp;
		}
	}
	else if(y==2){
		for (i=0;i<3;++i){
			temp = tetro[0][i];
			tetro[0][i] = tetro[1][i];
			tetro[1][i] = temp;
		}
	}
}

void Tetromino::transpose(){
	int i,j;
	int r=y;
	int c=x;
	int tempint;
	setX(r);
	setY(c);
	char** temp = new char*[c];
	for(i=0;i<c;++i){
		temp[i] = new char[r];
	}
	if(shapePiece == O);  //intentionally empty Because 'O' Tetromino doesn't have to be rotated
	else{
		for(i=0;i<c;++i){
			for(j=0;j<r;++j){
				temp[i][j] = tetro[j][i];
			}
		}
		for(i=0;i<r;++i){
			delete []tetro[i];
		}
		delete []tetro;
		tetro=temp;
	}
	
}

void Tetromino::setshape(const shapes &s){
    shapePiece = s;
}
shapes Tetromino::getshape()const{
    return shapePiece;
}

bool Tetromino::canFit(const Tetromino& tetro2)const{
    int row1,row2,col1,col2;
    row1=y;
    row2=tetro2.getY();
    col1=x;
    col2=tetro2.getX();
	
	if(tetro[row1-1][col1-1]!=0 && tetro2.gettetro(row2-1,0)!=0) return true;
    else return false;
}

void Tetromino:: setX(const int &value){
	x=value;
}
void Tetromino:: setY(const int &value){
	y=value;
}
int Tetromino:: getX()const{
	return x;
}
int Tetromino:: getY()const{
	return y;
}

char Tetromino:: gettetro(const int &y, const int &x)const{
	return tetro[y][x];
}

Tetromino& Tetromino:: operator=(const Tetromino& oth){
	int i,j;
	if(this->tetro==oth.tetro)
		return *this;
	for(i=0;i<y;++i){
		delete []this->tetro[i];
	}
	delete []this->tetro;
	this->tetro = new char*[oth.getY()];
	for(i=0;i<y;++i){
		this->tetro[i] = new char[oth.getX()];
	}
	this->y= oth.getY();
	this->x= oth.getX();
	for(i=0;i<this->y;++i){
		for(j=0;j<this->x;++j){
			this->tetro[i][j] = oth.tetro[i][j];
		}
	}
	return *this;
}

Tetromino:: ~Tetromino(){
	int i,j;
	for(i=0;i<y;++i){
		delete []tetro[i];
	}
	delete []tetro;
}

}
