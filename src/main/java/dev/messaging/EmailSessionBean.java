//package dev.messaging;
//
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.annotation.Resource;
//import javax.ejb.Stateless;
//import javax.mail.Message;
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//import javax.mail.util.ByteArrayDataSource;
//
//@Stateless
//public class EmailSessionBean {
//
//    @Resource(name = "mail/localsmtp")
//    private Session mailSession;
//
//    public void sendSimpleMail(String emailAddress) {
//
//        Message simpleMail = new MimeMessage(mailSession);
//
//        try {
//            simpleMail.setSubject("Hello World from Java EE!");
//            simpleMail.setRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
//
//            MimeMultipart mailContent = new MimeMultipart();
//
//            MimeBodyPart mailMessage = new MimeBodyPart();
//            mailMessage.setContent(
//                    "<p>Take a look at the <b>scecretMessage.txt</b> file</p>", "text/html; charset=utf-8");
//            mailContent.addBodyPart(mailMessage);
//
//            MimeBodyPart mailAttachment = new MimeBodyPart();
//            DataSource source = new ByteArrayDataSource(
//                    "This is a secret message".getBytes(), "text/plain");
//            mailAttachment.setDataHandler(new DataHandler(source));
//            mailAttachment.setFileName("secretMessage.txt");
//
//            mailContent.addBodyPart(mailAttachment);
//            simpleMail.setContent(mailContent);
//
//            Transport.send(simpleMail);
//
//            System.out.println("Message successfully send to: " + emailAddress);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
