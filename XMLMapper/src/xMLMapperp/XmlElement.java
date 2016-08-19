package xMLMapperp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.*;

public class XmlElement {
	
	private static int indent = 0;
	public static XmlElement root = null;
	public static XmlElement current = null;
	private static int level;
	private XmlElement parent = null;
	private ArrayList<XmlElement> children = new ArrayList<XmlElement>();
	private ArrayList<String> path = new ArrayList<String>();
	private int occurs;
	private int occLevel = 0;
	private int occIx = 0;
	private int prevOccIx = 0;
	private static boolean dmyMode ;
	private static int iNo;
	private static File sourceFile = null;
	private int choices = 0;
	private int choiceNo = 0;
	private String attrName = null;
	private boolean choiceElement = false;
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	private String attrExample  = null;
	
	
	
	public String getAttrExample() {
		return attrExample;
	}
	public void setAttrExample(String attrExample) {
		this.attrExample = attrExample;
	}
	private String name =  null;
	private int length;
	private String example = null;
	public static XmlElement getRoot() {
		return root;
	}
	public static void setRoot(XmlElement root) {
		XmlElement.root = root;
	}
	public static XmlElement getCurrent() {
		return current;
	}
	public static void setCurrent(XmlElement current) {
		XmlElement.current = current;
	}
	public XmlElement getParent() {
		return parent;
	}
	public void setParent(XmlElement parent) {
		this.parent = parent;
	}
	public ArrayList<XmlElement> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<XmlElement> children) {
		this.children = children;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getExample() {
		return example;
	}
	public void setExample(String example) {
		this.example = example;
	}
	public boolean isDmyMode() {
		return dmyMode;
	}
	public static void setDmyMode(boolean dmyMode) {
		XmlElement.dmyMode = dmyMode;
	}
	public String getPathAsString(){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < this.getPath().size(); i++) {
			sb.append(this.getPath().get(i)+"/");
			

			
		}
		return sb.toString();
	}
	public static void traverseElementsForXml(XmlElement el,PrintWriter pwXml){
		
		

//		System.out.println("Choice no "+el.getChoiceNo()+" "+el.getChoices()+" "+el.getName()+" "+el.getPath());
		
		 
		
//		if(el.getName().equals("OthrDocId"))
//			System.out.println("ss>>");
//		if (el.getChoiceNo()>1 &&el.getChoices()>1){
//			if (el.getChoiceNo()>1)
//				return;
//		}
		
		int iMax = 1;
		if (el.getOccurs()>1){
			iMax = (int) (3 + Math.random()*(el.getOccurs()-3));
		}
		if (iMax > el.getOccurs())
			iMax = el.getOccurs();
		if (iMax<1)
			iMax=1;
		
		for (int i = 0; i < iMax; i++) {
			
			String indentStr = XmlElement.indent(XmlElement.getIndent()); 
			if(el.getName().equals("Document"))
				Helper.preXML(pwXml,XMLDOMMapper.messageName.substring(0,15));
			else
			{
			pwXml.print(indentStr+"<"+el.getName());
			if (el.getAttrName()==null)
				pwXml.print(">");
			else
				pwXml.print(" "+el.getAttrName()+" = \""+el.getAttrExample()+"\">");
				
			} 

			if (el.getChildren().size()==0){
				String ex = el.getExample();
				if (ex==null)
					System.out.println("no data");
				if(ex.indexOf("%%")>0){

					iNo = iNo%30 +1;
					DecimalFormat df = new DecimalFormat("00");

					ex = ex.replaceFirst("%%", df.format(iNo));
				}

				pwXml.print(ex);
				pwXml.print("</" + el.getName()+">");
			}
			pwXml.println("");

			for (int j = 0; j < el.getChildren().size(); j++) {
				
			
				if (el.isChoiceElement() && j> 0)
					;
				else{
				XmlElement.setIndent(XmlElement.getIndent() + 2);
				traverseElementsForXml(el.getChildren().get(j),pwXml);
				XmlElement.setIndent(XmlElement.getIndent() - 2);
				}


			}
			if (el.getChildren().size()>0){

				pwXml.println(indentStr+"</"+el.getName()+">");
			}
		}
		
	}
	public static void traverseElementsForPli(XmlElement el, PrintWriter pwPLI){
		

		int interVene = 55;
		DecimalFormat df = new DecimalFormat("00");
		
		int maxRead = el.occurs;
		
		if (XMLDOMMapper.messageName.equals("seev.031.001.05") && el.getName().equals("AddtlInf"))
			maxRead = 1;

		
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < XmlElement.getIndent(); i++) {
			sb.append(" ");
			
		}
		
		if (el.getOccurs()>1&&XMLDOMMapper.incoming){
			pwPLI.println(XmlElement.indent(XmlElement.indent)+df.format(XmlElement.getLevel())+"  "+el.getName()+"Count"+
					XmlElement.indent(interVene-getIndent() - el.getName().length() - 7)+"char(5),");
		}
			
		sb.append(""+df.format(XmlElement.getLevel())+"  "+el.getName());

		
		if (!(el.getAttrName()==null)){
			sb.append("_w_attr");
			if (el.getOccurs() > 1)
				sb.append("("+el.getOccurs()+")");
			sb.append(",");
			pwPLI.println(sb.toString());
			pwPLI.println(XmlElement.indent(XmlElement.indent+2)+df.format(XmlElement.getLevel()+2)+"  "+el.getAttrName()+
					XmlElement.indent(interVene-getIndent() - el.getAttrName().length()-2)+"char("+el.getAttrExample().length()+"),");
			pwPLI.println(XmlElement.indent(XmlElement.indent+2)+df.format(XmlElement.getLevel()+2)+"  "+el.getName()+
					XmlElement.indent(interVene-getIndent() - el.getName().length()-2)+"char("+el.getLength()+"),");

		}	
		else{
			if (maxRead > 1)
				sb.append("("+el.getOccurs()+")");

			if (el.getChildren().size()==0){
				for (int i = 0; i < 55-getIndent() - el.getName().length(); i++) {
					sb.append(" ");

				}
				sb.append("char("+el.getLength()+")");
			}
			
			sb.append(",");
			pwPLI.println(sb.toString());
			if (el.getName().equals("Document"))
	    		pwPLI.println("                         /*  "+XMLDOMMapper.messageName+" */" );
				
			if(XMLDOMMapper.messageName.equals("seev.031.001.05") && el.getName().equals("Document")) 
				Helper.keysForPLI(pwPLI);
			else
	    	if(XMLDOMMapper.messageName.equals("seev.035.001.05") && el.getName().equals("Document"))
	    		Helper.keysForPLI(pwPLI);

			else
		    	if(XMLDOMMapper.messageName.equals("seev.036.001.05") && el.getName().equals("Document"))
		    		Helper.keysForPLI36(pwPLI);

					

		}
		
		for (XmlElement el1 : el.getChildren()) {
			XmlElement.setIndent(XmlElement.getIndent() + 2);
			XmlElement.setLevel(XmlElement.getLevel() + 2);
			traverseElementsForPli(el1,pwPLI);
			XmlElement.setIndent(XmlElement.getIndent() - 2);
			XmlElement.setLevel(XmlElement.getLevel() - 2);
			
			
		}

		
	}
	public static void traverseElementsForXslt(XmlElement el, PrintWriter pwXslt){

       	int maxRead = el.getOccurs();
        		
		
		String indentStr = XmlElement.indent(XmlElement.getIndent()); 
		
		if (XMLDOMMapper.messageName.equals("seev.031.001.05") && el.getName().equals("AddtlInf")){
			maxRead = 1;
		}
		
		
        if (el.getOccurs()>1){
        	
        	
        	pwXslt.print(indentStr+"<xsl:variable name=\""+el.getName()+"\" select=\"");
        	
        	int fromIx = 0;
        	if (el.getOccLevel()>1){
        		pwXslt.print(".");
        		fromIx = el.getPrevOccIx();
        	}	
        		
        	for (int i = fromIx; i < el.getPath().size(); i++) {
				pwXslt.print("/*[local-name()='"+ el.getPath().get(i)+"']");
			}
			pwXslt.println("\" />");

			pwXslt.println(indentStr+"   <xsl:variable name=\""+el.getName()+"Count\" select=\"count($" + el.getName()+ ")\" />");
			
			pwXslt.println(indentStr+"   <"+el.getName()+"Count><xsl:value-of select=\"$"+el.getName()+"Count\" /> </"+el.getName()+"Count>");
			
			pwXslt.println(indentStr +"<xsl:for-each select=\"$"+el.getName()+"\">");
			pwXslt.println(indentStr + "<xsl:if test=\"position() &lt;= '"+maxRead+"'\">");
			pwXslt.println(indentStr + "  <" + el.getName() + ">");
			if (el.getChildren().size()==0){
				pwXslt.print(indentStr+"  <xsl:value-of select=\"");
				
				
				int fromIx1 = el.getOccIx();
				
					
				if (el.getOccIx()>0){
					
					pwXslt.print(".");
				}
				for (int i = fromIx1; i < el.getPath().size(); i++) {
					pwXslt.print("/*[local-name()='"+ el.getPath().get(i)+"']");
				}
				pwXslt.println("\" />");

				
			}
			

        	
        }
        else{
		
		if (el.getChildren().size()==0){
			if (el.getAttrName()!=null){
				pwXslt.println(indentStr+"<"+el.getName()+"_w_attr>");
				pwXslt.println(indentStr+"  <"+el.getAttrName()+">");
				pwXslt.print(indentStr+"    <xsl:value-of select=\"");
				
				
				int fromIx = el.getOccIx();
					
				if (el.getOccIx()>0){
					
					pwXslt.print(".");
				}
				for (int i = fromIx; i < el.getPath().size(); i++) {
					pwXslt.print("/*[local-name()='"+ el.getPath().get(i)+"']");
				}
				pwXslt.println("/@"+el.getAttrName()+ "\"/>");


				pwXslt.println(indentStr+"  </"+el.getAttrName()+">");


			}
			
			
			pwXslt.println(indentStr+"<"+el.getName()+">");

			
			pwXslt.print(indentStr+"  <xsl:value-of select=\"");
					
			
			int fromIx = el.getOccIx();
				
			if (el.getOccIx()>0){
				
				pwXslt.print(".");
			}
			for (int i = fromIx; i < el.getPath().size(); i++) {
				pwXslt.print("/*[local-name()='"+ el.getPath().get(i)+"']");
			}
			pwXslt.println("\" />");


		}
		else
			pwXslt.println(indentStr+"<"+el.getName()+">");

        }
        
        if (el.getName().equals("Document"))
        	pwXslt.println("<!-- Generated from "+sourceFile.getAbsolutePath()+ "-->");
        
        if(XMLDOMMapper.messageName.equals("seev.031.001.05") && el.getName().equals("Document"))
        	Helper.keysForXslt(pwXslt);
    	if(XMLDOMMapper.messageName.equals("seev.035.001.05") && el.getName().equals("Document"))
    		Helper.keysForXslt35(pwXslt);
    	if(XMLDOMMapper.messageName.equals("seev.036.001.05") && el.getName().equals("Document"))
    		Helper.keysForXslt36(pwXslt);
				

		
		for (XmlElement el1 : el.getChildren()) {
			XmlElement.setIndent(XmlElement.getIndent() + 2);
			traverseElementsForXslt(el1,pwXslt);
			XmlElement.setIndent(XmlElement.getIndent() - 2);
			
			
		}

			pwXslt.println(indentStr+"</"+el.getName()+">");
			if (el.getAttrName()!=null)
				pwXslt.println(indentStr+"</"+el.getName()+"_w_attr>");
	
			
			
			if (el.occurs>1){
				pwXslt.println(indentStr+ "</xsl:if>");
                pwXslt.println(indentStr+ "</xsl:for-each>");


			}
			if (el.occurs>1){
				XmlElement.setDmyMode(true);
				for (int i = 0; i < maxRead; i++) {
					pwXslt.println(XmlElement.indent(XmlElement.indent+i*2)+"<xsl:if test=\"$"+el.getName()+"Count" +" &lt; "+ (maxRead - i) + "\" >"); 
					pwXslt.print(XmlElement.indent(XmlElement.indent+i*2+2)+"<"+el.getName()+">");
					for (XmlElement el1 : el.getChildren()) {
						traverseElementsForDummy(el1,pwXslt);

						
						
						
					}
					pwXslt.print("</"+el.getName()+">");
					pwXslt.println("");
				
				}
				for (int i = 0; i < maxRead; i++) {
					pwXslt.println(XmlElement.indent(XmlElement.indent+(maxRead-i)*2-2)+"</xsl:if>");
					
				}
				XmlElement.setDmyMode(false);

			}


		
	}
	public static void traverseElementsForData(XmlElement el,PrintWriter pwData){
		int maxRead = el.occurs;
		
		if (XMLDOMMapper.messageName.equals("seev.031.001.05") && el.getName().equals("AddtlInf"))
			maxRead = 1;

		for (int j = 0; j < maxRead; j++) {

			if (el.getChildren().size()==0){
				
				
				if (el.getAttrName()==null)
					pwData.print(el.exampleExpanded());
				else{
					pwData.print(el.attrExampleExpanded());
					pwData.print(el.exampleExpanded());
 				}
    			    
				
				

			}

			for (XmlElement el1 : el.getChildren()) {
				traverseElementsForData(el1,pwData);


			}
		}

		
	}
	public String exampleExpanded(){
		StringBuffer sb = new StringBuffer();
		String ex = example;
		if(ex==null){
			System.out.println("example missing "+getPathAsString());
			ex = ""	;
			example = ""	;
			
		}
		if(ex.indexOf("%%")>0){

			iNo = iNo%30 +1;
			DecimalFormat df = new DecimalFormat("00");

			ex = ex.replaceFirst("%%", df.format(iNo));
		}
		sb.append(ex);
		for (int i = 0; i < this.getLength()-example.length(); i++) {
			sb.append(" ");
			
		}
		
		return sb.toString();
		
	}
	public String attrExampleExpanded(){
		StringBuffer sb = new StringBuffer();
		sb.append(this.attrExample);
		
		return sb.toString();
		
	}
	public static void traverseElementsForFfd(XmlElement el,PrintWriter pwFfd){
		
		int gemIndent = XmlElement.getIndent();
		int maxRead = el.occurs;
		
		if (XMLDOMMapper.messageName.equals("seev.031.001.05") && el.getName().equals("AddtlInf"))
			maxRead = 1;

		
		
		if (el.getOccurs()>1 && XMLDOMMapper.incoming){
			pwFfd.println(XmlElement.indent(XmlElement.getIndent())+"<Field name=\""+el.getName()+"Count"+"\" length=\"5\""+" syntax=\"host\" type=\"String\"/>");
		}
		
		
		for (int j = 0; j < maxRead; j++) {
			XmlElement.setIndent(gemIndent);



			if (el.getChildren().size()==0){
				
				
				if (el.getAttrName()==null)
					pwFfd.println(XmlElement.indent(XmlElement.getIndent())+"<Field name=\""+el.getName()+"\" length=\""+el.getLength()+"\""+" syntax=\"host\" type=\"String\" />");
				else{
					pwFfd.println(XmlElement.indent(XmlElement.getIndent())+"<Group name = \""+el.getName()+"_w_attr\" >");
					pwFfd.println(XmlElement.indent(XmlElement.getIndent()+2)+"<Field name=\""+el.getAttrName()+"\" length=\""+el.getAttrExample().length()+"\""+" syntax=\"host\" type=\"String\"/>");
					pwFfd.println(XmlElement.indent(XmlElement.getIndent()+2)+"<Field name=\""+el.getName()+"\" length=\""+el.getLength()+"\""+" syntax=\"host\" type=\"String\"/>");
					pwFfd.println(XmlElement.indent(XmlElement.getIndent())+"</Group>");
 				}
    			    
				
				

			}
			else{
			    pwFfd.println(XmlElement.indent(XmlElement.getIndent())+"<Group name=\""+el.getName()+"\">");
			    if(XMLDOMMapper.messageName.substring(0,15).equals("seev.035.001.05") && el.getName().equals("Document") ||
	        	   XMLDOMMapper.messageName.substring(0,15).equals("seev.031.001.05") && el.getName().equals("Document")){
	        		Helper.keysForFfd(pwFfd);
	        		System.out.println("------------------------55"	);
	        	}
						
			    if(XMLDOMMapper.messageName.substring(0,15).equals("seev.036.001.05") && el.getName().equals("Document") ){
			        		Helper.keysForFfd36(pwFfd);
			        	}

			}

			for (XmlElement el1 : el.getChildren()) {
				XmlElement.setIndent(XmlElement.getIndent() + 2);
				traverseElementsForFfd(el1,pwFfd);
				XmlElement.setIndent(XmlElement.getIndent() - 2);


			}
			if (el.getChildren().size()>0){

				pwFfd.println(XmlElement.indent(XmlElement.getIndent())+"</Group>");
			}
		}

		
	}
	public static void traverseElementsForPack(XmlElement el,PrintWriter pwPack){
		

		String indentStr = XmlElement.indent(XmlElement.getIndent()); 
		
        if (el.getOccurs()>1){
        	
        	
        	pwPack.print(indentStr+"<xsl:variable name=\""+el.getName()+"\" select=\"");
        	
        	int fromIx = 0;
        	if (el.getOccLevel()>1){
        		pwPack.print(".");
        		fromIx = el.getPrevOccIx();
        	}	
        	else
				pwPack.print("/PLI_Msg");
        		
        	for (int i = fromIx; i < el.getPath().size(); i++) {
				pwPack.print("/"+ el.getPath().get(i));
			}
			pwPack.println("\" />");

			
			pwPack.println(indentStr +"<xsl:for-each select=\"$"+el.getName()+"\">");
			pwPack.println(indentStr + "  <" + el.getName() + ">");
			if (el.getChildren().size()==0){
				pwPack.print(indentStr+"  <xsl:value-of select=\"");
				
				
				int fromIx1 = el.getOccIx();
				
					
				if (el.getOccIx()>0){
					
					pwPack.print(".");
				}
				else
					pwPack.print("/PLI_Msg");
				for (int i = fromIx1; i < el.getPath().size(); i++) {
					pwPack.print("/"+ el.getPath().get(i));
				}
				pwPack.println("\" />");

				
			}
			

        	
        }
        else
    		if (el.getChildren().size()==0){
    			
    			
    			pwPack.println(indentStr+"<"+el.getName()+">");

    			
    			pwPack.print(indentStr+"  <xsl:value-of select=\"");
    					
    			
    			int fromIx = el.getOccIx();
    				
    			if (el.getOccIx()>0){
    				
    				pwPack.print(".");
    			}
    			else
    	   			pwPack.print("/PLI_Msg");
    			    				
    			for (int i = fromIx; i < el.getPath().size(); i++) {
    				pwPack.print("/"+ el.getPath().get(i));
    			}
    			pwPack.println("\" />");
    			
 
    		}
    		else{
    			if (el.getName().equals("Document"))
    				pwPack.println(indentStr+"<"+el.getName()+" xmlns=\"urn:iso:std:iso:20022:tech:xsd:"+XMLDOMMapper.messageName+"\">");
    			else
    			   pwPack.println(indentStr+"<"+el.getName()+">");
    		}

         
            
		

		
		
		
		for (XmlElement el1 : el.getChildren()) {
			XmlElement.setIndent(XmlElement.getIndent() + 2);
			traverseElementsForPack(el1,pwPack);
			XmlElement.setIndent(XmlElement.getIndent() - 2);
			
			
		}

			pwPack.println(indentStr+"</"+el.getName()+">");
			if (el.occurs>1){
                pwPack.println(indentStr+ "</xsl:for-each>");


			}



		
	}
	public static void traverseElementsForDummy(XmlElement el, PrintWriter pwXslt){

		if(el.getAttrName()!=null){
			pwXslt.print("<"+el.getName()+"_w_attr >");
			pwXslt.print("<"+el.getAttrName()+">");
			pwXslt.print("</"+el.getAttrName()+">");
			
		}
		
		for (int i = 0; i < el.occurs; i++) {


			pwXslt.print("<"+el.getName()+">");




			for (XmlElement el1 : el.getChildren()) {
				traverseElementsForDummy(el1,pwXslt);
			}

		pwXslt.print("</"+el.getName()+">");
		
		}

		if(el.getAttrName()!=null){
			
			pwXslt.print("</"+el.getName()+"_w_attr >");
		}

		
	}
	
	public static String indent(int indent){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < indent; i++) {
			sb.append(" ");
			
		}
		return sb.toString();
	}

	public ArrayList<String> getPath() {
		return path;
	}
	public void setPath(ArrayList<String> path) {
		this.path = path;
	}
	public static int getIndent() {
		return indent;
	}
	public static void setIndent(int indent) {
		XmlElement.indent = indent;
	}
	public static void setlevel(int i) {
		setLevel(i);
		
	}
	public static int getLevel() {
		return level;
	}
	public static void setLevel(int level) {
		XmlElement.level = level;
	}
	public int getOccurs() {
		return occurs;
	}
	public void setOccurs(int occurs) {
		this.occurs = occurs;
	}
	public int getOccIx() {
		return occIx;
	}
	public void setOccIx(int occIx) {
		this.occIx = occIx;
	}
	public int getOccLevel() {
		return occLevel;
	}
	public void setOccLevel(int occLevel) {
		this.occLevel = occLevel;
	}
	public int getPrevOccIx() {
		return prevOccIx;
	}
	public void setPrevOccIx(int prevOccIx) {
		this.prevOccIx = prevOccIx;
	}
	public static void setSourceFile(File sourceFile) {
		XmlElement.sourceFile = sourceFile;
	}
	public static int getiNo() {
		return iNo;
	}
	public static void setiNo(int iNo) {
		XmlElement.iNo = iNo;
	}
	public int getChoices() {
		return choices;
	}
	public void setChoices(int choices) {
		this.choices = choices;
	}
	public int getChoiceNo() {
		return choiceNo;
	}
	public void setChoiceNo(int choiceNo) {
		this.choiceNo = choiceNo;
	}
	public static File getSourceFile() {
		return sourceFile;
	}
	public boolean isChoiceElement() {
		return choiceElement;
	}
	public void setChoiceElement(boolean choiceElement) {
		this.choiceElement = choiceElement;
	}
}
