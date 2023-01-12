package model;

public class ComparableName implements Comparable<ComparableName> {
    private String Nom;
    private String Prenom;
    public ComparableName(String nom, String prenom){
        this.Nom = nom;
        this.Prenom = prenom;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

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
