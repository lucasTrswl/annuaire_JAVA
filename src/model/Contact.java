package model;

// Importation des méthodes JAVA
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {

     // Séparateur utilisé pour séparer les données dans le fichier CSV
    private static final String SEPARATEUR = ";";


     // Attributs d'un contact
    private String nom;
    private String prenom;
    private String mail;
    private String telephone;
    private Date dateNaissance;


     // Getters et setters pour les attributs
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }


    // Setter pour définir l'adresse mail du contact
// Vérifie également si le format de l'adresse mail est valide
    public void setMail(String mail) throws ParseException {

          // Utilise une expression régulière pour vérifier le format de l'adresse mail
        Pattern pat = Pattern.compile("^[a-zA-Z0-9_.-]+@{1}[a-zA-Z0-9_.-]{2,}\\.[a-zA-Z.]{2,10}$");
        Matcher matcher = pat.matcher(mail);
        if (matcher.matches()) {
            this.mail = mail;
        } else {
            ParseException e = new ParseException("Le format du mail est incorrect.", 0);
            throw e;
        }
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) throws ParseException {

        // Vérification du format du numéro de téléphone
        Pattern pat = Pattern.compile("^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$");
        Matcher matcher = pat.matcher(telephone);
        if (matcher.matches()) {
            this.telephone = telephone;
        } else {

                        // Lancement d'une exception si le format est incorrect

            ParseException e = new ParseException("Le format du numéro est incorrect.", 0);
            throw e;
        }
    }

    public String getDateNaissance() {

                // Formatage de la date de naissance

        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        return f.format(dateNaissance);
    }

    public void setDateNaissance(String dateNaissance) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.dateNaissance = format.parse(dateNaissance);
    }

    class DateOfBirthComparator implements Comparator<Contact> {

 // Comparaison de la date de naissance de deux contacts

        public int compare(Contact c1, Contact c2) {
            return c1.getDateNaissance().compareTo(c2.getDateNaissance());
        }
    }

    public void enregistrer() throws IOException {

     // Ecriture des données dans un fichier csv

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("contacts.csv", true)));
        try {
            pw.println(this.toString());
        } finally {
            pw.close();
        }
    }

    public void supprimer(File file) throws IOException {

        // Suppression des données dans un fichier csv

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
        try {
            pw.println(this.toString());
        } finally {
            pw.close();
        }
    }

    public static ArrayList<Contact> lister() throws IOException, ParseException  {

        // Liste pour stocker les contacts lus à partir du fichier CSV

        ArrayList<Contact> list = new ArrayList<>();

        // Chemin du fichier CSV contenant les contacts

        File file = new File("contacts.csv");

         // Lecture des données à partir du fichier CSV

        BufferedReader br
        = new BufferedReader(new FileReader(file));
        
        String st;

         // Boucle pour lire chaque ligne du fichier

        while ((st = br.readLine()) != null){
            Contact c = new Contact();
            String split[] = st.split(";");
            c.setNom(split[0]);
            c.setPrenom(split[1]);
            c.setMail(split[2]);
            c.setTelephone(split[3]);
            c.setDateNaissance(split[4]);
            list.add(c);
        }

        // Fermeture du fichier

        br.close();

        // Retourner la liste des contacts

        return list;
    }

    @Override
    public String toString() {

        // Création d'un objet StringBuilder pour construire la chaîne de caractères

        StringBuilder build = new StringBuilder();
        build.append(this.getNom());
        build.append(SEPARATEUR);
        build.append(this.getPrenom());
        build.append(SEPARATEUR);
        build.append(this.getMail());
        build.append(SEPARATEUR);
        build.append(this.getTelephone());
        build.append(SEPARATEUR);
        build.append(this.getDateNaissance());

         //Retourne une chaîne de caractères

        return build.toString();
    }
}



