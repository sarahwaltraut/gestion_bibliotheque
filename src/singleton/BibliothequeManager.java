package singleton;

import factory.*;
import model.Emprunt;
import model.Livre;
import strategy.*;

import java.io.*;
import java.util.*;

public class BibliothequeManager {
    private static BibliothequeManager instance;
    private List<Livre> livres;
    private final String FICHIER_LIVRES = "livres.txt";
    private List<Adherent> adherents = new ArrayList<>();
    private final String FICHIER_ADHERENTS = "adherents.txt";
    
    
    private BibliothequeManager() {
        livres = new ArrayList<>();
        chargerLivres();
        chargerAdherents();

    }

    
    public static BibliothequeManager getInstance() {
        if (instance == null) {
            instance = new BibliothequeManager();
        }
        return instance;
    }

    public List<Livre> getLivres() {
        return livres;
    }
    
    public List<Adherent> getAdherents() {
        return adherents;
    }

    public void ajouterLivre(Livre livre) {
        livres.add(livre);
        sauvegarderLivres();
    }

    public void supprimerLivre(int index) {
        if (index >= 0 && index < livres.size()) {
            livres.remove(index);
            sauvegarderLivres();
        }
    }

    private void chargerLivres() {
        try (BufferedReader br = new BufferedReader(new FileReader(FICHIER_LIVRES))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] parts = ligne.split(";");
                if (parts.length < 8) continue;

                Genre genre = Genre.valueOf(parts[0]);
                String titre = parts[1];
                String auteur = parts[2];
                String isbn = parts[3];
                int annee = Integer.parseInt(parts[4]);
                int pages = Integer.parseInt(parts[5]);
                String langue = parts[6];
                int nbEmprunts = Integer.parseInt(parts[7]);

                StrategieAmende strategie = switch (genre) {
                    case ROMAN -> new AmendeRoman();
                    case POESIE -> new AmendePoesie();
                    case FANTASY -> new AmendeFantasy();
                };

                Livre livre = LivreFactory.creerLivre(genre, titre, auteur, isbn, annee, pages, langue, strategie);
                livre.setNbEmprunts(nbEmprunts);
                livres.add(livre);
            }
            //System.out.println("✅ Livres chargés depuis le fichier.");
        } catch (IOException e) {
            System.out.println("❌ Erreur de chargement des livres : " + e.getMessage());
        }
    }

    private void sauvegarderLivres() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHIER_LIVRES))) {
            for (Livre livre : livres) {
                String ligne = livre.getGenre().name() + ";" +
                        livre.getTitre() + ";" +
                        livre.getAuteur() + ";" +
                        livre.getIsbn() + ";" +
                        livre.getAnneePublication() + ";" +
                        livre.getNbPages() + ";" +
                        livre.getLangue() + ";" +
                        livre.getNbEmprunts();
                bw.write(ligne);
                bw.newLine();
            }
           // System.out.println("✅ Livres sauvegardés.");
        } catch (IOException e) {
            System.out.println("❌ Erreur de sauvegarde : " + e.getMessage());
        }
    }
    
    private void chargerAdherents() {
        try (BufferedReader br = new BufferedReader(new FileReader(FICHIER_ADHERENTS))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] parts = ligne.split(";");
                if (parts.length < 4) continue;
                String id = parts[0];
                String nom = parts[1];
                String prenom = parts[2];
                String email = parts[3];
                adherents.add(new Adherent(id, nom, prenom, email));
            }
            //System.out.println("✅ Adhérents chargés.");
        } catch (IOException e) {
            System.out.println("❌ Erreur de chargement des adhérents : " + e.getMessage());
        }
    }
    
    private void sauvegarderAdherents() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHIER_ADHERENTS))) {
            for (Adherent a : adherents) {
                String ligne = a.getId() + ";" + a.getNom() + ";" + a.getPrenom() + ";" + a.getEmail();
                bw.write(ligne);
                bw.newLine();
            }
            System.out.println("✅ Adhérents sauvegardés.");
        } catch (IOException e) {
            System.out.println("❌ Erreur de sauvegarde : " + e.getMessage());
        }
    }


    public void ajouterAdherent(Adherent a) {
        adherents.add(a);
        sauvegarderAdherents();
    }
    
    public void modifierAdherent(int index, String nom, String prenom, String email) {
        Adherent a = adherents.get(index);
        a.setNom(nom);
        a.setPrenom(prenom);
        a.setEmail(email);
        sauvegarderAdherents();
    }

    public void supprimerAdherent(int index) {
        if (index >= 0 && index < adherents.size()) {
            adherents.remove(index);
            sauvegarderAdherents();
        }
    }

    private List<Emprunt> emprunts = new ArrayList<>();

    public void ajouterEmprunt(Emprunt e) {
        emprunts.add(e);
    }

    public Emprunt getEmpruntParLivre(Livre livre) {
        for (Emprunt e : emprunts) {
            if (e.getLivre().equals(livre)) return e;
        }
        return null;
    }
    public List<Emprunt> getEmprunts() {
        return emprunts;
    }

    public void supprimerEmprunt(Emprunt e) {
        emprunts.remove(e);
    }

    public Adherent rechercherAdherentParEmail(String email) {
        for (Adherent a : adherents) {
            if (a.getEmail().equalsIgnoreCase(email)) {
                return a; // On retourne l’adhérent correspondant
            }
        }
        return null; // Aucun adhérent trouvé avec cet email
    }


}

