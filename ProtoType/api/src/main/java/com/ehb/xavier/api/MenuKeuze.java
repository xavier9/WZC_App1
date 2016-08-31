package com.ehb.xavier.api;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.Date;

import javax.jdo.annotations.ForeignKey;

/**
 * Created by xavier on 6/02/2015.
 */
@Entity
public class MenuKeuze {
    @Id
    private Long ID;
    private String OchtendKeuze;
    private String MiddagKeuze;
    private String AvondKeuze;
    private Date Dag;
    @ForeignKey(table = "User")
    private Long UserID;

    public MenuKeuze() {
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getOchtendKeuze() {
        return OchtendKeuze;
    }

    public void setOchtendKeuze(String ochtendKeuze) {
        OchtendKeuze = ochtendKeuze;
    }

    public String getMiddagKeuze() {
        return MiddagKeuze;
    }

    public void setMiddagKeuze(String middagKeuze) {
        MiddagKeuze = middagKeuze;
    }

    public String getAvondKeuze() {
        return AvondKeuze;
    }

    public void setAvondKeuze(String avondKeuze) {
        AvondKeuze = avondKeuze;
    }

    public Date getDag() {
        return Dag;
    }

    public void setDag(Date dag) {
        Dag = dag;
    }

    public Long getUserID() {
        return UserID;
    }

    public void setUserID(Long userID) {
        UserID = userID;
    }
}
