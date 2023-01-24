import java.util.Scanner;


public class driver2{
    public static void main(String[] args){
        int boardx=10;
        int boardy=10;
        String next;
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        

        System.out.printf("#######     Driver2     #######\n\n");
        System.out.printf("Terminal will be deleted in driver2 !!\n\n");
        
        
        do{
            System.out.printf("Enter the column number of tetris board: ");
            while (!input1.hasNextInt()) {
                System.out.println("Wrong input!");
                input1.next();
            }
            boardx = input1.nextInt();
            if(boardx <= 3)
                System.out.println("Please enter a number bigger than 3!");
        }while(boardx <= 3);

        
        do{
            System.out.printf("Enter the row number of tetris board: ");
            while (!input1.hasNextInt()) {
                System.out.println("Wrong input!");
                input1.next();
            }
            boardy = input1.nextInt();
            if(boardy <= 3)
                System.out.println("Please enter a number bigger than 3!");
        }while(boardy <= 3);

        System.out.printf("%d %d", boardx, boardy);
        Tetris board = new Tetris(boardy+3,boardx);

        board.play();
    }
}