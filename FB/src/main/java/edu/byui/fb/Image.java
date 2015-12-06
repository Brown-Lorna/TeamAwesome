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

    public Image() {

    }

    public Image(String name, InputStream bytes) {
        this.name = name;
        this.bytes = bytes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InputStream getBytes() {
        return bytes;
    }

    public void setBytes(InputStream bytes) {
        this.bytes = bytes;
    }
}
