package mybatis.mapper.to.sql;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * https://blog.csdn.net/dmw412724/article/details/82256371
 */
public class Generator {

	public static void main(String[] args) throws Exception {
		generateSql("D:/project/shiro/src/main/resources/config/xml", "C:/Users/Administrator/Desktop/tables.sql");
	}

	/**
	 * 生成sql
	 *
	 * @param dirPath
	 *            mapper.xml的父级文件夹
	 * @param sqlFile
	 *            选择你将要生成sql的文件
	 * @throws IOException
	 */
	private static void generateSql(String dirPath, String sqlFile) throws IOException {
		FileWriter fw = null;
		try {
			File dir = new File(dirPath);
			File sql = new File(sqlFile);
			if (sql.exists()) {
				sql.delete();
			}
			sql.createNewFile();
			fw = new FileWriter(sql);

			if (dir.exists() && dir.isDirectory()) {
				File[] files = dir.listFiles();
				for (File file : files) {
					if (file.isFile() && file.getName().endsWith(".xml")) {
						System.out.println(file.getName());
						fw.append("\r\n");
						fw.append(getSql(file));
						fw.flush();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null)
				fw.close();
		}
	}

	private static String getSql(File xmlfile) throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(xmlfile);
		Element root = document.getRootElement();
		Element resultMap = root.element("resultMap");
		Table tab = new Table();
		tab.setTableName(getTableName(root));
		tab.setColumns(getColumns(resultMap));
		return tab.toString();
	}

	@SuppressWarnings("unchecked")
	private static Map<String, String> getColumns(Element resultMap) {
		List<Element> elements = resultMap.elements();
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (Element element : elements) {
			map.put(element.attribute("column").getValue(), element.attribute("jdbcType").getValue());
		}
		return map;
	}

	private static String getTableName(Element root) {
		String tableName = getTableNameFromSelection(root);
		if (StringUtils.isNotBlank(tableName)) {
			return tableName;
		} else {
			return getTableNameFromInsertion(root);
		}
	}

	private static String getTableNameFromSelection(Element root) {
		try {
			Element selectByPrimaryKey = root.element("select");
			String selectStr = selectByPrimaryKey.getTextTrim();
			String tableName = selectStr.split("from")[1].trim().split(" ")[0].trim();
			return tableName;
		} catch (Exception e) {
			return null;
		}
	}

	private static String getTableNameFromInsertion(Element root) {
		try {
			Element insertion = root.element("insert");
			String insertionStr = insertion.getTextTrim();
			String tableName = insertionStr.split("into")[1].trim().split(" ")[0].trim();
			return tableName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}