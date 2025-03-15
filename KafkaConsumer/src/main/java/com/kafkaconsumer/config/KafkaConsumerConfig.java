package com.kafkaconsumer.config;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.kafkaconsumer.dto.KafkaRequestDto;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, KafkaRequestDto> kafkaConsumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group-1");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        JsonDeserializer<KafkaRequestDto> deserializer = new JsonDeserializer<>(KafkaRequestDto.class);
        deserializer.setRemoveTypeHeaders(true);
        deserializer.addTrustedPackages("*"); // Allow all packages for deserialization
        deserializer.ignoreTypeHeaders();     // Prevents Kafka from requiring type headers

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
    }

    @Bean
    public KafkaListenerContainerFactory<?> kafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, KafkaRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaConsumerFactory());
        return factory;
    }
}
