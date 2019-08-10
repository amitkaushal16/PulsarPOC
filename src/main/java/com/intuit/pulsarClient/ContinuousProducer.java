package com.intuit.pulsarClient;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;

public class ContinuousProducer {

	public static void main(String[] args) throws PulsarClientException {

		PulsarClient pulsarClient = PulsarClient.builder().serviceUrl("pulsar://localhost:6650").build();

		Producer<String> producer = pulsarClient.newProducer(Schema.STRING).topic("my-topic").create();

		while (true) {
			try {
				
				producer.send("my-message");
				System.out.println("Message send successfully");
				Thread.sleep(10000);

			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}

		pulsarClient.close();

	}
}