package com.fikirtepe.app.Service.EmailServiceImplementation;

import com.fikirtepe.app.Model.User;
import com.fikirtepe.app.Service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Component
@Service
public class EmailServiceImplementation implements EmailService {
    private final Logger logger = LoggerFactory.getLogger(EmailServiceImplementation.class);
    private final JavaMailSenderImpl javaMailSender;
    private final String email;
    private final String password;

    //injects javaMailSender for mailing capability
    @Autowired
    public JavaMailSender setJavaMailSender(JavaMailSenderImpl javaMailSender) {
        javaMailSender.setUsername(email);
        javaMailSender.setPassword(password);
        javaMailSender.setJavaMailProperties(getMailProperties());
        return javaMailSender;
    }

    //get email bean with qualifier annotation that is defined in email configuration class
    @Autowired
    public EmailServiceImplementation(JavaMailSenderImpl javaMailSender, @Qualifier("email") String email, @Qualifier("email_password") String password){
        this.javaMailSender = javaMailSender;
        this.email = email;
        this.password = password;
    }

    private Properties getMailProperties(){
        Properties properties = new Properties();
        properties.setProperty("mail.username",email);
        properties.setProperty("mail.password",password);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        return properties;
    }

    @Override
    public void sendRegistrationReceivedMail(User user) throws MailException {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(email);
        msg.setTo(user.getEmail());
        msg.setSubject("Fikirtepe ????renci Sistemi Kay??t");
        msg.setText("Sevgili " + user.getFirstName() + " " + user.getLastName() + "\n\n" +
                "Kayd??n??z ba??ar??yla al??nm????t??r. Kay??t onay?? i??in bilgilendirme yap??lacakt??r. \n\n" +
                "??yi g??nler."
        );
        try {
            javaMailSender.send(msg);
        }catch (MailException ex){
            logger.info(msg.toString());
        }
    }

    @Override
    public void sendRegistrationApprovedMail(User user) throws MailException{
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(email);
        msg.setTo(user.getEmail());
        msg.setSubject("Fikirtepe ????renci Sistemi Kay??t Onay");
        msg.setText("Sevgili " + user.getFirstName() + " " + user.getLastName() + "\n\n" +
                "Kayd??n??z onaylanm????t??r, ??ifrenizi giri?? ekran??ndan olu??turup sisteme giri?? yapabilirsiniz. \n\n" +
                "??yi g??nler."
        );
        try{
            javaMailSender.send(msg);
        }catch(MailException ex){
            logger.info(msg.toString());
        }

    }

    @Override
    public void sendRegistrationRejectedMail(User user) throws MailException{
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(email);
        msg.setTo(user.getEmail());
        msg.setSubject("Fikirtepe ????renci Sistemi Kay??t Red");
        msg.setText("Sevgili " + user.getFirstName() + " " + user.getLastName() + "\n\n" +
                "Kayd??n??z reddedilmi??tir \n\n" + //rejection reason
                "??yi g??nler."
        );
        try{
            javaMailSender.send(msg);
        }catch(MailException ex){
            logger.info(msg.toString());
        }

    }
}
