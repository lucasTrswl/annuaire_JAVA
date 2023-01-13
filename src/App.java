import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collections;
import java.util.Comparator;

import model.Contact;
import model.DateComparator;
import model.Date;
import model.ComparableName;
import java.util.List;

public class App {

    private static Scanner _scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception{

        while (true) {
            afficherMenu();
            String choix = _scan.nextLine();
            switch (choix) {
                case "1":
                    try {
                        ajouterContact();
                    } catch (IOException e1) {
                        System.out.println("Une erreur est survenue lors de l'ajout du contact.");        
                        e1.printStackTrace();
                    }
                    break;
                case "2":
                    try {
                        listerContacts();
                    } catch (IOException | ParseException e) {
                        System.out.println("Une erreur est survenue lors de l'obtention des contacts.");        
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    modifierContact();
                    break;
                case "4":
                    supprimerContact();
                    break;
                case "5":
                    sortByEmail();
                case "q":
                    return;
                default:
                    System.out.println("Boulet !!!");
                    break;
            }
        }
    }

    private static void supprimerContact() {
        ArrayList<Contact> list;
        try {
            list = Contact.lister();
            System.out.println("Saisir le nom ou prénom du contact à supprimer :");
            String nom = _scan.nextLine();
            try {
                for (Contact contact : list) {
                    if(contact.getNom().equals(nom) || contact.getPrenom().equals(nom)){
                        int index = list.indexOf(contact);
                        list.remove(index);
                    }
                }
            } catch (ConcurrentModificationException e) {

            }
            System.out.println(nom + " à été supprimé.");
            File file = new File("contacts.csv");
        
            file.delete();
            file.createNewFile(); 
            for (Contact contact : list) {
                contact.supprimer(file);

            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void modifierContact() {
        System.out.println("Saisir le nom du contact que vous souhaitez modifier: ");
        String nm = _scan.nextLine();
        try {
            ArrayList<Contact> list = Contact.lister();
            for (Contact contact : list) {
                if(nm.equals(contact.getNom()) || nm.equals(contact.getPrenom()) || nm.equals(contact.getPrenom() + " " + contact.getNom())){
                    System.out.println("Vous avez choisi " + contact.getPrenom() + " " + contact.getNom());
                    System.out.println("Veuillez saisir le nouveau Nom: ");
                    String cho = _scan.nextLine();
                    if(!cho.equals("")){
                        contact.setNom(cho); 
                    }
                    System.out.println("Veuillez saisir le nouveau prénom: ");
                    String cho1 = _scan.nextLine();
                    if(!cho1.equals("")){
                        contact.setPrenom(cho1); 
                    }                
                    System.out.println("Veuillez saisir la nouvelle adresse-email: ");
                    String cho2 = _scan.nextLine();
                    if(!cho2.equals("")){
                        contact.setMail(cho2);
                    }
                    System.out.println("Veuillez saisir le nouveau numéro de téléphone: ");
                    String cho3 = _scan.nextLine();
                    if(!cho3.equals("")){
                        contact.setTelephone(cho3);                
                    }
                    System.out.println("Veuillez saisir la nouvelle date de naissance: ");
                    String cho4 = _scan.nextLine();
                    if(!cho4.equals("")){
                        contact.setDateNaissance(cho4);
                    }
                    break;
                        
                    }
                }
            File file = new File("contacts.csv");
    
            file.delete();
            file.createNewFile();
            for (Contact contact : list) {
                contact.supprimer(file);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Une erreur est survenue lors de l'obtention des contacts.");        
        } catch (ParseException e2) {
            e2.printStackTrace();
            System.out.println("Une erreur est survenue lors de l'obtention des contacts");
        }
    }

    private static void listerContacts() throws IOException, ParseException {
        System.out.println("Souhaitez vous trier la liste? (y/n)");
        String yesno = _scan.nextLine();
        switch(yesno){
            case "y":
                System.out.println("Quelle méthode de tri voulez-vous utiliser?");
                System.out.println("--Noms--");
                System.out.println("--Emails--");
                System.out.println("--Dates--");
                String choix = _scan.nextLine();
                switch(choix){
                    case "Noms":
                        nameSorting();
                    case "Emails":
                        emailSorting();
                    case "Dates":
                        dateSorting();
                    
                }
                break;
            case "n":
                normalSorting();
                break;
        }


        System.out.println("Souhaitez vous rechercher un contact en particulier? (y/n)");
        String yesno2 = _scan.nextLine();
        switch(yesno2){
            case "y":
                System.out.println("Entrez le nom du contact à rechercher");
                String nom = _scan.nextLine();
                ArrayList<Contact> list = Contact.lister();
                for (Contact contact : list) {
                    if(nom.equals(contact.getNom()) || nom.equals(contact.getPrenom()) || nom.equals(contact.getPrenom() + " " + contact.getNom())){
                        System.out.println(contact.getNom() + " " + contact.getPrenom());
                    }
                }

        }
    }
    

    private static void normalSorting() throws IOException, ParseException{
            ArrayList<Contact> list = Contact.lister();

            for (Contact contact : list) {
                System.out.println(contact.getNom() + " " + contact.getPrenom());
            }
    }

    private static void nameSorting() throws IOException, ParseException{
        ArrayList<Contact> list = Contact.lister();
        ArrayList<ComparableName> comparableNames = new ArrayList<>();
        
        for (Contact contact : list) {
            comparableNames.add(new ComparableName(contact.getNom(), contact.getPrenom()));
        }
        Collections.sort(comparableNames);

        for (ComparableName name : comparableNames) {
            System.out.println(name.getNom() + " " + name.getPrenom());
        }
    }

    public static void emailSorting() {
        // Liste pour stocker les objets Contact lus à partir du fichier CSV
        try {

            List<Contact> listeContacts = Contact.lister();
            Collections.sort(listeContacts, (c1, c2) -> c1.getMail().compareTo(c2.getMail()));
    
            // Afficher la liste triée
            for (Contact contact : listeContacts) {
                System.out.println(contact.toString());
            }

        } catch (Exception e) {
            System.out.println("impossible de recuperer la liste des contacts");
        }
    }

    private static void dateSorting() throws IOException, ParseException{
        ArrayList<Contact> list = Contact.lister();
        ArrayList<Date> comparableDates = new ArrayList<>();
        
        for (Contact contact : list) {
            ArrayList<Integer> dateNumbers = dateSeparator(contact.getDateNaissance());
            comparableDates.add(new Date(dateNumbers.get(0), dateNumbers.get(1), dateNumbers.get(2)));
        }

        Collections.sort(comparableDates, new DateComparator());

        for (Date date : comparableDates) {
            System.out.println(date);
        }
    }

    private static ArrayList<Integer> dateSeparator(String date){
        String split[] = date.split("/");
        String a = split[2];
        String m = split[1];
        String j = split[0];
        int annee = Integer.parseInt(a);
        int mois = Integer.parseInt(m);
        int jour = Integer.parseInt(j);
        ArrayList<Integer> dateInts = new ArrayList<>();
        dateInts.add(annee);
        dateInts.add(mois);
        dateInts.add(jour);
        return dateInts;
    }

    private static void ajouterContact() throws IOException {
        Contact c = new Contact();
        System.out.println("Saisir le nom");
        c.setNom(_scan.nextLine());

        System.out.println("Saisir le prénom");
        c.setPrenom(_scan.nextLine());

        while (true) {
            try {
                System.out.println("Saisir le mail");
                c.setMail(_scan.nextLine());
                break;
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.println("Saisir le téléphone");
                c.setTelephone(_scan.nextLine());
                break;
            } catch (ParseException e) {
                System.out.println("Mauvais téléphone!");
            }
        }

        while (true) {
            try {
                System.out.println("Saisir la date de naissance");
                c.setDateNaissance(_scan.nextLine());
                break;
            } catch (ParseException e) {
                System.out.println("Mauvaise date de naissance!");
            }
        }
        c.enregistrer();
        System.out.println("Contact enregistré");
    }

    private static void afficherMenu() {
        ArrayList<String> menus = new ArrayList<>();
        menus.add("-- MENU --");
        menus.add("1- Ajouter un contact");
        menus.add("2- Lister les contacts");
        menus.add("3- Modifier un contact");
        menus.add("4- Supprimer un contact");
        menus.add("5 - Afficher la liste de contacts triée par email");
        menus.add("q- Quitter");
        for (String menu : menus) {
            System.out.println(menu);
        }
    }





    /**
     * @param args
     */
    public static void sortByEmail() {
        // Chemin du fichier CSV contenant les contacts
        String filePath = "contacts.csv";
        
        // Liste pour stocker les objets Contact lus à partir du fichier CSV
        List<Contact> contacts = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Lire chaque ligne du fichier
            String list;
            while ((list = br.readLine()) != null) {
                // Séparer les données de chaque ligne en utilisant la virgule comme séparateur
                String[] data = list.split(";");
                String nom = data[0];
                String prenom = data[1];
                String mail = data[2];
                String telephone = data[3];
                String dateNaissance = data[4];



                // Ajouter un nouvel objet Contact à la liste avec les données lues
                contacts.add(new Contact());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Trier la liste de contacts par email
        Collections.sort(contacts, (c1, c2) -> c1.getMail().compareTo(c2.getMail()));
        
        // Afficher la liste triée
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    



    
}


    