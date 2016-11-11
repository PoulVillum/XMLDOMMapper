package xMLMapperp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.XmlList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLDOMMapper {

	/**
	 * @param args
	 */
	
	private Document xmlDoc;
	private XPathExpression expr;
	private XPath xpath;
	private ArrayList<String> patternList = new ArrayList<String>();
	private PatternMap patternMap = new PatternMap();
	private int indent;
	private boolean tagStarted;
	private StringBuffer stringBuffer = null;
	private ArrayList<String> xmlAL =  new ArrayList<String>();
	private ArrayList<Integer> xmlNo =  new ArrayList<Integer>();
	private ArrayList<String> pathAr = new ArrayList<String>();
	public static Deque<ChoiceData> choiceDec = new ArrayDeque<ChoiceData>();

	
	private int level;
	private StringBuffer pliBuffer = null;
	private ArrayList<String> pliAL = new ArrayList<String>();
	private ArrayList<Integer> pliNo = new ArrayList<Integer>();
	private boolean toBeMapped;
	private int mapNo;
	private XmlLine xmlLine;
	private ArrayList<XmlLine> xmlLineArl = new ArrayList<XmlLine>();
	PrintWriter pwXml = null;
	PrintWriter pwPLI = null;
	PrintWriter pwExcel = null;
	PrintWriter pwXslt = null;
	PrintWriter pwPack = null;
	PrintWriter pwFfd = null;
	PrintWriter pwData = null;
	private ArrayList<XsltLine> xsltLineArl = new ArrayList<XsltLine>();
	private ArrayList<FfdData> ffdDataArl = new ArrayList<FfdData>();
	private StringBuffer ffdBuffer;
	private Node currentElementNode;
	private FfdData currentFfd;
	private FfdData currentFfdData;
	private int currentChoices;
	private int currentChoiceNo;
	private static int iNo = 0;
	public static String messageName;
	public static String EvtPrcgTp;
	public static String EvtTp;
	public static String MndtryVlntryEvtTp;
	public static String messageVersion;
	public static boolean normalMode = true;
	public static boolean incoming = true;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		String s = "dsdsdsfdgseev.041hhhhdhdhdhseev.041llll";
//		s = s.replaceAll("seev\\.041","seev.031");
//		System.out.println(s);

         XMLDOMMapper xmlmap= new XMLDOMMapper();



	}
	public XMLDOMMapper(){
	    DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	    domFactory.setNamespaceAware(true); // never forget this!
	    DocumentBuilder builder = null;
		try {
			builder = domFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XMLDOMMapper.incoming = true;
//		File fXml = new File("c:\\app\\EFI_-_CA_-_Securities_Events_(Notification_&_Cance...-CorporateActionNotificationV05_seev.031.001.05_RightsExercise_CHOS-20151125.xsd");
//		File fXml = new File("c:\\app\\EFI_-_CA_-_Securities_Events_(Notification_&_Cance...-CorporateActionNotificationV05_seev.031.001.05_CashDistribution_MAND-20151209.xsd");
//		File fXml = new File("c:\\app\\EFI_-_CA_-_Securities_Events_(Notification_&_Cance...-CorporateActionNotificationV05_seev.031.001.05_CashDistribution_CHOS-20160108 (1).xsd");
//		File fXml = new File("c:\\app\\EFI_-_CA_-_Securities_Events_(Notification_&_Cance...-CorporateActionNotificationV05_seev.031.001.05_CashDistribution_MAND-20160112.xsd");
//		File fXml = new File("c:\\app\\EFI_-_CA_-_Securities_Events_(Notification_&_Cance...-CorporateActionNotificationV05_seev.031.001.05_SecurityReorg_MAND-20160112.xsd");
//		File fXml = new File("c:\\app\\EFI-_CA-_Securities_Events_(Notification___Cance_CorporateActionNotificationV05_seev_031_001_05_OtherEventTypes_20160114_2041.xsd");
//		File fXml = new File("c:\\app\\EFI-_CA-_Securities_Events_(Notification___Cance_CorporateActionNotificationV05_seev_031_001_05_ReorgwithSecurityDistribution_MAN_20160116_1514.xsd");
//		File fXml = new File("c:\\app\\EFI-_CA-_Securities_Events_(Notification___Cance_CorporateActionNotificationV05_seev_031_001_05_SecurityReorg_MAND_20160116_1516.xsd");
//		File fXml = new File("c:\\app\\EFI-_CA-_Securities_Events_(Notification___Cance_CorporateActionNotificationV05_seev_031_001_05_VoluntaryEvents_20160116_1517.xsd");
//		File fXml = new File("c:\\app\\EFI-_CA-_Securities_Events_(Notification___Cance_CorporateActionNotificationV05_seev_031_001_05_SecurityDistribution_MAND_20160119_2033.xsd");
//		File fXml = new File("c:\\app\\EFI-_CA-_Securities_Events_(Movement_Advice___Co_CorporateActionMovementPreliminaryAdviceV04_seev_035_001_05_ReorgwithSecurityDis_20160126_2122.xsd");
//		File fXml = new File("c:\\app\\EFI-_SR-_Securities_Settlement-_V2_5-_FINAL__SecuritiesSettlementTransactionAllegementNotificationV03_sese_028_001_03_20160208_1541.xsd");
//		File fXml = new File("c:\\app\\EFI-_SR-_Securities_Settlement-_V2_5-_FINAL__SecuritiesSettlementAllegementRemovalAdviceV03_sese_029_001_03_20160208_1728.xsd");
//		File fXml = new File("c:\\app\\EFI-_CA-_Securities_Events_(Movement_Advice___Co_CorporateActionMovementConfirmationV05_seev_036_001_05_RightsExercise_CHOS_20160127_2337.xsd");
//		File fXml = new File("c:\\app\\EFI-_CA-_Securities_Events_(Movement_Advice___Co_CorporateActionMovementConfirmationV05_seev_036_001_05_CashDistribution_MAND_20160127_2336.xsd");
//		File fXml = new File("c:\\app\\EFI-_CA-_Securities_Events_(Movement_Advice___Co_CorporateActionMovementConfirmationV05_seev_036_001_05_CashDistribution_CHOS_20160127_2335.xsd");
//		File fXml = new File("c:\\app\\EFI-_CA-_Securities_Events_(Movement_Advice___Co_CorporateActionMovementConfirmationV05_seev_036_001_05_CashDistribution_MAND_20160210_2101Rettet.xsd");
//		File fXml = new File("c:\\app\\seev.036.001.05.xsd");
//		File fXml = new File("c:\\app\\EFIXSD\\EFI-_CA-_Securities_Events_%28Notification___Cance_CorporateActionNotificationV05_seev_031_001_05_VoluntaryEvents_20160811_2203.xsd");
//		File fXml = new File("c:\\app\\EFIXSD\\EFI-_CA-_Securities_Events_%28Notification___Cance_CorporateActionNotificationV05_seev_031_001_05_RightsExercise_CHOS_20160814_2225.xsd");
//		File fXml = new File("c:\\app\\EFIXSD\\EFI-_CA-_Securities_Events_%28Notification___Cance_CorporateActionNotificationV05_seev_031_001_05_VoluntaryEvents_20160815_2053.xsd");
//		File fXml = new File("c:\\app\\EFIXSD\\EFI-_CA-_Securities_Events_%28Notification___Cance_CorporateActionNotificationV05_seev_031_001_05_CashDistribution_MAND_20160815_2136.xsd");
//		File fXml = new File("c:\\app\\EFIXSD\\EFI-_CA-_Securities_Events_%28Notification___Cance_CorporateActionNotificationV05_seev_031_001_05_CashDistribution_CHOS_20160815_2148.xsd");
//		File fXml = new File("c:\\app\\EFIXSD\\EFI-_CA-_Securities_Events_%28Notification___Cance_CorporateActionNotificationV05_seev_031_001_05_SecurityReorg_MAND_20160815_2149.xsd");
//		File fXml = new File("c:\\app\\EFIXSD\\EFI-_CA-_Securities_Events_%28Notification___Cance_CorporateActionNotificationV05_seev_031_001_05_OtherEventTypes_20160815_2151.xsd");
//		File fXml = new File("c:\\app\\EFIXSD\\EFI-_CA-_Securities_Events_%28Notification___Cance_CorporateActionNotificationV05_seev_031_001_05_ReorgwithSecurityDistribution_MAN_20160815_2152.xsd");
//		File fXml = new File("c:\\app\\EFIXSD\\EFI-_CA-_Securities_Events_%28Notification___Cance_CorporateActionNotificationV05_seev_031_001_05_SecurityDistribution_MAND_20160816_2143.xsd");

		//   Seev.035.001.05  */
//		File fXml = new File("c:\\app\\EFI-_CA-_Securities_Events_(Movement_Advice___Co_CorporateActionMovementPreliminaryAdviceV04_seev_035_001_05_ReorgwithSecurityDis_20160223_2036.xsd");
//		File fXml = new File("c:\\app\\EFI-_CA-_Securities_Events_(Movement_Advice___Co_CorporateActionMovementPreliminaryAdviceV04_seev_035_001_05_SecurityDistribution_20160223_2037.xsd");
//		File fXml = new File("c:\\app\\EFI-_CA-_Securities_Events_(Movement_Advice___Co_CorporateActionMovementPreliminaryAdviceV04_seev_035_001_05_SecurityReorg_MAND_20160223_2038.xsd");
//		File fXml = new File("c:\\app\\EFI-_CA-_Securities_Events_(Movement_Advice___Co_CorporateActionMovementPreliminaryAdviceV05_seev_035_001_05_CashDistribution_CHO_20160223_2039.xsd");
//		File fXml = new File("c:\\app\\EFI-_CA-_Securities_Events_(Movement_Advice___Co_CorporateActionMovementPreliminaryAdviceV05_seev_035_001_05_CashDistribution_MAN_20160223_2040.xsd");
	
//      seev.034
//		File fXml = new File("c:\\app\\EFI-_CA-_Securities_Events_(Option_Instruction)__CorporateActionInstructionStatusAdviceV05_seev_034_001_05_20160302_2118.xsd");
//		seev.034.001.06	
//		XMLDOMMapper.incoming = true;
//		File fXml = new File("d:\\ab\\EFI-_CA-_Securities_Events_%28Option_Instruction%29__CorporateActionInstructionStatusAdviceV06_seev_034_001_06_20160902_1800.xsd");
		
		
		// seev.036 - 08
//		File fXml = new File("c:\\app\\EFI-_CA-_Securities_Events_(Movement_Advice___Co_CorporateActionMovementConfirmationV05_seev_036_001_05_VoluntaryEvents_20160217_2012.xsd");
//		File fXml = new File("c:\\app\\EFI-_CA-_Securities_Events_(Movement_Advice___Co_CorporateActionMovementConfirmationV05_seev_036_001_05_ReorgwithSecurityDistribu_20160217_2012.xsd");
//		File fXml = new File("c:\\app\\seev.036.001.05.xsd");
//		File fXml = new File("c:\\app\\EFI-_CA-_Securities_Events_(Movement_Advice___Co_CorporateActionMovementConfirmationV05_seev_036_001_05_ReorgwithSecurityDistribu_20160217_2012_rettet.xsd");

		// seev.036 - 02
//		File fXml = new File("c:\\app\\efixsd\\EFI-_CA-_Securities_Events_%28Movement_Advice___Co_CorporateActionMovementConfirmationV05_seev_036_001_05_CashDistribution_CHOS_20160315_1749.xsd");
//		File fXml = new File("c:\\app\\efixsd\\EFI-_CA-_Securities_Events_%28Movement_Advice___Co_CorporateActionMovementConfirmationV05_seev_036_001_05_CashDistribution_MAND_20160315_1749.xsd");
//		File fXml = new File("c:\\app\\efixsd\\seev.036.001.05.xsd");
//		File fXml = new File("c:\\app\\efixsd\\EFI-_CA-_Securities_Events_%28Movement_Advice___Co_CorporateActionMovementConfirmationV05_seev_036_001_05_CashDistribution_CHOS_20160315_1749 - rettetaaaa.xsd");

		// seev.036 - 05
//		File fXml = new File("c:\\app\\efixsd\\EFI-_CA-_Securities_Events_%28Movement_Advice___Co_CorporateActionMovementConfirmationV05_seev_036_001_05_RightsExercise_CHOS_20160317_1836.xsd");
		// seev.036 - 06
//		File fXml = new File("c:\\app\\efixsd\\EFI-_CA-_Securities_Events_(Movement_Advice___Co_CorporateActionMovementConfirmationV05_seev_036_001_05_SecurityDistribution_MAND_20160321_2116.xsd");
		// seev.036 - 07
//		File fXml = new File("c:\\app\\efixsd\\EFI-_CA-_Securities_Events_(Movement_Advice___Co_CorporateActionMovementConfirmationV05_seev_036_001_05_SecurityReorg_MAND_20160321_2117.xsd");

		
		// sese.030 - 1
//		XMLDOMMapper.incoming = false;
//		File fXml = new File("c:\\app\\efixsd\\EFI-_SR-_Securities_Settlement-_V2_6-_FINAL__SecuritiesSettlementConditionsModificationRequestV03_sese_030_001_03_20160613_1758.xsd");

		
//		// sese.031 - 1
//		XMLDOMMapper.incoming = true;
//		File fXml = new File("c:\\app\\efixsd\\EFI-_SR-_Securities_Settlement-_V2_6-_FINAL__SecuritiesSettlementConditionModificationStatusAdviceV03_sese_031_001_03_20160613_1757.xsd");

		
		// semt.020 - 1
//		File fXml = new File("c:\\app\\xsd\\EFI-_SR-_Securities_Management-_V2_4-_FINAL__SecuritiesMessageCancellationAdviceV03_semt_020_001_03_20160418_1959.xsd");
		
		// sese.032 - 1
//		File fXml = new File("c:\\app\\xsd\\EFI-_SR-_Securities_Settlement-_V2_5-_FINAL__SecuritiesSettlementTransactionGenerationNotificationV03_sese_032_001_03_20160317_1145-extended.xsd");
		// sese.032 - 2.6
//		File fXml = new File("c:\\app\\efixsd\\EFI-_SR-_Securities_Settlement-_V2_6-_FINAL__SecuritiesSettlementTransactionGenerationNotificationV03_sese_032_001_03_Claims__20160516_2057.xsd");
//		File fXml = new File("c:\\app\\efixsd\\EFI-_SR-_Securities_Settlement-_V2_6-_FINAL__SecuritiesSettlementTransactionGenerationNotificationV03_sese_032_001_03_MKUP_MK_20160516_2057.xsd");
//		File fXml = new File("c:\\app\\efixsd\\EFI-_SR-_Securities_Settlement-_V2_6-_FINAL__SecuritiesSettlementTransactionGenerationNotificationV03_sese_032_001_03_PORT_20160516_2058.xsd");
//		File fXml = new File("c:\\app\\efixsd\\EFI-_SR-_Securities_Settlement-_V2_6-_FINAL__SecuritiesSettlementTransactionGenerationNotificationV03_sese_032_001_03_CORP_20160516_2059.xsd");
//		File fXml = new File("c:\\app\\efixsd\\sese.032.001.03A.xsd");
//		XMLDOMMapper.incoming = true;
		XMLDOMMapper.incoming = true;
//		File fXml = new File("c:\\app\\efixsd\\EFI-_SR-_Securities_Settlement-_V2_7-_FINAL__SecuritiesSettlementTransactionGenerationNotificationV03_sese_032_001_03_PORT_20160914_2053.xsd");
//		File fXml = new File("c:\\app\\efixsd\\EFI-_SR-_Securities_Settlement-_V2_7-_FINAL__SecuritiesSettlementTransactionGenerationNotificationV03_sese_032_001_03_MKUP_MK_20160914_2053.xsd");
//		File fXml = new File("c:\\app\\efixsd\\EFI-_SR-_Securities_Settlement-_V2_7-_FINAL__SecuritiesSettlementTransactionGenerationNotificationV03_sese_032_001_03_CORP_20160914_2053.xsd");
//		File fXml = new File("c:\\app\\efixsd\\EFI-_SR-_Securities_Settlement-_V2_7-_FINAL__SecuritiesSettlementTransactionGenerationNotificationV03_sese_032_001_03_Claims__20160914_2053.xsd");
		File fXml = new File("c:\\app\\efixsd\\sese.032.001.03A.xsd");
		
		// sese.036 new method
//		File fXml = new File("c:\\app\\efixsd\\EFI-_CA-_Securities_Events_(Movement_Advice___Co_CorporateActionMovementConfirmationV05_seev_036_001_05_CashDistribution_CHOS_20160511_2149.xsd");
//		File fXml = new File("c:\\app\\efixsd\\EFI-_CA-_Securities_Events_(Movement_Advice___Co_CorporateActionMovementConfirmationV05_seev_036_001_05_CashDistribution_MAND_20160511_2150.xsd");
//		File fXml = new File("c:\\app\\efixsd\\EFI-_CA-_Securities_Events_(Movement_Advice___Co_CorporateActionMovementConfirmationV05_seev_036_001_05_ReorgwithSecurityDistribu_20160511_2151.xsd");
//		File fXml = new File("c:\\app\\efixsd\\EFI-_CA-_Securities_Events_(Movement_Advice___Co_CorporateActionMovementConfirmationV05_seev_036_001_05_RightsExercise_CHOS_20160511_2151.xsd");
//		File fXml = new File("c:\\app\\efixsd\\EFI-_CA-_Securities_Events_(Movement_Advice___Co_CorporateActionMovementConfirmationV05_seev_036_001_05_SecurityDistribution_MAND_20160511_2152.xsd");
//		File fXml = new File("c:\\app\\efixsd\\EFI-_CA-_Securities_Events_(Movement_Advice___Co_CorporateActionMovementConfirmationV05_seev_036_001_05_SecurityReorg_MAND_20160511_2152.xsd");
//		File fXml = new File("c:\\app\\efixsd\\EFI-_CA-_Securities_Events_(Movement_Advice___Co_CorporateActionMovementConfirmationV05_seev_036_001_05_VoluntaryEvents_20160511_2153.xsd");
//		File fXml = new File("c:\\app\\efixsd\\seev.036.001.05.xsd");	
		
		
		// seev.033  2.5  
//		File fXml = new File("c:\\app\\efixsd\\EFI-_CA-_Securities_Events_%28Option_Instruction%29__CorporateActionInstructionV05_seev_033_001_05_20160516_1956.xsd");XMLDOMMapper.incoming=false;

		//seev.041.001.04
//		XMLDOMMapper.incoming = true;
//		xmlDoc = builder.parse("c:\\app\\EFI_-_CA_-_Securities_Events_(Option_Instruction)_...-CorporateActionInstructionCancellationRequestStatusAdviceV04_seev.041.001.04-20151117.xsd");
//		File fXml = new File("c:\\app\\efixsd\\EFI-_CA-_Securities_Events_%28Option_Instruction%29__CorporateActionInstructionCancellationRequestStatusAdviceV04_seev_041_001_04_20160703_1836.xsd");
		

		
		//seev.035.001.06
//		XMLDOMMapper.incoming = true;
//		File fXml = new File("c:\\app\\efixsd\\EFI-_CA-_Securities_Events_%28Movement_Advice___Co_CorporateActionMovementPreliminaryAdviceV06_seev_035_001_06_SecurityDistribution_20160828_2015.xsd");
//		File fXml = new File("c:\\app\\efixsd\\EFI-_CA-_Securities_Events_%28Movement_Advice___Co_CorporateActionMovementPreliminaryAdviceV06_seev_035_001_06_CashDistribution_MAN_20160828_2015-rettet.xsd");
//		File fXml = new File("c:\\app\\efixsd\\EFI-_CA-_Securities_Events_%28Movement_Advice___Co_CorporateActionMovementPreliminaryAdviceV06_seev_035_001_06_ReorgwithSecurityDis_20160828_2015.xsd");
//		File fXml = new File("c:\\app\\efixsd\\EFI-_CA-_Securities_Events_%28Movement_Advice___Co_CorporateActionMovementPreliminaryAdviceV06_seev_035_001_06_CashDistribution_CHO_20160828_2016.xsd");
//		File fXml = new File("c:\\app\\efixsd\\EFI-_CA-_Securities_Events_%28Movement_Advice___Co_CorporateActionMovementPreliminaryAdviceV06_seev_035_001_06_SecurityReorg_MAND_20160828_2016.xsd");
		
		
		
		XmlPathSet.readXmlPathSet();
		
//		XmlPathSet.getXps().clear();
		XMLDOMMapper.normalMode = false;
        XmlElement.setSourceFile(fXml);
        
        
		
		xmlDoc = null; 
		try {
// 			xmlDoc = builder.parse("c:\\app\\seev.031.001.04-pvea.xsd");
//			xmlDoc = builder.parse("c:\\app\\seev.039.001.04-pvea.xsd");
//			xmlDoc = builder.parse("c:\\app\\seev.036.001.04-pvea.xsd");
//			xmlDoc = builder.parse("c:\\app\\sese.023.001.03.xsd");
//			xmlDoc = builder.parse("c:\\app\\EFI_-_SR_-_Securities_Settlement_-_V2.2_-_FINAL_-SecuritiesTransactionCancellationRequestV03_sese.020.001.03-20150911.xsd");
//			xmlDoc = builder.parse("c:\\app\\seev.039.001.04.xsd");
//			xmlDoc = builder.parse("c:\\app\\efi\\EFI_-_CA_-_Securities_Events_(Notification_&_Cance...-CorporateActionCancellationAdviceV05_seev.039.001.05-20150913.xsd");
//			xmlDoc = builder.parse("c:\\app\\efi\\EFI-seev.033.001.05-20150914.xsd");
//			xmlDoc = builder.parse("c:\\app\\efi-v2\\EFI_-_CA_-_Securities_Events_(Option_Instruction)_...-CorporateActionInstructionV05_seev.033.001.05-20150916.xsd");
//			xmlDoc = builder.parse("c:\\app\\efi-v2\\EFI_-_CA_-_Securities_Events_(Option_Instruction)_...-CorporateActionInstructionStatusAdviceV05_seev.034.001.05-20150916.xsd");
///    		xmlDoc = builder.parse("c:\\app\\efi-v2\\EFI_-_SR_-_Securities_Settlement_-_V2.2_-_FINAL_-SecuritiesTransactionCancellationRequestV03_sese.020.001.03-20150928.xsd");
//     		xmlDoc = builder.parse("c:\\app\\EFI_-_CA_-_Securities_Events_(Notification_&_Cance...-CorporateActionNotificationV05_seev.031.001.05_CashDistribution_MAND-20151109.xsd");
//    		xmlDoc = builder.parse("c:\\app\\EFI_-_CA_-_Securities_Events_(Option_Instruction)_...-CorporateActionInstructionCancellationRequestV05_seev.040.001.05-20151117.xsd");
//    		xmlDoc = builder.parse("c:\\app\\EFI_-_CA_-_Securities_Events_(Notification_&_Cance...-CorporateActionNotificationV05_seev.031.001.05_CashDistribution_CHOS-20151109.xsd");
//    		xmlDoc = builder.parse("c:\\app\\efi-v2\\mymap\\seev.039\\EFI_-_CA_-_Securities_Events_(Notification_&_Cance...-CorporateActionCancellationAdviceV05_seev.039.001.05-20151125.xsd");
//    		xmlDoc = builder.parse("c:\\app\\EFI_-_CA_-_Securities_Events_(Notification_&_Cance...-CorporateActionNotificationV05_seev.031.001.05_RightsExercise_CHOS-20151125.xsd");
			xmlDoc = builder.parse(fXml);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	    XPathFactory factory = XPathFactory.newInstance();
	    xpath = factory.newXPath();
	    expr = null;
	    NodeList result = null;
	    xpath.setNamespaceContext(new myNamespaceContent()	);
		try {
			String t0 = "//xs:schema";
 			expr = xpath.compile(t0);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			result = (NodeList) expr.evaluate(xmlDoc, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    String ss1 = result.item(0).getAttributes().getNamedItem("xmlns").getNodeValue();
	    String[] messageName = ss1.split(":");
	    System.out.println(">>"+messageName[messageName.length-1]);
	    XMLDOMMapper.messageName = messageName[messageName.length-1];
	    
	    


//		String name = atrList.getNamedItem("name").getTextContent();

		try {
			String t0 = "//xs:element[@name='Document']";
 			expr = xpath.compile(t0);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			result = (NodeList) expr.evaluate(xmlDoc, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		handleNode(result.item(0));
		
		
		FileOutputStream fosPLI = null;
		FileOutputStream fosXml = null;
		FileOutputStream fosXslt = null;
		FileOutputStream fosExcel = null;
		FileOutputStream fosPack = null;
		FileOutputStream fosFfd = null;
		FileOutputStream fosData = null;
		String fileNames = messageName[messageName.length-1];
		XMLDOMMapper.messageVersion = "00.00";
		if (fileNames.equals("seev.031.001.05")){
				if (XMLDOMMapper.EvtTp.equals("EXRI")&&XMLDOMMapper.MndtryVlntryEvtTp.equals("CHOS") && XMLDOMMapper.EvtPrcgTp.equals("REOR"))
					XMLDOMMapper.messageVersion = "05.00";
				else
					if (XMLDOMMapper.EvtTp.equals("DVCA")&&XMLDOMMapper.MndtryVlntryEvtTp.equals("CHOS") && XMLDOMMapper.EvtPrcgTp.equals("DISN"))
						XMLDOMMapper.messageVersion = "01.00";
					else
						if (XMLDOMMapper.EvtTp.equals("CAPD")&&XMLDOMMapper.MndtryVlntryEvtTp.equals("MAND") && XMLDOMMapper.EvtPrcgTp.equals("DISN"))
							XMLDOMMapper.messageVersion = "02.00";
						else
							if (XMLDOMMapper.EvtTp.equals("BRUP")&&XMLDOMMapper.MndtryVlntryEvtTp.equals("MAND") && XMLDOMMapper.EvtPrcgTp.equals("GENL"))
								XMLDOMMapper.messageVersion = "03.00";
							else
								if (XMLDOMMapper.EvtTp.equals("CONV")&&XMLDOMMapper.MndtryVlntryEvtTp.equals("MAND") && XMLDOMMapper.EvtPrcgTp.equals("REOR"))
									XMLDOMMapper.messageVersion = "04.00";
								else
									if (XMLDOMMapper.EvtTp.equals("BONU")&&XMLDOMMapper.MndtryVlntryEvtTp.equals("MAND") && XMLDOMMapper.EvtPrcgTp.equals("DISN"))
										XMLDOMMapper.messageVersion = "06.00";
									else
										if (XMLDOMMapper.EvtTp.equals("REDM")&&XMLDOMMapper.MndtryVlntryEvtTp.equals("MAND") && XMLDOMMapper.EvtPrcgTp.equals("REOR"))
											XMLDOMMapper.messageVersion = "07.00";
										else
											if (XMLDOMMapper.EvtTp.equals("CONS")&&XMLDOMMapper.MndtryVlntryEvtTp.equals("VOLU") && XMLDOMMapper.EvtPrcgTp.equals("GENL"))
												XMLDOMMapper.messageVersion = "08.00";
											else{
												System.out.println("seev031grp not found "+XMLDOMMapper.EvtTp+" "+XMLDOMMapper.MndtryVlntryEvtTp+" "+XMLDOMMapper.EvtPrcgTp);
										        System.exit(-2);
											}
				fileNames = fileNames +"_CorpActnNtfctn_Grp" + XMLDOMMapper.messageVersion.substring(0,2) ;
		}
		if (fileNames.equals("seev.035.001.05")||fileNames.equals("seev.035.001.06")){
			if (XMLDOMMapper.EvtTp.equals("EXRI")&&XMLDOMMapper.MndtryVlntryEvtTp.equals("CHOS") && XMLDOMMapper.EvtPrcgTp.equals("REOR"))
				XMLDOMMapper.messageVersion = "05.00";
			else
				if (XMLDOMMapper.EvtTp.equals("DVCA")&&XMLDOMMapper.MndtryVlntryEvtTp.equals("CHOS") && XMLDOMMapper.EvtPrcgTp.equals("DISN"))
					XMLDOMMapper.messageVersion = "01.00";
				else
					if (XMLDOMMapper.EvtTp.equals("CAPD")&&XMLDOMMapper.MndtryVlntryEvtTp.equals("MAND") && XMLDOMMapper.EvtPrcgTp.equals("DISN"))
						XMLDOMMapper.messageVersion = "02.00";
					else
						if (XMLDOMMapper.EvtTp.equals("BRUP")&&XMLDOMMapper.MndtryVlntryEvtTp.equals("MAND") && XMLDOMMapper.EvtPrcgTp.equals("GENL"))
							XMLDOMMapper.messageVersion = "03.00";
						else
							if (XMLDOMMapper.EvtTp.equals("CONV")&&XMLDOMMapper.MndtryVlntryEvtTp.equals("MAND") && XMLDOMMapper.EvtPrcgTp.equals("REOR"))
								XMLDOMMapper.messageVersion = "04.00";
							else
								if (XMLDOMMapper.EvtTp.equals("BONU")&&XMLDOMMapper.MndtryVlntryEvtTp.equals("MAND") && XMLDOMMapper.EvtPrcgTp.equals("DISN"))
									XMLDOMMapper.messageVersion = "06.00";
								else
									if (XMLDOMMapper.EvtTp.equals("REDM")&&XMLDOMMapper.MndtryVlntryEvtTp.equals("MAND") && XMLDOMMapper.EvtPrcgTp.equals("REOR"))
										XMLDOMMapper.messageVersion = "07.00";
									else
										if (XMLDOMMapper.EvtTp.equals("CONS")&&XMLDOMMapper.MndtryVlntryEvtTp.equals("VOLU") && XMLDOMMapper.EvtPrcgTp.equals("REOR"))
											XMLDOMMapper.messageVersion = "08.00";
										else
										{
											System.out.println("sevv035gruppe not found "+XMLDOMMapper.EvtTp);
										    System.exit(-2);
										    
										}
		
			fileNames = fileNames +"_CorpActnMvmntPrlimryAdvc_Grp" + XMLDOMMapper.messageVersion.substring(0,2) ;
    	}
		if (fileNames.equals("seev.036.001.05")){
			String tst = null;
			if (XmlPathSet.getXps().get("EvtTp") ==null)
				tst = "";
			else
				tst = XmlPathSet.getXps().get("EvtTp").EvtTp;
				
			
			if (XMLDOMMapper.EvtTp.equals("DVCA")||tst.equals("CAPD")||XMLDOMMapper.EvtTp.equals("CAPD"))
				XMLDOMMapper.messageVersion = "02.00";
			else
				if (XMLDOMMapper.EvtTp.equals("EXRI"))
					XMLDOMMapper.messageVersion = "05.00";
				else
					if (XMLDOMMapper.EvtTp.equals("REDM"))
						XMLDOMMapper.messageVersion = "07.00";
					else
						if (XMLDOMMapper.EvtTp.equals("BONU"))
							XMLDOMMapper.messageVersion = "06.00";
						else
							if (XMLDOMMapper.EvtTp.equals("CONS")||tst.equals("CONS")||XMLDOMMapper.EvtTp.equals("CONV"))
								XMLDOMMapper.messageVersion = "08.00";
							else
								XMLDOMMapper.messageVersion = "00.00";;
			XMLDOMMapper.messageVersion = "00.00";					
			fileNames = fileNames +"_CorpActnMvmntConf_Grp" + XMLDOMMapper.messageVersion.substring(0,2) ;
    	}
		File xmlFile = new File("C:\\app\\t2sefi\\"+fileNames+"\\"+fileNames+".xml");
		File xmlPLI = new File("C:\\app\\t2sefi\\"+fileNames+"\\"+fileNames+".PLI.txt");
		File xmlXslt = new File("C:\\app\\t2sefi\\"+fileNames+"\\"+fileNames+".parse.xslt");
		File xmlExcl = new File("C:\\app\\t2sefi\\"+fileNames+"\\"+fileNames+".csv");
		File xmlPack = new File("C:\\app\\t2sefi\\"+fileNames+"\\"+fileNames+".pack.xslt");
		File xmlFfd = new File("C:\\app\\t2sefi\\"+fileNames+"\\"+fileNames+".handlers.ffd");
		File xmlData = new File("C:\\app\\t2sefi\\"+fileNames+"\\"+fileNames+".data.txt");
		new File(xmlFile.getParent()).mkdirs();	
		
		Helper.copyDirOrFile(Paths.get(fXml.getAbsolutePath()), Paths.get(xmlFile.getParent()));
		


		try {
			fosXml = new FileOutputStream(xmlFile);
			pwXml = new PrintWriter(fosXml);
			fosPLI = new FileOutputStream(xmlPLI);
			pwPLI = new PrintWriter(fosPLI);
			fosXslt = new FileOutputStream(xmlXslt);
			pwXslt = new PrintWriter(fosXslt);
			fosExcel = new FileOutputStream(xmlExcl);
			pwExcel = new PrintWriter(fosExcel);
			fosPack = new FileOutputStream(xmlPack);
			pwPack = new PrintWriter(fosPack);
			fosFfd = new FileOutputStream(xmlFfd);
			pwFfd = new PrintWriter(fosFfd);
			fosData = new FileOutputStream(xmlData);
			pwData = new PrintWriter(fosData);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	    if (stringBuffer!=null)
	    	if (stringBuffer.length()> 0 )
	    		xmlAL.add(stringBuffer.toString());

	    int lastXmlNo = 0;
	    for (int i = 0; i < pliAL.size() || i < xmlAL.size(); i++) {
	    	StringBuffer sb = new StringBuffer();
	    	sb.append(";;;;");
	    	if (i < xmlAL.size() )
	    		sb.append(xmlAL.get(i));
	    	sb.append(";;;"	);
	    	if (i < xmlNo.size() )
	    		if(xmlNo.get(i).intValue()> 0) {
	    			if (xmlNo.get(i).intValue()-1==lastXmlNo)
	    				sb.append(xmlNo.get(i).intValue());
	    			else
	    				sb.append((xmlNo.get(i).intValue()-1)+","+xmlNo.get(i).intValue());
	    			lastXmlNo = xmlNo.get(i).intValue();
	    		}
	    			
	    	
	    	sb.append(";;;");
	    	if (i < pliNo.size() )
	    		if(pliNo.get(i).intValue()> 0) 
	    		sb.append(pliNo.get(i).intValue());
	    	sb.append(";"	);
	    	if (i < pliAL.size() )
	    		sb.append(pliAL.get(i));
	    	sb.append(";;;"	);
//	    	System.out.println(sb.toString());
			
		}
		
		
		for (int i = 0; i < patternList.size(); i++) {
			System.out.println("-->"+patternList.get(i));
			
		}
//		XmlLine.generateMapping(xmlLineArl);
		
		
//		System.out.println("elementer "+xmlLineArl.size());
		
//		for (int i = 0; i < xmlLineArl.size(); i++) {
//			xmlLineArl.get(i).printAsXml(pwXml);
//		}
		
		
		
//		for (int i = 0; i < xmlLineArl.size(); i++) {
//			xmlLineArl.get(i).printAsPli(pwPLI);
//		}
		
		
//		for (Iterator<XmlLine> iterator = xmlLineArl.iterator(); iterator.hasNext();) {
//			
//			
//		}
		
		
//	    XsltLine.generateXslt(xsltLineArl,pwXslt);
		


		XmlLine.generateExcelForUsers(xmlLineArl, pwExcel);
		
		
		
		for (int i = 0; i < ffdDataArl.size(); i++) {
//				ffdDataArl.get(i).formatPack(pwPack);
			
		}
		

		

		
		
		for (int i = 0; i < ffdDataArl.size(); i++) {
//				ffdDataArl.get(i).formatHandler(pwFfd);
			
		}
		
		
		XmlElement.setIndent(2);
		pwXml.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		XmlElement.traverseElementsForXml(XmlElement.root, pwXml);
		pwXml.println("</Env:RequestPayload>");
		
		XmlElement.setLevel(2);
		XmlElement.setIndent(2);
		XmlElement.traverseElementsForPli(XmlElement.root,pwPLI);

		XmlElement.setlevel(1);
		Helper.prePack(pwPack, fileNames);
		XmlElement.traverseElementsForPack(XmlElement.root, pwPack);
		Helper.postPack(pwPack, fileNames);

		XmlElement.setlevel(1);
		Helper.preXslt(pwXslt, fileNames);
		XmlElement.traverseElementsForXslt(XmlElement.root,pwXslt);
		Helper.postXslt(pwXslt, fileNames.substring(0,15));

		XmlElement.setlevel(1);
		Helper.PreFfd(pwFfd);
		XmlElement.traverseElementsForFfd(XmlElement.root,pwFfd);
		Helper.PostFfd(pwFfd);

		XmlElement.traverseElementsForData(XmlElement.root,pwData);
		pwData.println();
		
		XmlPathSet.getXps().writeXmlPathSet();
		

		pwXslt.flush();
		pwXslt.close();
		pwPLI.flush();
		pwPLI.close();
		pwXml.flush();
		pwXml.close();
		pwExcel.flush();
		pwExcel.close();
		pwPack.flush();
		pwPack.close();
		pwFfd.flush();
		pwFfd.close();
		pwData.flush();
		pwData.close();
		try {
			fosXml.flush();
			fosXml.close();
			fosPLI.flush();
			fosPLI.close();
			fosXslt.flush();
			fosXslt.close();
			fosExcel.flush();
			fosExcel.close();
			fosPack.flush();
			fosPack.close();
			fosFfd.flush();
			fosFfd.close();
			fosData.flush();
			fosData.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	public void handleNode(Node node){
		if (node.getNodeName().equals("xs:element"))
			handleElement(node);
		else
			if (node.getNodeName().equals("xs:simpleType"))
				handleSimpleType(node);
			else
				if (node.getNodeName().equals("xs:complexType")){
					if (node.getChildNodes().getLength()==0){
						handleSimpleType(node);
						XmlElement.current.setEmpty(true);
					}
					else
						handleComplexType(node);
				}
				else
					if (node.getNodeName().equals("xs:choice")) {
						ChoiceData chDat = new ChoiceData();
						System.out.println("nn>"+XmlElement.current.getName());
						XmlElement.current.setChoiceElement(true);
						XMLDOMMapper.choiceDec.push(chDat);
						int k1 = 0;
						for (int j = 0; j < node.getChildNodes().getLength(); j++) {
							if (!node.getChildNodes().item(j).getNodeName().equals("#text"))
							    k1++;
							
						}
						chDat.setChoices(k1);
						int k2=1;
						for (int j = 0; j < node.getChildNodes().getLength(); j++) {
							if (!node.getChildNodes().item(j).getNodeName().equals("#text")){
								chDat.setChoiceNo(k2++);
								System.out.println("Push "+ chDat.getChoiceNo()+" "+ chDat.getChoices()+" "+XMLDOMMapper.choiceDec.size());
								System.out.println("pusj "+node.getChildNodes().item(j).getAttributes().getNamedItem("name"));
								handleNode(node.getChildNodes().item(j));
							}

						}
						XMLDOMMapper.choiceDec.pop();
					}	
						

				else
					if (node.getNodeName().equals("#text"))
			 		;
					else
					System.out.println("Unknown "+node.getNodeName());
		
				
		
		
		
	}
	private void handleComplexType(Node node) {
		
//		System.out.println("<ComplexType"+node.getAttributes().getNamedItem("name").getTextContent()+">");


		NamedNodeMap atrList = node.getAttributes();
//		String name = atrList.getNamedItem("name").getTextContent();
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			Node nd = node.getChildNodes().item(i);
			if (nd.getNodeName().equals("xs:sequence"	)) {
				for (int j = 0; j < nd.getChildNodes().getLength(); j++) {
					handleNode(nd.getChildNodes().item(j));
					
				} 
				
				
			}
				
			
		}
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			Node nd = node.getChildNodes().item(i);
			if (nd.getNodeName().equals("xs:choice"	)) {
				ChoiceData chDat = new ChoiceData();
				XMLDOMMapper.choiceDec.push(chDat);
				chDat.setChoices(nd.getChildNodes().getLength());
				for (int j = 0; j < nd.getChildNodes().getLength(); j++) {
					System.out.println("Choice "+nd.getNodeName()+" "+nd.getNodeType()+" "+nd.getNodeValue() );
					Node xx = nd.getChildNodes().item(j);
					System.out.println(xx);
//					chDat.setChoiceNo(j+1);
//					handleNode(nd.getChildNodes().item(j));
					
				} 
				XmlElement.current.setChoiceElement(true);
				for (int j = 0; j < nd.getChildNodes().getLength(); j++) {
					System.out.println("Choice "+nd.getNodeName()+" "+nd.getNodeType()+" "+nd.getNodeValue() );
					chDat.setChoiceNo(j+1);
					handleNode(nd.getChildNodes().item(j));
					
				} 
				
				XMLDOMMapper.choiceDec.pop();
				
			}
				
			
		}

//		System.out.println("</ComplexType "+node.getAttributes().getNamedItem("name").getTextContent()+">");


		// TODO Auto-generated method stub
		
	}
	private void handleSimpleType(Node node) {
		// TODO Auto-generated method stub
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			Node nd = node.getChildNodes().item(i);
			if (nd.getNodeName().equals("xs:restriction"	)) {
				NamedNodeMap xx = nd.getAttributes();
				Node yy = xx.getNamedItem("base");
				if (yy.getTextContent().equals("xs:string"))
					baseString(nd);
				if (yy.getTextContent().equals("xs:decimal"))
					baseDecimal(nd);
				if (yy.getTextContent().equals("xs:boolean"))
					baseBoolean(nd);
				if (yy.getTextContent().equals("xs:date"))
					baseISODate(nd);
				if (yy.getTextContent().equals("xs:dateTime"))
					baseISODateTime(nd);
				if (yy.getTextContent().equals("xs:time"))
					baseISOTime(nd);
					
			
				
				
			}
				
			
		}
	}
		
	private void baseDecimal(Node nd) {
		String fractDict = null;
		String totDict = null;
		NodeList nx = nd.getChildNodes();
		for (int i = 0; i < nx.getLength(); i++) {
			Node yy = nx.item(i);
			if (yy.getNodeName().equals("xs:fractionDigits")){
				fractDict = yy.getAttributes().getNamedItem("value"	).getTextContent();
			}
			if (yy.getNodeName().equals("xs:totalDigits")){
				totDict = yy.getAttributes().getNamedItem("value"	).getTextContent();
			}
		}
		if (totDict!= null) {
			int totd = Integer.parseInt(totDict);
			int fractd = 0;
			if (fractDict!=null)
			   fractd = Integer.parseInt(fractDict);
			String vv = "";
			for (int j = 0; j < totd-fractd; j++) {
				vv = vv + "9";
			}
			if (fractd>0){
				vv = vv + ".";
				for (int j = 0; j < fractd; j++) {
					vv = vv + "9";
				}
				
			}
			
	
		   stringBuffer.append(vv);
		   xmlLine.content = vv;
			xmlLine.contentLength = xmlLine.content.length()+1;

		   pliBuffer.append(" "+ "char("+vv.length()+")"	);
		   xsltLineArl.add(new XsltLine(buildXsltLine(pathAr),indent+2));
	    

			toBeMapped = true;

       		currentFfdData.setLength(vv.length()+1);

       		if(vv.subSequence(vv.length()-2, vv.length()).equals("99"))
				vv = vv.substring(0,vv.length()-2)+"%%";	
			
			XmlElement.current.setLength(vv.length()+1);
			XmlElement.current.setExample(vv);
		}   

		// TODO Auto-generated method stub
		
	}
	private void baseString(Node nd) {
		NodeList nx = nd.getChildNodes();
		for (int i = 0; i < nx.getLength(); i++) {
			Node yy = nx.item(i);
			if (yy.getNodeName().equals("xs:pattern")){
				boolean found = false;
				String example = patternMap.getExample(yy.getAttributes().getNamedItem("value").getTextContent());
				if (example == null) {
					for (int j = 0; j < patternList.size(); j++) {
						if (patternList.get(j).equals(yy.getAttributes().getNamedItem("value").getTextContent()))
							found = true;
					
					} 

					if(!found)
						patternList.add(yy.getAttributes().getNamedItem("value").getTextContent());
					example = "UNKNOWN";
					System.out.println("Error: Pattern not known "+yy.getAttributes().getNamedItem("value").getTextContent());
					System.exit(-10);	
				}
				stringBuffer.append(example);
				xmlLine.content = example;
				xmlLine.contentLength = xmlLine.content.length();

				pliBuffer.append(" "+ "char("+example.length()+")"	);
		    	xsltLineArl.add(new XsltLine(buildXsltLine(pathAr),indent+2));

				toBeMapped = true;
				
	       		currentFfdData.setLength(xmlLine.content.length());
	       		
	       		XmlElement.current.setLength(example.length());
	       		XmlElement.current.setExample(example);


				return;
			}
			if (yy.getNodeName().equals("xs:maxLength")){
				String vv = yy.getAttributes().getNamedItem("value").getTextContent();
				int maxl = Integer.parseInt(vv);
				if (maxl > 35){
					pliBuffer.append(" "+ "char("+maxl +")"	);
					vv = "more than 35 char %%";
				}
				else{
					vv = "-%%";
					for (int j = 0; j < maxl-4; j++) {
						vv = vv + "A";
						
					}
					vv = vv+ "-";
					pliBuffer.append(" "+ "char("+vv.length() +")"	);

				}
				toBeMapped = true;

				stringBuffer.append(vv);
				xmlLine.content = vv;
				xmlLine.contentLength = maxl;
		    	xsltLineArl.add(new XsltLine(buildXsltLine(pathAr),indent+2));
		    	
	       		currentFfdData.setLength(maxl);
	       		
	       		
	       		XmlElement.current.setLength(maxl);
	       		XmlElement.current.setExample(vv);

		    	

		    	
		    	


				return;
			}
			if (yy.getNodeName().equals("xs:enumeration")){
				String vv = yy.getAttributes().getNamedItem("value").getTextContent();
				stringBuffer.append(vv);
				xmlLine.content = vv;
				xmlLine.contentLength = xmlLine.content.length();

				pliBuffer.append(" "+ "char("+vv.length() +")"	);
		    	xsltLineArl.add(new XsltLine(buildXsltLine(pathAr),indent+2));

				toBeMapped = true;

				currentFfdData.setLength(vv.length());
				
				if(pathAr.get(pathAr.size()-1).equals("Cd") && pathAr.get(pathAr.size()-2).equals("EvtTp") ){
					XMLDOMMapper.EvtTp = vv;
					if (XmlPathSet.getXps().get("EvtTp")==null)
						XmlPathSet.getXps().put("EvtTp", new XmlPathSet.OccurCount(0,vv));
					else
					{
						XmlPathSet.OccurCount xocc = XmlPathSet.getXps().get("EvtTp");
						if (vv.compareTo(xocc.EvtTp)<0 && XMLDOMMapper.normalMode)
							xocc.EvtTp = vv;
					}
				}
				if(pathAr.get(pathAr.size()-1).equals("Cd") && pathAr.get(pathAr.size()-2).equals("MndtryVlntryEvtTp") )
					XMLDOMMapper.MndtryVlntryEvtTp = vv;
				if(pathAr.get(pathAr.size()-1).equals("Cd") && pathAr.get(pathAr.size()-2).equals("EvtPrcgTp") )
					XMLDOMMapper.EvtPrcgTp = vv;
				
				XmlElement.current.setLength(vv.length());
	       		XmlElement.current.setExample(vv);

				return;
			}
				

		}	

		
	}
	private void baseBoolean(Node nd){
		stringBuffer.append("true");
		xmlLine.content="true";
		xmlLine.contentLength = 5;

		pliBuffer.append(" " + "Char(5)");
    	xsltLineArl.add(new XsltLine(buildXsltLine(pathAr),indent+2));


    	toBeMapped = true;
		currentFfdData.setLength(5);
		
   		XmlElement.current.setLength(5);
   		XmlElement.current.setExample("true");


	}
	
    private void baseISODateTime(Node nd){
    	stringBuffer.append("2014-01-01T22:11:05-06:00");
    	xmlLine.content="2014-01-01T22:11:05-06:00";
		xmlLine.contentLength = xmlLine.content.length();

		pliBuffer.append(" " + "Char(25)");
    	xsltLineArl.add(new XsltLine(buildXsltLine(pathAr),indent+2));

		toBeMapped = true;
		
   		XmlElement.current.setLength(25);
   		XmlElement.current.setExample("2014-01-01T22:11:05-06:00");



			currentFfdData.setLength(25);
    }
    private void baseISODate(Node nd){
    	stringBuffer.append("2014-01-01");
    	xmlLine.content="2014-01-01";
		xmlLine.contentLength = xmlLine.content.length();

		pliBuffer.append(" " + "Char(10)");
    	xsltLineArl.add(new XsltLine(buildXsltLine(pathAr),indent+2));

		toBeMapped = true;
		
		
		
		XmlElement.current.setLength(10);
		XmlElement.current.setExample("2014-01-%%");

		currentFfdData.setLength(10);
    }
    private void baseISOTime(Node nd){
    	stringBuffer.append("22:11:05");
    	xmlLine.content="22:11:05";
		xmlLine.contentLength = xmlLine.content.length();

		pliBuffer.append(" " + "Char(8)");
    	xsltLineArl.add(new XsltLine(buildXsltLine(pathAr),indent+2));

		toBeMapped = true;
		currentFfdData.setLength(8);
		
   		XmlElement.current.setLength(8);
   		XmlElement.current.setExample("22:11:05");



    }
    


	private void handleElement(Node node) {
		
		NamedNodeMap atrList = node.getAttributes();
		String name = atrList.getNamedItem("name").getTextContent();
		String type;
		if (atrList.getNamedItem("type")==null)
			type = "";
		else
		    type = atrList.getNamedItem("type").getTextContent();

		Node omit = atrList.getNamedItem("omit");
		if (omit!=null){
			if (omit.getTextContent().equals("true"))
				return;
		}

		currentElementNode = node;
		

   		FfdData localFfdData = null;

		
		
		String minOccurs = "";
		if (atrList.getNamedItem("minOccurs")!=null)
			minOccurs  = atrList.getNamedItem("minOccurs").getTextContent();
		String maxOccurs="";
		if (atrList.getNamedItem("maxOccurs")!=null)
			maxOccurs  = atrList.getNamedItem("maxOccurs").getTextContent();
		String occ = "";
		int occNum = 1;
		if (maxOccurs.equals("")){
			occ = "";
			occNum = 1;
		}
		else
		if (maxOccurs.equals("unbounded")){
			occ = "(xx)";
			occNum = 10;
		}
		else{
			occNum = Integer.parseInt(maxOccurs);

			if (maxOccurs.equals("1"))
				occ = "";
			else{

				occ = "(XX)";
			}
		}
		
		if (XmlElement.root == null) 
			;
		else {
		  String path = XmlElement.current.getPathAsString()+name;
		  XmlPathSet.OccurCount pathOcc = XmlPathSet.getXps().get(path);
		  if (pathOcc==null){
			  if (XMLDOMMapper.normalMode)
		     		XmlPathSet.getXps().put(path,new XmlPathSet.OccurCount(occNum));
			  else
				  return;
			  
		  }
		  else
			  
		  if (XMLDOMMapper.normalMode)
	     		pathOcc.occurs = Math.max(occNum,pathOcc.occurs);
	      else {
			    occNum = pathOcc.occurs;
	      }
		}

		
		
		
		XPathExpression expr2=null;
		NodeList result = null;
		try {
			String t0 = "//xs:complexType[@name='"+type+"'] | //xs:simpleType[@name='"+type+"']";
//			System.out.println(">>> "+t0);
 			expr2 = xpath.compile(t0);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			result = (NodeList) expr2.evaluate(xmlDoc, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    if (result.getLength()==0)
	    {
	    	System.out.println(" findes ikke "+ type);
	        System.exit(-2);
	    }
	    if (toBeMapped){
	    	mapNo += 1;
	    }    
	    if (stringBuffer!=null)
	    	if (stringBuffer.length()> 0 ){
	    		xmlAL.add(stringBuffer.toString());
	    		if (toBeMapped)
	    			xmlNo.add(new Integer(mapNo));
	    		else
	    			xmlNo.add(new Integer(0));
	    	}
	    if (pliBuffer!=null)
	    	if (pliBuffer.length()> 0 ){
	    		pliBuffer.append(",");
	    		pliAL.add(pliBuffer.toString());
	    		if (toBeMapped)
	    			pliNo.add(new Integer(mapNo));
	    		else
	    			pliNo.add(new Integer(0));
	    	}
	    indent += 2;
	    level += 2;
	    tagStarted = true;
	    toBeMapped = false;
	    if (xmlLine!=null)
	    	xmlLineArl.add(xmlLine); 
	    xmlLine = new XmlLine(name,true,indent );
	    xmlLine.mapno[0]=mapNo+1; 
	    xmlLine.occ = occ;
	    stringBuffer = new StringBuffer();
	    pliBuffer = new StringBuffer();
	    pathAr.add(name);
	    
        XmlElement newXmlElement = new XmlElement();
        if (XmlElement.root == null){
        	XmlElement.root = newXmlElement;
        	XmlElement.current = newXmlElement;
        	newXmlElement.getPath().add(name);
        }
        else{
            XmlElement.current.getChildren().add(newXmlElement);
            for (String pathElem : XmlElement.current.getPath()) {
            	newXmlElement.getPath().add(pathElem);
				
			}
            newXmlElement.setPrevOccIx(XmlElement.current.getPrevOccIx());
            newXmlElement.setOccIx(XmlElement.current.getOccIx());
            newXmlElement.setOccLevel(XmlElement.current.getOccLevel());
            newXmlElement.getPath().add(name);
            newXmlElement.setParent(XmlElement.current);
        	XmlElement.current = newXmlElement;

       	
        }
//        if (node.getChildNodes().getLength()==0)
//        	newXmlElement.setEmpty(true);
        newXmlElement.setName(name);
        newXmlElement.setOccurs(occNum);
        if (XMLDOMMapper.choiceDec.peekLast()==null)
        	;
        else{
         System.out.println("Peek "+name + " "+ XMLDOMMapper.choiceDec.peekFirst().getChoiceNo()+" "+XMLDOMMapper.choiceDec.peekFirst().getChoices());	
      	 newXmlElement.setChoiceNo(XMLDOMMapper.choiceDec.peekFirst().getChoiceNo());
      	 newXmlElement.setChoices(XMLDOMMapper.choiceDec.peekFirst().getChoices());
        }
       	
       	if(occNum>1){
       		newXmlElement.setPrevOccIx(newXmlElement.getOccIx());
       		newXmlElement.setOccLevel(newXmlElement.getOccLevel()+1);
       		newXmlElement.setOccIx(newXmlElement.getPath().size());
       	}

//	    showPath(pathAr);
//	    System.out.println();
	    for (int i = 0; i < indent; i++) {
	    	stringBuffer.append(" ");
	    	pliBuffer.append(" ");
			
		}
	    String indents = pliBuffer.toString();
        if (type.equals("ActiveCurrencyAndAmount")){
        	stringBuffer.append("<"+name+" "+ "Ccy=\"C\">");
        	stringBuffer.append("9999999999999.99999");
        	xmlLine.content = "9999999999999.99999";
        	xmlLine.contentLength = 20;
        	xmlLine.attrName.add("Ccy");
        	xmlLine.attrValue.add("EUR"	);
        	pliBuffer.append(level +" " +name +"_w_attr," );
    		pliAL.add(pliBuffer.toString());
    		pliNo.add(new Integer(0));
    		pliAL.add(indents + "  " + (level+2)+ " CCY  char(3),");
    		mapNo += 1;
    		pliNo.add(new Integer(-mapNo));
    		pliBuffer = new StringBuffer(indents+"  "+(level+2)+" "+name+ "  char(20)");
    		
    		
    		xsltLineArl.add(new XsltLine("<"+name+">",indent));
	    	xsltLineArl.add(new XsltLine(buildXsltLine(pathAr),indent+2));

       		currentFfdData = new FfdData(name,indent,false, buildPackLine(pathAr));
       		localFfdData = currentFfdData;
       		ffdDataArl.add(currentFfdData);
       		currentFfdData.setLength(20);
       		
       		XmlElement.current.setLength(20);
       		XmlElement.current.setExample("9999999999999.999%%");
       		XmlElement.current.setAttrExample("EUR");
       		XmlElement.current.setAttrName("Ccy");


        	toBeMapped = true;
        }
        else
            if (type.equals("ActiveCurrencyAnd13DecimalAmount")){
            	stringBuffer.append("<"+name+" "+ "Ccy=\"DKK\">");
            	stringBuffer.append("9999999999999.99999");
            	xmlLine.content =   "9999999999999.99999";
            	xmlLine.contentLength = 20;
            	xmlLine.attrName.add("Ccy");
            	xmlLine.attrValue.add("EUR"	);
            	pliBuffer.append(level +" " +name +"_w_attr," );
        		pliAL.add(pliBuffer.toString());
        		pliNo.add(new Integer(0));
        		pliAL.add(indents+"  " + (level+2)+ " CCY  char(3),");
        		mapNo += 1;
        		pliNo.add(new Integer(mapNo));
        		pliBuffer = new StringBuffer(indents+"  "+(level+2)+" "+name+"  char(20)"  );

        		xsltLineArl.add(new XsltLine("<"+name+">",indent));
    	    	xsltLineArl.add(new XsltLine(buildXsltLine(pathAr),indent+2));

        		
           		currentFfdData = new FfdData(name,indent,false, buildPackLine(pathAr));
           		localFfdData = currentFfdData;
           		ffdDataArl.add(currentFfdData);
           		currentFfdData.setLength(20);
           		
           		XmlElement.current.setLength(20);
           		XmlElement.current.setExample("9999999999999.999%%");
           		XmlElement.current.setAttrExample("EUR");
           		XmlElement.current.setAttrName("Ccy");


        		
        		
            	toBeMapped = true;
            }
            else
        if (type.equals("ActiveCurrencyAnd13DecimalAmount")){
        	stringBuffer.append("<"+name+" "+ "Ccy=\"DKK\">");
        	stringBuffer.append("99999.9999999999999");
        	xmlLine.content =   "99999.9999999999999";
        	xmlLine.contentLength = 20;
        	xmlLine.attrName.add("Ccy");
        	xmlLine.attrValue.add("EUR"	);

        	pliBuffer.append(level +" " +name);
        	
    		xsltLineArl.add(new XsltLine("<"+name+">",indent));
	    	xsltLineArl.add(new XsltLine(buildXsltLine(pathAr),indent+2));

        	toBeMapped = true;

       		currentFfdData = new FfdData(name,indent,false, buildPackLine(pathAr));
       		localFfdData = currentFfdData;
       		ffdDataArl.add(currentFfdData);
       		currentFfdData.setLength(20);

       		
       		XmlElement.current.setLength(20);
       		XmlElement.current.setExample("9999999999999.999%%");
       		XmlElement.current.setAttrExample("EUR");
       		XmlElement.current.setAttrName("Ccy");


        }
        else
    if (type.equals("ActiveOrHistoricCurrencyAnd13DecimalAmount")){
    	stringBuffer.append("<"+name+" "+ "Ccy=\"DKK\">");
    	stringBuffer.append("99999.9999999999999");
    	xmlLine.content =   "99999.9999999999999";
    	xmlLine.contentLength = 20;
    	xmlLine.attrName.add("Ccy");
    	xmlLine.attrValue.add("EUR"	);

    	pliBuffer.append(level +" " +name);
    	
		xsltLineArl.add(new XsltLine("<"+name+">",indent));
    	xsltLineArl.add(new XsltLine(buildXsltLine(pathAr),indent+2));

    	toBeMapped = true;

   		currentFfdData = new FfdData(name,indent,false, buildPackLine(pathAr));
   		localFfdData = currentFfdData;
   		ffdDataArl.add(currentFfdData);
   		currentFfdData.setLength(20);

   		
   		XmlElement.current.setLength(20);
   		XmlElement.current.setExample("9999999999999.999%%");
   		XmlElement.current.setAttrExample("EUR");
   		XmlElement.current.setAttrName("Ccy");


    }
      else{
        	stringBuffer.append("<"+name+">");
        	
        	xsltLineArl.add(new XsltLine("<"+name+">", indent));
        	 
        	pliBuffer.append(level +" " +name + occ);
        	
       		currentFfdData = new FfdData(name,indent,false, buildPackLine(pathAr));
       		localFfdData = currentFfdData;
       		ffdDataArl.add(currentFfdData);
        	
            handleNode(result.item(0));
        }   
        
	    if (tagStarted)
	    	;
	    else{
//		    System.out.println();
		    if (toBeMapped){
		    	mapNo += 1;
		    }
		    if (stringBuffer!=null)
		    	if (stringBuffer.length()> 0 ){
		    		xmlAL.add(stringBuffer.toString());
		    		if (toBeMapped)
		    			xmlNo.add(new Integer(mapNo));
		    		else
		    			xmlNo.add(new Integer(0));

		    	}
		    if (pliBuffer!=null)
		    	if (pliBuffer.length()> 0 ){
		    		pliBuffer.append(",");
		    		pliAL.add(pliBuffer.toString());
		    		if (toBeMapped)
		    			pliNo.add(new Integer(mapNo));
		    		else
		    			pliNo.add(new Integer(0));

		    	}
	    	stringBuffer = new StringBuffer();
	    	pliBuffer = new StringBuffer();
		    for (int i = 0; i < indent; i++) {
		    	stringBuffer.append(" ");
				
			}
		    toBeMapped = false;
	    	
	    }
	    
	    
	    pathAr.remove(pathAr.size()-1);
//	    showPath(pathAr);
	    stringBuffer.append("</"+name+">");
	    tagStarted = false;
	    if (xmlLine==null){
	    		xmlLine = new XmlLine(name,false,true);
	    		xmlLine.indent = indent;
	    	}
	    	else
	    		xmlLine.endTag = true;
	    xmlLineArl.add(xmlLine);
	    xmlLine = null;
    	xsltLineArl.add(new XsltLine("</"+name+">", indent));
    	
        int ll = localFfdData.getLength(); 	
        	localFfdData = new FfdData(name, indent, true,"");
        	localFfdData.setLength(ll); 
        	
    		ffdDataArl.add(localFfdData);
    		
    	
    	
	    
	    
	    	
	    
	    indent -= 2;
	    level -= 2;
	    
	    
	    XmlElement.current = newXmlElement.getParent();

		// TODO Auto-generated method stub
		
	}
	private String buildXsltLine(ArrayList<String> pathAr2) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xsl:value-of select=\"");
		for (int i = 0; i < pathAr2.size(); i++) {
			sb.append("/*[local-name()='"+ pathAr2.get(i)+"']");
		}
		sb.append("\" />");
		return sb.toString();

		
	}
	private String buildPackLine(ArrayList<String> pathAr2) {
		StringBuffer sb = new StringBuffer();
		sb.append("PLI_Msg");
		for (int i = 0; i < pathAr2.size(); i++) {
			sb.append("/"+pathAr2.get(i));
		}
		return sb.toString();

		
	}

	private void showPath(ArrayList<String> pathAr2) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < pathAr2.size(); i++) {
			sb.append("-"+ pathAr2.get(i)+"-");
		}	
		
        System.out.println(sb.toString());		
	}

}
