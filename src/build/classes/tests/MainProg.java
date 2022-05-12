/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;


import com.esprit.models.Categorie;
import com.esprit.models.Produit;
import com.esprit.services.ImCategorie;
import com.esprit.services.ImProduit;
import com.esprit.utils.DataSource;
import static java.sql.JDBCType.NULL;

/**
 *
 * @author
 */
public class MainProg {
    public static void main(String[] args) {
      /*   DataSource bd1=DataSource.getInstance();
         ImCategorie ic = new ImCategorie();
         //ic.ajouter(new Categorie(13,"HEDI T"));
         ic.supprimer(new Categorie(13));
         ic.modifier(new Categorie(12,"I DIDIT"));
         System.out.println(ic.afficher());
         ImProduit pr = new ImProduit();
         pr.ajouter( new Produit("hedi","azerazer",250,"image"));
         pr.supprimer(new Produit(3));
         pr.modifier(new Produit(1,1,"hedi","azerazer",4000,"image"));
           System.out.println( pr.afficher());
        */ Produit p=new Produit();
         ImProduit ps=new ImProduit();
         ps.ajouter(p);
        //ServicePersonne sp = new ServicePersonne();
        //sp.ajouter(new Personne("Med", "Med"));
        //sp.modifier(new Personne(2, "Med", "Yassine"));
        //sp.supprimer(new Personne(2, "Med", "Yassine"));
        //System.out.println(sp.afficher());
        
      
    }
}
