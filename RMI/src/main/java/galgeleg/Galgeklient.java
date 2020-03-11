package galgeleg;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import game.GalgelegInterface;
import brugerautorisation.transport.Brugeradmin;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Galgeklient {
    public static void main(String[] args) throws Exception{

        GalgelegInterface logic = (GalgelegInterface) Naming.lookup("rmi://localhost:8081/logic");
        //GalgelegInterface glI =(GalgelegInterface) Naming.lookup("rmi://dist.saluton.dk:23609/kontotjeneste");
        logic.nulstil();
        Scanner scan = new Scanner(System.in);

        Brugeradmin brugeradmin = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
        System.out.println("Indtast studienummer: ");
        String studienummer = scan.nextLine();
        System.out.println("Indtast kode: ");
        String kode = scan.nextLine();
        if (logic.login(studienummer,kode)) {
            System.out.println("suck");
        }

        try {
            brugeradmin.hentBruger(studienummer,kode);
            System.out.println("Login autoriseret");
            runGalgeleg(logic, scan);
        } catch (IllegalArgumentException e) {
            System.out.println("Forkert brugernavn eller adgangskode. Spillet lukkes.");
        }


    }

    private static void runGalgeleg(GalgelegInterface glI, Scanner scan) throws RemoteException {
        glI.nulstil();
        System.out.println("-- Galgeleg starter --");

        while (!glI.erSpilletSlut()) {
            System.out.println("Gæt et bogstav: ");
            glI.gætBogstav(scan.nextLine());
            System.out.println(glI.getSynligtOrd());
            System.out.println(7 - glI.getAntalForkerteBogstaver() + " liv tilbage");
        }

        System.out.println(glI.erSpilletVundet() ? "Tillykke du vandt!" : "Du tabte desværre. Ordet du prøvede at gætte var: " + glI.getOrdet());
    }


}
