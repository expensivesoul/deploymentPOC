package com.example.demo.utils;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component("PropertiesListener")
public class PropertiesListener implements ApplicationListener<ApplicationPreparedEvent> {
    private ObjectMapper mapper = new ObjectMapper();
    private static final String AWS_SECRET_NAME = "DeploymentPOC";
    private static final String AWS_REGION = "ap-south-1";
    private static final String SPRING_DATASOURCE_USERNAME = "spring.datasource.username";
    private static final String SPRING_DATASOURCE_PASSCODE = "spring.datasource.password";
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        /***
         *Get user name and password from AWS Secret Manager using secret name
         */
        String secretJson = getSecret();
        String username = getString(secretJson, SPRING_DATASOURCE_USERNAME);
        String password = getString(secretJson, SPRING_DATASOURCE_PASSCODE);
        ConfigurableEnvironment environment = event.getApplicationContext().getEnvironment();
        Properties props = new Properties();
        props.put(SPRING_DATASOURCE_USERNAME, username);
        props.put(SPRING_DATASOURCE_PASSCODE, password);
        environment.getPropertySources().addFirst(new PropertiesPropertySource("aws.secret.manager", props));
    }
    private String getSecret() {
        // Creating a Secrets Manager client
        AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard().withRegion(AWS_REGION).build();
        String secret = null;
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(AWS_SECRET_NAME);
        GetSecretValueResult getSecretValueResult = null;
        try {
            /**
             * Decrypts secret using the associated KMS CMK
             */
            getSecretValueResult = client.getSecretValue(getSecretValueRequest);
            /***
             * Depending on whether the secret is a string or binary, one of these fields  will be populated.
             */
            if (getSecretValueResult != null && getSecretValueResult.getSecretString() != null) {
                secret = getSecretValueResult.getSecretString();
            }
        } catch (DecryptionFailureException | InternalServiceErrorException | InvalidParameterException
                | InvalidRequestException | ResourceNotFoundException e) {

            return null;
        }
        return secret;
    }
    private String getString(String json, String path) {
        try {
            JsonNode root = mapper.readTree(json);
            return root.path(path).asText();
        } catch (IOException e) {

            return null;
        }
    }
}