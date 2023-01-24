class driver1{

    public static void main(String[] args){
        Tetromino t1 = new Tetromino(shapes.L);
        Tetromino t2 = new Tetromino(shapes.I);
        Tetromino t3 = new Tetromino(shapes.T);
    
        System.out.printf("#######     Driver1     #######\n");
        System.out.printf("\n\n------- Tetromino class's functions -------\n\n");
        System.out.printf("Print L tetromino(print function):  \n");
        t1.print();
        System.out.printf("Rotate Right(rotate function): \n");
        t1.rotate('R');
        t1.print();
        System.out.printf("Rotate Left(rotate function): \n");
        t1.rotate('L');
        t1.print();

        System.out.printf("Print I tetromino(print function):  \n");
        t2.print();
        System.out.printf("Rotate Right(rotate function): \n");
        t2.rotate('R');
        t2.print();
        System.out.printf("Rotate Left(rotate function): \n");
        t2.rotate('L');
        t2.print();
        System.out.printf("Other tetrominos work same because they have same sizes (2x3 or 3x2).\n");

        System.out.printf("------- Tetris class's functions -------\n\n");
        System.out.printf("Draw the board 5x5(draw function):\n");
    
        Tetris board = new Tetris(5+3,5);

        board.draw();
        System.out.printf("Add function for T tetromino(default position: at top of the board in the middle):\n");

        board.add(t3);
        board.draw();

        System.out.printf("Erase the piece from default poisiton :(function i added)\n");
        board.erase(t3);
        board.draw();

        System.out.printf("Animate function will be already tested on driver 2\n\n");
    }
}