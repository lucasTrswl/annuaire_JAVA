package model;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

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
    
    public int getAnnee() {
        return Annee;
    }

    public String getContactName() {
        return contactName;
    }

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

    public void setAnnee(int annee) {
        Annee = annee;
    }



    public int getMois() {
        return Mois;
    }



    public void setMois(int mois) {
        Mois = mois;
    }



    public int getJour() {
        return Jour;
    }



    public void setJour(int jour) {
        Jour = jour;
    }

    public int countDig(int n)  {  
        int count = 0;  
        while(n != 0)  
        {  
        n = n / 10;  
        count = count + 1;  
        }  
        return count;  
    }  

    @Override
    public String toString() {
        setContactName();
        if(countDig(Jour) <= 1 || countDig(Mois) <= 1){
            return contactName + " né le 0" + Jour + "/0" + Mois + "/" + Annee;
        }
        return contactName + " né le " + Jour + "/" + Mois + "/" + Annee;
    }
  }