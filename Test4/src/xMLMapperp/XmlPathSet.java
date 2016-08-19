package xMLMapperp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;


public class XmlPathSet extends HashMap<String,XmlPathSet.OccurCount> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1948402806791058919L;
	/**
	 * 
	 */
	
	private static XmlPathSet xps = new XmlPathSet();
	
	public static class OccurCount implements Serializable {
		public int occurs;
		public String EvtTp = null;
		public OccurCount(int i){
			occurs = i;
		}
		public OccurCount(int i,String EvtTp){
			occurs = i;
			this.EvtTp = EvtTp;
		}
	}

	public static XmlPathSet getXps() {
		return xps;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		readXmlPathSet();
//		xps.put("sss1",new xps().OccurCount(4));
		xps.put("sss1",new OccurCount(4));
		xps.put("sss2",new OccurCount(7));
		xps.put("sss3",new OccurCount(3));
		xps.put("sss4",new OccurCount(8));
		xps.put("sss5",new OccurCount(1));
		
		xps.get("sss5").occurs = 11;
		
		
		
		for (String string : xps.keySet()) {
			
		
			System.out.println(">"+string+ " " + xps.get(string).occurs);
			
		}
		xps.writeXmlPathSet();
		
		xps = null;
		
		readXmlPathSet();
		for (String string : xps.keySet()) {
			
			
			System.out.println(">"+string+ " " + xps.get(string).occurs);
			
		}

	}
	public static void readXmlPathSet(){
		
		  FileInputStream fis = null;
			try {
				fis = new FileInputStream("C:\\app\\pathSet.txt");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			XmlPathSet xps = null;
			try {
				xps = (XmlPathSet) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			XmlPathSet.xps = xps;
			
			



		
	}
	public  void writeXmlPathSet(){
		
		  FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("C:\\app\\pathSet.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(fos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	      
	      try {
			oos.writeObject(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	      try {
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	

}
