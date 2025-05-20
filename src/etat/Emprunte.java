package etat;

import model.Livre;

public class Emprunte implements EtatLivre {
    public void emprunter(Livre livre) {
        System.out.println("Le livre est déjà emprunté.");
    }

    public void retourner(Livre livre) {
        livre.setEtat(new Disponible());
        System.out.println("Le livre est maintenant disponible.");
    }

    public void reserver(Livre livre) {
        System.out.println("Ce livre est déjà emprunté, impossible de le réserver.");
    }

    public void reparer(Livre livre) {
        livre.setEtat(new Reparation());
        System.out.println("Le livre a été envoyé en réparation.");
    }

    public String getEtat() {
        return "Emprunté";
    }

}

