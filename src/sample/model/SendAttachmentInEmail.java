package sample.model;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class SendAttachmentInEmail {
    private static final String SERVIDOR_SMTP = "smtp.office365.com";
    private static final int PORTA_SERVIDOR_SMTP = 587;
    private static final String CONTA_PADRAO = "hotelsystemsender@outlook.com";
    private static final String SENHA_CONTA_PADRAO = "hotel12345!!!";

    private final String from = "hotelsystemsender@outlook.com";


    private final String subject = "Hotel System";


    public void sendEmail(String tO  , String content) {

        final Session session = Session.getInstance(this.getEmailProperties(), new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(CONTA_PADRAO, SENHA_CONTA_PADRAO);
            }

        });

        try {
            final Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(tO));
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            message.setText(content);
            message.setSentDate(new Date());
            Transport.send(message);
        } catch (final MessagingException ex) {
            System.out.println(" " + ex);
        }
    }

    public Properties getEmailProperties() {
        final Properties config = new Properties();
        config.put("mail.smtp.auth", "true");
        config.put("mail.smtp.starttls.enable", "true");
        config.put("mail.smtp.host", SERVIDOR_SMTP);
        config.put("mail.smtp.port", PORTA_SERVIDOR_SMTP);
        return config;
    }
}
