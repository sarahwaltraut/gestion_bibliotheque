package etat;

import model.Livre;

public class Reserve implements EtatLivre {

    @Override
    public void emprunter(Livre livre) {
        System.out.println("Le livre est réservé et ne peut pas être emprunté.");
    }

    @Override
    public void retourner(Livre livre) {
        livre.setEtat(new Disponible());
        System.out.println("Le livre est retourné, il est maintenant disponible.");
    }

    @Override
    public void reserver(Livre livre) {
        System.out.println("Le livre est déjà réservé.");
    }

    @Override
    public void reparer(Livre livre) {
        livre.setEtat(new Reparation());
        System.out.println("Le livre est envoyé en réparation.");
    }

    @Override
    public String getEtat() {
        return "Réservé";
    }
}
