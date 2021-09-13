package es.udc.fic.manoelfolgueira.gdai.model.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
	
	private static final String SMTP_SERVER = "smtp.gmail.com";
	private static final String SMTP_PORT = "465";
	
	private static final String fromEmailAuthAddress = "gdaiapp@gmail.com";
	private static final String fromEmailAuthPassword = "wey3QGBFEh";

	public static void send(String toEmail, String subject, String body) {

		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", SMTP_SERVER);
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
			props.setProperty("mail.smtp.socketFactory.fallback", "false"); 
			props.setProperty("mail.smtp.port", SMTP_PORT); 
			props.setProperty("mail.smtp.socketFactory.port", SMTP_PORT); 
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			// create some properties and get the default Session
			Session sessionMail = Session.getInstance(props, new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmailAuthAddress, fromEmailAuthPassword);
				}
			});
			sessionMail.setDebug(true);

			Message msg = new MimeMessage(sessionMail);

			InternetAddress addressFrom = new InternetAddress(fromEmailAuthAddress);
			msg.setFrom(addressFrom);
			InternetAddress[] addressTo = new InternetAddress[1];
			addressTo[0] = new InternetAddress(toEmail);
			msg.setRecipients(Message.RecipientType.TO, addressTo);

			msg.addHeader("site", "gdai.localhost");

			msg.setSubject(subject);
			msg.setContent(body, "text/html");
			Transport.send(msg);
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}