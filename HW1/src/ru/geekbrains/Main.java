package ru.geekbrains;

public class Main {
        public static void main(String[] args) {
            byte by;
            short sh;
            int in;
            long lo;
            float fl;
            double dou;
            char ch;
            boolean bo;
            by = 127;
            sh = 32767;
            in = 2147483647;
            lo = 9223372036854775807L;
            fl = 12.35f;
            dou = 12.356;
            ch = 54; //ch = 't'
            bo = true;

            float a, b, c, d;
            a = 1.0f;
            b = 2.0f;
            c = 3.0f;
            d = 4.0f;
            System.out.println( a * (b + (c / d)));

            int a1 = 10;
            int a2 = 20;
            if ((a1 + a2) <= 20) System.out.println(true);
            else System.out.println(false);
            if (a1 >= 0) System.out.println(true);
            else System.out.println(false);

            String st = "Vados";
            System.out.println("Hi" + st);

            for (int i = 1900; i <= 2100; i++){
                if (i%4 == 0 & i%100 != 0 | i%400 == 0)
                    System.out.println(i + " Год високосный");
                else System.out.println(i + " год не високосный");
            }

    }
}
