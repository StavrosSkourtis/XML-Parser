import java.io.IOException;

/**
 *
 * @author Stavros Skourtis
 */
public class TestXML {
        
    public static void main(String args[]) throws IOException{
        
        String xml = "<mesh id=\"lolol\" >\n"+
                     "    lolthis is text<vertex x=\"1\" y=\"2\" /> \n"+
                     "</mesh>\n"+
                     "<fallingbridge x=\"5\" y=\"6\" />\n";
        
        
        XMLFile file = new XMLFile();
        file.read("temp.xml");
        System.out.println(file);
        
    }
}
