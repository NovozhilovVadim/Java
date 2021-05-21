package ru.geekbrains;



import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

//То же самое, что и с сервером, только не запускаем сам сервер, вместо этого подключаемся к локальному серверу.

public class ClientMain {
    public static void main(String[] args) {
        Socket socket = null;

        try {
            socket = new Socket("localhost", 6000);//Создаем сокет для подключения и подключаемся к локальному серверу
            Scanner in = new Scanner(socket.getInputStream());// считываем входящий поток с помощью сканера
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);//Добавляем в исходящий поток наш текст
            Scanner console = new Scanner(System.in);// читаем консоль

            System.out.printf("Connected [%s] server", socket.getInetAddress());
            System.out.println();

            Thread t1 = new Thread(()->{ //Создаём отдельный поток (с лямбдой непонятно,но удобно)
                while (true){//Запускаем бесконечный цикл
                    String str = in.nextLine();//Создаём строку из обработанного сканером в in
                    if (str.equals("/end")){//Ловим команду выхода
                        out.println("/end");
                        break;//Прерываем цикл
                    }
                    System.out.println("Server " + str); //Выводим в консоль сообщение от клиента
                }
            });
            t1.start();//Запускаем поток

            Thread t2 = new Thread(()->{
                while (true){
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


        }catch (IOException e){
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