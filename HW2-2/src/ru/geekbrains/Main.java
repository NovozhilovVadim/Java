package ru.geekbrains;

public class Main {

    //1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4,
    // при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
    //2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать.
    // Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа),
    // должно быть брошено исключение MyArrayDataException, с детализацией в какой именно ячейке лежат неверные данные.
    //3. В методе main() вызвать полученный метод,
    // обработать возможные исключения MySizeArrayException и MyArrayDataException, и вывести результат расчета.

    public static void main(String[] args) {

        String[][] arrCorrect = {
                {"1", "2", "3", "4"},
                {"2", "7", "2", "3"},
                {"1", "2", "7", "2"},
                {"2", "2", "7", "2"}
        };

        String[][] arrInCorrect = {
//                {"1", "2", "3"}, //Ошибка размера
                {"1", "2", "3", "4"},
                {"4", "7", "0", "3"},
                {"1", "2", "7", "9"},
                {"3", "2", "7", "4"}
        };

        String[][] arrInCorrect2 = {
                {"2", "2", "2", "A"}, //Ошибка значения
                {"2", "2", "2", "2"},
                {"2", "2", "2", "2"},
                {"2", "2", "2", "2"}
        };

        try {
            try {
                int result = task(arrCorrect);
                System.out.println(result);

                int result2 = task(arrInCorrect);
                System.out.println(result2);

                int result3 = task(arrInCorrect2);
                System.out.println(result3);

            }catch (MyArraySizeException e) {
                    System.out.println(e.toString());
            }
        }catch (MyArrayDataException e) {
                    System.out.println(e.toString());
        }

    }


    public static int task(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        int count = 0;
        if (arr.length != 4) {
            throw new MyArraySizeException();
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != 4) {
                throw new MyArraySizeException();
            }
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    count = count + Integer.parseInt(arr[i][j]);
                }
                catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }

        }
        return count;
    }
}


