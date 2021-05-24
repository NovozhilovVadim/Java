package ru.geekbrains;
//1. Необходимо написать два метода, которые делают следующее:
//1) Создают одномерный длинный массив, например://
//static final int size = 10000000;
//static final int h = size / 2;
//float[] arr = new float[size];//
//2) Заполняют этот массив единицами;
//3) Засекают время выполнения: long a = System.currentTimeMillis();
//4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
//arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//5) Проверяется время окончания метода System.currentTimeMillis();
//6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);//
//Отличие первого метода от второго:
//Первый просто бежит по массиву и вычисляет значения.
//Второй разбивает массив на то количество потоков, которые будет передано в качестве аргумента для этого метода,
// в отдельных потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.//
//Пример деления одного массива на два://
//System.arraycopy(arr, 0, a1, 0, h);
//System.arraycopy(arr, h, a2, 0, h);//
//Пример обратной склейки://
//System.arraycopy(a1, 0, arr, 0, h);
//System.arraycopy(a2, 0, arr, h, h);//
//Примечание:
//System.arraycopy() – копирует данные из одного массива в другой:
//System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение,
// откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)
//По замерам времени:
//Для первого метода надо считать время только на цикл расчета://
//for (int i = 0; i < size; i++) {
//arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//}//
//Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.
public class Main {
    static final int size = 10000000;
    static final int count = 4;
    static final int h = size / count;


    public static void main(String[] args) {
        float[] arr1 = reFillingArray();
        System.out.println(arr1[1] + " " + arr1[500] + " " + arr1[999999]);
        float[] arr2 = reFiilingArray2();
        System.out.println(arr2[1] + " " + arr2[500] + " " + arr2[999999]);
    }

    public static float[] reFillingArray(){
        float[] arr = new float[size];
        for (int i = 0; i < size; i++){
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++){
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long b = System.currentTimeMillis();
        System.out.println("time 1: " + (b - a));
        return arr;
    }

    public static float[] reFiilingArray2(){
        float[] arr = new float[size];
        for (int i = 0; i < size; i++){ //Есть другое решение, через методы класса масивов. надо смотреть в описании.
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        final float[][] arr1 = new float[count][h]; // двухуровневый массив, где количество ячеек первого уровня равно количеству потоков,
        // а количество ячеек второго уровня равно размеру изночального архива разделённого на количество потоков.
        Thread[] t = new Thread[count]; // массив потоков, количество ячеек\потоков задаётся в count.
        for (int i = 0; i < count; i++){
            System.arraycopy(arr,h*i, arr1[i], 0, h);
            final int u = i;
             t[i] = new Thread(() ->{ //t[i] один из потоков количество которых равно переменной count .
                 // (не очень понял с лямбдамиб, пока буду считать короткой записью по умолчанию)
                for (int j = 0; j < h; j++) {
                    arr1[u][j] = (float) (arr1[u][j]
                            * Math.sin(0.2f + (j + h * u) / 5) // для arr1[поток 0][ячейка 0] замена (0.2f + i / 5) на (0.2f + (j + h * u) / 5),
                            // где при первом проходе i = (j=0 + h = размер архива * u = номер потока (для первой итерации 0)
                            // (если забыть про мат логику, то получается другое значение для всех ячеек после 1)
                            * Math.cos(0.2f + (j + h * u) / 5)
                            * Math.cos(0.4f + (j + h * u) / 2));
                }
            });
            t[i].start(); //Запустили поток с номером равным i
        }
        try {
            for (int i = 0; i < count; i++) {
                t[i].join();// попросили подождать поток с номером i
            }
        } catch (InterruptedException e) { //обработали исключение, чтобы не было ошибки
            e.printStackTrace();
        }
        for (int i = 0; i < count; i++) {
            System.arraycopy(arr1[i], 0, arr, i * h, h); // склеили обратно
        }
        long b = System.currentTimeMillis();
        System.out.println("time 2: " + (b - a));
        return arr;
    }
}







