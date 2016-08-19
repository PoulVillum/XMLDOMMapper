package xMLMapperp;

import java.text.DecimalFormat;
import java.text.FieldPosition;

public class MyDecimalFormat {
	


	DecimalFormat df = new DecimalFormat("####");
	public String format(int number){
		StringBuffer sb = new StringBuffer(df.format(number));
//		System.out.println(">>>>>>"+sb.toString()	);
		for (; sb.length() < 4; ) {
			sb.insert(0, ' ');
			
		}
//		System.out.println(">>>>>>"+sb.toString()	);
		return sb.toString();
		
	}

}
