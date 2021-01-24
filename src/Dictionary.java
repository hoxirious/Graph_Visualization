import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionary {
    private static final Map<String, String> monthsDict = new HashMap<>();
	final static String [] listTypeChartA;
    private static final Map<String, Integer> yTypeValueDict = new HashMap<>();
    final static String [] monthsA;   
	
	static String [] yearsA;	
	static String [] yTypeValues;
	
	static {
		listTypeChartA = new String [] {"Bar Chart", "Scatter Chart"};
		monthsA =new String [] {"January","Febuary","March","April","May","June","July","August","September","October","November","December"};
		yearsA  = new String []{"1990","1991","1992","1993","1994","1995","1996","1997","1998","1999",
				"2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010",
				"2011","2012","2013","2014","2015","2016","2017","2018","2019"};
		yTypeValues= new String [] {"maxTemp", "minTemp", "snowFall"};

		
		for(int i=0; i<3;i++) {
			yTypeValueDict.put(yTypeValues[i],i);
		}
		
		for(int i=1; i<=12;i++) {
			monthsDict.put(monthsA[i-1], Integer.toString(i));
		}
	};
	

	
	public static Map<String, Integer> getYTypeValueDict() {
		return yTypeValueDict;
	}
	public static Map<String, String> getMonthsDict() {
		return monthsDict;
	}
    
}
