package ma.yc.api.myrhapi.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.awt.font.TextHitInfo;

@Service()
//@Qualifier("native")
@Primary
public class NativeEmailService implements EmailService{


    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sourceEmail;

    Logger logger = org.slf4j.LoggerFactory.getLogger(NativeEmailService.class);
    public NativeEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public boolean sendEmail(String to, String subject, String text) throws RuntimeException {
//        this.javaMailSender.
        logger.info("sending email to {}",to);
        logger.info("sending email from {}",sourceEmail);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();


//        simpleMailMessage.setTo(to);
        simpleMailMessage.setTo("souirimehdi311@gmail.com");
        simpleMailMessage.setFrom(this.sourceEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);


        javaMailSender.send(simpleMailMessage);

        return true;
    }
}
