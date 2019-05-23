/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.StageManager;
import com.mycompany.Entite.User;
import com.mycompany.Service.ServiceUser;

/**
 *
 * @author bhk
 */
public class Login {

    Form hi;
    TextField tnom;
    TextField tetat;
    Button btnajout,btnaff;
    ServiceUser userService = new ServiceUser();

    public Login() {
                                
        StageManager sm = StageManager.getStageManager();
        //sm.setUser(new User(3 , "Khalid" , "Moderateur" ,"keeptoo@gmail.com", "user.jpeg"));
        /****************** Premier Interface : dashboard ******************/
        hi = new Form("Login" , BoxLayout.y());
        TextField txtusername = new TextField("", "Username");
        TextField txtpassword = new TextField("", "Password", 20, TextField.PASSWORD);
        Button btnconnect = new Button("connecter");
        
        btnconnect.addActionListener((evt) -> {
            int id = userService.checkPassword(txtusername.getText(), txtpassword.getText());
            System.out.println(id);
            if (id!=0){
                User user = userService.getById(id);
                sm.setUser(user);
            }
        });
        
        Button btnSinscrire= new Button("s'inscrire");
        
        btnSinscrire.addActionListener((evt) -> {
            
        });
        
        hi.add(new Label("                                "));
        hi.add(new Label("                                "));
        hi.add(new Label("                                "));
        hi.add(new Label("                                "));

        hi.add(txtusername);
        hi.add(txtpassword);
        hi.add(btnconnect);
        hi.add(btnSinscrire);

        hi.show();
    }

    public Form getHi() {
        return hi;
    }

    public void setHi(Form hi) {
        this.hi = hi;
    }


    public TextField getTnom() {
        return tnom;
    }

    public void setTnom(TextField tnom) {
        this.tnom = tnom;
    }


}
