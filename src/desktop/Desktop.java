/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop;

import Services.ReclamationService;
import entities.Reclamation;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author asus
 */
public class Desktop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        ReclamationService ps = new ReclamationService();
        Reclamation r1 = new Reclamation("okok","bonne",Date.valueOf("2022-02-02"),"traité"); // TODO code application logic here
        //ps.ajoutReclamation(r1);
        System.out.println(ps.listerReclamation());
     //  ps.updateReclamation(r1,10);
       // System.out.println("Reclamation ajouté");
        // System.out.println(ps.ajoutReclamation().toString());
    }
    
}
