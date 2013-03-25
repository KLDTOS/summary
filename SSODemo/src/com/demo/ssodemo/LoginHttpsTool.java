package com.demo.ssodemo;

import java.io.InputStream;
import java.net.URI;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

import com.shenpu_mportal.service.bean.FactoryForHomeFirstBean;
import com.shenpu_mportal.service.bean.FactoryForResponseBean;
import com.tool.xmltool.ElementBean;
import com.tool.xmltool.ElementBeanFactory;
import com.tool.xmltool.XMLTool;

import android.content.Context;
import android.widget.Toast;

public class LoginHttpsTool {

	
	private Context c;
	HttpClient hc;
	List<ElementBean> responses;
	List<ElementBean> tests;
	
	public boolean doLogin(String userName, String userPwd , Context c) {

		this.c = c;
		System.out.println("doLogin");
		hc = new DefaultHttpClient(); 
		try {
			initKey();
		} catch (Exception e) {
			System.out.println("initKey error");
			e.printStackTrace();
		} 
		String url = "https://192.168.1.111:8443/ServiceDemoFor_mPortal/LoginServlet?&userName="
				+ userName + "&&passWord=" + userPwd;
		String url2 = "https://192.168.1.110:8443/ServiceDemoFor_mPortal/LoginServlet";
		String url3 = "http://http://192.168.1.110/xampp/";
//		try {
//			Toast.makeText(c, getData(url), Toast.LENGTH_LONG).show();
//			System.out.println(getData(url));
//		} catch (Exception e) {
//			System.out.println("getData error");
//			e.printStackTrace();
//		} 
		System.out.println(url);
		InputStream input = null;
		try {
			input = getData(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (input == null) {
			return false;
		}
		
		
		ArrayList<ElementBeanFactory> factorys  = new ArrayList<ElementBeanFactory>();
		factorys.add(new FactoryForHomeFirstBean());
		factorys.add(new FactoryForResponseBean());
		
		responses = new XMLTool(1, "ResponseBean", factorys)
				.resolution(input);
		//	tests = new XMLTool(2, "mPortal",
		//			new FactoryForHomeFirstBean()).resolution(getData(url));
	
		
		System.out.println("responsesNum" + responses.size());
//		if(responses.size() > 0){
//			return true;
//		}
		
		
		for (int i = responses.size(); i > 0; i--) {
			System.out.println(" " + responses.get(i - 1).getChildsNum());
			System.out.println(" " + responses.get(i - 1).getValue(0));
			System.out.println(" " + responses.get(i - 1).getName(0));
			
			
			
			if (("1").equals(responses.get(i - 1).getValue("isUser"))) {
				return true;
			}
		}
		
		

		
		
		return false;
	}

	
	private void initKey() throws Exception { 
		KeyStore trustStore = KeyStore.getInstance("BKS"); 
		trustStore.load(c.getResources().getAssets().open("server_trist.keystore"),"121227".toCharArray());
		//trustStore.load(getBaseContext().getResources().openRawResource(R.raw.server_trust), "121227".toCharArray()); 

		SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore); 
	//	SSLSocketFactory socketFactorytest = new 
		EasySSLSocketFactory easySocketFactory = new EasySSLSocketFactory();
		
		Scheme sch = new Scheme("https", easySocketFactory, 8443); 
		hc.getConnectionManager().getSchemeRegistry().register(sch); 
	} 
	
	private InputStream getData(String url) throws Exception { 
//		System.out.println("111");
		URI uri = new URI(url);
		HttpUriRequest hr = new HttpGet(url);
//		System.out.println("222");
		HttpResponse hres = hc.execute(hr);
//		System.out.println("333");
		HttpEntity he = hres.getEntity();
		
		InputStream is = he.getContent();
		
		return is;
//		下面为输出成字符串 代码 ，仅用于开发时看效果。		
//		StringBuffer sb = new StringBuffer();
//		
//		byte[] bytes = new byte[1024];
//		
//		for (int len = 0; (len = is.read(bytes)) != -1;) { 
//			sb.append(new String(bytes, 0, len, "gb2312")); 
//		} 
//		
//		System.out.println("" + sb);
		
		
		
//		return sb.toString(); 
	} 
	
}
