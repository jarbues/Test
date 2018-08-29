import java.net.URI;
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
    
    private static Connection getConnection() throws URISyntaxException, SQLException 
    {
    	Connection c = null;

    	URI dbUri = new URI(System.getenv("DATABASE_URL"));
    	String uname = dbUri.getUserInfo().split(":")[0];
        String pass = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
        
        //Class.forName("org.postgresql.Driver");
        // Connecting to database using credentials
        c = DriverManager.getConnection(dbUrl, uname, pass);
        
		return c;
    }
}