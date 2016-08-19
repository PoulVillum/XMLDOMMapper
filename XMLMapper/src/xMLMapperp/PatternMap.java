package xMLMapperp;

import java.util.ArrayList;

public class PatternMap {
	
	ArrayList<String> pattern = new ArrayList<String>();
	ArrayList <Integer > length = new ArrayList<Integer>();
	ArrayList <String> example = new ArrayList<String>();
	
	public PatternMap(){
		
		add("[0-9]{1,5}                                                ",         5     ,"12345"                                     );
		add("[a-zA-Z0-9]{4}                                            ",         4     ,"AaZ0"                                      );
		add("[a-zA-Z0-9]{1,4}                                          ",         4     ,"AaY0"                                      );
		add("[0-9]{3}                                                  ",         3     ,"123"                                       );
		add("[a-z]{4}\\.[0-9]{3}\\.[0-9]{3}\\.[0-9]{2}                 ",         15    ,"abcd.012.123.01"                           );
		add("[A-Z0-9]{12,12}                                           ",         12     ,"0ABCDEFG8889"                              );
		add("[A-Z0-9]{4,4}                                             ",         4     ,"01AB"                                      );
		add("[A-Z]{1,6}                                                ",         6     ,"ABCDEF"                                     );
		add("[A-Z]{3,3}                                                ",         3     ,"ABZ"                                     );
		add("[A-Z]{6,6}[A-Z2-9][A-NP-Z0-9]([A-Z0-9]{3,3}){0,1}         ",        11     ,"ABCDEFGH012"                                     );
		add("[A-Z]{2,2}                                                ",         2     ,"AZ"                                     );
		add("[A-Z0-9]{3}                                               ",         3     ,"A9Z"                                     );
		add("[A-Z]{2,2}[0-9]{2,2}[a-zA-Z0-9]{1,30}		      ",        30     ,"AAZ09AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAZ");
		add("[0-9]{4}                                         ",        4     ,"0123");
	}	
	private void add(String string, int i, String string2) {
		pattern.add(string.trim());
		length.add(new Integer(i));
		example.add(string2);
		
	}
	public int getLength(String pat){
		for (int i = 0; i < pattern.size(); i++) {
			if (pattern.get(i). equals(pat))
				return (length.get(i).intValue());
			
		}
		return(-1);
		
		
	}
	public String getExample(String pat){
		for (int i = 0; i < pattern.size(); i++) {
			if (pattern.get(i).equals(pat)) {
			
				return (example.get(i));
			}
			
		}
		return(null);
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         PatternMap pm = new PatternMap();
         System.out.println(pm.getExample("[0-9]{1,5}        ".trim()));
         String  string1 = "abc";
         String  string2 = "abc";
         System.out.println(string1.equals(string2));



	}


}
