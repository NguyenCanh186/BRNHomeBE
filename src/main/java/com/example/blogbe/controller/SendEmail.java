package com.example.blogbe.controller;

import com.example.blogbe.model.Mailer;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {
    public static void send(Mailer mailer) {
        String from = "leagueyasuo186@gmail.com";
        String password = "bnqx lkha xelz gjpm";
        String to = "canh.nguyen3306@gmail.com";
        String host = "smtp.gmail.com";
        String port = "587";
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            message.setSubject("Đăng ký sử dụng dịch vụ SMS brandname");
            message.setText("Từ: " + mailer.getName() + "\n" +
                    "Email: " + mailer.getEmail() + "\n" +
                    "Số điện thoại: " + mailer.getPhoneNumber() + "\n" +
                    "Lĩnh vực kinh doanh: " + mailer.getBusinessName() + "\n" +
                    "Loại khách hàng: " + mailer.getCustomerType() + "\n" +
                    "Địa chỉ: " + mailer.getAddress());

            Transport.send(message);

            System.out.println("Gửi email thành công");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Gửi email thất bại");
        }
    }
}

