import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JPanel;

public class BarChart extends JPanel {
	private static final Color BACKGROUND_COLOR = Color.lightGray;
	private static final Color BARMAX_COLOR = Color.red;
	private static final Color BARMIN_COLOR = Color.blue;
	private static final Color SNOW_COLOR = Color.white;
	private static final Color gridColor = new Color(200, 200, 200, 200);
	private static final int width = 795;
	private static final int height = 215;
	private static final int padding = 25;
	private static final int pointWidth = 4;
	private static final int labelPadding = 25;
	private static final int numberYDivisions = 8;
	private static final int yUnit = (height - padding * 2 - labelPadding) / numberYDivisions;
	private static final int boardHeight = height - padding * 2 - labelPadding;
	private static final int boardWidth = width - padding * 2 - labelPadding;
	private static final double xUnit = (double) boardWidth / (30 * 12);
	private static final int zeroYValue = (height - padding) / 2 + yUnit + 2;
	private static final double strokePadding = 0.25;

	private Color BAR_COLOR;
	private List<String> selectedMonths;
	private List<String> selectedYears;
	private ReadData dataPoints;
	private boolean[] checkedBoxes;
	private List<List<Integer>> orderDraw = new ArrayList<>();
	private List<List<Double>> yValues = new ArrayList<>();
	private List<List<Integer>> xValues = new ArrayList<>();
	private int numberOfPoints;
	static Dictionary valueDict = new Dictionary();

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// draw white background
		g2.setColor(BACKGROUND_COLOR);
		g2.fillRect(padding + labelPadding, padding, boardWidth, boardHeight);
		g2.setColor(Color.BLACK);

		// create grid lines for y axis.
		drawAxes(g2);

		for (int i = 0; i < checkedBoxes.length; i++) {
			// generate and save datapoints of Max Temperature/ Min Temperature/ Snow Fall
			generateTypeYValue(i, checkedBoxes[i]);
		}
		numberOfPoints = xValues.size();
		System.out.println("numberOfPoints is: " + numberOfPoints);

		setDrawOrder();
		System.out.println("order of Drawing are: " + orderDraw);
		// set graphs points
		List<List<Point2D.Double>> graphPoints = new ArrayList<>();
		System.out.println("xValues are: " + xValues);
		System.out.println("yValues are: " + yValues);
		for (int i = 0; i < 3; i++) {
			setGraphPoints(graphPoints, i);
			System.out.println("Graph Points at " + i + "are: " + graphPoints.get(i));
		}

		// drawbar
		drawBar(g2, graphPoints);

	}

	public void generateTypeYValue(int i, boolean checkedBox) {
		List<Double> yValues = new ArrayList<>();
		List<List<Integer>> xValues = new ArrayList<>();

		int length = this.dataPoints.getYCoors().size();

		for (int k = 0; k < length; k++) {
			double yValue;
			if (checkedBox) {
				yValue = this.dataPoints.getYCoors().get(k).get(i);
			} else {
				yValue = 0;
			}
			int month = this.dataPoints.getXCoors().get(k).get(1);
			int year = this.dataPoints.getXCoors().get(k).get(0);

			yValues.add(yValue);
			xValues.add(Arrays.asList(year, month));
		}

		setYValues(yValues);
		setXValues(xValues);
	}

	/// fix picking color
	private void drawBar(Graphics2D g2, List<List<Point2D.Double>> graphPoints) {
		for (int i = 0; i < numberOfPoints; i++) {
			for (int j = 0; j < 3; j++) {
				int type = orderDraw.get(i).get(j);
				System.out.println(type);

				double y1 = graphPoints.get(type).get(i).y;
				double x1 = graphPoints.get(0).get(i).x;
				g2.setColor(Color.black);
				g2.draw(new Rectangle2D.Double(padding + labelPadding + x1, decideStartPoint(y1), xUnit, Math.abs(y1)));
				setColor(type);
				g2.setColor(BAR_COLOR);
				g2.fill(new Rectangle2D.Double(padding + labelPadding + x1 + strokePadding, decideStartPoint(y1),
						xUnit - strokePadding, Math.abs(y1)));
			}
		}

	}

	private void setGraphPoints(List<List<Point2D.Double>> graphPoints, int yType) {
		double xScale = xUnit;
		int yScale = yUnit / 5;
		graphPoints.add(new ArrayList<>());
		for (int i = 0; i < numberOfPoints; i++) {
			System.out.println("yValues.size()" + yValues.size());
			int year = xValues.get(i).get(0);
			int month = xValues.get(i).get(1);
			int xPos = (year - 1990) * 12 + (month - 1);

			double x1 = xPos * xScale;
			double y1;
			if (checkedBoxes[yType])
				y1 = (yValues.get(yType).get(i) * yScale);
			else
				y1 = 0.0;
			graphPoints.get(yType).add(new Point2D.Double(x1, y1));
		}
	}

	private void setDrawOrder() {
		for (int i = 0; i < numberOfPoints; i++) {
			double maxValue = Math.abs(yValues.get(0).get(i));
			double minValue = Math.abs(yValues.get(1).get(i));
			double snowValue =Math.abs(yValues.get(2).get(i));

			if (maxValue >= minValue && maxValue >= snowValue) {
				// Here you determine second biggest, but you know that a is largest
				if (minValue > snowValue)
					orderDraw.add(Arrays.asList(0, 1, 2));
				else
					orderDraw.add(Arrays.asList(0, 2, 1));
			} else if (minValue >= maxValue && minValue >= snowValue) {
				// Here you determine second biggest, but you know that b is largest
				if (maxValue > snowValue)
					orderDraw.add(Arrays.asList(1, 0, 2));
				else
					orderDraw.add(Arrays.asList(1, 2, 0));
			}

			else if (snowValue >= minValue && snowValue >= maxValue) {
				// Here you determine second biggest, but you know that c is largest
				if (minValue > maxValue)
					orderDraw.add(Arrays.asList(2, 1, 0));
				else
					orderDraw.add(Arrays.asList(2, 0, 1));
			}
		}
	}

	private void drawAxes(Graphics2D g2) {
		for (int i = 0; i < numberYDivisions + 1; i++) {
			int x0 = padding + labelPadding;
			int x1 = pointWidth + padding + labelPadding;
			int y0P = (getHeight() - i * yUnit - padding * 2);
			g2.setColor(gridColor);
			g2.drawLine(padding + labelPadding + 1 + pointWidth, y0P, getWidth() - padding, y0P);
			g2.setColor(Color.BLACK);
			String yLabelPos = ((int) ((-15 + (40) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
			FontMetrics metrics = g2.getFontMetrics();
			int labelWidth = metrics.stringWidth(yLabelPos);
			g2.drawString(yLabelPos, x0 - labelWidth - 5, y0P + (metrics.getHeight() / 2) - 3);
			/// draw hatchmarks
			g2.drawLine(x0, y0P, x1, y0P);
		}

		// and for x axis

		for (int i = 0; i < 30; i++) {
			int x0 = i * (boardWidth) / (30) + padding + labelPadding;
			int x1 = x0;
			int y0 = (getHeight() - padding) / 2 + (boardHeight) / numberYDivisions;
			int y1 = y0 - pointWidth;
			g2.setColor(gridColor);
			g2.drawLine(x0 + 1, boardHeight + padding, x1 + 1, padding);
			g2.setColor(Color.BLACK);
			if (i % 2 != 0) {
				String xLabel = Dictionary.yearsA[i] + "";
				FontMetrics metrics = g2.getFontMetrics();
				int labelWidth = metrics.stringWidth(xLabel);
				g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
			}
			g2.drawLine(x0, y0 + 2, x1, y1 + 2);
		}

		// create x and y axes
		g2.drawLine(padding + labelPadding, boardHeight + padding, padding + labelPadding, padding);
		g2.drawLine(padding + labelPadding, zeroYValue, width - padding, zeroYValue);
	}

	private double decideStartPoint(double yValue) {
		if (yValue > 0) {
			return zeroYValue - Math.abs(yValue);
		} else
			return zeroYValue;
	}

	public void setColor(int type) {
		if (type == 0) {
			this.BAR_COLOR = BARMAX_COLOR;
		} else if (type == 1) {
			this.BAR_COLOR = BARMIN_COLOR;
		} else if (type == 2) {
			this.BAR_COLOR = SNOW_COLOR;
		}
	}

	public void setYValues(List<Double> yValues) {
		this.yValues.add(yValues);
	}

	public void setXValues(List<List<Integer>> xValues) {
		this.xValues = xValues;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	//// now it only draw one at a time.

	// the problem is it redraws all given datas for both max and min
	public BarChart(final List<String> selectedMonths, final List<String> selectedYears, boolean[] checkedBoxes) {
		this.selectedMonths = selectedMonths;
		this.selectedYears = selectedYears;
		this.checkedBoxes = checkedBoxes;
		this.dataPoints = new ReadData(this.selectedYears, this.selectedMonths);
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

	//// test
	/*
	 * super.paintComponent(g); Graphics2D g2 = (Graphics2D)g;
	 * g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	 * RenderingHints.VALUE_ANTIALIAS_ON); g2.drawRect(0, 0, 50, 50);
	 */
}
