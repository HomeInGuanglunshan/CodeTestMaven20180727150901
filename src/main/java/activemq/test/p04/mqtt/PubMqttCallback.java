package activemq.test.p04.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * 生产者回掉接口
 */
public class PubMqttCallback implements MqttCallback {
	static int i = 0;

	@Override
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub  

	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		i++;
		if (arg0.isComplete()) {//判断消息是否发送成功  

		}
	}

	@Override
	public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
		// TODO Auto-generated method stub  

	}

}