package marktData;

import global.Mysql;
import http.Http;

/**
 * Bitstamp api om alle markt data op te vragen
 * @author michel
 */
public class Bitstamp extends MainServer{
    
    Mysql mysql = new Mysql();
    Http http = new Http();
    
    //array
    private String[] marktArray;
    private final String EXCHANGE_NAAM;

    /**
     * Constructor
     */
    public Bitstamp() {
        
        //this stament
        this.EXCHANGE_NAAM = "bitstamp";
        
        
        
        
        
        //vraag de markten op van de super klassen
        getData();
        
        
        
        
    }
    
    
   
    @Override
    public void getData() {
        
        String sqlSelect = "";
        
        
        
        
    }
}
