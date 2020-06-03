package mail_user_agent.main;

public class EmailMessage
{
    private int id;
    private String from;
    private String subject;
    private String body;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String toString()
    {
        return "От: " + from + "\tТема: " + subject + "\tСообщение: " +
                body.substring(0, Math.min(body.length(), 27));
    }
}
