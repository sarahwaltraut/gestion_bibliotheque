package bibliotheque;

import factory.Employe;
import java.util.Scanner;

public class ZeMainnn {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n╔═════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                         📚  BIBLIOTHÈQUE CENTRALE                   ║");
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
                String motDePasse = scanner.nextLine();
                if (!Employe.verifierMotDePasse(motDePasse)) {
                    System.out.println("❌ Mot de passe incorrect.");
                } else {
                    menuEmploye();
                }
            } else if (choix == 1) {
                menuAdherent();
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
            System.out.println("\n╔═══════════════ MENU EMPLOYÉ ═══════════════╗");
            System.out.println("  1. Ajouter un livre");
            System.out.println("  2. Supprimer un livre");
            System.out.println("  3. Modifier un livre");
            System.out.println("  4. Afficher les livres");
            System.out.println("  5. Chercher un livres");
            System.out.println("  0. Se déconnecter");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("👉 Choix : ");
            choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 1 -> Bibliotheque.ajouterLivre();
                case 2 -> Bibliotheque.supprimerLivre();
                case 3 -> Bibliotheque.modifierLivre();
                case 4 -> Bibliotheque.afficherLivres();
                case 5 -> Bibliotheque.chercherLivre();
                case 0 -> System.out.println("\n🔙 Déconnexion... Retour au menu principal.\n");
                default -> System.out.println("❌ Choix invalide.\n");
            }
        } while (choix != 0);
    }

    // ----------- Menu Adhérent -----------

    private static void menuAdherent() {
        int choix;
        do {
            System.out.println("\n╔═══════════════ MENU ADHÉRENT ═══════════════╗");
            System.out.println("  1. Afficher les livres disponibles");
            System.out.println("  2. Emprunter un livre");
            System.out.println("  3. Retourner un livre");
            
            System.out.println("  4. chercher un livre");
            System.out.println("  0. Se déconnecter");
            System.out.println("╚═════════════════════════════════════════════╝");
            System.out.print("👉 Choix : ");
            choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 1 -> Bibliotheque.afficherLivresDisponibles();
                case 2 -> Bibliotheque.emprunterLivre();
                case 3 -> Bibliotheque.retournerLivre();
                case 4 -> Bibliotheque.chercherLivre();
                case 0 -> System.out.println("\n🔙 Déconnexion... Retour au menu principal.\n");
                default -> System.out.println("❌ Choix invalide.\n");
            }
        } while (choix != 0);
    }
}
