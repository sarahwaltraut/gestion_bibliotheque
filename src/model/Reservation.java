package model;

import java.time.LocalDate;

public class Reservation {
    private Utilisateur utilisateur;
    private Livre livre;
    private LocalDate dateReservation;

    public Reservation(Utilisateur utilisateur, Livre livre, LocalDate dateReservation) {
        this.utilisateur = utilisateur;
        this.livre = livre;
        this.dateReservation = dateReservation;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Livre getLivre() {
        return livre;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }
}

