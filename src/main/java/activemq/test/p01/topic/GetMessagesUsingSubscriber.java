package activemq.test.p01.topic;

import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnectionFactory;

public class GetMessagesUsingSubscriber {

	static String userName = "admin";
	static String password = "admin";
//	static String brokerURL = "tcp://127.0.0.1:61616";
	static String brokerURL = "failover:(tcp://127.0.0.1:61616,tcp://127.0.0.1:62626,tcp://127.0.0.1:63636)";

	public static void main(String[] args) {
		TopicConnection topicConnection = null;
		TopicSession topicSession = null;
		try {
			TopicConnectionFactory topicConnectionFactory = new ActiveMQConnectionFactory(userName, password,
					brokerURL);
			topicConnection = topicConnectionFactory.createTopicConnection();
			topicConnection.setClientID("sa");
//			topicConnection.setClientID("ld");
			topicConnection.start();

			topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

			Topic topic = topicSession.createTopic("静夜思");

			TopicSubscriber topicSubscriber = topicSession.createDurableSubscriber(topic, "subscriberAlive");
//			TopicSubscriber topicSubscriber = topicSession.createDurableSubscriber(topic, "lida");
			for (;;) {
				TextMessage textMessage = (TextMessage) topicSubscriber.receive();
				System.out.println(textMessage.getText());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (topicSession != null) {
//					topicSession.commit();
					topicSession.close();
				}
				if (topicConnection != null) {
					topicConnection.close();
				}
			} catch (Exception e) {
			}
		}
	}
}
