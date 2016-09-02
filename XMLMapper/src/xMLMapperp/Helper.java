package xMLMapperp;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardCopyOption.*;

public class Helper {
	public static void prePack(PrintWriter pw,String messageName){
		pw.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		pw.println("<xsl:stylesheet version=\"1.0\""); 
		pw.println("            xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"");
		pw.println("			xmlns:dp=\"http://www.datapower.com/extensions\"");
		pw.println("			extension-element-prefixes=\"dp\">");

		pw.println("");

		pw.println("<dp:input-mapping href=\"local:///VPVP/T2SEFI/"+messageName+".handlers.ffd\" type=\"ffd\"/>");
		pw.println("<!-- ");
		pw.println("<xsl:preserve-space elements=\"/PLI_Msg\" />");
		pw.println(" -->");
		
		pw.println("");
		 
		pw.println("<xsl:template match=\"/PLI_Msg\">");

		pw.println("");
		pw.println("");

		pw.println("<xsl:message dp:priority=\"debug\">");
		pw.println("	****DEBUG MSG****************************************** ");
		pw.println(" 	<xsl:copy-of select=\"/\"/>"); 
		pw.println("</xsl:message>");

		pw.println("");
		
		
		
	}
	public static void postPack(PrintWriter pw,String messageName){

		pw.println("</xsl:template>");
		pw.println("</xsl:stylesheet>");

	}
	public static void preXslt(PrintWriter pw,String messageName){
		pw.println("<?xml version=\"1.0\" encoding=\"utf-8\"?> ");
		pw.println("<xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" xmlns:dp=\"http://www.datapower.com/extensions\" extension-element-prefixes=\"dp\"> ");
		pw.println("<xsl:output method=\"xml\" version=\"1\" encoding=\"UTF-8\" indent=\"yes\"/> ");
		pw.println("<dp:output-mapping href=\"local:///VPVP/T2SEFI/"+messageName+".handlers.ffd\" type=\"ffd\"/> ");
		pw.println("<xsl:template match=\"/\"> ");
		pw.println("<xsl:variable name=\"msg_ffd\" >");
	}
	public static void postXslt(PrintWriter pw,String messageName){
		pw.println("</xsl:variable>");

		pw.println("<PLI_Msg>");
		pw.println("<xsl:copy-of select=\"dp:variable('var://context/HEAD_FFD')\"/>");
		pw.println("<xsl:copy-of select=\"$msg_ffd\" /> ");  
		pw.println("</PLI_Msg>");
		pw.println("</xsl:template>");
		pw.println("</xsl:stylesheet>");	
	}
	public static  void preXML(PrintWriter pw,String message) {
		
		
		pw.println("<Env:RequestPayload xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:euroclearfi:iso20022:xsd:businessenvelope ISO20022BusinessEnvelope.xsd\" xmlns:Env=\"urn:euroclearfi:iso20022:xsd:businessenvelope\">"); 
		String s = "<AppHdr xmlns=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\" xsi:schemaLocation=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01 head.001.001.01.xsd\"><Fr><FIId><FinInstnId><BICFI>APKEFIH0XXX</BICFI></FinInstnId></FIId></Fr><To><FIId><FinInstnId><BICFI>DABAFIH0</BICFI></FinInstnId></FIId></To><BizMsgIdr>150408164199472</BizMsgIdr><MsgDefIdr>seev.041.001.04</MsgDefIdr><CreDt>2015-04-06T16:41:08Z</CreDt></AppHdr>";
        s=s.replaceAll("seev\\.041\\.001\\.04",message);
		pw.println(s);
		s = "<Document xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:iso:std:iso:20022:tech:xsd:seev.041.001.04 seev.041.001.04.xsd\" xmlns=\"urn:iso:std:iso:20022:tech:xsd:seev.041.001.04\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">";
        s=s.replaceAll("seev\\.041\\.001\\.04",message);
		pw.println(s);
		
	}
	public static void keysForXslt(PrintWriter pw){
		pw.println("<MsgTypeCodesDp>");
		pw.println("<EvtTp>");
        pw.println("<Cd>");
		pw.println("  <xsl:value-of select=\"/*[local-name()='Document']/*[local-name()='CorpActnNtfctn']/*[local-name()='CorpActnGnlInf']/*[local-name()='EvtTp']/*[local-name()='Cd']\" />");
		pw.println("</Cd>");
		pw.println("</EvtTp>");
		pw.println("<MndtryVlntryEvtTp>");
        pw.println("<Cd>");
		pw.println("  <xsl:value-of select=\"/*[local-name()='Document']/*[local-name()='CorpActnNtfctn']/*[local-name()='CorpActnGnlInf']/*[local-name()='MndtryVlntryEvtTp']/*[local-name()='Cd']\" />");
		pw.println("</Cd>");
		pw.println("</MndtryVlntryEvtTp>");
		pw.println("<EvtPrcgTp>");
        pw.println("<Cd>");
		pw.println("<xsl:value-of select=\"/*[local-name()='Document']/*[local-name()='CorpActnNtfctn']/*[local-name()='CorpActnGnlInf']/*[local-name()='EvtPrcgTp']/*[local-name()='Cd']\" />");
		pw.println("</Cd>");
		pw.println("</EvtPrcgTp>");
		pw.println("<MessageVersionDp>");
        pw.println("<Cd>"+XMLDOMMapper.messageVersion+"</Cd>");
		pw.println("</MessageVersionDp>");
		pw.println("</MsgTypeCodesDp>");
		
        
	}
	public static void keysForXslt35(PrintWriter pw){
		pw.println("<MsgTypeCodesDp>");
		pw.println("<EvtTp>");
        pw.println("<Cd>");
		pw.println("  <xsl:value-of select=\"/*[local-name()='Document']/*[local-name()='CorpActnMvmntPrlimryAdvc']/*[local-name()='CorpActnGnlInf']/*[local-name()='EvtTp']/*[local-name()='Cd']\" />");
		pw.println("</Cd>");
		pw.println("</EvtTp>");
		pw.println("<MndtryVlntryEvtTp>");
        pw.println("<Cd>");
		pw.println("  <xsl:value-of select=\"/*[local-name()='Document']/*[local-name()='CorpActnMvmntPrlimryAdvc']/*[local-name()='CorpActnGnlInf']/*[local-name()='MndtryVlntryEvtTp']/*[local-name()='Cd']\" />");
		pw.println("</Cd>");
		pw.println("</MndtryVlntryEvtTp>");
		pw.println("<EvtPrcgTp>");
        pw.println("<Cd>");
		pw.println("<xsl:value-of select=\"/*[local-name()='Document']/*[local-name()='CorpActnMvmntPrlimryAdvc']/*[local-name()='CorpActnGnlInf']/*[local-name()='EvtPrcgTp']/*[local-name()='Cd']\" />");
		pw.println("</Cd>");
		pw.println("</EvtPrcgTp>");
		pw.println("<MessageVersionDp>");
        pw.println("<Cd>"+XMLDOMMapper.messageVersion+"</Cd>");
		pw.println("</MessageVersionDp>");
		pw.println("</MsgTypeCodesDp>");
		
        
	}
	public static void keysForXslt36(PrintWriter pw){
		pw.println("<MsgTypeCodesDp>");
		pw.println("<EvtTp>");
        pw.println("<Cd>");
		pw.println("  <xsl:value-of select=\"/*[local-name()='Document']/*[local-name()='CorpActnMvmntConf']/*[local-name()='CorpActnGnlInf']/*[local-name()='EvtTp']/*[local-name()='Cd']\" />");
		pw.println("</Cd>");
		pw.println("</EvtTp>");
		pw.println("<MessageVersionDp>");
        pw.println("<Cd>"+XMLDOMMapper.messageVersion+"</Cd>");
		pw.println("</MessageVersionDp>");
		pw.println("</MsgTypeCodesDp>");
		
        
	}

	public static void keysForFfd(PrintWriter pw){
		pw.println("     <Group name=\"MsgTypeCodesDp\">");
		pw.println("       <Group name=\"EvtTp\">");
        pw.println("         <Field name=\"Cd\" length=\"4\" syntax=\"host\" type=\"String\" />");
		pw.println("       </Group>");
		pw.println("       <Group name=\"MndtryVlntryEvtTp\">");
        pw.println("         <Field name=\"Cd\" length=\"4\" syntax=\"host\" type=\"String\" />");
		pw.println("       </Group>");
		pw.println("       <Group name=\"EvtPrcgTp\">");
        pw.println("         <Field name=\"Cd\" length=\"4\" syntax=\"host\" type=\"String\" />");
		pw.println("       </Group>");
		pw.println("       <Group name=\"MessageVersionDp\">");
        pw.println("         <Field name=\"Cd\" length=\"5\" syntax=\"host\" type=\"String\" />");
		pw.println("       </Group>");
		pw.println("     </Group>");
		
        
	}
	public static void keysForFfd36(PrintWriter pw){
		pw.println("     <Group name=\"MsgTypeCodesDp\">");
		pw.println("       <Group name=\"EvtTp\">");
        pw.println("         <Field name=\"Cd\" length=\"4\" syntax=\"host\" type=\"String\" />");
		pw.println("       </Group>");
		pw.println("       <Group name=\"MessageVersionDp\">");
        pw.println("         <Field name=\"Cd\" length=\"5\" syntax=\"host\" type=\"String\" />");
		pw.println("       </Group>");
		pw.println("     </Group>");
		
        
	}
	public static void keysForPLI(PrintWriter pw){
		pw.println("    04  MsgTypeCodesDp,  /* Fx. "+XMLDOMMapper.EvtTp+"/"+XMLDOMMapper.MndtryVlntryEvtTp+"/"+XMLDOMMapper.EvtPrcgTp +" Vers."+XMLDOMMapper.messageVersion+" */" );
		pw.println("      06  EvtTp,");
        pw.println("        08 Cd                                              char(4),");
		pw.println("      06  MndtryVlntryEvtTp,");
        pw.println("        08 Cd                                              char(4),");
		pw.println("      06  EvtPrcgTp,"); 
        pw.println("        08 Cd                                              char(4),");
		pw.println("      06  MessageVersionDp,"); 
        pw.println("        08 Cd                                              char(5),");
		
        
	}
	public static void keysForPLI36(PrintWriter pw){
		pw.println("    04  MsgTypeCodesDp,  /* Fx. "+XMLDOMMapper.EvtTp + " Vers."+XMLDOMMapper.messageVersion+" */" );
		pw.println("      06  EvtTp,");
        pw.println("        08 Cd                                              char(4),");
		pw.println("      06  MessageVersionDp,"); 
        pw.println("        08 Cd                                              char(5),");
		
        
	}
	public static void PreFfd(PrintWriter pw){
		pw.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		pw.println("<File name=\"PLI_Msg\">");
		pw.println("<Syntax name=\"host\" encoding=\"UTF-8\" />");
		pw.println("<Group name=\"PLI_Head\">");
		pw.println("<Group name=\"Internal_Keys\">");
		pw.println("	<Field name=\"UniqueKey\" 	length=\"45\" syntax=\"host\" type=\"String\"/>");
		pw.println("    <Field name=\"System_Id\" 	length=\"8\"  syntax=\"host\" type=\"String\"/>");
		pw.println("    <Field name=\"Filler\" 		length=\"4\"  syntax=\"host\" type=\"String\"/>");
		pw.println("</Group>");

		pw.println("<Group name=\"Business_Application_Header\">");
		pw.println("	<Group name=\"From\">");
		pw.println("		<Field name=\"Bic\" 		length=\"11\" syntax=\"host\" type=\"String\"/>");
		pw.println("		<Field name=\"Id\" 		length=\"35\" syntax=\"host\" type=\"String\"/>");
		pw.println("	</Group>");
		pw.println("	<Group name=\"To\">");
		pw.println("		<Field name=\"Bic\" 		length=\"11\" syntax=\"host\" type=\"String\"/>");
		pw.println("		<Field name=\"Id\" 		length=\"35\" syntax=\"host\" type=\"String\"/>");
		pw.println("	</Group>	");
		pw.println("	<Field name=\"MsgId\" 		length=\"35\" syntax=\"host\" type=\"String\"/>");
		pw.println("	<Field name=\"MsgName\" 		length=\"35\" syntax=\"host\" type=\"String\"/>");
		pw.println("	<Field name=\"MsgDate\" 		length=\"20\" syntax=\"host\" type=\"String\"/>");
		pw.println("	<Field name=\"CopyMark\" 		length=\"4\" 	syntax=\"host\" type=\"String\"/>");
		pw.println("	<Field name=\"DupMark\"       length=\"1\"  syntax=\"host\" type=\"String\"/>");
		pw.println("	<Field name=\"Priority\" 		length=\"99\" syntax=\"host\" type=\"String\"/>");
		pw.println("	<Field name=\"Signature\" 	length=\"99\" syntax=\"host\" type=\"String\"/>");
		pw.println("	<Field name=\"Rel_MsgId\" 	length=\"35\" syntax=\"host\" type=\"String\"/>");
		pw.println("	<Field name=\"Rel_MsgName\"	length=\"35\" syntax=\"host\" type=\"String\"/>");
		pw.println("</Group>");
		pw.println("</Group>");		
	}
	public static void PostFfd(PrintWriter pw){
		pw.println("</File>");
    }

	public static void copyDirOrFile(Path in, Path out){
		CopyOption[] options = new CopyOption[] {} ;
		LinkOption[] lkoptions = new LinkOption[]{};
		

		Path pp = null;
			try {
					System.out.println(">>>>>>>>>>>>>>>>>>" + in);
					pp = Files.copy(in, out.resolve(in.getFileName()),StandardCopyOption.REPLACE_EXISTING);
					String groupName = "";
					if (XMLDOMMapper.messageName.equals("seev.031.001.05"))
						groupName = "_CorpActnNtfctn_Grp";
					if (XMLDOMMapper.messageName.equals("seev.035.001.05")||XMLDOMMapper.messageName.equals("seev.035.001.06"))
						groupName = "_CorpActnMvmntPrlimryAdvc";
			    	Files.copy(in,out.resolve(XMLDOMMapper.messageName+groupName+XMLDOMMapper.messageVersion.substring(0,2)+".xsd"),StandardCopyOption.REPLACE_EXISTING); 							
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
