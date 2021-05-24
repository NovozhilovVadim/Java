package Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class ConsolServer {
    private Vector<ClientHandler> users;//объявляем переменную синхронизированой коллекции


    public ConsolServer() {

        users = new Vector<>();//Создаём экземпляр вектора
        Socket socket = null;//клиент
        ServerSocket server = null;//Наша сторона
        try { //пердполагаем возникновение ошибки
            AuthServise.connect(); //подключаемся к базе данных
            server = new ServerSocket(6003); //создаЁм сервер. Слушаем порт 6001
            if (AuthServise.getNicknameByLoginAndPassword("login1", "pass1") != null){
                System.out.println("Date Base connected/");//проверка конекта
                System.out.println("Server start. Whiting connect... ");
            }
            while (true){//Запускаем бесконечный цикл отслеживания событий входящего потока
                socket = server.accept();//Ждём подключения
                System.out.printf("Client [%s] connected\n", socket.getInetAddress());//Сообщение о подключении клиента -
                // getInetAddress() - возвращает адрес клиента
                new ClientHandler(this, socket);//создаём нового клиента
            }
        }catch (IOException e){//Обрабатываем возникновение ошибки "Ошибка ввода - вывода"
            e.printStackTrace();
        }finally {
            try {
                socket.close();//Закрываем сокет, чтобы освободить ресурсы, даже если возник сбой в работе приложения
            }catch (IOException e){
                e.printStackTrace();
            }
            try {
                server.close();//закрываем сервер
            }catch (IOException e){
                e.printStackTrace();
            }
            AuthServise.disconnect();//отключаемся от БД
        }
    }

    public void subscribe(ClientHandler client){//добовляем клиента в Vector
        users.add(client);
    }

    public boolean verificationNickname(String name){//проверка авторизован клиент или нет
        for (ClientHandler c : users//проходим по всему списку подключённых пользователей
        ) {
            if (name.equals(c.getNickname())){//сравниваем имя нового клиета с уже подключёнными
                return false;
            }
        }return true;
    }

    public void unsubscribe(ClientHandler client){//убираем клиента из Vector'a
        users.remove(client);
    }

    public void broadcastMessage(String str){//создаём метод рассылки всем клиентам
        for (ClientHandler c : users//проходим по всему списку подключённых пользователей
        ) {
            c.sendMsg(str);//каждому отправляем сообщение с помощью sendMsg(str - строка сообщения) из класса ClientHandler
        }
    }

    public void setPrivateMsg(ClientHandler nickFrom, String nickTo, String msg) {
        for (ClientHandler c:users//пробегаем по вектору
             ) {
            if (c.getNickname().equals(nickTo)){//ищем пользователя в списке
                if (!nickFrom.getNickname().equals(nickTo)){//проверяем, что ник отправителя не равен нику получателя
                    c.sendMsg(nickFrom.getNickname() + ": [Send for " + nickTo + "] " + msg);//отправляем сообщение пользователю
                    nickFrom.sendMsg(nickFrom.getNickname() + ": [Send for " + nickTo + "] " + msg);//отправляем себе
                }
            }
        }
    }
}