/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.Entite.Espace;

import com.mycompany.Entite.Reservation;
import com.mycompany.Service.ServiceEspace;

import com.mycompany.Service.ServiceReservation;
import com.mycompany.gui.ChercherResEsp;
import com.mycompany.gui.RechercherByDate;
import com.mycompany.gui.Reserver;
import com.mycompany.gui.TraiterResExp;
import static com.mycompany.gui.AjoutEspace.isNumeric;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author Ghaith
 */
public class AfficheReservation {
    Form f;
    Form f2;
    ComboBox<Espace> comboEspace = new ComboBox<Espace>();
   // SpanLabel lb;
    
       public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
    
     public AfficheReservation() {
        
        f = new Form("Liste Des Réservation");
       // lb = new SpanLabel("");
      //  f.add(lb);
        ServiceReservation serviceReservation=new ServiceReservation();
      //  lb.setText(serviceEspace.getList2().toString());
        
        for (Reservation reservation : serviceReservation.getList2())
        
        {
          Container c = new Container(BoxLayout.y());   
           
          MultiButton twoLinesNoIcon = new MultiButton("Numéro De Réservation :  "+reservation.getId());
          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
          String strDate = dateFormat.format(reservation.getDateDebut()); 
          String strDateFin = dateFormat.format(reservation.getDateFin());
          twoLinesNoIcon.setTextLine2("Nombre De Places Réservées : "+String.valueOf(reservation.getNbPlaces()));
          twoLinesNoIcon.setTextLine3("Date De Début De Réservation : "+strDate);
          twoLinesNoIcon.setTextLine4("Date De La Fin De Réservation : "+strDateFin);
          Button supprimer = new Button ("Annuler La Réservation");
          Button modifier = new Button ("Modifier La Réservation");
          c.add(twoLinesNoIcon);
          c.add(supprimer);
          c.add(modifier);
          
          
               supprimer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ee) {
                    
                    if (Dialog.show("Warning Dialog", "Vous Voulez Vraiment Supprimer La Réservation Numéro '"+reservation.getId()+"'", "OK", "Cancel"))
                    {
                    ServiceReservation ser = new ServiceReservation();
                     ser.annulerReservation(reservation.getId());
                     AfficheReservation h=new AfficheReservation();
                     h.getF().show();
                     
                 //  f.refreshTheme();
               //  f.refreshTheme(true);
                }}
            });
               
                       modifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ee) {
                 //   f.setHidden(true);
                     f2 = new Form("Modification Des Rés");
                       f2.getToolbar().addCommandToRightBar("Back", null, (ev)->{AfficheReservation h=new AfficheReservation();
          h.getF().show();
          });
 Container c = new Container(BoxLayout.y());
        Container c1 = new Container(BoxLayout.x());
        Container c2 = new Container(BoxLayout.x());
      //  f = new Form("Reserver Vos Places");

        Picker dateDebut = new Picker();
        Picker dateFin = new Picker();
        Button b = new Button("Modifier La Réservation");

        ServiceEspace serviceEspace = new ServiceEspace();
        for (Espace espace : serviceEspace.getList2()) {
            comboEspace.addItem(espace);
        }
     Label       l = new Label("Date Debut : ");
     Label       l2 = new Label("Date Fin : ");
     TextField   NbPlaces = new TextField("", "Nb Places");
       

        c1.add(l);
        c1.add(dateDebut);
        c2.add(l2);
        c2.add(dateFin);
        c.add(c1);
        c.add(c2);
        c.add(NbPlaces);
        c.add(comboEspace);
        c.add(b);
        f2.add(c);
        f2.show();
      
   //   eNom.setText(espace.getNom());
    //  eCapacity.setText(String.valueOf(espace.getCapacity()));
    //  eLieu.setText(espace.getLieu());
               
              b.addActionListener((e)->{
                  
                 if (NbPlaces.getText().isEmpty()) {
                Dialog.show("Information Dialog", "Nb Places Doit étre Rempli", "OK", "Cancel");
            }
                   else   if (isNumeric(NbPlaces.getText()) == false) {
                Dialog.show("Information Dialog", "Nb Places Doit étre Numérique", "OK", "Cancel");

            }
                  
                     else {

            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            Date today = new Date();
            String dateu = formater.format(today);
            String date_deb = formater.format(dateDebut.getDate());
            String date_fin = formater.format(dateFin.getDate());

            int cap = comboEspace.getSelectedItem().getCapacity();
            int nbpla = Integer.parseInt(NbPlaces.getText());
            

           //   System.out.println(comboEspace.getSelectedItem().getId()); 
             if (dateu.compareTo(date_deb) > 0) {
                Dialog.show("Information Dialog", "Date Debut Doit étre Supérieure à la date D'aujourd'hui", "OK", "Cancel");
            } else if (date_deb.compareTo(date_fin) > 0) {
                Dialog.show("Information Dialog", "Date Fin Doit étre Supérieure à la date de début", "OK", "Cancel");

            } else if (Integer.parseInt(NbPlaces.getText()) < 1) {
                Dialog.show("Information Dialog", "Nb Places Doit étre Supérieur à 1", "OK", "Cancel");
            } else if (nbpla > cap) {

                Dialog.show("Information Dialog", "Vous Avez Dépassé La Capacity De L'espace", "OK", "Cancel");
            }
                  
             
            else {
      
           int esp = comboEspace.getSelectedItem().getId();
          //  Reservation r = new Reservation ();
           int   id = reservation.getId();
                  Reservation r = new Reservation(id, (Date) dateDebut.getDate(), (Date) dateFin.getDate(), Integer.parseInt(NbPlaces.getText()), esp);
               // ser.ajoutReservation(r);
                   
              
                    
                   
                    ServiceReservation ser = new ServiceReservation();
                    ser.modifierReservation(r);
            
          Dialog.show("Information Dialog", "La Réservation Numéro '"+reservation.getId()+"' A été Modifier Avec Succés ", "OK", "Cancel");
     
      NbPlaces.clear();}
     
                   
                     
                  /*   AfficheReservation h=new AfficheReservation();
                     h.getF().show();
             */}
                     
                    });}
            });
          
          f.add(c);
        }
        
          f.getToolbar().addCommandToLeftBar("Back     ", null, (ev)->{Reserver h=new Reserver();
          h.getF().show();
          });
         
            f.getToolbar().addCommandToOverflowMenu("Recherche par espace   ", null, (ev)->{ChercherResEsp h=new ChercherResEsp();
          h.getF().show();
          });
            
              f.getToolbar().addCommandToOverflowMenu("Recherche par période   ", null, (ev)->{RechercherByDate h=new RechercherByDate();
          h.getF().show();
          });
              
                f.getToolbar().addCommandToOverflowMenu("Traiter les réservations expirés   ", null, (ev)->{TraiterResExp h=new TraiterResExp();
          h.getF().show();
          });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

   /* public SpanLabel getLb() {
        return lb;
    }


    public void setLb(SpanLabel lb) {
        this.lb = lb;
    }
     */
     
    
}
