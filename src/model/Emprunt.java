package model;

import java.time.LocalDate;

public class Emprunt {
    private Utilisateur emprunteur;
    private Livre livre;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;

    public Emprunt(Utilisateur emprunteur, Livre livre, LocalDate dateEmprunt, LocalDate dateRetourPrevue) {
        this.emprunteur = emprunteur;
        this.livre = livre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public Utilisateur getEmprunteur() {
        return emprunteur;
    }

    public Livre getLivre() {
        return livre;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }
}


