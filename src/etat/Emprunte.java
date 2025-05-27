package etat;

import model.Livre;

public class Emprunte implements EtatLivre {

    @Override
    public void emprunter(Livre livre) {
        System.out.println("Le livre est déjà emprunté.");
    }

    @Override
    public void retourner(Livre livre) {
        livre.setEtat(new Disponible());
        System.out.println("Le livre a été retourné et est maintenant disponible.");
    }

    @Override
    public void reserver(Livre livre) {
        System.out.println("Le livre est emprunté, vous pouvez le réserver quand il sera retourné.");
    }

    @Override
    public void reparer(Livre livre) {
        livre.setEtat(new Reparation());
        System.out.println("Le livre est envoyé en réparation.");
    }

    @Override
    public String getEtat() {
        return "Emprunté";
    }
}
