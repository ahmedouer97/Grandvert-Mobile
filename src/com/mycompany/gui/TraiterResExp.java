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
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.Reservation;
import com.mycompany.Service.ServiceReservation;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Ghaith
 */
public class TraiterResExp {
     Form f;
     public TraiterResExp () {
             f = new Form("Res Expirés Traités");
              f.getToolbar().addCommandToLeftBar("Back        ", null, (ev)->{AfficheReservation h=new AfficheReservation();
          h.getF().show();
          });
             ServiceReservation serviceReservation=new ServiceReservation();
      //  lb.setText(serviceEspace.getList2().toString());
        
        for (Reservation reservation : serviceReservation.getList5())
        
        {
          Container c = new Container(BoxLayout.y());   
           
          MultiButton twoLinesNoIcon = new MultiButton("Numéro De Réservation :  "+reservation.getId());
          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
          String strDate = dateFormat.format(reservation.getDateDebut()); 
          String strDateFin = dateFormat.format(reservation.getDateFin());
          twoLinesNoIcon.setTextLine2("Nombre De Places Réservées : "+String.valueOf(reservation.getNbPlaces()));
          twoLinesNoIcon.setTextLine3("Date De Début De Réservation : "+strDate);
          twoLinesNoIcon.setTextLine4("Date De La Fin De Réservation : "+strDateFin);
         
          c.add(twoLinesNoIcon);
        
          f.add(c);
        }
             
     }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
