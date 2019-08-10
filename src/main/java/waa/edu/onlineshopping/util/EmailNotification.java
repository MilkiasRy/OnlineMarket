package waa.edu.onlineshopping.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;

public class EmailNotification {

    public static String sendEmail(String email, String name) {
        try{
            sendmail(email, name);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Email sent successfully");

        return "Email sent successfully";
    }


    private static void sendmail(String email, String name) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("erishoppers@gmail.com", "eshoppers@mum");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("erishoppers@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        // msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        msg.setSubject("E-Shoppers Order Confirmation");
        msg.setContent("E-Shoppers email", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        // messageBodyPart.setContent("Tutorials point email", "text/html");
        messageBodyPart.setContent("Hi " + name +" your order will arrive: " + LocalDate.now().plusDays(3), "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        //MimeBodyPart attachPart = new MimeBodyPart();

        // attachPart.attachFile("/var/tmp/image19.png");
        // multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }
}
