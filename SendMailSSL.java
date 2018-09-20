package alexaskill;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailSSL {
	
	
	public void sendMail( String mail, String sub, String target, String user, double duration) {
		StringBuilder sb = null;
		Properties props = new Properties();
		props.put("mail.smtp.host", "Your AWS SMTP HOST");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"YOUR USERNAME",
								"YOUR SMTP PASSWORD");
					}
				});

		try {
			sb = new StringBuilder();

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("well-being-guru@digininjas.in",
					"Well-Being Guru"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(mail));
			message.setSubject(sub);

			//Multipart mp = new MimeMultipart();

			//BodyPart htmlPart = new MimeBodyPart();
			//htmlPart.setContent(SendEarlyBird.getBody(sb, coupon),
				//	"text/html; charset=UTF-8");
			//mp.addBodyPart(htmlPart);
			message.setContent(SendMailSSL.getBody(sb, user, target, duration),"text/html");
			
            //message.setText(info);
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			System.out.println("failed for mailid"+mail);
			throw new RuntimeException(e);
			
		} catch (Exception e) {
			System.out.println("failed for mailid"+mail);
			throw new RuntimeException(e);
		}
	}
	
	
	public static String getBody(StringBuilder sb, String user, String target, double duration){
		sb.append("<html>").
		append(" <body leftmargin='0' marginwidth='0' topmargin='0' marginheight='0' offset='0' style='height: 100% !important; width: 100% !important; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; min-width: 100% !important; background-color: #FFFFFF; margin: 0; padding: 0;' bgcolor='#FFFFFF'>&#13;").
        append("<center>&#13;<table align='center' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%' style='height: 100% !important; width: 100% !important; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; background-color: #FFFFFF; margin: 0; padding: 0;' bgcolor='#FFFFFF'><tr><td align='center' valign='top' style='height: 100% !important; width: 100% !important; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; border-top-style: solid; border-top-color: #14c96b; border-top-width: 4px; margin: 0; padding: 0 0 40px;'>&#13;&#13;").
        append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><tr><td align='center' valign='top' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'>&#13;&#13;").
append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; border-top-width: 0; border-bottom-width: 1px; border-bottom-color: #000000; border-bottom-style: solid; display: block !important; background-color: #FFFFFF;' bgcolor='#FFFFFF'><tr><td align='center' valign='top' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'>&#13;").
append("<table border='0' cellpadding='0' cellspacing='0' width='600' class='templateContainer' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; max-width: 600px !important; width: 100% !important;'><tr><td valign='top' class='preheaderContainer' style='padding-top: 10px; padding-bottom: 10px; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnTextBlock' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><tbody class='mcnTextBlockOuter'><tr><td valign='top' class='mcnTextBlockInner' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'>&#13;").
append("&#13;<table align='left' border='0' cellpadding='0' cellspacing='0' width='600' class='mcnTextContentContainer' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; width: 100% !important;'><tbody><tr><td valign='top' class='mcnTextContent' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; color: #000000; font-family: Helvetica; font-size: 14px !important; line-height: 115% !important; text-align: left; padding: 9px 18px;' align='left'>&#13;&#13;").
append("<h1 class='mc-toc-title' style='color: #000000 !important; display: block; font-family: Helvetica; font-size: 24px !important; font-style: normal; font-weight: bold; line-height: 125% !important; letter-spacing: -1px; text-align: center; margin: 0; padding: 0;' align='center'><span style='font-size: 24px;'>Well-Being Guru</span></h1>&#13;&#13;</td>&#13;</tr></tbody></table></td>&#13;").
append("</tr></tbody></table></td>&#13;</tr></table></td>&#13;</tr></table></td>&#13;</tr><tr><td align='center' valign='top' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'>&#13;&#13;").
append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; border-top-width: 0; border-bottom-width: 0; background-color: #FFFFFF;' bgcolor='#FFFFFF'><tr><td align='center' valign='top' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'>&#13;<table border='0' cellpadding='0' cellspacing='0' width='600' class='templateContainer' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; max-width: 600px !important; width: 100% !important;'><tr><td valign='top' class='headerContainer' style='padding-top: 10px; padding-bottom: 10px; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnTextBlock' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><tbody class='mcnTextBlockOuter'><tr><td valign='top' class='mcnTextBlockInner' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'>&#13;&#13;<table align='left' border='0' cellpadding='0' cellspacing='0' width='600' class='mcnTextContentContainer' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; width: 100% !important;'><tbody><tr><td valign='top' class='mcnTextContent' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; color: #000000; font-family: Helvetica; font-size: 18px !important; line-height: 125% !important; text-align: left; padding: 9px 18px;' align='left'>&#13;&#13;").
append("<h1 style='color: #000000 !important; display: block; font-family: Helvetica; font-size: 24px !important; font-style: normal; font-weight: bold; line-height: 125% !important; letter-spacing: -1px; text-align: center; margin: 0; padding: 0;' align='center'><img align='none' height='65' src='https://s3.amazonaws.com/alexamotivation/myfitness_large.png' style='width: 67px; height: auto !important; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; margin: 0px; border: 0;' width='67' /></h1>&#13;&#13;</td>&#13;</tr></tbody></table></td>&#13;").
append("</tr></tbody></table><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnDividerBlock' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><tbody class='mcnDividerBlockOuter'><tr><td class='mcnDividerBlockInner' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; padding: 18px;'>&#13;<table class='mcnDividerContent' border='0' cellpadding='0' cellspacing='0' width='100%' style='border-top-width: 4px; border-top-style: solid; border-top-color: #000000; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><tbody><tr><td style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'>&#13;<span></span>&#13;</td>&#13;</tr></tbody></table></td>&#13;</tr></tbody></table></td>&#13;</tr></table></td>&#13;</tr></table></td>&#13;").
append("</tr><tr><td align='center' valign='top' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'>&#13;&#13;<table border='0' cellpadding='0' cellspacing='0' width='100%' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; border-top-width: 0; border-bottom-width: 0; background-color: #FFFFFF;' bgcolor='#FFFFFF'><tr><td align='center' valign='top' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'>&#13;").
append("<table border='0' cellpadding='0' cellspacing='0' width='600' class='templateContainer' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; max-width: 600px !important; width: 100% !important;'><tr><td valign='top' class='bodyContainer' style='padding-top: 10px; padding-bottom: 10px; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnTextBlock' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><tbody class='mcnTextBlockOuter'><tr><td valign='top' class='mcnTextBlockInner' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'>&#13;&#13;<table align='left' border='0' cellpadding='0' cellspacing='0' width='600' class='mcnTextContentContainer' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; width: 100% !important;'><tbody><tr><td valign='top' class='mcnTextContent' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; color: #000000; font-family: Helvetica; font-size: 18px !important; line-height: 125% !important; text-align: left; padding: 9px 18px;' align='left'>&#13;").
append("&#13;<h3 style='color: #000000 !important; display: block; font-family: Helvetica; font-size: 18px !important; font-style: normal; font-weight: bold; line-height: 125% !important; letter-spacing: normal; text-align: center; margin: 0; padding: 0;' align='center'>Customized Diet Plan for "+user+"<br />&#13;</h3>&#13;&#13;</td>&#13;</tr></tbody></table></td>&#13;").
append("</tr></tbody></table><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnTextBlock' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><tbody class='mcnTextBlockOuter'><tr><td valign='top' class='mcnTextBlockInner' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'>&#13;&#13;<table align='left' border='0' cellpadding='0' cellspacing='0' width='600' class='mcnTextContentContainer' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; width: 100% !important;'><tbody><tr><td valign='top' class='mcnTextContent' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; color: #000000; font-family: Helvetica; font-size: 18px !important; line-height: 125% !important; text-align: left; padding: 9px 18px;' align='left'>&#13;").
append("&#13;<span style='font-family: arial,helvetica neue,helvetica,sans-serif;'><span style='font-size: 12px;'>I will be helping you with acheiving your target weight. <br />&#13;You can interact with me every day so that I can review your daily progress and keep you on track. This is a customized diet plan generated for you. Please follow it for effective and immediate results..<br /><br />&#13;Predicted Duration : "+duration+" weeks to reach your target weight of "+target+"kgs</span></span>&#13;").
append("</td>&#13;</tr></tbody></table></td>&#13;</tr></tbody></table><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnImageGroupBlock' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><tbody class='mcnImageGroupBlockOuter' style='padding-bottom: 9px !important; padding-top: 9px !important;'><tr><td valign='top' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; padding: 0 9px;' class='mcnImageGroupBlockInner'>&#13;&#13;").
append("<table align='left' width='273' border='0' cellpadding='0' cellspacing='0' class='mcnImageGroupContentContainer' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; width: 100% !important;'><tbody><tr><td class='mcnImageGroupContent' valign='top' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; padding: 9px;'>&#13;&#13;&#13;").
append("<img alt='' src='https://s3.amazonaws.com/alexamotivation/chart.jpg' width='564' style='max-width: 800px; padding-bottom: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; vertical-align: bottom; width: 100% !important; border: 0;' class='mcnImage' /></td>&#13;</tr></tbody></table>").
append("<table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnTextBlock' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><tbody class='mcnTextBlockOuter'><tr><td valign='top' class='mcnTextBlockInner' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'>&#13;&#13;").
append("<table align='left' border='0' cellpadding='0' cellspacing='0' width='600' class='mcnTextContentContainer' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; width: 100% !important;'><tbody><tr><td valign='top' class='mcnTextContent' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; color: #000000; font-family: Helvetica; font-size: 18px !important; line-height: 125% !important; text-align: left; padding: 9px 18px;' align='left'>&#13;&#13;<span style='font-size: 12px;'><span style='font-family: arial,helvetica neue,helvetica,sans-serif;'>If you are not able to view the image, please click show images displayed on this mail.<br>Try these 'Alexa ask wellbeing guru to give me a healthy tip' <br> 'Alexa ask wellbeing guru to review my today's progress' <br> 'Alexa ask wellbeing guru to review my weekly progress.'<br> Now what are you waiting for ? Let us burn some calories <br>P.S: Please install google fit app in your device.<br><br /><br />&#13;").
append("Happy Burning <br />&#13;Team Well-Being Guru</span></span>&#13;</td>&#13;</tr></tbody></table></td>&#13;</tr></tbody></table></td>&#13;</tr></table></td>&#13;</tr></table></td>&#13;").
append("</tr><tr><td align='center' valign='top' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'>&#13;&#13;").
append("<table border='0' cellpadding='0' cellspacing='0' width='100%' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; border-top-width: 0; border-bottom-width: 0; background-color: #FFFFFF;' bgcolor='#FFFFFF'><tr><td align='center' valign='top' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'>&#13;<table border='0' cellpadding='0' cellspacing='0' width='600' class='templateContainer' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; max-width: 600px !important; width: 100% !important;'><tr><td valign='top' class='footerContainer' style='padding-top: 10px; padding-bottom: 10px; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnDividerBlock' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><tbody class='mcnDividerBlockOuter'><tr><td class='mcnDividerBlockInner' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; padding: 18px;'>&#13;").
append("<table class='mcnDividerContent' border='0' cellpadding='0' cellspacing='0' width='100%' style='border-top-width: 4px; border-top-style: solid; border-top-color: #000000; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><tbody><tr><td style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'>&#13;<span></span>&#13;</td>&#13;").
append("</tr></tbody></table></td>&#13;</tr></tbody></table><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnFollowBlock' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><tbody class='mcnFollowBlockOuter'><tr><td align='center' valign='top' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; padding: 9px;' class='mcnFollowBlockInner'>&#13;<table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnFollowContentContainer' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><tbody><tr><td align='center' style='padding-left: 9px; padding-right: 9px; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'>&#13;").
append("<table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnFollowContent' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><tbody><tr><td align='center' valign='top' style='padding-top: 9px; padding-right: 9px; padding-left: 9px; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'>&#13;<table border='0' cellpadding='0' cellspacing='0' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><tbody><tr><td valign='top' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'>&#13;&#13;&#13;&#13;").
append("<table align='left' border='0' cellpadding='0' cellspacing='0' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><tbody><tr><td valign='top' style='padding-right: 10px; padding-bottom: 9px; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;' class='mcnFollowContentItemContainer'>&#13;<table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnFollowContentItem' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><tbody><tr><td align='left' valign='middle' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; padding: 5px 10px 5px 9px;'>&#13;").
append("<table align='left' border='0' cellpadding='0' cellspacing='0' width='' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><tbody><tr><td align='center' valign='middle' width='24' class='mcnFollowIconContent' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'>&#13;<a href='http://www.facebook.com/' target='_blank' style='word-wrap: break-word; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><img src='http://www.dietonclick.com/img/fb.png' style='display: block; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: 0;' height='24' width='24' class='' /></a>&#13;").
append("</td>&#13;&#13;&#13;</tr></tbody></table></td>&#13;</tr></tbody></table></td>&#13;</tr></tbody></table><table align='left' border='0' cellpadding='0' cellspacing='0' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><tbody><tr><td valign='top' style='padding-right: 0; padding-bottom: 9px; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;' class='mcnFollowContentItemContainer'>&#13;").
append("<table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnFollowContentItem' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><tbody><tr><td align='left' valign='middle' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; padding: 5px 10px 5px 9px;'>&#13;<table align='left' border='0' cellpadding='0' cellspacing='0' width='' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><tbody><tr><td align='center' valign='middle' width='24' class='mcnFollowIconContent' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'>&#13;").
append("<a href='http://plus.google.com.com/' target='_blank' style='word-wrap: break-word; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><img src='http://www.dietonclick.com/img/gg.png' style='display: block; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: 0;' height='24' width='24' class='' /></a>&#13;</td>&#13;&#13;&#13;").
append("</tr></tbody></table></td>&#13;</tr></tbody></table></td>&#13;</tr></tbody></table></td>&#13;</tr></tbody></table></td>&#13;</tr></tbody></table></td>&#13;</tr></tbody></table></td>&#13;").
append("</tr></tbody></table><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnTextBlock' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'><tbody class='mcnTextBlockOuter'><tr><td valign='top' class='mcnTextBlockInner' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important;'>&#13;").
append("&#13;<table align='left' border='0' cellpadding='0' cellspacing='0' width='600' class='mcnTextContentContainer' style='border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; width: 100% !important;'><tbody><tr><td valign='top' class='mcnTextContent' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: none !important; color: #000000; font-family: Helvetica; font-size: 14px !important; line-height: 115% !important; text-align: center; padding: 9px 18px;' align='center'>&#13;&#13;&#13;</td>&#13;").
append("</tr></tbody></table></td>&#13;</tr></tbody></table></td>&#13;</tr></table></td>&#13;</tr></table></td>&#13;</tr></table></td>&#13;</tr></table></center>&#13;</body>").
		append("</html>");
		return sb.toString();
		
	}
	
	public static void main(String[] args) {
		System.out.println("inside main class");
		SendMailSSL send = new SendMailSSL();
	//send.sendMail("Actual Weight : 65kgs <br> TargetWeight: 60kgs <br> Predicted Duration : 4 weeks to acheive the target", "YOUR TEST MAIL EXAMPLE YOUR GMAIL ID", "DietPlan");
	}

	
	
	public void sendMailToken(String token) {
		StringBuilder sb = null;
		Properties props = new Properties();
		props.put("mail.smtp.host", "YOUR SMTP HOST");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"YOUR SMTP USERNAME",
								"YOUR SMTP PASSWORD");
					}
				});

		try {
			sb = new StringBuilder();

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("well-being-guru@digininjas.in",
					"Well-Being Guru Testing started"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("YOUR TEST MAIL ID"));
			message.setSubject("Testing started");

			//Multipart mp = new MimeMultipart();

			//BodyPart htmlPart = new MimeBodyPart();
			//htmlPart.setContent(SendEarlyBird.getBody(sb, coupon),
				//	"text/html; charset=UTF-8");
			//mp.addBodyPart(htmlPart);
			message.setContent(token,"text/html");
			
            //message.setText(info);
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			System.out.println("failed for mailid");
			throw new RuntimeException(e);
			
		} catch (Exception e) {
			System.out.println("failed for mailid");
			throw new RuntimeException(e);
		}
	}
	
}
