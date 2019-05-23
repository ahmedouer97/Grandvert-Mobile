/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.Espace;
import com.mycompany.Service.ServiceEspace;
import com.mycompany.gui.AjoutEspace;
import static com.mycompany.gui.AjoutEspace.isNumeric;


/**
 *
 * @author Ghaith
 */
public class AfficheEspace {
    Form f;
    Form f2;
   // SpanLabel lb;
    
     public AfficheEspace() {
        
        f = new Form("Liste Des Espaces");
       
       // lb = new SpanLabel("");
      //  f.add(lb);
        ServiceEspace serviceEspace=new ServiceEspace();
      //  lb.setText(serviceEspace.getList2().toString());
        
        for (Espace espace : serviceEspace.getList2())
        
        {
          Container c = new Container(BoxLayout.y());   
           
          MultiButton twoLinesNoIcon = new MultiButton("Nom De L'Espace :  "+espace.getNom());
          twoLinesNoIcon.setTextLine3("Capacity De L'espace : "+Integer.toString(espace.getCapacity()));
          twoLinesNoIcon.setTextLine2("Lieu De L'espace : "+espace.getLieu());
          Button supprimer = new Button ("Supprimer L'espace");
          Button modifier = new Button ("Modifier L'espace");
          c.add(twoLinesNoIcon);
          c.add(supprimer);
          c.add(modifier);
          
               supprimer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ee) {
                    
                    if (Dialog.show("Warning Dialog", "Vous Voulez Vraiment Supprimer L'Espace '"+espace.getNom()+"'", "OK", "Cancel"))
                    {
                    ServiceEspace ser = new ServiceEspace();
                     ser.supprimerEspace(espace.getId());
                     AfficheEspace h=new AfficheEspace();
                     h.getF().show();
                }}
            });
               
                 modifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ee) {
                 //   f.setHidden(true);
                     f2 = new Form("Modification Des Caractérestiques");
      TextField  eNom = new TextField("","Nom");
      TextField  eCapacity = new TextField("","Capacity");
      TextField  eLieu = new TextField("","Lieu");
      Button b = new Button("Modifier L'espace");
      eNom.setText(espace.getNom());
      eCapacity.setText(String.valueOf(espace.getCapacity()));
      eLieu.setText(espace.getLieu());
                    f2.add(eNom);
                    f2.add(eCapacity);
                    f2.add(eLieu);
                    f2.add(b);
                    f2.show();
              b.addActionListener((e)->{
                  
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
             Espace s = new Espace ();
                  
                    int   id = espace.getId();
                    s.setNom(eNom.getText());
                    s.setCapacity(Integer.parseInt(eCapacity.getText()));
                    s.setLieu(eLieu.getText());
                    
                    s.setId(id);
                    
                   
                    ServiceEspace ser = new ServiceEspace();
                     ser.modifierEspace(s);
            
            Dialog.show("Information Dialog", "L'espace '"+eNom.getText()+"' A été Modifier Avec Succés ", "OK", "Cancel");
            
       eNom.clear();
       eCapacity.clear();
       eLieu.clear();}
                 
                     
                     AfficheEspace h=new AfficheEspace();
                     h.getF().show();
                     
                    });}
            });
          
          f.add(c);
        }
        
          f.getToolbar().addCommandToRightBar("Back", null, (ev)->{AjoutEspace h=new AjoutEspace();
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
