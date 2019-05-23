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

import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Espace;
import com.mycompany.Entite.Reservation;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;




/**
 *
 * @author Ghaith
 */
public class ServiceReservation {
    
     
    
    public void ajoutReservation(Reservation r) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/GrandVert/web/app_dev.php/preservation/newReservation?DateDebut="+r.getDateDebut()+"&DateFin="+r.getDateFin()+"&NbPlaces="+r.getNbPlaces()+"&idp="+r.getEspace().getId()+"&idu="+3;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
      public void modifierReservation(Reservation r) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/GrandVert/web/app_dev.php/preservation/modifierPreservationJson?id="+r.getId()+"&DateDebut="+r.getDateDebut()+"&DateFin="+r.getDateFin()+"&NbPlaces="+r.getNbPlaces()+"&idp="+r.getEspace().getId();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
     public void annulerReservation(int id) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/GrandVert/web/app_dev.php/preservation/annulerReservation/";// création de l'URL
        con.setUrl(Url+id);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }

    public ArrayList<Reservation> parseListReservationJson(String json) {

        ArrayList<Reservation> listReservations = new ArrayList<>();

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
            Map<String, Object> reservations = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) reservations.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Reservation r = new Reservation();

                float id = Float.parseFloat(obj.get("id").toString());
                float nbPlaces = Float.parseFloat(obj.get("nbPlaces").toString());
   
               String t =  obj.get("dateDebut").toString().substring(229);
               String t2 = t.substring(0,t.indexOf("}"));
               
               String t3 =  obj.get("dateFin").toString().substring(229);
               
               String t4 = t.substring(0,t3.indexOf("}"));
               System.out.println(t4);
               
                //   Timestamp ts=new Timestamp();  
               // String a =    Long.toString(Math.round((Double.parseDouble(t.substring(t.indexOf("}"), 10)))));
                Long x=  Math.round((Double.parseDouble(t.substring(0, t.indexOf("}")))));
                Long x2=  Math.round((Double.parseDouble(t3.substring(0, t3.indexOf("}")))));
               //System.out.println(x);
                String d = new SimpleDateFormat("yyyy-MM-dd").format(new Date (x*1000L));
                String d2 = new SimpleDateFormat("yyyy-MM-dd").format(new Date (x2*1000L));
                System.out.println(d);
                System.out.println(d2);
              //  System.out.println(x);
             /*  java.util.Date time=new java.util.Date((long)x*1000);
                System.out.println("hi"+time);
                String d = new SimpleDateFormat("yyyy-MM-dd").format(new Date (x*1000L));
                System.out.println(d);
             //   Date date=ts;  
             //   System.out.println(date);
               
               
             
          */
                      
              
                
                r.setId((int) id); 
                r.setNbPlaces((int) nbPlaces );
                r.setDateDebut(new Date (x*1000L));
                r.setDateFin(new Date (x2*1000L));
             //   r.setDateDebut( Long.toString(Math.round((Double.parseDouble(t.substring(0, 10))))));
               // System.out.println(Long.toString(Math.round((Double.parseDouble(t.substring(0, 10))))));
            //    r.setNbPlaces(t);
                //r.setDateDebut(str);
                
             
               /*
                es.setNom(obj.get("nom").toString());
                es.setCapacity(obj.get("capacity").toString());
                es.setLieu(obj.get("lieu").toString());
                */
                System.out.println(r);
                
                listReservations.add(r);

            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        System.out.println(listReservations);
        return listReservations;

    }
    
    
    ArrayList<Reservation> listReservations = new ArrayList<>();
    
    public ArrayList<Reservation> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/GrandVert/web/app_dev.php/preservation/allReservation");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceReservation ser = new ServiceReservation();
                listReservations = ser.parseListReservationJson(new String(con.getResponseData()));
            }
        });
     
     
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listReservations;
    }
    
     ArrayList<Reservation> listReservations3 = new ArrayList<>();
    
       public ArrayList<Reservation> getList3(String lieu){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/GrandVert/web/app_dev.php/preservation/findPresLieuDQL?lieu="+lieu+"");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceReservation ser = new ServiceReservation();
                listReservations3 = ser.parseListReservationJson(new String(con.getResponseData()));
            }
        });
     
     
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listReservations3;
    }
    
         ArrayList<Reservation> listReservations4 = new ArrayList<>();
    
       public ArrayList<Reservation> getList4(String dateDebut,String dateFin){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/GrandVert/web/app_dev.php/preservation/findReserByDateDQL?&dateDebut="+dateDebut+"&dateFin="+dateFin+"");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceReservation ser = new ServiceReservation();
                listReservations4 = ser.parseListReservationJson(new String(con.getResponseData()));
            }
        });
     
     
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listReservations4;
    }
       
            ArrayList<Reservation> listReservations5 = new ArrayList<>();
    
       public ArrayList<Reservation> getList5(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/GrandVert/web/app_dev.php/preservation/allExprReservationDQL");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceReservation ser = new ServiceReservation();
                listReservations5 = ser.parseListReservationJson(new String(con.getResponseData()));
            }
        });
     
     
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listReservations5;
    }
}
