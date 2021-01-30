
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadData {
	private List<List<Integer>> xCoors = new ArrayList<>();
	private List<List<Double>> yCoors = new ArrayList<>();
	private List<String> selectedMonths = new ArrayList<>();
	private List<String> selectedYears = new ArrayList<>();
	static Dictionary monthDict = new Dictionary();

	public ReadData(List<String> selectedYears, List<String> selectedMonths) {
		this.selectedMonths = selectedMonths;
		this.selectedYears = selectedYears;
		try (BufferedReader br = new BufferedReader(new FileReader("src/CalgaryWeather.csv"))) {
			br.readLine();
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				if (search(values[0], values[1])) {
					xCoors.add(Arrays.asList(Integer.parseInt(values[0]), Integer.parseInt(values[1])));
					yCoors.add(Arrays.asList(Double.parseDouble(values[2]), Double.parseDouble(values[3]),
							Double.parseDouble(values[4])));
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error on file name!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error on reading data file!");
			e.printStackTrace();
		}
	}

	public boolean search(String yearValue, String monthValue) {
		boolean isMonthExist = false;
		boolean isYearExist = false;

		for (int i = 0; i < selectedYears.size(); i++) {
			if (selectedYears.get(i).equals(yearValue)) {
				isYearExist = true;
				break;
			}
		}

		for (int i = 0; i < selectedMonths.size(); i++) {
			if (Dictionary.getMonthsDict().get(selectedMonths.get(i)).equals(monthValue)) {
				isMonthExist = true;
				break;
			}
		}

		if (isMonthExist && isYearExist) {
			return true;
		} else {
			return false;
		}
	}

	public List<List<Integer>> getXCoors() {
		return xCoors;
	}

	public List<List<Double>> getYCoors() {
		return yCoors;
	}

}