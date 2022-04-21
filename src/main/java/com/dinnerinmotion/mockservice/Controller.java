package com.dinnerinmotion.mockservice;

import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;

@RestController
public class Controller {
	private final Logger logger = LoggerFactory.getLogger(MockServiceApplication.class);

    @RetryableTopic(attempts = "5", backoff = @Backoff(delay = 2_000, maxDelay = 10_000, multiplier = 2))
	@KafkaListener(id = "mockGroup", topics = "mockTopic")
	public void listen(String in, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
					   @Header(KafkaHeaders.OFFSET) long offset) {

		this.logger.info("Received: {} from {} @ {}", in, topic, offset);
		if (in.startsWith("fail")) {
			throw new RuntimeException("failed");
		}
	}

	@DltHandler
	public void listenDlt(String in, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
						  @Header(KafkaHeaders.OFFSET) long offset) {

		this.logger.info("DLT Received: {} from {} @ {}", in, topic, offset);
	}
}
