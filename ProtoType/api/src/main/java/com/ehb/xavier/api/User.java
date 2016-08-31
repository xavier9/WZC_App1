package com.ehb.xavier.api;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.Date;

import javax.jdo.annotations.ForeignKey;

/**
 * Created by xavier on 16/11/2014.
 */
@Entity
public class User {
    @Id
    private Long Id;
    private String FirstName;
    private String LastName;
    private String email;
    private int kamernr;
    private Date Geboortedatum;
    private String UserName;
    private String PassWord;
    @ForeignKey(table = "Rol")
    private Long rol;

    public Long getRol() {
        return rol;
    }

    public void setRol(Long rol) {
        this.rol = rol;
    }
    //private Adres adres;

    /**
     * Constructs a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param id the id
     */
    public User(Long id) {
        Id = id;
    }

    /**
     * Instantiates a new User.
     *
     * @param userName the user name
     * @param passWord the pass word
     */
    public User(String userName, String passWord) {
        UserName = userName;
        PassWord = passWord;
    }

    /**
     * Instantiates a new User.
     *
     * @param id        the id
     * @param firstName the first name
     * @param lastName  the last name
     * @param userName  the user name
     * @param passWord  the pass word
     */
    public User(Long id, String firstName, String lastName, String userName, String passWord, Long rol) {
        Id = id;
        FirstName = firstName;
        LastName = lastName;
        UserName = userName;
        PassWord = passWord;
        this.rol = rol;
        //this.adres = adres;
    }

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    public Long getId() {
        return Id;
    }

    /**
     * Setter for property 'id'.
     *
     * @param id Value to set for property 'id'.
     */
    public void setId(Long id) {
        Id = id;
    }

    /**
     * Getter for property 'firstName'.
     *
     * @return Value for property 'firstName'.
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     * Setter for property 'firstName'.
     *
     * @param firstName Value to set for property 'firstName'.
     */
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    /**
     * Getter for property 'lastName'.
     *
     * @return Value for property 'lastName'.
     */
    public String getLastName() {
        return LastName;
    }

    /**
     * Setter for property 'lastName'.
     *
     * @param lastName Value to set for property 'lastName'.
     */
    public void setLastName(String lastName) {
        LastName = lastName;
    }

    /**
     * Getter for property 'userName'.
     *
     * @return Value for property 'userName'.
     */
    public String getUserName() {
        return UserName;
    }

    /**
     * Setter for property 'userName'.
     *
     * @param userName Value to set for property 'userName'.
     */
    public void setUserName(String userName) {
        UserName = userName;
    }

    /**
     * Getter for property 'passWord'.
     *
     * @return Value for property 'passWord'.
     */
    public String getPassWord() {
        return PassWord;
    }

    /**
     * Setter for property 'passWord'.
     *
     * @param passWord Value to set for property 'passWord'.
     */
    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    /**
     * Getter for property 'adres'.
     *
     * @return Value for property 'adres'.
     */
    /*public Adres getAdres() {
        return adres;
    }*/

    /**
     * Setter for property 'adres'.
     *
     * Value to set for property 'adres'.
     */
    /*public void setAdres(Adres adres) {
        this.adres = adres;
    }*/

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", UserName='" + UserName + '\'' +
                ", PassWord='" + PassWord + '\'' +
               // ", adres=" + adres.toString() +
                '}';
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (Id != user.Id) return false;
        if (FirstName != null ? !FirstName.equals(user.FirstName) : user.FirstName != null)
            return false;
        if (LastName != null ? !LastName.equals(user.LastName) : user.LastName != null)
            return false;
        if (PassWord != null ? !PassWord.equals(user.PassWord) : user.PassWord != null)
            return false;
        if (!UserName.equals(user.UserName)) return false;
        //if (adres != null ? !adres.equals(user.adres) : user.adres != null) return false;

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (FirstName != null ? FirstName.hashCode() : 0);
        result = 31 * result + (LastName != null ? LastName.hashCode() : 0);
        result = 31 * result + UserName.hashCode();
        result = 31 * result + (PassWord != null ? PassWord.hashCode() : 0);
        //result = 31 * result + (adres != null ? adres.hashCode() : 0);
        return result;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getKamernr() {
        return kamernr;
    }

    public void setKamernr(int kamernr) {
        this.kamernr = kamernr;
    }

    public Date getGeboortedatum() {
        return Geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        Geboortedatum = geboortedatum;
    }
}
