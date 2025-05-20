package factory;

import model.Livre;
import strategy.StrategieAmende;

public class Roman extends Livre {
    public Roman(String titre, String auteur, String isbn, int anneePublication, int nbPages, String langue, StrategieAmende strategie) {
        super(titre, auteur, isbn, anneePublication, nbPages, langue, strategie);
    }

    public Genre getGenre() {
        return Genre.ROMAN;
    }
}
