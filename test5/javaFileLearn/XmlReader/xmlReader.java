package XmlReader;

import java.io.FileOutputStream;
import java.util.concurrent.locks.Lock;

import javax.swing.text.Document;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;



public class xmlReader {
	
//	org.dom4j.Document document=null;
//	
//	public boolean createXmlDocument() {
//		boolean isSuccess = false;
//		if(document!=null){
//			document = DocumentHelper.createDocument();
//		}
//		return isSuccess;
//	}
	
	
	private static void create() throws Exception {
		
		Lock
		Document document = (Document) DocumentHelper.createDocument();
		Element person = DocumentHelper.createElement("person");
		document.add(person);
		Element name = person.addElement("name").addAttribute("a", "x").addText("xiazdong");
		Element age = person.addElement("age").addText("20");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream("output.xml"),format);
		writer.write(document);
		writer.close();
	}
}
