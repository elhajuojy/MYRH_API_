package ma.yc.api.myrhapi.service;

public interface EmailService {
    boolean sendEmail(String to, String subject, String text);
}
