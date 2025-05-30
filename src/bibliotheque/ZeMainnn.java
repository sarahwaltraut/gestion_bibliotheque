package bibliotheque;

import factory.Adherent;
import factory.Employe;
import singleton.BibliothequeManager;

import java.io.Console;
import java.util.Scanner;

public class ZeMainnn {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n╔═════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                         📚  BIBLIOTHÈQUE CENTRALE                    ║");
            System.out.println("╚═════════════════════════════════════════════════════════════════════╝\n");

            System.out.println("Qui êtes-vous ?");
            System.out.println("  1. Adhérent");
            System.out.println("  2. Employé");
            System.out.println("  0. Quitter\n");
            System.out.print("👉 Choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // consommer le retour à la ligne

            if (choix == 2) {
                System.out.print("\n🔐 Veuillez entrer le mot de passe : ");
                Console console = System.console();
                char[] mdpChars = console.readPassword();  // ➤ Masque l'entrée
                String motDePasse = new String(mdpChars);
                if (!Employe.verifierMotDePasse(motDePasse)) {
                    System.out.println("❌ Mot de passe incorrect.");
                } else {
                    menuEmploye();
                }
            } else if (choix == 1) {
            	System.out.print("Veuillez entrer votre email : ");
            	String email = scanner.nextLine();

            	Adherent adherent = BibliothequeManager.getInstance().rechercherAdherentParEmail(email);

            	if (adherent != null) {
            	    menuAdherent(adherent);  // ou continuer les actions avec cet adhérent
            	} else {
            	    System.out.println("❌ Aucun adhérent trouvé avec cet email.");
            	}
            } else if (choix == 0) {
                System.out.println("\n👋 Merci de votre visite. À bientôt !");
                break;
            } else {
                System.out.println("❌ Choix invalide.\n");
            }
        }
    }

    // ----------- Menu Employé -----------

    private static void menuEmploye() {
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
    private static void menuGestionLivres() {
        int choix;
        do {
            System.out.println("\n📚 ═════ GESTION DES LIVRES ═════");
            System.out.println("  1. Ajouter un livre");
            System.out.println("  2. Supprimer un livre");
            System.out.println("  3. Modifier un livre");
            System.out.println("  4. Afficher tous les livres");
            System.out.println("  5. Voir statistiques (emprunts)");
            System.out.println("  6. Voir les livres en retard");
            System.out.println("  0. Retour");
            System.out.print("👉 Choix : ");
            choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 1 -> Bibliotheque.ajouterLivre();
                case 2 -> Bibliotheque.supprimerLivre();
                case 3 -> Bibliotheque.modifierLivre();
                case 4 -> Bibliotheque.afficherLivres();
                case 5 -> Employe.afficherStatsLivres();
                case 6 -> Bibliotheque.afficherLivresEnRetard();
                case 0 -> System.out.println("🔙 Retour au menu Employé.");
                default -> System.out.println("❌ Choix invalide.\n");
            }
        } while (choix != 0);
    }

    // Menu gestion des adhérents
    
    private static void menuGestionAdherents() {
        BibliothequeManager manager = BibliothequeManager.getInstance();
        int choix;

        do {
            System.out.println("\n👥 ═════ GESTION DES ADHÉRENTS ═════");
            System.out.println("  1. Voir tous les adhérents");
            System.out.println("  2. Ajouter un adhérent");
            System.out.println("  3. Modifier un adhérent");
            System.out.println("  4. Supprimer un adhérent");
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
                case 2 -> {
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prénom : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Email : ");
                    String email = scanner.nextLine();
                    String id = "id" + System.currentTimeMillis();
                    manager.ajouterAdherent(new factory.Adherent(id, nom, prenom, email));
                }
                case 3 -> {
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
                case 4 -> {
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

    private static void menuAdherent(Adherent adherent) {
        int choix;
        do {
            System.out.println("\n╔═══════════════ MENU ADHÉRENT ═══════════════╗");
            System.out.println("  1. Voir les livres de la Bibliothèque");
            System.out.println("  2. Emprunter un livre");
            System.out.println("  3. Retourner un livre");
            System.out.println("  0. Se déconnecter");
            System.out.println("╚═════════════════════════════════════════════╝");
            System.out.print("👉 Choix : ");
            choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 1 -> Bibliotheque.afficherLivres();
                case 2 -> Bibliotheque.emprunterLivre(adherent);
                case 3 -> Bibliotheque.retournerLivre(adherent);
                case 0 -> System.out.println("\n🔙 Déconnexion... Retour au menu principal.\n");
                default -> System.out.println("❌ Choix invalide.\n");
            }
        } while (choix != 0);
    }
}
