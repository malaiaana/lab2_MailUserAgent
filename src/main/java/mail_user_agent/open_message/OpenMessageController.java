package mail_user_agent.open_message;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import mail_user_agent.Context;
import mail_user_agent.SceneSetter;
import mail_user_agent.main.EmailMessage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OpenMessageController implements Initializable
{
    public VBox message;
    public Button backBtn;

    public void back() throws IOException
    {
        SceneSetter.setScene("Главная страница", "main");
    }

    public void initialize(URL location, ResourceBundle resources)
    {
        EmailMessage message = Context.emailMessages.get(Context.chosenMessageID);
        Label from = new Label();
        from.setFont(Font.font(22));
        from.setText("От: " + message.getFrom());
        this.message.getChildren().add(from);

        Label subject = new Label();
        subject.setFont(Font.font(22));
        subject.setText("Тема: " + message.getSubject());
        this.message.getChildren().add(subject);

        Text text = new Text();
        text.setFont(Font.font(22));
        text.setText("Сообщение: " + message.getBody());
        text.setWrappingWidth(950);
        this.message.getChildren().add(text);
    }
}
