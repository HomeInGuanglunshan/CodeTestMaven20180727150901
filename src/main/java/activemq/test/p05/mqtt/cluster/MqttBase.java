package activemq.test.p05.mqtt.cluster;

import org.eclipse.paho.client.mqttv3.MqttClient;

/**
 * https://umbrellall1.iteye.com/blog/2343317
 */
public class MqttBase {

	MqttClient sampleClient;

	String BROKER = "tcp://127.0.0.1:1883";

	Integer QOS = 0;

	public void setSampleClient(MqttClient sampleClient) {
		this.sampleClient = sampleClient;
	}
}
