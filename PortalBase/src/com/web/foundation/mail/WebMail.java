package com.web.foundation.mail;

import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConnectionParameters;
import com.rabbitmq.client.MessageProperties;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.util.File;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.util.CommonUtil;
import com.web.util.PropertiesReader;

public class WebMail {
	private String propertiesLocation = "com/web/foundation/mail/webmail";
	private String protocolName = "smtp";
	private PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
	private String customerKey = "";
	private Session session;
	private Message msg;
	private MailParameters mailParameters;

	public WebMail(MailParameters mailParameters) {
		// boolean debug = true;
		this.mailParameters = mailParameters;
		customerKey = mailParameters.getCustomerKey();
		// Set the host smtp address
		Properties properties = new Properties();
		properties.put("mail." + protocolName + ".host", propertiesReader.getValueOfKey(customerKey + "_smtp_host"));
		properties.put("mail." + protocolName + ".auth", "true");
		if (propertiesReader.getValueOfKey(customerKey + "_smtp_port") != null) {
			properties
					.put("mail." + protocolName + ".port", propertiesReader.getValueOfKey(customerKey + "_smtp_port"));
		}
		if (propertiesReader.getValueOfKey(customerKey + "_smtp_encryption") != null
				&& propertiesReader.getValueOfKey(customerKey + "_smtp_encryption").equals("SSL")) {
			properties.put("mail.smtp.ssl.enable", true);
		} else if (propertiesReader.getValueOfKey(customerKey + "_smtp_encryption") != null
				&& propertiesReader.getValueOfKey(customerKey + "_smtp_encryption").equals("STARTTLS")) {
			properties.put("mail.smtp.starttls.enable", true);
		}

		// properties.put("mail.debug", "true");

		// create some properties and get the default Session
		session = Session.getDefaultInstance(properties, null);
		// session.setDebug(debug);
	}

	public String getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(String customerKey) {
		this.customerKey = customerKey;
	}

	public MailParameters getMailParameters() {
		return mailParameters;
	}

	public void setMailParameters(MailParameters mailParameters) {
		this.mailParameters = mailParameters;
	}

	/**
	 * This is the method that accepts a single recipient
	 * 
	 * @param mailRecipients
	 *            - single recipient
	 * @param mailSubject
	 * @param mailMessage
	 * @param mailFrom
	 * @param customerKey
	 *            - assuming that we prepend the keys in properties files for
	 *            different customers
	 * @throws MessagingException
	 * @throws FrameworkException
	 * @throws IOException
	 * @throws NumberFormatException
	 * 
	 */
	public void sendSingleMail(MailParameters mailParameters) throws MessagingException, FrameworkException {
		// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		if ((propertiesReader.getValueOfKey("mail_enabled") != null)
				&& propertiesReader.getValueOfKey("mail_enabled").equals("1")) {
			if ((propertiesReader.getValueOfKey("queueing_enabled") != null)
					&& propertiesReader.getValueOfKey("queueing_enabled").equals("1")) {
				try {
					enqueueEmailMessage();
				} catch (NumberFormatException e) {
					throw new FrameworkException("messaging_exception", e.getCause());
				} catch (IOException e) {
					throw new FrameworkException("messaging_exception", e.getCause());
				}
			} else {
				sendMailSynchronously();
			}
		}
	}

	/**
	 * This is the method that accepts a collection of recipients
	 * 
	 * @param mailRecipients
	 *            - Collection of multiple recipients
	 * @param mailSubject
	 * @param mailMessage
	 * @param mailFrom
	 * @param customerKey
	 *            - assuming that we prepend the keys in properties files for
	 *            different customers
	 * @throws MessagingException
	 * @throws FrameworkException
	 * @throws IOException
	 * @throws NumberFormatException
	 * 
	 */
	public void sendMultipleMail() throws MessagingException, FrameworkException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside PortalMail ::  before enqueueEmailMessage()");
		// boolean debug = false;
		if (propertiesReader.getValueOfKey("mail_enabled").equals("1")) {

			if ((propertiesReader.getValueOfKey("queueing_enabled") != null)
					&& propertiesReader.getValueOfKey("queueing_enabled").equals("1")) {
				try {
					myLog.debug("inside PortalMail ::  before enqueueEmailMessage()");
					enqueueEmailMessage();
					myLog.debug("inside PortalMail ::  after enqueueEmailMessage()");
				} catch (NumberFormatException e) {
					throw new FrameworkException("messaging_exception", e.getCause());
				} catch (IOException e) {
					throw new FrameworkException("messaging_exception", e.getCause());
				}
			} else {
				sendMailSynchronously();
			}
		}
	}

	public void sendMail() throws MessagingException {
		createEmailMessage();
		sendEmailMessage();
	}

	private void createEmailMessage() throws MessagingException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside createEmailMessage ::  ");
		// create a message
		msg = new MimeMessage(session);
		myLog.debug("session ::  " + session);

		// set the from and to address
		InternetAddress addressFrom = new InternetAddress(propertiesReader.getValueOfKey(customerKey + "_from"));
		msg.setFrom(addressFrom);

		// set reply to address. if this null, then make sure "from" in
		// properties includes the mail id
		if (mailParameters.getMailReplyTo() != null && mailParameters.getMailReplyTo().length > 0) {
			msg.setReplyTo(mailParameters.getMailReplyTo());
		}

		// if (mailParameters.getMailRecipients() != null) {
		// for (int i = 0; i < mailParameters.getMailRecipients().length; i++) {
		// System.out.println((mailParameters.getMailRecipients()[i]).getAddress());
		// }
		// }
		// set TO mail recipient
		if (mailParameters.getMailRecipients() != null && mailParameters.getMailRecipients().length > 0) {
			msg.setRecipients(Message.RecipientType.TO, mailParameters.getMailRecipients());
		}

		// set CC mail recipient
		if (mailParameters.getMailRecipientsCC() != null && mailParameters.getMailRecipientsCC().length > 0) {
			msg.setRecipients(Message.RecipientType.CC, mailParameters.getMailRecipientsCC());
		}

		// set BCC mail recipient
		if (mailParameters.getMailRecipientsBCC() != null && mailParameters.getMailRecipientsBCC().length > 0) {
			msg.setRecipients(Message.RecipientType.BCC, mailParameters.getMailRecipientsBCC());
		}

		// You can also set your custom headers in the Email if you want
		// msg.addHeader("MyHeaderName", "myHeaderValue");

		// Setting the Subject and Content Type
		msg.setSubject(mailParameters.getMailSubject());

		Multipart mp = new MimeMultipart();
		if ((mailParameters.getMailFormat() == null)
				|| mailParameters.getMailFormat().equals(CommonConstant.MimeType.TEXT_PLAIN)) {
			createTextMessage(msg, mp);
		} else if (mailParameters.getMailFormat().equals(CommonConstant.MimeType.TEXT_HTML)) {
			createHtmlMessage(msg, mp);
		}
		addAttachments(msg, mp);
		msg.setContent(mp);
		myLog.debug("inside createEmailMessage end of method ::  ");
	}

	private void sendEmailMessage() throws MessagingException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside sendEmailMessage protocolName  " + protocolName);
		myLog.debug("inside sendEmailMessage session " + session);
		Transport mailTransport = session.getTransport(protocolName);
		myLog.debug("before mssg abcd:: ");
		myLog.debug("before sent mail " + msg.getSubject());
		mailTransport.connect(propertiesReader.getValueOfKey(customerKey + "_smtp_host"),
				propertiesReader.getValueOfKey(customerKey + "_mail_user"),
				propertiesReader.getValueOfKey(customerKey + "_mail_password"));
		myLog.debug("after sent mail " + msg.getSubject());
		mailTransport.sendMessage(msg, msg.getAllRecipients());
		myLog.debug("sent mail " + msg.getSubject());
	}

	private void createTextMessage(Message msg, Multipart mp) throws MessagingException {
		String text = mailParameters.getMailMessage();

		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent(text, CommonConstant.MimeType.TEXT_PLAIN);
		mp.addBodyPart(textPart);
	}

	private void createHtmlMessage(Message msg, Multipart mp) throws MessagingException {
		String html = mailParameters.getMailMessage();
		html = "<HTML><BODY>" + html + "</BODY></HTML>";

		MimeBodyPart htmlPart = new MimeBodyPart();
		htmlPart.setContent(html, CommonConstant.MimeType.TEXT_HTML);
		mp.addBodyPart(htmlPart);
	}

	private void addAttachments(Message msg, Multipart mp) throws MessagingException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside PortalMail ::  inside addAttachments");
		if (mailParameters.getAttachments() != null) {
			for (int i = 0; i < mailParameters.getAttachments().size(); i++) {
				File file = mailParameters.getAttachments().get(i);
				String contentType = file.getMime();
				if (contentType == null) {
					contentType = CommonConstant.MimeType.APP_OCTET_STREAM;
				}
				DataSource ds = new ByteArrayDataSource(file.getData(), file.getMime());
				MimeBodyPart attachmentPart = new MimeBodyPart();
				attachmentPart.setDataHandler(new DataHandler(ds));
				attachmentPart.setFileName(file.getName());
				mp.addBodyPart(attachmentPart);
			}
		}
	}

	private void sendMailSynchronously() throws MessagingException {
		sendMail();
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside PortalMail ::  inside enqueueEmailMessage(5455623)");

	}

	private void enqueueEmailMessage() throws NumberFormatException, IOException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside PortalMail ::  inside enqueueEmailMessage()");
		PropertiesReader propertiesReaderCommon = new PropertiesReader(CommonConstant.MessageLocation.COMMON_MESSAGES);
		myLog.debug("inside PortalMail ::  inside enqueueEmailMessage() :: 1");
		ConnectionParameters params = new ConnectionParameters();
		params.setUsername(propertiesReaderCommon.getValueOfKey("rabbitmq_username"));
		params.setPassword(propertiesReaderCommon.getValueOfKey("rabbitmq_password"));
		params.setVirtualHost(propertiesReaderCommon.getValueOfKey("rabbitmq_virtualhost"));
		params.setRequestedHeartbeat(0);
		myLog.debug("inside PortalMail ::  inside enqueueEmailMessage() :: 2");
		ConnectionFactory factory = new ConnectionFactory(params);
		myLog.debug("inside PortalMail ::  inside enqueueEmailMessage() :: 3");
		Connection conn = factory.newConnection(propertiesReaderCommon.getValueOfKey("rabbitmq_hostname"),
				Integer.parseInt(propertiesReaderCommon.getValueOfKey("rabbitmq_port")));
		myLog.debug("inside PortalMail ::  inside enqueueEmailMessage() :: 4");
		Channel channel = conn.createChannel();
		myLog.debug("inside PortalMail ::  inside enqueueEmailMessage() :: 5");
		String exchangeName = propertiesReaderCommon.getValueOfKey("rabbitmq_exchange_name");
		myLog.debug("inside PortalMail ::  inside enqueueEmailMessage() :: 6");
		String routingKeySent = mailParameters.getRoutingKey();
		myLog.debug("inside PortalMail ::  inside enqueueEmailMessage() :: 7");
		String messageQueue = mailParameters.getMessageQueue();
		myLog.debug("inside PortalMail ::  inside enqueueEmailMessage() :: 8");
		byte[] messageBodyBytes;
		myLog.debug("===Routing Key sent =====" + routingKeySent);
		if (routingKeySent != null && routingKeySent.trim().length() > 0) {
			if (messageQueue != null && messageQueue.trim().length() > 0) {
				boolean durable = true;
				channel.queueDeclare(messageQueue, durable);
				channel.queueBind(messageQueue, exchangeName, routingKeySent);
			}
			myLog.debug("inside if block");
			messageBodyBytes = CommonUtil.toByteArray(mailParameters);
			myLog.debug("inside if block======");
		} else {
			routingKeySent = propertiesReaderCommon.getValueOfKey("rabbitmq_email_routing_key");
			myLog.debug("inside else block");
			messageBodyBytes = new Gson().toJson(mailParameters).getBytes();
		}
		// byte[] messageBodyBytes = new
		// Gson().toJson(mailParameters).getBytes();
		myLog.debug("===Routing Key=====" + routingKeySent);
		channel.basicPublish(exchangeName, routingKeySent, MessageProperties.PERSISTENT_TEXT_PLAIN, messageBodyBytes);
		channel.close();
		conn.close();
	}
}
