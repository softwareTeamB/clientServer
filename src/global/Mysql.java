package global;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.StampedLock;

/**
 *
 * @author michel
 */
public class Mysql {

    //variable
    private final String USERNAME = "root";
    private final String PASSWORD = "Pulsar11";
    private final String IPADDRESS = "localhost";
    private final String POORT = "3306";
    private final String DATABASENAAM = "clientproject";
    private final boolean AUTORECONNECT = true;
    private final boolean SSL = false;
    private final String CONN_STRING = "jdbc:mysql://" + IPADDRESS + ":" + POORT + "/" + DATABASENAAM + "?autoReconnect=" + AUTORECONNECT + "&useSSL=" + SSL;

    /**
     * Select stament
     *
     * @param sqlString mysql string
     * @return return de velden
     * @throws SQLException als er iets fout gaat
     */
    public ResultSet mysqlSelect(String sqlString) throws SQLException {

        Connection conn;
        conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
        Statement stmt = (Statement) conn.createStatement();

        //return
        return stmt.executeQuery(sqlString);
    }

    /**
     * Run sql staments zonder return
     *
     * @param sqlString sql stament
     * @throws SQLException als er iets fout gaat
     */
    public void mysqlExecute(String sqlString) throws SQLException {

        Connection conn;
        conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
        Statement stmt = (Statement) conn.createStatement();

        //run stament
        stmt.execute(sqlString);
    }

    public int mysqlCount(String sqlString) throws SQLException, Exception {

        //maak contact
        Connection conn;
        conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
        Statement stmt = (Statement) conn.createStatement();

        //return
        ResultSet rs = stmt.executeQuery(sqlString);
        while (rs.next()) {
            return rs.getInt("total");
        }

        //als er een error optreed
        throw new Exception("Mysql count error. Mogelijk geen connectie of er is geen geldig colum ingevuld.");
    }

    /**
     * Return nummer wat er is
     *
     * @param sqlString nummer
     * @return return 1 nummer
     * @throws SQLException sql error
     * @throws Exception andere exceptions
     */
    public int mysqlExchangeNummer(String sqlString) throws SQLException, Exception {

        //maak contact
        Connection conn;
        conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
        Statement stmt = (Statement) conn.createStatement();

        //return
        ResultSet rs = stmt.executeQuery(sqlString);
        while (rs.next()) {
            return rs.getInt("nummer");
        }

        //als er een error optreed
        throw new Exception("Mysql kan geen nummer return geven");
    }

    /**
     * Group counter is een methoden die het result van group by telt
     * @param sqlString sql string met group by 
     * @return de index waarde
     * @throws SQLException sql error exceptie
     */
    public int mysqlGroupCounter(String sqlString) throws SQLException {

        //index geld als counter in dit geval want iedere loop wordt met 1 verhoogt
        int index= 0;
        
        //vraag de data op
        ResultSet rs = mysqlSelect(sqlString);

        //loop er door heen
        while (rs.next()) {
            
            //tel het er bij op
            index++;
        }
        
        //return de index waarde
        return index;
    }
}
