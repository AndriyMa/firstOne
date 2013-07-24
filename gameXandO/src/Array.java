import java.util.Random;
import java.util.Scanner;

public class Array {

    private static final int FIELD_SIZE = 4;
    private static final char DEFAULT_ERASE_FIELD = ' ';
    private boolean result = false;
    private boolean twoCharsInLine = false;
    private boolean twoCharsInColumn = false;
    private boolean randomGo= false;
    private char[][] field = new char[FIELD_SIZE][FIELD_SIZE];
    private int freeCells = (FIELD_SIZE - 1)*(FIELD_SIZE - 1);



//**************************BRAINS******************************************
    public void startGame(){
        result = false;
        Scanner in = new Scanner(System.in);

        while (!checkResult()== true){

            System.out.println("Enter coordinate for X");
            int x = Integer.parseInt(in.next());
            System.out.println("Enter coordinate for Y");
            int y = Integer.parseInt(in.next());

            setMove(x, y);
            setPcMove(x, y);
            checkTie();
        }
        System.out.println("GAME OVER");
        startNewGame();

    }

    private boolean checkResult(){

        //Check rows
        for (int i = 1; i<=FIELD_SIZE - 1; i++){
            if (field[i][1] == 'X' && field[i][2] == 'X' && field[i][3] == 'X'){
                System.out.println("Player X won");
                result=true;
            } else if (field[i][1] == 'O' && field[i][2] == 'O' && field[i][3] == 'O'){
                System.out.println("Player O won");
                result=true;
            }
        }

        //Check columns
        for (int j = 1; j<= FIELD_SIZE - 1; j++){
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
        for (int j = 1; j<=FIELD_SIZE - 1; j++){
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
        int temp = FIELD_SIZE-1;
        for (int j = 1; j<=FIELD_SIZE - 1; j++){
            if (field[j][temp] == 'X'){
                countX++;
            } else if (field[j][temp] == 'O'){
                countO++;
            }
            temp--;
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

    private void checkTie (){
        if (freeCells == 1 && !checkResult()== true ){
            System.out.println("It`s TIE");
            showField();
            startNewGame();
        }
    }

    private void setMove(int x, int y){
        if (x >=1 && x <= 3 && y >=1 && y <= 3 && field[x][y] != 'X' && field[x][y] != 'O'){
            setFieldValue(x,y,'X');
        }else
            System.out.println("Wrong coordinates or this cell is already busy");
    }

    private void checkFreeCentralCell(){

        if (field[2][2] != 'X' && field[2][2] != 'O'){
            setFieldValue(2, 2, 'O');
        }
    }

    private void setPcMove(int x, int y) {

        checkFreeCentralCell();
        setOWhenTwoXCharsInLineOrColumn();
        checkTie();
        setRandValue();
        showField();

    }

    private void setFieldValue(int x, int y, char value){
        this.field[x][y] = value;
        freeCells--;
    }

    private void setRandValue(){

       if (randomGo && freeCells%2 == 0){
            System.out.println("free cells " + freeCells);
            int temp = freeCells;

            while (temp==freeCells){

            Random rand = new Random();
            int x = rand.nextInt(FIELD_SIZE - 1) + 1;
            int y = rand.nextInt(FIELD_SIZE - 1) + 1;
            System.out.println("x " + x);
            System.out.println("y " + y);

                if (field[x][y] != 'X' && field[x][y] != 'O'){
                    setFieldValue(x,y,'O');
                }
            }
        }
    }

    private void setOWhenTwoXCharsInLineOrColumn(){

        for (int j = 1; j<=FIELD_SIZE-1; j++){  // perebigajem po linijax

            if (countCharsInLine(j)){           // duvumsja 4u v liniji je 2 X, jakwo je stavum O
                for (int i = 1; i<=FIELD_SIZE - 1; i++){
                    if (field[j][i] != 'X'){
                        setFieldValue(j,i,'O');
                        randomGo = false;
                    }
                }
            }

            // DUVUMSJA NA 2 `X` v COLONCI
            if (countCharsInColumn(j)){           // duvumsja 4u v colonce je 2 X, jakwo je stavum O
                for (int i = 1; i<=FIELD_SIZE - 1; i++){
                    if (field[i][j] != 'X'){
                        setFieldValue(i,j,'O');
                        randomGo = false;
                    }
                }
            }
            //Duvumsja na diagonalj

        }
    }

    private boolean countCharsInLine(int temp){

        twoCharsInLine = false;
        int countX = 0;
        int countBoth = 0;

        for (int i = 1; i<=FIELD_SIZE - 1; i++){
            if (field[temp][i] == 'X'){
                countX++;
            }
            if (field[temp][i] == 'X' || field[temp][i] == 'O' ){
                countBoth++;
            }
        }

        if (countX == 2 && countBoth != 3){
            twoCharsInLine = true;
            randomGo = false;
        }else{
            twoCharsInLine = false;
            randomGo = true;
        }

        return twoCharsInLine;
    }

    private boolean countCharsInColumn(int temp){

        twoCharsInColumn = false;
        int countX = 0;
        int countBoth = 0;

        for (int i = 1; i<=FIELD_SIZE - 1; i++){
            if (field[i][temp] == 'X'){
                countX++;
            }
            if (field[i][temp] == 'X' || field[i][temp] == 'O' ){
                countBoth++;
            }
        }
        if (countX == 2 && countBoth != 3) {
            twoCharsInColumn = true;
            randomGo = false;
        }else{
            twoCharsInColumn = false;
            randomGo = true;
        }

        return twoCharsInColumn ;

    }

    private void startNewGame(){
        Scanner in = new Scanner(System.in);
        String startNewGame;
        do {
            System.out.println("Do you want to start new GAME, type Y or N");
            startNewGame = in.next();
            if (startNewGame.equalsIgnoreCase("y")){
                freeCells = (FIELD_SIZE - 1)*(FIELD_SIZE - 1);
                eraseField();
                startGame();
            }else if (startNewGame.equalsIgnoreCase("n")){
                System.exit(0);
            }else
                System.out.println("Wrong letter, type YES(Y) or NO(N)");

        }
        while(!startNewGame.equalsIgnoreCase("y") || !startNewGame.equalsIgnoreCase("n"));
    }

//**************************END OF BRAINS***********************************
//**************************VIEW********************************************

    private void showCell(int x, int y){
        System.out.print("[" + field[x][y] + "]");
    }

    private void showLine(int lineNumber){
        for (int i = 0; i<FIELD_SIZE; i++){
            showCell(lineNumber, i);
        }
        System.out.println();
    }

    private void showField(){
        fillFrame();
        for(int i = 0; i<FIELD_SIZE; i++){
            showLine(i);
        }
    }

    private void eraseField(){
        for (int i = 1; i< FIELD_SIZE; i++){
            eraseLine(i);
        }
    }

    private void eraseLine(int lineNumber){
        for (int i = 1; i< FIELD_SIZE; i++){
            field[lineNumber][i] = DEFAULT_ERASE_FIELD;
        }
    }

    private void fillFrame(){
        field[0][0] = '+';
        for (int i = 1; i<FIELD_SIZE; i++){
            char a = Character.forDigit(i,FIELD_SIZE);
            field[0][i] = a;
            for (int j = 1; j < FIELD_SIZE; j++){
                char b = Character.forDigit(j,FIELD_SIZE);
                field[j][0] = b;
            }
        }
    }
//**************************END OF VIEW*************************************


}

