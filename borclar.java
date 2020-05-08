import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.awt.event.ActionEvent;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.*;
import javax.swing.GroupLayout.*;

import java.awt.*;

import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class borclar extends javax.swing.JFrame {
	ArrayList<person> pArr = new ArrayList<person>();
	Date date = new Date();
	LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	int year  = localDate.getYear();
	private javax.swing.JButton aidatlar_btn;
    private javax.swing.JButton geri_btn;
    private javax.swing.JButton borc_btn;
    private javax.swing.JButton kisiler_btn;
    /*private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;*/
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea borclar_txta;
    private javax.swing.JTextArea kisiler_txta;
    private javax.swing.JTextField row_txtf;
    private javax.swing.JTextField ara_txtf;
    private javax.swing.JTextField borclar_txtf;
    
	JFrame frmAidatlar;

	
	public borclar(ArrayList<person> list) {
		initialize(list);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ArrayList<person> list) {
		pArr=list;
		Color maroon=Color.decode("#800000");
		frmAidatlar = new JFrame();
		frmAidatlar.setTitle("Borclar");
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frmAidatlar.setBounds(0, 0,screen.width,screen.height);
		frmAidatlar.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmAidatlar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		borclar_txtf = new JTextField("Borclar");
		borclar_txtf.setEditable(false);
	    borclar_txtf.setBackground(Color.GRAY);
		borclar_txtf.setHorizontalAlignment(SwingConstants.LEFT);
		borclar_txtf.setFont(new java.awt.Font("Times New Roman", 0, 25)); // NOI18N
        borclar_txtf.setText("BORÇLAR");
		
        Object[][] o=new Object[list.size()][year-2010+4];
        
        for (Object[] row: o) {Arrays.fill(row, "");}
        

        for (int count=0;count<list.size();count++) {
        	o[count][0]=list.get(count).getKimlikNo_lbl();
        	o[count][1]=list.get(count).getAd_lbl();
        	o[count][2]=list.get(count).getSoy_lbl();
        	int giriş = Integer.parseInt(list.get(count).getGirisTarihi_lbl().substring(6));
        	for(int d=giriş-2010;d<(year-2010+1);d++) {
        		o[count][d+3]=list.get(count).getBorcarray()[d];
        	}
        }
        String [] rows= {"TC","İsim","Soyisim","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020"};
        
		aidatlar_btn = new JButton("Aidatlar");
		aidatlar_btn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		aidatlar_btn.setForeground(Color.BLACK);
		aidatlar_btn.setBackground(maroon);
		aidatlar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frmAidatlar.setVisible(false);
				
				try {
			            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
			                if ("Windows".equals(info.getName())) {
			                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
			                    break;
			                }
			            }
			        } catch (ClassNotFoundException ex) {
			            java.util.logging.Logger.getLogger(aidatlar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			        } catch (InstantiationException ex) {
			            java.util.logging.Logger.getLogger(aidatlar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			        } catch (IllegalAccessException ex) {
			            java.util.logging.Logger.getLogger(aidatlar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
			            java.util.logging.Logger.getLogger(aidatlar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			        }
			        //</editor-fold>
			        //</editor-fold>

			        /* Create and display the form */
			        java.awt.EventQueue.invokeLater(new Runnable() {
			            public void run() {
			            	aidatlar window = new aidatlar(list);
							window.frmAidatlar.setVisible(true);
			            }
			        });
			}
		});
		
		geri_btn = new JButton("Geri");
		geri_btn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		geri_btn.setForeground(Color.BLACK);
		geri_btn.setBackground(maroon);
		geri_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GeriActionPerformed(evt);
            }
        });
		
		JScrollPane top = new JScrollPane();
		
		ara_txtf = new JTextField("ARA");
		ara_txtf.setHorizontalAlignment(SwingConstants.CENTER);
		ara_txtf.setEnabled(false);
		ara_txtf.setEditable(false);
		ara_txtf.setForeground(Color.BLACK);
		ara_txtf.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		row_txtf = new JTextField();
		row_txtf.setColumns(10);
		row_txtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personalActionPerformed(evt);
            }
        });
        row_txtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

		
		borc_btn = new JButton("Borç toplam");
        borc_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	aidatlarActionPerformed(evt);
             }
        });
        
        kisiler_btn = new JButton("Borçlu Sayısı");
        kisiler_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	kisilerActionPerformed(evt);
            }
        });
		
        borclar_txta = new JTextArea();
        borclar_txta.setFont(new Font("Times New Roman", Font.BOLD, 14));
        borclar_txta.setEditable(false);
		
        kisiler_txta = new JTextArea();
        kisiler_txta.setFont(new Font("Times New Roman", Font.BOLD, 14));
        kisiler_txta.setEditable(false);
        kisiler_txta.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frmAidatlar.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(borclar_txtf, GroupLayout.PREFERRED_SIZE, 411, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 655, Short.MAX_VALUE)
					.addComponent(ara_txtf, 44, 44, 44)
					.addGap(18)
					.addComponent(row_txtf, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(top, GroupLayout.DEFAULT_SIZE, 1330, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(252)
					.addComponent(borc_btn)
					.addPreferredGap(ComponentPlacement.RELATED, 673, Short.MAX_VALUE)
					.addComponent(kisiler_btn)
					.addGap(245))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(42)
							.addComponent(aidatlar_btn, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(148)
							.addComponent(borclar_txta, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 447, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(geri_btn, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addGap(65))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(kisiler_txta, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
							.addGap(127))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(row_txtf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(ara_txtf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(borclar_txtf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(top, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(borc_btn)
						.addComponent(kisiler_btn))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(kisiler_txta, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
						.addComponent(borclar_txta, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(geri_btn, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(aidatlar_btn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(46))
		);
		
		jTable1 = new JTable();
		jTable1.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(o,rows));
		jTable1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmAidatlar.setVisible(false);
				int rowSelected =jTable1.getSelectedRow();  // bi türlü olmadı row seçemedim -1 veriyo !!
        		personal p = new personal(list, 0);
        		//p.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        		p.setSize(700, 600);
        		centreWindow(p);
        		p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	    p.setVisible(true);
        		
			}
		});
		jTable1.setEnabled(false);
		top.setViewportView(jTable1);
		frmAidatlar.getContentPane().setLayout(groupLayout);
		frmAidatlar.getContentPane().setBackground(Color.GRAY);
		
	}
	
	private void GeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GeriActionPerformed
        // TODO add your handling code here:
    	
    	frmAidatlar.setVisible(false);
		main m = new main(pArr);
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setVisible(true);
		centreWindow(m);
    	
    }//GEN-LAST:event_GeriActionPerformed
	
	private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTable1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(row_txtf.getText().trim()));
    }//GEN-LAST:event_jTextField1KeyPressed

    private void personalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    	frmAidatlar.setVisible(false);
    	int rowSelected =jTable1.getSelectedRow();
		personal p = new personal(pArr, rowSelected);
		//p.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		p.setSize(700, 600);
		p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		centreWindow(p);
	    p.setVisible(true);
    }//GEN-LAST:event_jTextField1ActionPerformed
	
	private void aidatlarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
		 double toplam = 0;
         int count=0;
         String[] name = new String[50] ;
         int [] yearsum = new int[50];
          
        
             for (int y = 3; y < jTable1.getColumnCount(); y++) {
                  for (int x = 0; x < jTable1.getRowCount(); x++) {
                 
                      if(jTable1.getValueAt(x, y).toString() != ""){
                 toplam = toplam + Double.parseDouble(jTable1.getValueAt(x, y).toString());
                 
                  }
                      
         }
                  yearsum[count]= (int)toplam;
                  name[count] = jTable1.getColumnName(y);
                  count++;
                  toplam=0;
       
         }
             String show = "";
             int toplamx = 0;
             for(int i=0; i<count; i++){
                 show = show + name[i]+  ": " + (int) yearsum[i] + ".00 TL\n";
                 toplamx = toplamx +(int) yearsum[i];
             }
            
             borclar_txta.setText(show +  "TOPLAM: " +toplamx+".00 TL");
  
        
    }//GEN-LAST:event_jButton3ActionPerformed
	
	private void kisilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
		double toplam = 0;
        int count=0;
        String[] name = new String[50] ;
        int [] yearsum = new int[50];
         
       
            for (int y = 3; y < jTable1.getColumnCount(); y++) {
                 for (int x = 0; x < jTable1.getRowCount(); x++) {
                
                     if(jTable1.getValueAt(x, y).toString() != "" && Double.parseDouble(jTable1.getValueAt(x, y).toString()) != 0.0 ){
                toplam = toplam + 1;
                
                 }
                     
        }
                 yearsum[count]= (int)toplam;
                 name[count] = jTable1.getColumnName(y);
                 count++;
                 toplam=0;
      
        }
            String show = "";
            int toplamx = 0;
            for(int i=0; i<count; i++){
                show = show + name[i]+  ": " + (int) yearsum[i] + " kişi\n";
                toplamx = toplamx +(int) yearsum[i];
            }
            kisiler_txta.setText(show + "TOPLAM: " +toplamx + " kişi");    
        
    }//GEN-LAST:event_BorÃ§larActionPerformed*/
	public static void centreWindow(JFrame frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
}
