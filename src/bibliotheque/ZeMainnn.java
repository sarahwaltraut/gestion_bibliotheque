package bibliotheque;
import factory.*;
import model.Livre;
import strategy.*;

import java.util.*;

public class ZeMainnn {

	    private static List<Livre> livres = new ArrayList<>();
	    private static Scanner scanner = new Scanner(System.in);

	    public static void main(String[] args) {
	    	 System.out.println("Bienvenue à la bibliothèque !");
	         System.out.println("Êtes-vous un : \n1. Adhérent \n2. Employé");
	         int choix = scanner.nextInt();
	         scanner.nextLine(); // pour consommer le retour à la ligne

	         if (choix == 2) {
	             System.out.print("Veuillez entrer le mot de passe : ");
	             String motDePasse = scanner.nextLine();
	             if (!Employe.verifierMotDePasse(motDePasse)) {
	                 System.out.println("Mot de passe incorrect. Retour au menu principal.");
	                 return;
	             }
	             menuEmploye();
	         } else if (choix == 1) {
	             menuAdherent();
	         } else {
	             System.out.println("Choix invalide.");
	         }
	    }

	    // ----------- Menu Employé -----------
	    private static void menuEmploye() {
	        int choix;
	        do {
	            System.out.println("\n===== Menu Employé =====");
	            System.out.println("1. Ajouter un livre");
	            System.out.println("2. Supprimer un livre");
	            System.out.println("3. Modifier un livre");
	            System.out.println("4. Afficher les livres");
	            System.out.println("0. Quitter");
	            System.out.print("Choix : ");
	            choix = Integer.parseInt(scanner.nextLine());

	            switch (choix) {
	                case 1 -> Bibliotheque.ajouterLivre();
	                case 2 -> Bibliotheque.supprimerLivre();
	                case 3 -> Bibliotheque.modifierLivre();
	                case 4 -> Bibliotheque.afficherLivres();
	                case 0 -> System.out.println("Déconnexion...");
	                default -> System.out.println("Choix invalide.");
	            }
	        } while (choix != 0);
	    }

	    // ----------- Menu Adhérent -----------
	    private static void menuAdherent() {
	        int choix;
	        do {
	            System.out.println("\n===== Menu Adhérent =====");
	            System.out.println("1. Afficher les livres disponibles");
	            System.out.println("2. Emprunter un livre");
	            System.out.println("3. Retourner un livre");
	            System.out.println("0. Quitter");
	            System.out.print("Choix : ");
	            choix = Integer.parseInt(scanner.nextLine());

	            switch (choix) {
	                case 1 -> Bibliotheque.afficherLivresDisponibles();
	                case 2 -> Bibliotheque.emprunterLivre();
	                case 3 -> Bibliotheque.retournerLivre();
	                case 0 -> System.out.println("Déconnexion...");
	                default -> System.out.println("Choix invalide.");
	            }
	        } while (choix != 0);
	    }


}
