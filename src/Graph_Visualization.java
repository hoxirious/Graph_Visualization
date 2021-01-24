

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JToggleButton;

public class Graph_Visualization extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Graph_Visualization frame = new Graph_Visualization();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public Graph_Visualization() {	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		sl_contentPane.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panel, 225, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panel, 509, SpringLayout.WEST, contentPane);
		contentPane.add(panel);
		
		JLabel typeChart = new JLabel("Type of chart");
		sl_contentPane.putConstraint(SpringLayout.WEST, typeChart, 22, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, typeChart, 105, SpringLayout.WEST, contentPane);
		typeChart.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(typeChart);

		JLabel months = new JLabel("Select a month");
		sl_contentPane.putConstraint(SpringLayout.NORTH, typeChart, 0, SpringLayout.NORTH, months);
		sl_contentPane.putConstraint(SpringLayout.NORTH, months, 16, SpringLayout.SOUTH, panel);
		sl_contentPane.putConstraint(SpringLayout.WEST, months, 234, SpringLayout.WEST, contentPane);
		contentPane.add(months);
		
		JLabel years = new JLabel("Select a year");
		sl_contentPane.putConstraint(SpringLayout.NORTH, years, 0, SpringLayout.NORTH, months);
		sl_contentPane.putConstraint(SpringLayout.WEST, years, 129, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, years, -24, SpringLayout.WEST, months);
		contentPane.add(years);
		
		JList<String> listTypeChart = new JList<>(Dictionary.listTypeChartA);
		sl_contentPane.putConstraint(SpringLayout.NORTH, listTypeChart, 6, SpringLayout.SOUTH, typeChart);
		sl_contentPane.putConstraint(SpringLayout.WEST, listTypeChart, 32, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, listTypeChart, -10, SpringLayout.EAST, typeChart);
		listTypeChart.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listTypeChart.setSelectedIndex(0);
		contentPane.add(listTypeChart);
		
		
		JList<String> listYears = new JList<>(Dictionary.yearsA);
		sl_contentPane.putConstraint(SpringLayout.WEST, listYears, 33, SpringLayout.EAST, listTypeChart);
		listYears.setVisibleRowCount(5);
		sl_contentPane.putConstraint(SpringLayout.NORTH, listYears, -107, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, listYears, 373, SpringLayout.SOUTH, contentPane);
		listYears.setSelectedIndex(0);
		
		
		JList<String> listMonths = new JList<>(Dictionary.monthsA);
		sl_contentPane.putConstraint(SpringLayout.NORTH, listMonths, 6, SpringLayout.SOUTH, months);
		sl_contentPane.putConstraint(SpringLayout.WEST, listMonths, -5, SpringLayout.WEST, contentPane);
		listMonths.setVisibleRowCount(5);
		sl_contentPane.putConstraint(SpringLayout.EAST, listMonths, -221, SpringLayout.EAST, contentPane);
		listMonths.setSelectedIndex(0);

		
		
		JCheckBox maxTemp = new JCheckBox("Maximum Temp");
		sl_contentPane.putConstraint(SpringLayout.WEST, maxTemp, 340, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, months, -35, SpringLayout.WEST, maxTemp);
		sl_contentPane.putConstraint(SpringLayout.NORTH, maxTemp, 28, SpringLayout.SOUTH, panel);
		maxTemp.setSelected(false);
		contentPane.add(maxTemp);
		
		
		JCheckBox minTemp = new JCheckBox("Minimum Temp");
		sl_contentPane.putConstraint(SpringLayout.NORTH, minTemp, 281, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, maxTemp, -11, SpringLayout.NORTH, minTemp);
		sl_contentPane.putConstraint(SpringLayout.WEST, minTemp, 0, SpringLayout.WEST, maxTemp);
		maxTemp.setSelected(true);
		contentPane.add(minTemp);
		
		
		JCheckBox snowFall = new JCheckBox("Snow Fall");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, minTemp, -6, SpringLayout.NORTH, snowFall);
		sl_contentPane.putConstraint(SpringLayout.NORTH, snowFall, 311, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, snowFall, 43, SpringLayout.EAST, listMonths);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, snowFall, -39, SpringLayout.SOUTH, contentPane);
		maxTemp.setSelected(false);
		contentPane.add(snowFall);
		
		JButton generateButton = new JButton("Generate");
		sl_contentPane.putConstraint(SpringLayout.WEST, generateButton, 341, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, generateButton, -93, SpringLayout.EAST, contentPane);
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List <String> months = listMonths.getSelectedValuesList();
				List <String> years = listYears.getSelectedValuesList();
				String type = (String) listTypeChart.getSelectedValue();
				boolean [] checkedBoxes = {maxTemp.isSelected(), minTemp.isSelected(), snowFall.isSelected()}; 
				
				if(type.equals("Bar Chart")) {
					panel.add(new BarChart(months,years, checkedBoxes));
					panel.validate();
					System.out.println(panel.getSize());
				}
				else {
					ScatterChart ScatterChart = new ScatterChart(months,years);
				}
								
			}
		});
		
		sl_contentPane.putConstraint(SpringLayout.NORTH, generateButton, 6, SpringLayout.SOUTH, snowFall);
		contentPane.add(generateButton);
		
		JScrollPane monthsScrollPane = new JScrollPane(listMonths);
		sl_contentPane.putConstraint(SpringLayout.NORTH, monthsScrollPane, 6, SpringLayout.SOUTH, months);
		sl_contentPane.putConstraint(SpringLayout.WEST, monthsScrollPane, 0, SpringLayout.WEST, months);
		sl_contentPane.putConstraint(SpringLayout.EAST, listYears, -42, SpringLayout.WEST, monthsScrollPane);
		monthsScrollPane.setMinimumSize(new Dimension(100,50));
		contentPane.add(monthsScrollPane);
		
		JScrollPane yearsScrollPane = new JScrollPane(listYears);
		sl_contentPane.putConstraint(SpringLayout.NORTH, yearsScrollPane, 6, SpringLayout.SOUTH, years);
		sl_contentPane.putConstraint(SpringLayout.WEST, yearsScrollPane, 0, SpringLayout.WEST, years);
		sl_contentPane.putConstraint(SpringLayout.EAST, yearsScrollPane, 64, SpringLayout.WEST, years);
		yearsScrollPane.setMinimumSize(new Dimension(100,50));
		contentPane.add(yearsScrollPane);
	}
}
