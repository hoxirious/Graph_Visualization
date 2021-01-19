import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionary {
    private static final Map<String, String> myMap;
    static String [] monthsA = {"January","Febuary","March","April","May","June","July","August","September","October","November","December"};   
	static String [] listTypeChartA = {"Bar Chart", "Scatter Chart"};
	static String [] yearsA = {"1990","1991","1992","1993","1994","1995","1996","1997","1998","1999",
			"2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010",
			"2011","2012","2013","2014","2015","2016","2017","2018","2019"};	
    
    static {
        Map<String, String> aMap = new HashMap<>();
        
        	for(int i=1; i<=monthsA.length; i++ ) {
        		aMap.put(monthsA[i-1], Integer.toString(i)); 
        	}
        myMap = Collections.unmodifiableMap(aMap);
    }
	public Map<String, String> getMymap() {
		return myMap;
	}
    
}
