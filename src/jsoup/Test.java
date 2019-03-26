package jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Test {
	private static Map<String, String> cookies = null;
	
	public static void main(String[] args) throws IOException {
//    	String url = "home=1&dbpre=Usr&fieldlist%5B0%5D.value=&fieldlist%5B0%5D.viewvalue=&fieldlist%5B1%5D.value=&fieldlist%5B2%5D.value=02447&fieldlist%5B3%5D.value=&fieldlist%5B3%5D.viewvalue=&fieldlist%5B4%5D.value=&fieldlist%5B4%5D.viewvalue=&fieldlist%5B5%5D.value=&fieldlist%5B5%5D.viewvalue=&a0111=on&fieldlist%5B6%5D.value=&fieldlist%5B6%5D.viewvalue=&fieldlist%5B7%5D.value=&fieldlist%5B7%5D.viewvalue=&fieldlist%5B8%5D.value=&fieldlist%5B8%5D.viewvalue=&fieldlist%5B9%5D.value=&fieldlist%5B9%5D.viewvalue=&b_mquery=%B2%E9%D1%AF";
//    	Connection.Response res = Jsoup.connect("http://10.1.1.13:8080/templates/index/hrlogon.do")
//    			.data("logon.x","link","username", "%C8%CB%CA%C2%D7%A8%D4%B1", "password", "r8692712")
//    			.method(Method.POST)
//    			.execute();
//
//    			Document doc = res.parse();
//    			System.err.println(doc);
//    			//这儿的SESSIONID需要根据要登录的目标网站设置的session Cookie名字而定
//    			String sessionId = res.cookie("JSESSIONID"); 
//    			
//    			Document objectDoc = Jsoup.connect("http://10.1.1.13:8080/workbench/browse/showselfinfo.do?b_search=link&home=1&userbase=Usr&a0100=00000178&flag=notself&returnvalue=3")
//    			.cookie("JSESSIONID", sessionId)
//    			.get();
//    			
//    			System.err.println(objectDoc);

//    	Document doc = Jsoup.connect("http://10.1.1.13:8080/workbench/query/query_interface.do?home=1&dbpre=Usr&fieldlist%5B0%5D.value=&fieldlist%5B0%5D.viewvalue=&fieldlist%5B1%5D.value=&fieldlist%5B2%5D.value=02447&fieldlist%5B3%5D.value=&fieldlist%5B3%5D.viewvalue=&fieldlist%5B4%5D.value=&fieldlist%5B4%5D.viewvalue=&fieldlist%5B5%5D.value=&fieldlist%5B5%5D.viewvalue=&a0111=on&fieldlist%5B6%5D.value=&fieldlist%5B6%5D.viewvalue=&fieldlist%5B7%5D.value=&fieldlist%5B7%5D.viewvalue=&fieldlist%5B8%5D.value=&fieldlist%5B8%5D.viewvalue=&fieldlist%5B9%5D.value=&fieldlist%5B9%5D.viewvalue=&b_mquery=%B2%E9%D1%AF").get();
//    	System.err.println(doc);
		run();
		
		
	
	}

	public static void run1() throws IOException {
		Document doc = Jsoup.connect("http://10.1.1.13:8080/workbench/query/query_interface.do?fieldlist%5B2%5D.value=02447&b_mquery=%B2%E9%D1%AF")
				.cookie("JSESSIONID", "CC40E5696D04734CF56DDB5FC43D332D")
				.get();
		
//		System.err.println(doc);
		
//		Document doc1 = Jsoup.connect("http://10.1.1.13:8080/workbench/browse/showselfinfo.do?b_search=link&home=1&userbase=Usr&a0100=00000178&flag=notself&returnvalue=3")
//				.cookie("JSESSIONID", "CC40E5696D04734CF56DDB5FC43D332D")
//				.get();
//		System.err.println(doc1);
		
		Document doc1 = Jsoup.connect("http://10.1.1.13:8080/workbench/browse/employeesettree.jsp")
				.cookie("JSESSIONID", "CC40E5696D04734CF56DDB5FC43D332D")
				.get();
		System.err.println(doc1);
		
	}
	
	
	/**
	 * 
	 */
	public static void run() throws IOException {
//    	Connection conn =  Jsoup.connect("http://10.1.1.13:8080/templates/index/hrlogon.do?logon.x=link&username=%C8%CB%CA%C2%D7%A8%D4%B1&password=R8692712");
		String urlLogin = "http://10.1.1.13:8080/templates/index/hrlogon.do";
		Connection connect = Jsoup.connect(urlLogin);

		// 伪造请求头
		connect.header("Accept", "application/json, text/javascript, */*; q=0.01").header("Accept-Encoding",
				"gzip, deflate");
		connect.header("Accept-Language", "zh-CN,zh;q=0.9").header("Connection", "keep-alive");
		connect.header("Content-Length", "72").header("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		connect.header("Host", "qiaoliqiang.cn").header("Referer", "http://qiaoliqiang.cn/Exam/");
		connect.header("User-Agent",
				"Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
				.header("X-Requested-With", "XMLHttpRequest");
		
		
		 connect.data("username", "人事专员").data("password", "r8692712").data("login.x", "link")
         .data("isRememberme", "yes");
		 
		//请求url获取响应信息
	        Response res = connect.ignoreContentType(true).method(Method.POST).execute();// 执行请求
	        // 获取返回的cookie
	        cookies = res.cookies();
	        
	        for (Entry<String, String> entry : cookies.entrySet()) {
	            System.out.println(entry.getKey() + "-" + entry.getValue());
	        }
	        
	        
//		String sessionId = res.cookie("JSESSIONID"); 

    	String queryUrl = "http://10.1.1.13:8080/workbench/query/query_interface.do";
    	Document document = Jsoup.connect(queryUrl)
    			.data("home","1")
    			.data("dbpre","Usr")
    			.data("fieldlist%5B2%5D.value","02447")
    			.data("a0111","on")
    			.data("b_mquery","%B2%E9%D1%AF")
    			.cookies(cookies)
    			.post();
        System.out.println(document.toString());
	}
}
