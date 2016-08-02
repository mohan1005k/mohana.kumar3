package com.CNU2016.WebServices.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mohanakumar on 09/07/16.
 */
@Entity
@Table(name="Feedback")


public class Feedback {

    @JsonProperty("feedbackId")
    @Column(name="FeedbackId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int FeedbackId;
    private String description;
    private String email;
    private String type;
    private Timestamp feedbackDate;


    private String status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId")
    private User user_for_feedback;

    public User getUser_for_feedback() {
        return user_for_feedback;
    }

    public void setUser_for_feedback(User user_for_feedback) {
        this.user_for_feedback = user_for_feedback;
    }



    public Feedback()
    {

    }


    public int getFeedbackId() {
        return FeedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        FeedbackId = feedbackId;
    }
    public Timestamp getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Timestamp feedbackDate) {
        this.feedbackDate = feedbackDate;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
