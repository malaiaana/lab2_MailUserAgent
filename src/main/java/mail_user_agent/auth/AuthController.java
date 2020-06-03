package mail_user_agent.auth;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mail_user_agent.Context;
import mail_user_agent.SceneSetter;

import java.io.IOException;

public class AuthController
{
    public TextField email;
    public TextField username;
    public TextField pass;
    public Button authBtn;

    public void onAuthBtnClicked() throws IOException
    {
        Context.email = email.getText();
        Context.password = pass.getText();
        Context.username = username.getText();

        SceneSetter.setScene("Главная страница", "main");
    }
}
