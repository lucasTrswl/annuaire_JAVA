import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Scanner;
import java.io.File;
import java.util.Collections;

import model.Contact;

public class App {

    private static Scanner _scan = new Scanner(System.in);

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
                    modifierContact();
                    break;
                case "4":
                    supprimerContact();
                    break;
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

    private static void listerContacts() {
        try {
            ArrayList<Contact> list = Contact.lister();
            ArrayList<String> namesList = new ArrayList<String>();

            for (Contact contact : list) {
                namesList.add((contact.getNom() + " " + contact.getPrenom()));
            }

            Collections.sort(namesList);
            System.out.println(namesList);
            for (Contact contact : list) {
                System.out.println(contact.getNom() + " " + contact.getPrenom());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Une erreur est survenue lors de l'obtention des contacts.");        
        } catch (ParseException e2) {
            System.out.println("Une erreur est survenue lors de l'obtention des contacts");
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
        menus.add("q- Quitter");
        for (String menu : menus) {
            System.out.println(menu);
        }
    }
}
