package activemq.test.p01.topic;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.DeliveryMode;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

import org.apache.activemq.ActiveMQConnectionFactory;

public class SendMessages {

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
			topicConnection.start();

			topicSession = topicConnection.createTopicSession(true, Session.AUTO_ACKNOWLEDGE);

			Topic topic = topicSession.createTopic("静夜思");

			TopicPublisher topicPublisher = topicSession.createPublisher(topic);
//			topicPublisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//			如果不为DeliveryMode.PERSISTENT，则持久化订阅者，接收不到开始订阅之前，发布者推送的消息
			topicPublisher.setDeliveryMode(DeliveryMode.PERSISTENT);

			TextMessage textMessage = topicSession.createTextMessage();
			textMessage.setText("床前明月光，疑是地上霜。举头望明月，低头思故乡。written on "
					+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

			topicPublisher.send(textMessage);

			topicSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (topicSession != null) {
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
