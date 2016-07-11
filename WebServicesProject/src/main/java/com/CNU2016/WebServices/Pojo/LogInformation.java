package com.CNU2016.WebServices.Pojo;


import java.sql.Timestamp;
import java.util.*;

/**
 * Created by mohanakumar on 11/07/16.
 */
public class LogInformation {

    private Timestamp dateOfRequest;
    private String url;
    private int parameter;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getParameter() {
        return parameter;
    }

    public void setParameter(int parameter) {
        this.parameter = parameter;
    }

    public Timestamp getDateOfRequest() {
        return dateOfRequest;
    }

    public void setDateOfRequest(Timestamp dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
    }

    private String responseCode;
    private String ipAdress;
    public LogInformation()
    {

    }
}
