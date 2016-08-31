package com.ehb.xavier.api;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
/**
 * Created by xavier on 25/11/2014.
*/
@Entity
public class Rol {
    @Id
    private Long id;
    private String naam;
    private int menu;

    public int getMenu() {
        return menu;
    }

    public void setMenu(int menu) {
        this.menu = menu;
    }


    //private ArrayList<Inlogmenu> inlogmenus;

    public Rol() {
    }

    public Rol(Long id) {
        this.id = id;
    }

    public Rol(Long id, String naam, int menu) {
        this.id = id;
        this.naam = naam;
        this.menu = menu;
    }

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for property 'id'.
     *
     * @param id Value to set for property 'id'.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for property 'naam'.
     *
     * @return Value for property 'naam'.
     */
    public String getNaam() {
        return naam;
    }

    /**
     * Setter for property 'naam'.
     *
     * @param naam Value to set for property 'naam'.
     */
    public void setNaam(String naam) {
        this.naam = naam;
    }

    /**
     * Getter for property 'inlogmenus'.
     *
     * @return Value for property 'inlogmenus'.
     */
    //public ArrayList<Inlogmenu> getInlogmenus() {
     //   return inlogmenus;
    //}

    /**
     * Setter for property 'inlogmenus'.
     *
     * //@param inlogmenus Value to set for property 'inlogmenus'.
     */
    //public void setInlogmenus(ArrayList<Inlogmenu> inlogmenus) {
       // this.inlogmenus = inlogmenus;
   // }

    @Override
    public String toString() {
        return "Rol{" +
                "id=" + id +
                ", naam='" + naam + '\'' +
               // ", inlogmenus=" + inlogmenus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rol)) return false;

        Rol rol = (Rol) o;

        if (id != rol.id) return false;
        if (naam != null ? !naam.equals(rol.naam) : rol.naam != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (naam != null ? naam.hashCode() : 0);
        return result;
    }
}
