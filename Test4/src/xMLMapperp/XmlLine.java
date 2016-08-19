package xMLMapperp;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class XmlLine {
	public String name = null;
	public boolean onlyEnd = false;
	public int[] mapno = new int[2];
	public String content = null;
	public boolean startTag = false;
	public boolean endTag = false;
	public int indent = 0;
	public ArrayList<String> attrName = new ArrayList<String>();
	public ArrayList<String> attrValue = new ArrayList<String>();
	public int contentLength;
	public String occ;
	public static int level = 0;
	public static ArrayList<excelCol> PLIExcel = new ArrayList<excelCol>();

	public XmlLine(String name, boolean startTag, boolean endTag) {
		this.name = name;
		this.startTag = startTag;
		this.endTag = endTag;
		// TODO Auto-generated constructor stub
	}

	public XmlLine(String name, boolean startTag, int indent) {
		this.name = name;
		this.startTag = true;
		this.indent=indent;
		// TODO Auto-generated constructor stub
	}
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < indent; i++) {
			sb.append(" ");
		}
		if (startTag){
			sb.append("<"+name+">");
			
			if (content!=null)
				sb.append(content);
		}	
		if (endTag)
			sb.append("</"+name+">");
		
//		System.out.println(">>>>>>>>>"+sb.toString());
		
		return sb.toString();
		
	}
	public String toXMLString(boolean excelFormat){
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < indent; i++) {
			sb.append(" ");
		}
		if (startTag){
			sb.append("<"+name);
			
			for (int i = 0; i < attrName.size(); i++) {
				sb.append(" "+attrName.get(i)+"=\""+attrValue.get(i)+"\"");
				
			} 
			sb.append(">");
			if (content!=null)
				sb.append(content);
		}	
		if (endTag)
			sb.append("</"+name+">");
		
		if (excelFormat){
			sb.append(";"	);
			for (int i = 0; i < mapno.length; i++) {
				if(mapno[i]>0){
					if(i>0)
						sb.append(",");
					sb.append(mapno[i]);
				}
				
				
			}
		
		}
		
		return sb.toString();
	}
	
	
	
	public void printAsXml(PrintWriter pw){
		

			pw.println(toXMLString(false));
		

	}
	public void printAsPli(PrintWriter pw){
		ArrayList<String> arl = toPliString(true);
		for (Iterator<String> iterator = arl.iterator(); iterator.hasNext();) {
			pw.println(iterator.next());
			
		}
		
	}
	
	public ArrayList<String> toPliString(boolean excelFormat){
		
		excelFormat = true;
		
        MyDecimalFormat df = new MyDecimalFormat();

		
		ArrayList<String> result = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		
		if (excelFormat){
			sb.append(";");
			if (attrName.size()==0 && content!=null)
				sb.append(df.format(mapno[0])+";");
			if (attrName.size()>0)
				sb.append(";");
			if (attrName.size()==0 && content==null)
				sb.append(";");
			
			
		}
		
//		System.out.println(">111>"+df.format(mapno[0])+"<");
		
			
		for (int i = 0; i < level; i++) {
			sb1.append(" ");
		}
		sb.append(sb1.toString());
		
		if (startTag){
			level+=2;
			sb.append(level + " "+ name);
			if (attrName.size()>0){
				sb.append("_w_attr"+occ+",");
				
	            sb.insert(1, "    ");
				result.add(sb.toString());
	            sb = new StringBuffer();
	            if(excelFormat)
	            	sb.append(";");
	            sb1.append("  ");
			}
			else
				sb.append(occ);
			for (int i = 0; i < attrName.size(); i++) {
				if(excelFormat)
					sb.append(df.format(mapno[i])+";");
				sb.append(sb1.toString());
				sb.append((level+2)+" "+attrName.get(i));
				toRight(sb,50);
				sb.append("char("+attrValue.get(i).length()+"),");
				
				result.add(sb.toString());
				sb = new StringBuffer();
				if (excelFormat)
					sb.append(";");
				
			} 
			if (content==null){
				sb.append(",");
				sb.insert(1,"    ");
			}
			else
				if (attrName.size()>0){
					if (excelFormat)
						sb.append(df.format(mapno[attrName.size()])+";");
					sb.append(sb1.toString()+(level+2) + " "+ name);
					toRight(sb, 50);
					sb.append("char("+contentLength+"),");
				}
				else{
					toRight(sb, 50);
					sb.append("char("+contentLength+"),");
				}
			result.add(sb.toString());
			
				
		}	
		if (endTag)
			level -= 2;
		
		return result;
		

	}
	private void toRight(StringBuffer sb, int pos) {
		int mx = sb.length();
		for (int i = 0; i < pos - mx; i++) {
			sb.append(" ");
			
		}
	}

	public static void generateMapping(ArrayList<XmlLine> xmlLineArr){
		int startMapNo = 0;
		for (int i = 0; i < xmlLineArr.size(); i++) {
			xmlLineArr.get(i).mapno[0]=0;
			xmlLineArr.get(i).mapno[1]=0;
			int k=0;
			if (xmlLineArr.get(i).startTag){
				XmlLine xmlLine = xmlLineArr.get(i);
				for (int j = 0; j < xmlLine.attrName.size(); j++) {
					startMapNo++;
					xmlLine.mapno[k]=startMapNo;
					k++;

				} 
				if (xmlLine.content!=null ){
					startMapNo++;
					xmlLine.mapno[k]=startMapNo;
					k++;
				}
					
					
			}
			
		}
		
	}

	public static void generateExcel(ArrayList<XmlLine> xmlLineArr,PrintWriter pw) {
		// TODO Auto-generated method stub
		ArrayList<String> xml = new ArrayList<String>();
		ArrayList<String> pli = new ArrayList<String>();
		
		for (int i = 0; i < xmlLineArr.size(); i++) {
			xml.add(xmlLineArr.get(i).toXMLString(true));
			ArrayList<String> ar = xmlLineArr.get(i).toPliString(true);
			for (int j = 0; j < ar.size(); j++) {
				pli.add(ar.get(j));
				
			} 
			
			
		} 
		for (int i = 0; i < xml.size() || i < pli.size(); i++) {
			StringBuffer sb = new StringBuffer();
			
			if (i<xml.size() )
				sb.append(xml.get(i)+";");  
			else 
				sb.append(";;");
			
			
			if (i<pli.size() )
				sb.append(pli.get(i)+";");  
			else 
				sb.append(";;");
			
			pw.println(sb.toString());
			
		} 
		
		
		
	}
	public static void generateExcelForUsers(ArrayList<XmlLine> xmlLineArr,PrintWriter pw) {
		// TODO Auto-generated method stub
		

		
		for (int i = 0; i < xmlLineArr.size(); i++) {
			
			String xml1 = xmlLineArr.get(i).toXMLString(true);
			
			ArrayList<String> ar = xmlLineArr.get(i).toPliString(true);
			pw.print(xml1);
			for (int j = 0; j < ar.size(); j++) {
				if(j==0)
					pw.println(ar.get(j));
				else
					pw.println(";"+ar.get(j));
				
			}
			if(ar.size()==0)
				pw.println();
			
		} 
		
		
		
	}

}
