import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author Stavros Skourtis
 */
public class TestXML {

	public static void main(String args[]) throws IOException {
		String xml = "<root>"
				+ " <message type='public' from='Name Surname' to='John Doe' >"
				+ "Hello Friend"
				+ "</message>"
				+ "<info type='this is an xml document' />"
				+ "</root>";
		
		XmlParser parser = new XmlParser(xml);
		XmlElementNode root = parser.parse();
		
		LinkedList<XmlNode> messages = root.getSubElements("message");
		System.out.println(messages.size());
		System.out.println( ((XmlElementNode)messages.get(0)).getAttribute("to") );
		System.out.println( ((XmlElementNode)messages.get(0)).getText() );
		
		LinkedList<XmlNode> info = root.getSubElements("info");
		System.out.println( ((XmlElementNode)info.get(0)).getAttribute("type") );
	}

}
