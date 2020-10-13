package activemq.test.p04.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * 消费者
 * 
 */
public class MqttSubscribe extends MqttBase {
	//客户端Id  
	String clientId = "";

	/**
	 * 构建消费者链接
	 */
	public MqttSubscribe() {
		try {
			//判断客户端链接是否已经建立  
			if (sampleClient == null) {
				//生成客户端唯一ID
//              UUID uuid = UUID.randomUUID();  
				String clientId = "sub1";
				//持久化
//              MqttClientPersistence dsSubscriberA = new MqttDefaultFilePersistence("F:\\mqttFile\\sub1");
//              链接MQTT服务  
				sampleClient = new MqttClient(BROKER, clientId);
				//链接MQTT服务
//              sampleClient = new MqttClient(BROKER, clientId);  
			}
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 接受消息方法
	 */
	public void receive() throws MqttException {
		//配置项设置  
		MqttConnectOptions connOpts = new MqttConnectOptions();
		//设置会话  
		connOpts.setCleanSession(false);
		//mqtt回调对象  
		SubMqttCallback callback = new SubMqttCallback();
		//设置客户端回调方式  
		sampleClient.setCallback(callback);
		//传递客户端连接  
		callback.setSampleClient(sampleClient);
		//传递客户端Id  
		callback.setClientId("Publisher");
		//链接客户端  
		sampleClient.connect(connOpts);
		//订阅主题416  
		sampleClient.subscribe("Queues", 2);
		//退订
//        sampleClient.unsubscribe("Queues");  
	}

	public static void main(String[] args) throws MqttException {
		new MqttSubscribe().receive();

	}

}