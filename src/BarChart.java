import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart extends JFrame {

	    private  List <String> selectedMonths;
	    private  List <String> selectedYears;
	    private ReadData dataPoints; 
	    private boolean [] checkedBox; 

	    public BarChart(final List <String> selectedMonths, final List <String> selectedYears, boolean [] checkedBox) {
	    	
	    	this.selectedMonths = selectedMonths;
	    	this.selectedYears = selectedYears;	 
	    	this.checkedBox = checkedBox; 
	    	dataPoints = new ReadData(this.selectedYears,this.selectedMonths);
	    	
	    	JPanel panel = createChartPanel();
	    	
			panel.setBackground(Color.LIGHT_GRAY);
	        setSize(640, 480);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	    	
	    }
	    
//	    private JPanel createChartPanel() {
//	        // creates a line chart object
//	        // returns the chart panel
//	    }
	 
	    private CategoryDataset createDataset() {
	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        
	        String maxTemp = "Max Temperature";
	        String minTemp = "Min Temperature";
	        String snowFall = "Snow Fall";
	        
	        
	        for(List<Integer> i : dataPoints.getMapList().keySet()) {
	        	if(checkedBox[0]) {
	        	dataset.addValue(dataPoints.getMapList().get(i).get(0),maxTemp,i.get(1));
	        	}
	        	if(checkedBox[1]) {
	        	dataset.addValue(dataPoints.getMapList().get(i).get(1),maxTemp,i.get(1));
	        	}
	        	if(checkedBox[2]) {
	        	dataset.addValue(dataPoints.getMapList().get(i).get(2),maxTemp,i.get(1));
	        	}
	        }
	        
	        return dataset;
	    }
	    
}
