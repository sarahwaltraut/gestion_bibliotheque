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
    protected int nbEmprunts;
    protected EtatLivre etat = new Disponible(); // par defaut
    protected StrategieAmende strategieAmende;

    public Livre(String titre, String auteur, String isbn, int anneePublication,
                 int nbPages, String langue,int nbEmprunts, StrategieAmende strategieAmende) {
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.anneePublication = anneePublication;
        this.nbPages = nbPages;
        this.langue = langue;
        this.nbEmprunts = nbEmprunts;
        this.strategieAmende = strategieAmende;
        this.etat = new Disponible(); // Par défaut
    }

    
    
    public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getNbEmprunts() {
		return nbEmprunts;
	}

	public void setNbEmprunts(int nbEmprunts) {
		this.nbEmprunts = nbEmprunts;
	}

	// Méthode à définir par les sous-classes
    public abstract Genre getGenre();

    // Getters / setters utiles
    public void setEtat(EtatLivre etat) {
        this.etat = etat;
    }

    public String getIsbn() {
		return isbn;
	}



	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}



	public int getAnneePublication() {
		return anneePublication;
	}



	public void setAnneePublication(int anneePublication) {
		this.anneePublication = anneePublication;
	}



	public int getNbPages() {
		return nbPages;
	}



	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}



	public String getLangue() {
		return langue;
	}



	public void setLangue(String langue) {
		this.langue = langue;
	}



	public EtatLivre getEtat() {
        return etat;
    }
    
    public String getAuteur() {
    	return auteur;
    }
    
    public void setStrategieAmende(StrategieAmende strategie) {
        this.strategieAmende = strategie;
    }

    
    public double calculerAmende(int joursDeRetard) {
        if (strategieAmende == null) return 0;
        return strategieAmende.calculerAmende(joursDeRetard);
    }

    // Méthodes pour déléguer les actions
    public void emprunter() {
        etat.emprunter(this);
    }

    public void retourner() {
        etat.retourner(this);
    }

    public void reserver() {
        etat.reserver(this);
    }

    public void reparer() {
        etat.reparer(this);
    }

    public boolean estDisponible() {
        return etat instanceof Disponible;
    }



}
