package com.ecommerce.service.amazon;

import java.io.*;
import java.util.Date;
import java.util.Random;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ecommerce.model.constant.C;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;

@Service
public class AmazonS3Service {

    private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    @Value("${amazonProperties.accessKey}")
    private String accessKey;

    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = AmazonS3Client.builder()
                .withRegion("us-east-1")
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getName());
        file.transferTo(convFile);
        return convFile;
    }

    private void uploadFile(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(C.AWS.BUCKET, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));


    }

    private static String generateFileName(MultipartFile multiPart) {
        return  new Date().getTime() + "-" +new Random().nextLong()+ multiPart.getOriginalFilename().replace(" ", "_");
    }

    public String uploadFileTos3bucket(MultipartFile multipartFile) {


        String fileName = generateFileName(multipartFile);

        try {
            File file = convertMultiPartToFile(multipartFile);
            uploadFile(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return C.AWS.END_POINT_URL+"/"+ C.AWS.BUCKET+"/"+fileName;
    }

    public String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3client.deleteObject(new DeleteObjectRequest(C.AWS.BUCKET + "/", fileName));
        return "Successfully deleted";
    }

}
