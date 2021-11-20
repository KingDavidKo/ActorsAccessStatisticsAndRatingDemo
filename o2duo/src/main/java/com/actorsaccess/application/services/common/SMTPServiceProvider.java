package com.actorsaccess.application.services.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


import com.actorsaccess.application.config.AAConfig;
import com.actorsaccess.application.system.AAUtil;


public class SMTPServiceProvider {

  public static void transmit(String from, String to, String subj, String aMsg, String host,
      String type, String user, String psw, int port, boolean ssl,
      ArrayList<Object> attachList) throws Exception {
    SMTPServiceProvider.transmit(from, to, subj, aMsg, host, type, user, psw, port, ssl,
        attachList, AAConfig.DEFAULT_ENCODING,false);
  }

  public static void transmit(String from, Object to, String subj, String aMsg, String host,
      String type, String user, String psw, int port, boolean ssl,
      ArrayList<Object> attachList, String enc,boolean bcc) throws Exception {
    if (type == null) {
      type = "text/plain";
    }
    if (subj != null) {
      subj = subj.replace("\n", " ").replace("\r", " ");
    }

    InternetAddress inetFrom[] = InternetAddress.parse(from);
    String toAddress = "";
    String ccAddress = "";
    String bccAddress = "";
    InternetAddress inetTo[] = null;
    InternetAddress inetCc[] = null;
    InternetAddress inetBcc[] = null;
    if (to instanceof String) {
    	toAddress = String.valueOf(to);
    	
    } else {
      Object[] toArray = (Object[])to;
      toAddress = String.valueOf(toArray[0]);
      if (toArray.length > 1 && toArray[1] != null) {
        ccAddress = String.valueOf(toArray[1]);
        if(!AAUtil.isNullOrEmpty(ccAddress) && !ccAddress.trim().equalsIgnoreCase("null"))
          inetCc = InternetAddress.parse(ccAddress);
      }
      if (toArray.length > 2 && toArray[2] != null) {
        bccAddress = String.valueOf(toArray[2]);
        if(!AAUtil.isNullOrEmpty(bccAddress) && !bccAddress.trim().equalsIgnoreCase("null"))
          inetBcc = InternetAddress.parse(bccAddress);
      }
    }
    inetTo = InternetAddress.parse(toAddress);

    Session session = getSession(host, user, psw, port, ssl);
    MimeMessage msg = new MimeMessage(session);
    inetFrom[0].setPersonal("Actors Access");
    msg.setFrom(inetFrom[0]);
    if (inetBcc == null)
      msg.addRecipients(bcc ? Message.RecipientType.BCC : Message.RecipientType.TO, inetTo);
    else
      msg.addRecipients(Message.RecipientType.TO, inetTo);
    if (inetCc != null)
      msg.addRecipients(Message.RecipientType.CC, inetCc);
    if (inetBcc != null)
      msg.addRecipients(Message.RecipientType.BCC, inetBcc);
   
    msg.setSubject(subj);
    MimeBodyPart bpart = new MimeBodyPart();

    bpart.setContent(aMsg, "text/html");

    MimeMultipart mpart = new MimeMultipart();
    mpart.addBodyPart(bpart);

    msg.setContent(mpart);
    msg.setSentDate(new Date());

    if (ssl) {
      Transport tr = session.getTransport("smtps");
      if (port > 0)
        tr.connect(host, port, user, psw);
      else
        tr.connect(host, user, psw);
      tr.sendMessage(msg, msg.getAllRecipients());
      tr.close();
    } else
      Transport.send(msg);
  }

  private static Session getSession(String host, String user, String psw, int port, boolean ssl) {
    Session session;
    Properties properties = new Properties(System.getProperties());
    if (ssl) {
      properties.put("mail.smtps.host", host);
      if (user != null)
        properties.put("mail.smtps.auth", "true");
    } else { //normal
      properties.put("mail.smtp.host", host);
      if (port > 0)
        properties.put("mail.smtp.port", Integer.toString(port));
      if (user != null)
        properties.put("mail.smtp.auth", "true");

      if (properties.getProperty("mail.smtp.starttls.enable") == null)
        properties.put("mail.smtp.starttls.enable", "true");
    }

    if (user != null) {  
      session = Session.getInstance(properties, new PasswordAuthenticator(user, psw));
    } else
      session = Session.getInstance(properties, null);
    return session;
  }

static class PasswordAuthenticator extends Authenticator {
  String user;
  String psw;

  public PasswordAuthenticator(String user, String psw) {
    super();
    this.user = user;
    this.psw = psw;
  }

  public PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication(user, psw);
  }
}

}
