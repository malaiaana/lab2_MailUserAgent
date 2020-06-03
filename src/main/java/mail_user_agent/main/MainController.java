package mail_user_agent.main;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import mail_user_agent.Context;
import mail_user_agent.SceneSetter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    public TabPane tabPane;
    public Tab inboxTab;
    public Tab sendTab;
    public VBox inbox;
    public Button updateBtn;
    public Button sendMsgBtn;
    public TextField recipient;
    public TextField subject;
    public TextArea text;

    public void updateInbox()
    {
        EmailReceiver emailReceiver = new EmailReceiver();
        Context.emailMessages = emailReceiver.downloadInbox();
        System.out.println(Context.emailMessages);
        drawMessages();
    }

    private void drawMessages()
    {
        inbox.getChildren().clear();
        for (final EmailMessage message : Context.emailMessages)
        {
            final Label label = new Label();
            label.setFont(Font.font(22));
            label.setText(message.toString());
            label.setOnMouseEntered(new EventHandler<MouseEvent>()
            {
                public void handle(MouseEvent event)
                {
                    label.setStyle("-fx-text-fill: aquamarine");
                }
            });

            label.setOnMouseExited(new EventHandler<MouseEvent>()
            {
                public void handle(MouseEvent event)
                {
                    label.setStyle(null);
                }
            });

            label.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                public void handle(MouseEvent event)
                {
                    label.setStyle(null);
                    Context.chosenMessageID = message.getId();
                    try
                    {
                        SceneSetter.setScene(message.getSubject(), "open");
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            });

            inbox.getChildren().add(label);
        }
    }

    public void initialize(URL location, ResourceBundle resources)
    {
        drawMessages();
    }

    public void sendMessage()
    {
        EmailSender emailSender = new EmailSender();
        emailSender.send(recipient.getText(), subject.getText(), text.getText());
    }
}
