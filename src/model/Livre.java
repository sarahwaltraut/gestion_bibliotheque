package model;

import etat.Disponible;
import etat.EtatLivre;
import factory.Genre;
import strategy.StrategieAmende;

public abstract class Livre {
    protected String titre;
    protected String auteur;
    protected String isbn;
    protected int anneePublication;
    protected int nbPages;
    protected String langue;
    protected EtatLivre etat;
    protected StrategieAmende strategieAmende;

    public Livre(String titre, String auteur, String isbn, int anneePublication,
                 int nbPages, String langue, StrategieAmende strategieAmende) {
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.anneePublication = anneePublication;
        this.nbPages = nbPages;
        this.langue = langue;
        this.strategieAmende = strategieAmende;
        this.etat = new Disponible(); // Par défaut
    }

    // Méthode à définir par les sous-classes
    public abstract Genre getGenre();

    // Getters / setters utiles
    public void setEtat(EtatLivre etat) {
        this.etat = etat;
    }

    public EtatLivre getEtat() {
        return etat;
    }

}
