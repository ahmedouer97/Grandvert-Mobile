/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.demos.charts.BudgetDoughnutChart;
import com.codename1.demos.charts.BudgetPieChart;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.Espace;
import com.mycompany.Service.ServiceEspace;
import com.mycompany.gui.Reserver;


/**
 *
 * @author Ghaith
 */
public class AjoutEspace {
    
    Form f;
    TextField eNom;
    TextField eCapacity;
    TextField eLieu;
    Button btnajout,btnaff,btnstat,reserver;
    
public static boolean isNumeric (String strNum)
{
try
{double d = Double.parseDouble(strNum); }
catch (NumberFormatException | NullPointerException nfe) {
return false;
}
return true;
}

    public AjoutEspace() {
        f = new Form("           Gestion Des Espaces");
        eNom = new TextField("","Nom");
        eCapacity = new TextField("","Capacity");
        eLieu = new TextField("","Lieu");
        btnajout = new Button("Ajouter L'espace");
        btnaff=new Button("Afficher La Liste Des Espaces");
        btnstat=new Button("Etablir Des Statistiques");
        reserver=new Button("Réserver Des Places");
        f.add(eNom);
        f.add(eCapacity);
        f.add(eLieu);
        f.add(btnajout);
        f.add(btnaff);
        f.add(btnstat);
        f.add(reserver);
        
        f.getToolbar().addCommandToLeftBar("Back",null,(evv)->{
        
        HomeForm f =new HomeForm();
                f.getHi();
        });
        
        btnajout.addActionListener((ActionEvent e) -> {
            
               if (eCapacity.getText().isEmpty()) {
              Dialog.show("Information Dialog", "Capacity De L'espace Doit étre Rempli", "OK", "Cancel");  
            }
                 else if (isNumeric(eCapacity.getText())==false) { 
               Dialog.show("Information Dialog", "Capacity Doit étre Numerique", "OK", "Cancel");}
               else if (Integer.parseInt(eCapacity.getText()) < 0) { 
               Dialog.show("Information Dialog", "Capacité De L'espace Doit étre Supérieur ou égale à 0", "OK", "Cancel");}
             
               
                 
            else if (eNom.getText().length() < 3 ) {
              Dialog.show("Information Dialog", "Nom De L'espace Doit étre Supérieur ou égal à 3 caractéres", "OK", "Cancel");  
            }
            else if (eNom.getText().length() > 20 ) {
              Dialog.show("Information Dialog", "Nom De L'espace Doit étre Inférieur ou égal à 20 caractéres", "OK", "Cancel");  
            }
             else if (eLieu.getText().length() < 3 ) {
              Dialog.show("Information Dialog", "Lieu De L'espace Doit étre Supérieur ou égal à 3 caractéres", "OK", "Cancel");  
            }
            else if (eLieu.getText().length() > 20 ) {
              Dialog.show("Information Dialog", "Lieu De L'espace Doit étre Inférieur ou égal à 20 caractéres", "OK", "Cancel");  
            }
               else {
            ServiceEspace ser = new ServiceEspace();
            Espace es = new Espace(0, eNom.getText(), Integer.parseInt(eCapacity.getText() ),eLieu.getText());
            ser.ajoutEspace(es);
            
            Dialog.show("Information Dialog", "L'espace '"+eNom.getText()+"' A été Ajouter Avec Succés ", "OK", "Cancel");
            
       eNom.clear();
       eCapacity.clear();
       eLieu.clear();}
            

        });
        btnaff.addActionListener((e)->{
        AfficheEspace a=new AfficheEspace();
        a.getF().show();
        });
       reserver.addActionListener((e)->{
        Reserver a=new Reserver();
        a.getF().show();
        });  
         btnstat.addActionListener((e)->{
        Form hi = new Form("Statistiques Des Espaces", BoxLayout.y());
        BudgetPieChart b = new BudgetPieChart();
        hi.add(b.execute());
        hi.getToolbar().addCommandToRightBar("Back", null, (ev)->{AjoutEspace h=new AjoutEspace();
          h.getF().show();
          });
        hi.show();
        });
    }
      

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField geteNom() {
        return eNom;
    }

    public void seteNom(TextField eNom) {
        this.eNom = eNom;
    }

    public TextField geteCapacity() {
        return eCapacity;
    }

    public void seteCapacity(TextField eCapacity) {
        this.eCapacity = eCapacity;
    }

    public TextField geteLieu() {
        return eLieu;
    }

    public void seteLieu(TextField eLieu) {
        this.eLieu = eLieu;
    }

    public Button getBtnajout() {
        return btnajout;
    }

    public void setBtnajout(Button btnajout) {
        this.btnajout = btnajout;
    }

    public Button getBtnaff() {
        return btnaff;
    }

    public void setBtnaff(Button btnaff) {
        this.btnaff = btnaff;
    }

 
    
}
