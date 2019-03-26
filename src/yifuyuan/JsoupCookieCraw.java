package yifuyuan;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import word.MDoc;

/**
 * ģ��Jsoup������½��������
 * 
 * @author 
 *
 */
public class JsoupCookieCraw {
//    private static Map<String, String> cookies = null;
    public static Map<String, String> cookies = new HashMap<>();
    

    
    public static void main(String[] args) throws IOException {
        // ��ģ���¼��ȡ��cookie��sessionid���浽ȫ�ֱ���cookies��
//         login();
        
    	findByWorkId("00207");
    	
//    	Document document2 = Jsoup.connect("http://www.cdyfy.com:8080/templates/close.jsp")
//          		.cookies(cookies)
//          		.get();
//    	System.err.println(document2);

    	
    	
    }
    
    

    /**
     * ģ���¼��ȡcookie��sessionid
     * @throws IOException 
     */
    public static void login() throws IOException  {
        String urlLogin = "http://www.cdyfy.com:8080/templates/index/hrlogon.do?logon.x=link&username=%C8%CB%CA%C2%D7%A8%D4%B1&password=r8692712";
//        String urlLogin = "http://www.cdyfy.com:8080/templates/index/hrlogon.do";
//        String urlLogin = "http://219.229.252.135/reader/login.php";
        Connection connect = Jsoup.connect(urlLogin);
        // α������ͷ
        
        connect.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        connect.header("Accept-Encoding", "gzip, deflate");
        connect.header("Accept-Language", "zh,en-US;q=0.9,en;q=0.8,zh-CN;q=0.7");
        connect.header("Cache-Control", "no-cache");
        connect.header("Connection", "keep-alive");
//        connect.header("Content-Length", "86");
        connect.header("Content-Type", "application/x-www-form-urlencoded");
        connect.header("Host", "www.cdyfy.com:8080");
        connect.header("Pragma", "no-cache");
        connect.header("Referer", "http://www.cdyfy.com:8080/templates/index/hrlogon.jsp");
        connect.header("Upgrade-Insecure-Requests", "1");
        connect.header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");
        // Я����½��Ϣ
//        connect.data("logon.x","link","username", "����רԱ","password", "r8692712");
        
        //����url��ȡ��Ӧ��Ϣ
        Response res = connect.method(Method.POST).execute();// ִ������
        // ��ȡ���ص�cookie
        cookies = res.cookies();
        for (Entry<String, String> entry : cookies.entrySet()) {
            System.err.println(entry.getKey() + "-" + entry.getValue());
        }
        System.out.println(res.statusCode());
        
        System.err.println(res.body().toString());
        
        write(cookies.get("JSESSIONID"));
        
       
//        return cookies.get("JSESSIONID");
    }
    
    
    
    
    public static boolean findByWorkId(String gh) throws IOException {
    	String jessId = read();

      cookies.put("JSESSIONID", jessId);
//      String url = "http://www.cdyfy.com:8080/workbench/query/hquery_interface.do?returnvalue=dxt&a_query=1&b_query=link&a_inforkind=1&home=1";
      String selecturl = "http://www.cdyfy.com:8080/workbench/query/query_interface.do";
      String selecturl1 = "http://www.cdyfy.com:8080/workbench/query/query_interface.do?returnvalue=dxt&b_query=link&a_inforkind=1&home=1";
      
      Scanner in = new Scanner(System.in);
//      System.err.println("������Ҫ��ѯ�˵Ĺ���");
//      String gh = in.nextLine();
      
      //������ٲ�ѯ-�����иò����������޷���ѯ
      Document document1 = Jsoup.connect(selecturl1)
      		.cookies(cookies)
//      		.data("returnvalue","dxt","b_query","link","a_inforkind","1","home","1")
      		.get();
//      System.out.println(document1.toString());
      
      // ֱ�ӻ�ȡDOM��������cookiesȥ��ȡ
      Document document2 = Jsoup.connect(selecturl)
      		.cookies(cookies)
      		.data("fieldlist[2].value",gh,"a0111","on","dbpre","Usr").data("home", "1").data("b_mquery", "��ѯ")
      		.post();
//      System.err.println(document2.toString());
      String allInfoUrl = browBook(document2);
      System.out.println("allInfoUrl----"+allInfoUrl);
      System.err.println(allInfoUrl.contains("http"));
      if(allInfoUrl.contains("a0100")){
      	System.err.println("�ҵ��ù���");
      	//�����Ϣ���ٵ�����ֿɲ�ѯ��ϸ��Ϣ
      	Document document3 = Jsoup.connect(allInfoUrl)
      			.cookies(cookies)
      			.get();
//      	System.out.println(document3.toString());
      	
      	//��ϸ��Ϣ
      	String allInfoUrl2 = "http://www.cdyfy.com:8080/workbench/browse/browseinfo.jsp";
      	Document document4 = Jsoup.connect(allInfoUrl2)
      			.cookies(cookies)
      			.get();
//      	System.err.println(document4.toString());
      	Elements es =  document4.getElementsByTag("input");
      	for(Element e :es){
      		System.err.println(e.attr("value"));
      	}
      	System.out.println("������"+es.get(5).attr("value"));
      	System.out.println("�ù���ʽ��"+es.get(6).attr("value"));
      	System.out.println("���֤�ţ�"+es.get(13).attr("value"));
      	System.out.println("����Ժʱ�䣺"+es.get(17).attr("value"));
      	String comeTime = es.get(17).attr("value");
      	
      	Map<String, Object> dataMap = new HashMap<String, Object>();
  		dataMap.put("name",es.get(5).attr("value"));
  		dataMap.put("yong", es.get(6).attr("value"));
  		dataMap.put("id", es.get(13).attr("value"));
  		//��������
  		dataMap.put("year", es.get(14).attr("value").substring(0, 4));
  		dataMap.put("month", es.get(14).attr("value").substring(5, 7));
  		
  		dataMap.put("comeYear",comeTime.substring(0, 4));
  		dataMap.put("comeMonth",comeTime.substring(5, 7) );
  		
  		dataMap.put("nowYear", "2019");
  		dataMap.put("nowMonth", 3);
  		dataMap.put("nowDay", 25);
  		
  		MDoc mdoc = new MDoc();
  		mdoc.createDoc(dataMap, "D:/javaWordTest/"+gh+es.get(5).attr("value")+".doc");
  		System.out.println("ģ�����ɳɹ�");
      	return true;
      }else{
      	System.err.println("δ�ҵ��ù���");
      	return false;
      }
    }
    
    
    
    //��ȡ
   public static String read() throws IOException {
	
   		FileReader fd = new FileReader("src/jess.txt");
   		BufferedReader br = new BufferedReader(fd);
   		String s = br.readLine();
   		System.err.println(s);
		fd.close();
		return s;
		
   }
   
	//д��
   public static void write(String jessId) throws IOException {
  
   	FileWriter aw=new FileWriter("src/jess.txt");
   	aw.write(jessId);
   	aw.close();
   }
    
    
    
    
    
    public static String browBook(Document d) throws IOException{
    	String a = d.getElementsByTag("a").first().attr("onclick");
    	System.out.println("a----"+a);
    	String[] as = a.split("'");
    	String url = as[1];
    	System.out.println("as1----"+as[1]);
    	return "http://www.cdyfy.com:8080"+as[1];
    }
    

}

