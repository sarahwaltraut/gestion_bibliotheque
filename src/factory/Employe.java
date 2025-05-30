package factory;

import model.Utilisateur;
import singleton.BibliothequeManager;
import model.Livre;

import java.util.List;

public class Employe extends Utilisateur {
    private static final String MOT_DE_PASSE_EMPLOYE = "admin123";

    public Employe(String nom, String prenom, String email, String id) {
        super(nom, prenom, email, id);
    }

    public static boolean verifierMotDePasse(String motDePasseEntre) {
        return MOT_DE_PASSE_EMPLOYE.equals(motDePasseEntre);
    }

    public void ajouterLivre(List<Livre> livres, Livre nouveau) {
        livres.add(nouveau);
        System.out.println("Livre ajouté : " + nouveau.getTitre());
    }

    public void modifierLivre(Livre livre, String nouveauTitre) {
        livre.setTitre(nouveauTitre);
        System.out.println("Titre du livre modifié : " + nouveauTitre);
    }

    public void supprimerLivre(List<Livre> livres, Livre livre) {
        livres.remove(livre);
        System.out.println("Livre supprimé : " + livre.getTitre());
    }

    public static void afficherStatsLivres() {
        var livres = BibliothequeManager.getInstance().getLivres();

        System.out.println("\n📊 Livres les plus empruntés :");
        livres.stream()
            .sorted((l1, l2) -> l2.getNbEmprunts() - l1.getNbEmprunts())
            .limit(5)
            .forEach(l -> System.out.println(l.getTitre() + " (" + l.getNbEmprunts() + " emprunts)"));

        
    }

}

