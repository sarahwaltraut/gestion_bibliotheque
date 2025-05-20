package etat;

import model.Livre;

public class Disponible implements EtatLivre {
    public void emprunter(Livre livre) {
        livre.setEtat(new Emprunte());
        System.out.println("Livre emprunté.");
    }

    public void retourner(Livre livre) {
        System.out.println("Le livre est déjà disponible.");
    }

    public void reparer(Livre livre) {
        livre.setEtat(new Reparation());
    }

    public void reserver(Livre livre) {
        livre.setEtat(new Reserve());
    }

    public String getEtat() {
        return "Disponible";
    }
}

