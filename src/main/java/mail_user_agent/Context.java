package mail_user_agent;

import javafx.scene.Parent;
import javafx.stage.Stage;
import mail_user_agent.main.EmailMessage;

import java.util.ArrayList;

public class Context
{
    public static Parent root;
    public static Stage stage;
    public static String username;
    public static String email;
    public static String password;
    public static ArrayList<EmailMessage> emailMessages = new ArrayList<EmailMessage>();
    public static int chosenMessageID;
}
