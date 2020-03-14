package com.example.rest;


import game.GalgelegInterface;
import org.springframework.web.bind.annotation.*;

import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.Brugeradmin;
import kong.unirest.json.JSONObject;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MessageController {
    private GalgelegInterface logik;

    public void getInfo() throws RemoteException, NotBoundException, MalformedURLException {
        logik = (GalgelegInterface) Naming.lookup("rmi://localhost:8081/logic");
        logik.nulstil();
    }


    @GetMapping("/newgame")
    public Game getGame() throws RemoteException, NotBoundException, MalformedURLException {
        getInfo();
        System.out.println(logik.erSpilletSlut());
        return new Game(logik.getSynligtOrd(), logik.getAntalForkerteBogstaver(),
                logik.getBrugteBogstaver(), logik.erSpilletSlut(), "-- Nyt spil er startet --",
                logik.erSpilletVundet());
    }

    @PostMapping("/guess")
    public Game guessLetter(@RequestBody String guess) throws RemoteException {
        System.out.println(guess);
        logik.gætBogstav(guess);
        return new Game(logik.getSynligtOrd(), logik.getAntalForkerteBogstaver(),
                logik.getBrugteBogstaver(), logik.erSpilletSlut()
                , "Du gættede på " + guess, logik.erSpilletVundet());
    }

    @PostMapping("/login")
    public boolean login(@RequestBody String login) throws Exception {
        System.out.println(login);
        JSONObject json = new JSONObject(login);
        System.out.println(json);
        System.out.println(json.getString("user"));
        System.out.println(json.getString("pass"));
        Brugeradmin brugeradmin = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
        try {
            Bruger nytLogin = brugeradmin.hentBruger(json.getString("user"), json.getString("pass"));
            System.out.println(nytLogin.email);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Login not authorized");
        }
        return false;

    }

}