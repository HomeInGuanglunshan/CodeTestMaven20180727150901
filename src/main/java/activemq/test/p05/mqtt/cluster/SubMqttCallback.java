package activemq.test.p05.mqtt.cluster;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * 消费者回调接口
 */
public class SubMqttCallback implements MqttCallback {

	Logger logger = Logger.getLogger(SubMqttCallback.class);

	private static List<String> list = new ArrayList<String>();
	//客户端ID  
	private String clientId;
	//客户端连接  
	private MqttClient sampleClient;

	static int count = 0;

	public SubMqttCallback() {

	}

	/**
	 * 执行任务出错或者断开连接会执行到的方法
	 */
	@Override
	public void connectionLost(Throwable cause) {
		System.out.println("自行恢复链接");
		//自行恢复链接  
		new Thread() {
			@Override
			public void run() {
				boolean flg = true;
				while (flg) {
					try {
						Thread.sleep(1000);
						MqttSubscribe client = new MqttSubscribe();
						//断开重连  
						client.setSampleClient(sampleClient);
						client.receive();
						//链接恢复退出重连  
						flg = false;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("连接断开尝试重连");
					}
				}
			}
		}.start();
	}

	/**
	 * 接收回调方法
	 */
	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		String messageStr = new String(message.getPayload());
		try {
			/**
			 * 执行任务块
			 */
			//测试异常
//          count++;
//          if(count >5){
//              count = count/0;
//          }  
			list.add(messageStr);

			System.out.println("  接收消息为:" + messageStr);
//          System.out.println("  条数为:"+list.size());  
		} catch (Exception e) {
			StringBuffer sb = new StringBuffer();
			sb.append(" pubId :");
			sb.append(clientId);
			sb.append(" ,任务内容:");
			sb.append(messageStr);
			sb.append(" ,订阅主题:");
			sb.append(topic);
			sb.append(" ,异常信息：");
			sb.append(e.getMessage());
			sb.append(topic);
			//将执行错误的任务保存起来  
			logger.error(sb);
		}

	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("deliveryComplete---------" + token.isComplete());
	}

	public MqttClient getSampleClient() {
		return sampleClient;
	}

	public void setSampleClient(MqttClient sampleClient) {
		this.sampleClient = sampleClient;
	}

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(SubMqttCallback.class);

		logger.error("测试！");
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

}