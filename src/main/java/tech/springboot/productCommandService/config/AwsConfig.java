package tech.springboot.productCommandService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class AwsConfig {

  @Value ("${aws.iam.key}")
  private String accessKeyId;

  @Value ("${aws.iam.secret}")
  private String secretAccessKey;

  @Value ("${aws.sqs.url}")
  private String sqsUrl;

  @Bean
  public SqsClient getSqsClient () {

    return SqsClient.builder ()
        .region (Region.AP_SOUTH_1)
        .credentialsProvider (StaticCredentialsProvider.create (
            AwsBasicCredentials.create (accessKeyId, secretAccessKey)))
        .build ();
  }

}
