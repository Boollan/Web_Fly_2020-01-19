package com.boollan.service.impl;

import com.boollan.service.IEmailMessageSend;
import com.boollan.util.module.SendEmail;

/**
 * @author Boollan
 */
public class EmailMessageSend implements IEmailMessageSend {

    private SendEmail sendEmail;
    private String emailSendName;
    private String emailTitle;
    private String emailBody;

    public void setSendEmail(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    public void setEmailSendName(String emailSendName) {
        this.emailSendName = emailSendName;
    }

    public void setEmailTitle(String emailTitle) {
        this.emailTitle = emailTitle;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    @Override
    public boolean sendEmail(String email) throws Exception {
        sendEmail.CreateEmail(email,emailSendName,emailTitle,emailBody);
        return true;
    }

    @Override
    public boolean sendEmail(String email, String sendName) throws Exception {
        sendEmail.CreateEmail(email,sendName,emailTitle,emailBody);
        return true;
    }

    @Override
    public boolean sendEmail(String email, String sendName, String title) throws Exception {
        sendEmail.CreateEmail(email,sendName,title,emailBody);
        return true;
    }

    @Override
    public boolean sendEmail(String email, String sendName, String title, String body) throws Exception {
        sendEmail.CreateEmail(email,sendName,title,body);
        return true;
    }
}
