package generate.word.p01_referenced;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;

public class CreateWordDemo {
	public void createDocContext(String file, String contextString) throws DocumentException, IOException {
		// ����ֽ�Ŵ�С
		Document document = new Document(PageSize.A4);
		// ����һ����д������document�������
		RtfWriter2.getInstance(document, new FileOutputStream(file));
		document.open();
		// ������������
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		// ����������
		Font titleFont = new Font(bfChinese, 12, Font.BOLD);
		// ����������
		Font contextFont = new Font(bfChinese, 10, Font.NORMAL);
		Paragraph title = new Paragraph("����");
		// ���ñ����ʽ���뷽ʽ
		title.setAlignment(Element.ALIGN_CENTER);
		title.setFont(titleFont);
		document.add(title);
		Paragraph context = new Paragraph(contextString);
		context.setAlignment(Element.ALIGN_LEFT);
		context.setFont(contextFont);
		// �μ��
		context.setSpacingBefore(3);
		// ���õ�һ�пյ�����
		context.setFirstLineIndent(20);
		document.add(context);
		// ����Table���,����һ�����еı��
		Table table = new Table(3);
		int width[] = { 25, 25, 50 };// ����ÿ�п�ȱ���
		table.setWidths(width);
		table.setWidth(90);// ռҳ���ȱ���
		table.setAlignment(Element.ALIGN_CENTER);// ����
		table.setAlignment(Element.ALIGN_MIDDLE);// ��ֱ����
		table.setAutoFillEmptyCells(true);// �Զ�����
		table.setBorderWidth(1);// �߿���
		// ���ñ�ͷ
		Cell haderCell = new Cell("����ͷ");
		haderCell.setHeader(true);
		haderCell.setColspan(3);
		table.addCell(haderCell);
		table.endHeaders();

		Font fontChinese = new Font(bfChinese, 12, Font.NORMAL, Color.GREEN);
		Cell cell = new Cell(new Paragraph("����һ��3*3���Ա������", fontChinese));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell);
		table.addCell(new Cell("#1"));
		table.addCell(new Cell("#2"));
		table.addCell(new Cell("#3"));

		document.add(table);
		document.close();

	}

	public static void main(String[] args) {
		CreateWordDemo word = new CreateWordDemo();
		String file = "C:/Users/Administrator/Desktop/test.doc";
		try {
			word.createDocContext(file, "����iText����Word�ĵ�");
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}