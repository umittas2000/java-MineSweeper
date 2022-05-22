import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("==========WELCOME TO THE MINESWEEPER==========");
        String playAgain="n";
        Scanner input = new Scanner(System.in);

        do {
                System.out.print("Play mode[1] or Test Mode[2]");
                int mode = input.nextInt();
                System.out.println("Please Select board size![1-4]");
                System.out.print("Small(3x3) [1] , Medium(5x5) [2], Large(7x7)[3]:, very Large(9x9)[4]: ");
                int board = input.nextInt();
                if(board==1){
                    board=3;
                }else if(board==2){
                    board=5;
                }else if(board==3){
                    board=7;
                }else if(board==4){
                    board=9;
                }else{
                    board=3;
                }

            MineSweeper mineSweeper = new MineSweeper(board,mode);
            mineSweeper.start(mineSweeper);

            System.out.print("Do you want to Play Again?[y/n]: ");
            playAgain=input.nextLine();
            playAgain=input.nextLine();

        }while(playAgain.equalsIgnoreCase("y"));

    }
}
