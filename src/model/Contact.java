package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private static final String SEPARATEUR = ";";

    private String nom;
    private String prenom;
    private String mail;
    private String telephone;
    private Date dateNaissance;

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

    public void setMail(String mail) throws ParseException {
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
        Pattern pat = Pattern.compile("^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$");
        Matcher matcher = pat.matcher(telephone);
        if (matcher.matches()) {
            this.telephone = telephone;
        } else {
            ParseException e = new ParseException("Le format du numéro est incorrect.", 0);
            throw e;
        }
    }

    public String getDateNaissance() {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        return f.format(dateNaissance);
    }

    public void setDateNaissance(String dateNaissance) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.dateNaissance = format.parse(dateNaissance);
    }

    //méthode pour enregistrer un contact dans le fichier csv
    public void enregistrer() throws IOException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("contacts.csv", true)));
        try {
            pw.println(this.toString());
        } finally {
            pw.close();
        }
    }

    // fonction pour lister les contacts enregistrés dans le fichier csv
    public ArrayList<Contact> lister() {
        ArrayList<Contact> list = new ArrayList<>();
        // TODO: récupérer les contacts dans le fichier et les ajouter à la liste.

        try {
            FileInputStream fstream = new FileInputStream("contacts.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                String[] fields = strLine.split(SEPARATEUR);
                Contact contact = new Contact();
                contact.setNom(fields[0]);
                contact.setPrenom(fields[1]);
                contact.setMail(fields[2]);
                contact.setTelephone(fields[3]);
                contact.setDateNaissance(fields[4]);
                //and so on
                list.add(contact);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String toString() {
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
        return build.toString();
    }

    public static void clear() {
    }

    public static Iterator<Contact> iterator() {
        return null;
    }

    public int getId() {
        return 0;
    }

}