package factory;

import model.Utilisateur;
import model.Livre;

import java.util.List;

public class Adherent extends Utilisateur {

    public Adherent(String nom, String prenom, String email, String id) {
        super(nom, prenom, email, id);
    }

    public void consulterLivres(List<Livre> livres) {
        System.out.println("Liste des livres disponibles :");
        for (Livre livre : livres) {
            System.out.println(livre);
        }
    }

    /*public void emprunterLivre(Livre livre) {
        if (livre.estDisponible()) {
            livre.emprunter();
            System.out.println("Livre emprunté : " + livre.getTitre());
        } else {
            System.out.println("Le livre n'est pas disponible.");
        }
    }*/
    
    public void emprunterLivre(Livre livre) {
        if (livre.getEtat().equals("Disponible")) {
            livre.emprunter();
            System.out.println("Livre emprunté : " + livre.getTitre());
        } else if (livre.getEtat().equals("Emprunté")) {
            System.out.println("Le livre est déjà emprunté.");
        } else if (livre.getEtat().equals("En réparation")) {
            System.out.println("Le livre est en réparation, emprunt impossible.");
        } else if (livre.getEtat().equals("Réservé")) {
            System.out.println("Le livre est réservé, emprunt impossible.");
        }
    }

    /*public void reserverLivre(Livre livre) {
        if (!livre.estDisponible()) {
            livre.reserver();
            System.out.println("Vous avez réservé le livre : " + livre.getTitre());
        } else {
            System.out.println("Le livre est disponible, vous pouvez l'emprunter directement.");
        }
    }*/
    
    public void reserverLivre(Livre livre) {
        if (livre.getEtat().equals("Disponible")) {
            livre.reserver();
            System.out.println("Vous avez réservé le livre : " + livre.getTitre());
        } else if (livre.getEtat().equals("Emprunté")) {
            livre.reserver();
            System.out.println("Vous avez réservé le livre : " + livre.getTitre());
        } else if (livre.getEtat().equals("En réparation")) {
            System.out.println("Le livre est en réparation, réservation impossible.");
        } else if (livre.getEtat().equals("Réservé")) {
            System.out.println("Le livre est déjà réservé.");
        }
    }
    
}
