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
        if(countDig(Jour) <= 1 || countDig(Mois) <= 1){
            return "Né le 0" + Jour + "/0" + Mois + "/" + Annee;
        }
        return "Né le " + Jour + "/" + Mois + "/" + Annee;
    }
  }