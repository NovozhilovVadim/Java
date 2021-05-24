package ru.geekbrains;

import java.util.Scanner;

public class Main {
    // 1. Написать программу, которая загадывает случайное число от 0 до 9 и пользователю дается 3 попытки угадать это число.
    // При каждой попытке компьютер должен сообщить, больше ли указанное пользователем число, чем загаданное, или меньше.
    // После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
    // 2. * Создать массив из слов
    // String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic",
    // "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"}.
    // При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя, сравнивает его с загаданным словом и сообщает,
    // правильно ли ответил пользователь. Если слово не угадано, компьютер показывает буквы, которые стоят на своих местах.
    // apple – загаданное
    // apricot - ответ игрока
    // ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
    // Для сравнения двух слов посимвольно можно пользоваться:
    // String str = "apple";
    // char a = str.charAt(0); - метод вернет char, который стоит в слове str на первой позиции
    // Играем до тех пор, пока игрок не отгадает слово.
    // Используем только маленькие буквы.

    public static void main(String[] args) {

        task2();

    }

    public static void task1() {
        Scanner scan = new Scanner(System.in);
        int aiNum = 0;
        int humanNum = 0;
        int repeat = 0;
        String hi = "Компьютер загадал число от 0 до 9. \n Попробуйте его угадать. \n У вас 3 попытки.";
        String outRange = "Значение вне диапозона. \n Попробуйте ещё раз";
        String bigRange = "Ваше число больше";
        String smallRange = "Ваше число меньше";
        String win = "Поздравляю - Вы выиграли!";
        String loose = "Вы проиграли! Попробуйте ещё раз.";
        String repeater = "играть ещё раз? 1 - да, 0 - нет";
        do {
            System.out.println(hi);
            aiNum = (int) (Math.random() * 10);
            for (int i = 0; i < 3; i++){
                humanNum = scan.nextInt();
                if (humanNum < 0 | humanNum > 9) {
                    System.out.println(outRange);
                    humanNum = scan.nextInt();
                }
                if (humanNum != aiNum){
                    if (humanNum < aiNum)
                        System.out.println(smallRange);
                    else System.out.println(bigRange);
                }else {
                    System.out.println(win);
                    break;
                }
            }
            if (aiNum != humanNum)
                System.out.println(loose + " Было число " + aiNum);
            System.out.println();
            System.out.println("играть ещё раз? 1 - да, 0 - нет");
            repeat = scan.nextInt();
        } while (repeat == 1);
    }

    public static void task2(){
        String human;
        String ai;
        String x = "################################";
        char a;
        Scanner scan = new Scanner(System.in);

        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
                "pear", "pepper", "pineapple", "pumpkin", "potato"};
        do {
            int rand = (int) (Math.random() * words.length);
            ai = words[rand];
            System.out.println("Компьютер загадал слово. " + ai.length() + " букв.");
//            System.out.println(ai);//Для проверки

            x = x.substring(0, ai.length());
            System.out.println(x);// Обрезаем X до размера ai
            System.out.println();
            System.out.println("Попробуйте его угадать:");
            human = scan.next();
            human = human.toLowerCase(); //Переводим в нижний регистр
            if (human == "q") break; //Обрабатываем команду выхода
            if (ai.equals(human)){ //Проверяем слово
                System.out.println(" Вы угадали! ");
                break;
            }
            for (int i = 0; i < ai.length(); i++){
                
            }



        }while (false);

    }

}
