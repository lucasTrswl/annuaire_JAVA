package model;

// Classe qui implémente l'interface Comparable pour pouvoir trier des objets de type ComparableName par nom et prénom.
public class ComparableName implements Comparable<ComparableName> {
    private String Nom;
    private String Prenom;
    public ComparableName(String nom, String prenom){
        this.Nom = nom;
        this.Prenom = prenom;
    }

    //Méthode pour récupérer le nom

    public String getNom() {
        return Nom;
    }

    //Méthode pour récupérer le prénom

    public String getPrenom() {
        return Prenom;
    }

    //Méthode pour définir le prénom

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    //Méthode pour définir le nom

    public void setNom(String nom) {
        Nom = nom;
    }


    //Méthode pour comparer des objets de type ComparableName par nom et prénom

    @Override
    public int compareTo(ComparableName other) {
        int compareName = this.getNom().compareTo(other.getNom());
        int comparePrenom = this.getPrenom().compareTo(other.getPrenom());

        if (compareName == 0) {
            return comparePrenom;
          } else {
            return compareName;
          }
    }
    
}
