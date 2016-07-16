package com.CNU2016.WebServices.Pojo;

/**
 * Created by mohanakumar on 09/07/16.
 */
public class NotFound {

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    private String detail;
    public NotFound()
    {

    }
    public NotFound(String detail)
    {
        this.detail=detail;
    }

}
