package factory;

import model.Livre;
import strategy.StrategieAmende;

public class Fantasy extends Livre {
    public Fantasy(String titre, String auteur, String isbn, int anneePublication, int nbPages, String langue, StrategieAmende strategie) {
        super(titre, auteur, isbn, anneePublication, nbPages, langue, nbPages, strategie);
    }

    public Genre getGenre() {
        return Genre.FANTASY;
    }
}
