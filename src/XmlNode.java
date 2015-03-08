

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stavros
 */
abstract class XmlNode implements Comparable{
    
    /**
     * The name of this node
     */
    protected String name="";
    
    
    /**
     * gets the name
     * @return 
     */
    public String getName(){
        return name;
    }
    
    /**
     * sets the name
     * @param name 
     */
    public void setName(String name){
        this.name = name;
    }
    

    
    @Override
    public int compareTo(Object o) {
        return name.compareTo( ((XmlNode)o).getName());
    }
    
    
	
} 
    

