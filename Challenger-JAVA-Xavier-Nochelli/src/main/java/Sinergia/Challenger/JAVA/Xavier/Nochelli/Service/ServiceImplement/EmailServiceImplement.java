package Sinergia.Challenger.JAVA.Xavier.Nochelli.Service.ServiceImplement;
import Sinergia.Challenger.JAVA.Xavier.Nochelli.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EmailServiceImplement implements EmailService {

    private static final Logger logger = Logger.getLogger(EmailServiceImplement.class.getName());
    @Autowired
    private JavaMailSender emailSender;



    @Override
    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
        logger.log(Level.INFO,"Email enviado a: {0}" , to);
    }
}