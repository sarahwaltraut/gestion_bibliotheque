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

    public void emprunterLivre(Livre livre) {
        if (livre.estDisponible()) {
            livre.emprunter();
            System.out.println("Livre emprunté : " + livre.getTitre());
        } else {
            System.out.println("Le livre n'est pas disponible.");
        }
    }
    
    

    public void reserverLivre(Livre livre) {
        if (!livre.estDisponible()) {
            livre.reserver();
            System.out.println("Vous avez réservé le livre : " + livre.getTitre());
        } else {
            System.out.println("Le livre est disponible, vous pouvez l'emprunter directement.");
        }
    }
    
   
    
}
