package etat;

import model.Livre;

public class Reparation implements EtatLivre {

    @Override
    public void emprunter(Livre livre) {
        System.out.println("Le livre est en réparation. Emprunt impossible.");
    }

    @Override
    public void retourner(Livre livre) {
        System.out.println("Le livre est en réparation.");
    }

    @Override
    public void reserver(Livre livre) {
        System.out.println("Le livre est en réparation. Réservation impossible.");
    }

    @Override
    public void reparer(Livre livre) {
        livre.setEtat(new Disponible());
        System.out.println("La réparation est terminée. Le livre est maintenant disponible.");
    }

    @Override
    public String getEtat() {
        return "En réparation";
    }
}


