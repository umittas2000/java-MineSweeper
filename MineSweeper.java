import java.util.Scanner;

public class MineSweeper {
    private int boardSize;
    private int mines;
    private String[][] visibleBoard;
    private String[][] hiddenBoard;
    int lives =1;
    int mode;

    public MineSweeper(int boardSize,int mode){
        this.boardSize=boardSize;
        this.mines=(int)(mines*0.2);
        this.visibleBoard= new String[boardSize][boardSize];
        this.hiddenBoard = new String[boardSize][boardSize];
        this.mode=mode;
    }

    public static void start(MineSweeper a){

        Scanner input = new Scanner(System.in);
        cleanNulls(a);
        placeBombs(a);
        while(a.lives>0){
            printMap(a);
            System.out.print("Enter Row: ");
            int row = input.nextInt();
            System.out.print("Enter Column: ");
            int col = input.nextInt();
            if((row<1 || col<1) || (col>a.boardSize || row>a.boardSize)){
                System.out.println("Out of index, Please Try again!");
            }else {
                checkMap(a, row - 1, col - 1);
            }
        }
        printMap(a);
    }


   /*
    public static void checkQuadrant(MineSweeper a,int x, int y){


        a[x][y]
        a[x][y+1]

    }
    */

    public static void checkMap(MineSweeper a,int x, int y){
        if(a.visibleBoard[x][y].equals("B")){
            a.hiddenBoard[x][y]=a.visibleBoard[x][y];
            a.lives=0; //Game Over
            System.out.println("You hit the Bomb, Game Over...");
        }else{
            int num = Integer.parseInt(a.visibleBoard[x][y]);
            if(num>0) {
                a.hiddenBoard[x][y] = a.visibleBoard[x][y];
            }else if(num==0){
                int rowCount=0;
                int colCount=0;
                //BEGIN Open all zeros around
                do{
                    if((x+rowCount)< a.boardSize && !a.visibleBoard[x+rowCount][y].equalsIgnoreCase("B")) {
                        int num1 = Integer.parseInt(a.visibleBoard[x + rowCount][y]);
                        if (num1 > 0 && a.hiddenBoard[x + rowCount][y].equalsIgnoreCase("*")) {
                            a.hiddenBoard[x + rowCount][y] = a.visibleBoard[x + rowCount][y];
                            rowCount++;
                        } else if (num1 == 0) {
                            a.hiddenBoard[x + rowCount][y] = a.visibleBoard[x + rowCount][y];
                            rowCount++;
                        } else {
                            rowCount = 0;
                        }
                    }else{
                        rowCount=0;
                    }

                    if((y+colCount)< a.boardSize && !a.visibleBoard[x][y+colCount].equalsIgnoreCase("B")) {
                        int num1 = Integer.parseInt(a.visibleBoard[x][y+colCount]);
                        if (num1 > 0 && !a.visibleBoard[x][y + colCount].equalsIgnoreCase("B") && a.hiddenBoard[x][y + colCount].equalsIgnoreCase("*")) {
                            a.hiddenBoard[x][y + colCount] = a.visibleBoard[x][y + colCount];
                            colCount++;
                        } else if (num1 == 0) {
                            a.hiddenBoard[x][y + colCount] = a.visibleBoard[x][y + colCount];
                            colCount++;
                        } else {
                            colCount = 0;
                        }
                    }else{
                        colCount=0;
                    }
                }while(rowCount>0 && colCount>0);
                //END Open all zeros around
            }
        }
    }

    public static void printMap(MineSweeper a){
        String[][] Map = a.hiddenBoard;
        if(a.mode==2){
            Map = a.visibleBoard;
        }


        for(int i=0;i<a.boardSize;i++){
            for(int j=0;j<a.boardSize;j++){
                if(Map[i][j].equalsIgnoreCase("*")){
                    System.out.print(Map[i][j] + " ");
                }else if(Map[i][j].equalsIgnoreCase("B")){
                    //System.out.print(ConsoleColors.RED + a.visibleBoard[i][j] + " " + ConsoleColors.RESET);
                    System.out.print(ConsoleColors.RED + Map[i][j] + " " + ConsoleColors.RESET);
                }else if(Integer.parseInt(Map[i][j])==1) {
                    //System.out.print(ConsoleColors.BLUE + a.visibleBoard[i][j] + " " +  ConsoleColors.RESET);
                    System.out.print(ConsoleColors.BLUE + Map[i][j] + " " +  ConsoleColors.RESET);
                }else if(Integer.parseInt(Map[i][j])==2) {
                    //System.out.print(ConsoleColors.GREEN + a.visibleBoard[i][j] + " " +  ConsoleColors.RESET);
                    System.out.print(ConsoleColors.GREEN + Map[i][j] + " " +  ConsoleColors.RESET);
                }else if(Integer.parseInt(Map[i][j])==3) {
                    //System.out.print(ConsoleColors.PURPLE + a.visibleBoard[i][j] + " " +  ConsoleColors.RESET);
                    System.out.print(ConsoleColors.PURPLE + Map[i][j] + " " +  ConsoleColors.RESET);
                }else if(Integer.parseInt(Map[i][j])==4) {
                    //System.out.print(ConsoleColors.YELLOW_BOLD + a.visibleBoard[i][j] + " " +  ConsoleColors.RESET);
                    System.out.print(ConsoleColors.YELLOW_BOLD + Map[i][j] + " " +  ConsoleColors.RESET);
                }else{
                    //System.out.print(a.visibleBoard[i][j] + " ");
                    System.out.print(Map[i][j] + " ");

                }
            }
            System.out.println( "| " +(i+1));
        }

        for(int i=1;i<=a.boardSize;i++){
            System.out.print("--");
        }
        System.out.println("");
        for(int i=1;i<=a.boardSize;i++){
            System.out.print(i + " ");
        }
        System.out.println("");
    }

    public static void placeBombs(MineSweeper a){
        int x,y;

        for(int i=0;i<a.visibleBoard.length;i++){
            x= (int)(Math.random() * a.boardSize);
            y=(int)(Math.random() * a.boardSize);
           // a.hiddenBoard[x][y]="B";
            a.visibleBoard[x][y]="B";
            int num=0;

            if((x-1)>=0 && !a.visibleBoard[x-1][y].equalsIgnoreCase("B")){
                num = Integer.parseInt(a.visibleBoard[x-1][y]);
                a.visibleBoard[x-1][y]=""+(num+1);
            }

            if((x+1)< a.boardSize && !a.visibleBoard[x+1][y].equalsIgnoreCase("B")){
                num = Integer.parseInt(a.visibleBoard[x+1][y]);
                a.visibleBoard[x+1][y]=""+(num+1);
            }

            if((y-1)>=0 && !a.visibleBoard[x][y-1].equalsIgnoreCase("B")){
                num = Integer.parseInt(a.visibleBoard[x][y-1]);
                a.visibleBoard[x][y-1]=""+(num+1);
            }

            if((y+1)<a.boardSize && !a.visibleBoard[x][y+1].equalsIgnoreCase("B")){
                num = Integer.parseInt(a.visibleBoard[x][y+1]);
                a.visibleBoard[x][y+1]=""+(num+1);
            }

            if((y+1)<a.boardSize && (x+1)<a.boardSize && !a.visibleBoard[x+1][y+1].equalsIgnoreCase("B")){
                num = Integer.parseInt(a.visibleBoard[x+1][y+1]);
                a.visibleBoard[x+1][y+1]=""+(num+1);
            }

            if((y-1)>=0 && (x-1)>=0 && !a.visibleBoard[x-1][y-1].equalsIgnoreCase("B")){
                num = Integer.parseInt(a.visibleBoard[x-1][y-1]);
                a.visibleBoard[x-1][y-1]=""+(num+1);
            }

            if((y+1)<a.boardSize && (x-1)>=0 && !a.visibleBoard[x-1][y+1].equalsIgnoreCase("B")){
                num = Integer.parseInt(a.visibleBoard[x-1][y+1]);
                a.visibleBoard[x-1][y+1]=""+(num+1);
            }

            if((y-1)>=0 && (x+1)<a.boardSize && !a.visibleBoard[x+1][y-1].equalsIgnoreCase("B")){
                num = Integer.parseInt(a.visibleBoard[x+1][y-1]);
                a.visibleBoard[x+1][y-1]=""+(num+1);
            }


        }
    }

    public static void cleanNulls(MineSweeper a){
        int x,y;

        for(int i=0;i<a.boardSize;i++){
            for(int j=0;j<a.boardSize;j++){
                a.hiddenBoard[i][j]="*";
                a.visibleBoard[i][j]="0";
            }
        }
    }
}
