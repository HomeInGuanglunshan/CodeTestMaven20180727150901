package json.test.p03_json_to_object;

import com.alibaba.fastjson.JSONObject;

public class MainClass {

	public static void main(String[] args) {
//		String params = "{\"name\" : \"kk\", \"dateStr\" : \"2018-9-11\"}"; // �������벹��0
//		String params = "{\"name\" : \"kk\", \"dateStr\" : \"2018-09-11\"}"; // ��ȷ
		String params = "{\"name\" : \"kk\", \"dateStr\" : \"2018-09-11 16:28:18\"}";
		JSONObject jsonObj = JSONObject.parseObject(params);
		Target target = (Target) JSONObject.toJavaObject(jsonObj, Target.class);
		System.out.println(target.getName());
		System.out.println(target.getDateStr());
	}
}
