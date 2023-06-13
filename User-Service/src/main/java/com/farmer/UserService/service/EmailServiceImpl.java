package com.farmer.UserService.service;


import java.io.File;


import com.farmer.UserService.dto.CartDetails;
import com.farmer.UserService.model.EmailDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl  {

    @Autowired private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;




    public  String sendSimpleMail(EmailDetails emailDetails) {
            CartDetails details=new CartDetails();
        try {

            String text="Dear ,"+details.getCustomer().getFirstName()+" /n "+"Below are the List of details added into your Cart"+details.getCrops()+"/n"+details.getFertilizer();


            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(emailDetails.getRecipient());
            mailMessage.setText(text);
            mailMessage.setSubject(emailDetails.getSubject());


            javaMailSender.send(mailMessage);
            return "Email sent Succusfully";
        }

        catch (Exception e) {
            return "mail sent Failed due to exception";
        }
    }

    public String sendMailWithAttachment(EmailDetails details) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(details.getSubject());

            FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));

            mimeMessageHelper.addAttachment(file.getFilename(), file);


            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }


        catch (MessagingException e) {

            return "Error while sending mail!!!";
        }
    }
}
