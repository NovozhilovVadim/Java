package sample;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.stage.WindowEvent;

import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Controller {
    @FXML //Аннотация, помечающая класс или член как доступный для разметки.
    TextArea chatArea;
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
    @FXML
    ListView<String> clientList;

    Socket socket;//Создаём сокет для подключения
    DataInputStream in;//Создаём обработчик входящего потока
    DataOutputStream out;//Создаём обработчик исходящего потока
    public static final String ADDRESS = "localhost"; //Переменная c адресом подключения
    public static final int PORT = 6003; //Перемення с портом для подключения
    private String userName;//имя клиента
    private boolean isAuthorized;//переменная отслеживающая состояние авторизации (ложно\истино)
    private List<TextArea> textAreas;

    public void setAuthorized(boolean authorized) {//метод авторизации
        this.isAuthorized = authorized;//экземляр переменной клиента
        if (!authorized) {//если не авторизован
            upperPanel.setVisible(true);//панель авторизации видна
            upperPanel.setManaged(true);//панель авторизации активнв
            bottomPanel.setVisible(false);//панель ввода скрыта
            bottomPanel.setManaged(false);//панель ввода не активна
            clientList.setVisible(false);//панель пользователей скрыта
            clientList.setManaged(false);//панель пользователей не активна

        } else {
            upperPanel.setVisible(false);//панель авторизации не видна
            upperPanel.setManaged(false);//панель авторизации не активна
            bottomPanel.setVisible(true);//панель ввода видна
            bottomPanel.setManaged(true);//панель ввода  активна
            clientList.setVisible(true);//панель пользователей не скрыта
            clientList.setManaged(true);//панель пользователей  активна
        }
    }

    @FXML////Аннотация, помечающая класс или член как доступный для разметки.
    void sendMsg() { //Отправка сообщений
        if (textField.getText().equals("/clear")) {//Ловим команду очистки чата
            chatArea.clear();//Очистка чата
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
                            chatArea.clear();//очищаем поле чата
                            break;
                        } else {//если не авторизован, то принимаем сообщение об ошибке и выводим его в окно чата
//                            Date date = new Date();//Выделяет объект Date и инициализирует его, чтобы он представлял время, в которое он был выделен.
//                            SimpleDateFormat timeObj = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");//SimpleDateFormat
                            // - это конкретный класс для форматирования и анализа дат с учетом локали.
                            // Он позволяет выполнять форматирование (дата → текст), синтаксический анализ (текст → дата) и нормализацию.
                            // SimpleDateFormat позволяет начать с выбора любых пользовательских шаблонов для форматирования даты и времени.
                            for (TextArea ta : textAreas) {
                                ta.appendText(str + "\n");
                                //печатаем сообщение об ошибке строку в окно чата
                            }
                        }
                    }

                    while (true) {// Запускам бесконечный цикл
                        String str = in.readUTF();//получаем строку из входящего потока в UTF
                        if ("/serverClosed".equals(str)) {//ловим команду закрытия сервера
                            System.exit(0);//выходим из экземпляра клиента + закрываем окно
                            break;//если сервер закрыт, то выходим из цикла
                        }
                        if ("/clientList".equals(str)){
                            String[] tokens = str.split(" ");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    clientList.getItems().clear();
                                    for (int i = 1; i < tokens.length; i++ ){
                                        clientList.getItems().add(tokens[i]);
                                    }
                                }
                            });
                        }else {
                            chatArea.appendText(str + "\n");//печатаем эту строку в окно чата
                        }
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
            chatArea.appendText("Connection refused\n");//сообщаем об ошибке в чат
        }
    }

    public void disconnect(){
        if (socket != null) {
            if (!socket.isClosed()) {
                try {
                    out.writeUTF("/end");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        System.exit(0);
                    }
                }
            }
        }
    }

    public void selectClient(MouseEvent mouseEvent){

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
            disconnect();
//            try {//моё решение
//                out.writeUTF("/end");//если авторизованы - даём команду окончания сеанса
//            } catch (IOException e) {
//                System.out.println("EXIT");
//            }finally {
//                System.exit(0);//если не авторизованы то просто выходим из приложения
//            }
        }
    };
    public javafx.event.EventHandler<WindowEvent> getCloseEventHandler() {//гетер для слушателя
        return closeEventHandler;
    }

    public void setUserName(String userName) {//Сеттер для юзернэйма
        this.userName = userName;
    }



    @Override
    public void initialize(URL location, ResourceBundle bundle){
        setAuthorized(false);
        textAreas = new ArrayList<>();
        textAreas.add(chatArea);
    }
}


