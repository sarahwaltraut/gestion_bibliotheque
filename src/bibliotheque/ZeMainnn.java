package bibliotheque;

import factory.Adherent;
import factory.Employe;
import singleton.BibliothequeManager;

import java.io.Console;
import java.util.Scanner;

import facade.BibliothequeFacade;

public class ZeMainnn {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BibliothequeFacade.menu();
    }
}