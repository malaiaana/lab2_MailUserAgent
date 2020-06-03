package mail_user_agent.main;

import mail_user_agent.Context;

import javax.mail.*;
import java.util.ArrayList;
import java.util.Properties;

public class EmailReceiver
{
    private final ArrayList<EmailMessage> messages = new ArrayList<EmailMessage>();
    private int nextMessageID = 0;

    private Properties createProperties()
    {
        Properties properties = new Properties();

        // server setting
        properties.put(String.format("mail.%s.host", "imap"), "imap.yandex.ru");
        properties.put(String.format("mail.%s.port", "imap"), "993");

        // SSL setting
        properties.setProperty(String.format("mail.%s.socketFactory.class", "imap"), "javax.net.ssl.SSLSocketFactory");
        properties.setProperty(String.format("mail.%s.socketFactory.fallback", "imap"), "false");
        properties.setProperty(String.format("mail.%s.socketFactory.port", "imap"), "993");

        return properties;
    }

    public ArrayList<EmailMessage> downloadInbox()
    {
        Properties properties = createProperties();
        Session session = Session.getDefaultInstance(properties);

        try
        {
            // connects to the message store
            Store store = session.getStore("imap");
            store.connect(Context.email, Context.password);

            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_ONLY);

            // fetches new messages from server
            Message[] messages = folderInbox.getMessages();

            for (int i = 0; i < messages.length; i++)
            {
                Message msg = messages[i];
                Address[] fromAddress = msg.getFrom();
                String from = fromAddress[0].toString();
                String subject = msg.getSubject();

                String contentType = msg.getContentType();
                String messageContent = "";

                if (contentType.contains("text/plain") || contentType.contains("text/html"))
                {
                    try
                    {
                        Object content = msg.getContent();
                        if (content != null)
                        {
                            messageContent = content.toString();
                        }
                    }
                    catch (Exception ex)
                    {
                        messageContent = "[Error downloading content]";
                        ex.printStackTrace();
                    }
                }

                EmailMessage message = new EmailMessage();
                message.setId(nextMessageID++);
                message.setFrom(from);
                message.setSubject(subject);
                if (!messageContent.equals(""))
                {
                    message.setBody(messageContent);
                }
                else
                {
                    message.setBody("Слишком сложно декодировать сообщение");
                }
                this.messages.add(message);

                // print out details of each message
                System.out.println("Message #" + (i + 1) + ":");
                System.out.println("\t From: " + from);
                System.out.println("\t Subject: " + subject);
                System.out.println("\t Message: " + messageContent);
            }

            // disconnect
            folderInbox.close(false);
            store.close();
        }
        catch (NoSuchProviderException ex)
        {
            System.out.println("No provider for protocol: " + "imap");
            ex.printStackTrace();
        }
        catch (MessagingException ex)
        {
            System.out.println("Could not connect to the message store");
            ex.printStackTrace();
        }
        return messages;
    }
}
