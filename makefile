all: clear clean compile run
clear:
	@clear
clean:
	@rm -f *.o
	@rm -f test
compile:
	@g++ driver.cpp PFArray.cpp -o test
run:
	@./test