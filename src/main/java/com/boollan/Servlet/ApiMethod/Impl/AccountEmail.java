package com.boollan.Servlet.ApiMethod.Impl;

import com.boollan.Servlet.ApiMethod.IAccountEmail;
import com.boollan.util.module.SendEmail;

/**
 * 此类是由SpringXML配置文件进行装配
 */
public class AccountEmail implements IAccountEmail {

    private SendEmail sendEmail;
    //定义信息
    private String Email_SendName;
    private String Email_Title;
    private String Email_boey;

    public SendEmail getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    public String getEmail_SendName() {
        return Email_SendName;
    }

    public void setEmail_SendName(String email_SendName) {
        Email_SendName = email_SendName;
    }

    public String getEmail_Title() {
        return Email_Title;
    }

    public void setEmail_Title(String email_Title) {
        Email_Title = email_Title;
    }

    public String getEmail_boey() {
        return Email_boey;
    }

    public void setEmail_boey(String email_boey) {
        Email_boey = email_boey;
    }

    @Override
    public boolean SendEmail(String Email) {
        try {
            sendEmail.CreateEmail(Email, Email_SendName, Email_Title, Email_boey);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean SendEmail(String Email, String SendName) {
        try {
            sendEmail.CreateEmail(Email, SendName, Email_Title, Email_boey);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean SendEmail(String Email, String SendName, String Title) {
        try {
            sendEmail.CreateEmail(Email, SendName, Title, Email_boey);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean SendEmail(String Email, String SendName, String Title, String boey) {
        try {
            sendEmail.CreateEmail(Email, SendName, Title, boey);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
