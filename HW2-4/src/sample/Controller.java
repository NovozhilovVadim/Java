package sample;

import com.sun.org.apache.xalan.internal.lib.ExsltDatetime;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.TextFormatter;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller{



    @FXML
    TextArea textArea;

    @FXML
    TextField textField;

    @FXML
    void sendMsg(){
        String userName = "Vadim";
        Date date = new Date();
        SimpleDateFormat timeObj = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        String timeStr =  timeObj.format(date);
        Text time = new Text("UYTRRE");
        time.setFill(Color.RED);
        TextFlow textFlow = new TextFlow(time);








//        textArea.setStyle("-fx-text-fill: RED;");

        textArea.appendText(" " + timeObj.format(date) + " " + textFlow + " " );
//        textArea.setStyle("-fx-highlight-fill: lightgray; -fx-highlight-text-fill: firebrick; -fx-font-size: 12px;");
        textArea.selectRange(2, 9);
//        textArea.setStyle("-fx-text-fill: black;");
        textArea.appendText(textField.getText() + "\n");

        textField.clear();
        textField.requestFocus();
    }






}
