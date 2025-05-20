package etat;

import model.Livre;

public class Reserve implements EtatLivre {
    public void emprunter(Livre livre) {
        livre.setEtat(new Emprunte());
        System.out.println("Le livre réservé a été emprunté.");
    }

    public void retourner(Livre livre) {
        livre.setEtat(new Disponible());
        System.out.println("Le livre réservé est maintenant disponible.");
    }

    public void reserver(Livre livre) {
        System.out.println("Ce livre est déjà réservé.");
    }

    public void reparer(Livre livre) {
        livre.setEtat(new Reparation());
        System.out.println("Le livre réservé a été envoyé en réparation.");
    }

    public String getEtat() {
        return "Réservé";
    }

}

