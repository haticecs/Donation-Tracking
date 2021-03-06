import java.awt.Color;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;

import org.jfree.chart.*;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.util.Rotation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class stats_general extends JFrame{
	int posX;
	int posY;
	public stats_general(String title,String title2,String[][]arr) {

		 initUI(title, title2,arr);
	}
	public void initUI(String title, String chartTitle,String[][]arr) {
		setTitle("İstatistikler");
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 605, 370);
		getContentPane().add(panel);
				
		//move window
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				posX=e.getX();			
				posY=e.getY();
	        }	
	    });
		this.addMouseMotionListener(new MouseAdapter(){
			public void mouseDragged(MouseEvent evt){		
				setLocation (evt.getXOnScreen()-posX,evt.getYOnScreen()-posY);	        					
	        }
		});
	    //move window
		
		PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
			    "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0.0%"));
		PieDataset mezun_bolum_dataset = createDataset(arr);
		
		JFreeChart mezun_bolum_chart = createChart(mezun_bolum_dataset, chartTitle,gen);

        ChartPanel mezun_bolum_chartPanel = new ChartPanel(mezun_bolum_chart);
        panel.add(mezun_bolum_chartPanel);
        mezun_bolum_chartPanel.setPreferredSize(new java.awt.Dimension(600, 370));
        mezun_bolum_chartPanel.addChartMouseListener(new ChartMouseListener() {
            @Override
            public void chartMouseClicked(ChartMouseEvent event) {
            }
            @Override
            public void chartMouseMoved(ChartMouseEvent event) {
            }
        });
	    
        JButton btnGeri = new JButton("Geri");
		btnGeri.setBounds(485, 375, 89, 23);
		getContentPane().add(btnGeri);
		btnGeri.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {		
        		dispose();
			}
        });
		panel.setBackground(Color.white);
		getContentPane().setBackground(Color.WHITE);
	}
	private  PieDataset createDataset(String [][]arr) {
		DefaultPieDataset set = new DefaultPieDataset();
		for(int x=0;x<arr.length;x++) {
			set.setValue(arr[x][0], Integer.parseInt(arr[x][1]));
		}
		return set;
	}
	private JFreeChart createChart(PieDataset dataset, String title,PieSectionLabelGenerator w) {
		JFreeChart chart = ChartFactory.createPieChart3D(
			title,                  // chart title
	      	dataset,                // data
	        true,                   // include legend
	        true,
	        false
	    );
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.9f);
		plot.setLabelGenerator(w);
		return chart;
	}
}
