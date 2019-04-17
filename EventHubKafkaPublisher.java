package com.sams.clubops.exitapp.transactionbuilder.integrations.eh.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.sams.clubops.exitapp.transactionbuilder.beans.client.TransactionDetails;

@Service
public class EventHubKafkaPublisher {

    private static final Logger LOG = LoggerFactory.getLogger(EventHubKafkaPublisher.class);

    @Autowired
    private KafkaTemplate<String, TransactionDetails> kafkaTemplate;

    @Value("${app.topic.foo}")
    private String topic;

    public void send(TransactionDetails transactionDetails){
    	/** ADD BACK LATER
        LoggerUtil.info("-----sending tcNumber=" + transactionDetails.getData().getTransactionByTcNumber().getTcNumber()+ " to topic=" + topic);
        LoggerUtil.info("----eventhub send time ="+ DateTimeUtility.getCurrentTime()  );
        LoggerUtil.info("----eventhub send TcNumber ="+transactionDetails.getTcNumber());
        **/
        transactionDetails.setCurrentTime(System.currentTimeMillis());
        kafkaTemplate.send(topic,transactionDetails);
    }
}
