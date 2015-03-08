import java.util.LinkedList;


public class XmlTextNode extends XmlNode {

    /**
     * The innerText of this node , includes any xml
     */
    protected String text;
    
    /**
     * gets the innerText
     * @return 
     */
    public String getText(){
        return text;
    }
    
    /**
     * sets the inner text
     * @param innerText 
     */
    public void setText(String text){
        this.text = text;
    }
    
    public String toString(){
    	return text;
    }
}
