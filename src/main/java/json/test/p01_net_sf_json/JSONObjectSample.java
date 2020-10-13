package json.test.p01_net_sf_json;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class JSONObjectSample {

    public static void main(String[] args) {
        createJsonByMap();
    }

    private static void createJsonByMap() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("name", "John");
        data.put("sex", "male");
        data.put("age", 22);
        data.put("is_student", true);
        data.put("hobbies", new String[] {"hiking", "swimming"});
        
        JSONObject obj = JSONObject.fromObject(data);
        System.out.println(obj.toString());
    }

}