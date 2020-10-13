package activemq.test.p02.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class GetMessagesUsingListener {

	static String userName = "admin";
	static String password = "admin";
	static String brokerURL = "tcp://127.0.0.1:61616";

	public static void main(String[] args) {
		Connection connection = null;
		Session session = null;
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(userName, password, brokerURL);
			connection = connectionFactory.createConnection();
			connection.start();

			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Queue queue = session.createQueue("江雪");
			MessageConsumer messageConsumer = session.createConsumer(queue);
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

			System.in.read();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (session != null) {
//					Session.commit();
					session.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
			}
		}
	}
}
