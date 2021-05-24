package sample;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller{



    @FXML //Аннотация, помечающая класс или член как доступный для разметки.
    TextArea textArea;

    @FXML
    TextField textField;

    @FXML
    void sendMsg(){
        String userName;//Строрковая переменная
        userName= "Vadim";
        Date date = new Date();//Выделяет объект Date и инициализирует его, чтобы он представлял время, в которое он был выделен.
        SimpleDateFormat timeObj = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");//SimpleDateFormat
        // - это конкретный класс для форматирования и анализа дат с учетом локали.
        // Он позволяет выполнять форматирование (дата → текст), синтаксический анализ (текст → дата) и нормализацию.
        // SimpleDateFormat позволяет начать с выбора любых пользовательских шаблонов для форматирования даты и времени.

        if (textField.getText().equals("/clear")){//Очистка чата
            textArea.clear();
            textField.clear();//Очищаем поле TextField
            textField.requestFocus();//Возвращаем фокус
        }else {

            textArea.appendText(" " + timeObj.format(date) + " " + userName + " " + textField.getText() + "\n");//Добавляем в поле TextArea Дату, время, имя пользователя
            // и введёную строку/
            textField.clear();//Очищаем поле TextField
            textField.requestFocus();//Запрашивает, чтобы этот узел получил фокус ввода и чтобы его предок верхнего уровня стал окном с фокусом.
            // Чтобы иметь право на получение фокуса, узел должен быть частью сцены, он и все его предки должны быть видимыми, и его нельзя отключать.
            // Если этот узел имеет право, эта функция заставит его стать «владельцем фокуса» этой Сцены.
            // Каждая сцена имеет не более одного узла владельца фокуса. Однако владелец фокуса фактически не будет иметь фокуса ввода,
            // если только сцена не принадлежит Stage, которая является видимой и активной.
        }
    }






}
