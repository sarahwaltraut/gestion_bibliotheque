package etat;

import model.Livre;

public class Disponible implements EtatLivre {

    @Override
    public void emprunter(Livre livre) {
        livre.setEtat(new Emprunte());
        System.out.println("Le livre a été emprunté.");
    }

    @Override
    public void retourner(Livre livre) {
        System.out.println("Le livre est déjà disponible.");
    }

    @Override
    public void reserver(Livre livre) {
        livre.setEtat(new Reserve());
        System.out.println("Le livre a été réservé.");
    }

    @Override
    public void reparer(Livre livre) {
        livre.setEtat(new Reparation());
        System.out.println("Le livre part en réparation.");
    }

	@Override
	public String getEtat() {
		return "Disponible";
	}
}
