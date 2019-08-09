package com.intuit.pulsarClient;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;


public class PulsarConsumer {
	
	public static void main(String[] args) throws PulsarClientException {
		Producer<String> stringProducer = null;
		PulsarClient client = null;
		try {
			 client = PulsarClient.builder()
			        .serviceUrl("pulsar://localhost:6650")
			        .build();
			
			 Consumer<?> consumer = client.newConsumer()
				        .topic("my-topic")
				        .subscriptionName("my-subscription")
				        .subscribe();
			 
			 
			 while (true) {
				  // Wait for a message
				  Message msg = consumer.receive();

				  try {
				      // Do something with the message
				      System.out.println("Message received "+ new String(msg.getData()));

				      // Acknowledge the message so that it can be deleted by the message broker
				      consumer.acknowledge(msg);
				  } catch (Exception e) {
				      // Message failed to process, redeliver later
				      consumer.negativeAcknowledge(msg);
				  }
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
