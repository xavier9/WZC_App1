package com.ehb.xavier.api;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.jdo.annotations.ForeignKey;

/**
 * Created by xavier on 1/12/2014.
 * Contacten in a db
 */
@Entity
public class Contact {
    @Id
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String telnr;
    private String gsm;
    private String skypename;
    @ForeignKey(table = "User")
    private Long UserID;

    /**
     * Instantiates a new Contact.
     */
   public Contact() {
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", telnr='" + telnr + '\'' +
                ", gsm='" + gsm + '\'' +
                ", email'"+ email+'\''+
                '}';
    }

    public Contact(String firstname, String lastname,String email, String telnr, String gsm) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.telnr = telnr;
        this.gsm = gsm;
        this.email = email;
    }

    /**
     * Instantiates a new Contact.
     *
     * @param id the id
     */
    public Contact(Long id) {
        this.id = id;
    }

    /**
     * Instantiates a new Contact.
     *
     * @param gsm the gsm
     * @param id the id
     * @param firstname the firstname
     * @param lastname the lastname
     * @param telnr the telnr
     */
    public Contact(Long id, String firstname, String lastname,String email, String telnr, String gsm) {
        this.gsm = gsm;
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.telnr = telnr;
        this.email = email;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets firstname.
     *
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets firstname.
     *
     * @param firstname the firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Gets lastname.
     *
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets lastname.
     *
     * @param lastname the lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Gets telnr.
     *
     * @return the telnr
     */
    public String getTelnr() {
        return telnr;
    }

    /**
     * Sets telnr.
     *
     * @param telnr the telnr
     */
    public void setTelnr(String telnr) {
        this.telnr = telnr;
    }

    /**
     * Gets gsm.
     *
     * @return the gsm
     */
    public String getGsm() {
        return gsm;
    }

    /**
     * Sets gsm.
     *
     * @param gsm the gsm
     */
    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkypename() {
        return skypename;
    }

    public void setSkypename(String skypename) {
        this.skypename = skypename;
    }

    public Long getUserID() {
        return UserID;
    }

    public void setUserID(Long userID) {
        UserID = userID;
    }
}
