package model;

public class Date {
    private int Annee;
    private int Mois;
    private int Jour;
  
    public Date(int annee, int mois, int jour) {
      this.Annee = annee;
      this.Mois = mois;
      this.Jour = jour;
    }
    
    public int getAnnee() {
        return Annee;
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


    @Override
    public String toString() {
      return "Né le " + Jour + "/" + Mois + "/" + Annee;
    }
  }