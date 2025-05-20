package factory;

import java.util.ArrayList;
import java.util.List;
import model.*;
import java.util.Date;

/**
 * Classe représentant un adhérent de la bibliothèque
 */
	public class Adherent {
		
	    private int id;
	    private String nom;
	    private String prenom;
	    private String adresse;
	    private String email; 
	    private String telephone;
	    private Date dateInscription; 
	    private List<Emprunt> empruntsEnCours; 
    
    /**
     * Constructeur pour créer un nouvel adhérent
     * @param id identifiant unique
     * @param nom nom de famille
     * @param prenom prénom
     * @param adresse adresse postale
     * @param email adresse email
     * @param telephone numéro de téléphone
     */
    public Adherent(int id, String nom, String prenom, String adresse, String email, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.dateInscription = new Date(); // date courante par défaut
        this.empruntsEnCours = new ArrayList<>();
    }
    
    // Getters et Setters
    public int getId() {
        return id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String getAdresse() {
        return adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public Date getDateInscription() {
        return dateInscription;
    }
    
    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }
    
    public List<Emprunt> getEmpruntsEnCours() {
        return new ArrayList<>(empruntsEnCours); // retourne une copie pour protéger l'encapsulation
    }
    
    /**
     * Ajoute un emprunt à la liste des emprunts en cours
     * @param emprunt l'emprunt à ajouter
     * @throws IllegalStateException si l'adhérent a déjà trop d'emprunts
     */
    public void ajouterEmprunt(Emprunt emprunt) {
        if (empruntsEnCours.size() >= 5) { // limite de 5 emprunts
            throw new IllegalStateException("Cet adhérent a déjà le maximum d'emprunts autorisés.");
        }
        empruntsEnCours.add(emprunt);
    }
    
    /**
     * Retire un emprunt de la liste des emprunts en cours
     * @param emprunt l'emprunt à retirer
     * @return true si l'emprunt a été trouvé et retiré, false sinon
     */
    public boolean retirerEmprunt(Emprunt emprunt) {
        return empruntsEnCours.remove(emprunt);
    }
    
    /**
     * Vérifie si l'adhérent a des emprunts en retard
     * @return true si au moins un emprunt est en retard, false sinon
     */
    /*public boolean aDesEmpruntsEnRetard() {
        Date aujourdhui = new Date();
        for (Emprunt emprunt : empruntsEnCours) {
            if (emprunt.getDateRetourPrevue().before(aujourdhui)) {
                return true;
            }
        }
        return false;
    }*/
    
    /**
     * Calcule le nombre total d'emprunts en cours
     * @return le nombre d'emprunts en cours
     */
    public int nombreEmpruntsEnCours() {
        return empruntsEnCours.size();
    }
    
    @Override
    public String toString() {
        return "Adhérent #" + id + ": " + prenom + " " + nom + " (" + email + ")";
    }
    
    /**
     * Méthode pour afficher les détails complets de l'adhérent
     * @return une chaîne avec toutes les informations
     */
    public String afficherDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Détails de l'adhérent ===\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Nom: ").append(nom).append("\n");
        sb.append("Prénom: ").append(prenom).append("\n");
        sb.append("Adresse: ").append(adresse).append("\n");
        sb.append("Email: ").append(email).append("\n");
        sb.append("Téléphone: ").append(telephone).append("\n");
        sb.append("Date d'inscription: ").append(dateInscription).append("\n");
        sb.append("Nombre d'emprunts en cours: ").append(nombreEmpruntsEnCours()).append("\n");
        
        /*if (!empruntsEnCours.isEmpty()) {
            sb.append("\nEmprunts en cours:\n");
            for (Emprunt emprunt : empruntsEnCours) {
                sb.append("- ").append(emprunt.getLivre().getTitre())
                  .append(" (à rendre avant ").append(emprunt.getDateRetourPrevue()).append(")\n");
            }
        }*/
        
        return sb.toString();
    }


}
