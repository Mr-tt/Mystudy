package jsoup;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
 
/**
 * @since      crawler(datasnatch) version(1.0)</br>
 * @author     bluetata / sekito.lv@gmail.com</br>
 * @reference  http://bluetata.blog.csdn.net/</br>
 * @version    1.0</br>
 * @update     03/14/2019 16:00
 */
public class GITHUBLoginApater {
 
    public static String LOGIN_URL = "http://10.1.1.13:8080/templates/index/hrlogon.jsp";
    public static String USER_AGENT = "User-Agent";
    public static String Content_Type = "text/html;charset=GBK";
    public static String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36";
    
    public static void main(String[] args) throws Exception {
 
        simulateLogin("����רԱ", "r8692712"); // ģ���½github���û���������
 
    }
 
    /**
     * @param userName �û���
     * @param pwd ����
     * @throws Exception
     */
    public static void simulateLogin(String userName, String pwd) throws Exception {
 
        /* 
         * ��һ������ 
         * grab login form page first
         * ��ȡ��½�ύ�ı���Ϣ�����޸����ύdata���ݣ�login��password��
         */
        // get the response, which we will post to the action URL(rs.cookies())
        Connection con = Jsoup.connect(LOGIN_URL);  // ��ȡconnection
        con.header(USER_AGENT, USER_AGENT_VALUE);   // ����ģ�������
        Response rs = con.execute();                // ��ȡ��Ӧ
        Document d1 = Jsoup.parse(rs.body());       // ת��ΪDom��
        
//        System.out.println(d1);
        
//        List<Element> eleList = d1.select("form");  // ��ȡ�ύform��������ͨ���鿴ҳ��Դ������֪
 
        // ��ȡcooking�ͱ�����
        // lets make data map containing all the parameters and its values found in the form
        Map<String, String> datas = new HashMap<>();
        
        // 01/24/2019 17:45 bluetata ���� -------------------------------------------------------------- Start ----------
        // GitHub��θİ���£����µ��ύrequest dataΪ:
        
        // authenticity_token   ll0RJnG1f9XDAaN1DxnyTDzCs+YXweEZWel9kGkq8TvXH83HjCwPG048sJ/VVjDA94YmbF0qvUgcJx8/IKlP8Q==
        // commit  Sign+in
        // login   bluetata
        // password    password123
        // utf8    ?
        
//        for(int i = 0; i < eleList.size(); i++) {
//        
//            for (Element e : eleList.get(i).getAllElements()) {
//                // �����û���
//                if (e.attr("name").equals("username")) {
//                    e.attr("value", userName);
//                }
//                // �����û�����
//                if (e.attr("name").equals("password")) {
//                    e.attr("value", pwd);
//                }
//                // �ų���ֵ������
//                if (e.attr("name").length() > 0) {
//                    datas.put(e.attr("name"), e.attr("value"));
//                    System.err.println(e.attr("name")+"-"+e.attr("value"));
//                }
//            }
//        }
 
        
        datas.put("logon.x", "link");
        datas.put("username", "%C8%CB%CA%C2%D7%A8%D4%B1");
        datas.put("password", "r8692712");
        datas.put("logon.x", "31");
        datas.put("logon.y", "10");
        
        /*
         * �ڶ���������post��ʽ�ύ�������Լ�cookie��Ϣ
         */
        String url = "http://10.1.1.13:8080/workbench/browse/showselfinfo.do?b_search=link&home=1&userbase=Usr&a0100=00000178&flag=notself&returnvalue=3";
        String submitUrl = "http://10.1.1.13:8080/templates/index/hrlogon.do";
        Connection con2 = Jsoup.connect(submitUrl);
        con2.header(USER_AGENT, USER_AGENT_VALUE);
        // ����cookie��post�����map����
        Response login = con2.ignoreContentType(true).followRedirects(true).method(Method.POST).data(datas).cookies(rs.cookies()).execute();
        // ��ӡ����½�ɹ������Ϣ
        System.out.println(login.body());
 
        // ��½�ɹ����cookie��Ϣ�����Ա��浽���أ��Ժ��½ʱ��ֻ��һ�ε�½����
        Map<String, String> map = login.cookies();
        for (String s : map.keySet()) {
            System.out.println(s + " : " + map.get(s));
        }
    }
 
}