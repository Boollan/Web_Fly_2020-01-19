package com.boollan.Servlet.ApiMethod;

public interface IAccountEmail {

    boolean SendEmail(String Email);

    boolean SendEmail(String Email, String SendName);

    boolean SendEmail(String Email, String SendName, String Title);

    boolean SendEmail(String Email, String SendName, String Title, String boey);
}
