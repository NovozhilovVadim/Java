package ru.geekbrains;

public class Main {

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();

    }

//    1. Задать целочисленный массив, состоящий из элементов 0 и 1.
//    Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
//    С помощью цикла и условия заменить 0 на 1, 1 на 0;
    public static void task1() {
        int[] a1 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] == 0)
                a1[i] = 1;
            else a1[i] = 0;
            System.out.print(a1[i] + " ");
        }
        System.out.println();
    }

//  2. Задать пустой целочисленный массив размером 8.
//  С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
    public static void task2(){
        int[] a2 = new int[8];
        for (int i = 0; i < a2.length; i++){
            if (i == 0)
                a2[i] = 0;
            else a2[i] = a2[i-1] + 3;
            System.out.print(a2[i] + " ");
        }
        System.out.println();
    }

//    3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом,
//    и числа меньшие 6 умножить на 2;
    public static void task3(){
        int[] a3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < a3.length; i++){
            if (a3[i] < 6)
                a3[i] = a3[i] * 2;
            System.out.print(a3[i] + " ");
        }
        System.out.println();
    }

//    4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое)
//    и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
    public static void task4(){
        int[][] a4 = new int[8][8];
        for (int i = 0; i < a4.length; i++){
            for (int j = 0; j < a4.length; j++);
            a4[i][i] = 1;
        }
        for (int i = 0; i < a4.length; i++){
            for (int j = a4.length - 1; j >= 0; j--){
                a4[j][(a4.length - 1) - j] = 1;
            }
        }
        for (int i = 0; i < a4.length; i++){
            System.out.println();
            for (int j =0; j < a4[i].length; j++){
                System.out.print(a4[i][j] + " ");
            }
        }
        System.out.println();
    }

//  5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы
//  (без помощи интернета);
    public static void task5(){
        int[] a5 = {1, 5, 3, -2, 11, 4, 5, 2, 4, 8, 9, 1};
        int max = 0;
        int min = 0;
        for (int i = 0; i < a5.length; i++){
            if (max - a5[i] <= 0)
                max = a5[i];
            else if (a5[i] < max & a5[i] < min | min ==0 )
                min = a5[i];
            System.out.print(a5[i] + " ");
        }
        System.out.println();
        System.out.println("max " + max);
        System.out.println("min " + min);
        System.out.println();
    }

//    6. ** Написать метод, в который передается не пустой одномерный целочисленный массив,
//    метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
//    Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
//    граница показана символами ||, эти символы в массив не входят.

    public static void task6(){
        int[] a6 = {1, 1, 1, 2, 1};
        boolean result = false;
        for (int i = 0; i < a6.length; i++){
            int sum = 0;
            for (int j = 0; j < i; j++) sum += a6[j];
            for (int j = i; j < a6.length; j++) sum-= a6[j];
            if (sum == 0) result = true;
        }
        System.out.println(result);
        System.out.println();
    }

//   7. **** Написать метод, которому на вход подается одномерный массив
//   и число n (может быть положительным, или отрицательным),
//   при этом метод должен сместить все элементы массива на n позиций.
//   Элементы смещаются циклично. Для усложнения задачи нельзя пользоваться вспомогательными массивами.
//   Примеры: [ 1, 2, 3 ] при n = 1 (на один вправо) -> [ 3, 1, 2 ]; [ 3, 5, 6, 1]
//   при n = -2 (на два влево) -> [ 6, 1, 3, 5 ]. При каком n в какую сторону сдвиг можете выбирать сами.

    public static void task7(){
        int[] a7 = {3, 5, 6, 1};
        int n = 1;
        for (int i = 0; i < a7.length; i++){
            
        }
    }
}
