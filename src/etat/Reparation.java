package etat;

import model.Livre;

public class Reparation implements EtatLivre {
    public void emprunter(Livre livre) {
        System.out.println("Ce livre est en réparation, impossible de l’emprunter.");
    }

    public void retourner(Livre livre) {
        System.out.println("Ce livre est en réparation.");
    }

    public void reserver(Livre livre) {
        System.out.println("Ce livre est en réparation, impossible de le réserver.");
    }

    public void reparer(Livre livre) {
        livre.setEtat(new Disponible());
        System.out.println("Le livre est réparé et maintenant disponible.");
    }

    public String getEtat() {
        return "En Réparation";
    }

}

