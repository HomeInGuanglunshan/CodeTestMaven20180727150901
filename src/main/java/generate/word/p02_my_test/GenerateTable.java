package generate.word.p02_my_test;

import java.awt.Color;
import java.io.FileOutputStream;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;

public class GenerateTable {

	public static void main(String[] args) throws Exception {

		Document document = new Document(PageSize.A4);
		RtfWriter2.getInstance(document, new FileOutputStream("C:/Users/Administrator/Desktop/wordOfMine.doc"));
		document.open();

		Table table = new Table(3);
		table.setBorderWidth(1);
		table.setBorderColor(Color.BLACK);
		table.setPadding(11);
		table.setSpacing(0);
		table.setWidth(100);

		Cell cell = new Cell("��ͷ");// ��Ԫ��
		cell.setRowspan(3);// ���ñ��Ϊ����
		table.addCell(cell);

		// ��������
		cell = new Cell("Example cell 2");
		cell.setColspan(2);// ��ǰ��Ԫ��ռ����,������
		cell.setBackgroundColor(new Color(230, 230, 230));
		table.addCell(cell);
		table.addCell("1,1");
		table.addCell("1,2");
		table.addCell("1,3");
		table.addCell("1,4");
		
		document.add(table);
		document.close();
	}
}
