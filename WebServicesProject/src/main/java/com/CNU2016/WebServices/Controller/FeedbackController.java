package com.CNU2016.WebServices.Controller;

/**
 * Created by mohanakumar on 10/07/16.
 */

import java.sql.Timestamp;
import java.util.*;
import java.util.logging.Logger;

import com.CNU2016.WebServices.Model.Orders;
import com.CNU2016.WebServices.Model.*;
import com.CNU2016.WebServices.Pojo.*;
import com.CNU2016.WebServices.PrimaryKey.OrderProductPrimaryKey;
import com.CNU2016.WebServices.Repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class FeedbackController {

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    @RequestMapping(value="/api/contactus",method=RequestMethod.POST)
    public ResponseEntity<?>ContactUs(@RequestBody FeedbackContent feedbackContent)
    {
        String user_name=feedbackContent.getUser_name();
        String description=feedbackContent.getDescription();
        if(description==null)
        {
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("Description can't be empty");
        }
        User user=userRepository.findByName(user_name);
        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackRepository.save(new Feedback(description,user,new Timestamp(new Date().getTime()))));

    }

}
