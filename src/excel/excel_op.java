package excel;
//in ExcelOperater   

import java.io.File;

import java.io.FileInputStream;

import java.io.InputStream;

import jxl.Cell;

import jxl.CellType;

import jxl.Sheet;

import jxl.Workbook;

import jxl.write.Label;

public class excel_op

{

	public static void main(String[] args)

	{

		jxl.Workbook readwb = null;

		try

		{

			// ����Workbook����, ֻ��Workbook����

			// ֱ�Ӵӱ����ļ�����Workbook

			InputStream instream = new FileInputStream("E:/1.xls");

			readwb = Workbook.getWorkbook(instream);

			// Sheet���±��Ǵ�0��ʼ

			// ��ȡ��һ��Sheet��

			Sheet readsheet = readwb.getSheet(0);

			// ��ȡSheet������������������

			int rsColumns = readsheet.getColumns();
			System.err.println(rsColumns);
			// ��ȡSheet������������������

			int rsRows = readsheet.getRows();
			System.err.println(rsRows);

			for (int i = 0; i < 211; i++) {

				Cell c = readsheet.getCell(3, i);
				System.err.println("1,210=" + c.getContents());
			}

			// ��ȡָ����Ԫ��Ķ�������

//			for (int i = 0; i < rsRows; i++)
//
//			{
//
//				for (int j = 0; j < rsColumns; j++)
//
//				{
//
//					Cell cell = readsheet.getCell(j, i);
//
//					System.out.print(cell.getContents() + "   ");
//
//				}
//
//				System.out.println();
//
//			}

			// �����Ѿ�������Excel������,�����µĿ�д���Excel������

			jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(new File(

					"E:/��¥����1.xls"), readwb);

			// ��ȡ��һ�Ź�����

			jxl.write.WritableSheet ws = wwb.getSheet(0);

			// ��õ�һ����Ԫ�����

			jxl.write.WritableCell wc = ws.getWritableCell(0, 0);

			// �жϵ�Ԫ�������, ������Ӧ��ת��

			if (wc.getType() == CellType.LABEL)

			{

				Label l = (Label) wc;

				l.setString("������");

			}

			// д��Excel����

			wwb.write();

			wwb.close();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

//			readwb.close();

		}

	}

}
