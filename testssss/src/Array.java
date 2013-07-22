import java.util.Scanner;

public class Array {

    private static final int FIELD_SIZE     = 4;
    private static final char DEFAULT_ERASE_FIELD = ' ';
    boolean result = false;
    private char[][] field = new char[FIELD_SIZE][FIELD_SIZE];

//**************************BRAINS******************************************
    private boolean checkResult(){

        //Check rows
        for (int i = 1; i<FIELD_SIZE; i++){
            if (field[i][1] == 'X' && field[i][2] == 'X' && field[i][3] == 'X'){
                System.out.println("Player X won");
                result=true;
            } else if (field[i][1] == 'O' && field[i][2] == 'O' && field[i][3] == 'O'){
                System.out.println("Player O won");
                result=true;
            }
        }
        //Check columns
        for (int j = 1; j<FIELD_SIZE; j++){
            if (field[1][j] == 'X' && field[2][j] == 'X' && field[3][j] == 'X'){
                System.out.println("Player X won");
                result=true;
            } else if (field[1][j] == 'O' && field[2][j] == 'O' && field[3][j] == 'O'){
                System.out.println("Player O won");
                result=true;
            }
        }
        //Check diagonals
        //first diagonal
        int countX = 0;
        int countO = 0;
        for (int j = 1; j<FIELD_SIZE; j++){
            if (field[j][j] == 'X'){
                countX++;
            } else if (field[j][j] == 'O'){
                countO++;
            }
        }
        if(countX == 3){
            System.out.println("player X won ");
            result=true;
        }else if (countO == 3){
            System.out.println("player O won ");
            result=true;
        }else {
            countX = 0;
            countO = 0;
        }
        //second diagonal

        for (int j = 1; j<FIELD_SIZE; j++){
            int temp = FIELD_SIZE;
            if (field[j][--temp] == 'X'){
                countX++;
            } else if (field[j][--temp] == 'O'){
                countO++;
            }
        }
        if(countX == 3){
            System.out.println("player X won ");
            result=true;
        }else if (countO == 3){
            System.out.println("player O won ");
            result=true;
        }

        return result;
    }



    public void startGame(){
        result = false;
        Scanner in = new Scanner(System.in);
        while (!checkResult()== true){
            System.out.println("Enter coordinate for X");
            int x = Integer.parseInt(in.next());
            System.out.println("Enter coordinate for Y");
            int y = Integer.parseInt(in.next());
            setMove(x, y);
            setPCmove(x,y);

        }
        System.out.println("Game over, do you want to start new GAME, type Y or N");
        String startNewGame = in.next();
        startNewGame(startNewGame);

    }

    private void setMove(int x, int y){
        if (x >=1 && x <= 3 && y >=1 && y <= 3 ){
            field[x][y] = 'X';
        }else
            System.out.println("Wrong coordinates, should be in range 1 - 3");

    }
    private void setPCmove(int x, int y) {

        if (field[2][2] != 'X' && field[2][2] != ' '){
            System.out.println("a" + field[2][2] + "aAsdasdasd" );
            field[2][2] = 'O';
        }/*else if (field[x][y+1] != 'X' && (x+1) <=FIELD_SIZE){
          field[x][y + 1] = 'O';
        }else if (field[x][y+1] != 'X' && (y+1) <=FIELD_SIZE){
           field[x+1][y] = 'O';
        }   else
            System.exit(0);*/

        showField();



    }

    private void startNewGame(String a){
        if(a.equalsIgnoreCase("y")){
            eraseField();
            startGame();
        }else if (a.equalsIgnoreCase("n")){
            System.exit(0);
        }else
            System.out.println("Wrong letter, type YES(Y) or NO(N)");
    }

//**************************END OF BRAINS***********************************




//**************************VIEW********************************************

    public void showCell(int x, int y){
        System.out.print("[" + field[x][y] + "]");
    }

    public void showLine(int lineNumber){
        for (int i = 0; i<FIELD_SIZE; i++){
            showCell(lineNumber, i);
        }
        System.out.println();
    }

    public void showField(){
        fillFrame();
        for(int i = 0; i<FIELD_SIZE; i++){
            showLine(i);
        }

    }

    public void eraseField(){
        for (int i = 1; i< FIELD_SIZE; i++){
            eraseLine(i);
        }

    }

    public void eraseLine(int lineNumber){
        for (int i = 1; i< FIELD_SIZE; i++){
            field[lineNumber][i] = DEFAULT_ERASE_FIELD;
        }

    }

    public void fillFrame(){
        field[0][0] = '+';
        field[0][1] = '1';
        field[0][2] = '2';
        field[0][3] = '3';
        field[1][0] = '1';
        field[2][0] = '2';
        field[3][0] = '3';
    }
//**************************END OF VIEW*************************************


}
