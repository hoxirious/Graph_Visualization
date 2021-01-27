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
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
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
	    private static final Color SNOW_COLOR = Color.white;
	    private static final Color gridColor = new Color(200, 200, 200, 200);
	    private static final int width = 751;
	    private static final int height = 215;
	    private static final int padding = 25;
	    private static final int pointWidth = 4;
	    private static final int labelPadding = 25;
	    private static final int boardHeight= height - padding * 2 - labelPadding;
	    private static final int boardWidth = width - padding * 2  - labelPadding;
	    private static final int numberYDivisions = 8;
	    private static final int numberXDivisions = 30;
	    private static final int yUnit = (height - padding * 2 - labelPadding) / numberYDivisions ;
	    private static final int xUnit = boardWidth/360 ;
	    private static final int zeroYValue = (height - padding)/2 + yUnit+2;
//	    private static final double wBar = boardWidth/360;   
	    private static final BasicStroke GRAPH_STROKE = new BasicStroke(2f);	    
	    
	    private  Color BAR_COLOR;
	    private  List <String> selectedMonths;
	    private  List <String> selectedYears;
	    private ReadData dataPoints; 
	    private boolean [] checkedBox; 
	    private List<Double> yValues;
	    private List<List<Integer>> xValues;
	    static Dictionary valueDict = new Dictionary();
	    

	    
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        
	        //xScale not correct
	        double xScale = xUnit ;
	        int yScale = yUnit/5 ;	   
	       
	        List<Point2D.Double> graphPoints = new ArrayList<>();
	        
	        for (int i = 0; i < yValues.size(); i++) {
	        	int year =xValues.get(i).get(0);
	        	int month =xValues.get(i).get(1);
	        	int xPos = (year-1990)*12+month;
	            double x1 = xPos*xScale;
	            double y1 =  (yValues.get(i)*yScale);
	            System.out.println(y1);
	            graphPoints.add(new Point2D.Double (x1,y1));
	        }

	        // draw white background
	        g2.setColor(Color.WHITE);
	        g2.fillRect(padding + labelPadding, padding ,boardWidth , boardHeight);
	        g2.setColor(Color.BLACK);

	        
	        // create grid lines for y axis.
	        for (int i = 0; i < numberYDivisions + 1; i++) {
	            int x0 = padding + labelPadding;
	            int x1 = pointWidth + padding + labelPadding;
	            int y0P = (getHeight() - i*yUnit - padding*2)   ;	
	                g2.setColor(gridColor);
	                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0P, getWidth() - padding, y0P);
	                g2.setColor(Color.BLACK);
	                String yLabelPos = ((int) ((-15 + (40) * ((i*1.0)/numberYDivisions)) * 100)) / 100.0 + "";
	                FontMetrics metrics = g2.getFontMetrics();
	                int labelWidth = metrics.stringWidth(yLabelPos);
	                g2.drawString(yLabelPos, x0 - labelWidth - 5, y0P + (metrics.getHeight() / 2) - 3);
	            ///draw hatchmarks
	            g2.drawLine(x0, y0P, x1, y0P);
	        }

	        // and for x axis
	        
	        for (int i = 0; i < 30; i++) {
		                int x0 = i * (boardWidth) / (30) + padding + labelPadding;
		                int x1 = x0;
		                int y0 = (getHeight() - padding) /2 + (boardHeight) / numberYDivisions;
		                int y1 = y0 - pointWidth;
		                    g2.setColor(gridColor);
		                    g2.drawLine(x0, boardHeight + padding , x1, padding);
		                    g2.setColor(Color.BLACK);
		                    if(i%2!=0) {
		                    String xLabel = Dictionary.yearsA[i] +"";
		                    FontMetrics metrics = g2.getFontMetrics();
		                    int labelWidth = metrics.stringWidth(xLabel);
		                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
		                    }
		                g2.drawLine(x0, y0+2, x1, y1+2);
	        }

	        // create x and y axes 
	        g2.drawLine(padding + labelPadding, boardHeight + padding, padding + labelPadding, padding);
	        g2.drawLine(padding + labelPadding, zeroYValue, getWidth() - padding, zeroYValue);

	        //drawbar
	        drawBar(g2,graphPoints);
	    }
	    
	    private void drawBar(Graphics2D g2,List<Point2D.Double> graphPoints) {
	        for (int i = 0; i < graphPoints.size(); i++) {
	            double x1 = graphPoints.get(i).x;
	            double y1 = graphPoints.get(i).y;
	            g2.setColor(Color.black);
	            g2.draw(new Rectangle2D.Double(padding+labelPadding + x1, decideStartPoint(y1) , xUnit, Math.abs(y1)));
	            g2.setColor(BAR_COLOR);
	            g2.fill(new Rectangle2D.Double(padding+labelPadding + x1, decideStartPoint(y1), xUnit, Math.abs(y1)));
	        }
	    }
	    
	    private double decideStartPoint(double yValue) {
	    	System.out.println(yValue);
	    	if(yValue>0) {
	    		return zeroYValue - Math.abs(yValue)  ; 
	    	}
	    	else 
	    		return zeroYValue; 
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
	    
	    public void setColor(Color BAR_COLOR) {
	    	this.BAR_COLOR = BAR_COLOR;
	    }

	    public void setYValues(List<Double> yValues) {
	        this.yValues = yValues;
	        invalidate();
	        this.repaint();
	    }
	    
	    public void setXValues(List<List<Integer>> xValues) {
	        this.xValues = xValues;
	        invalidate();
	        this.repaint();
	    }

	   

	    
	    @Override
	    public Dimension getPreferredSize() {
	        return new Dimension(width, height);
	    }
	    
	 /////something wrong. y takes everything
	    public void generateTypeYValue() {
	    	List<Double> yValues = new ArrayList<>();
	    	List<List<Integer>> xValues = new ArrayList<>();;
	    	for(int i=0; i<checkedBox.length;i++) {
	    		if(checkedBox[i]) {
	    			int length =this.dataPoints.getYCoors().size();
	    			for(int k=0;k<length;k++) {
	    				yValues.add(this.dataPoints.getYCoors().get(k).get(i));
	    				xValues.add(Arrays.asList(this.dataPoints.getXCoors().get(k).get(0),this.dataPoints.getXCoors().get(k).get(1)));
	    				if(i==0) setColor(BARMAX_COLOR);
	    				else if(i==1) setColor(BARMIN_COLOR);
	    				else if(i==2) setColor(SNOW_COLOR);
	    			}
	    			setYValues(yValues);
	    			setXValues(xValues);
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
