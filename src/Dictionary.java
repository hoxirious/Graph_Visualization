import java.util.HashMap;
import java.util.Map;

public class Dictionary {
	private static final Map<String, String> monthsDict = new HashMap<>();
	final static String[] listTypeChartA;
	private static final Map<String, Integer> yTypeValueDict = new HashMap<>();
	final static String[] monthsA;

	static String[] yearsA = new String[30];
	static String[] yTypeValues;
	static String[] monthsN = new String[12];

	static {
		listTypeChartA = new String[] { "Bar Chart", "Scatter Chart" };
		monthsA = new String[] { "January", "Febuary", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };

		yTypeValues = new String[] { "maxTemp", "minTemp", "snowFall" };

		for (int i = 0; i < 12; i++) {
			monthsN[i] = Integer.toString(i + 1);
		}

		for (int i = 0; i < 30; i++) {
			yearsA[i] = Integer.toString(1990 + i);
		}

		for (int i = 0; i < 3; i++) {
			yTypeValueDict.put(yTypeValues[i], i);
		}

		for (int i = 1; i <= 12; i++) {
			monthsDict.put(monthsA[i - 1], Integer.toString(i));
		}
	};

	public static Map<String, Integer> getYTypeValueDict() {
		return yTypeValueDict;
	}

	public static Map<String, String> getMonthsDict() {
		return monthsDict;
	}

}
