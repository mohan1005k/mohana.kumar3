package com.CNU2016.WebServices.SQSQueue;

import com.CNU2016.WebServices.Pojo.LogInformation;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mohanakumar on 11/07/16.
 */

@Component
public class QueueService {



    private AmazonSQSAsyncClient sqsAsync=new AmazonSQSAsyncClient();


    private LogInformation logInformation;



    public QueueService()
    {

    }
    public LogInformation getLogInformation() {
        return logInformation;
    }

    public void setLogInformation(LogInformation logInformation) {
        this.logInformation = logInformation;
    }
    public QueueService(LogInformation logInformation)
    {
        this.logInformation=logInformation;
    }
    public void queueMessage()
    {


        String sqsUrl="https://sqs.us-east-1.amazonaws.com/368606650716/cnu2016_mohana_kumar_assignment05";
        Gson gson = new Gson();
        String json = gson.toJson(this.logInformation);
        sqsAsync.sendMessage(new SendMessageRequest(sqsUrl,json));
    }

}
