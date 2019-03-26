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
		//定义数组，保存数据，利用保存的数组进行运算
		double[][] array = new double[10][10];
		
		//读Excel表
		jxl.Workbook readwb = null;
			
		try    		  
        {     
            //构建Workbook对象, 只读Workbook对象    
            //直接从本地文件创建Workbook    
            InputStream instream = new FileInputStream("C:/Users/mac/Desktop/test.xls");     
            readwb = Workbook.getWorkbook(instream);      
            //Sheet的下标是从0开始    
            //获取第一张Sheet表    
            Sheet readsheet = readwb.getSheet(0);     
            //获取Sheet表中所包含的总列数     
            int rsColumns = readsheet.getColumns();     
            //获取Sheet表中所包含的总行数     
            int rsRows = readsheet.getRows();     
            //获取指定单元格的对象引用     
            for (int i = 0; i < rsRows; i++)//行不变  
            {    
                for (int j = 0; j < rsColumns; j++)//先写列    
                {     
                    Cell cell = readsheet.getCell(j, i);//括号第一个参数是列，第二个是行
                    NumberCell numc = (NumberCell)cell;
                    double value = numc.getValue();
                    array[i][j] = value;
                    
                    System.out.print(value + "   ");     
                }    
                System.out.println();    
            }
        }catch(Exception e){
    			System.out.println(e);
    		}//第一个try结束，读数据完成
		
		
		
		
		
		//写入excel表
		try{
			//打开文件
			WritableWorkbook book = Workbook.createWorkbook(new File("C:/Users/mac/Desktop/test1.xls"));
			//生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("第一页", 0);
			//往第二列第一行写入数据
			for(int i = 0; i < 10; ++i)//行不变
			{
				for(int j = 0; j < 10; ++j)//先写列
				{
					double temp = 2*array[i][j];
					jxl.write.Number number = new jxl.write.Number(j,i,temp);//括号第一个参数是列，第二个是行
					sheet.addCell(number);
				}
			}
			book.write();
			book.close();
		}catch(Exception e){
			System.out.println(e);
		}//第二个try结束
		System.out.println("Compelete");
	}
}
