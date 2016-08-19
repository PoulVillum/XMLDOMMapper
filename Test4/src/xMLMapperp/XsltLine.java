package xMLMapperp;

import java.io.PrintWriter;
import java.util.ArrayList;

public class XsltLine {
	int indent;
	String line;
	
	XsltLine(String line,int indent){
		this.indent = indent;
		this.line = line;
		
	}
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < indent; i++) {
			sb.append(" ");
			
		}
		sb.append(line);
		return sb.toString();
	}
	public static void generateXslt(ArrayList<XsltLine> xsltLineArl,
			PrintWriter pwXslt) {
		for (int i = 0; i < xsltLineArl.size(); i++) {
			StringBuffer sb = new StringBuffer();
			XsltLine xsltLine = xsltLineArl.get(i);
			for (int j = 0; j < xsltLine.indent; j++) {
				sb.append(" ");
			}
			sb.append(xsltLine.line);
//			pwXslt.println(sb.toString());
			
		} 
		// TODO Auto-generated method stub
		
	}

}
