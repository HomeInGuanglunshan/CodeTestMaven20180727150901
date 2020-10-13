package generate.word.p01_referenced;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;

public class RTFCreate {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			RTFCreate rtfMain = new RTFCreate();
			rtfMain.createRTFContext("C:/Users/Administrator/Desktop/withImg.doc");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createRTFContext(String path) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		RtfWriter2.getInstance(document, new FileOutputStream(path));
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
		String contextString = "iText��һ���ܹ����ٲ���PDF�ļ���java��⡣iText��java�������ЩҪ���������ı������ͼ�ε�ֻ���ĵ��Ǻ����õġ��������������java Servlet�кܺõĸ��ϡ�ʹ��iText��PDF�ܹ�ʹ����ȷ�Ŀ���Servlet�������";
		Paragraph context = new Paragraph(contextString);
		// ���ĸ�ʽ�����
		context.setAlignment(Element.ALIGN_LEFT);
		context.setFont(contextFont);
		// ����һ���䣨���⣩�յ�����
		context.setSpacingBefore(20);
		// ���õ�һ�пյ�����
		context.setFirstLineIndent(20);
		document.add(context);
		// //�ڱ��ĩβ���ͼƬ
		Image png = Image.getInstance("C:/Users/Administrator/Desktop/cut.jpg");
		document.add(png);
		document.close();
	}
}
