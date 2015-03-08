XML-Parser
==========

XML parser in java. <br>
This is a small java library which allows you to easily parse and use xml documents
    
How to use
==========

Put the .java files in your project folder or add the jar (when a release comes out) to your classpath.

To parse the xml document you need to create a XmlParser object

    XmlParser parser = new XmlParser();

and then you must give the parser the xml string 
    
    parser.setXml("<root><xml></xml></root>");

or file location

    parser.readFromFile("path/to/xml/file.xml");
    

You can also give the xml document to the parser directly when you create the object    
    
    XmlParser parser = new XmlParser("<root><xml></xml></root>");
or

    XmlParser parser = new XmlParser(new File("path/to/xml/file.xml"));
    
After that you must call the parse() method. This method will return an XmlElementNode which is the root node of the document.

    XmlElelementNode root = parser.parse();
    
At this point the xml document has been parsed and everything is accessible via the root element.
Some useful methods of the XmlElementNode are :

    addAttribute(String name,String value);
    setAttribute(String name,String value);
    getAttribute(String name);
    add(XmlNode node);
    LinkedList<XmlNode> getSubElements(String name);
    
I would write a bigger and better tutorial but i dont think anyone will read it.


Can you use it?
==========

Yes , you can use and modify it as you want.
This project comes with the MIT licence.
