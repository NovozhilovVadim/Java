package ru.geekbrains;
//1. Полностью разобраться с кодом, попробовать переписать с нуля, стараясь не подглядывать в методичку.
//2. Переделать проверку победы, чтобы она не была реализована просто набором условий, например, с использованием циклов.
//3. * Попробовать переписать логику проверки победы, чтобы она работала для поля 5х5 и количества фишек 4.
//     Очень желательно не делать это просто набором условий для каждой из возможных ситуаций;
//4. *** Доработать искусственный интеллект, чтобы он мог блокировать ходы игрока.

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static char[][] field;
    public static int size;
    public static int dotWin = size;
    public static final char dotEmpty = '•';
    public static final char dotX = 'X';
    public static final char dotO = 'O';
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();


    public static void main(String[] args) {
        System.out.println("Please, enter size field: ");
        size = sc.nextInt();
       start();

    }

    public static void start(){
        initField();
        drawField();
        while (true) {
            manTurn();
            drawField();
            if (checkWin(dotX)) {
                System.out.println("Human win");
                break;
            }
            if (fullField()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            drawField();
            if (checkWin(dotO)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (fullField()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    public static void initField(){
        field = new char[size][size];
        for (int i = 0; i < field.length; i++){
            for (int j = 0; j < field.length; j++)
                field[i][j] = dotEmpty;
        }
    }

    public static void drawField(){
        for (int i = 0; i <= field.length; i++){
            System.out.print(i + "  ");
        }
        System.out.println();
        for (int i = 0; i < field.length; i++){
            System.out.print((i + 1) + "  ");
            for (int j = 0; j < field.length; j++){
                System.out.print(field[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void manTurn(){
        int x, y;
        do {
            System.out.println("Enter coordinate to format X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        field[x][y] = dotX;
    }

    public static boolean isCellValid(int x, int y){
        if (x < 0 || x > size || y < 0 || y > size) return false;
        if (field[x][y] == dotEmpty) return true;
        return false;
    }

    public static void aiTurn(){
        int x, y;
        do {
            x = rand.nextInt(size);
            y = rand.nextInt(size);
        } while (!isCellValid(x, y));
        System.out.println("AI tuned " + (x + 1) + " " + (y + 1));
        field[x][y] = dotO;
    }

    public static boolean checkWin(char symb){
        boolean hor = true;
        boolean ver = true;
        boolean di1 = true;
        boolean di2 = true;


        for (int i = 0; i < field.length; i++){
            hor = true;
            for (int j = 0; j < field.length; j++){
                hor = hor & (field[i][j] == symb);
            }
            if (hor){
                return true;
            }
        }

        for (int i = 0; i < field.length; i++){
            ver = true;
            for (int j = 0; j < field.length; j++){
                ver = ver & (field[j][i] == symb);
            }
            if (ver){
                return true;
            }
        }

        for (int i = 0; i < field.length; i++){
            di1 =  di1 & (field[i][i] == symb);
        }
        if (di1){
            return true;
        }

        for (int i = 0; i < field.length; i++){
            di2 = di2 &  (field[field.length - i - 1][i] == symb);
        }
        if (di2){
            return true;
        }

        if (hor || ver || di1 || di2)
            return true;

        return false;
    }

    public static boolean fullField(){
        for (int i = 0; i < field.length; i++){
            for (int j = 0; j < field.length; j++){
                if (field[i][j] == dotEmpty) return false;
            }
        }
        return true;
    }
    
}
