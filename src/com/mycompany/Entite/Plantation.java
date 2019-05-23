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
public class Plantation {
    
    private int id;
    private int plante;
    private int jardin;
    private Date dateP;
    private String typeP;
    private int qt;

    public Plantation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlante() {
        return plante;
    }

    public void setPlante(int plante) {
        this.plante = plante;
    }

    public int getJardin() {
        return jardin;
    }

    public void setJardin(int jardin) {
        this.jardin = jardin;
    }

    public Date getDateP() {
        return dateP;
    }

    public void setDateP(Date dateP) {
        this.dateP = dateP;
    }

    public String getTypeP() {
        return typeP;
    }

    public void setTypeP(String typeP) {
        this.typeP = typeP;
    }

    public int getQt() {
        return qt;
    }

    public void setQt(int qt) {
        this.qt = qt;
    }
    
    public String getDatePString(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
        String strDate = dateFormat.format(this.dateP); 
        return strDate;
                
    }
    
}
