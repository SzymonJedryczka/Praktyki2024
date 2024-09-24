
//W tym pliku mam łączenie z bazą danych (wymagane dodanie pliku mysql-connector-java-5.0.8.jar do kataligu libs)

import java.sql.*;
import java.util.logging.Logger;


public class Connect {

    private static Logger LOG = Logger.getLogger(Server.class.getName());
    
    public static Integer table_length(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gry","root","");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM `game_library`;");
            while(rs.next()){
            return (rs.getInt(1));
            }
            con.close();
        } catch (Exception e) {
            LOG.info("Nie dziala");
        }
        return 2;
    }

    public static String getCode(Integer i){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gry","root","");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT code FROM `game_library`WHERE game_id = " + i);
            while(rs.next()){
            return (((rs.getString(1))));
            }
            con.close();
        } catch (Exception e) {
            LOG.info("Nie dziala");
        }
        return "0";
    }

    public static String getTitle(Integer i){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gry","root","");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT title FROM `game_library`WHERE game_id = " + i);
            while(rs.next()){
            return (((rs.getString(1))));
            }
            con.close();
        } catch (Exception e) {
            LOG.info("Nie dziala");
        }
        return "0";
    }

    public static String getDescription(Integer i){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gry","root","");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT description FROM `game_library`WHERE game_id = " + i);
            while(rs.next()){
            return (((rs.getString(1))));
            }
            con.close();
        } catch (Exception e) {
            LOG.info("Nie dziala");
        }
        return "0";
    }

    public static String getRate(Integer i){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gry","root","");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT rate FROM `game_library`WHERE game_id = " + i);
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
