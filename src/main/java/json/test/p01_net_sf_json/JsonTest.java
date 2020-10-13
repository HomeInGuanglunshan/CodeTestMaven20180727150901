package json.test.p01_net_sf_json;

import java.util.Iterator;

import net.sf.json.JSONObject;

public class JsonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSONObject jsonObj = JSONObject.fromObject("{\"hobbies\":[\"hiking\",\"swimming\"],\"sex\":\"male\",\"name\":\"John\",\"is_student\":true,\"age\":22}");
		System.out.println(jsonObj.toString());
		System.out.println(jsonObj.get("sex"));
		Iterator<String> it = jsonObj.keys();
		while (it.hasNext()) {
			String key = it.next();
			System.out.println(jsonObj.get(key));
		}
	}

}