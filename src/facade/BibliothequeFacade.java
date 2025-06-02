package facade;

import java.util.Scanner;

import bibliotheque.Bibliotheque;
import factory.Adherent;
import factory.Employe;
import singleton.BibliothequeManager;

public class BibliothequeFacade {
	private static Scanner scanner = new Scanner(System.in);

	// ----------- Menu Employé -----------

    public static void menuEmploye() {
        int choix;
        do {
            System.out.println("\n╔══════════════ MENU EMPLOYÉ ══════════════╗");
            System.out.println("  1. Gestion des livres");
            System.out.println("  2. Gestion des adhérents");
            System.out.println("  0. Se déconnecter");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.print("👉 Choix : ");
            choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 1 -> menuGestionLivres();
                case 2 -> menuGestionAdherents();
                case 0 -> System.out.println("🔙 Déconnexion... Retour au menu principal.\n");
                default -> System.out.println("❌ Choix invalide.\n");
            }
        } while (choix != 0);
    }

    // Menu gestion des livres
    public static void menuGestionLivres() {
        int choix;
        do {
            System.out.println("\n📚 ═════ GESTION DES LIVRES ═════");
            System.out.println("  1. Ajouter un livre");
            System.out.println("  2. Supprimer un livre");
            System.out.println("  3. Modifier un livre");
            System.out.println("  4. Afficher tous les livres");
            System.out.println("  5. Rechercher un livre");
            System.out.println("  6. Voir statistiques (emprunts)");
            System.out.println("  7. Voir les livres en retard");
            System.out.println("  0. Retour");
            System.out.print("👉 Choix : ");
            choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 1 -> Bibliotheque.ajouterLivre();
                case 2 -> Bibliotheque.supprimerLivre();
                case 3 -> Bibliotheque.modifierLivre();
                case 4 -> Bibliotheque.afficherLivres();
                case 5 -> Bibliotheque.rechercherLivre();
                case 6 -> Employe.afficherStatsLivres();
                case 7 -> Bibliotheque.afficherLivresEnRetard();
                case 0 -> System.out.println("🔙 Retour au menu Employé.");
                default -> System.out.println("❌ Choix invalide.\n");
            }
        } while (choix != 0);
    }

    // Menu gestion des adhérents
    
    public static void menuGestionAdherents() {
        BibliothequeManager manager = BibliothequeManager.getInstance();
        int choix;

        do {
            System.out.println("\n👥 ═════ GESTION DES ADHÉRENTS ═════");
            System.out.println("  1. Voir tous les adhérents");
            System.out.println("  2. Rechercher un adhérent");
            System.out.println("  3. Ajouter un adhérent");
            System.out.println("  4. Modifier un adhérent");
            System.out.println("  5. Supprimer un adhérent");
            System.out.println("  0. Retour");
            System.out.print("👉 Choix : ");
            choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 1 -> {
                    var list = manager.getAdherents();
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + " - " + list.get(i));
                    }
                }
                case 2 -> Bibliotheque.rechercherAdherent();
                case 3 -> {
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prénom : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Email : ");
                    String email = scanner.nextLine();
                    String id = "id" + System.currentTimeMillis();
                    manager.ajouterAdherent(new factory.Adherent(id, nom, prenom, email));
                }
                case 4 -> {
                    System.out.print("Index à modifier : ");
                    int index = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nouveau nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Nouveau prénom : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Nouvel email : ");
                    String email = scanner.nextLine();
                    manager.modifierAdherent(index, nom, prenom, email);
                }
                case 5 -> {
                    System.out.print("Index à supprimer : ");
                    int index = Integer.parseInt(scanner.nextLine());
                    manager.supprimerAdherent(index);
                }
                case 0 -> System.out.println("🔙 Retour au menu Employé.");
                default -> System.out.println("❌ Choix invalide.");
            }

        } while (choix != 0);
    }
    
    // ----------- Menu Adhérent -----------

    public static void menuAdherent(Adherent adherent) {
        int choix;
        do {
            System.out.println("\n╔═══════════════ MENU ADHÉRENT ═══════════════╗");
            System.out.println("  1. Voir les livres de la Bibliothèque");
            System.out.println("  2. Rechercher un livre");
            System.out.println("  3. Emprunter un livre");
            System.out.println("  4. Retourner un livre");
            System.out.println("  0. Se déconnecter");
            System.out.println("╚═════════════════════════════════════════════╝");
            System.out.print("👉 Choix : ");
            choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 1 -> Bibliotheque.afficherLivres();
                case 2 -> Bibliotheque.rechercherLivre();
                case 3 -> Bibliotheque.emprunterLivre(adherent);
                case 4 -> Bibliotheque.retournerLivre(adherent);
                case 0 -> System.out.println("\n🔙 Déconnexion... Retour au menu principal.\n");
                default -> System.out.println("❌ Choix invalide.\n");
            }
        } while (choix != 0);
    }
}

