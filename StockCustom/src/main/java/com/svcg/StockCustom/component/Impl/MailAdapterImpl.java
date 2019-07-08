package com.svcg.StockCustom.component.Impl;

import com.svcg.StockCustom.StockCustomGetPropertyValues;
import com.svcg.StockCustom.component.MailAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

@Service("MailAdapterImpl")
public class MailAdapterImpl implements MailAdapter {

    @Autowired
	private JavaMailSenderImpl mailSender;

	@Override
    @Async
	public void sendMail(String to, String subject, String body) throws IOException {
		StockCustomGetPropertyValues properties = new StockCustomGetPropertyValues();
		Properties result = properties.getPropValues();
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setTo(to);
	    message.setSubject(subject);
	    message.setText(body);
	    message.setFrom(result.getProperty("username"));
	    this.setMailSender(result);
	    mailSender.send(message);
	}

	@Async
	public void sendMailHTML(String to, String subject, String body) throws IOException {
		StockCustomGetPropertyValues properties = new StockCustomGetPropertyValues();
		Properties result = properties.getPropValues();
		String email = result.getProperty("username");
		String html = "<!DOCTYPE html>\n" +
				"<html lang=\"en\">\n" +
				"<head>\n" +
				"    <meta charset=\"UTF-8\">\n" +
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
				"    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
				"    <style>\n" +
				"        body {\n" +
				"            font-family: Lato;\n" +
				"        }\n" +
				"        .header {\n" +
				"            min-height: 80px;\n" +
				"        }\n" +
				"        .title {\n" +
				"            color: white;\n" +
				"            background: #2A3E52;\n" +
				"            padding: 10px 0;\n" +
				"            text-align: center;\n" +
				"            font-size: 1.5em;\n" +
				"        }\n" +
				"        .content {\n" +
				"            min-height: 200px;\n" +
				"            padding: 20px 0;" +
				"        }\n" +
				"        .footer {\n" +
				"            min-height: 80px;\n" +
				"            padding: 10px 0;\n" +
				"            text-align: center;\n" +
				"            background-color: #DDD;\n" +
				"            font-size: 0.8em;\n" +
				"        }\n" +
				"        .footer-img {\n" +
				"            float: left;\n" +
				"            width: calc(50% - 5px);\n" +
				"            text-align: right;\n" +
				"            margin-right: 5px;\n" +
				"            margin-top: 17px;\n" +
				"        }\n" +
				"        .footer-content {\n" +
				"            float: left;\n" +
				"            width: calc(50% - 5px);\n" +
				"            text-align: left;\n" +
				"            margin-left: 5px;\n" +
				"            vertical-align: middle;\n" +
				"            min-height: 80px;\n" +
				"        }\n" +
				"        @media (max-width: 444px) {\n" +
				"            .header img {\n" +
				"                height: 50px;\n" +
				"            }\n" +
				"            .footer {\n" +
				"                font-size: 0.7em;\n" +
				"            }\n" +
				"            .footer img {\n" +
				"                height: 40px;\n" +
				"            }\n" +
				"        }\n" +
				"        @media (max-width: 360px) {\n" +
				"            .header img {\n" +
				"                height: 40px;\n" +
				"            }\n" +
				"            .footer {\n" +
				"                font-size: 0.6em;\n" +
				"            }\n" +
				"        }\n" +
				"    </style>\n" +
				"    <title>EMAIL</title>\n" +
				"</head>\n" +
				"<body>\n" +
				"    <div class=\"header\">\n" +
				"    </div>\n" +
				"    <div class=\"title\">\n" + subject +
				"        \n" +
				"    </div>\n" +
				"    <div class=\"content\">\n" + body +
				"        \n" +
				"    </div>\n" +
				"    <div class=\"footer\">\n" +
				"        <div  class=\"footer-content\">\n" +
				"                Almirante Brown y El Callao\n" + 
				"General San Martín, Mendoza, Argentina<br> (5570)\n" +
				"                MENDOZA. ARGENTINA<br> <span class=\"glyphicon glyphicon-phone-alt\"></span> +54 0263 433-9891<br>\n" +
				"                <a href=\"mailto:"+ email +"\" target=\"_blank\">"+ email +"</a>\n" +
				"        </div>\n" +
				"    </div>\n" +
				"    \n" +
				"</body>\n" +
				"</html>";
		this.setMailSender(result);
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(html, true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		mailSender.send(message);
	}
	
	private void setMailSender(Properties result) {
        Properties mailProperties = new Properties();
        mailSender.setHost(result.getProperty("host"));
        mailSender.setUsername(result.getProperty("username"));
        mailSender.setPassword(result.getProperty("password"));
        mailSender.setPort(Integer.parseInt(result.getProperty("smtp-port")));
        mailSender.setProtocol("smtp");
        mailSender.setDefaultEncoding("UTF-8");
        mailProperties.put("mail.smtp.auth", true);
        mailProperties.put("mail.smtp.starttls.enable", true);
		mailProperties.put("mail.smtp.starttls.required", true);
        mailSender.setJavaMailProperties(mailProperties);
    }

}
