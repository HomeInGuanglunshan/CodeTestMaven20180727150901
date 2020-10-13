package activemq.test.p03.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class SendMessages {

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

			Queue queue = session.createQueue("numbers");

			MessageProducer messageProducer = session.createProducer(queue);

			for (int i = 0; i < 100; i++) {

				TextMessage textMessage = session.createTextMessage();
				textMessage.setText(String.valueOf(i));

				messageProducer.send(textMessage);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (session != null) {
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
