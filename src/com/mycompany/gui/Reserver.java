/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.Entite.Espace;
import com.mycompany.Entite.Reservation;
import com.mycompany.Service.ServiceEspace;
import com.mycompany.Service.ServiceReservation;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.DatePicker;

/**
 *
 * @author Ghaith
 */
public class Reserver {

    Form f;
    DatePicker dateDebut;
    DatePicker dateFin;
    Label l;
    Label l2;
    TextField NbPlaces;
    ComboBox<Espace> comboEspace = new ComboBox<Espace>();
    Button bt_ajout, bt_affiche;

    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public Reserver() {

        Container c = new Container(BoxLayout.y());
        Container c1 = new Container(BoxLayout.x());
        Container c2 = new Container(BoxLayout.x());
        f = new Form("Reserver Vos Places");

        Picker dateDebut = new Picker();
        Picker dateFin = new Picker();

        ServiceEspace serviceEspace = new ServiceEspace();
        for (Espace espace : serviceEspace.getList2()) {
            comboEspace.addItem(espace);
        }
        l = new Label("Date Debut : ");
        l2 = new Label("Date Fin : ");
        NbPlaces = new TextField("", "Nb Places");
        bt_ajout = new Button("Réserver");
        bt_affiche = new Button("Afficher La Liste Des Réservation");

        c1.add(l);
        c1.add(dateDebut);
        c2.add(l2);
        c2.add(dateFin);
        c.add(c1);
        c.add(c2);
        c.add(NbPlaces);
        c.add(comboEspace);
        c.add(bt_ajout);
        c.add(bt_affiche);
        f.add(c);
        // dateDebut.getDate().compareTo(new Date());
        // f.add(c2);

        bt_ajout.addActionListener((ActionEvent e) -> {
            
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
            } /*            if (dateDebut.getDate().before(dateFin.getDate())) {
           
            
            
        Dialog.show("Error Dialog", "La date de la debut de réservation doit étre supérieure à la date du fin", "OK", "Cancel");

        }
             */ // if (dateDebut.getValue().hashCode()){
            //   System.out.println(dateDebut.getValue().hashCode());
            //} 
            //  System.out.println(dateDebut.getDate()>(dateFin.getDate()));
            // }
            else {

                int esp = comboEspace.getSelectedItem().getId();

                /*     else if (isNumeric(NbPlaces.getText())==false) { 
               Dialog.show("Information Dialog", "Capacity Doit étre Numerique", "OK", "Cancel");}
            
                 */
                ServiceReservation ser = new ServiceReservation();
                Reservation r = new Reservation(0, (Date) dateDebut.getDate(), (Date) dateFin.getDate(), Integer.parseInt(NbPlaces.getText()), esp);
                ser.ajoutReservation(r);
                Dialog.show("Information Dialog", "Votre Réservation est prise en considération", "OK", "Cancel");
                NbPlaces.clear();
            }}
        }
        );

        bt_affiche.addActionListener((e) -> {
            AfficheReservation a = new AfficheReservation();
            a.getF().show();
        });

        f.getToolbar().addCommandToRightBar("Back", null, (ev) -> {
            AjoutEspace h = new AjoutEspace();
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

    public TextField getNbPlaces() {
        return NbPlaces;
    }

    public void setNbPlaces(TextField NbPlaces) {
        this.NbPlaces = NbPlaces;
    }

    public Button getBt_ajout() {
        return bt_ajout;
    }

    public void setBt_ajout(Button bt_ajout) {
        this.bt_ajout = bt_ajout;
    }

    public Button getBt_affiche() {
        return bt_affiche;
    }

    public void setBt_affiche(Button bt_affiche) {
        this.bt_affiche = bt_affiche;
    }

}
