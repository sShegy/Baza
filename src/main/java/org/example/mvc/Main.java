package org.example.mvc;



import javafx.application.Application;
import javafx.stage.Stage;
import org.example.mvc.gui.LoginForm;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Отворимо LoginForm као прву форму
        new LoginForm(primaryStage);
    }
}
