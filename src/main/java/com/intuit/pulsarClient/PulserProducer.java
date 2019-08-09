package com.intuit.pulsarClient;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;

public class PulserProducer {
	
	public static void main(String[] args) throws PulsarClientException {
		Producer<String> stringProducer = null;
		PulsarClient client = null;
		try {
			 client = PulsarClient.builder()
			        .serviceUrl("pulsar://localhost:6650")
			        .build();
			
			stringProducer = client.newProducer(Schema.STRING)
			        .topic("my-topic")
			        .create();
			for(int i =0; i < 100 ; i++) {
				System.out.println("Send message ==" + i);
			stringProducer.send("My First Message Amit" + i);
			}
		} catch (PulsarClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			stringProducer.close();
			//consumer.close();
			client.close();
		}
	}

}