
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

/**
 *
 * @author psn
 */
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Plantation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author psn
 */
public class PlantationService {
    
    ArrayList<Plantation> listPlantation = new ArrayList<>();
    
    public ArrayList<Plantation> parseListTaskJson(String json) {

        ArrayList<Plantation> listPlantation = new ArrayList<>();

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
            Map<String, Object> notes = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) notes.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Plantation e = new Plantation();

                float id = Float.parseFloat(obj.get("id").toString());
                float jardinId = Float.parseFloat(obj.get("jarind_id").toString());
                float planteId = Float.parseFloat(obj.get("plante").toString());
                Date date=new SimpleDateFormat("yyyy-mm-dd").parse(obj.get("date").toString());


                e.setId((int) id);
                e.setPlante((int) planteId);
                e.setDateP(date);
                e.setTypeP(obj.get("type").toString());
                e.setJardin((int)jardinId);
                System.out.println(e);
                
                listPlantation.add(e);

            }

        } catch (IOException ex) {
        } catch (ParseException ex) {
            
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        System.out.println(listPlantation);
        return listPlantation;
        
    }
        
        public Plantation getPlantation(int id){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/GrandVert-Web/web/app_dev.php/api/note/find?id="+id);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                PlantationService ser = new PlantationService();
                listPlantation = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPlantation.get(0);
    }
        
    public ArrayList<Plantation> getPlantations(int id, String date){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/GrandVert-Web/web/app_dev.php/api/note/find?date="+date+"&jardin="+id);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                PlantationService ser = new PlantationService();
                listPlantation = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPlantation;
    }
        
    public void addPlantation(Plantation ta) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/GrandVert-Web/web/app_dev.php/api/note/add?type=" + ta.getTypeP() + "&date=" + ta.getDatePString()+ "&jardin=" + ta.getJardin();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
}

