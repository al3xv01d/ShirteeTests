package Util;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class ParseHelper {

	public BigDecimal profitStringToBigDecimal(String str)
	{
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
		String[] newStr = str.split(" ");

		newStr[0] = newStr[0].replace(".", "");
		newStr[0] = newStr[0].replaceAll(",", ".");

		BigDecimal a = new BigDecimal(newStr[0]);
		nf.format(a);
		return a;
	}
	
	public int[] stringToIntArrayBySplit(String str, String split)
	{
		String[] strArray = str.split(split);
		
		int[] verk = {0,0};
		verk[0] = Integer.parseInt(strArray[0]);
		verk[1] = Integer.parseInt(strArray[1]);
		
		return verk;
	}
	
	public int stringToInt(String str)
	{
		int i = Integer.parseInt(str);
		return i;
	}
}
