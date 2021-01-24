import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
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

public class BarChart extends JPanel {
		private static final Color BACKGROUND_COLOR = Color.white;
	    private static final Color BARMAX_COLOR = Color.red;
	    private static final Color BARMIN_COLOR = Color.blue;
	    private static final Color gridColor = new Color(200, 200, 200, 200);
	    private static final Color lineColor = new Color(44, 102, 230, 180);
	    private int width = 800;
	    private int height = 400;
	    private int wBar=2;
	    
	    private int padding = 25;
	    private int pointWidth = 4;
	    private int labelPadding = 25;
	    private int numberYDivisions = 10;
	    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);

	    private  List <String> selectedMonths;
	    private  List <String> selectedYears;
	    private ReadData dataPoints; 
	    private boolean [] checkedBox; 
	    private String dataType;
	    private List<Double> yValues;
	    static Dictionary valueDict = new Dictionary();
	    

	    
	    
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        
	        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (yValues.size() - 1);
	        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxY() - getMinY());	   

	        List<Point> graphPoints = new ArrayList<>();
	        
	        for (int i = 0; i < yValues.size(); i++) {
	            int x1 = (int) (i * xScale + padding + labelPadding);
	            int y1 = (int) ((getMaxY() - yValues.get(i)) * yScale + padding);
	            graphPoints.add(new Point(x1, y1));
	        }

	        // draw white background
	        g2.setColor(Color.WHITE);
	        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
	        g2.setColor(Color.BLACK);

	        // create hatch marks and grid lines for y axis.
	        for (int i = 0; i < numberYDivisions + 1; i++) {
	            int x0 = padding + labelPadding;
	            int x1 = pointWidth + padding + labelPadding;
	            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);	
	            int y1 = y0;
	            if (yValues.size() > 0) {
	                g2.setColor(gridColor);
	                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
	                g2.setColor(Color.BLACK);
	                String yLabel = ((int) ((getMinY() + (getMaxY() - getMinY()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
	                FontMetrics metrics = g2.getFontMetrics();
	                int labelWidth = metrics.stringWidth(yLabel);
	                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
	            }
	            g2.drawLine(x0, y0, x1, y1);
	        }

	        // and for x axis
	        for (int i = 0; i < yValues.size(); i++) {
	            if (yValues.size() > 1) {
	                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (yValues.size() - 1) + padding + labelPadding;
	                int x1 = x0;
	                int y0 = getHeight() - padding - labelPadding;
	                int y1 = y0 - pointWidth;
	                if ((i % ((int) ((yValues.size() / 20.0)) + 1)) == 0) {
	                    g2.setColor(gridColor);
	                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
	                    g2.setColor(Color.BLACK);
	                    String xLabel = i + "";
	                    FontMetrics metrics = g2.getFontMetrics();
	                    int labelWidth = metrics.stringWidth(xLabel);
	                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
	                }
	                g2.drawLine(x0, y0, x1, y1);
	            }
	        }

	        // create x and y axes 
	        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
	        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

	        
	        //drawbar
	        Stroke oldStroke = g2.getStroke();
	        g2.setColor(lineColor);
	        g2.setStroke(GRAPH_STROKE);
	        for (int i = 0; i < graphPoints.size() - 1; i++) {
	            int x1 = graphPoints.get(i).x;
	            int y1 = graphPoints.get(i).y;
	            int x2 = graphPoints.get(i + 1).x;
	            int y2 = graphPoints.get(i + 1).y;
	            g2.drawRect(x1,0,wBar, y2);
	        }

	        g2.setStroke(oldStroke);
//	        g2.setColor(pointColor);
	        for (int i = 0; i < graphPoints.size(); i++) {
	            int x = graphPoints.get(i).x - pointWidth / 2;
	            int y = graphPoints.get(i).y - pointWidth / 2;
	            int ovalW = pointWidth;
	            int ovalH = pointWidth;
	            g2.fillOval(x, y, ovalW, ovalH);
	        }
	    }
	    
	    private double getMinY() {
	        double minY = 0;
	        for (Double value : yValues) {
	            minY = Math.min(minY, value);
	        }
	        return minY;
	    }

	    private double getMaxY() {
	        double maxY = 0;
	        for (Double value : yValues) {
	            maxY = Math.max(maxY, value);
	        }
	        return maxY;
	    }

	    public void setYValues(List<Double> yValues) {
	        this.yValues = yValues;
	        invalidate();
	        this.repaint();
	    }

	    
	    @Override
	    public Dimension getPreferredSize() {
	        return new Dimension(width, height);
	    }
	    
	 
	    public void generateTypeYValue() {
	    	for(int i=0; i<checkedBox.length;i++) {
	    		if(checkedBox[i]) {
	    			setYValues(this.dataPoints.getYCoors().get(i));
	    		}
	    	}
	    }
	    
	    public BarChart(final List <String> selectedMonths, final List <String> selectedYears, boolean [] checkedBox) {
	    	
	    	this.selectedMonths = selectedMonths;
	    	this.selectedYears = selectedYears;	 
	    	this.checkedBox = checkedBox;
	    	
	    	this.dataPoints = new ReadData(this.selectedYears,this.selectedMonths);
	    	generateTypeYValue();
	    	
//	    	ChartPanel chartPane = createChartPanel();
//	    	this.panel.removeAll();
//	    	this.panel.add(chartPane, BorderLayout.CENTER);
//	    	this.panel.validate();    	
	    }
	    
//	    private ChartPanel createChartPanel() {
//	        String chartTitle = "Calgary Weather's Bar Chart";
//	        String categoryAxisLabel = "Interest over time";
//	        String valueAxisLabel = "(mm)";
//	     
//	        CategoryDataset dataset = createDataset();
//	     
//	        JFreeChart chart = ChartFactory.createBarChart(chartTitle,
//	                categoryAxisLabel, valueAxisLabel, dataset);
//	      
//	        return new ChartPanel(chart);
//	    }
//	 
//	    private CategoryDataset createDataset() {
//	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//	        String maxTemp = "Max Temperature";
//	        String minTemp = "Min Temperature";
//	        String snowFall = "Snow Fall";
//	        
//	        for(List<Integer> i : dataPoints.getMapList().keySet()) {
//	        	if(checkedBox[0]) {
//	        	dataset.addValue(dataPoints.getMapList().get(i).get(0),maxTemp,i.get(1));
//	        	}
//	        	if(checkedBox[1]) {
//	        	dataset.addValue(dataPoints.getMapList().get(i).get(1),minTemp,i.get(1));
//	        	}
//	        	if(checkedBox[2]) {
//	        	dataset.addValue(dataPoints.getMapList().get(i).get(2),snowFall,i.get(1));
//	        	}
//	        }	        
//	        return dataset;
//	    }
	    
	    
	    
	    ////test
	    /*
	     *         super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawRect(0, 0, 50, 50);
	     */
}
