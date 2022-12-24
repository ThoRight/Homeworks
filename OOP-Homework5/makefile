all: clear clean compile run
clear:
	@clear
clean:
	@rm -f driver1
	@rm -f driver2
	@rm -f *o
	@rm -f game.txt
compile:
	@g++ driver1.cpp AbstractTetris.cpp TetrisAdapter.cpp TetrisArray1D.cpp TetrisVector.cpp Tetromino.cpp -o driver1
	@g++ driver2.cpp AbstractTetris.cpp TetrisAdapter.cpp TetrisArray1D.cpp TetrisVector.cpp Tetromino.cpp -o driver2
run:
	@./driver1
	@./driver2
