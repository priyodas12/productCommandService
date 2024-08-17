package tech.springboot.productCommandService.service;


import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import tech.springboot.productCommandService.model.Product;
import tech.springboot.productCommandService.model.SqsEvent;
import tech.springboot.productCommandService.repository.ProductEventRepository;

@Service
@Log4j2
public class ProductEventService {

  @Value ("${aws.sqs.url}")
  private String sqsUrl;

  @Autowired
  private SqsClient sqsClient;

  @Autowired
  private ProductEventRepository productEventRepository;

  public void publishToSqs (Product product, String eventType) {
    ObjectMapper objectMapper = new ObjectMapper ();
    try {
      String jsonProductString = objectMapper.writeValueAsString (product);
      SqsEvent saveEvent = saveEvent (jsonProductString, eventType);
      String jsonEventString = objectMapper.writeValueAsString (saveEvent);
      log.info ("Sending to SQS: {}", jsonEventString);
      SendMessageRequest sendMsgRequest = SendMessageRequest.builder ()
          .queueUrl (sqsUrl)
          .messageBody (jsonEventString)
          .build ();
      sqsClient.sendMessage (sendMsgRequest);
    } catch (JsonProcessingException e) {
      log.warn ("Error while serializing object: " + e.getMessage ());
      throw new RuntimeException (e.getMessage ());
    } catch (Exception e) {
      log.warn ("Error while sending object to sqs: " + e.getMessage ());
      throw new RuntimeException (e.getMessage ());
    }
  }

  public SqsEvent saveEvent (String product, String eventType) {
    SqsEvent sqsEvent = new SqsEvent ();
    sqsEvent.setEventId (UUID.randomUUID ());
    sqsEvent.setEventType (eventType);
    sqsEvent.setEventPayload (product);
    sqsEvent.setCreate_timestamp (new Date ());
    productEventRepository.save (sqsEvent);
    return sqsEvent;
  }
}
