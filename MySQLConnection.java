import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class MySQLConnection {
	private String kullanıcı_adı = "root";
	private String db_name = "donation";
	private String host = "localhost";
	private int port = 3307;

	String url = "jdbc:mysql://" + host + ":" + port + "/" + db_name;

	public Connection  getmysql_connection() {
		 Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException ex) {
			Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			con = (Connection) DriverManager.getConnection(url, "root", null);
		} catch (SQLException ex) {
			Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null,"Database bağlantısı kurulamadı");
			System.exit(0);
		}
		return con;
	}

	
}
