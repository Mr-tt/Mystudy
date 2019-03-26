package word;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map;
 
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
 
public class MDoc {
 
	private Configuration configuration = null;
 
	public MDoc() {
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
	}
 
	public void createDoc(Map<String, Object> dataMap, String fileName) throws UnsupportedEncodingException {
		// dataMap Ҫ����ģ���������ļ�
		// ����ģ��װ�÷�����·��,FreeMarker֧�ֶ���ģ��װ�ط�����������servlet��classpath�����ݿ�װ�أ�
		// �������ǵ�ģ���Ƿ���com.havenliu.document.template������
//		configuration.setClassForTemplateLoading(this.getClass(), "C:\\Users\\Administrator\\Desktop\\");
		configuration.setClassForTemplateLoading(this.getClass(), "/word");
		Template t = null;
		try {
			// test.ftlΪҪװ�ص�ģ��
			t = configuration.getTemplate("֤��.ftl");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ����ĵ�·��������
		File outFile = new File(fileName);
		Writer out = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(outFile);
			OutputStreamWriter oWriter = new OutputStreamWriter(fos, "UTF-8");
			// ����ط������ı��벻�ɻ�ȱ��ʹ��main������������ʱ��Ӧ�ÿ��ԣ����������web���󵼳�ʱ������word�ĵ��ͻ�򲻿������Ұ�XML�ļ�������Ҫ�Ǳ����ʽ����ȷ���޷�������
			// out = new BufferedWriter(new OutputStreamWriter(new
			// FileOutputStream(outFile)));
			out = new BufferedWriter(oWriter);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
 
		try {
			t.process(dataMap, out);
			out.close();
			fos.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
