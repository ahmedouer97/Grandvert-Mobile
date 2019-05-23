/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Jardin;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author psn
 */
public class JardinService {
    
    ArrayList<Jardin> listJardin = new ArrayList<>();
    
    public ArrayList<Jardin> parseListTaskJson(String json) {

        ArrayList<Jardin> listJardin = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String, Object> jardins = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) jardins.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Jardin e = new Jardin();

                float id = Float.parseFloat(obj.get("id").toString());
                float userId = Float.parseFloat(obj.get("user_id").toString());
                Date date=new SimpleDateFormat("yyyy-mm-dd").parse(obj.get("date_creation").toString());

                e.setId((int) id);
                e.setNom(obj.get("nom").toString());
                e.setDateC(date);
                e.setUser((int) userId);
                System.out.println(e);
                
                listJardin.add(e);

            }

        } catch (IOException ex) {
        } catch (ParseException ex) {
            
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        System.out.println(listJardin);
        return listJardin;

    }
    
    public Jardin getJardin(int id){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/GrandVert-Web/web/app_dev.php/api/jardin/"+id);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JardinService ser = new JardinService();
                listJardin = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listJardin.get(0);
    }
    
}
