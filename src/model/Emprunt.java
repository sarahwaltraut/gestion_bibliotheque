package model;

import java.time.LocalDate;

import factory.Adherent;

public class Emprunt {
    private Livre livre;
    private Adherent emprunteur;
    private LocalDate dateEmprunt;
    private LocalDate dateLimiteRetour;
    private LocalDate dateRetour; // à définir quand le livre est rendu

    public Emprunt(Livre livre, Adherent emprunteur) {
        this.livre = livre;
        this.emprunteur = emprunteur;
        this.dateEmprunt = LocalDate.now();
        this.dateLimiteRetour = dateEmprunt.plusDays(14); // 14 jours par défaut
    }

    public void enregistrerRetour() {
        this.dateRetour = LocalDate.now();
    }

    public long getJoursDeRetard() {
        if (dateRetour == null || !dateRetour.isAfter(dateLimiteRetour)) {
            return 0;
        }
        return java.time.temporal.ChronoUnit.DAYS.between(dateLimiteRetour, dateRetour);
    }

    public double calculerAmende() {
        long joursDeRetard = getJoursDeRetard();
        return livre.calculerAmende((int) joursDeRetard);
    }

    public Livre getLivre() {
        return livre;
    }

    public Adherent getAdherent() {
        return emprunteur;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public LocalDate getDateLimiteRetour() {
        return dateLimiteRetour;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }
}
