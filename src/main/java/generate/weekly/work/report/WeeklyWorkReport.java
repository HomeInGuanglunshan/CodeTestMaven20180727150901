package generate.weekly.work.report;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class WeeklyWorkReport {

	public static void login() throws Exception {

		CloseableHttpClient client = HttpClients.createDefault();

//		String url = "http://192.168.0.249/Users/UserHome/Index";
//		String url = "http://192.168.0.249/Home/Login?ReturnUrl=%2fUsers%2fUserHome%2fIndex";
		String url = "http://192.168.0.249/MvcPages/Home/Login";

		HttpPost httpPost = new HttpPost(url);

		// 参数形式为key=value&key=value
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("CAPassword", ""));
		formparams.add(new BasicNameValuePair("Password", "火狐"));
		formparams.add(new BasicNameValuePair("UserId", "lida"));
		formparams.add(new BasicNameValuePair("__RequestVerificationToken", "火狐"));
		formparams.add(new BasicNameValuePair("loginType", "CA"));
		formparams.add(new BasicNameValuePair("rememberMe", "true"));

		// 加utf-8进行编码
		UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
		httpPost.setEntity(uefEntity);

		CloseableHttpResponse response = client.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		System.out.println(result);
	}

	
	public static String getRequestVerificationToken() throws Exception {
		
		CloseableHttpClient client = HttpClients.createDefault();
		String url = "http://192.168.0.249/Home/Login?ReturnUrl=%2fUsers%2fUserHome%2fIndex";
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = client.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		
		String requestVerificationToken = "";
		String regex = "<input\\s+name=\"__RequestVerificationToken\"\\s+type=\"hidden\"\\s+value=\"(.+?(?=\"))\"\\s*/>";
		Matcher m = Pattern.compile(regex).matcher(result);
		while (m.find()) {
			requestVerificationToken = m.group(1);
			break;
		}
		
		return requestVerificationToken;
	}
	
	public static String getEncryptedPassword() throws Exception {
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine se = sem.getEngineByExtension("js");
		se.eval(new FileReader("D:/project/CodeTestMaven20180727150901/src/main/java/regex/text/p01_match_token/jsencrypt.min.js"));
		String s = (String) se.eval("eval(\"encode32(bin216(Base32.encrypt('1111', 'ODQ5OTkw')))\")");
		System.out.println(s);
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		getEncryptedPassword();
	}
}
