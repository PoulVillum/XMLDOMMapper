package xMLMapperp;

import java.io.IOException;
import java.io.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class XMLMapper {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    SAXParserFactory parserFactor = SAXParserFactory.newInstance(); 
	    SAXParser parser = null;
		try {
			parser = parserFactor.newSAXParser();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    SAXHandler handler = new SAXHandler(); 
	    try {
	    	parser.parse(new File("c:\\app\\seev.031.001.04.xsd"), handler);  
			             
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
