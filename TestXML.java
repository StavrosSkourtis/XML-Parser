/**
 *
 * @author Stavros Skourtis
 */
public class TestXML {
        
    public static void main(String args[]){
        
        String xml = "<mesh id=\"lolol\" >\n"+
                     "    lolthis is text<vertex x=\"1\" y=\"2\" /> \n"+
                     "</mesh>\n"+
                     "<fallingbridge x=\"5\" y=\"6\" />\n";
        
        
        XMLFile file = new XMLFile();
        file.addNode(new String[] {"mesh2","id='1'","x='2'"} ,"bla");
        file.addNode(new String[] {"mesh","id='2'","y='4'"} ,"bla");
        file.getChildrenByName("mesh2").get(0).addChildNode(new String[] {"hello","name='Stavros'"},"Bla");
        //file.update(xml);
        System.out.println(file);
        
    }
}
