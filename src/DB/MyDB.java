/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author takwa
 */
public class MyDB {

    final String url = "jdbc:mysql://localhost/pidevv";
    final String login = "root";
    final String password = "";
    Connection connexion;
    static MyDB instance;

    private MyDB() {
        try {
            connexion
                    = DriverManager.getConnection(url, login, password);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion à la base de données");
        }
    }
    
    public static  MyDB getInstance(){
        if(instance == null)
            instance = new MyDB();
        return instance;
    }
    
    public Connection getConnection(){
        return connexion;
    }
}
