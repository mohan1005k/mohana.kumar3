package com.CNU2016.WebServices.Pojo;

/**
 * Created by mohanakumar on 09/07/16.
 */
public class OrderHelper {
    private String emailId;



    private String user_name;
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String user_name) {
        this.user_name = user_name;
    }

    public OrderHelper()
    {

    }
    public OrderHelper(String emailId)
    {
        this.emailId=emailId;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

}
