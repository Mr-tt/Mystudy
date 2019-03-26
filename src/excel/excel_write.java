package excel;
import java.io.File;

import java.io.File;   
import java.io.FileInputStream;   
import java.io.InputStream;   
   
import jxl.Cell;    
import jxl.CellType;    
import jxl.Sheet;
import jxl.*;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class excel_write {
	public static void main(String[] args) {
		//�������飬�������ݣ����ñ���������������
		double[][] array = new double[10][10];
		
		//��Excel��
		jxl.Workbook readwb = null;
			
		try    		  
        {     
            //����Workbook����, ֻ��Workbook����    
            //ֱ�Ӵӱ����ļ�����Workbook    
            InputStream instream = new FileInputStream("C:/Users/mac/Desktop/test.xls");     
            readwb = Workbook.getWorkbook(instream);      
            //Sheet���±��Ǵ�0��ʼ    
            //��ȡ��һ��Sheet��    
            Sheet readsheet = readwb.getSheet(0);     
            //��ȡSheet������������������     
            int rsColumns = readsheet.getColumns();     
            //��ȡSheet������������������     
            int rsRows = readsheet.getRows();     
            //��ȡָ����Ԫ��Ķ�������     
            for (int i = 0; i < rsRows; i++)//�в���  
            {    
                for (int j = 0; j < rsColumns; j++)//��д��    
                {     
                    Cell cell = readsheet.getCell(j, i);//���ŵ�һ���������У��ڶ�������
                    NumberCell numc = (NumberCell)cell;
                    double value = numc.getValue();
                    array[i][j] = value;
                    
                    System.out.print(value + "   ");     
                }    
                System.out.println();    
            }
        }catch(Exception e){
    			System.out.println(e);
    		}//��һ��try���������������
		
		
		
		
		
		//д��excel��
		try{
			//���ļ�
			WritableWorkbook book = Workbook.createWorkbook(new File("C:/Users/mac/Desktop/test1.xls"));
			//������Ϊ����һҳ���Ĺ���������0��ʾ���ǵ�һҳ
			WritableSheet sheet = book.createSheet("��һҳ", 0);
			//���ڶ��е�һ��д������
			for(int i = 0; i < 10; ++i)//�в���
			{
				for(int j = 0; j < 10; ++j)//��д��
				{
					double temp = 2*array[i][j];
					jxl.write.Number number = new jxl.write.Number(j,i,temp);//���ŵ�һ���������У��ڶ�������
					sheet.addCell(number);
				}
			}
			book.write();
			book.close();
		}catch(Exception e){
			System.out.println(e);
		}//�ڶ���try����
		System.out.println("Compelete");
	}
}
