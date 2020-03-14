package com.example.rest;

import game.brugerautorisation.transport.rmi.Brugeradmin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AuthenticationController {

    @PostMapping("/login")
    public ResponseEntity login(@RequestParam("user") String user, @RequestParam("pass") String pass) throws RemoteException, NotBoundException, MalformedURLException {
        Brugeradmin brugeradmin = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");

        brugeradmin.hentBruger(user,pass);

        return new ResponseEntity(HttpStatus.OK);
    }


}


/*

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

*/

