import java.util.LinkedList;


public class XmlElementNode extends XmlNode {
	
	/**
     * A list that hold the sub nodes of this node
     */
    private LinkedList<XmlNode> subNodes;
    
    /**
     * A list that holds the attributes of this
     */
    private LinkedList<Attribute> attributes;
    
    
    public XmlElementNode(){
    	subNodes = new LinkedList<>();
        attributes = new LinkedList<>();
    }
    
    /**
     * Adds an attribute to the node
     * @param name
     * @param value 
     */
    public void addAttribute(String name,String value){
        Attribute attr = new Attribute();
        attr.setName(name);
        attr.setValue(value);
        
        attributes.add(attr);
    }
    
    public void setAttribute(String name,String value){
    	for(int i=0; i < attributes.size() ; i ++ ){
    		if( attributes.get(i).getName().equals(name)){
    			attributes.get(i).setValue(value);
    			return;
    		}
    	}
    	throw new IllegalArgumentException("There is no attribute with name "+name);
    }
    
    public String getAttribute(String name){
    	
    	for(int i=0; i < attributes.size() ; i ++ ){
    		if( attributes.get(i).getName().equals(name))
    			return attributes.get(i).getValue();
    	}
    	throw new IllegalArgumentException("There is no attribute with name "+name);
    }
    
    
    /**
     * adds a sub node to this nodes
     * @param node the sub node to add
     */
    public void add(XmlNode node){
        subNodes.add(node);
    }
    
    /**
     * returns a sub node
     * @param index the index of the sub node
     * @return 
     */
    public XmlNode get(int index){
        return subNodes.get(index);
    }
    
    /**
     * deletes a sub node , if the sub node has itself more sub node then those too will be removed
     * @param index the index of the sub node
     * @return 
     */
    public XmlNode delete(int index){
        return subNodes.remove(index);
    }
    
    /**
     * finds all the nodes with the given name, it will search nodes recursively
     * 
     * @param name the name of the node
     * @return returns a LinkedList of nodes
     */
    public LinkedList<XmlNode> getSubElements(String name){
        LinkedList<XmlNode> result = new LinkedList<>();
        
        
        for(int i=0 ; i<subNodes.size() ; i++){
        	XmlNode currentNode = subNodes.get(i);
        	
        	if(currentNode.getName().equals(name)){
        		result.add(currentNode);
        	}
        	if(currentNode instanceof XmlElementNode){
        		LinkedList<XmlNode> targetResult = ((XmlElementNode)currentNode).getSubElements(name);
        	
        		result.addAll(targetResult);
        	}
        }
        
        return result;
    } 
    
    public String getText(){
    	StringBuffer buffer = new StringBuffer();
    	
    	for(int i=0; i< subNodes.size();  i++){
    		if(subNodes.get(i) instanceof XmlTextNode)
    			buffer.append(subNodes.get(i));
    	}
    	
    	return buffer.toString();
    	
    	
    }
	
	/**
     * A simple class that just holds the data of an xml attribute
     */
    public static class Attribute{
        private String name;
        private String value;
        
        public String getName(){ return name; }
        public void setName(String name){ this.name = name; }
        
        public String getValue(){ return value; }
        public void setValue(String value){ this.value = value; }        
    }
}
