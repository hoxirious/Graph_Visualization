
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

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
		setBounds(100, 100, 846, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		JPanel panel = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.EAST, panel, 761, SpringLayout.WEST, contentPane);
		panel.setBackground(Color.LIGHT_GRAY);
		sl_contentPane.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panel, 225, SpringLayout.NORTH, contentPane);
		contentPane.add(panel);

		JLabel typeChart = new JLabel("Type of chart");
		sl_contentPane.putConstraint(SpringLayout.WEST, typeChart, 129, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, typeChart, -123, SpringLayout.SOUTH, contentPane);
		typeChart.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(typeChart);

		JLabel months = new JLabel("Select a month");
		sl_contentPane.putConstraint(SpringLayout.WEST, months, 398, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, months, -123, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, months, -307, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, typeChart, 0, SpringLayout.NORTH, months);
		contentPane.add(months);

		JLabel years = new JLabel("Select a year");
		sl_contentPane.putConstraint(SpringLayout.EAST, typeChart, -58, SpringLayout.WEST, years);
		sl_contentPane.putConstraint(SpringLayout.WEST, years, 267, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, years, -123, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, years, -51, SpringLayout.WEST, months);
		contentPane.add(years);

		JList listTypeChart = new JList(Dictionary.listTypeChartA);
		sl_contentPane.putConstraint(SpringLayout.WEST, listTypeChart, 0, SpringLayout.WEST, typeChart);
		listTypeChart.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listTypeChart.setSelectedIndex(0);
		contentPane.add(listTypeChart);

		JList listYears = new JList(Dictionary.yearsA);
		sl_contentPane.putConstraint(SpringLayout.WEST, listYears, 33, SpringLayout.EAST, listTypeChart);
		listYears.setVisibleRowCount(5);
		sl_contentPane.putConstraint(SpringLayout.NORTH, listYears, -107, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, listYears, 373, SpringLayout.SOUTH, contentPane);
		listYears.setSelectedIndex(0);

		JList listMonths = new JList(Dictionary.monthsA);
		sl_contentPane.putConstraint(SpringLayout.NORTH, listMonths, 6, SpringLayout.SOUTH, months);
		sl_contentPane.putConstraint(SpringLayout.WEST, listMonths, -5, SpringLayout.WEST, contentPane);
		listMonths.setVisibleRowCount(5);
		sl_contentPane.putConstraint(SpringLayout.EAST, listMonths, -221, SpringLayout.EAST, contentPane);
		listMonths.setSelectedIndex(0);

		JCheckBox maxTemp = new JCheckBox("Maximum Temp");
		sl_contentPane.putConstraint(SpringLayout.NORTH, listTypeChart, 3, SpringLayout.NORTH, maxTemp);
		sl_contentPane.putConstraint(SpringLayout.NORTH, maxTemp, 28, SpringLayout.SOUTH, panel);
		sl_contentPane.putConstraint(SpringLayout.WEST, maxTemp, 535, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, maxTemp, -98, SpringLayout.SOUTH, contentPane);
		maxTemp.setSelected(false);
		contentPane.add(maxTemp);

		JCheckBox minTemp = new JCheckBox("Minimum Temp");
		sl_contentPane.putConstraint(SpringLayout.NORTH, minTemp, 6, SpringLayout.SOUTH, maxTemp);
		sl_contentPane.putConstraint(SpringLayout.WEST, minTemp, 0, SpringLayout.WEST, maxTemp);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, minTemp, -68, SpringLayout.SOUTH, contentPane);
		maxTemp.setSelected(true);
		contentPane.add(minTemp);

		JCheckBox snowFall = new JCheckBox("Snow Fall");
		sl_contentPane.putConstraint(SpringLayout.NORTH, snowFall, 6, SpringLayout.SOUTH, minTemp);
		sl_contentPane.putConstraint(SpringLayout.WEST, snowFall, 0, SpringLayout.WEST, maxTemp);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, snowFall, -39, SpringLayout.SOUTH, contentPane);
		maxTemp.setSelected(false);
		contentPane.add(snowFall);

		JButton generateButton = new JButton("Generate");
		sl_contentPane.putConstraint(SpringLayout.NORTH, generateButton, 6, SpringLayout.SOUTH, snowFall);
		sl_contentPane.putConstraint(SpringLayout.WEST, generateButton, 535, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, generateButton, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, generateButton, 3, SpringLayout.EAST, minTemp);
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				List<String> months = listMonths.getSelectedValuesList();
				List<String> years = listYears.getSelectedValuesList();
				String type = (String) listTypeChart.getSelectedValue();

				boolean[] checkedBoxes = { maxTemp.isSelected(), minTemp.isSelected(), snowFall.isSelected() };

				/// add barchart into the panel
				if (type.equals("Bar Chart")) {
					panel.add(new BarChart(months, years, checkedBoxes));
					panel.revalidate();
					panel.repaint();
				// add scatterchart into the panel
				} else if (type.equals("Scatter Chart")) {
					panel.add(new ScatterChart(months, years, checkedBoxes));
					panel.revalidate();
					panel.repaint();
				}
			}
		});
		contentPane.add(generateButton);

		JScrollPane monthsScrollPane = new JScrollPane(listMonths);
		sl_contentPane.putConstraint(SpringLayout.NORTH, monthsScrollPane, 6, SpringLayout.SOUTH, months);
		sl_contentPane.putConstraint(SpringLayout.WEST, monthsScrollPane, 396, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, monthsScrollPane, -35, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, monthsScrollPane, 0, SpringLayout.EAST, months);
		sl_contentPane.putConstraint(SpringLayout.EAST, listYears, -42, SpringLayout.WEST, monthsScrollPane);
		monthsScrollPane.setMinimumSize(new Dimension(100, 50));
		contentPane.add(monthsScrollPane);

		JScrollPane yearsScrollPane = new JScrollPane(listYears);
		sl_contentPane.putConstraint(SpringLayout.EAST, listTypeChart, -63, SpringLayout.WEST, yearsScrollPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, yearsScrollPane, 6, SpringLayout.SOUTH, years);
		sl_contentPane.putConstraint(SpringLayout.WEST, yearsScrollPane, 266, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, yearsScrollPane, -30, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, yearsScrollPane, -68, SpringLayout.WEST, monthsScrollPane);
		yearsScrollPane.setMinimumSize(new Dimension(100, 50));
		contentPane.add(yearsScrollPane);
	}
}
