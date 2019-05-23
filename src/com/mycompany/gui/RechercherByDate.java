/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.Entite.Reservation;
import com.mycompany.Service.ServiceReservation;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.DatePicker;

/**
 *
 * @author Ghaith
 */
public class RechercherByDate {
    Form f;
    DatePicker dateDebut;
    DatePicker dateFin;
    Label l;
    Label l2;
    Button btnAffiche;
    
    public RechercherByDate () {
        
        
        
           Container c = new Container(BoxLayout.y());
        Container c1 = new Container(BoxLayout.x()); 
        Container c2 = new Container(BoxLayout.x()); 
        
         btnAffiche=new Button("Res Par Période");
         ServiceReservation serviceRes=new ServiceReservation();
         f = new Form("Réservations Par Période");
         Picker dateDebut = new Picker();
         Picker dateFin = new Picker();
         l = new Label ("Date Debut : ");
         l2 = new Label ("Date Fin : ");
         
        c1.add(l);
        c1.add(dateDebut);
        c2.add(l2);
        c2.add(dateFin);
        c.add(c1);
        c.add(c2);
         f.add(c);
         f.add(btnAffiche);
         
         btnAffiche.addActionListener((ActionEvent e)->{
             System.out.println((Date)dateDebut.getDate());
              System.out.println((Date)dateFin.getDate());
              
              DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
             String strDtDebut = dateFormat.format(dateDebut.getDate()); 
             String strDtFin = dateFormat.format(dateFin.getDate()); 
            
        
        for (Reservation reservation : serviceRes.getList4(strDtDebut,strDtFin))
        
        {
         // Container c = new Container(BoxLayout.y());   
           
          MultiButton twoLinesNoIcon = new MultiButton("Numéro De Réservation :  "+reservation.getId());
     //     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
          String strDate = dateFormat.format(reservation.getDateDebut()); 
          String strDateFin = dateFormat.format(reservation.getDateFin());
          twoLinesNoIcon.setTextLine2("Nombre De Places Réservées : "+String.valueOf(reservation.getNbPlaces()));
          twoLinesNoIcon.setTextLine3("Date De Début De Réservation : "+strDate);
          twoLinesNoIcon.setTextLine4("Date De La Fin De Réservation : "+strDateFin);
         f.add(twoLinesNoIcon);
         f.refreshTheme();
        
        }
        
        });
         
          f.getToolbar().addCommandToLeftBar("Back   ", null, (ev)->{AfficheReservation h=new AfficheReservation();
          h.getF().show();
          });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public DatePicker getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(DatePicker dateDebut) {
        this.dateDebut = dateDebut;
    }

    public DatePicker getDateFin() {
        return dateFin;
    }

    public void setDateFin(DatePicker dateFin) {
        this.dateFin = dateFin;
    }

    public Label getL() {
        return l;
    }

    public void setL(Label l) {
        this.l = l;
    }

    public Label getL2() {
        return l2;
    }

    public void setL2(Label l2) {
        this.l2 = l2;
    }

    public Button getBtnAffiche() {
        return btnAffiche;
    }

    public void setBtnAffiche(Button btnAffiche) {
        this.btnAffiche = btnAffiche;
    }
    
}
