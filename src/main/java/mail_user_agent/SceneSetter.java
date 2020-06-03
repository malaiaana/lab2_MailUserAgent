package mail_user_agent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class SceneSetter
{
    public static void setScene(String title, String sceneName) throws IOException
    {
        FileInputStream fileInputStream =
                new FileInputStream("src/main/java/mail_user_agent/scenes/" + sceneName + ".fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(fileInputStream);

        Context.root = root;
        Stage stage = Context.stage;
        stage.setTitle(title);
        stage.setScene(new Scene(root, 1600, 800));
        stage.show();
    }
}
