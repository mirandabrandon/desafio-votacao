package com.brandon.desafio_tecnico_nt.service;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageQueueService {

    private final SqsClient sqsClient;

    @Value("${aws.sqs.queue.url}")
    private String queueUrl;

    public MessageQueueService(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public void sendVotingResult(String message) {
        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(message)
                .build();
        sqsClient.sendMessage(sendMessageRequest);
    }
}

