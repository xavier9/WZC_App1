package com.ehb.xavier.api;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.Date;

/**
 * Created by xavier on 8/02/2015.
 */
@Entity
public class MenuInsert {
    @Id
    private Long ID;
    private String OchtenKeuze;
    private String MiddagKeuze;
    private String AvondKeuze;
    private Date Dagkeuze;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getOchtenKeuze() {
        return OchtenKeuze;
    }

    public void setOchtenKeuze(String ochtenKeuze) {
        OchtenKeuze = ochtenKeuze;
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

    public Date getDagkeuze() {
        return Dagkeuze;
    }

    public void setDagkeuze(Date dagkeuze) {
        Dagkeuze = dagkeuze;
    }
}
