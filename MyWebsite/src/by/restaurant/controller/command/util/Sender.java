package by.restaurant.controller.command.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.restaurant.controller.command.impl.AddDish;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.service.ServiceException;

import java.util.Properties;

public class Sender {

	private static final Logger logger = LogManager.getLogger(Sender.class);
    
	private String username = "evelina.sarkisyan.1990@mail.ru";
    private String password = "02evelina.sarkisyan02";
    private Properties props;

    public Sender() {

        props = new Properties();
        props.put("mail.smtp.host", "smtp.mail.ru");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
    }

    public void send(String subject, String text, String toEmail) throws ServiceException{
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            //от кого
            message.setFrom(new InternetAddress(username));
            //кому
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            //тема сообщения
            message.setSubject(subject);
            //текст
            message.setText(text);

            //отправляем сообщение
            Transport.send(message);
            
        } catch (MessagingException e) {
			logger.log(Level.ERROR, "Error during sending message to user", e);
			throw new ServiceException();
        }
    }
}
