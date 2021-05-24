package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application { //Наследуемся от Application (является средой для работы JavaFX)

    @Override// Метод start абстрактный, требует переопределения
    public void start(Stage primaryStage) throws Exception{ //Оператор trows необходим для перечисления исключений, которые может породить метод start
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));//Создаём объект-загрузчик для javaFX и указываем путь к файлу
        primaryStage.setTitle("Chat");//Передаём название окна
        primaryStage.setScene(new Scene(root, 640, 480));//Устанавливаем исходные размеры окна
//        primaryStage.setResizable(false);//Запрет изменения размеров окна
        primaryStage.show();//делаем окно видимым
    }


    public static void main(String[] args) {
        launch(args);
        //В этом методе вызывается статический метод Application.launch(args).
        // При запуске в метод launch мы можем передать аргументы в виде массива строк.
        // Например, мы можем взять те значения, которые получает метод main через командную строку/терминал.
        // Затем эти аргументы мы можем получить через getParameters, например, в методе start.
        //Затем среда создает объект класса Main, вызывает у него последовательно методы init и start.
        // После вызова в методе start метода stage.show() нам отобразится окно приложения.
        //Закрытие окна приведет к вызову метода stop. И таким образом приложение завершит свое выполнение.
    }


}
