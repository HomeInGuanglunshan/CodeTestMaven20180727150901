package activemq.test.p04.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

/**
 * 生产者
 * 
 */
public class MqttPublisher extends MqttBase {

	/**
	 * 构建生产者链接
	 */
	MqttPublisher() {
		try {
			//本地化信息
//          MqttClientPersistence dsSubscriberA = new MqttDefaultFilePersistence("F:\\mqttFile\\subscriberA");  
			//创建mqtt链接  
			sampleClient = new MqttClient(BROKER, "Publisher");
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送消息任务
	 * 
	 * @param topic
	 *            订阅主题名称
	 * @param str
	 *            消息内容
	 */
	public void sendMessage(String topic, String str) throws MqttSecurityException, MqttException {
		//mqtt配置  
		MqttConnectOptions connOpts = new MqttConnectOptions();
		//设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接  
		connOpts.setCleanSession(false);
		//mqtt回调对象  
		PubMqttCallback callback = new PubMqttCallback();
		//设置客户端回调方式  
		sampleClient.setCallback(callback);
//       String haURIs[] = new String[]{"tcp://192.168.56.3:61666","tcp://192.168.56.4:61666"};
//       //集群高可用配置
//       connOpts.setServerURIs(haURIs);  
		//链接boker  
		sampleClient.connect(connOpts);
		//拼接格式ID=消息体  
		StringBuffer sb = new StringBuffer();
//         sb.append(id);
//         sb.append("=");  
		sb.append(str + "  ");
		System.out.println(sb);
		MqttMessage message = new MqttMessage(sb.toString().getBytes());

		//设置消息类型  
		message.setQos(QOS);
		//将一条消息发布到服务器上的一个主题中  
		sampleClient.publish("Queues", message);
//         System.out.println(message.getId()+"   "+sb);  
		//从服务器断开连接0  
		sampleClient.disconnect();
	}

	public static void main(String[] args) throws MqttSecurityException, MqttException {
		final MqttPublisher pub = new MqttPublisher();

		for (int i = 0; i < 50; i++) {
			pub.sendMessage("mark", i + "");
		}

	}

}