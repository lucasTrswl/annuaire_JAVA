package model;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

// constructeur de la classe Date qui initialise les variables d'année, mois et jour
public class Date {
    private int Annee;
    private int Mois;
    private int Jour;
    private String contactName;
  
    public Date(int annee, int mois, int jour) {
      this.Annee = annee;
      this.Mois = mois;
      this.Jour = jour;
    }
    
    // methode qui retourne l'année

    public int getAnnee() {
        return Annee;
    }

    // methode qui retourne le nom du contact associé à cette date

    public String getContactName() {
        return contactName;
    }

     // methode qui set le nom du contact associé à cette date en recherchant dans le fichier csv
    public void setContactName() {
        try {
            ArrayList<Contact> list = Contact.lister();
            for (Contact contact : list) {
                if(contact.getDateNaissance().equals("0" + Jour + "/0" + Mois + "/" + Annee)){
                    this.contactName = contact.getNom();
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    // methode qui set l'année

    public void setAnnee(int annee) {
        Annee = annee;
    }


    // methode qui retourne le mois

    public int getMois() {
        return Mois;
    }


    // methode qui set le mois
    public void setMois(int mois) {
        Mois = mois;
    }


    // methode qui retourne le jour

    public int getJour() {
        return Jour;
    }


    // methode qui set le jour

    public void setJour(int jour) {
        Jour = jour;
    }

    // methode qui compte le nombre de chiffres dans un nombre

    public int countDig(int n)  {  
        int count = 0;  
        while(n != 0)  
        {  
        n = n / 10;  
        count = count + 1;  
        }  
        return count;  
    }  

    // Methode toString qui retourne une chaine de caractère qui contient le nom du contact associé à cette date et la date

    @Override
    public String toString() {
        setContactName();
        if(countDig(Jour) <= 1 || countDig(Mois) <= 1){
            return contactName + " né le 0" + Jour + "/0" + Mois + "/" + Annee;
        }
        return contactName + " né le " + Jour + "/" + Mois + "/" + Annee;
    }
  }