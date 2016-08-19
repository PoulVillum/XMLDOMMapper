package xMLMapperp;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler; 
  


public class SAXHandler extends DefaultHandler {

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		System.out.println("uri "+uri	);
		System.out.println("local "+localName);
		System.out.println("qName "+ qName);
		System.out.println("name "+attributes.getValue("name"));
	}

}
