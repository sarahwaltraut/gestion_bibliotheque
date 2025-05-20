package etat;

import model.Livre;

public interface EtatLivre {
    void emprunter(Livre livre);
    void retourner(Livre livre);
    void reparer(Livre livre);
    void reserver(Livre livre);
    String getEtat();
}

