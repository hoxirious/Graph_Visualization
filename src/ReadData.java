

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
	private List<List<Float>> yCoors = new ArrayList<>();
	private Map<List<Integer>,List<Float>> xyCoors = new HashMap<>();
	static Dictionary monthDict = new Dictionary(); 
	
	public ReadData(List <String>selectedYears, List <String> selectedMonths) {		
		try (BufferedReader br = new BufferedReader(new FileReader("src/CalgaryWeather.csv"))) {
			br.readLine();
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        if(search(selectedYears,selectedMonths,values[0],values[1])) {
		        	xCoors.add(Arrays.asList(Integer.parseInt(values[0]),Integer.parseInt(values[1])));
			        yCoors.add(Arrays.asList(Float.parseFloat(values[2]),Float.parseFloat(values[3]),Float.parseFloat(values[4])));   
		        }
		    }
		} catch (FileNotFoundException e) {
			System.out.println("Error on file name!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error on reading data file!");			
			e.printStackTrace();
		}
		mapList(xCoors,yCoors); 
		
	}
	
	public boolean search(List <String>selectedYears, List <String> selectedMonths, String yearValue, String monthValue) {
		boolean isMonthExist = false; 
		boolean isYearExist = false;
		
		for(int i =0; i<selectedYears.size(); i++) {
			if(selectedYears.get(i).equals(yearValue)) {
				isYearExist = true;
				break; 
			}
		}
		
		for(int i =0; i<selectedMonths.size(); i++)
			if(monthDict.getMymap().get(selectedMonths.get(i)).equals(monthValue)) {
				isMonthExist = true;
				break; 
			}
		
		
		if(isMonthExist&&isYearExist) {
			return true;
		}
		else {
			return false;	
		}
	}

	
	public void mapList(List <List<Integer>> xCoors, List <List<Float>> yCoors) {
		for(int i =0; i<xCoors.size();i++) {
			xyCoors.put(xCoors.get(i),yCoors.get(i));
		}
	}
	

	public Map<List<Integer>, List<Float>> getMapList() {
		// TODO Auto-generated method stub
		return xyCoors; 
	}
}

