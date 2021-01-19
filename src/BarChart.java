import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart extends JFrame {
		private static final Color BACKGROUND_COLOR = Color.white;
	    private static final Color BARMAX_COLOR = Color.red;
	    private static final Color BARMIN_COLOR = Color.blue;

	    private  List <String> selectedMonths;
	    private  List <String> selectedYears;
	    private ReadData dataPoints; 
	    private boolean [] checkedBox; 
	    private JPanel panel;

	    public BarChart(final List <String> selectedMonths, final List <String> selectedYears, boolean [] checkedBox, JPanel panel2) {
	    	
	    	this.selectedMonths = selectedMonths;
	    	this.selectedYears = selectedYears;	 
	    	this.checkedBox = checkedBox;
	    	this.panel = panel2; 
	    	
	    	dataPoints = new ReadData(this.selectedYears,this.selectedMonths);
	    	ChartPanel chartPane = createChartPanel();
	    	this.panel.removeAll();
	    	this.panel.add(chartPane, BorderLayout.CENTER);
	    	this.panel.validate();
	    }
	    
	    private ChartPanel createChartPanel() {
	        String chartTitle = "Calgary Weather's Bar Chart";
	        String categoryAxisLabel = "Interest over time";
	        String valueAxisLabel = "(mm)";
	     
	        CategoryDataset dataset = createDataset();
	     
	        JFreeChart chart = ChartFactory.createBarChart(chartTitle,
	                categoryAxisLabel, valueAxisLabel, dataset);
	      
	        return new ChartPanel(chart);
	    }
	 
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
	        	dataset.addValue(dataPoints.getMapList().get(i).get(1),minTemp,i.get(1));
	        	}
	        	if(checkedBox[2]) {
	        	dataset.addValue(dataPoints.getMapList().get(i).get(2),snowFall,i.get(1));
	        	}
	        }
	        
	        return dataset;
	    }
	    
}
