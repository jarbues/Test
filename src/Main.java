import java.net.URISyntaxException;
import java.sql.*;

public class Main {
    
    public static void main(String[] args) throws Exception 
    {
        
        Connection connection = getConnection();
        
        Statement stmt = connection.createStatement();
        
        // Simple Database creation and inserting values
        /*stmt.executeUpdate("DROP TABLE IF EXISTS first");
        stmt.executeUpdate("CREATE TABLE first"
        		+ "( ID CHAR(5) PRIMARY KEY, "
        		+ "NAME VARCHAR, "
        		+ "ADDRESS VARCHAR);");
        stmt.executeUpdate("INSERT INTO first"
        		+ "(ID, NAME, ADDRESS) "
        		+ "VALUES "
        		+ "('SIOJK', 'CARL', 'TOWN')");
        stmt.executeUpdate("INSERT INTO first"
        		+ "(ID, NAME, ADDRESS) "
        		+ "VALUES "
        		+ "('LOJKF', 'SAM', 'PLACE')");*/
        
        System.out.println("querying SELECT * FROM first");
        ResultSet rs = stmt.executeQuery("SELECT * FROM first");
        ResultSetMetaData rsmd = rs.getMetaData();
        
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) 
        {
            for (int i = 1; i <= columnsNumber; i++) 
            {
                if (i > 1) System.out.print(",  ");
                String columnValue = rs.getString(i);
                System.out.print(rsmd.getColumnName(i) + " " + columnValue);
            }
            System.out.println("");
        }
    }
    
    private static Connection getConnection() throws URISyntaxException, SQLException {
    	Connection c = null;

        String dbUrl = "jdbc:postgresql://ec2-23-23-216-40.compute-1.amazonaws.com:5432/d5i8tc220p5e26?&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        String uname = "nzspjzexyynxnz";
        String pass = "811ecfc859e720d7850f544af5f010c10d1bf60e15abfceaf0f3356fa77e5b6e";
        try {
        	Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbUrl, uname, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return c;
    }

}