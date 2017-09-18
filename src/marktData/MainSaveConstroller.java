package marktData;

import JSON.JSONArray;
import global.Mysql;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Deze methoden is om de controller welke data er op geslagen / geupdate moet
 * worden
 *
 * @author michel
 */
public class MainSaveConstroller {

    Mysql mysql = new Mysql();

    //array met de data
    private String[] exchangeArray;

    //JSONArray waarvan de key de exchange is en 
    //maak alle exchange objecten aan
    Bitstamp bitstamp = new Bitstamp();

    /**
     * Constrcutor
     *
     * @throws java.lang.Exception exception error
     */
    public MainSaveConstroller() throws Exception {

        //roep de methoden op die de array vult
        fillExchangeArray();

    }

    /**
     * Methoden die door de constructor word gebruikt om de exchangeArray de
     * vullen
     *
     * @throws Exception error exception
     */
    private void fillExchangeArray() throws Exception {

        //arary nummer
        int index = 0;
        int arraySize = 0;

        //sqlSelect
        String sqlSelect = "SELECT handelsplaatsNaam FROM marktlijstvolv1 "
                + "WHERE saveMarktData = 'true' GROUP BY handelsplaatsNaam;";

        ResultSet rs = mysql.mysqlSelect(sqlSelect);
        while (rs.next()) {
            //add ++ bij array size
            arraySize++;
        }

        //geef de array grote aan
        exchangeArray = new String[arraySize];

        //loop nog eens door de data heen om er voor te zorgen dat de araat word gevuld
        while (rs.next()) {
            //vul de array index is de positie van de array
            exchangeArray[index] = rs.getString("handelsplaatsNaam");

            //maak index 1 hoger
            index++;
        }
    }

    /**
     * Return de lijst van markten op de exchange niet opgeslagen moet worden
     * @param exchangeNaam exchange naam
     * @return de markt array
     */
    private JSONArray maakMarktLijst(String exchangeNaam) throws SQLException {
        
        JSONArray marktLijstArray = new JSONArray();

        //kijk of er iets op true staat in de lijst
        String selectSql = "SELECT marktnaamDB, naamMarkt "
                + "FROM marktactief "
                + "WHERE saveMarktData = 'true' "
                + "AND handelsplaatsNaam='" + exchangeNaam + "';";

        //vraag het op uit het database
        ResultSet rs = mysql.mysqlSelect(selectSql);

        //loop door de response heen
        while (rs.next()) {
            
            int indexNummer = 0;
            
            //maak de array aan
            String[] array = new String[2];
            
            //add marktNaamDB in de array
            array[indexNummer] = rs.getString("marktnaamDB");
            
            //verhoog het nummer met 1
            indexNummer++;
            
            //vul de markt naam
            array[indexNummer] = rs.getString("naamMarkt");
        }
        
        //return de array
        return marktLijstArray;
    }

}
