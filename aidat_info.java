import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

import java.awt.*;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

public class aidat_info extends javax.swing.JFrame{
	private JTextField textField;
	public aidat_info() {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year = localDate.getYear();
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 485, 461);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblTl = new JLabel("TL");
		lblTl.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblTl.setBounds(186, 427, 30, 14);
		panel.add(lblTl);
		
		textField = new JTextField();
		textField.setFont(new Font("Calibri", Font.PLAIN, 14));
		textField.setText(AidatRead());
		textField.setEditable(false);
		textField.setBounds(127, 420, 57, 30);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblGncelAidatTutar = new JLabel("Güncel Aidat Tutarı: ");
		lblGncelAidatTutar.setForeground(new Color(128, 0, 0));
		lblGncelAidatTutar.setFont(new Font("Calibri", Font.BOLD, 14));
		lblGncelAidatTutar.setBounds(10, 427, 120, 14);
		panel.add(lblGncelAidatTutar);
		
		JLabel aidat_tutar_label = new JLabel("AİDAT TUTAR BİLGİLERİ");
		aidat_tutar_label.setForeground(new Color(128, 0, 0));
		aidat_tutar_label.setFont(new Font("Calibri", Font.BOLD, 20));
		aidat_tutar_label.setBounds(126, 11, 200, 40);
		panel.add(aidat_tutar_label);

		JTextPane bilgi = new JTextPane();
		bilgi.setEditable(false);
		bilgi.setFont(new Font("Calibri", Font.PLAIN, 12));
		bilgi.setText(AidatBilgisiRead());
		bilgi.setBounds(10, 49, 465, 360);
		panel.add(bilgi);
		
		JButton btnGeri = new JButton("Geri");
		btnGeri.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnGeri.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnGeri.setBounds(418, 11, 57, 23);
		panel.add(btnGeri);
		
		JButton update = new JButton("Bilgileri Güncelle");
		update.setForeground(new Color(0, 0, 0));
		update.setFont(new Font("Calibri", Font.PLAIN, 12));
		update.setBounds(346, 427, 129, 23);
		update.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bilgi.setEditable(true);
				update.setVisible(false);
				textField.setEditable(true);
				
				JButton save = new JButton("Kaydet");
				save.setForeground(new Color(0, 0, 0));
				save.setFont(new Font("Calibri", Font.PLAIN, 12));
				save.setBounds(346, 427, 129, 23);
				panel.add(save);
				
				save.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						save.setVisible(false);
						update.setVisible(true);
						bilgi.setEditable(false);
						textField.setEditable(false);
						String x=textField.getText();
						AidatLog(x);
						x=bilgi.getText();
						AidatBilgisiLog(x);
					}
				});
			}
		});
		panel.add(update);
	}
	public String AidatRead() {
		String documentpath=FileSystemView.getFileSystemView().getDefaultDirectory().getPath()+"\\Donation Tracking";
    	File file = new File(documentpath+"\\aidatlar.txt");
    	String new_borc="";
		try(Scanner fileReader = new Scanner(file)) {
			new_borc=fileReader.nextLine();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			new_borc="100";
		}
		return new_borc;
	}
	public void AidatLog(String x) {
        try{
        	String documentpath=FileSystemView.getFileSystemView().getDefaultDirectory().getPath()+"\\Donation Tracking";
        	File file = new File(documentpath+"\\aidatlar.txt");
            FileWriter fw = new FileWriter(file.getAbsoluteFile(),false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(x);
            bw.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
	public String AidatBilgisiRead() {
		String documentpath=FileSystemView.getFileSystemView().getDefaultDirectory().getPath()+"\\Donation Tracking";
    	File file = new File(documentpath+"\\aidat_bilgisi.txt");
    	String bilgi="";
		try(Scanner fileReader = new Scanner(file)) {
			bilgi = fileReader.useDelimiter("\\A").next();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			bilgi="- 2010 yılında 2016 yılına kadar aidat bedeli 50 TL olarak alınmıştır.\r\n- 2017 ve sonrası için aidat bedeli 60 TL olarak güncellenmiştir.\r\n- 23.02.2016 tarihi itibariyle alınan karar sebebi ile  üye olmak isteyen mezunlarımıza mezuniyetinin ilk yılında derneğimize üye olursa aidat bedeli alınmayacaktır. Ücretsizdir.\r\n- Karar 01.04.2019 tarihinde kaldırılmış ve 1 NİSAN 2019 tarihi ile gelenler için normal 2019 aidat bedeli olan 60 TL alınacaktır. \r\n- 2020 yılı için tekrar güncelleme alınmıştır ve 2020 güncel aidat bedeli 100 TL olmuştur. Bu kararın alındığı 20.01.2020 tarihi itibariyle ise mezun kişi mezuniyetinin ilk 1 yıl içerisinde gelirse 60 TL ile giriş yapabilecektir. Tüm diğer eski üyeler için 2020 aidatı 100 TL olarak alınacaktır. \r\n- Şehit çocuklarından dernek yıllık aidat bedeli alınmamaktadır.\r\n- Çalışan dernek personellerimiz çalışmaya başladığı dönem boyunca aidat tutarından muaftır. \r\n";
		}
		return bilgi;
	}
	public void AidatBilgisiLog(String x) {
        try{
        	String documentpath=FileSystemView.getFileSystemView().getDefaultDirectory().getPath()+"\\Donation Tracking";
        	File file = new File(documentpath+"\\aidat_bilgisi.txt");
            FileWriter fw = new FileWriter(file.getAbsoluteFile(),false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(x);
            bw.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
	
}
