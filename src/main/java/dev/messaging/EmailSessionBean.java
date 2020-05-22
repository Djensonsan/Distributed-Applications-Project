package dev.messaging;

import dev.entities.CustomerEntity;
import dev.entities.OrderEntity;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Stateless
public class EmailSessionBean {

    @Resource(name = "mail/localsmtp")
    private Session mailSession;

    public void sendSimpleMail(CustomerEntity customer, OrderEntity order) {
        Message simpleMail = new MimeMessage(mailSession);
        try {
            simpleMail.setSubject("Order Confirmation for Order: "+order.getOrderId());
            simpleMail.setRecipient(Message.RecipientType.TO, new InternetAddress(customer.getPerson().getEmail()));

            MimeMultipart mailContent = new MimeMultipart();
            MimeBodyPart mailMessage = new MimeBodyPart();

            mailMessage.setContent(
                    "Dear "+customer.getPerson().getFirstname()+", thank you for using Grocy! <br /> <br />  We have received your order and will start working on it soon. <br /> Your order will be delivered to: "+order.getAddress().getAddress1()+", "+order
                            .getAddress().getAddress2()+", "+order.getAddress().getCity()+"<br /> You will receive your order before: "+order.getRequiredDateEnd()+"<br /> <br />  For any updates on your order, please visit our website Grocy.be. <br /> <br /> Have a nice day! <br /> - Grocy Team", "text/html; charset=utf-8");

            mailContent.addBodyPart(mailMessage);
            simpleMail.setContent(mailContent);

            Transport.send(simpleMail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
