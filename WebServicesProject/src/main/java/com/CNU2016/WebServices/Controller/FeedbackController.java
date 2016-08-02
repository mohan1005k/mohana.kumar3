package com.CNU2016.WebServices.Controller;

/**
 * Created by mohanakumar on 10/07/16.
 */

import com.CNU2016.WebServices.Model.Feedback;
import com.CNU2016.WebServices.Model.User;
import com.CNU2016.WebServices.Pojo.FeedbackContent;
import com.CNU2016.WebServices.Repositories.FeedbackRepository;
import com.CNU2016.WebServices.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class FeedbackController {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    UserRepository userRepository;

    /**
     *
     * @param feedbackContent POJO for receiving the JSON request body
     *              Creates a feedback for any product or order.
     *              This may or may not contain a user name but needs a description.
     *                        Else shows HttpStatus-400
     * @return Httpstatus-201 on successfull create of a feedback
     */

    @Transactional
    @RequestMapping(value="/api/contactus",method=RequestMethod.POST)
    public ResponseEntity<?>ContactUs(@RequestBody FeedbackContent feedbackContent)
    {
        logger.info("Entering ContactUs API");
        String user_name=feedbackContent.getUser_name();
        String description=feedbackContent.getDescription();
        if(description==null)
        {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Description can't be empty");
        }
        User user=userRepository.findByName(user_name);
        Feedback feedbackObject=new Feedback();
        feedbackObject.setDescription(description);
        feedbackObject.setUser_for_feedback(user);
        feedbackObject.setFeedbackDate(new Timestamp(new Date().getTime()));
        logger.info("Exiting ContactUs API");
        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackRepository.save(feedbackObject));

    }

}
