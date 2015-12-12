/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.byui.fb;

import java.io.InputStream;

/**
 *
 * @author Grant
 */
public class Image {

    private String name;
    private InputStream bytes;
    private int id;

    /**
     * Default constructor
     */
    public Image() {

    }

    /**
     * Constructor
     *    Sets the name and the bytes
     * @param name
     * @param bytes 
     */
    public Image(String name, InputStream bytes) {
        this.name = name;
        this.bytes = bytes;
    }

    /**
     * Constructor
     *    Sets the name, bytes, and the id
     * @param name
     * @param bytes
     * @param id 
     */
    public Image(String name, InputStream bytes, int id) {
        this.name = name;
        this.bytes = bytes;
        this.id = id;
    }
    
    /**
     * id Getter
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * id Setter
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * name Getter
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * name Setter
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * bytes Getter
     * @return 
     */
    public InputStream getBytes() {
        return bytes;
    }

    /**
     * bytes Setter
     * @param bytes 
     */
    public void setBytes(InputStream bytes) {
        this.bytes = bytes;
    }
}
