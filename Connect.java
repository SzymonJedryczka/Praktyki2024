import java.sql.*;
import java.util.logging.Logger;


public class Connect {

    private static Logger LOG = Logger.getLogger(Server.class.getName());
    

    public static String getCode(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gry","root","");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `code` FROM `game_library`");
            while(rs.next()){
            return (((rs.getString(1))));
            }
            con.close();
        } catch (Exception e) {
            LOG.info("Nie dziala");
        }
        return "0";
    }

    public static String getTitle(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gry","root","");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `title` FROM `game_library`");
            while(rs.next()){
            return (((rs.getString(1))));
            }
            con.close();
        } catch (Exception e) {
            LOG.info("Nie dziala");
        }
        return "0";
    }

    public static String getDescription(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gry","root","");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `description` FROM `game_library`");
            while(rs.next()){
            return (((rs.getString(1))));
            }
            con.close();
        } catch (Exception e) {
            LOG.info("Nie dziala");
        }
        return "0";
    }

    public static String getRate(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gry","root","");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `rate` FROM `game_library`");
            while(rs.next()){
            return (((rs.getString(1))));
            }
            con.close();
        } catch (Exception e) {
            LOG.info("Nie dziala");
        }
        return "0";
    }

    public static void setGame(String _code, String _title, String _description, Integer _rate){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gry","root","");
            Statement stmt = con.createStatement();
            stmt.executeQuery("INSERT INTO `game_library`(`code`, `title`, `description`, `rate`) VALUES ('" + _code +"','" + _title +"','" + _description +"','" + _rate +"')");
            con.close();
            
        } catch (Exception e) {
            LOG.info("Nie dziala");
        }
    }

}