package com.CNU2016.WebServices.Pojo;




import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by mohanakumar on 11/07/16.
 */
public class LogInformation {

    private Timestamp dateOfRequest;
    private String url;
    private String parameters;
    private int httpStatus;
    private String ipAdress;



    private long executionTime;

    public String getParameters() {
        return parameters;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }
    public void setParameters(String parameters) {
        this.parameters = parameters;
    }


    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
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



    public Timestamp getDateOfRequest() {
        return dateOfRequest;
    }

    public void setDateOfRequest(Timestamp dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
    }


    public LogInformation()
    {

    }
}
