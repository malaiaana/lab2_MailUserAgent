package mail_user_agent;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Context.stage = primaryStage;
        primaryStage.setResizable(false);
        SceneSetter.setScene("Авторизация", "login");
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
