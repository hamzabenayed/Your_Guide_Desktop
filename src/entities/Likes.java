/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author asus
 */
public class Likes {
    private int id ;
    private int com;
    private String nom_like;
    private int rate,note;

    public Likes() {
    }

    public Likes(String nom_like, int note, int rate, int com) {
        this.com = com;
        this.nom_like = nom_like;
        this.rate = rate;
        this.note = note;
    }
   
    

    public Likes(int id, int com, String nom_like, int rate, int note) {
        this.id = id;
        this.com = com;
        this.nom_like = nom_like;
        this.rate = rate;
        this.note = note;
    }

    public Likes(String text, String text0, String text1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCom() {
        return com;
    }

    public void setCom(int com) {
        this.com = com;
    }

    public String getNom_like() {
        return nom_like;
    }

    public void setNom_like(String nom_like) {
        this.nom_like = nom_like;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Likes{" + "id=" + id + ", com=" + com + ", nom_like=" + nom_like + ", rate=" + rate + ", note=" + note + '}';
    }
    
    
}
