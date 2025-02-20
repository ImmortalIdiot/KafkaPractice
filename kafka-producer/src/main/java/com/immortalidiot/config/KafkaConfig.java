package com.immortalidiot.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${topic-request.name}")
    private String TOPIC_REQUEST_NAME;

    @Value("${topic-response.name}")
    private String TOPIC_RESPONSE_NAME;

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> properties = kafkaProperties.buildProducerProperties();
        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic topicRequest() {
        return TopicBuilder
                .name(TOPIC_REQUEST_NAME)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topicResponse() {
        return TopicBuilder
                .name(TOPIC_RESPONSE_NAME)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
