package com.CNU2016.WebServices.Interceptor;

import com.CNU2016.WebServices.Pojo.LogInformation;
import com.CNU2016.WebServices.SQSQueue.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by mohanakumar on 11/07/16.
 */

@Component
public class SqsQueueInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private QueueService queueService;


    @Override
    public boolean preHandle(HttpServletRequest servletRequest, HttpServletResponse servletResponse,Object handler)throws Exception
    {


        long startTime=System.currentTimeMillis();
        servletRequest.setAttribute("startTime",startTime);
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView)
            throws Exception
    {
        long finishTime=System.currentTimeMillis();
        long startTime=(long)request.getAttribute("startTime");
        Timestamp timestamp=new Timestamp(new Date().getTime());
        long executionTime=finishTime-startTime;
        String ipAddress=request.getRemoteAddr();
        int httpStatus=response.getStatus();
        Enumeration<String> parameterNames=request.getHeaderNames();

        Map<String,String>parameters=new HashMap<String,String>();
        while(parameterNames.hasMoreElements())
        {
            String key=parameterNames.nextElement();
            String value=request.getHeader(key);
            parameters.put(key,value);
        }



        String url=request.getRequestURI().toString();

        LogInformation logInformation=new LogInformation();
        logInformation.setDateOfRequest(timestamp);
        logInformation.setIpAdress(ipAddress);
        logInformation.setParameters(parameters.toString());
        logInformation.setHttpStatus(httpStatus);
        logInformation.setExecutionTime(executionTime);
        logInformation.setUrl(url);
        queueService.setLogInformation(logInformation);
        queueService.queueMessage();




        System.out.println("posthandle");
    }
}
