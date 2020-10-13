package activemq.test.p01.topic;

import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;

import org.apache.activemq.ActiveMQConnectionFactory;

public class GetMessagesUsingListener {

	static String userName = "admin";
	static String password = "admin";
	static String brokerURL = "tcp://127.0.0.1:61616";

	public static void main(String[] args) {
		TopicConnection topicConnection = null;
		TopicSession topicSession = null;
		try {
			TopicConnectionFactory topicConnectionFactory = new ActiveMQConnectionFactory(userName, password,
					brokerURL);
			topicConnection = topicConnectionFactory.createTopicConnection();
			topicConnection.setClientID("sa2");
			topicConnection.start();

			topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

			Destination destination = topicSession.createTopic("静夜思");
			MessageConsumer messageConsumer = topicSession.createConsumer(destination);
			for (;;) {
				messageConsumer.setMessageListener(new MessageListener() {
					@Override
					public void onMessage(Message message) {
						try {
							System.out.println(((TextMessage) message).getText());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
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
