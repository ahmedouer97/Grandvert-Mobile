/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.Entite.Note;
import com.mycompany.Service.NoteService;
import java.util.Date;

/**
 *
 * @author psn
 */
public class addNote {
    
    NoteService noteService = new NoteService();
    Form f;
    TextArea contenuGUI;
    Button btnajout;
    
    public addNote(int jardinId, Date date){
        f = new Form("Add Note");
        Container cnt = new Container(new BorderLayout());
        contenuGUI = new TextArea("");
        contenuGUI.setWidth(200);
        btnajout = new Button("Ajouter Note");
        cnt.add(BorderLayout.CENTER, contenuGUI);
        cnt.add(BorderLayout.SOUTH, btnajout);
        f.add(cnt);
        
        
        
        btnajout.addActionListener((e) -> {
            NoteService noteService = new NoteService();
            Note n = new Note();
            n.setContenu(contenuGUI.getText());
            n.setDateN(date);
            n.setJardinId(jardinId);
            noteService.addNote(n);
        });
        
        f.getToolbar().addCommandToRightBar("Retour", null, (ev)->{Jardin h=new Jardin(jardinId);
          h.getF().show();
          });
    }
    
    public addNote(int noteId){
        f = new Form("Note");
        Container cnt = new Container(new BorderLayout());
        contenuGUI = new TextArea("");
        contenuGUI.setWidth(200);
        btnajout = new Button("Ajouter Note");
        cnt.add(BorderLayout.CENTER, contenuGUI);
        cnt.add(BorderLayout.SOUTH, btnajout);
        f.add(cnt);
        
        Note n = noteService.getNote(noteId);
        contenuGUI.setText(n.getContenu());
        
        btnajout.addActionListener((e) -> {
            
            n.setContenu(contenuGUI.getText());
            noteService.addNote(n);
        });
        
        f.getToolbar().addCommandToRightBar("Retour", null, (ev)->{Jardin h=new Jardin(n.getJardinId());
          h.getF().show();
          });
    }
    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextArea getTcont() {
        return contenuGUI;
    }

    public void setTcont(TextArea ta) {
        this.contenuGUI = ta;
    }
    
    
    
}
