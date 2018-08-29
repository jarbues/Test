import java.net.URISyntaxException;
import java.sql.*;

public class Main {
    
    public static void main(String[] args) throws Exception 
    {
        
        Connection connection = getConnection();
        
        Statement stmt = connection.createStatement();
        
        System.out.println("querying SELECT * FROM tables.tab");
        ResultSet rs = stmt.executeQuery("SELECT * FROM tables.tab");
        ResultSetMetaData rsmd = rs.getMetaData();
        
        // Result formatting
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

        String dbUrl = "jdbc:postgresql://ec2-54-235-242-63.compute-1.amazonaws.com:5432/ddd54jvsgoiq4f1?&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        String uname = "rehtiztyxtozbp";
        String pass = "947fe2c544d3d46fc7532f7efa58a324f4ac1de17d78fd40a8b85b505013f33d";
        try {
        	Class.forName("org.postgresql.Driver");
        	// Connecting to database using credentials
			c = DriverManager.getConnection(dbUrl, uname, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return c;
    }

}