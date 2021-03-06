package us.colloquy.util;


import org.apache.commons.lang3.StringUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by petergershkovich on 12/30/15.
 */
public class Mailer
{
    public static void sendFeedback(String email, String messageBody)
    {
        final String username = "";
        final String password = "";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username,password);
                    }
                });
        try
        {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(""));
            InternetAddress [] address = new InternetAddress[1] ;

            InternetAddress add = new InternetAddress("peter.gershkovich@yale.edu");
            address[0] = add;

            message.addFrom(address);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(""));

            message.setSubject(StringUtils.abbreviate(messageBody, 30));

            message.setText(messageBody + "\nComment From:" + email);

            Transport.send(message);


        } catch (MessagingException e)
        {
            e.printStackTrace();

            throw new RuntimeException(e);

        }
    }

}
