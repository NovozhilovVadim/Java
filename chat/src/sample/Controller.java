package sample;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.stage.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {
    @FXML //Аннотация, помечающая класс или член как доступный для разметки.
    TextArea textArea;
    @FXML//Аннотация, помечающая класс или член как доступный для разметки.
    TextField textField;
    @FXML//Аннотация, помечающая класс или член как доступный для разметки.
    HBox bottomPanel;
    @FXML//Аннотация, помечающая класс или член как доступный для разметки.
    HBox upperPanel;
    @FXML//Аннотация, помечающая класс или член как доступный для разметки.
    TextField loginField;
    @FXML//Аннотация, помечающая класс или член как доступный для разметки.
    PasswordField passwordField;

    Socket socket;//Создаём сокет для подключения
    DataInputStream in;//Создаём обработчик входящего потока
    DataOutputStream out;//Создаём обработчик исходящего потока
    public static final String ADDRESS = "localhost"; //Переменная c адресом подключения
    public static final int PORT = 6003; //Перемення с портом для подключения
    private String userName;//имя клиента
    private boolean isAuthorized;//переменная отслеживающая состояние авторизации (ложно\истино)

    public void setAuthorized(boolean authorized) {//метод авторизации
        this.isAuthorized = authorized;//экземляр переменной клиента
        if (!authorized) {//если не авторизован
            upperPanel.setVisible(true);//панель авторизации видна
            upperPanel.setManaged(true);//панель авторизации активнв
            bottomPanel.setVisible(false);//панель ввода скрыта
            bottomPanel.setManaged(false);//панель ввода не активна
        } else {
            upperPanel.setVisible(false);//панель авторизации не видна
            upperPanel.setManaged(false);//панель авторизации не активна
            bottomPanel.setVisible(true);//панель ввода видна
            bottomPanel.setManaged(true);//панель ввода  активна
        }
    }

    @FXML////Аннотация, помечающая класс или член как доступный для разметки.
    void sendMsg() { //Отправка сообщений
        if (textField.getText().equals("/clear")) {//Ловим команду очистки чата
            textArea.clear();//Очистка чата
            textField.clear();//Очищаем поле TextField
            textField.requestFocus();//Возвращаем фокус
        } else {
            try {
                out.writeUTF(textField.getText());
                textField.clear();//Очищаем поле TextField
                textField.requestFocus();//Возвращаем фокус
            } catch (IOException e) {
                e.printStackTrace();
            }


//            try { //МОЯ РЕАЛИЗАЦИЯ ОТПРАВКИ НИКНЕЙМА В ЧАТ. ВАРИАНТ С КОСТЫЛЯМИ :))))
//                String str = textField.getText();//Строка из поля ввода
//                if (str.startsWith("/")) {//проверяем наличиу знака обозначающего команду
//                    out.writeUTF(str);//отправляем команду в чат
//                    textField.clear();//Очищаем поле TextField
//                    textField.requestFocus();//возвращаем фокус
//                } else {
//                    out.writeUTF(timeObj.format(date) + " " + this.userName + ": " + textField.getText());//отправляем исходящее собщение в поток
//                    textField.clear();//Очищаем поле TextField
//                    textField.requestFocus();//Запрашивает, чтобы этот узел получил фокус ввода и чтобы его предок верхнего уровня стал окном с фокусом.
//                } // Чтобы иметь право на получение фокуса, узел должен быть частью сцены, он и все его предки должны быть видимыми, и его нельзя отключать.
//                // Если этот узел имеет право, эта функция заставит его стать «владельцем фокуса» этой Сцены.
//                // Каждая сцена имеет не более одного узла владельца фокуса. Однако владелец фокуса фактически не будет иметь фокуса ввода,
//                // если только сцена не принадлежит Stage, которая является видимой и активной.
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    public void connect() {//метод подключения
        try {
            socket = new Socket(ADDRESS, PORT);//определяем сокету адрес и порт
            in = new DataInputStream(socket.getInputStream());//передаём в обработчик входящий поток с сокета
            out = new DataOutputStream(socket.getOutputStream());//передаём в обработчик исходящий поток с сокета
            new Thread(() -> {//Запускаем поток
                try {
                    while (true) {// Запускам бесконечный цикл
                        String str = in.readUTF();//получаем строку из входящего потока в UTF
                        if ("/auth-ok".equals(str)) {//ловим строку авторизации клиента
                            setAuthorized(true);//устанавливаем авторизацию истина
                            textArea.clear();//очищаем поле чата
                            break;
                        } else {//если не авторизован, то принимаем сообщение об ошибке и выводим его в окно чата
                            Date date = new Date();//Выделяет объект Date и инициализирует его, чтобы он представлял время, в которое он был выделен.
                            SimpleDateFormat timeObj = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");//SimpleDateFormat
                            // - это конкретный класс для форматирования и анализа дат с учетом локали.
                            // Он позволяет выполнять форматирование (дата → текст), синтаксический анализ (текст → дата) и нормализацию.
                            // SimpleDateFormat позволяет начать с выбора любых пользовательских шаблонов для форматирования даты и времени.
                            textArea.appendText(timeObj.format(date) + " " + str + "\n");
                            //печатаем сообщение об ошибке строку в окно чата
                        }
                    }
                    while (true) {
                        String str = in.readUTF();//строка из входящего потока
                        if (str.startsWith("/nick")) {//Ловим отправку имени пользователя
                            String[] tokens = str.split(" ");//массив строк из строки через пробел
                            String nick = tokens[1];//присваиваем ник из первого значения массива
                            if (nick != null) {//если ник не null
                                setUserName(nick);//Экземпляр юзернейма получает ник
                                out.writeUTF(" Client " + userName + " entrance Chat");//отправляем сообщение о входе в чат
                            }
                        }
                        textArea.clear();//очищаем текстовое поле
                        break;
                    }
                    while (true) {// Запускам бесконечный цикл
                        String str = in.readUTF();//получаем строку из входящего потока в UTF
                        if ("/serverClosed".equals(str)) {//ловим команду закрытия сервера
                            System.exit(0);//выходим из экземпляра клиента + закрываем окно
                            break;//если сервер закрыт, то выходим из цикла
                        }
                        textArea.appendText(str + "\n");//печатаем эту строку в окно чата
                    }
                } catch (IOException e) {//обрабатываем ошибку ввода
                    e.printStackTrace();
                } finally {//Закрываем сокет (сетевое соединение)
                    try {
                        socket.close();//закрываем сокет
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    setAuthorized(false);//сбрасываем статус авторизации
                }
            }).start();//старт потока
        } catch (IOException e) {
            e.printStackTrace();
            textArea.appendText("Connection refused\n");//сообщаем об ошибке в чат
        }
    }

    public void disconnect(){
        try {
            out.writeUTF("/end");
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                System.exit(0);
            }
        }

    }

    public void tryToAuth(ActionEvent actionEvent) {//попытка авторизации
        if (socket == null || socket.isClosed()) {//проверяем, что мы не подключены к серверу(сокет не существует или закрыт
            connect();//вызываем метод подключения
        }
        try {
            out.writeUTF("/auth " + loginField.getText() + " " + passwordField.getText());//передаём в поток комманду авторизации, логин и пароль
            loginField.clear();//очищаем поле логина
            passwordField.clear();//очищаем поле пароля
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private javafx.event.EventHandler<WindowEvent> closeEventHandler = new javafx.event.EventHandler<WindowEvent>() {//создаём слушателя события
        @Override//переопределяем действие
        public void handle(WindowEvent event) {
//            disconnect();
            try {//моё решение
                out.writeUTF("/end");//если авторизованы - даём команду окончания сеанса
            } catch (IOException e) {
                System.out.println("EXIT");
            }finally {
                System.exit(0);//если не авторизованы то просто выходим из приложения
            }
        }
    };
    public javafx.event.EventHandler<WindowEvent> getCloseEventHandler() {//гетер для слушателя
        return closeEventHandler;
    }

    public void setUserName(String userName) {//Сеттер для юзернэйма
        this.userName = userName;
    }
}


