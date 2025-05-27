package bibliotheque;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import factory.LivreFactory;
import factory.Genre;
import model.Livre;
import strategy.AmendeFantasy;
import strategy.AmendePoesie;
import strategy.AmendeRoman;
import strategy.StrategieAmende;

public class Bibliotheque {
	
	private static Scanner scanner = new Scanner(System.in);
	private static List<Livre> livres = new ArrayList<>();

	
	 // ----------- Fonctions Employé -----------
    static void ajouterLivre() {
        System.out.print("Titre : ");
        String titre = scanner.nextLine();
        System.out.print("Auteur : ");
        String auteur = scanner.nextLine();
        System.out.print("ISBN : ");
        String isbn = scanner.nextLine();
        System.out.print("Année de publication : ");
        int annee = Integer.parseInt(scanner.nextLine());
        System.out.print("Nombre de pages : ");
        int nbPages = Integer.parseInt(scanner.nextLine());
        System.out.print("Langue : ");
        String langue = scanner.nextLine();
        System.out.print("Genre (roman, poesie, fantasy) : ");
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        String genreStr = scanner.nextLine().toUpperCase();

        Genre genre;
        try {
            genre = Genre.valueOf(genreStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Genre invalide !");
            return;
        }

        StrategieAmende strategie;
        switch (genre) {
            case ROMAN -> strategie = new AmendeRoman();
            case POESIE -> strategie = new AmendePoesie();
            case FANTASY -> strategie = new AmendeFantasy();
            default -> {
                System.out.println("Genre non pris en charge");
                return;
            }
        }
        
        Livre livre = LivreFactory.creerLivre(genre, titre, auteur, isbn, annee, nbPages, langue, strategie);
        livres.add(livre);
        System.out.println("Livre ajouté avec succès !");
    }

    static void supprimerLivre() {
        afficherLivres();
        System.out.print("Index du livre à supprimer : ");
        int index = Integer.parseInt(scanner.nextLine());
        if (index >= 0 && index < livres.size()) {
            livres.remove(index);
            System.out.println("Livre supprimé !");
        } else {
            System.out.println("Index invalide.");
        }
    }

    static void modifierLivre() {
        afficherLivres();
        System.out.print("Index du livre à modifier : ");
        int index = Integer.parseInt(scanner.nextLine());
        if (index >= 0 && index < livres.size()) {
            Livre livre = livres.get(index);
            System.out.print("Nouveau titre (" + livre.getTitre() + ") : ");
            String nouveauTitre = scanner.nextLine();
            livre.setTitre(nouveauTitre.isEmpty() ? livre.getTitre() : nouveauTitre);
            System.out.println("Modification réussie.");
        } else {
            System.out.println("Index invalide.");
        }
    }

    // ----------- Fonctions Adhérent -----------
    static void afficherLivresDisponibles() {
        System.out.println("\n--- Livres disponibles ---");
        for (int i = 0; i < livres.size(); i++) {
            Livre l = livres.get(i);
            if (l.estDisponible()) {
                System.out.println(i + " - " + l.getTitre() + " (" + l.getAuteur() + ")");
            }
        }
    }

    static void emprunterLivre() {
        afficherLivresDisponibles();
        System.out.print("Index du livre à emprunter : ");
        int index = Integer.parseInt(scanner.nextLine());
        if (index >= 0 && index < livres.size()) {
            Livre l = livres.get(index);
            if (l.estDisponible()) {
                l.emprunter();
                System.out.println("Livre emprunté !");
            } else {
                System.out.println("Ce livre est déjà emprunté.");
            }
        } else {
            System.out.println("Index invalide.");
        }
    }

    static void retournerLivre() {
        System.out.println("\n--- Livres empruntés ---");
        for (int i = 0; i < livres.size(); i++) {
            Livre l = livres.get(i);
            if (!l.estDisponible()) {
                System.out.println(i + " - " + l.getTitre());
            }
        }
        System.out.print("Index du livre à retourner : ");
        int index = Integer.parseInt(scanner.nextLine());
        if (index >= 0 && index < livres.size()) {
            Livre l = livres.get(index);
            if (!l.estDisponible()) {
            	l.retourner();
                System.out.print("Nombre de jours de retard : ");
                int jours = Integer.parseInt(scanner.nextLine());
                double amende = l.calculerAmende(jours);
                System.out.println("Livre retourné avec amende de : " + amende + "€");
            } else {
                System.out.println("Ce livre n'était pas emprunté.");
            }
        } else {
            System.out.println("Index invalide.");
        }
    }

    static void afficherLivres() {
        System.out.println("\n--- Liste des livres ---");
        for (int i = 0; i < livres.size(); i++) {
            Livre l = livres.get(i);
            System.out.println(i + " - " + l.getTitre() + " | " + l.getAuteur() + " | " + l.getGenre().name() + " | Disponible: " + l.estDisponible());
        }
    }
}
