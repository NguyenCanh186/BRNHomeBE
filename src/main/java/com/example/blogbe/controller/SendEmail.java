package com.example.blogbe.controller;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {
    public static void send() {
        // Thông tin email nguồn
        String from = "leagueyasuo186@gmail.com";
        String password = "Anhkiet2512@*"; // Thay thế bằng mật khẩu email của bạn


        String to = "canh.nguyen3306@gmail.com";

        String host = "smtp.gmail.com";
        String port = "587";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Tạo phiên gửi email
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            message.setSubject("Chủ đề email");
            message.setText("Nội dung email");

            Transport.send(message);

            System.out.println("Gửi email thành công");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

