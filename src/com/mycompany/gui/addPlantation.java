/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.Entite.Plantation;
import com.mycompany.Service.PlantationService;
import java.util.Date;

/**
 *
 * @author psn
 */
public class addPlantation {
    
    Form f;
    TextField txtType;
    TextField txtQt;
    Button btnajout;
    
    public addPlantation(int jardinId, Date date){
        f = new Form("Add Plantation");
        Container cnt = new Container(new BorderLayout());
        txtType = new TextField("");
        txtType.setWidth(200);
        btnajout = new Button("Ajouter Note");
        cnt.add(BorderLayout.CENTER, txtType);
        cnt.add(BorderLayout.SOUTH, btnajout);
        f.add(cnt);
        
        
        
        btnajout.addActionListener((e) -> {
            PlantationService pService = new PlantationService();
            Plantation p = new Plantation();
            p.setTypeP(txtType.getText());
            p.setDateP(date);
            p.setJardin(jardinId);
            pService.addPlantation(p);
        });
        
        f.getToolbar().addCommandToRightBar("Retour", null, (ev)->{Jardin h=new Jardin(jardinId);
          h.getF().show();
          });
    }
    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTcont() {
        return txtType;
    }

    public void setTcont(TextField ta) {
        this.txtType = ta;
    }
}
