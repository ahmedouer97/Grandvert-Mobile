/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.Service.NoteService;
import com.mycompany.Service.PlantationService;
import java.util.Date;

/**
 *
 * @author psn
 */
public class Jardin {
    
    NoteService nService = new NoteService();
    PlantationService pService = new PlantationService();
    
    int jardinId;
    Form f;
    Picker datePicker;
    
    public Jardin(int jardinId){
        
        this.jardinId=jardinId;
        
        f = new Form();
        
        datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setDate(new Date());
        
        Container mainContainer = new Container(new BorderLayout());
        Container dayContainer = new Container(new GridLayout(2,2));
        
        Button addNoteBtn = new Button("Ajouter Note");
        Button addPlantBtn = new Button("Ajouter Plantation");
        
        Container notesContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container plantContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        dayContainer.add(addNoteBtn);
        dayContainer.add(addPlantBtn);
        dayContainer.add(notesContainer);
        dayContainer.add(plantContainer);
        
        mainContainer.add(BorderLayout.NORTH, datePicker);
        mainContainer.add(BorderLayout.SOUTH, dayContainer);
        
        f.add(mainContainer);
        
        addNoteBtn.addActionListener((e) -> {
            addNote h=new addNote(jardinId,datePicker.getDate());
            h.getF().show();
        });
        
        addPlantBtn.addActionListener((e) -> {
            addPlantation h=new addPlantation(jardinId,datePicker.getDate());
            h.getF().show();
        });
    }
    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
