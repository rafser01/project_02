package com.smartMess.model;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;

import org.apache.commons.mail.SimpleEmail;


public class SendVerificationMail {

    public SendVerificationMail() {
    }
    Email email = new SimpleEmail();
     public void sendMail2(String sendTo, String code, String name){


          try {
            String authuser = "lovid.cosa@gmail.com";
            String authpwd = "r50493233";
            email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator(authuser, authpwd));
            email.setDebug(true);
            email.setHostName("smtp.gmail.com");
            
            email.getMailSession().getProperties().put("mail.smtps.auth", "true");
            email.getMailSession().getProperties().put("mail.debug", "true");
            email.getMailSession().getProperties().put("mail.smtps.port", "587");
            email.getMailSession().getProperties().put("mail.smtps.socketFactory.port", "587");
            email.getMailSession().getProperties().put("mail.smtps.socketFactory.class",   "javax.net.ssl.SSLSocketFactory");
            email.getMailSession().getProperties().put("mail.smtps.socketFactory.fallback", "false");
            email.getMailSession().getProperties().put("mail.smtp.starttls.enable", "true");
            
            email.setFrom("lovid.cosa@gmail.com", "Lovid");
            email.setSubject("Smart Mess Mail Verification");
            email.setMsg("Thank you for your interest. Put this code ("+code+") to our page");
            email.addTo(sendTo,"Hi "+ name);
//            email.setStartTLSRequired(false);
            email.send();
            
        } catch (EmailException e) {
            e.printStackTrace();
        }
     

     }
}
