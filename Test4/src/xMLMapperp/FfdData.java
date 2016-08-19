package xMLMapperp;

import java.io.PrintWriter;

public class FfdData {
	private String name ;
	private int indent = 0;
	private int length = 0;
	private boolean endtag = false;
	private String path;
	
	FfdData(String name, int indent, boolean endtag,String path){
		this.name = name;
		this.indent = indent;
		this.endtag = endtag;
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndent() {
		return indent;
	}

	public void setIndent(int indent) {
		this.indent = indent;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}


	public boolean isEndtag() {
		return endtag;
	}

	public void setEndtag(boolean endtag) {
		this.endtag = endtag;
	}
    public String toString(){
    	return "ffd "+name+" "+length+" "+endtag + " "+indent;
    }
    public String formatFfd(){
    	StringBuffer sb = new StringBuffer();
    	for (int i = 0; i < indent; i++) {
    		sb.append(" ");
			
		} 
    	if (endtag)
    	   sb.append("</Group>");
    	else
    		if (length>0)
    			sb.append("<Field name=\""+name+"\" length=\""+length+"\" syntax=\"host\" type=\"String\"/>");
    		else
    			sb.append("<Group name=\""+name+"\">");
    	return sb.toString();
    }

	public void formatHandler(PrintWriter pwFfd) {
    	StringBuffer sb = new StringBuffer();
    	for (int i = 0; i < indent; i++) {
    		sb.append(" ");
			
		} 
    	if (endtag&&length>0)
    	   return;
    	if (endtag)
   			sb.append("</Group>");
    	    		
    	else	
    		if (length>0)
    			sb.append("<Field name=\""+name+"\" length=\""+length+"\" syntax=\"host\" type=\"String\"/>");
    		else
    			sb.append("<Group name=\""+name+"\">");
    	pwFfd.println(sb.toString());
    	return ;
	}

	public String formatPack(PrintWriter pw) {
		StringBuffer sb = new StringBuffer();
    	StringBuffer sb1 = new StringBuffer();
    	for (int i = 0; i < indent; i++) {
    		sb.append(" ");
    		sb1.append(" ");
			
		} 
        if (endtag){
        	sb.append("</"+name+">");
     		pw.println(sb.toString());
        }
        else
        if (length>0){
        	sb1.append("<"+name+">");
        	sb.append("   <xsl:value-of select=\"/" + path + "\" />");
    		pw.println(sb1.toString());
    		pw.println(sb.toString());
        }
        else{
        	sb.append("<"+name+">");
        	pw.println(sb.toString());
        }
		return path;
	}
}
