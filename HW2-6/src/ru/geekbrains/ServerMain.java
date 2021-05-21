package ru.geekbrains;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
//1. Написать консольный вариант клиент\серверного приложения, в котором пользователь может писать сообщения,
// как на клиентской стороне, так и на серверной. Т.е. если на клиентской стороне написать "Привет",
// нажать Enter то сообщение должно передаться на сервер и там отпечататься в консоли.
// Если сделать то же самое на серверной стороне, сообщение соответственно передается клиенту
// и печатается у него в консоли. Есть одна особенность, которую нужно учитывать: клиент или сервер может
// написать несколько сообщений подряд, такую ситуацию необходимо корректно обработать
//Разобраться с кодом с занятия, он является фундаментом проекта-чата
//ВАЖНО! Сервер общается только с одним клиентом, т.е. не нужно запускать цикл, который будет ожидать второго/третьего/n-го клиентов

public class ServerMain {
    public static void main(String[] args) {
        Socket socket = null;
        ServerSocket serverSocket = null;

        try { //пердполагаем возникновение ошибки
//            ServerSocket serverSocket = new ServerSocket(6000);// создаЁм сервер. Слушаем порт 6000
            serverSocket = new ServerSocket(6000); //создаЁм сервер. Слушаем порт 6000
            System.out.println("Server start. Whiting connect... ");
            socket = serverSocket.accept();//Ждём подключения

            Scanner in = new Scanner(socket.getInputStream());// считываем входящий поток с помощью сканера
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);//Добавляем в исходящий поток наш текст
            Scanner console = new Scanner(System.in);// читаем консоль

            System.out.printf("Client [%s] connected", socket.getInetAddress());
            System.out.println();

            Thread t1 = new Thread(()->{ //Создаём отдельный поток (с лямбдой непонятно,но удобно)
               while (true){//Запускаем бесконечный цикл отслеживания событий входящего потока
                   String str = in.nextLine();//Создаём строку из обработанного сканером в in
                   if (str.equals("/end")){//Ловим команду выхода
                       out.println("/end");
                       break;//выходим
                   }
                   System.out.println("Client " + str); //Выводим в консоль сообщение от клиента
               }
            });
            t1.start();//Запускаем поток

            Thread t2 = new Thread(()->{//Создаём отдельный поток
               while (true){//Запускаем бесконечный цикл отправки
                   System.out.println("Enter message: ");
                   String str = console.nextLine(); //Получаем строку из консоли через Scanner console = new Scanner(System.in)
                   System.out.println("Message sent");
                   System.out.println();
                   out.println(str); // Отправляем нашу строку в исходящий поток
               }
            });

            t2.setDaemon(true); //Разрешаем создание потоков демонов
            t2.start();

            try {
                t1.join(); //Заставляем первый поток ждать
            }catch (InterruptedException e){//Исключение: поток прерван другим потоком
                e.printStackTrace();
            }


//            DataInputStream in = new DataInputStream(socket.getInputStream()); //Обработчик входящего потока,
//            // значение получаем из сокета через метод gitInputStream
//            DataOutputStream out = new DataOutputStream(socket.getOutputStream());//Обработчик исходящего потока, значение из socet через getOutputStream

//            while (true){ //Запускаем бесконечный цикл
//                String str = in.readUTF(); // Создаём строку с из потока через обработчик in, считывая её с помощью метода readUTF() (utf - 8)
//                if (str.equals("/end")){ //Ловим команду на выход
//                    break;//выходим из цикла
//                }
//                out.writeUTF(str); //отправляем строку в консоль
//
//            }


        }catch (IOException e){//Обрабатываем возникновение ошибки "Ошибка ввода - вывода"
            e.printStackTrace();
        }finally {
            try {
                socket.close();//Закрываем сокет, чтобы освободить ресурсы, даже если возник сбой в работе приложения
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }


}