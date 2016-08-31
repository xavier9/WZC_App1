package com.ehb.xavier.prototype.Contact;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.util.Arrays;

/**
 * Created by xavier on 5/01/2015.
 */
public class Image {

    @DatabaseField(id = true)
    private int id;
    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    byte[] image;
    @DatabaseField
    private String beschrijving;

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", image=" + Arrays.toString(image) +'\'' +
                ", beschrijving='" + beschrijving + '\'' +
                '}';
    }

    public Image() {
    }

    public Image(int id) {
        this.id = id;
    }

    public Image(byte[] image, String beschrijving) {
        this.image = image;
        this.beschrijving = beschrijving;
    }

    public Image(int id, byte[] image, String beschrijving) {
        this.id = id;
        this.image = image;
        this.beschrijving = beschrijving;
    }

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for property 'id'.
     *
     * @param id Value to set for property 'id'.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for property 'image'.
     *
     * @return Value for property 'image'.
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Setter for property 'image'.
     *
     * @param image Value to set for property 'image'.
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * Getter for property 'beschrijving'.
     *
     * @return Value for property 'beschrijving'.
     */
    public String getBeschrijving() {
        return beschrijving;
    }

    /**
     * Setter for property 'beschrijving'.
     *
     * @param beschrijving Value to set for property 'beschrijving'.
     */
    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }
}
