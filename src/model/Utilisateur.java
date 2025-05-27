package model;

public abstract class Utilisateur {
    protected String nom;
    protected String prenom;
    protected String email;
    protected String id;

    public Utilisateur(String nom, String prenom, String email, String id) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.id = id;
    }

    // Getters & setters
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

    public String getEmail() { 
    	return email; 
    }
    
    public void setEmail(String email) { 
    	this.email = email; 
    }

    public String getId() { 
    	return id; 
    }
    
    public void setId(String id) { 
    	this.id = id; 
    }

    @Override
    public String toString() {
        return "Nom: " + nom + ", Prénom: " + prenom + ", Email: " + email + ", ID: " + id;
    }
}
