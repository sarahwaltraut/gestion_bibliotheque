package bibliotheque;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import factory.LivreFactory;
import factory.Adherent;
import factory.Genre;
import model.Emprunt;
import model.Livre;
import observer.SystemeNotification;
import singleton.BibliothequeManager;
import strategy.AmendeFantasy;
import strategy.AmendePoesie;
import strategy.AmendeRoman;
import strategy.StrategieAmende;

public class Bibliotheque {
	
	private static Scanner scanner = new Scanner(System.in);
	private static BibliothequeManager manager = BibliothequeManager.getInstance();
    private static List<Livre> livres = manager.getLivres();


	
	 // ----------- Fonctions Employé -----------
    public static void ajouterLivre() {
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
          
        String genreStr = scanner.nextLine().trim().toUpperCase();

        Genre genre;
        try {
            genre = Genre.valueOf(genreStr);
        } catch (IllegalArgumentException e) {
        	System.out.println("Genre invalide ! Veuillez entrer roman, poesie ou fantasy.");
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

    public static void supprimerLivre() {
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

    public static void modifierLivre() {
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
    public static void afficherLivresDisponibles() {
        System.out.println("\n--- Livres disponibles ---");
        for (int i = 0; i < livres.size(); i++) {
            Livre l = livres.get(i);
            if (l.estDisponible()) {
                System.out.println(i + " - " + l.getTitre() + " (" + l.getAuteur() + ")");
            }
        }
    }

    public static void emprunterLivre( Adherent adherent) {
        afficherLivres();
        System.out.print("Index du livre à emprunter : ");
        int index = Integer.parseInt(scanner.nextLine());
        if (index >= 0 && index < livres.size()) {
            Livre l = livres.get(index);
            if (l.estDisponible()) {
                l.emprunter();
                System.out.println("Livre emprunté !");
                Emprunt emprunt = new Emprunt(l, adherent); // ou adhérent courant
                BibliothequeManager.getInstance().ajouterEmprunt(emprunt);

                System.out.println("✅ Livre emprunté !");
                System.out.println("📅 Date limite de retour : " + emprunt.getDateLimiteRetour());
            } else {
            	System.out.println("❌ Livre indisponible (État : " + l.getEtat() + ")");
                System.out.print("Souhaitez-vous le réserver ? (oui/non) : ");
                String rep = scanner.nextLine();
                if (rep.equalsIgnoreCase("oui")) {
                    l.reserver();
                    SystemeNotification.ajouterObserver(adherent);
                    System.out.println("📌 Livre réservé avec succès !");
                }
            }
        } else {
            System.out.println("Index invalide.");
        }
    }

    public static void retournerLivre(Adherent adherent) {
    	System.out.print("Index du livre à retourner : ");
    	int index = Integer.parseInt(scanner.nextLine());
    	Livre livre = livres.get(index);

    	// Récupérer l'emprunt lié à ce livre :
    	Emprunt emprunt = BibliothequeManager.getInstance().getEmpruntParLivre(livre);

    	if (emprunt != null) {
    	    emprunt.enregistrerRetour();
    	    livre.retourner();
    	    SystemeNotification.notifierTous("Le livre " + livre.getTitre() + " est maintenant disponible.");

    	    long retard = emprunt.getJoursDeRetard();
    	    if (retard > 0) {
    	        double amende = emprunt.calculerAmende();
    	        System.out.println("⏰ Vous avez " + retard + " jours de retard.");
    	        System.out.println("💸 Amende due : " + amende + " €");
    	    } else {
    	        System.out.println("✅ Livre rendu à temps. Merci !");
    	    }

    	    // supprimer l'emprunt de la liste ?
    	    BibliothequeManager.getInstance().supprimerEmprunt(emprunt);

    	} else {
    	    System.out.println("❌ Aucun emprunt associé à ce livre.");
    	}

    }

    public static void afficherLivres() {
        System.out.println("\n--- Liste des livres ---");
        for (int i = 0; i < livres.size(); i++) {
            Livre l = livres.get(i);
            System.out.println(i + " - " + l.getTitre() + " | " + l.getAuteur() + " | " + l.getGenre().name() + " | " + l.getEtat());
        }
    }
    
    public static void afficherLivresEnRetard() {
        List<Emprunt> emprunts = BibliothequeManager.getInstance().getEmprunts();

        System.out.println("\n📚📅 LIVRES EN RETARD :\n");

        boolean auMoinsUn = false;

        for (Emprunt e : emprunts) {
            if (e.getDateRetour() == null && e.getDateLimiteRetour().isBefore(LocalDate.now())) {
                auMoinsUn = true;

                long joursRetard = e.getJoursDeRetard();
                double amende = e.calculerAmende();

                System.out.println("📖 Livre : " + e.getLivre().getTitre());
                System.out.println("👤 Emprunté par : " + e.getAdherent().getPrenom() + " " + e.getAdherent().getNom());
                System.out.println("📅 Emprunté le : " + e.getDateEmprunt());
                System.out.println("🛑 Date limite : " + e.getDateLimiteRetour());
                System.out.println("⏳ Jours de retard : " + joursRetard);
                System.out.println("💸 Amende : " + amende + " €");
                System.out.println("──────────────────────────────\n");
            }
        }

        if (!auMoinsUn) {
            System.out.println("✅ Aucun livre en retard actuellement !");
        }
    }

    public static void rechercherLivre() {
        Scanner scanner = new Scanner(System.in);
        List<Livre> livres = BibliothequeManager.getInstance().getLivres();

        System.out.print("🔍 Entrez un mot-clé (titre, auteur ou ISBN) : ");
        String motCle = scanner.nextLine().toLowerCase();

        boolean trouve = false;
        System.out.println("\n🔎 Résultats de recherche :");
        for (Livre livre : livres) {
            if (livre.getTitre().toLowerCase().contains(motCle)
                    || livre.getAuteur().toLowerCase().contains(motCle)
                    || livre.getIsbn().toLowerCase().contains(motCle)) {
                System.out.println("📘 " + livre.getTitre() + " | " + livre.getAuteur() + " | ISBN : " + livre.getIsbn());
                trouve = true;
            }
        }

        if (!trouve) {
            System.out.println("❌ Aucun livre trouvé.");
        }
    }
    
    public static void rechercherAdherent() {
        Scanner scanner = new Scanner(System.in);
        List<Adherent> adherents = BibliothequeManager.getInstance().getAdherents();

        System.out.print("🔍 Entrez un mot-clé (nom ou email) : ");
        String motCle = scanner.nextLine().toLowerCase();

        boolean trouve = false;
        System.out.println("\n🔎 Résultats de recherche :");
        for (Adherent a : adherents) {
            if (a.getNom().toLowerCase().contains(motCle)
                    || a.getEmail().toLowerCase().contains(motCle)) {
                System.out.println("👤 " + a.getPrenom() + " " + a.getNom() + " | Email : " + a.getEmail());
                trouve = true;
            }
        }

        if (!trouve) {
            System.out.println("❌ Aucun adhérent trouvé.");
        }
    }


    
    
}
