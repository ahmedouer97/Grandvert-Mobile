/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.mycompany.Entite.StageManager;
import com.mycompany.Service.ServiceTask;
import com.mycompany.Entite.Task;
import com.mycompany.Entite.User;
import com.mycompany.Service.ServiceUser;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author bhk
 */
public class HomeForm {

    Form hi;
    TextField tnom;
    TextField tetat;
    Button btnajout,btnaff;

    public HomeForm() {
                                
        StageManager sm = StageManager.getStageManager();
        sm.setUser(new User(3 , "Khalid" , "Moderateur" ,"keeptoo@gmail.com", "user.jpeg"));
        /****************** Premier Interface : dashboard ******************/
        EncodedImage IconForum = null;
        EncodedImage IconPlante = null;
        EncodedImage IconJardin = null;
        EncodedImage IconPreservation = null;
        EncodedImage IconEvent = null;
         EncodedImage Iconserre = null;
        try {
            IconForum = EncodedImage.create("/Forum3.png").scaledEncoded(300, 300);
            IconPlante = EncodedImage.create("/Plante.png").scaledEncoded(300, 300);
            IconJardin = EncodedImage.create("/jardin.png").scaledEncoded(300, 300);
            IconPreservation = EncodedImage.create("/Forum3.png").scaledEncoded(300, 300);
            IconEvent = EncodedImage.create("/Event.png").scaledEncoded(300, 300);
            Iconserre = EncodedImage.create("/serre.jpg").scaledEncoded(300, 300);
        } catch (IOException ex) {
        }
        Label labelForum = new Label(IconForum);
        labelForum.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Categorie a=new Categorie();
                a.getF().show();
                
            }
        });
        
          Label labelpres = new Label(Iconserre);
        labelpres.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               AjoutEspace h = new AjoutEspace();
        h.getF().show();
                
            }
        });

        Label labelEvent = new Label(IconEvent);
        Label labelPlante = new Label(IconPlante);
        Label labelJardin = new Label(IconJardin);
        
        labelJardin.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               Jardin h = new Jardin(sm.getJardinId());
                h.getF().show();
                
            }
        });

        hi = new Form("Dashboard", new GridLayout(3, 2));
        hi.add(labelForum).
            add(labelEvent).
            add(labelPlante).
            add(labelJardin).add(labelpres);
        
        /******************************* Menu *******************************/
        Toolbar tb = hi.getToolbar();
        Image icon = null; 
        Container topBar = BorderLayout.east(new Label(icon));
        //topBar.add(BorderLayout.SOUTH, new Label("Grand Vert", "SidemenuTagline")); 
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);

        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
            HomeForm f =new HomeForm();
            f.getHi();
        }); 
        tb.addMaterialCommandToSideMenu("Site web", FontImage.MATERIAL_WEB, e -> {});
        tb.addMaterialCommandToSideMenu("Déconnecter", FontImage.MATERIAL_LOCK, e -> {
            sm.setUser(null);
            Login l = new Login();
            l.getHi().show();
        });
        tb.addMaterialCommandToSideMenu("à propos", FontImage.MATERIAL_INFO, e -> {});
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
