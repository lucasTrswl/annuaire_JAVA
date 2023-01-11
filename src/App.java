import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import model.Contact;

public class App {

    private static Scanner _scan = new Scanner(System.in);
    private static ArrayList<Contact> menus;

    public static void main(String[] args) throws Exception {

        while (true) {
            afficherMenu();
            String choix = _scan.nextLine();
            switch (choix) {
                case "1":
                    ajouterContact();
                    break;
                case "2":
                    listerContacts();
                    break;
                case "3":
                    supprimerContact();
                    break;
                case "q":
                    return;
                default:
                    System.out.println("Veuillez sélectionner parmi les choix disponibles !!!");
                    break;
            }
        }
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
                System.out.println("Format de téléphone incorrect!");
            }
        }

        while (true) {
            try {
                System.out.println("Saisir la date de naissance");
                c.setDateNaissance(_scan.nextLine());
                break;
            } catch (ParseException e) {
                System.out.println("Date de naissance doit être dd/MM/yyyy");
            }
        }
        c.enregistrer();
        System.out.println("Contact enregistré");



    }


    private static void listerContacts() {
        Contact c = new Contact();
        ArrayList<Contact> list = c.lister();
        for (Contact contact : list) {
            System.out.println(contact.getNom() + "; " + contact.getPrenom() + "; " + contact.getMail() + "; " + contact.getTelephone() + "; " + contact.getDateNaissance());
        }
    }

    private static ArrayList<Contact> contacts = new ArrayList<Contact>();


    private static void supprimerContact() throws IOException{
        Contact c = new Contact();
        ArrayList<Contact> list = c.lister();
        System.out.println("Saisir l'indice du contact à supprimer :");
        int index = _scan.nextInt();
        list.remove(index);

    }


    private static void afficherMenu() {
        ArrayList<String> menus = new ArrayList<>();
        menus.add("-- MENU --");
        menus.add("1- Ajouter un contact");
        menus.add("2- Lister les contacts");
        menus.add("3- Supprimer un contact");
        menus.add("q- Quitter");
        for (String menu : menus) {
            System.out.println(menu);
        }
    }


 
}