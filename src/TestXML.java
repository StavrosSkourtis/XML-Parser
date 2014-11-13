import java.io.IOException;

/**
 *
 * @author Stavros Skourtis
 */
public class TestXML {
        
    public static void main(String args[]) throws IOException{
        
        XMLNode node = new XMLNode("message");
        node.addAttribute("from", "username");
        node.addAttribute("to", "user2");
        node.setInnerText("hello, how are you?");
        
        XMLFile file = new XMLFile();
        
        file.addNode(node);
        
        
        System.out.println(file);
        
    }
}
