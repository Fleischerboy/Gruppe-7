package org.example.kotlin.android.app.data.s3bucket

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3Client


class AwsS3bucket() {

    fun getS3Client(Access_id: String, Secret_key: String): AmazonS3Client {
        val awsCredentials: BasicAWSCredentials =
            BasicAWSCredentials(S3constants.Constants.ACCESS_ID, S3constants.Constants.SECRET_KEY)
        return AmazonS3Client(awsCredentials);
    }








}