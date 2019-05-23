/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.Reservation;
import com.mycompany.Service.ServiceReservation;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Ghaith
 */
public class ChercherResEsp {
    
        Form f;
       // Label l;
        TextField espaceNom;
        Button btnAffiche;
        
        public ChercherResEsp () {
            
         

      //  l = new Label ("Espace : ");
        espaceNom = new TextField("","Espace Lieu");
        btnAffiche=new Button("Réservations Associées");
         ServiceReservation serviceRes=new ServiceReservation();
         
            f = new Form("Réservations Associées");
     
        ServiceReservation serviceReservation=new ServiceReservation();
     
      f.add(espaceNom);
      f.add(btnAffiche);
      btnAffiche.addActionListener((e)->{
       //   f = new Form("Liste Des Réservation Associée");
        
        for (Reservation reservation : serviceReservation.getList3(espaceNom.getText()))
        
        {
         // Container c = new Container(BoxLayout.y());   
           
          MultiButton twoLinesNoIcon = new MultiButton("Numéro De Réservation :  "+reservation.getId());
          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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

 

    public TextField getEspaceNom() {
        return espaceNom;
    }

    public void setEspaceNom(TextField espaceNom) {
        this.espaceNom = espaceNom;
    }

    public Button getBtnAffiche() {
        return btnAffiche;
    }

    public void setBtnAffiche(Button btnAffiche) {
        this.btnAffiche = btnAffiche;
    }
    
}
