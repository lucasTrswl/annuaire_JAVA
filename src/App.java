// importation des méthodes JAVA

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

public class App {

    private static Scanner _scan = new Scanner(System.in);

    public static void main(String[] args){
        // Boucle infinie pour afficher le menu et lire les entrées utilisateur
        while (true) {
            // Affiche le menu
            afficherMenu();
            // Lit le choix de l'utilisateur
            String choix = _scan.nextLine();
            try{
                // Exécute l'action correspondante au choix de l'utilisateur
                switch (choix) {
                    case "1":
                        ajouterContact();
                        break;
                    case "2":
                        listerContacts();
                        break;
                    case "3":
                        modifierContact();
                        break;
                    case "4":
                        supprimerContact();
                        break;
                    case "q":
                        // Quitte le programme si l'utilisateur saisit "q"
                        return;
                    default:
                        System.out.println("Boulet !!!");
                        break;
                }
            } catch (IOException | ParseException e){
                // Affiche un message d'erreur et trace la pile d'exécution en cas d'erreur d'entrée/sortie ou de parsing
                System.out.println("Une erreur est survenue lors de l'obtention des contacts.");
                e.printStackTrace();
            }
            
        }
    }
    private static void supprimerContact() {
        ArrayList<Contact> list;
        try {
            list = Contact.lister();

            // Demande à l'utilisateur de saisir le nom ou le prénom du contact à supprimer
            System.out.println("Saisir le nom ou prénom du contact à supprimer :");
            String nom = _scan.nextLine();
            try {

                // Parcours la liste de contacts pour trouver le contact correspondant à la saisie de l'utilisateur
                for (Contact contact : list) {
                    if(contact.getNom().equals(nom) || contact.getPrenom().equals(nom)){
                        int index = list.indexOf(contact);

                        // Supprime le contact de la liste
                        list.remove(index);
                    }
                }
            } catch (ConcurrentModificationException e) {
                // Gestion de l'exception de modification concurrente
            }
            System.out.println(nom + " à été supprimé.");

            // Crée un objet File pour le fichier contenant les contacts
            File file = new File("contacts.csv");

            // Supprime le fichier contenant les contacts
            file.delete();

            // Crée un nouveau fichier vide
            file.createNewFile(); 

            // Enregistre les contacts modifiés dans le nouveau fichier
            for (Contact contact : list) {
                contact.supprimer(file);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }



    private static void modifierContact() {
        // Demande à l'utilisateur de saisir le nom du contact à modifier
        System.out.println("Saisir le nom du contact que vous souhaitez modifier: ");
        String nm = _scan.nextLine();
        try {
            // Obtient la liste des contacts existants
            ArrayList<Contact> list = Contact.lister();
            // Parcours la liste pour trouver le contact correspondant à la saisie de l'utilisateur
            for (Contact contact : list) {
                if(nm.equals(contact.getNom()) || nm.equals(contact.getPrenom()) || nm.equals(contact.getPrenom() + " " + contact.getNom())){
                    // Affiche le nom et le prénom du contact sélectionné
                    System.out.println("Vous avez choisi " + contact.getPrenom() + " " + contact.getNom());
                    // Demande à l'utilisateur de saisir les nouvelles informations du contact
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
                    // Arrête la boucle une fois le contact modifié
                    break;
                        
                    }
                }
            // Crée un objet File pour le fichier contenant les contacts
            File file = new File("contacts.csv");
            // Supprime le fichier contenant les contacts
            file.delete();
            // Crée un nouveau fichier vide
            file.createNewFile();
            // Enregistre les contacts modifiés dans le nouveau fichier
            for (Contact contact : list) {
                contact.supprimer(file);
            }
            
        } catch (IOException e) {
            // Trace la pile
            e.printStackTrace();
            // Affiche un message d'erreur en cas d'erreur d'entrée/sortie
            System.out.println("Une erreur est survenue lors de l'obtention des contacts.");        
        } catch (ParseException e2) {
            e2.printStackTrace();
            // Affiche un message d'erreur en cas d'erreur de parsing
            System.out.println("Une erreur est survenue lors de l'obtention des contacts");
        }
    }


    private static void listerContacts() throws IOException, ParseException {
        // demander si l'utilisateur souhaite trier la liste
        System.out.println("Souhaitez vous trier la liste? (y/n)");
        String yesno = _scan.nextLine();
        switch(yesno){
            case "y":
                // demander quelle méthode de tri utiliser
                System.out.println("Quelle méthode de tri voulez-vous utiliser?");
                System.out.println("--Noms--");
                System.out.println("--Emails--");
                System.out.println("--Dates--");
                String choix = _scan.nextLine();
                switch(choix){
                    case "Noms":
                        // appeler la méthode de tri par nom
                        nameSorting();
                        break;
                    case "Emails":
                        // appeler la méthode de tri par email
                        emailSorting();
                        break;
                    case "Dates":
                        // appeler la méthode de tri par date
                        dateSorting();
                        break;
                    
                }
                break;
            case "n":
                // appeler la méthode de tri normal
                normalSorting();
                break;
        }
        // demander si l'utilisateur souhaite rechercher un contact
        System.out.println("Souhaitez vous rechercher un contact en particulier? (y/n)");
        String yesno2 = _scan.nextLine();
        switch(yesno2){
            case "y":
                // demander le nom du contact à rechercher
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
    

    // méthode pour trier la liste normalement
    
    private static void normalSorting() throws IOException, ParseException{
            ArrayList<Contact> list = Contact.lister();
            for (Contact contact : list) {
                System.out.println(contact.getNom() + " " + contact.getPrenom());
            }
    }

    // méthode pour trier la liste par nom
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

    // méthode pour trier la liste par email
    public static void emailSorting() throws IOException, ParseException {
            List<Contact> listeContacts = Contact.lister();
            Collections.sort(listeContacts, (c1, c2) -> c1.getMail().compareTo(c2.getMail()));
    
            for (Contact contact : listeContacts) {
                System.out.println(contact.getMail());
            }
    }

    // méthode pour trier la liste par date de naissance

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

        // méthode pour séparer les différents éléments d'une date

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

    // méthode pour ajouter un contact
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

    // Méthode pour afficher le menu
    private static void afficherMenu() {
        ArrayList<String> menus = new ArrayList<>();
        menus.add("-- MENU --");
        menus.add("1- Ajouter un contact");
        menus.add("2- Lister les contacts");
        menus.add("3- Modifier un contact");
        menus.add("4- Supprimer un contact");
        menus.add("q- Quitter");
        for (String menu : menus) {
            System.out.println(menu);
        }
    }
}



    