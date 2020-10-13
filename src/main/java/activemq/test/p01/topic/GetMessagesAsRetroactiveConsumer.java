package activemq.test.p01.topic;

import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnectionFactory;

public class GetMessagesAsRetroactiveConsumer {

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
//			topicConnection.setClientID("sa");
			topicConnection.setClientID("retro");
			topicConnection.start();

			topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

//			回溯和持久化订阅不同，前者可以无限次订阅历史消息，后者只能订阅一次“开始订阅前就推送出来的消息”
//			需要在activemq.xml中设置subscriptionRecoveryPolicy，才能使用回溯功能。该policy在初始状态下是关闭的，即“noSubscriptionRecoveryPolicy”
			Topic topic = topicSession.createTopic("静夜思?consumer.retroactive=true");

			TopicSubscriber topicSubscriber = topicSession.createSubscriber(topic);
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
