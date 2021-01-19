

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
	
	
	public ReadData(List <String>selectedYears, List <String> selectedMonths) {		
		try (BufferedReader br = new BufferedReader(new FileReader("src/CalgaryWeather.csv"))) {
			br.readLine();
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        xCoors.add(Arrays.asList(Integer.parseInt(values[0]),Integer.parseInt(values[1])));
		        yCoors.add(Arrays.asList(Float.parseFloat(values[2]),Float.parseFloat(values[3]),Float.parseFloat(values[4])));   
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
	public int search(String Name) {
		
		return 0;
	}
	
	public void addList(int xCoor, float yCoor) {
		
	}
	
	public void mapList(List <List<Integer>> xCoors, List <List<Float>> yCoors) {
		for(int i =0; i<359;i++) {
			xyCoors.put(xCoors.get(i),yCoors.get(i));
		}
	}
}

