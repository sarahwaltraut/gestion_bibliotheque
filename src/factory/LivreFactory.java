package factory;

import model.Livre;
import strategy.StrategieAmende;

public class LivreFactory {
    public static Livre creerLivre(Genre genre, String titre, String auteur, String isbn, int annee,
                                   int nbPages, String langue, StrategieAmende strategie) {
        switch (genre) {
            case ROMAN:
                return new Roman(titre, auteur, isbn, annee, nbPages, langue, strategie);
            case POESIE:
                return new Poesie(titre, auteur, isbn, annee, nbPages, langue, strategie);
            case FANTASY:
                return new Fantasy(titre, auteur, isbn, annee, nbPages, langue, strategie);
            default:
                throw new IllegalArgumentException("Genre inconnu");
        }
    }
}

