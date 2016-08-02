package com.CNU2016.WebServices.Controller;

/**
 * Created by mohanakumar on 09/07/16.
 */

import java.util.*;

import com.CNU2016.WebServices.Model.User;

import com.CNU2016.WebServices.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    @Autowired
    UserRepository userRepository;

  }
