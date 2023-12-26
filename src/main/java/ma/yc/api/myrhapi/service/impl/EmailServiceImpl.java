package ma.yc.api.myrhapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ma.yc.api.myrhapi.service.EmailService;
import org.slf4j.Logger;

@Service
public class EmailServiceImpl implements EmailService {

   Logger logger = org.slf4j.LoggerFactory.getLogger(EmailServiceImpl.class);

    @Value("${spring.mail.username}")
    String sourceEmail ;
    @Value("${spring.mail.password}")
    String sourcePassword ;
    @Value("${spring.mail.port}")
    String port ;
    @Autowired
    private final JavaMailSender mailSender;


    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }





    @Override
    public boolean sendEmail(String to, String subject, String text) {
        logger.info("sending email to {}",to);
        logger.info("sending email from {}",sourceEmail);
        logger.info("sending in port {}",port);


        try{
            //TODO: NOT WORKING YET BECAUSE OF GMAIL SECURITY LESS SECURE APPS BLOCKED
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sourceEmail);
            message.setTo(sourceEmail);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
        }catch (Exception e){
            logger.error("error while sending email",e);
            return false;
        }
        return true;
    }
}
