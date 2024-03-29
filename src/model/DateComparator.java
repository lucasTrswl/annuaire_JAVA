package model;

import java.util.Comparator; 

// classe qui permet de mettre en place la métohde Comparator
public class DateComparator implements Comparator<Date> {
    private int Annee;
    private int Mois;
    private int Jour;

    public void ComparableDate(int annee, int mois, int jour){
        this.Annee = annee;
        this.Mois = mois;
        this.Jour = jour;
    }

    public int getAnnee() {
        return Annee;
    }

    // Setter pour l'année

    public void setAnnee(int annee) {
        Annee = annee;
    }


    public int getMois() {
        return Mois;
    }


        // Setter pour le mois

    public void setMois(int mois) {
        Mois = mois;
    }


    public int getJour() {
        return Jour;
    }

        // Setter pour le jour
    public void setJour(int jour) {
        Jour = jour;
    }


     // Méthode pour comparer les dates
    @Override
    public int compare(Date o1, Date o2) {
        int compareAnnee = o1.getAnnee() - o2.getAnnee();
        int compareMois = o1.getMois() - o2.getMois();
        int compareJour = o1.getJour() - o2.getJour();

        if (compareAnnee == 0) {
        return ((compareMois == 0) ? compareJour : compareMois);
        } else {
        return compareAnnee;
        }
    }
    
}
