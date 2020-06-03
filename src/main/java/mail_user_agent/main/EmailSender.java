package mail_user_agent.main;

import com.sun.mail.smtp.SMTPTransport;
import mail_user_agent.Context;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class EmailSender
{
    public void send(String recipientEmail, String subject, String body)
    {
        Properties prop = new Properties();
        prop.put("mail.smtps.auth", "true");
        prop.put("mail.smtps.starttls.enable", "true");
        prop.put("mail.smtps.host", "smtp.yandex.ru");
        prop.put("mail.smtps.ssl.trust", "smtp.yandex.ru");
        prop.put("mail.smtps.connectiontimeout", "5000");
        prop.put("mail.smtps.timeout", "5000");

        Session session = Session.getInstance(prop, new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(Context.username, Context.password);
            }
        });

        Message message = new MimeMessage(session);
        try
        {
            message.setFrom(new InternetAddress(Context.email));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setText(body, "UTF-8", "html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);
            SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
            t.connect("smtp.yandex.ru", Context.email, Context.password);
            t.sendMessage(message, message.getAllRecipients());
            System.out.println("Response: " + t.getLastServerResponse());
            t.close();
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }
}
