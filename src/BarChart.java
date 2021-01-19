import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

public class BarChart extends JPanel {
	    private static final Color BACKGROUND_COLOR = Color.white;
	    private static final Color BARMAX_COLOR = Color.red;
	    private static final Color BARMIN_COLOR = Color.blue;

	    private  List <String> selectedMonths;
	    private  List <String> selectedYears;
	    

	    public BarChart(final List <String> selectedMonths, final List <String> selectedYears) {
	    	this.selectedMonths = selectedMonths;
	    	this.selectedYears = selectedYears;	   
	    	ReadData file = new ReadData(selectedYears,selectedMonths); 
	    }

	    @Override
	    protected void paintComponent(final Graphics g) {
	        super.paintComponent(g);

	        drawBars(g);
	    }

	    private void drawBars(final Graphics g) {
//	        int /*i,*/ OUTER_MARGIN = 20,
//	                WIDTH = 800 + 2 * OUTER_MARGIN,
//	                HEIGHT = 600 + 2 * OUTER_MARGIN;
//	                /*SPACE_BETWEEN_BARS = 10, SPACE_ON_TOP_BOTTOM = 25;*/
//
//	        g.setColor(BACKGROUND_COLOR);
//	        g.fillRect(0, 0, WIDTH, HEIGHT);
//
//	        g.setColor(BAR_COLOR);
//	        final int barWidth = 20;
//	        for (int itemIndex = 0; itemIndex < inputData.length; itemIndex++) {
//	            final int x = OUTER_MARGIN + 25 * itemIndex;
//	            final int barHeight = 10 * inputData[itemIndex];
//	            final int y = [...y is calculated using barHeight; the higher the bar, the lower y should be...];
//	            g.fillRect(x, y, barWidth, barHeight);
//	        }
	}
}
