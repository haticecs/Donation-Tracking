import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.text.DecimalFormat;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.*;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.util.Rotation;
import org.jfree.data.general.*;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
public class bolum_stats extends JFrame{
	int posX;
	int posY;
	int toplam=0;
	public bolum_stats(String title, String chartTitle) {
		super("statfrm");
		super.setBackground(Color.WHITE);
		super.setForeground(Color.WHITE);
		setTitle("İstatistikler");
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setForeground(Color.WHITE);
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
	    String arr[][]= {{"Mühendislik Fakültesi","29"},{"Tıp Fakültesi","20"},
	    		{"İktisadi ve İdari Bilimler Fakültesi","51"},{"Hukuk Fakültesi","5"},
	    		{"Mimarlık ve Tasarım Fakültesi","15"},{"Fen Edebiyat Fakültesi","60"},
	    		{"Yabancı Diller Bölümü","20"},{"Fen Bilimleri Enstitüsü","40"},
	    		{"Sosyal Bilimler Enstitüsü","30"},{"Sağlık Bilimleri Enstitüsü","50"}};
	    
	    String arr2[][]= {{"İktisat","20"},{"İşletme","40"},{"İşletme","60"},{"Uluslararası Girişimcilik","20"}};
	    String arr3[][]= {{"Bilgisayar Mühendisliği","10"},{"Biyomedikal Mühendisliği","20"},
	    		{"Elektrik-Elektronik Mühendisliği","30"},{"Endüstri Mühendisliği","40"},{"Makine Mühendisliği","10"},
	    		{"Malzeme Bilimi ve Nanoteknoloji Müh.","10"},{"Mekatronik Mühendisliği Yandal Programı","1"},
	    		{"Yapay Zeka Mühendisliği","1"}};
	    		 
	    System.out.println(chartTitle);
	    PieDataset mezun_bolum_dataset = createDataset(arr);
	    if(chartTitle.equals("İktisadi")) {
	    	mezun_bolum_dataset = createDataset(arr2);
	    }
	    if(chartTitle.equals("Mühendislik")) {
	    	mezun_bolum_dataset = createDataset(arr3);
	    }
	    
	    JFreeChart mezun_bolum_chart = createChart(mezun_bolum_dataset, chartTitle,gen);
        
        JPanel panel_1 = new JPanel();
        panel_1.setForeground(Color.WHITE);
        getContentPane().add(panel_1, BorderLayout.CENTER);
        ChartPanel mezun_bolum_chartPanel = new ChartPanel(mezun_bolum_chart);
        panel_1.add(mezun_bolum_chartPanel);
        mezun_bolum_chartPanel.setPreferredSize(new java.awt.Dimension(600, 370));
        mezun_bolum_chartPanel.addChartMouseListener(new ChartMouseListener() {
            @Override
            public void chartMouseClicked(ChartMouseEvent event) {
            }
            @Override
            public void chartMouseMoved(ChartMouseEvent event) {
            }
        });
	    
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setForeground(Color.WHITE);
        panel.setBackground(Color.WHITE);
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        JButton btnGeri = new JButton("Geri");
        panel.add(btnGeri);
        btnGeri.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {		
        		setVisible(false);
			}
        });
        //frmstats.getContentPane().setLayout(groupLayout);
	}

	private  PieDataset createDataset(String [][]arr) {
		DefaultPieDataset set = new DefaultPieDataset();
		for(int x=0;x<arr.length;x++) {
			set.setValue(arr[x][0], Integer.parseInt(arr[x][1]));
			toplam=toplam+Integer.parseInt(arr[x][1]);
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
	public static void centreWindow(JFrame frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}      
}
