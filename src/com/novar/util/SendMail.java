/**
 * This class is an abstract class used to make a ManagerManager
 * @author PELCE Nicolas
 */

package com.novar.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
/**
 * Put in place a server SMTP to send mails 
 */
public class SendMail {
	
	/**
	 * @see Properties
	 */
	static Properties mailServerProperties;
	
	/**
	 * @see Session
	 */
	static Session getMailSession;
	
	/**
	 * @see MimeMessage
	 */
	static MimeMessage generateMailMessage;
	
	/**
	 * Generate and send mails 
	 */
	public static void generateAndSendEmail(String address, String password) throws AddressException, MessagingException {
 
// Properties of mailServer SMTP
		
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
 
// Generate Mail Message
		
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(address));
	
		generateMailMessage.setSubject("Password Reset");
		String emailBody = "Your new password for GoFit is reset. " + "<br><br> Your new password is : "+ password + "<br><br> Sincerely yours, <br><br>GoFit®";
		generateMailMessage.setContent(emailBody, "text/html");
 
// Send Mail Message	
		
		Transport transport = getMailSession.getTransport("smtp");
		
		transport.connect("smtp.gmail.com", "servicegofit@gmail.com", "polytech");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}
}

