package com.CNU2016.WebServices.Model;

/**
 * Created by mohanakumar on 09/07/16.
 */
public class OrderHelper {
    private String emailId;
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public OrderHelper()
    {

    }
    public OrderHelper(String emailId)
    {
        this.emailId=emailId;
    }

}
