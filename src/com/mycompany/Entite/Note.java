/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import java.util.Date;



/**
 *
 * @author psn
 */
public class Note {
    
    private int id;
    private int jardinId;
    private String contenu;
    private Date dateN;

    public Note() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJardinId() {
        return jardinId;
    }

    public void setJardinId(int jardinId) {
        this.jardinId = jardinId;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDateN() {
        return dateN;
    }

    public void setDateN(Date dateN) {
        this.dateN = dateN;
    }
    
    public String getDateNString(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        String strDate = dateFormat.format(this.dateN); 
        return strDate;
                
    }
    
    
    
}
