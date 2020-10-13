package activemq.test.p02.queue;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
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

			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

			Queue queue = session.createQueue("江雪");

			MessageProducer messageProducer = session.createProducer(queue);
//			messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);

			TextMessage textMessage = session.createTextMessage();
			textMessage.setText("千山鸟飞绝，万径人踪灭。孤舟蓑笠翁，独钓寒江雪。written on "
					+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

			messageProducer.send(textMessage);

			session.commit();
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
