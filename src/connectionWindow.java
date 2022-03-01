import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class connectionWindow {
	Statement statement;
	ResultSet  rs;
	PreparedStatement ps;
	Connection conn; 
	
	public connectionWindow() {
	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/ssurvey","root","");
			statement = conn.createStatement();
			
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
