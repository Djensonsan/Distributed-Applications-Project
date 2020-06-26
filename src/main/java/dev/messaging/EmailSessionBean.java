package dev.messaging;

import dev.entities.CustomerEntity;
import dev.entities.OrderEntity;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.interceptor.AroundTimeout;
import javax.interceptor.InvocationContext;
import javax.mail.Message;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Calendar;
import java.util.ArrayList;

@Stateless
public class EmailSessionBean {

    @Resource(name = "mail/localsmtp")
    private Session mailSession;

    @Resource
    TimerService timerService;

    public void sendSimpleMail(CustomerEntity customer, OrderEntity order) {
        createOrderTimer(customer, order);
        Message simpleMail = new MimeMessage(mailSession);
        try {
            simpleMail.setSubject("Order Confirmation for Order: "+order.getOrderId());
            simpleMail.setRecipient(Message.RecipientType.TO, new InternetAddress(customer.getPerson().getEmail()));

            MimeMultipart mailContent = new MimeMultipart();
            MimeBodyPart mailMessage = new MimeBodyPart();

            mailMessage.setContent(
                    "Dear "+customer.getPerson().getFirstname()+", thank you for using Grocy! <br /> <br />  We have received your order and will start working on it soon. <br /> Your order will be delivered to: "+order.getAddress().getAddress1()+", "+order
                            .getAddress().getAddress2()+", "+order.getAddress().getCity()+"<br /> You will receive your order before: "+order.getRequiredDateEnd()+"<br /> <br />  For any updates on your order, please visit our website Grocy.be. <br /> <br /> Have a nice day! <br /> - The Grocy Team", "text/html; charset=utf-8");

            mailContent.addBodyPart(mailMessage);
            simpleMail.setContent(mailContent);

            Transport.send(simpleMail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Note: The idea would be to send a mail two hours before the delivery end date.
    public void createOrderTimer(CustomerEntity customer, OrderEntity order) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(order.getRequiredDateEnd());

        int orderMonth = calendar.get(Calendar.MONTH)+1;
        int orderDay = calendar.get(Calendar.DAY_OF_MONTH);
        int orderHour = calendar.get(Calendar.HOUR_OF_DAY);
        int orderMinutes = calendar.get(Calendar.MINUTE)+2; // this would be gone

        ScheduleExpression twoHoursToOrder = new ScheduleExpression().dayOfMonth(orderDay).month(orderMonth).hour(orderHour).minute(orderMinutes);

        ArrayList <Object> timerInfo = new ArrayList<>();
        timerInfo.add(customer);
        timerInfo.add(order);

        timerService.createCalendarTimer(twoHoursToOrder, new TimerConfig(timerInfo, false));
    }

    @Timeout
    public void reminderMail(Timer timer) {
        ArrayList <Object> timerInfo = (ArrayList<Object>) timer.getInfo();
        CustomerEntity customer = (CustomerEntity) timerInfo.get(0);
        OrderEntity order = (OrderEntity) timerInfo.get(1);

        Message simpleMail = new MimeMessage(mailSession);
        try {
            simpleMail.setSubject("Reminder for Order: "+order.getOrderId());
            simpleMail.setRecipient(Message.RecipientType.TO, new InternetAddress(customer.getPerson().getEmail()));

            MimeMultipart mailContent = new MimeMultipart();
            MimeBodyPart mailMessage = new MimeBodyPart();

            mailMessage.setContent(
                    "Dear "+customer.getPerson().getFirstname()+", this is a reminder that your order will be delivered soon! <br /> <br /> Your order will be delivered to: "+order.getAddress().getAddress1()+", "+order
                            .getAddress().getAddress2()+", "+order.getAddress().getCity()+"<br /> You will receive your order before: "+order.getRequiredDateEnd()+"<br /> <br />  For any updates on your order, please visit our website Grocy.be. <br /> <br /> Have a nice day! <br /> - The Grocy Team", "text/html; charset=utf-8");

            mailContent.addBodyPart(mailMessage);
            simpleMail.setContent(mailContent);

            Transport.send(simpleMail);
        } catch (MessagingException e)  {
            e.printStackTrace();
        }
    }

    @AroundTimeout
    public Object logTimer(InvocationContext ic) throws Exception {
        Timer timer = (Timer) ic.getTimer();
        ArrayList <Object> timerInfo = (ArrayList<Object>) timer.getInfo();
        CustomerEntity customer = (CustomerEntity) timerInfo.get(0);
        System.out.println("Entering: "+ic.getMethod().toString());
        try {
            return ic.proceed();
        } finally {
            System.out.println("Reminder mail was send to: "+customer.getPerson().getEmail());
        }
    }
}
