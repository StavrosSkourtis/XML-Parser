
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;


/**
 *
 * @author Stavros Skourtis
 */
public class XmlParser {

    private StringBuilder xml;

    /**
     * Default empty constructor , does nothing
     */
    public XmlParser() {
    }

    /**
     * Constructor , sets the xml string
     *
     * @param xml
     */
    public XmlParser(String xml) {
        this.xml = new StringBuilder(xml);
    }

    /**
     * Constructor, reads the xml from the given file
     *
     * @param file the file to read from
     * @throws IOException
     */
    public XmlParser(File file) throws IOException {
        readFromFile(file);
    }

    /**
     * sets xml
     *
     * @param xml
     */
    public void setXml(String xml) {
        this.xml = new StringBuilder(xml);
    }

    /**
     * Reads the xml from a file
     *
     * @param filePath the path of the file to read from
     * @throws IOException may throw IOException
     */
    public void readFromFile(String filePath) throws IOException {
        readFromFile(new File(filePath));
    }

    /**
     * Reads the xml from a file
     *
     * @param file the file to read from
     * @throws IOException may throw IOException
     */
    public void readFromFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        xml = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            xml.append(line);
        }

        reader.close();
    }

    /**
     * parses the xml string and creates the xml dom
     *
     * @return returns the root elements of the xml string
     */
    public XmlElementNode parse() {
        if (xml == null) {
        	throw new InvalidXmlDocumentException("The xml String buffer was not initialized.");
        }
        
        skipSpaces();
        
        XmlElementNode root;
        if(xml.charAt(0) == '<' ){
        	 root = parseElementNode();
        }else
        	throw new InvalidXmlDocumentException("This xml document is invalid");
        
        return root;
    }
    
    
    private XmlElementNode parseElementNode(){
    	
    	XmlElementNode node = new XmlElementNode();
    	
    	node.setName(readName());
    	
    	readAttributes(node);
    	
    	// Element has children
    	if(xml.charAt(0) == '>'){
    		xml.deleteCharAt(0);
    		String closeTag = "</"+node.getName();
    		
    		if(xml.indexOf(closeTag) == -1)
    			throw new InvalidXmlDocumentException("All xml elements must have a closing tag");
    		
    		
    		while( xml.indexOf(closeTag) !=0 ){
	    		int spaces = skipSpaces();
	    		
	    		if(xml.charAt(0) == '<'){
	    			XmlElementNode subNode = parseElementNode();
	    			node.add(subNode);
	    		}else{
	    			XmlTextNode subNode = parseTextNode(spaces);
	    			node.add(subNode);
	    		}
    		}
    		
    		//Skip closing tag
    		xml.delete(0, closeTag.length()+1);
    		
    	}else if( xml.charAt(0) != '/' && xml.charAt(1) != '>'){
    		// Element does not close properly
    		throw new InvalidXmlDocumentException("All xml elements must have a closing tag");
    	}
    	
    	return node;
    }
    
    private XmlTextNode parseTextNode(int paddingFront){
    	StringBuffer text = new StringBuffer();
    	XmlTextNode node = new XmlTextNode();
    	
    	for(int i=0;i<paddingFront;i++){
    		text.append(' ');
    	}
    	
    	int positionOfStartTag = xml.indexOf("<");
    	
    	if(positionOfStartTag == -1){
    		throw new InvalidXmlDocumentException("The xml document is not valid.");
    	}
    	
    	text.append(xml.substring(0, positionOfStartTag));
    	xml.delete(0, positionOfStartTag);
    	
    	node.setText(text.toString());
    	return node;
    }

    /**
     * reads and adds attributes to the node
     *
     * @param node the node to add to
     */
    public void readAttributes(XmlElementNode node) {
    	skipSpaces();
        // loop until there are no more attributes
        while (xml.charAt(0) != '<' && xml.charAt(0) != '>' && !(xml.charAt(0) == '/' && xml.charAt(1) == '>')) {
            
            //get the name
            String name = xml.substring(0, xml.indexOf("="));
            xml.delete(0, xml.indexOf("=") + 1);
            
            //find what kind of quote is used and store it
            char quote;
            if(xml.charAt(0) == '\"' || xml.charAt(0)== '\'' )
                quote = xml.charAt(0);
            else
                throw new InvalidXmlDocumentException("Attribute value must start with \" or \'");
            xml.deleteCharAt(0);
            
            
            //is true if the nect char is a special char and it should be ignored but stored in the buffer
            boolean escape = true;
            //it will hold the value
            StringBuilder buffer = new StringBuilder();
            
            char next = xml.charAt(0);
            do{
                
                escape = next == '\\';
                
                buffer.append(next);
                
                xml.deleteCharAt(0);
                next = xml.charAt(0);
                
            }while(next != quote && !escape);
            xml.deleteCharAt(0);
            node.addAttribute(name, buffer.toString());
            
            skipSpaces();
            
        }
    }

    /**
     * reads a xml node name from the string builder
     *
     * @return the name as a string
     */
    private String readName() {
        int endOfNameIndex = xml.indexOf(" ");
        int temp = xml.indexOf(">");

        if (temp < endOfNameIndex) {
            endOfNameIndex = temp;
        }
        String name = xml.substring(1, endOfNameIndex);
        xml.delete(0, endOfNameIndex);
        return name;
    }

    /**
     * skips spaces in the xml object
     * @return the number of spaces skipped
     */
    private int skipSpaces() {
    	int counter = 0;
        while (xml.charAt(0) == ' ') {
            xml.deleteCharAt(0);
            counter++;
        }
        return counter;
    }
    
    

    /**
     * Exception thrown when an error is found in the xml document
     *
     */
    private class InvalidXmlDocumentException extends RuntimeException {

        public InvalidXmlDocumentException(String msg) {
            super(msg);
        }
    }
}
