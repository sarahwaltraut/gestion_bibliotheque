package factory;

import model.Utilisateur;
import model.Livre;

import java.util.List;
import observer.Observer;

public class Adherent extends Utilisateur implements Observer {

    public Adherent( String id, String nom, String prenom , String email) {
        super(id, nom, prenom, email);
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

	@Override
	public void notifier(String message) {
		 System.out.println("Notification pour " + prenom + " " + nom + " : " + message);
		
	}
}
