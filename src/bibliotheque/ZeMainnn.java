package bibliotheque;

import factory.Adherent;
import factory.Employe;
import singleton.BibliothequeManager;

import java.io.Console;
import java.util.Scanner;

import facade.BibliothequeFacade;

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
                    BibliothequeFacade.menuEmploye();
                }
            } else if (choix == 1) {
            	System.out.print("Veuillez entrer votre email : ");
            	String email = scanner.nextLine();

            	Adherent adherent = BibliothequeManager.getInstance().rechercherAdherentParEmail(email);

            	if (adherent != null) {
            		BibliothequeFacade.menuAdherent(adherent);  // ou continuer les actions avec cet adhérent
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
}