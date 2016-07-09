package com.CNU2016.WebServices;

/**
 * Created by mohanakumar on 07/07/16.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        AWSCredentials credentials = new BasicAWSCredentials("AKIAJWYWLIWTWZKLJRDA", "udhmpDtMIlR+ruEaWV2L9ZEGhA8QFL9sbIcgitqI");
        AmazonS3 s3client = new AmazonS3Client(credentials);


    }
}
