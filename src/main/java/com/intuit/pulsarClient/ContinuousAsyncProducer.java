package com.intuit.pulsarClient;

import java.io.IOException;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;

public class ContinuousAsyncProducer {
    public static void main(String[] args) throws PulsarClientException, InterruptedException, IOException {
    	PulsarClient pulsarClient = PulsarClient.builder().serviceUrl("pulsar://localhost:6650").build();

		Producer<String> producer = pulsarClient.newProducer(Schema.STRING).topic("my-topic").create();

        while (true) {
            producer.sendAsync("My Async message");
            System.out.println("Async message send successfully");
        }
    }
}